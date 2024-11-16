package main.DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static Connection conn;
	public boolean connNull = true;
	public boolean connIsValid = false;
	public int rowsInserted = 0;

	public JDBC() throws IOException, InterruptedException {
		String url = "jdbc:mysql://127.0.0.1:3306/airtime_test?reconnect=true";
		String user = "AirTime_123";
		String password = "dqEn%rv@Up%$2?f";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				// initialize();
				connNull = conn == null;
				connIsValid = conn.isValid(5);
				
				System.out.println("Connected to the database");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() {
		return conn;
	}
	
	public void setAutoCommit(boolean choice) {
		try {
			JDBC.conn.setAutoCommit(choice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /*
	public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing the database connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
    */

    public static void main(String[] args) {
        try {
            DB DBInstance = new DB();

            Boolean result = DBInstance.createUser(1, "admin11", "damin11", "admin");
            System.out.println(result);
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
