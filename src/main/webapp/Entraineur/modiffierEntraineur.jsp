<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.sportflow.Model.Entraineur" %>
<%
    Entraineur entraineur = (Entraineur) request.getAttribute("entraineurById");
%>
<html>
<head>
    <title>Modifier Entraineur</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<h2>Modifier Entraineur</h2>
<form action="entraineur?action=modifier" method="post">
    <input type="hidden" name="id" value="<%= entraineur.getId() %>">
    <div class="mb-3">
        <label for="nom" class="form-label">Nom</label>
        <input type="text" class="form-control" id="nom" name="nom" value="<%= entraineur.getNom() %>" required>
    </div>
    <div class="mb-3">
        <label for="specialite" class="form-label">Spécialité</label>
        <input type="text" class="form-control" id="specialite" name="specialite" value="<%= entraineur.getSpecialite() %>" required>
    </div>
    <button type="submit" class="btn btn-success">Modifier</button>
    <a href="entraineur?action=afficher" class="btn btn-secondary">Annuler</a>
</form>
</body>
</html>