package Controller;

import DAO.ClienteDAO;
import Model.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeletarClienteController", urlPatterns = {"/deletarCliente"})
public class DeletarClienteController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
            //Estancio meus objetos, cliente e clienteDAO
            ClienteDAO CliDAO = new ClienteDAO();
            Cliente objCli = new Cliente();
            
            //Seto no cliente o ID que vem do FORM
            objCli.setId(Integer.parseInt(request.getParameter("id")));
        
            //chamo a DAO e uso o delete passando o objeto
            CliDAO.deletar(objCli);
     
            //mando para JSP atualizar a página
            response.sendRedirect("listarCliente");  
    }
    
}
