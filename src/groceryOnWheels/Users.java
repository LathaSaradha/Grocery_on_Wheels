package groceryOnWheels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Path;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Users {

    private String fileName;


    public Users(String fileName) {
        this.fileName = fileName;
    }

    public Users() {

    }


    public void enterUsers(Connection con) {

        String s;
        String[] sArray;
        int linenum = 0;
        Scanner kb = new Scanner(System.in);

        System.out.println(this.fileName);


        Path path = new File(Users.class.getResource(this.fileName).getFile()).toPath();


        System.out.println("Enter the File Path for Users.csv");

        System.out.println(path);


        try (BufferedReader br = new BufferedReader(new FileReader(new File(path + "")))) {

            Statement stmt = con.createStatement();
            System.out.println("Connection Established");

            Instant start = Instant.now();

            List<Integer> range = IntStream.range(0, 10000).boxed().collect(Collectors.toCollection(ArrayList::new));
            Collections.shuffle(range);

            System.out.println("Entering records in Users Table");

            while ((s = br.readLine()) != null) {

                // System.out.println(s);
                sArray = s.split(",");
                //System.out.println("Row " + linenum + " inserted in Users Table");
                linenum++;
                // System.out.println(sArray[0]);

                int uid = range.get(linenum);
                // System.out.println(uid);
                String username = sArray[0];
                String aptnum = sArray[1];
                String StreetName = sArray[2];
                String City = sArray[3];
                String State = sArray[4];
                int zcode = Integer.parseInt(sArray[5]);

                String DateOfBirth = sArray[6];
                String PhoneNo = sArray[7];
                String EmailID = sArray[8];

                String sql = "insert into users values (" + uid + ",'" + username + "','" + aptnum + "','" + StreetName
                        + "','" + City + "','" + State + "'," + zcode + "," + DateOfBirth + "," + PhoneNo + ",'"
                        + EmailID + "')";


                stmt.execute(sql);


            }
			System.out.println( linenum + " records inserted in Users Table");
            Instant stop = Instant.now();
            long timeElapsed = Duration.between(start, stop).toMillis();
            System.out.println("Time Elapsed in Milliseconds: " + timeElapsed);
            System.out.println("Completed entry of Users .Inserted " + linenum + " records");
            stmt.close();
        } catch (FileNotFoundException e) {

            System.out.println("File not found");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();


            System.out.println("IO error");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("SQL error");
        }
        kb.close();
    }


}
