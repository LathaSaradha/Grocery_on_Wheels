package groceryOnWheels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;


public class UserCart {

	public void enterRows(Connection con) {
		int linenum = 0;
		Random rand = new Random();

		int start = 100;
		int end = 9999999;

		try {

			Statement stmt = con.createStatement();

			System.out.println("Connection Established for UserCart");

			ResultSet rsForUsers = stmt.executeQuery("select UserID from users ORDER BY RAND() LIMIT 200");

			PreparedStatement ps;
			System.out.println("Entering Records in UserCart Table.....");

			while (rsForUsers.next())

			{
				ResultSet rsForInventory;
				Statement stmt2 = con.createStatement();
				List<Integer> listItemId = new ArrayList<>();
				List<Integer> listItem_qty = new ArrayList<>();
				List<Double> listPrice = new ArrayList<>();
				List<Double> listDiscount = new ArrayList<>();

				int UserId = rsForUsers.getInt("UserID");
				int numOfItems = rand.nextInt(14) + 1;
				int count = 0;
				int cartid = (rand.nextInt(end - start) + start);


				rsForInventory = stmt2.executeQuery(
						"select distinct item_id,item_qty,price,discount from inventory where item_qty>0 ORDER BY RAND() LIMIT "
								+ numOfItems);

				// Adding the inventory list to a collection of list.
				while (rsForInventory.next()) {

					listItemId.add(rsForInventory.getInt("item_id"));
					listItem_qty.add(rsForInventory.getInt("item_qty"));
					listPrice.add(rsForInventory.getDouble("price"));
					listDiscount.add(rsForInventory.getDouble("discount"));
				}


				while (count < numOfItems) {
					linenum++;
					// System.out.println(linenum);
					ps = con.prepareStatement("INSERT INTO UserCart(UserID,CartID,Item_id,ItemQtyforCart,Price)" +

							"VALUES(?,?,?,?,?)");

					int maxItem_qty = listItem_qty.get(count);
					double price = listPrice.get(count);
					double disc = listDiscount.get(count);
					int ItemQtyforCart = rand.nextInt(10) + 1;
					double priceAfterDiscount = (ItemQtyforCart * (price / maxItem_qty)) * ((100 - disc) / 100);
					ps.setInt(1, UserId);

					// Cart Id
					ps.setInt(2, cartid);

					ps.setInt(3, listItemId.get(count));

					// Assuming that a user will order only upto 10 nums for an item

					ps.setInt(4, ItemQtyforCart);

					ps.setDouble(5, priceAfterDiscount);

					ps.execute();
					ps.close();
					count++;

				}
				//System.out.println(	count + " item/s added by user:" + UserId + " in UserCart Table for the cart ID :" + cartid);
				rsForInventory.close();
				stmt2.close();
			}
			System.out.println("Completed entry of UserCart. Inserted " + linenum + " records");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}
}
