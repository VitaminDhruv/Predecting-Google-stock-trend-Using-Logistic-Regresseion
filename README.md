# Predecting-Google-stock-trend-Using-Logistic-Regresseion


-> Predicting the The Likelyhood of Google stocks prices will be 'Increase' or 'decrease' using Logistic regression.

->Datasets: Trainning (Google.csv) contains 1696 row where column 1 is the opeaning price of the stock and columb 4 is the closing price of the stock on same day
column 2: Highest stock price per day. column 3: lowest stock price per day. column 5:Adj.close rate. column 6: volume. datasetfrom: https://www.kaggle.com/

->Instruction to run:
We are using CSV files as data set. To read that files we added CSV reader MAVEN dependency to java Project. 

Following are the parameters for the CSV dependency:
Parameters:

<dependency>
    <groupId>com.opencsv</groupId>
    <artifactId>opencsv</artifactId>
    <version>4.1</version>
</dependency>

To add MAVEN dependency, we need to take following steps:

1. On the top menu bar, open Window -> Show View -> Other
2. In the Show View window, open Maven -> Maven Repositories 
3.In the window that appears, right-click on Global Repositories and select Go Into
4. Right-click on "central (http://repo.maven.apache.org/maven2)" and select "Rebuild Index"
	Note that it will take a while to complete the download
5. Once indexing is complete, Right-click on the project -> Maven -> Add Dependency and start typing 
the name of the project you want to import (such as "hibernate").

