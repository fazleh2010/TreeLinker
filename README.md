
downloadApp
================================

This is a download application. 
The program takes a text file containing urls and downloads all the images and then stores them in local machine.
The program is written in java.


Input
------------

The Input file is a .txt file that contains urls. An example of input file is as follow:

data/links.txt

https://www.technicalkeeda.com/img/images/article/spring-file-upload-eclipse-setup.png
https://farm3.static.flickr.com/2531/4094333885_e8462a8338.jpg
http://eeweb.poly.edu/~yao/EL5123/image/barbara_gray.bmp
http://mywebserver.com/images/24174.jpg
http://mywebserver.com/images/24174.jpg


Output
------------
The program downloads all the images and stores them in the same location of input file. An example of output is as follow:

data/

```
spring-file-upload-eclipse-setup.png
4094333885_e8462a8338.jpg
barbara_gray.bmp
links_Report.xml
```

The links_Report.xml reports the result of download status of each url. I.e. whether images are downloaded or not (SUCCESS or FAIL). It also includes a note that mentions the reason of the failure. For example:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<report>
    <input>../downloadApp/data/links.txt</input>
    <downloadReport>../downloadApp/data/links_Report.xml</downloadReport>
    <downloadLocation>../downloadApp/data</downloadLocation>

    <element url="https://farm3.static.flickr.com/2531/4094333885_e8462a8338.jpg">
        <note>The image is successfully stored!! </note>
        <status>SUCCESS</status>
    </element>
    <element url="http://mywebserver.com/images/24174.jpg">
        <note>No image found to download!!</note>
        <status>FAIL</status>
    </element>
</report>
```

The download status of each url of the `links.txt` file is transformed into a separate element. 

## pom.xml
The POM contains information about the project used by Maven to build the project. A simple artifact is used with default project structure.

## dependencies
The program is written using core java. No external library is added to pom.xml. Only junit library is added in dependencies.



How To Run 
------------

git clone <repo> 


Go to project folder downloadApp/ and build the program 
```
mvn clean package
```

Run the application
```
java -jar <generated jar> <input file>

Example:
java -jar target/downloadApp-1.5.jar data/links.txt
```

## Note
No additional library (I.e. Apache Tika) is used to detect file type or mime type. The issue was resolved by codes. No library of Utils (i.e Apache Common) is used. The program was written in core java.


## Author

* **Mohammad Fazleh Elahi**

