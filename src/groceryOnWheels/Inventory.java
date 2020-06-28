package groceryOnWheels;

import java.io.*;
import java.nio.file.Path;
import java.sql.*;
import java.text.*;
import java.util.*;

public class Inventory {

	private String fileName;

	public Inventory(String fileName) {
		this.fileName = fileName;
	}

	public Inventory() {

	}

	public void insertInventory(Connection con) throws SQLException {


		System.out.println(this.fileName);


		Path path = new File(Users.class.getResource(this.fileName).getFile()).toPath();


		System.out.println("Enter the File Path for " + this.fileName);

		System.out.println(path);


		double minBillAmt = 1.0;
		double maxBillAmt = 1000.0;

		DecimalFormat form = new DecimalFormat("##.00");

		try (BufferedReader br = new BufferedReader(new FileReader(new File(path + "")))) {


			System.out.println("Connection Established");
			int count=0;


			Random rand = new Random();
			int min = 555, max = 999;
			int item_id = rand.nextInt((max - min) + 1) + min;

			String[] date_of_expiry = {"2022-01-17", "2022-06-12", "2022-08-16", "2022-09-30", "2022-11-11",
					"2021-03-21", "2022-02-12", "2022-12-29", "2022-09-15", "2023-10-03",
					"2021-04-30", "2023-05-14", "2023-07-22", "2023-01-18", "2023-10-21"
			};
			int[] item_qty = {0, 1, 2, 3, 4, 5, 10, 15, 6, 8, 11, 13, 12, 20, 100, 70, 40, 50, 37, 32, 46, 50,
					150, 38, 39, 45};


			int[] discount = {10, 50, 20, 15, 30, 25, 35, 55, 40, 45, 60, 65, 70};


			PreparedStatement ps = con.prepareStatement("INSERT INTO inventory(item_id,price,date_of_expiry,company_name,item_qty,item_name,discount)" + "VALUES(?,?,?,?,?,?,?)");


			String lineText;

			br.readLine();

			while ((lineText = br.readLine()) != null) {

				String[] data = lineText.split(",");

				String company_name = data[0];
				String item_name = data[1];

				double amt = (rand.nextDouble() * (maxBillAmt - minBillAmt)) + minBillAmt;
				ps.setInt(1, item_id++);
				ps.setDouble(2, Double.parseDouble(form.format(amt)));



				ps.setString(3, date_of_expiry[rand.nextInt(date_of_expiry.length)]);
				ps.setString(4, company_name);
				ps.setInt(5, item_qty[rand.nextInt(item_qty.length)]);
				ps.setString(6, item_name);
				ps.setInt(7, discount[rand.nextInt(discount.length)]);


				ps.execute();
				count++;

			}
			ps.close();
			System.out.println( count + " records inserted in Inventory Table");


		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}



