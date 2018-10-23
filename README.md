# demo-for-play-and-neo4j
A simple demo for using neo4j in play with Neo4j OGM

Assume that you have already installed SBT in your machine. (and a workable Neo4j server, of course).

1. Start you Neo4j server
2. Configure the Neo4j endpoint and password in app/controllers/HomeController.java, line 23-25
3. sbt run

There are two APIs:
1. localhost:9000/initialize  
by doing this,  **all the original data in Neo4j will be removed**, and example data: two actors and one movie, will be inserted.

2. localhost:9000/getActorsIn/<MovieName>  
you will get a table of all the actors in that movie in your database.

Hope this can help!

Best,
Kai
