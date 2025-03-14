<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.sportflow.Model.Membre" %>

<%
    Membre membre = (Membre) request.getAttribute("membreById");
%>

<html>
<head>
    <title>Modifier un Membre</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #555;
        }
        input[type="text"], input[type="date"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .error-message {
            color: #dc3545;
            text-align: center;
            margin-bottom: 15px;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Modifier un Membre</h2>

    <% if (membre != null) { %>
    <form action="membres?action=modifier" method="post">
        <input type="hidden" name="id" value="<%= membre.getId() %>">

        <label>Nom :</label>
        <input type="text" name="nom" value="<%= membre.getNom() %>" required><br>

        <label>Date de naissance (yyyy-MM-dd) :</label>
        <input type="date" name="naissance" value="<%= membre.getNaissance() %>" required><br>

        <label>Sport Pratiqué :</label>
        <input type="text" name="sportpratique" value="<%= membre.getSportPratique() %>" required><br>

        <input type="submit" value="Modifier">
    </form>
    <% } else { %>
    <p class="error-message">⚠ Erreur : Aucun membre trouvé avec cet ID.</p>
    <a href="membres?action=afficher">Retour à la liste des membres</a>
    <% } %>
</div>
</body>
</html>