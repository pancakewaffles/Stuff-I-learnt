#! downloadXkcd.py
'''
Here’s what your program does:
• Loads the XKCD home page.
• Saves the comic image on that page.
• Follows the Previous Comic link.
• Repeats until it reaches the first comic.

This means your code will need to do the following:
• Download pages with the requests module.
• Find the URL of the comic image for a page using Beautiful Soup.
• Download and save the comic image to the hard drive with iter_content().
• Find the URL of the Previous Comic link, and repeat.
'''

'''
If you open the browser’s developer tools and inspect the elements on the
page, you’ll find the following:
• The URL of the comic’s image file is given by the href attribute of an
<img> element.
• The <img> element is inside a <div id="comic"> element.
• The Prev button has a rel HTML attribute with the value prev.
• The first comic’s Prev button links to the http://xkcd.com/# URL, indicating
that there are no more previous pages.
'''
import requests,os,bs4;
url = "http://xkcd.com"
os.makedirs("xkcd",exist_ok=True) # store comics in ./xkcd


while not url.endswith("#"): # This downloads all comics.
                             #You can change this to specify how many you want.
    
    #Todo: Download the page.
    print("Downloading page %s..."%(url));
    res = requests.get(url);
    res.raise_for_status();

    soup = bs4.BeautifulSoup(res.text);
    
    #Iodo: Find the URL of the comic image.
    comicElem = soup.select("#comic img");
    
    if(comicElem == []):
        print("Could not find comic image.");
    else:
        
        comicUrl = "http:"+comicElem[0].get("src");
        #Todo: Download the image.
        print("Downloading image %s..."%(comicUrl));
        res = requests.get(comicUrl);
        res.raise_for_status();
        
        #Todo: Save the image to ./xkcd.
        imageFile = open(os.path.join("xkcd",os.path.basename(comicUrl)),"wb");
        for chunk in res.iter_content(100000):
            imageFile.write(chunk);
        imageFile.close();

        
    #Todo: Get the Prev button's url.
    prevLink = soup.select("a[rel='prev']")[0];
    url = "http://xkcd.com"+prevLink.get("href");

print("Done.");
