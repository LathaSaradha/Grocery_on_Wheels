package groceryOnWheels;

import java.sql.*;
import java.text.*;
import java.util.*;

public class VanSchedule {


    public void insertvanSchedule(Connection myConn) {
        System.out.println(" vanschedule details method");
        try {

            Random rand = new Random();


            int admin = 1000, admax = 10000;
            int address_id = rand.nextInt((admax - admin) + 1) + admin;
            int shmin = 800, shmax = 20000;
            int schedule_id = rand.nextInt((shmax - shmin) + 1) + shmin;

            String[] street_name = {"North Talman", "North Artesian", "North Wastenaw", "North Pulaski Road", "Roosevel Road", "Augusta Cir",
                    "Halsted Street", "Milwuakee Avenue", "Hubbard Street", "Western Avenue", "Dempster Avenue", "Kedzie Avenue",
                    "Broadway", "Houston Street", "Madison Avenue", "Grand Street", "Christopher Street", "Wall Street", "Victory Boulevard"


            };
            String[] city = {"Chicago", "Peoria", "Rockford", "Champaign", "Schaumburg", "Elgin", "Evanston", "Carbondale", "Bolingbrook", "Denver",
                    "Aurora", "Fort Collins", "Castle Rock", "Boulder", "Westminster", "Pueblo", "Thornton", "Broomfield"

            };

            String[] state = {"Illinois", "California", "Texas", "Florida", "Colorado", "Arizona", "Alaska", "Arkansas",
                    "Maryland", "Massachusetts", "Michigan", "Kentucky", "New Jersey", "Indiana", "Idaho",
                    "New York", "Missouri", "Mississippi", "Iowa"

            };

            int zcmin = 60000, zcmax = 90000;
            int zipcode = rand.nextInt((zcmax - zcmin) + 1) + zcmin;

            String[] date_of_delivery = {"2019-01-17", "2019-06-12", "2019-08-16", "2019-09-30", "2019-11-11",
                    "2019-03-21", "2019-02-12", "2019-12-29", "2019-09-15", "2019-10-03",
                    "2019-04-30", "2019-05-14", "2019-07-22", "2019-01-18", "2019-10-21",
                    "2020-01-01", "2020-01-02", "2020-01-19"
            };


            String[] gps_location = {"Atesian & Talman", "Washtenaw & Talman", "Bryn Mawr & Claremont", "Rosemont & Granville",
                    "Talman & Granville", "Washtenaw & Granville", "Artesian & Granville", "Pulaski Road & Kedzie"


            };

            String[] departure_time = {"8:30 AM", "7:30 AM", "9:00 AM", "9:30 AM", "9:45 AM", "10:00 AM",
                    "11:30 AM", "11:00 AM", "11:30 PM", "12:30 PM", "12:40 PM", "1:00 PM",
                    "1:30 PM", "1:40 PM", "2:00 PM", "2:30 PM", "2:40 PM", "3:00 PM",
                    "3:30 PM", "4:00 PM", "4:30 PM", "4:20 PM", "4:30 PM", "5:00 PM",
                    "5:30 PM", "5:20 PM", "5:30 PM"


            };


            String[] arrival_time = {"9:10 AM", "8:00 AM", "9:30 AM", "10:00 AM", "10:10 AM", "10:30 AM",
                    "11:00 AM", "11:30 AM", "12:00 PM", "1:00 PM", "1:10 PM", "1:30 PM",
                    "2:00 PM", "2:10 PM", "2:30 PM", "3:00 PM", "3:10 PM", "3:20 PM",
                    "3:30 PM", "4:00 PM", "4:10 PM", "4:20 PM", "4:30 PM", "5:00 PM",
                    "5:10 PM", "5:20 PM", "5:30 PM", "6:00 PM"
            };


            PreparedStatement ps = myConn.prepareStatement("INSERT INTO vanschedule(van_id,address_id,schedule_id," +
                    "street_name,city,state,zipcode,date_of_delivery,Gps_location,departure_time,arrival_time)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?)");

            Statement stmt = myConn.createStatement();


            int i = 0;
            int j = 0;
            int[] van_id = new int[200];

            ResultSet rs = stmt.executeQuery("select van_id from van");

            while (rs.next()) {
                van_id[i++] = rs.getInt(1);
            }


            while (j < 200) {


                ps.setInt(1, van_id[j++]);
                ps.setInt(2, address_id++);
                ps.setInt(3, schedule_id++);
                ps.setString(4, street_name[rand.nextInt(street_name.length)]);
                ps.setString(5, city[rand.nextInt(city.length)]);
                ps.setString(6, state[rand.nextInt(state.length)]);
                ps.setInt(7, zipcode++);
                ps.setString(8, date_of_delivery[rand.nextInt(date_of_delivery.length)]);
                ps.setString(9, gps_location[rand.nextInt(gps_location.length)]);
                ps.setString(10, departure_time[rand.nextInt(departure_time.length)]);
                ps.setString(11, arrival_time[rand.nextInt(arrival_time.length)]);


                ps.execute();


            }

            System.out.println( j + " records inserted in Van Table");


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }



    }


}






