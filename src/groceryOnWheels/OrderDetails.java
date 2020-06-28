package groceryOnWheels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;

import java.sql.Date;

import java.util.Random;

public class OrderDetails {

	@SuppressWarnings("deprecation")
	public void enterRows(Connection con) {
		int linenum = 0;
		int start = 100;
		int end = 9999999;

		Random rand = new Random();
		DecimalFormat form = new DecimalFormat("##.00");

		try {

			Statement stmt = con.createStatement();

			System.out.println("Connection Established");

			ResultSet rs = stmt.executeQuery("select distinct u.UserID from users u, usercart uc where uc.userid=u.userid ORDER BY RAND() LIMIT 200");
			PreparedStatement ps = null;
			int year, month, date, hour, minute, second, nano;

			while (rs.next())

			{
				ResultSet rsforTotalAmt;
				Statement stmt2 = con.createStatement();
				linenum++;
				//System.out.println("Row " + linenum + " inserted" + " in OrderDetails Table");
				int UserId = rs.getInt("UserID");
			//	System.out.println(UserId);

				rsforTotalAmt = stmt2.executeQuery("select sum(price) from usercart where userId=" + UserId);
				
				//System.out.println(rsforTotalAmt);
				rsforTotalAmt.next();
				double amt = rsforTotalAmt.getDouble("sum(price)");
				ps = con.prepareStatement("INSERT INTO OrderDetails(UserID,OrderID,TotalAmount,OrderDate,OrderTime)" +

						"VALUES(?,?,?,?,?)");

				year = rand.nextInt(2019 - 2009) + 99;
				month = rand.nextInt(11);
				date = createDate(month);
				hour = rand.nextInt(11);
				minute = rand.nextInt(59);
				second = rand.nextInt(59);
				nano = rand.nextInt(1000000000);

				ps.setInt(1, UserId);
				ps.setInt(2, (rand.nextInt(end - start) + start));

				ps.setDouble(3, Double.parseDouble(form.format(amt)));

				ps.setDate(4, new Date(year, month, date));

				ps.setTimestamp(5, new Timestamp(year, month, date, hour, minute, second, nano));

				ps.execute();
				ps.close();
				
			}
			rs.close();
			stmt.close();
			ps.close();
			System.out.println("Completed entry of OrderDetails Table. Inserted " + linenum + " records");
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private int createDate(int month) {
		int date;
		Random rand = new Random();
		if (month == 1)
			date = rand.nextInt(27) + 1;

		else if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11)
			date = rand.nextInt(30) + 1;
		else
			date = rand.nextInt(29) + 1;
		return date;
	}
}
