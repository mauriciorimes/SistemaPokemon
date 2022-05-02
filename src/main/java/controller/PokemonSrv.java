package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pokemon;
import model.dao.DaoFactory;
import model.dao.InterfaceDao;
import model.dao.PokemonDaoJpa;

/**
 *
 * @author mauri
 */
public class PokemonSrv extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {

            String acao = request.getParameter("acao");

            String id = request.getParameter("id");
            String nomePokemon = request.getParameter("nomePokemon");
            String tipo = request.getParameter("tipo");
            String numeroPokedex = request.getParameter("numeroPokedex");

            System.out.println("acao = " + acao);

            InterfaceDao dao = DaoFactory.novoPokemonDAO();
            Pokemon p = null;
            RequestDispatcher rd;

            switch (acao) {

                case "inclusao":
                    p = new Pokemon(nomePokemon, tipo, numeroPokedex);
                    try {
                        dao.incluir(p);
                    } catch (Exception ex) {
                        Logger.getLogger(PokemonSrv.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem());
                    rd.forward(request, response);

                    break;

                case "pre-edicao":
                    p = (Pokemon) dao.pesquisarPorId(Integer.parseInt(id));
                    rd = request.getRequestDispatcher("Formulario.jsp?acao=edicao"
                            + "&id=" + p.getId()
                            + "&nomePokemon=" + p.getNomePokemon()
                            + "&tipo=" + p.getTipo()
                            + "&numeroPokedex=" + p.getNumeroPokedex());
                    rd.forward(request, response);
                    break;

                case "edicao":
                    p = new Pokemon(nomePokemon, tipo, numeroPokedex);
                    p.setId(Integer.parseInt(id));
                    try {
                        dao.editar(p);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem());
                    rd.forward(request, response);
                    break;

                case "exclusao":
                    try {
                    p = new Pokemon();
                    p.setId(Integer.parseInt(id));
                    dao.excluir(p);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem());
                rd.forward(request, response);
                break;

                case "listagem":
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem());
                    rd.forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PokemonSrv.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String listagem() {
        InterfaceDao dao = new PokemonDaoJpa();
        List<Pokemon> lista = null;
        try {
            lista = dao.listar();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        String listaHTML = "";
        for (Pokemon pokemon : lista) {
            listaHTML = listaHTML
                    + "<tr>"
                    + "<td>" + pokemon.getNomePokemon() + "</td>"
                    + "<td>" + pokemon.getTipo() + "</td>"
                    + "<td>" + pokemon.getNumeroPokedex() + "</td>"
                    + "<td><form action=PokemonSrv?acao=pre-edicao method='POST'>"
                    + "<input type='hidden' name='id' value="
                    + pokemon.getId() + "><input type='submit' value=editar>"
                    + "</form></td>"
                    + "<form action=PokemonSrv?acao=exclusao method='POST'>"
                    + "<td><input type='hidden' name='id' value="
                    + pokemon.getId() + "><input type='submit' value=excluir>"
                    + "</form></td>"
                    + "</tr>";
        }
        return listaHTML;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
