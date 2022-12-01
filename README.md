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

## To test the features without a Douglas College student email account
<details><summary>Please use these testing accounts.</summary>

- username: sulej@student.douglascollege.ca
- password: $Canada12345 
	
- username: pastorilt@student.douglascollege.ca
- password: Password12!
	
</details>

## User stories upon signing in

1. User to search a group/event on the search bar. Results return groups and events based on names.
2. In every page, clicking the logo on the upper-left redirects to the browsing page if logged in, log in page if not logged in.
3. Upcoming events section has 6 future events displayed.
4. Trending groups section has 10 groups max displayed.
5. Upper-right section has a dropdown menu.
	- Profile
	- Group
	- Event
	- Logout
6. Clicking Profile displays the first & last names, and email address of the user.
7. Group lets the user manage the groups he owned/joined in.
7a. Group owner is able to edit and delete, but not able to leave the group.
7b. Deleting the group also deletes the associated events.
7c. Clicking the Edit lets the user edit Group's name and description.
7d. The group is accessible by clicking the group name.
8. The group page has 4 main features
	- About
	- Members
	- Upcoming Events
	- Past Events
- 8a. The group has its name, number of members, host name displayed.
- 8b. About is the group description.
- 8c. Members section displays all the group members' names.
- 8d. Upcoming events section has all the future events organized by the group. The events are accessible by clicking the name.
- 8e. Past events section displays all the past events organized by the group. The events are accessible by clicking the name.
- 8f. If the user is not a group member, he is able to join by clicking the 'Join' button.
9. Clicking Event lets the user manage the events he created/joined in.
- 9a. Event organizer is only allowed the edit and delete, but the leave.
- 9b. Clicking Edit lets the user edit the event's details except the Group Organizer.
- 9c. The event is accessible by clicking the event name.
10. The vent page has 2 features
	- Details
	- Participants
- 10a. If the user the not an event participant and the event's group member, he must join the group first.
- 10b. The event has its name, number of participants, organizer's name, group organizer's name, and event schedule (date, time) displayed.
- 10c. Details section has the event's description.
- 10d. Participants section has all the event participants' names displayed.
11. Clicking 'Log Out' logs the user out of the system. The home page is displayed when logging back in.
