<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter Entraineur</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<h2>Ajouter un nouvel entraineur</h2>
<form action="entraineur?action=ajouter" method="post">
    <div class="mb-3">
        <label for="nom" class="form-label">Nom</label>
        <input type="text" class="form-control" id="nom" name="nom" required>
    </div>
    <div class="mb-3">
        <label for="specialite" class="form-label">Spécialité</label>
        <input type="text" class="form-control" id="specialite" name="specialite" required>
    </div>
    <button type="submit" class="btn btn-primary">Ajouter</button>
    <a href="entraineur?action=afficher" class="btn btn-secondary">Annuler</a>
</form>
</body>
</html>