#! phoneAndEmail.py - Finds phone numbers and email addresses on the clipboard.
import pyperclip,re;

phoneRegex = re.compile(r'''(
     (\d{3}|\(\d{3}\))?              # area code   (optional hence the ?    either 3 digits or 3 digits with brackets
     (\s|-|\.)?                       # separator
     (\d{3})                          # first 3 digits
     (\s|-|\.)                        # separator
     (\d{4})                          # last 4 digits
     (\s*(ext|x|ext.)\s*(\d{2,5}))?   # extension (An optional extension hence the ?
                                      #            made up of any number of spaces \s*
                                      #            followed by ext, x, or ext.  (ext|x|ext.) 
                                      #            followed by any number of spaces \s*
                                      #            then followed by two to five digits \d{2,5}
     )''',re.VERBOSE)

# TODO: Create email regex.
emailRegex = re.compile(r'''(
     [a-zA-Z0-9._%+-]+     # username   one or more characters that can be any of the following:
                           # lowercase and uppercase letters, numbers, a dot, an underscore, a percent sign, a plus sign, or a hyphen.     
     @                     # @ symbol
     [a-zA-Z0-9.-]+        # domain name
     (\.[a-zA-Z]{2,4})     # dot-something
     )''',re.VERBOSE);

# TODO: Find matches in clipboard text.
text = str(pyperclip.paste());
matches = [];
for groups in phoneRegex.findall(text):
     phoneNum = '-'.join([groups[1],groups[3],groups[5]]);
     if groups[8] != '':
          phoneNum += " x"+groups[8]; # Why is groups[8] the extension? Because (\s*(ext|x|ext.)\s*(\d{2,5}))?
                                      #                                         ^6   ^7             ^8 THIS ONE!
     matches.append(phoneNum);
     
for groups in emailRegex.findall(text):
     matches.append(groups[0]);       # groups[0] matches the entire regular expression.
     
# TODO: Copy results to the clipboard.
if(len(matches)>0):
     pyperclip.copy('\n'.join(matches));
     print("Copied to clipboard:");
     print('\n'.join(matches));
else:
     print("No phone numbers or email addresses found.");

