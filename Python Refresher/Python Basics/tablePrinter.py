# Takes a list of lists of strings and displays it in a well-organised table,
# with each column right-justified.
tableData = [['apples', 'oranges', 'cherries', 'banana'],
['Alice', 'Bob', 'Carol', 'David'],
['dogs', 'cats', 'moose', 'goose']];

def printTable(table):
    colWidths = [0] * len(tableData);
    i = 0;
    for ls in table:
        maxWidth = len(ls[0]);
        for j in ls:
            if(len(j)>maxWidth):
                maxWidth = len(j);
        colWidths[i] = maxWidth;
        i += 1;

    adjustWidth = max(colWidths);

    for i in range(len(table[0])):
        for j in range(len(table)):
            print(table[j][i].rjust(adjustWidth),end="");
        print();
    
            

printTable(tableData);
