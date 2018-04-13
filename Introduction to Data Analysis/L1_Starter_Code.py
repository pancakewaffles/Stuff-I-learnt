
# coding: utf-8

# Before we get started, a couple of reminders to keep in mind when using iPython notebooks:
# 
# - Remember that you can see from the left side of a code cell when it was last run if there is a number within the brackets.
# - When you start a new notebook session, make sure you run all of the cells up to the point where you last left off. Even if the output is still visible from when you ran the cells in your previous session, the kernel starts in a fresh state so you'll need to reload the data, etc. on a new session.
# - The previous point is useful to keep in mind if your answers do not match what is expected in the lesson's quizzes. Try reloading the data and run all of the processing steps one by one in order to make sure that you are working with the same variables and data that are at each quiz stage.
# 
# 
# ## Load Data from CSVs

# In[5]:


import unicodecsv

## Longer version of code (replaced with shorter, equivalent version below)

# enrollments = []
# f = open('enrollments.csv', 'rb')
# reader = unicodecsv.DictReader(f)
# for row in reader:
#     enrollments.append(row)
# f.close()

with open('enrollments.csv', 'rb') as f:
    reader = unicodecsv.DictReader(f)
    enrollments = list(reader)
    


# In[6]:


#####################################
#                 1                 #
#####################################

## Read in the data from daily_engagement.csv and project_submissions.csv 
## and store the results in the below variables.
## Then look at the first row of each table.
with open("daily_engagement.csv","rb") as f:
    reader = unicodecsv.DictReader(f);
    daily_engagement = list(reader);
with open("project_submissions.csv","rb") as f:
    reader = unicodecsv.DictReader(f);
    project_submissions = list(reader);


# ## Fixing Data Types

# In[7]:


from datetime import datetime as dt

# Takes a date as a string, and returns a Python datetime object. 
# If there is no date given, returns None
def parse_date(date):
    if date == '':
        return None
    else:
        return dt.strptime(date, '%Y-%m-%d')
    
# Takes a string which is either an empty string or represents an integer,
# and returns an int or None.
def parse_maybe_int(i):
    if i == '':
        return None
    else:
        return int(i)

# Clean up the data types in the enrollments table
for enrollment in enrollments:
    enrollment['cancel_date'] = parse_date(enrollment['cancel_date'])
    enrollment['days_to_cancel'] = parse_maybe_int(enrollment['days_to_cancel'])
    enrollment['is_canceled'] = enrollment['is_canceled'] == 'True'
    enrollment['is_udacity'] = enrollment['is_udacity'] == 'True'
    enrollment['join_date'] = parse_date(enrollment['join_date'])
    
enrollments[0]


# In[8]:


# Clean up the data types in the engagement table
for engagement_record in daily_engagement:
    engagement_record['lessons_completed'] = int(float(engagement_record['lessons_completed']))
    engagement_record['num_courses_visited'] = int(float(engagement_record['num_courses_visited']))
    engagement_record['projects_completed'] = int(float(engagement_record['projects_completed']))
    engagement_record['total_minutes_visited'] = float(engagement_record['total_minutes_visited'])
    engagement_record['utc_date'] = parse_date(engagement_record['utc_date'])
    
daily_engagement[0]


# In[9]:


# Clean up the data types in the submissions table
for submission in project_submissions:
    submission['completion_date'] = parse_date(submission['completion_date'])
    submission['creation_date'] = parse_date(submission['creation_date'])

project_submissions[0]


# Note when running the above cells that we are actively changing the contents of our data variables. If you try to run these cells multiple times in the same session, an error will occur.
# 
# ## Investigating the Data

# In[12]:


#####################################
#                 2                 #
#####################################

## Find the total number of rows and the number of unique students (account keys)
## in each table.
def get_unique_students(dictionary):
    students = [];
    for entry in dictionary:
        students.append(entry["account_key"]);
    
    return set(students);

print len(enrollments)
unique_enrolled_students = get_unique_students(enrollments)
print len(unique_enrolled_students)
print len(daily_engagement)
unique_engagement_students = get_unique_students(daily_engagement)
print len(unique_engagement_students)
print len(project_submissions)
unique_project_submitters = get_unique_students(project_submissions)
print len(unique_project_submitters)


# ## Problems in the Data

# In[11]:


#####################################
#                 3                 #
#####################################

## Rename the "acct" column in the daily_engagement table to "account_key".
for engagement in daily_engagement:
    temp = engagement["acct"];
    del engagement["acct"];
    engagement.update({"account_key":temp});
    


# ## Missing Engagement Records

# In[13]:


#####################################
#                 4                 #
#####################################

## Find any one student enrollments where the student is missing from the daily engagement table.
## Output that enrollment.
for enrollment in enrollments:
    student = enrollment["account_key"];
    if student not in unique_engagement_students:
        print enrollment;
                  


# ## Checking for More Problem Records

# In[14]:


#####################################
#                 5                 #
#####################################

## Find the number of surprising data points (enrollments missing from
## the engagement table) that remain, if any.
count = 0;
for enrollment in enrollments:
    student = enrollment["account_key"];
    if student not in unique_engagement_students:
        if enrollment["join_date"] != enrollment["cancel_date"]:
            
            count+=1;
            print enrollment;
print count;        


# ## Tracking Down the Remaining Problems

# In[15]:


# Create a set of the account keys for all Udacity test accounts
udacity_test_accounts = set()
for enrollment in enrollments:
    if enrollment['is_udacity']:
        udacity_test_accounts.add(enrollment['account_key'])
len(udacity_test_accounts)


# In[16]:


# Given some data with an account_key field, removes any records corresponding to Udacity test accounts
def remove_udacity_accounts(data):
    non_udacity_data = []
    for data_point in data:
        if data_point['account_key'] not in udacity_test_accounts:
            non_udacity_data.append(data_point)
    return non_udacity_data


# In[17]:


# Remove Udacity test accounts from all three tables
non_udacity_enrollments = remove_udacity_accounts(enrollments)
non_udacity_engagement = remove_udacity_accounts(daily_engagement)
non_udacity_submissions = remove_udacity_accounts(project_submissions)

print len(non_udacity_enrollments)
print len(non_udacity_engagement)
print len(non_udacity_submissions)


# ## Refining the Question

# In[18]:


#####################################
#                 6                 #
#####################################

## Create a dictionary named paid_students containing all students who either
## haven't canceled yet or who remained enrolled for more than 7 days. The keys
## should be account keys, and the values should be the date the student enrolled.

paid_students = {};
for enrollment in non_udacity_enrollments:
    if enrollment["days_to_cancel"] is None     or enrollment["days_to_cancel"] > 7:
        accountkey = enrollment["account_key"];
        enrollment_date = enrollment["join_date"];
        
        if accountkey not in paid_students or         enrollment["join_date"] > paid_students[accountkey]:
            paid_students[accountkey] = enrollment_date;
        

print len(paid_students);


# ## Getting Data from First Week

# In[19]:


# Takes a student's join date and the date of a specific engagement record,
# and returns True if that engagement record happened within one week
# of the student joining.
def within_one_week(join_date, engagement_date):
    time_delta = engagement_date - join_date
    return time_delta.days < 7 and engagement_date >= join_date


# In[20]:


#####################################
#                 7                 #
#####################################

## Create a list of rows from the engagement table including only rows where
## the student is one of the paid students you just found, and the date is within
## one week of the student's join date.
paid_engagement_in_first_week = [];
for engagement in non_udacity_engagement:
    if engagement["account_key"] in paid_students and     within_one_week(paid_students[engagement["account_key"]] , engagement["utc_date"]):
        paid_engagement_in_first_week.append(engagement);

print len(paid_engagement_in_first_week)


# ## Exploring Student Engagement

# In[21]:


from collections import defaultdict

# Create a dictionary of engagement grouped by student.
# The keys are account keys, and the values are lists of engagement records.
def group_data(data,key):
    
    engagement_by_account = defaultdict(list)
    for engagement_record in data:
        account_key = engagement_record[key]
        engagement_by_account[account_key].append(engagement_record)
    return engagement_by_account;

engagement_by_account = group_data(paid_engagement_in_first_week,"account_key")


# In[22]:


# Create a dictionary with the total minutes each student spent in the classroom during the first week.
# The keys are account keys, and the values are numbers (total minutes)
total_minutes_by_account = {}
for account_key, engagement_for_student in engagement_by_account.items():
    total_minutes = 0
    for engagement_record in engagement_for_student:
        total_minutes += engagement_record['total_minutes_visited']
    total_minutes_by_account[account_key] = total_minutes


# In[23]:


import numpy as np

# Summarize the data about minutes spent in the classroom
total_minutes = total_minutes_by_account.values()
print 'Mean:', np.mean(total_minutes)
print 'Standard deviation:', np.std(total_minutes)
print 'Minimum:', np.min(total_minutes)
print 'Maximum:', np.max(total_minutes)


# ## Debugging Data Analysis Code

# In[26]:


#####################################
#                 8                 #
#####################################

## Go through a similar process as before to see if there is a problem.
## Locate at least one surprising piece of data, output it, and take a look at it.


weirdo = None;
for key , item in total_minutes_by_account.items():
    if item > 10500:
        weirdo = key;
    
for enrollment in non_udacity_enrollments:
    if enrollment["account_key"] == weirdo:
        print enrollment


# ## Lessons Completed in First Week

# In[68]:


#####################################
#                 9                 #
#####################################
get_ipython().magic(u'pylab inline')
## Adapt the code above to find the mean, standard deviation, minimum, and maximum for
## the number of lessons completed by each student during the first week. Try creating
## one or more functions to re-use the code above.
import matplotlib.pyplot as plt;
def stats(l):
    return np.mean(l),np.std(l),np.min(l),np.max(l);
def describedata(dictionary,key):
    number_lessons_by_account = {}
    for account_key, engagement_for_student in dictionary.items():
        total_lessons = 0
        for engagement_record in engagement_for_student:
            total_lessons += engagement_record[key]
        number_lessons_by_account[account_key] = total_lessons
    print stats(number_lessons_by_account.values())
    plt.hist(number_lessons_by_account.values());
    return number_lessons_by_account.values();
    
    
describedata(engagement_by_account,"lessons_completed")


# ## Number of Visits in First Week

# In[29]:


######################################
#                 10                 #
######################################

## Find the mean, standard deviation, minimum, and maximum for the number of
## days each student visits the classroom during the first week.
for account_key , engagement_records in engagement_by_account.items():
    for engagement_record in engagement_records:
        if engagement_record["num_courses_visited"] > 0:
            engagement_record["has_visited"] = 1;
        else:
            engagement_record["has_visited"] = 0;

describedata(engagement_by_account,"has_visited");
        
        
    


# ## Splitting out Passing Students

# In[30]:


######################################
#                 11                 #
######################################

## Create two lists of engagement data for paid students in the first week.
## The first list should contain data for students who eventually pass the
## subway project, and the second list should contain data for students
## who do not.

subway_project_lesson_keys = ['746169184', '3176718735']
passing_engagement = [];
non_passing_engagement = [];

pass_set = set();
nopass_set = set();

for submission_data in non_udacity_submissions:
    if submission_data["account_key"] in engagement_by_account:
        if submission_data["lesson_key"] in subway_project_lesson_keys: 
            account_key =submission_data["account_key"];
            if submission_data["assigned_rating"] == "PASSED" or submission_data["assigned_rating"] == "DISTINCTION":
                pass_set.add(account_key);
                
            else:
                
                nopass_set.add(account_key);
            
for engagement_record in paid_engagement_in_first_week:
    if engagement_record["account_key"] in pass_set:
        passing_engagement.append(engagement_record);
    else: 
        non_passing_engagement.append(engagement_record);

print len(passing_engagement);
print len(non_passing_engagement);


# ## Comparing the Two Student Groups

# In[69]:


######################################
#                 12                 #
######################################

## Compute some metrics you're interested in and see how they differ for
## students who pass the subway project vs. students who don't. A good
## starting point would be the metrics we looked at earlier (minutes spent
## in the classroom, lessons completed, and days visited).

passing_engagement_by_account = group_data(passing_engagement,"account_key");
nonpassing_engagement_by_account = group_data(non_passing_engagement,"account_key");

r1 = describedata(passing_engagement_by_account,"has_visited")
r2 = describedata(nonpassing_engagement_by_account,"has_visited")



# ## Making Histograms

# In[76]:


######################################
#                 13                 #
######################################

## Make histograms of the three metrics we looked at earlier for both
## students who passed the subway project and students who didn't. You
## might also want to make histograms of any other metrics you examined.

import matplotlib.pyplot as plt
import seaborn as sns
plt.hist(r1,bins=7)
plt.hist(r2,bins=7)


# ## Improving Plots and Sharing Findings

# In[77]:


######################################
#                 14                 #
######################################

## Make a more polished version of at least one of your visualizations
## from earlier. Try importing the seaborn library to make the visualization
## look better, adding axis labels and a title, and changing one or more
## arguments to the hist() function.

plt.hist(r1,bins=8)
plt.xlabel("Number of days")
plt.title("Distribution of classroom visits in the first week "+"for students who do not pass the project")


