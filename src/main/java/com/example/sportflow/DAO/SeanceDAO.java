package com.example.sportflow.DAO;

import com.example.sportflow.DBC.ConnectionDb;
import com.example.sportflow.Model.Seance;
import com.example.sportflow.Model.User;
import jakarta.servlet.http.HttpServlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAO extends HttpServlet {

    private Connection connection;
    public SeanceDAO() throws SQLException, ClassNotFoundException {
        connection = ConnectionDb.getConnection();
    }
    public List<User> getAllMembres() {
        List<User> membresList = new ArrayList<>();
        String sql = "SELECT id_Membre, nom FROM user WHERE role = 'member'";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User membre = new User();
                membre.setId(resultSet.getInt("user_id"));
                membre.setName(resultSet.getString("nom"));
                membresList.add(membre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return membresList;
    }

    // Vous pouvez également créer une méthode similaire pour récupérer les entraîneurs.
    public List<User> getAllEntraineurs() {
        List<User> entraineursList = new ArrayList<>();
        String sql = "SELECT entraineur_id, nom FROM user WHERE role = 'entraineur'";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User entraineur = new User();
                entraineur.setId(resultSet.getInt("user_id"));
                entraineur.setName(resultSet.getString("nom"));
                entraineursList.add(entraineur);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entraineursList;
    }

    public void addSeance(Seance seance) {
        String sql = "INSERT INTO seance (dateHeure, member_id, entraineur_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, seance.getDateTime());
            preparedStatement.setInt(3, seance.getIdMembre());
            preparedStatement.setInt(2, seance.getIdEntraineur());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public List<Seance> getAllSeances() {
        List<Seance> seanceList = new ArrayList<>();
        String SQL = "SELECT s.idSeance, s.dateHeure, m.nom AS membreNom, e.nom AS entraineurNom "
                + "FROM seance s "
                + "JOIN user m ON s.member_id = m.user_id "
                + "JOIN user e ON s.entraineur_id = e.user_id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Seance seance = new Seance();
                seance.setIdSeance(resultSet.getInt("idSeance"));
                seance.setDateTime(resultSet.getString("dateHeure"));

                seanceList.add(seance);
            }
            System.out.println("Séances récupérées : " + seanceList);
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
            e.printStackTrace();
        }
        return seanceList;
    }


}
