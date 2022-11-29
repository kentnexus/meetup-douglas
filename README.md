# meetup-douglas
CSIS3275 Final Projects


## Software Requirements
<p>Please run the Maven in the project. And the dependencies will be installed.</p>

- TO Connect to MySql Database
- Please set up your username and password to your mysql server and create a meetup_db for your database
- Or set your own database name and change the db name after localhost/{database name}

```
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/meetup_db?createDatabaseIfNotExist=true
spring.datasource.username=
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

## To make your confirmation email link to work properly

<p>Please go to java -> com.example.csis3275project -> web -> RegistrationService.java -> Rigister </p>

```
 public String[] register(RegistrationRequest request) {
       ....

        String link = "http://localhost:8080/registration/confirm?token="+token;  // <-- this is for local 
        String link = "https://meetup-douglas.herokuapp.com/registration/confirm?token="+token; // <-- this is for heroku 
       ...
        return info;
    }
```
