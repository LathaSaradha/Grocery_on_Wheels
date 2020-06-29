package groceryOnWheels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Carry {

	public Carry() {

	}

	public void enterRows(Connection con) {

		int linenum = 0;

		try {
			
			Statement stmt = con.createStatement();

			Statement stmt2 = con.createStatement();
			Statement stmt3 = con.createStatement();
			
			List<Integer> listOrderId = new ArrayList<>();
			List<Integer> listUserId = new ArrayList<>();
			
			System.out.println("Connection Established for Carry");

			ResultSet rsForOrders = stmt.executeQuery("select OrderID,UserID from orderdetails ORDER BY RAND() LIMIT 150");
			while (rsForOrders.next())

			{

				listOrderId.add(rsForOrders.getInt("OrderID"));
				listUserId.add(rsForOrders.getInt("UserID"));

			}
			//System.out.println("Step 1 completed for Carry");

			ResultSet rsForVanId = stmt2.executeQuery("select van_id from van ORDER BY RAND() LIMIT 20");

			while (rsForVanId.next()) {

				//System.out.println("Step 2  for Carry");
				int vanid = rsForVanId.getInt("van_id");
				for (int i = 0; i < 5; i++) {
					linenum++;
					int orderID = listOrderId.get(linenum);
					int userID = listUserId.get(linenum);
					
					List<Integer> listItemId = new ArrayList<>();

					//System.out.println("Step 2  for "+orderID);
					ResultSet rsforItemID = stmt3.executeQuery("select Item_id from userCart where userid=" + userID);

					while (rsforItemID.next())

					{
						listItemId.add(rsforItemID.getInt("Item_id"));

					}
					//System.out.println("Step 3  for list of items for "+orderID);
					//System.out.println(listItemId);
					//System.out.println(listItemId.size());

					for (int j = 0; j < listItemId.size(); j++)

					{PreparedStatement ps;

						
						ps = con.prepareStatement("INSERT INTO carry(van_id,OrderID,item_id)" + "VALUES(?,?,?)");

						ps.setInt(1, vanid);

						ps.setInt(2, orderID);

						ps.setInt(3, listItemId.get(j));
						ps.execute();
						

					}
					
					//System.out.println( orderID + " is going to be in Carried by the van ID :" + vanid);
				}
				
				
			}
			rsForOrders.close();
			rsForVanId.close();
			
			stmt.close();
			stmt2.close();
			stmt3.close();
			System.out.println("Records added in Carry Table");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
