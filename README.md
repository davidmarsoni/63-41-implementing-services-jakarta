# 63-41-implementing-services-jakarta

HES-SO School Project

## Start the project

1. Unzip the project to your local machine

First, you need to unzip the project to your local machine.

2. Open the project in your IDE

Open the project in your IDE.

For example, in ecpilse, you can do this creating a new Java project and chose the project in the file system.

3. Populate the database

To populate the database, you need to run the junit test located at this path `src/test/java/ch/hevs/test/PopulateDB.java`.

Note you need to have your database empty and running before running the test.

4. Add accounts to the wildfly server

To add accounts to the wildfly server, we will need to add users to the “ApplicationRealm” (i.e., application users) of our application server. With WildFly, this can be done by using the script
“rootJakartaEE\tools\ wildfly-27.0.0.Final\bin\add-user.bat” or
“rootJakartaEE\tools\ wildfly-27.0.0.Final\bin\add-user.sh”.
Yyou first have to choose the option 'b' to add the user to the ApplicationRealm.
After that, you have to choose the username and password of the user.
Then you have to choose the roles of the user, you can choose multiple roles by separating them with a comma.
And at the end, you have to confirm the user creation.
You have to say no to the next question, because we don't add the user to the management realm.
And you need to repeat this process for each user.

the accounts are the following:

| Username      | Password | Roles |
| ---           | ---      | ---   |
| Admintest     | admin    | admin |
| JeanDupont    | password | owner |
| MarieDurand   | password | owner |
| PaulDurand    | password | buyer |
| JacquesDupont | password | buyer |

n.b : the password are indicative, you can change them if you want.
n.b2: the username and roles are case sensitive be careful.

To verify that the users are correctly added, you can go to the following path `rootJakartaEE\tools\ wildfly-27.0.0.Final\standalone\configuration\application-users.properties` and you should see the users added.
To verify the roles, you can go to the following path `rootJakartaEE\tools\ wildfly-27.0.0.Final\standalone\configuration\application-roles.properties` and you should see the roles added.

5. Run the project

To run the project, you need to start the wildfly server and deploy the project with the following command:

```shell
mvn clean package wildfly:deploy
```

or with eclipse, you can right-click on the project and select `Run As` -> `Maven build...` and in the `Goals` field, you can write `clean package wildfly:deploy`.

6. Access the project

You can access the project at the following URL: [http://localhost:8080/CarSale-0.0.1-SNAPSHOT/faces/welcome.xhtml](http://localhost:8080/CarSale-0.0.1-SNAPSHOT/faces/welcome.xhtml)

7. Navigate the project

You can navigate the project by clicking on the links in the Navbar. note that you need to be logged with a specific account or you will not be able to access some pages.

resume of the roles:

| Roles      | Pages accessible           |
| ---        | ---                        |
| admin      | all pages                  |
| owner      | carsManage, ownerDashboard |
| buyer      | carsBuy, buyerDashboard    |