
Ontology based open domain question answering system
================================================================

This is a ontology based question answering system. 
The program takes a config file containing sparql queries developed on a domain.
The program output is autocomple questions answering system.
The program is written in java and python.


Input
------------

The Input file is a .rdf file that contains sparql queries. An example of input file is as follow:


How To Run 
------------

```

git clone <repo> 


mvn clean package


Run the application

java -jar <generated jar> <input file>

Output
------------

```
Output is automatically generated an auto complition question answering system. 

Then go to localhost
http://localhost:8080/


## Note
No additional library (I.e. Apache Tika) is used to detect file type or mime type. 


## Authors
* **Lukas Drothler**
* **Mohammad Fazleh Elahi**
* **Philipp Cimiano**

