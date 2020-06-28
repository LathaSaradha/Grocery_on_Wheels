package groceryOnWheels;

import java.sql.Connection;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class Driver {

    public void insertRecords(Connection con) {

        Scanner kb = new Scanner(System.in);

        try {



            String filename = "Users.csv";


            // User Table
            Users user = new Users(filename);
            user.enterUsers(con);

            // Payment Card Details table
            PaymentCardDetails pc = new PaymentCardDetails();
            pc.enterRows(con);

            filename = "inventory.csv";


            Inventory invObj = new Inventory(filename);
            invObj.insertInventory(con);

            // User Cart Table
            UserCart us = new UserCart();
            us.enterRows(con);

            // Order Details Table
            OrderDetails od = new OrderDetails();
            od.enterRows(con);

            //Carry table
            Carry c = new Carry();
            c.enterRows(con);


            filename = "vanDetails.csv";
            VanDetails vanObj = new VanDetails(filename);
            vanObj.insertVan(con);


            VanSchedule schObj = new VanSchedule();
            schObj.insertvanSchedule(con);


            con.close();
            System.out.println("Completed entire Program");

        } catch (SQLException | ParseException e) {

            e.printStackTrace();

        }

        kb.close();

    }

}
