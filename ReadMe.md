Grocery on Wheels


Project Description
-----------------------------
The project is to develop a terminal application using Java for ‘Grocery On Wheels’. The purpose of this
application is to make grocery available at the doorsteps of customers. A grocery van makes daily visits
to a fixed location (like the corner of E.Main Street and Lincoln Drive) in different neighborhoods. Once
it reaches the nearest location of buyers, they can go to the van and buy their groceries and vegetables.
Instead, customers can also send the required items list to the seller so that the seller/driver of the van can
keep the foodstuff ready to pick before hitting one’s nearest location.
This application enables the customers to search for any product and add to the cart and pay them using the
saved card details. It is developed solely to focus on food products available to common people so that
people should not bother about going to the store and buying especially in unsuitable environment.
We need database, because the project contains abundant of information regarding Users, Payment, Stock
of Products, Orders , Van and its schedule which has to be stored in DB to keep the track of user activities,
products in inventory and the visits of vans. So, DB is an important part of the system and one cannot go
ahead with the project without saving the details of their software components in DB.

Initial Steps
------------

To setup Database
-------------------
1. Install appropriate MySQLDBMS for your opertaing system using 'https://dev.mysql.com/downloads/' and select 'MySQL community Server' edition.
2. Select the operating system and click the image and select the larger size file. 

Open the downloaded file and start installation.

3. Read and accept the license agreement.

4. From the "Choosing a Setup Type" select the "Developer Default".

5. On the "Check requirements" select "execute" and then click "next". 

6. On the "Installation" tab click "Execute" for installation.
7. On the "Product Configuration" tab select next to configure the product.
 
8. On the High Availability menu, select "Standalone MySQL Server/Classic MySQL Replication" and On the Type and Networking, 
9. Keep the default value for config type to "Development Computer". Check TCP/IP and select the Port address to be "3306" then click Next.
10.On the Authentication Method, keep the default of "Use Strong Password ..." as the selected option and set your MySQL Root password (use a simple pass code)
11. On the Windows Service menu, keep the default selections.
11.The installation wizard will ask to connect to server -so insert "root" as username and then insert your password.
12. On Apply Configuration menu, click on "Execute" button.
13.Click finish. You will see mysql-js (which is mysql shell) and MySQL workbench


To setup Java Application
----------------------------
Install Java version 8 or above and install an IntelliJ IDE.

To set up the Java connection with Database
-----------------------------------------------

1. Download Connector/J JDBC driver so our program can connect to MySQL. Go to this web page (http://www.mysql.com/downloads/connector/j/)

In the operating System menu , Select 'Platform Independent'. Select the largest size file.

2. Open MySql WorkBench, Click on the "+" symbol. From the set up wizard,  create a new connection with a name of your choice. (eg: mydbconnection)

Check "test connection".And after successful connection , click 'OK'.

3. Open intellij IDE, Clone the current project.

File -> Settings -> Plugins -> Search for "Database Navigator" . Install "Database Navigator".

Restart IDE to apply the plugins.

View -> Tool Windows -> DB Browser.

Select the "+" symbol and select "mySQL" 

4. Fill the form with connection name selected earlier in mysql connection.
Enter username and password.
Test the connection to confirm the establishment of the connection. Click 'OK' to close the DB setting.

5. To add the mysqlconnector jar file.

File -> Project Structure -> Modules -> Dependencies > "+" Jars or directories 

Select the "mysql-connector-java-8.0.20" jar file downloaded from the website of mysql jdbc connector file.

Click 'OK' to close the DB setting.



Setting up the project
----------------------
1. Open 'MySqL  shell'.

Connect to the installed MySQL using your username (the one you placed while installing MySQL), using the following command where “root” is your username: 
 MySQL  JS > \connect root@localhost

Enter password

2. To switch to sql mode

 MySQL  localhost:33060+ ssl  JS > \sql;

3. Clone the files from Github repository. 

File$Folder$Path ="Path of the folder where the cloning has been done in your system"
Make sure that the folder path have no spaces in the folder names.

Execute the following command

 MySQL  JS > source File$Folder$Path\TableCreation.sql

4. All the tables and database will be created. You can verify in Intellij in the DB Browser tab.

![Test Image 1](screenshot_for_creating_tables.jpg)

5. Open Intellij and open the respective project. 

Open the "Application.java" file and run the file.

6. Execute the "Application.java" file.

7. Enter database name as "grocery_on_wheels" . Enter the username and password. 

![Test Image 2](Screenshot_for_Application.jpg)

8. Data will be entered in the Database with available csv files and you can query the database.


![Test Image 3](Screenshot_for_listing_queries_in_application.jpg)



