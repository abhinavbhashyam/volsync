# Volsync
This REST API, built with SpringBoot and the Java programming language, manages the main functionalities of Volsync.

## What is Volsync?
Volsync facilitates the interaction between organizations that are looking for volunteers for certain events/tasks and volunteers who are willing to provide that help (think Indeed, but for volunteering opportunities instead). 

## Main Functionalities of Volsync
1. The application is divided into "organization" and "volunteer" accounts
2. Organizations can post different "opportunities" that correspond to the events/tasks they require volunteers for
3. Volunteers can then sign up for the tasks that suit their specific interests and talents
4. Organizations will then have the ability to accept/reject volunteers to their events in an effort to recruit the necessary talent for their events

For information regarding how these specific tasks (in addition to other functionalities like authentication/authorization using Spring Security) have been implemented in the API, please take a look at the source code. Source code has been extensively documented with Javadoc documentation.

## Testing the API
Please take the following steps to test this API locally:

1. Download a zip version of this repository.
2. Follow [this tutorial](https://www.youtube.com/watch?v=u96rVINbAUI) to install MySQL (Server and Workbench) (note: if you're on Windows, make sure you download the MySQL Installer! This will ensure MySQL Server and MySQL Workbench are downloaded and configured appropriately).
3. When prompted to enter MySQL Root Password (while following the above tutorial), enter "Ramanuja1234".
4. Continue following the tutorial to open up a new database connection.
5. Once a database connection has been opened up, create a new schema titled "volsync".
6. Finally, run the "VolsyncProjectApplication.java" file, and use Postman to test any of the API endpoints defined in the code! (note: API has been built and tested using JDK 17)
