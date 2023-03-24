import java.sql.*;

public class ConnectionClass {

    Connection con;
    Statement stm;

    ConnectionClass() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "Aadi2108*");
            stm = con.createStatement();

            if (con.isClosed()) {
                System.out.println("Connection Successful");
            } else {
                // System.out.println("Connection Failed");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
