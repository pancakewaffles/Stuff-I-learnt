#! autoplay2048.py
# Navigates to https://gabrielecirulli.github.io/2048/ and plays 2048.
# Automatically.

import os,requests,bs4;
from selenium import webdriver;
from selenium.webdriver.common.keys import Keys;

browser = webdriver.Firefox(firefox_binary=r"C:\Program Files\Firefox Developer Edition\firefox.exe",
                            executable_path=r"F:\Program Files\Python\selenium\geckodriver.exe");
url = "https://gabrielecirulli.github.io/2048/";

browser.get(url);
htmlElem = browser.find_element_by_tag_name("html");
htmlElem.send_keys(Keys.UP);

