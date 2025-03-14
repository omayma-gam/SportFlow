package com.example.sportflow.Controller;

import com.example.sportflow.DAO.EntraineurDAO;
import com.example.sportflow.DAO.MemberDAO;
import com.example.sportflow.DAO.UserDAO;
import com.example.sportflow.Model.Entraineur;
import com.example.sportflow.Model.Membre;
import com.example.sportflow.Model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/membres")
public class UserServlet extends HttpServlet {

        private MemberDAO memberDAO;

        @Override
        public void init() {
            try {
                memberDAO = new MemberDAO();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException("Error initializing MembreDAO", e);
            }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");

            // Ensure action is not null, set to default if it is
            if (action == null || action.isEmpty()) {
                action = "default";  // Or any other default value based on your logic
            }

            try {
                switch (action) {
                    case "new":
                        showForm(request, response);
                        break;
                    case "ajouter":
                        ajouterMember(request, response);
                        break;
                    case "modifier":
                        modifieMember(request, response);
                        break;
                    case "supprimer":
                        supprimeMember(request, response);
                        break;
                    case "afficher":
                        afficherMember(request, response);
                        break;
                    case "afficherbyID":
                        afficherByIdMember(request, response);
                        break;
                    default:
                        response.sendRedirect("/membres?action=afficher");
                        break;
                }
            }catch(SQLException e){
                throw new ServletException("Erreur SQL", e);
            }

        }

        private void afficherByIdMember(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            Membre membreById = memberDAO.getMembreById(id);
            request.setAttribute("membreById", membreById);
            request.getRequestDispatcher("/WEB-INF/Membre/ModifierMembre.jsp").forward(request, response);
        }

        private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/WEB-INF/Membre/AjouterMembre.jsp").forward(request, response);
        }

        private void afficherMember(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
            List<Membre> membreList = memberDAO.afficherMembre();
            request.setAttribute("membreList", membreList);
            request.getRequestDispatcher("/WEB-INF/Membre/AfficherMembre.jsp").forward(request, response);
        }

        private void supprimeMember(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            memberDAO.supprimerMembre(id);
            response.sendRedirect("membres?action=afficher");
        }

        private void modifieMember(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            Date naissance = Date.valueOf(request.getParameter("naissance"));        String sportpratique = request.getParameter("sportpratique");
            Membre membre = new Membre(id, nom, naissance, sportpratique);
            memberDAO.modifierMembre(membre);
            response.sendRedirect("membres?action=afficher");

        }

        private void ajouterMember(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
            String nom = request.getParameter("nom");
            Date naissance = Date.valueOf(request.getParameter("naissance"));
            String sportpratique = request.getParameter("sportpratique");
            Membre membre = new Membre(nom,naissance,sportpratique);
            memberDAO.ajouterMembre(membre);
            response.sendRedirect("membres?action=afficher");

        }
    }


