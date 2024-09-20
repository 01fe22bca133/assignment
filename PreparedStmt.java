import java.sql.*;

public class PreparedStmt {
    public static void main(String[] args) {
        String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "SYSTEM";
        String password = "BCA5C";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish the connection
            con = DriverManager.getConnection(DB_URL, user, password);

            if (con != null) {
                System.out.println("Connection successful to Oracle DB");

                // Prepare SQL statement
                String insertSql = "INSERT INTO student (sid, sname, city) VALUES (?, ?, ?)";
                pstmt = con.prepareStatement(insertSql);
                pstmt.setInt(1, 135);
                pstmt.setString(2, "Prateek");
                pstmt.setString(3, "Hubli");

                // Execute update
                int r = pstmt.executeUpdate();
                System.out.println(r + " row(s) inserted.");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
