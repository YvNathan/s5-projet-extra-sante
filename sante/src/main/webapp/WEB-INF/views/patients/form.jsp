<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="itu.s5.model.Patient" %>
<%!
    private String esc(Object v) {
        if (v == null) return "";
        return v.toString()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
%>
<%
    String ctx = request.getContextPath();
    Patient p = (Patient) request.getAttribute("patient");
    boolean isEdit = Boolean.TRUE.equals(request.getAttribute("isEdit"));
    String error = (String) request.getAttribute("error");
    String action = isEdit ? (ctx + "/patients/" + p.getId()) : (ctx + "/patients");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title><%= isEdit ? "Modifier un patient" : "Nouveau patient" %></title>
</head>
<body>
    <h1><%= isEdit ? "Modifier le patient #" + p.getId() : "Nouveau patient" %></h1>

    <% if (error != null) { %>
        <p style="color: red;"><%= esc(error) %></p>
    <% } %>

    <form method="post" action="<%= action %>">
        <p>
            <label>Nom *</label><br>
            <input type="text" name="nom" value="<%= esc(p.getNom()) %>" required>
        </p>
        <p>
            <label>Prénom *</label><br>
            <input type="text" name="prenom" value="<%= esc(p.getPrenom()) %>" required>
        </p>
        <p>
            <label>Date de naissance *</label><br>
            <input type="date" name="dateNaissance" value="<%= esc(p.getDateNaissance()) %>" required>
        </p>
        <p>
            <label>Sexe *</label><br>
            <select name="sexe" required>
                <option value="M" <%= "M".equals(p.getSexe()) ? "selected" : "" %>>Masculin</option>
                <option value="F" <%= "F".equals(p.getSexe()) ? "selected" : "" %>>Féminin</option>
            </select>
        </p>
        <p>
            <label>Téléphone</label><br>
            <input type="text" name="telephone" value="<%= esc(p.getTelephone()) %>">
        </p>
        <p>
            <label>Email</label><br>
            <input type="email" name="email" value="<%= esc(p.getEmail()) %>">
        </p>
        <p>
            <label>Adresse</label><br>
            <textarea name="adresse" rows="3"><%= esc(p.getAdresse()) %></textarea>
        </p>
        <p>
            <label>Groupe sanguin</label><br>
            <select name="groupeSanguin">
                <option value="">-- Non renseigné --</option>
                <% for (String gs : new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}) { %>
                    <option value="<%= gs %>" <%= gs.equals(p.getGroupeSanguin()) ? "selected" : "" %>><%= gs %></option>
                <% } %>
            </select>
        </p>
        <button type="submit"><%= isEdit ? "Enregistrer les modifications" : "Créer" %></button>
        <a href="<%= ctx %>/patients">Annuler</a>
    </form>
</body>
</html>