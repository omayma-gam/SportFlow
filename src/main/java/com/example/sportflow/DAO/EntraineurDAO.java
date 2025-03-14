package com.example.sportflow.DAO;

import com.example.sportflow.DBC.ConnectionDb;
import com.example.sportflow.Model.Entraineur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntraineurDAO {
    private Connection connection;
    public EntraineurDAO() throws SQLException, ClassNotFoundException {
        connection = ConnectionDb.getConnection();
    }
    public void ajouterEntraineur(Entraineur entraineur) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO entraineurs (nom, specialite) VALUES (?, ?)";
        try (Connection connection = ConnectionDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, entraineur.getNom());
            preparedStatement.setString(2, entraineur.getSpecialite());
            preparedStatement.executeUpdate();
        }
    }


    public void modifierEntraineur(Entraineur entraineur) throws SQLException {
        String query = "UPDATE entraineurs SET nom=?,specialite=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entraineur.getNom());
        preparedStatement.setString(2, entraineur.getSpecialite());
        preparedStatement.setInt(3, entraineur.getId());
        preparedStatement.executeUpdate();
    }
    public List<Entraineur> afficherEntraineur() throws SQLException {
        List<Entraineur> entraineursList = new ArrayList<>();
        String query = "SELECT * FROM entraineurs";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Entraineur entraineur = new Entraineur(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("specialite"));
            entraineursList.add(entraineur);

        }
        return entraineursList;
    }
    public void supprimerEntraineur(int id) throws SQLException {
        String query = "DELETE FROM entraineurs WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }
    public Entraineur getEntraineurById(int id) throws SQLException {
        String query = "SELECT * FROM entraineurs WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return new Entraineur(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("specialite"));
        }
        return null;
    }
}