# ServiceReports
A RESTful Web Service written with Java that operates with JSON and communicates with a database.

# Installation

Here are the instructions on how to compile the web service.

First, clone the repository to your local folder.

# Database structure

The web service expects you to have a certain kind of MySQL database in order to submit and receive data.

![Database structure](https://zibang.s-ul.eu/6wQdSAya.png)

All the columns should be varchars.

After creating the database, add your correct database credentials to the src/server/ReportService.java file.

![Database information](https://zibang.s-ul.eu/1zYybPtA.png)

# Compiling class files with javac

The program does not include the class files, so you will have to build them yourself or extract the source files and the folder
structure to an IDE that will build the application for you.

To build manually with javac, open the src/server folder in command line.

Then copy the location of the WebContent/WEB-INF/lib folder to use as the classpath.

Run `javac -classpath <location/of/lib>/* *.java` to create the class files.

# Building .war with jar

After creating the .class files, cut them and go to the WEB-INF folder. Create a new folder called *classes* and paste the
class files there.

![](https://zibang.s-ul.eu/xHekWsui.png)

After this, go to the WebContent folder and use the following command to create the .war file.

`jar -cvf "yourfilename".war *`

This should create the .war file that can be deployed to a Tomcat 9 server or such.


# Usage of the service

The service has two methods - PUT and GET. They are both accessed through the address <yoururl>/api/servicereports.

PUT is ran with JSON. The structure of the JSON should be as in the following image, and the service will upload this information to the database.

![](https://zibang.s-ul.eu/1uAo3zDf)

GET is ran without parameters and will return you a JSON with all the available report information in the database.

![](https://zibang.s-ul.eu/jo5WBFzD)
