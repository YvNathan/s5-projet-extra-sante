<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, itu.s5.model.Patient" %>
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
    List<Patient> patients = (List<Patient>) request.getAttribute("patients");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des patients</title>
</head>
<body>
    <h1>Patients (<%= patients == null ? 0 : patients.size() %>)</h1>

    <p><a href="<%= ctx %>/patients/new">+ Nouveau patient</a></p>

    <table border="1" cellpadding="6" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Date naissance</th>
                <th>Sexe</th>
                <th>Téléphone</th>
                <th>Groupe</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <% if (patients == null || patients.isEmpty()) { %>
            <tr><td colspan="8">Aucun patient trouvé.</td></tr>
        <% } else {
            for (Patient p : patients) { %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= esc(p.getNom()) %></td>
                <td><%= esc(p.getPrenom()) %></td>
                <td><%= esc(p.getDateNaissance()) %></td>
                <td><%= esc(p.getSexe()) %></td>
                <td><%= esc(p.getTelephone()) %></td>
                <td><%= esc(p.getGroupeSanguin()) %></td>
                <td>
                    <a href="<%= ctx %>/patients/<%= p.getId() %>/edit">Modifier</a>
                    <form method="post" action="<%= ctx %>/patients/<%= p.getId() %>/delete" style="display:inline;"
                          onsubmit="return confirm('Supprimer ce patient ?');">
                        <button type="submit">Supprimer</button>
                    </form>
                </td>
            </tr>
        <%  }
        } %>
        </tbody>
    </table>
</body>
</html>