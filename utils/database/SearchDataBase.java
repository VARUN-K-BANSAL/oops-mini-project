package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchDataBase {
    public static void searchUser(String username) {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM account";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                if(rs.getString("username").equals(username)) {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Name of account holder : " + rs.getString("account_holder_name"));
                    System.out.println("Account Number : " + rs.getString("account_number"));
                    System.out.println("Current Balance : " + rs.getString("account_balance"));
                    System.out.println("Type of Account : " + ((rs.getString("account_type").equals("SA")) ? "Savings Account" : "Current Account"));
                    System.out.println("------------------------------------------------------------------------");
                    return;
                }
            }
            System.out.println("Record not found");
        } catch(Exception e) {
            System.out.println("Some error occurred while accessing the database");
            // e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    public static void searchUserByAccountNumber(String number) {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM account";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                if(rs.getString("account_number").equals(number)) {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Name of account holder : " + rs.getString("account_holder_name"));
                    System.out.println("Account Number : " + rs.getString("account_number"));
                    System.out.println("Current Balance : " + rs.getString("account_balance"));
                    System.out.println("Type of Account : " + ((rs.getString("account_type").equals("SA")) ? "Savings Account" : "Current Account"));
                    System.out.println("------------------------------------------------------------------------");
                    return;
                }
            }
            System.out.println("Record not found");
        } catch(Exception e) {
            System.out.println("Some error occurred while accessing the database");
            // e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }
}
