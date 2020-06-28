package groceryOnWheels;

import java.io.*;
import java.nio.file.Path;
import java.sql.*;
import java.text.*;
import java.util.*;

public class VanDetails {

    private String fileName;



    public VanDetails(String fileName) {
        this.fileName = fileName;
    }

    public VanDetails() {

    }

    public void insertVan(Connection myConn) throws SQLException, ParseException {
        System.out.println("inside van details method");
        try {

            System.out.println(this.fileName);


            Path path = new File(Users.class.getResource(this.fileName).getFile()).toPath();
            BufferedReader lineReader = new BufferedReader(new FileReader(new File(path + "")));
            Random rand = new Random();
            int min = 1111, max = 4444;
            int van_id = rand.nextInt((max - min) + 1) + min;
            int vid_min = 22222, vid_max = 44444;


            PreparedStatement ps = myConn.prepareStatement("INSERT INTO VAN(van_id,driver_name,model_name,vehicle_Identification)" + "VALUES(?,?,?,?)");


            String lineText;

            lineReader.readLine();
            int count=0;

            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",");

                String driver_name = data[0];
                String model_name = data[1];


                ps.setInt(1, van_id++);
                ps.setString(2, driver_name);
                ps.setString(3, model_name);
                String[] state = {"IL", "AZ", "TX", "CL", "OH", "NY", "MD", "NJ"};
                String state_vid = state[rand.nextInt(state.length)] + (rand.nextInt((vid_max - vid_min) + 1) + vid_min);
               // System.out.println("state_vid::" + state_vid);
                ps.setString(4, state_vid);

                ps.execute();
                count++;

            }
            System.out.println( count + " records inserted in Van Table");


            lineReader.close();
            myConn.close();
            ps.close();


        } catch (IOException e) {
            System.err.println(e);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }


}
