

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%
        String acao = request.getParameter("acao");

        String id = request.getParameter("id");
        String nomePokemon = request.getParameter("nomePokemon");
        String tipo = request.getParameter("tipo");
        String numeroPokedex = request.getParameter("numeroPokedex");

        if (id == null) {
            nomePokemon = "";
            tipo = "";
            numeroPokedex = "";
        }
    %>

    <head>
        <style type="text/css"> @import url("./styles.css"); </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulário de Cadastro</title>
    </head>
    <div>
        <body class="formulario">
        <center>
            <br>
            <br>
            <br>               
            <form action="PokemonSrv" method="POST">
                <input type="hidden" name="acao" value="<%=acao%>" />
                <table border="0">
                    <tbody>
                        <tr>
                            <td><input type="hidden" name="id" value="<%=id%>" /></td>
                        </tr>
                        <tr>
                        <tr>
                            <td>Nome do pokémon: </td>
                            <td><input type="text" name="nomePokemon" value="<%=nomePokemon%>" /></td>
                        </tr>
                        <tr>
                            <td>Tipo: </td>
                            <td><input type="text" name="tipo" value="<%=tipo%>" /></td>
                        </tr>
                        <tr>
                            <td>Número na Pokedex: </td>
                            <td><input type="text" name="numeroPokedex" value="<%=numeroPokedex%>" /></td>
                        </tr>
                    </tbody>
                </table>
                <br>
                <input type="submit" value="Enviar" />
                <input type="reset" value="Limpar" />
            </form>

        </center>
    </body>
</div>

</html>
