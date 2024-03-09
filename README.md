# Spring project by using GitHub Open API's.  

.jar and .bat files are in the hitit-spring-demo/hitit/target/ folder.  

1- First, repositories should be created by going to "localhost:port + /api/create-repos".
<br>
2- Secondly, to get the information of the contributing users, you need to go to "localhost:port + /api/create-contributions".  
<br>
Sample queries:
<br>
SELECT * FROM repository_table;
<br>
SELECT * FROM user_detail;  
<br>
Spring (java) was used for project coding and H2 database was used for the database. Database information is located in the application.properties file. Table records can be examined from the "localhost:port + /h2-console" page.  

1- Database Structure
![database structure](https://github.com/kemoek/hitit-spring-demo/assets/59113696/886b202e-f087-4f09-922c-8467fb6c986f)
<br>  
CREATE TABLE repository_table (
                            id INT PRIMARY KEY,
                            repository_name VARCHAR(255) NOT NULL
);

CREATE TABLE user_detail (
                      id INT PRIMARY KEY,
                      repository_id INT,
                      username VARCHAR(255) NOT NULL,
                      location VARCHAR(255),
                      company VARCHAR(255),
                      contributions INT,
                      FOREIGN KEY (repository_id) REFERENCES repository_table(id)
);  
<br>
2- Sample Records
![tables1](https://github.com/kemoek/hitit-spring-demo/assets/59113696/14a946e2-dbba-498b-a55f-d5ff85f31d9b)
![tables2](https://github.com/kemoek/hitit-spring-demo/assets/59113696/e49c1a5e-4cb1-43a9-9fbb-e0faca24ad58)
