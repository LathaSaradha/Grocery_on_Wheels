package groceryOnWheels;

import java.sql.*;



public class MysqlConnection {

Connection con = null;

public  Connection createConnection () throws SQLException {

try {

Class.forName("com.mysql.cj.jdbc.Driver");

  con = DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/grocerydb","root","ancient70s");  

System.out.println("MYSQL Connection established");


}
catch(Exception e)
{
System.out.println(e);
}

return con;
  }
public MysqlConnection() {} // default constructor

}

