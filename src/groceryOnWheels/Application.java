package groceryOnWheels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {

		System.out.println("Entering the data in the tables");
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter the Database Name");
		String DBName = kb.nextLine();
		System.out.println("Enter the User Name");
		String UserName = kb.nextLine();

		System.out.println("Enter the Password");
		String Password = kb.nextLine();
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DBName, UserName, Password);
					Driver driver= new Driver();
					driver.insertRecords(con);

				}
				catch (

						SQLException e) {

					e.printStackTrace();
				}




		System.out.println("Please enter which query to be executed");
		int entry;


		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DBName, UserName, Password);

			do {

				optionsDisplay();
				entry = Integer.parseInt(kb.nextLine());

				switch (entry) {
				case 1:
					case1(kb, con);
					break;

				case 2:
					Statement stmt2 = con.createStatement();
					int count2=0;
					ResultSet rs2 = stmt2.executeQuery("SELECT company_name,count(item_name) AS count FROM inventory	GROUP BY  company_name ORDER BY count(item_name)  DESC");
					while (rs2.next()) {
						System.out.println(rs2.getString("company_name") + " : " + rs2.getInt("count"));
					count2++;
					}
					System.out.println("Total rows: " + count2);
					System.out.println();
					break;

				case 3:
					Statement stmt3 = con.createStatement();
					ResultSet rs3 = stmt3.executeQuery("SELECT item_name FROM inventory WHERE item_qty=0");
					int count3 = 0;
					while (rs3.next()) {
						count3++;
						System.out.println(rs3.getString("item_name"));

					}
					System.out.println("Total rows: " + count3);
					System.out.println();
					break;

				case 4:
					case4(kb, con);
					break;

				case 5:
					case5(con);
					break;

				case 6:
					case6(con);
					break;

				case 7:
					case7(kb, con);
					break;

				case 8:
					case8(kb, con);
					break;

				case 9:
					case9(kb, con);
					break;

				case 10:

					case10(kb, con);
					break;

				case 0:
					System.out.println("Quitting the Program");
					System.out.println("Thank you for using our application");
					break;

				}

			} while (entry != 0);

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		kb.close();

	}

	private static void case10(Scanner kb, Connection con) throws SQLException {
		Statement stmt10 = con.createStatement();
		System.out.println("Enter State name (example as IL, AL etc)");
		String stateName = kb.nextLine();
		int count10 = 0;
		ResultSet rs10 = stmt10
				.executeQuery("\r\n" + "SELECT count(*) AS count FROM users WHERE State=\"" + stateName + "\"");
		while (rs10.next()) {
			count10++;
			int columnValue10 = rs10.getInt("count");
			System.out.println("count  : " + columnValue10);
		}
		System.out.println();

		System.out.println("Total rows: " + count10);
		System.out.println();
	}

	private static void case9(Scanner kb, Connection con) throws SQLException {
		Statement stmt9 = con.createStatement();
		System.out.println("Enter Item_name for first item");
		String itemName1 = kb.nextLine();
		System.out.println("Enter Item_name for second item");
		String itemName2 = kb.nextLine();
		int count9 = 0;
		ResultSet rs9 = stmt9.executeQuery("SELECT innertable.UserID AS UserID FROM \r\n"
				+ "(SELECT  uc1.UserID AS userID\r\n" + "FROM orderdetails AS o1,userCart AS uc1, inventory AS inv1\r\n"
				+ "WHERE o1.userID=uc1.UserId\r\n" + "AND uc1.Item_id=inv1.item_id\r\n" + "AND inv1.Item_name=\""
				+ itemName1 + "\"" + ") AS innertable , \r\n" + "userCart AS uc ,inventory AS inv\r\n"
				+ "WHERE innertable.userID=uc.UserId\r\n" + "AND uc.Item_id=inv.item_id\r\n" + "AND inv.Item_name=\""
				+ itemName2 + "\"");

		while (rs9.next()) {
			count9++;
			String columnValue9 = rs9.getString("UserID");
			System.out.println("User ID  : " + columnValue9);
		}
		System.out.println();

		System.out.println("Total rows: " + count9);
		System.out.println();
	}

	private static void case8(Scanner kb, Connection con) throws SQLException {
		Statement stmt8 = con.createStatement();
		System.out.println("Enter Item_name");
		String itemName = kb.nextLine();
		int count8 = 0;
		ResultSet rs8 = stmt8.executeQuery("SELECT u.UserID,u.EmailID\r\n"
				+ "FROM users as u,orderDetails as o, inventory as i, UserCart as uc\r\n"
				+ "WHERE u.UserID=o.UserID \r\n" + "AND uc.UserID=u.UserID \r\n" + "AND uc.item_id=i.item_id\r\n"
				+ "AND i.item_name=\"" + itemName + "\"");
		ResultSetMetaData rsmd8 = rs8.getMetaData();
		int columnsNumber8 = rsmd8.getColumnCount();

		while (rs8.next()) {
			count8++;
			for (int i = 1; i <= columnsNumber8; i++) {
				if (i > 1)
					System.out.print(",  ");

				String columnValue8 = rs8.getString(i);
				System.out.print(rsmd8.getColumnName(i) + " : " + columnValue8);
			}
			System.out.println();
		}
		System.out.println();

		System.out.println("Total rows: " + count8);
		System.out.println();
	}

	private static void case7(Scanner kb, Connection con) throws SQLException {
		String year;
		String month;
		String date;
		System.out.println("Enter Year");
		year = kb.nextLine();
		System.out.println("Enter Month");
		month = kb.nextLine();
		System.out.println("Enter date");
		date = kb.nextLine();
		Statement stmt7 = con.createStatement();
		ResultSet rs7 = stmt7.executeQuery("SELECT v.driver_name AS Driver_Name\r\n"
				+ "FROM van as v, vanschedule as vs\r\n" + "WHERE v.van_id=vs.van_id\r\n" + "AND vs.date_of_delivery=\""
				+ year + "-" + month + "-" + date + "\"");

		int count7 = 0;
		while (rs7.next()) {
			count7++;
			String columnValue7 = rs7.getString("Driver_Name");
			System.out.println("Driver Name : " + columnValue7);
		}
		System.out.println();

		System.out.println("Total rows: " + count7);
		System.out.println();
	}

	private static void case6(Connection con) throws SQLException {
		Statement stmt6 = con.createStatement();
		ResultSet rs6 = stmt6.executeQuery("SELECT  p.BankName,count(u.UserID) AS Number_Of_Orders\r\n" +
				"FROM orderDetails AS o,PaymentCardDetails AS p, users AS u\r\n" +
				"WHERE u.UserID=o.UserID and u.UserID=p.UserID\r\n" +
				"GROUP BY p.BankName\r\nORDER BY p.BankName ASC\r\n");
		ResultSetMetaData rsmd6 = rs6.getMetaData();
		int columnsNumber6 = rsmd6.getColumnCount();
		int count6 = 0;
		while (rs6.next()) {
			count6++;

			for (int i = 1; i <= columnsNumber6; i++) {
				if (i > 1)
					System.out.print(",  ");

				String columnValue6 = rs6.getString(i);
				System.out.print(rsmd6.getColumnName(i) + " : " + columnValue6);
			}
			System.out.println();
		}
		System.out.println("Total rows: " + count6);
		System.out.println();
	}

	private static void case5(Connection con) throws SQLException {
		Statement stmt5 = con.createStatement();
		ResultSet rs5 = stmt5.executeQuery("SELECT innertable.Age,count(innertable.Age) AS Count FROM  \r\n(SELECT    UserName as UName,\r\nYEAR(Current_timeStamp)- YEAR(DateOfBirth) AS Age\r\nFROM     Users ) \r\nAS innertable\r\nGROUP BY innertable.Age\r\nORDER BY innertable.Age ASC\r\n");
		ResultSetMetaData rsmd5 = rs5.getMetaData();
		int columnsNumber5 = rsmd5.getColumnCount();
		int count5 = 0;
		while (rs5.next()) {

			for (int i = 1; i <= columnsNumber5; i++) {
				if (i > 1)
					System.out.print(",  ");
				int columnValue = rs5.getInt(i);
				System.out.print(rsmd5.getColumnName(i) + " : " + columnValue);
				count5++;
			}
			System.out.println();
		}
		System.out.println("Total rows: " + count5);
		System.out.println();
	}

	private static void case4(Scanner kb, Connection con) throws SQLException {
		Statement stmt4 = con.createStatement();
		System.out.println("Enter the dollar Amount");
		String dollarAmt = kb.nextLine();
		ResultSet rs4 = stmt4.executeQuery(
				"SELECT u.username AS UName FROM   users As U,orderdetails As ou WHERE U.UserID=ou.UserID AND TotalAmount>"
						+ dollarAmt);
		int count4 = 0;
		while (rs4.next()) {
			count4++;
			System.out.println(rs4.getString("UName"));
		}
		System.out.println("Total rows: " + count4);
		System.out.println();
	}

	private static void case1(Scanner kb, Connection con) throws SQLException {
		String year;
		String month;
		String date;
		Statement stmt = con.createStatement();
		System.out.println("Enter Year");
		year = kb.nextLine();
		System.out.println("Enter Month");
		month = kb.nextLine();
		System.out.println("Enter date");
		date = kb.nextLine();
		ResultSet rs = stmt.executeQuery(
				"Select * from inventory where date_of_expiry=\"" + year + "-" + month + "-" + date + "\"");

		ResultSetMetaData rsmd = rs.getMetaData();
		// System.out.println(rsmd);
		int columnsNumber = rsmd.getColumnCount();

		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1)
					System.out.print(",  ");
				String columnValue = rs.getString(i);
				System.out.print(rsmd.getColumnName(i) + " : " + columnValue);
			}
			System.out.println();

		}
		System.out.println();
	}

	private static void optionsDisplay() {
		System.out.println("Enter 1 - List all the items that has given date of expiry (Example 2023 10 03)");

		System.out
				.println("Enter 2 - List the Products that are manufactured by each company and count of that items ");

		System.out.println("Enter 3 - List of items that are not available in inventory");

		System.out.println("Enter 4 - List of user names who have ordered groceries more than x  $ (Example 100)");

		System.out.println("Enter 5 - Group the members by their age and find the count of each age group");

		System.out.println("Enter 6 - Group the number of orders by each bank and print the count");
		System.out.println("Enter 7 - Print the name of drivers who are working on Given Date (Example 2019 11 11)");
		System.out.println(
				"Enter 8 - List of users and email ids who have ordered an item as you enter (Example : orange peels)");

		System.out.println("Enter 9 - List of users who have ordered 2 different items as you enter in the same order");

		System.out.println("Enter 10 - Count the number of users from the state you enter");
		System.out.println("Enter 0 for quitting");
	}

}
