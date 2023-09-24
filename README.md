# Volsync
This application, built with SpringBoot and the Java programming language, manages the main functionalities of Volsync.

## What is Volsync?
Volsync facilitates the interaction between organizations that are looking for volunteers for certain events/tasks and volunteers who are willing to provide that help (think Indeed, but for volunteering opportunities instead). 

## Main Functionalities of Volsync
1. The application is divided into "organization" and "volunteer" accounts
2. Organizations can post different "opportunities" that correspond to the events/tasks they require volunteers for
3. Volunteers can then sign up for the tasks that suit their specific interests and talents
4. Organizations will then have the ability to accept/reject volunteers to their events in an effort to recruit the necessary talent for their events

For information regarding how these specific tasks (in addition to other functionalities like authentication/authorization using Spring Security) have been implemented, please take a look at the source code. Source code has been extensively documented with Javadoc documentation.

## Trying the App
Please take the following steps to test this app locally:

1. Download a zip version of this repository
2. Download MySQL Workbench
3. Open up MySQL Workbench and create a new database connection with default username of "root" and password "Ramanuja1234"
4. Open up this database connection and create a new schema in this connected server. Name this new schema "volsync"
5. Finally, run the "VolsyncProjectApplication.java" file, and use Postman to test any of the API endpoints defined in the code!
