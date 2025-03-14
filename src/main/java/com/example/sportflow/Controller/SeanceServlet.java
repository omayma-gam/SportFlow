package com.example.sportflow.Controller;

import com.example.sportflow.DAO.SeanceDAO;
import com.example.sportflow.Model.Seance;
import com.example.sportflow.Model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/seance")
public class SeanceServlet extends HttpServlet{




        SeanceDAO seanceDao ;
        @Override
        public void init() throws ServletException {
            try {
                seanceDao = new SeanceDAO();
                System.out.println("SeanceDao initialisé et tables créées !");
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException("Erreur lors de l'initialisation de SeanceDao", e);
            }
            seanceDao.getAllMembres();
            seanceDao.getAllEntraineurs();
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req, resp);


        }


        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<User> membresList = seanceDao.getAllMembres();
            List<User> entraineursList = seanceDao.getAllEntraineurs();

            // Passer les données à la JSP
            req.setAttribute("membresList", membresList);
            req.setAttribute("entraineursList", entraineursList);


            String action = req.getParameter("action");
            switch (action){
                case "newseance":
                    showNewForm(req, resp);
                    break;
                case "create":
                    addSeance(req, resp);
                    break;
                case "listseance":
                    try {
                        listseance(req,resp);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "delete":
                    break;
                default:
                    break;
            }

        }
        private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("seance.jsp");
            dispatcher.forward(request, response);
        }
        private void listseance(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, ServletException, IOException {
            List<Seance> seances = seanceDao.getAllSeances();
            System.out.println("Liste des séances récupérées : " + seances);
            request.setAttribute("seances", seances);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listseance.jsp");
            dispatcher.forward(request, response);
        }

        private void addSeance(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String date = req.getParameter("date");
            int idEntraineur = Integer.parseInt(req.getParameter("entraineur_id"));
            int idMembre = Integer.parseInt(req.getParameter("member_id"));
            Seance seance = new Seance(date,idEntraineur,idMembre);
            seanceDao.addSeance(seance);
            resp.sendRedirect(req.getContextPath() + "/seance?action=listseance");

        }
    }
}
