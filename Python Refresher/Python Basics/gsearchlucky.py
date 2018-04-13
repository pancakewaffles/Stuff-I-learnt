#! gsearchlucky.py
'''
This is what your program does:
• Gets search keywords from the command line arguments.
• Retrieves the search results page.
• Opens a browser tab for each result.

This means your code will need to do the following:
• Read the command line arguments from sys.argv.
• Fetch the search result page with the requests module.
• Find the links to each search result.
• Call the webbrowser.open() function to open the web browser.
'''

# A Google result page has a URL like https://www.google.com/search?q=SEARCH_TERM_HERE

# The four key ingredients for dealing with webpages.
import requests,sys,webbrowser,bs4;
print("Googling...");
res = requests.get("http://google.com/search?q="+ " ".join(sys.argv[1:]));
res.raise_for_status();

# Todo: Retrieve top search result links.
soup = bs4.BeautifulSoup(res.text);

# Todo: Open a browser tab for each result.
linkElems = soup.select(".r a");
numOpen = min(5,len(linkElems));
for i in range(numOpen):
    webbrowser.open("http://google.com" + linkElems[i].get("href"));
