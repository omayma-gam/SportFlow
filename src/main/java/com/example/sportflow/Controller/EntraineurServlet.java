package com.example.sportflow.Controller;
import com.example.sportflow.DAO.EntraineurDAO;
import com.example.sportflow.Model.Entraineur;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/entraineur")
public class EntraineurServlet extends HttpServlet {
    private EntraineurDAO entraineurDAO;

    @Override
    public void init() {

        try {
            entraineurDAO = new EntraineurDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            response.sendRedirect("/entraineur?action=afficher");
            return;
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "ajouter":
                    ajouterEntraineur(request, response);
                    break;
                case "afficherbyID":
                    afficherEntraineurById(request, response);
                    break;
                case "modifier":
                    modifierEntraineur(request, response);
                    break;
                case "supprimer":
                    supprimerEntraineur(request, response);
                    break;
                case "afficher":
                    afficherEntraineur(request, response);
                    break;
                default:
                    response.sendRedirect("/entraineur?action=afficher");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Erreur SQL", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void afficherEntraineurById(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Entraineur entraineurById = entraineurDAO.getEntraineurById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Entraineur/modiffierEntraineur.jsp");
        request.setAttribute("entraineurById", entraineurById);
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Entraineur/ajouterEntraineur.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherEntraineur(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Entraineur> listeEntraineurs = entraineurDAO.afficherEntraineur();
        request.setAttribute("entraineurs", listeEntraineurs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Entraineur/afficherEntraineur.jsp");
        dispatcher.forward(request, response);
    }

    private void supprimerEntraineur(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        entraineurDAO.supprimerEntraineur(id);
        response.sendRedirect("entraineur?action=afficher");
    }

    private void modifierEntraineur(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String specialite = request.getParameter("specialite");
        Entraineur entraineur = new Entraineur(id, nom, specialite);
        entraineurDAO.modifierEntraineur(entraineur);
        response.sendRedirect("entraineur?action=afficher");
    }

    private void ajouterEntraineur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String nom = request.getParameter("nom");
        String specialite = request.getParameter("specialite");
        Entraineur entraineur = new Entraineur(nom,specialite);
        entraineurDAO.ajouterEntraineur(entraineur);
        response.sendRedirect("entraineur?action=afficher");
    }
}
