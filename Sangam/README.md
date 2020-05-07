#### System Requirement:

* JDK 1.8 or above
* Maven 3.2 or above
* Eclipse 

#### Create Maven project and import below dependencies
* Selenium 
* TestNG 

###Git use below commands
Git clone: to copy url from git hub
git add . : use for adding file in project
git commit -m : use for commiting the code with message
git push: use for pushing the code on github


#### Execution Steps for Get Shopify details
Please follow the instructions to execute the tests on local:

Refer config file if you want to run script on difference browser

1.Open package src/test/java
2. Now, select and open package name com.sangam.tests
3.Now, you can select any test case to run  as testng
4.Now, After completion of execution 
5.Observe, the results in console and myReport.html
6.For execution test result , please follow below path

#### Result Files:	
The Test Execution Results will be stored in the following directory once the test has completed

    ./target/test-output/emailable-report.html 
    ./target/surefire-reports/emailable-report.html 
    ./target/test-output/myReport.html 
    
 #### Maven Jenkins and Git:   
 run over jenkins
 run command java -jar jenkins.war  
 Create maven project
 configure github with your credentials
 now give goals
 and then click now Build Now
 
 
    
