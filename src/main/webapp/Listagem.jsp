

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%

        String listaHTML = request.getParameter("lista");

    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css"> @import url("./styles.css"); </style>
        <title>Pokemon Cadastrados</title>
    </head>
    <body class="listagem">
        
        <br>
        <br>
        <center>
        <table border="0">
            <thead>
                <tr>
                    <th>Nome do Pokemon</th>
                    <th>Tipo</th>
                    <th>Numero na Pokedex</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <hr>
            <tbody>
                <%=listaHTML %>
            </tbody>
        </table>
            <hr>
            <br>
            <a href="index.html">Tela Principal</a>
        </center>
</body>
</html>
