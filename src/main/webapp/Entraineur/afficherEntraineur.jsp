<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.sportflow.Model.Entraineur" %>
<%
    List<Entraineur> entraineurs = (List<Entraineur>) request.getAttribute("entraineurs");
%>
<html>
<head>
    <title>Liste des Entraineurs</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<h2>Liste des Entraineurs</h2>
<a href="entraineur?action=new" class="btn btn-primary mb-3">Ajouter un Entraineur</a>
<table class="table table-bordered">
    <thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Spécialité</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <% for (Entraineur entraineur : entraineurs) { %>
    <tr>
        <td><%= entraineur.getId() %></td>
        <td><%= entraineur.getNom() %></td>
        <td><%= entraineur.getSpecialite() %></td>
        <td>
            <a href="entraineur?action=afficherbyID&id=<%= entraineur.getId() %>" class="btn btn-warning btn-sm">Modifier</a>
            <a href="entraineur?action=supprimer&id=<%= entraineur.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet entraineur?')">Supprimer</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>