package code.alpha.studentgradetracker;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        try {
            Connection con = DatabaseConnection.getConnection();

            System.out.println("Database connected successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}