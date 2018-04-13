#! readCensusExcel.py - Tabulates population and number of census tracts for each county.

import openpyxl,pprint;
print("Opening workbook...");
wb = openpyxl.load_workbook("censuspopdata.xlsx");
sheet = wb.get_sheet_by_name("Population by Census Tract");
countyData = {};

# Fill in CountyData with each county's population and tracts.
print("Reading rows...");
for row in range(2,sheet.max_row+1):
    # Each row in the spreadsheet has data for one census tract.
    state = sheet["B"+str(row)].value;
    county = sheet["C"+str(row)].value;
    pop = sheet["D"+str(row)].value;


    # Data Structure of countyData;
    # Dictionary with state abbreviations as its keys.
    # Each state abbreviation will map to another dictionary,
        # whose keys are strings of the county names in that state
        # Each county name will in turn map to a dictionary with just two keys, "tracts" and "pop"

    '''
    {'AK': {'Aleutians East': {'pop': 3141, 'tracts': 1},
            'Aleutians West': {'pop': 5561, 'tracts': 2},
            'Anchorage': {'pop': 291826, 'tracts': 55},
            'Bethel': {'pop': 17013, 'tracts': 3},
            'Bristol Bay': {'pop': 997, 'tracts': 1},
            }
    countyData["AK"]["Anchorage"]["pop"];
    '''
    # Set defaults
    countyData.setdefault(state,{});
    countyData[state].setdefault(county,{"tracts":0,"pop":0});

    # Input data
    countyData[state][county]["tracts"] += 1;
    countyData[state][county]["pop"]+=int(pop);

# Open a new text file and write the contents of countyData to it.
print("Writing results...");
resultFile = open("census2010.py","w"); # We use a python file because we want to interact with the data
resultFile.write("allData = " + pprint.pformat(countyData)); # pprint.pformat produces a string that itself is formatted as valid Python code.
resultFile.close();
print("Done.");



''' *****Usage*****
>>> import os
>>> os.chdir('C:\\Python34')
>>> import census2010
>>> census2010.allData['AK']['Anchorage']
{'pop': 291826, 'tracts': 55}
>>> anchoragePop = census2010.allData['AK']['Anchorage']['pop']
>>> print('The 2010 population of Anchorage was ' + str(anchoragePop))
The 2010 population of Anchorage was 291826
'''



    
