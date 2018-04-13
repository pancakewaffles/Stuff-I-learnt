#! avgMinMaxTemp.py
# Graphs the average min temperatures and average max temperatures over a year for London.
# Gets the data from www.bbc.com/weather/2643743 automatically.


import requests,bs4,os,re;
from matplotlib import pyplot as plt;
import math;

def draw_graph(months,minT,maxT):
    plt.plot(months,minT);
    plt.plot(months,maxT);
    plt.xlabel("Months");
    plt.ylabel("Temperature");
    plt.title("Temperature Range");

    plt.legend(["Avg. Min Temps","Avg. Max Temps"]);
    plt.show();
    #probably need to change the axis somehow


def download_weatherData(url):
    #Todo: Navigate to the bbc weather website.
    res = requests.get(url);
    res.raise_for_status();

    #Todo: Find the weather data for both min Temps and max Temps.
    soup = bs4.BeautifulSoup(res.text);

    # Getting the Min Temps out.
    avgMinTData_raw = soup.select(".temperatureAverageMinC td");

    avgMinTData_processed = [];
    
    for t in avgMinTData_raw:
        regex = re.compile(r"<td>((1|2|3|4)?\d)</td>");
        result = regex.search(str(t));
        temperature = result.group(1);
        avgMinTData_processed.append(temperature);
        
    
    # Getting the Max Temps out.
    avgMaxTData_raw = soup.select(".temperatureAverageMaxC td");

    avgMaxTData_processed = [];
    
    for t in avgMaxTData_raw:
        regex = re.compile(r"<td>((1|2|3|4)?\d)</td>");
        result = regex.search(str(t));
        temperature = result.group(1);
        avgMaxTData_processed.append(temperature);
        

    months = range(1,13);

    return months, avgMinTData_processed,avgMaxTData_processed;

if(__name__=="__main__"):
    months, temperatureAvgMin, temperatureAvgMax = download_weatherData("http://www.bbc.com/weather/2643743");
    draw_graph(months, temperatureAvgMin, temperatureAvgMax);
    


 
