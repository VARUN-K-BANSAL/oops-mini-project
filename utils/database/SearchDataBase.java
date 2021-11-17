package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;

import individuals.Varun;
import utils.CurrentAccountUser;
import utils.SavingAccountUser;

public class SearchDataBase {
    public static Object searchUser(String username) {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM account";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                if(rs.getString("username").equals(username)) {
                    if(rs.getString("account_type").equalsIgnoreCase("CA")) {
                        CurrentAccountUser user = new CurrentAccountUser(rs.getString("account_holder_name"),
                                                                        rs.getString("gender"),
                                                                        rs.getString("username"),
                                                                        rs.getString("password"),
                                                                        Double.valueOf(rs.getString("account_balance")));
                        user.getAccount().setAccountNumber(rs.getString("account_number"));
                        System.out.println(user);
                        return user;
                    } else if(rs.getString("account_type").equalsIgnoreCase("SA")) {
                        SavingAccountUser user = new SavingAccountUser(rs.getString("account_holder_name"),
                                                                        rs.getString("gender"),
                                                                        rs.getString("username"),
                                                                        rs.getString("password"),
                                                                        Double.valueOf(rs.getString("account_balance")));
                        user.getAccount().setAccountNumber(rs.getString("account_number"));
                        System.out.println(user);
                        return user;
                    }
                }
            }
            System.out.println("Record not found");
        } catch(Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some error occurred while accessing the database");
            }
        }
        try {
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
        return null;
    }

    public static void searchUserByAccountNumber(String number) {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM account";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                if(rs.getString("account_number").equals(number)) {
                    if(rs.getString("account_type").equalsIgnoreCase("CA")) {
                        CurrentAccountUser user = new CurrentAccountUser(rs.getString("account_holder_name"),
                                                                        rs.getString("gender"),
                                                                        rs.getString("username"),
                                                                        rs.getString("password"),
                                                                        Double.valueOf(rs.getString("account_balance")));
                        user.getAccount().setAccountNumber(rs.getString("account_number"));
                        System.out.println(user);
                        return;
                    } else if(rs.getString("account_type").equalsIgnoreCase("SA")) {
                        SavingAccountUser user = new SavingAccountUser(rs.getString("account_holder_name"),
                                                                        rs.getString("gender"),
                                                                        rs.getString("username"),
                                                                        rs.getString("password"),
                                                                        Double.valueOf(rs.getString("account_balance")));
                        user.getAccount().setAccountNumber(rs.getString("account_number"));
                        System.out.println(user);
                        return;
                    }
                }
            }
            System.out.println("Record not found");
        } catch(Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some error occurred while accessing the database");
            }
        }
        try {
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }
    
    public static void searchUserByName(String name) {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM account";
        boolean isFound = false;
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                if(rs.getString("account_holder_name").equals(name.toLowerCase())) {
                    isFound = true;
                    if(rs.getString("account_type").equalsIgnoreCase("CA")) {
                        CurrentAccountUser user = new CurrentAccountUser(rs.getString("account_holder_name"),
                                                                        rs.getString("gender"),
                                                                        rs.getString("username"),
                                                                        rs.getString("password"),
                                                                        Double.valueOf(rs.getString("account_balance")));
                        user.getAccount().setAccountNumber(rs.getString("account_number"));
                        System.out.println(user);
                    } else if(rs.getString("account_type").equalsIgnoreCase("SA")) {
                        SavingAccountUser user = new SavingAccountUser(rs.getString("account_holder_name"),
                                                                        rs.getString("gender"),
                                                                        rs.getString("username"),
                                                                        rs.getString("password"),
                                                                        Double.valueOf(rs.getString("account_balance")));
                        user.getAccount().setAccountNumber(rs.getString("account_number"));
                        System.out.println(user);
                    }
                }
            }
            if(!isFound) {
                System.out.println("Record not found");
            }
        } catch(Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some error occurred while accessing the database");
            }
        }
        try {
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }
}
