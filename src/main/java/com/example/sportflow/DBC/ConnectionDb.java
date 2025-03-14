package com.example.sportflow.DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to the database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportflow", "root", "");

        // Create Statement object to execute SQL queries
        Statement stm = connection.createStatement();

        // Create 'members' table
        String memberTable = "CREATE TABLE IF NOT EXISTS members ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nom VARCHAR(255) NOT NULL, "
                + "datedenaissance DATE NOT NULL, "
                + "sportpratique VARCHAR(255) NOT NULL)";
        stm.executeUpdate(memberTable);

        // Create 'entraineurs' table
        String entraineurTable = "CREATE TABLE IF NOT EXISTS entraineurs ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nom VARCHAR(50), "
                + "specialite VARCHAR(50))";
        stm.executeUpdate(entraineurTable);

        // Create 'seance' table
        String seanceTable = "CREATE TABLE IF NOT EXISTS seance ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "id_Membre INT, "
                + "id_Entraineur INT, "
                + "dateHeure DATE, "
                + "FOREIGN KEY (id_Membre) REFERENCES members(id), "
                + "FOREIGN KEY (id_Entraineur) REFERENCES entraineurs(id))";
        stm.executeUpdate(seanceTable);

        // Create 'users' table
        String userTable = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(50), "
                + "password VARCHAR(50), "
                + "role VARCHAR(50))";
        stm.executeUpdate(userTable);

        return connection;
    }
}
