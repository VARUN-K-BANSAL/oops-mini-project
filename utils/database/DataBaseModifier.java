package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import individuals.Varun;
import utils.CurrentAccountUser;
import utils.SavingAccountUser;

public class DataBaseModifier {
    public static void addDataToAccountTable(String data[]) {
        Connection con = ConnectionFactory.getConnection();
        String insertQuery = "INSERT INTO account VALUES(" + "?, " + "?, " + "?, " + "?, " + "?, " + "?, " + "?)";

        try (PreparedStatement stmt = con.prepareStatement(insertQuery)) {
            stmt.setString(1, data[0]);
            stmt.setString(2, data[1]);
            stmt.setString(3, data[2]);
            stmt.setString(4, data[3]);
            stmt.setString(5, data[4]);
            stmt.setString(6, data[5]);
            stmt.setString(7, data[6]);
            stmt.executeUpdate();
        } catch (Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Unable to add data to the database....");
            }
            return;
        }
    }

    public static void addDataToAccountTable(CurrentAccountUser user) {
        String[] data = new String[7];
        data[0] = user.getUsername();
        data[1] = user.getPassword();
        data[2] = user.getAccount().getAccountNumber();
        data[3] = "CA";
        data[4] = user.getAccount().getName();
        data[5] = Double.toString(user.getAccount().getBalance());
        data[6] = user.getAccount().getGender();
        addDataToAccountTable(data);
    }

    public static void addDataToAccountTable(SavingAccountUser user) {
        String[] data = new String[7];
        data[0] = user.getUsername();
        data[1] = user.getPassword();
        data[2] = user.getAccount().getAccountNumber();
        data[3] = "SA";
        data[4] = user.getAccount().getName();
        data[5] = Double.toString(user.getAccount().getBalance());
        data[6] = user.getAccount().getGender();
        addDataToAccountTable(data);
    }

    public static void updatePassword(String username, String newPassword) {
        Connection con = ConnectionFactory.getConnection();
        String query = "UPDATE account SET password = ? WHERE username = ?";
        try (PreparedStatement statement2 = con.prepareStatement(query)) {
            statement2.setString(1, newPassword);
            statement2.setString(2, username);
            if (statement2.executeUpdate() != 0) {
                System.out.println("Password for " + username +" has been updated Successfully");
            } else {
                System.out.println("Unable to update password");
            }
        } catch (Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occured");
            }
        }
        try {
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }
}
