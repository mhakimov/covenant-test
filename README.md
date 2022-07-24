Selenium / JUnit5 / Selenoid  

### Scope:
    1. Test execution on remote machine(Docker+Selenoid). 
    2. Screenshot creation for failed tests
    3. Video recording
    4. Parallel test execution
    5. Logging
    
### Configuration:

Configuration defined in ApplicationProperties.java

Examples:
You need to specify the following parameters:

-Ddownload.folder = {host machine download folder}
-Dhm.password = {host machine password}
-Dcovenant.data = {absolute path to covenant data}
-Dapplication.appUrl = {covenant app URL}
-Duser.nme = {covenant username}
-Duser.password = {covenant user's password}
-Dvm.hostname = {Windows machine hostname}
-Dvm.password = {Windows machine password}
-Dvm.ip = {Windows machine IP}
-Dvm.downloads = {Windows machine Downloads folder}
-Dcovenant.data = {Path to Covenant Data}



You can specify browser by using one of the following switches:

-Dbrowser=FIREFOX
-Dbrowser=CHROME

Also you can specify where to run tests, on local machine / Docker:

-Dselenium.remoteDriver=true
 

### How to run test?
Execute the following command in the terminal to start Selenoid container:

        ./cm selenoid start --vnc 

and then launch the test:


        mvn clean install test -Ddownload.folder={} -Dhm.password -Dcovenant.data={} -Dapplication.appUrl={} -Duser.nme={} -Duser.password={} -Dvm.hostname={} -Dvm.password={} -Dvm.ip={} -Dvm.downloads={} -Dcovenant.data={}

       

### Technical choice
Java: a popular programming language. It is supported by Selenium, and runs faster than most of the 
popular programming languages.

Selenoid: a fast solution that is easy to install, and has an interactive UI.

Page Object Model as a framework structure: this design pattern makes the code clean and re-usable.
