package groceryOnWheels;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PaymentCardDetails {

	public void enterRows(Connection con) {

		int linenum = 0;
		String[] BankNameArray = { "Chase", "Bank of America", "JP Morgan Chase", "Citi Bank", "Wells Fargo",
				"PNC financial Services", "Capital One", "TD Bank", "Goldman Sachs", "Fifth Third Bank" };
		// Random Number Generation for Payment Source ID.
		List<Integer> PaymentSIDrange = IntStream.range(10000, 99999).boxed()
				.collect(Collectors.toCollection(ArrayList::new));

		int start = 10000;
		int end = 99999;
		Random rand = new Random();

		// File file = new File(classLoader.getResource(fileName).getFile());

		try {

			Statement stmt = con.createStatement();

			System.out.println("Connection Established");

			ResultSet rs = stmt
					.executeQuery("select UserID, AptNo,StreetName, City,State,ZipCode from users LIMIT 200");
			PreparedStatement ps;

			System.out.println("Entering records in PaymentCardDetails Table");

			while (rs.next()) {
				linenum++;
				System.out.println("Row " + linenum + " inserted" + " in PaymentCardDetails Table");
				int UserId = rs.getInt("UserID");
				String AptNo = rs.getString("AptNo");

				String StreetName = rs.getString("StreetName");

				String City = rs.getString("City");

				String State = rs.getString("State");

				String ZipCode = rs.getString("ZipCode");

				ps = con.prepareStatement(
						"INSERT INTO PaymentCardDetails(UserID,PaymentSourceID,BankName,CardNum,AptNum,Street_Name,City,State,ZipCode)"
								+ "VALUES(?,?,?,?,?,?,?,?,?)");

				ps.setInt(1, UserId);
				ps.setInt(2, PaymentSIDrange.get(rand.nextInt(PaymentSIDrange.size())));

				ps.setString(3, BankNameArray[rand.nextInt(BankNameArray.length)]);
				ps.setString(4, (rand.nextInt(end - start) + start) + "" + (rand.nextInt(end - start) + start));

				ps.setString(5, AptNo);
				ps.setString(6, StreetName);
				ps.setString(7, City);
				ps.setString(8, State);

				ps.setString(9, ZipCode);
				ps.execute();

			}
			System.out.println("Completed entry of PaymentCard .Inserted " + linenum + " records");
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Completed entry of PaymentCard");

		}

	}

}
