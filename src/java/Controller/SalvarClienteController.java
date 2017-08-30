package Controller;

import DAO.ClienteDAO;
import Model.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SalvarClienteController", urlPatterns = {"/salvarCliente"})
public class SalvarClienteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ClienteDAO CliDAO = new ClienteDAO();        
                
        if (request.getParameter("id").equals("")) {//FARÁ O CADASTRO NORMAL
            //Estancio meus objetos, cliente e clienteDAO
            CliDAO = new ClienteDAO();
            Cliente objCli = new Cliente();

            //Seto no objeto Cliente
            objCli.setNome(request.getParameter("nome"));
            
            //VERIFICO SE JÁ EXISTE E-MAIL
            if (CliDAO.verificarEmail(request.getParameter("email"))){
                    request.setAttribute("validacaoCadastro", 6);
                    request.getRequestDispatcher("cadastro.jsp").forward(request, response);
            }else{
                //seto normalmente
                objCli.setEmail(request.getParameter("email"));
            }
            
            objCli.setEndereco(request.getParameter("endereco"));
            objCli.setTelefone(request.getParameter("tell"));
            objCli.setSexo(request.getParameter("sexo").charAt(0));

            
            //Valido o campo de newsLetter para ver se ticou ou não
            if (request.getParameter("newsLetter") == null) {
                objCli.setNewsLetter('N');
            } else{
                objCli.setNewsLetter(request.getParameter("newsLetter").charAt(0));
            }

            //Cadastro o objeto cliente completo e comparo se cadastrou ou não para tratar o erro
            if (CliDAO.cadastrar(objCli)) {
                request.setAttribute("validacaoCadastro", 1);
            }else{
                request.setAttribute("validacaoCadastro", 3);
            }
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);            
        } else {
            //FARÁ A ATUALIZAÇÃO POIS O ID DO TIPO HIDDEN TEM VALOR
            //Estancio meus objetos, cliente e clienteDAO
            CliDAO = new ClienteDAO();
            Cliente objCli = new Cliente();

            //Seto no produto o que vem do FORM
            objCli.setId(Integer.parseInt(request.getParameter("id")));
            objCli.setNome(request.getParameter("nome"));            
            objCli.setEmail(request.getParameter("email"));
            objCli.setEndereco(request.getParameter("endereco"));
            objCli.setTelefone(request.getParameter("tell"));
            objCli.setSexo(request.getParameter("sexo").charAt(0));
            
            //Valido o campo de newsLetter
            //SE FOR NULO É PQ VEIO DO ALTERAR, ENTÃO APLICA O 'N' CASO CONTRÁRIO SERÁ O 'S' E ALTERA COM 'S'
            if (request.getParameter("newsLetter") == null) {
                objCli.setNewsLetter('N');
            } else if(request.getParameter("newsLetter").equals("S")){
                objCli.setNewsLetter(request.getParameter("newsLetter").charAt(0));
            }

            
            //atualizo o objeto cliente completo e comparo se atualizou ou não para tratar o erro
            if (CliDAO.atualizar(objCli)) {
                request.setAttribute("validacaoCadastro", 2);
            }else{
                request.setAttribute("validacaoCadastro", 3);
            }
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        }

    }

}
