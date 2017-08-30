package Controller;

import DAO.ClienteDAO;
import Model.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListarClienteController", urlPatterns = {"/listarCliente"})
public class ListarClienteController extends HttpServlet {

    @Override //LISTAGEM COMPLETO E ALTERAÇÃO
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //CONSULTAR NORMAL
        if (request.getParameter("id") == null) {

            //Crio uma lista que recebe uma lista que vem do BD de Clientes
            List<Cliente> listCli = new ClienteDAO().consultarTodos();

            //Seto a lista para mostrar na consulta
            request.setAttribute("listaDeClientes", listCli);
            //Dispacho para a JSP exibir a lista de produtos
            request.getRequestDispatcher("pesquisa.jsp").forward(request, response);

        } else { //COMSULTAR POR ID PARA ALTERAR

            //Pego ID pois será botão de alterar nao de listar
            int id = Integer.parseInt(request.getParameter("id"));

            // Produto recebe um OBJETO que retorna da consulta por ID
            Cliente CliUpdate = new ClienteDAO().findById(id);

            //Seto o objeto que veio do banco e dispacho para a tela de cadastro
            request.setAttribute("clienteUpdate", CliUpdate);
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        }

    }

    @Override //LISTAGEM POR NOME E E-MAIL
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeBusca = request.getParameter("nomeBusca");
        String emailBusca = request.getParameter("emailBusca");

        //VERIFICO SE É VAZIO OU SE DIGITARAM NOS DOIS CAMPOS PARA PESQUISAR
        if ((nomeBusca.isEmpty()) && (emailBusca.isEmpty())) {
            //Crio uma lista que recebe uma lista que vem do BD de Clientes
            List<Cliente> listCli = new ClienteDAO().consultarTodos();
            request.setAttribute("listaDeClientes", listCli);
        } else if ((nomeBusca.length() > 0) && (emailBusca.length() > 0)) {
            List<Cliente> listCli = new ClienteDAO().consultarTodos();
            request.setAttribute("listaDeClientes", listCli);
            request.setAttribute("validacaoCadastro", 4);
        }

        //VERIFICO SE NOME TEM CONTEUDO E O EMAIL É VAZIO
        if ((nomeBusca.length() > 0) && (emailBusca.isEmpty())) {
            List<Cliente> listCli = new ClienteDAO().findByNome(nomeBusca);
            
            //SE FOR VAZIA EU MANDO MENSAGEM DE NÃO ENCONTRAMOS REGISTRO
            if (listCli.isEmpty()){
                listCli = new ClienteDAO().consultarTodos();
                request.setAttribute("validacaoCadastro", 5);
                request.setAttribute("validacaoVar", nomeBusca);
            }            
            request.setAttribute("listaDeClientes", listCli);
        } else if ((emailBusca.length() > 0) && (nomeBusca.isEmpty())) {
            //VERIFICO SE EMAIL TEM CONTEUDO E O NOME É VAZIO
            List<Cliente> listCli = new ClienteDAO().findByEmail(emailBusca);
            
            //SE FOR VAZIA EU MANDO MENSAGEM DE NÃO ENCONTRAMOS REGISTRO
            if (listCli.isEmpty()){
                listCli = new ClienteDAO().consultarTodos();
                request.setAttribute("validacaoCadastro", 5);
                request.setAttribute("validacaoVar", emailBusca);
            }
            request.setAttribute("listaDeClientes", listCli);
        }

        //Envio para minha pesquisa    
        request.getRequestDispatcher("pesquisa.jsp").forward(request, response);
    }
}
