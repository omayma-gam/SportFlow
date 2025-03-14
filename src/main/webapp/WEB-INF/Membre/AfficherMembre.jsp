<%@ page contentType="text/html;charset=UTF-8" language="" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.sportflow.Model.Membre" %>

<%
    List<Membre> membreList = (List<Membre>) request.getAttribute("membreList");
%>

<html>
<head>
    <title>Liste des Membres</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
            padding: 0;
        }
        h2 {
            color: #333;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .actions a {
            margin-right: 10px;
            color: #007bff;
        }
        .actions a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h2>Liste des Membres</h2>
<a href="membres?action=new">Ajouter un Nouveau Membre</a>
<br><br>
<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Date de Naissance</th>
        <th>Sport Pratiqué</th>
        <th>Actions</th>
    </tr>
    <% for (Membre membre : membreList) { %>
    <tr>
        <td><%= membre.getId() %></td>
        <td><%= membre.getNom() %></td>
        <td><%= membre.getNaissance() %></td>
        <td><%= membre.getSportPratique() %></td>
        <td class="actions">
            <a href="membres?action=afficherbyID&id=<%= membre.getId() %>">Modifier</a>
            |
            <a href="membres?action=supprimer&id=<%= membre.getId() %>" onclick="return confirm('Voulez-vous vraiment supprimer ce membre ?');">Supprimer</a>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>