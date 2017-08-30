package DAO;

import Model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Conexao conexao;

    public ClienteDAO() {
        this.conexao = new Conexao();
    }

    //CADASTRAR
    public boolean cadastrar(Cliente cli) {
        // ELE FAZ MAS PODE DAR ERRO
        try {
            //Se conectar ele cadastra
            if (this.conexao.conectar()) {

                //Preparo minha QUERY
                String sql = "insert into clientes (nome,email,endereco,telefone,sexo,newsLetter) values (?,?,?,?,?,?);";
                PreparedStatement comando = this.conexao.getConnection().prepareStatement(sql);

                //Seto os valores da Query com oq temos no obj 'Produto'
                comando.setString(1, cli.getNome());
                comando.setString(2, cli.getEmail());
                comando.setString(3, cli.getEndereco());
                comando.setString(4, cli.getTelefone());
                comando.setString(5, String.valueOf(cli.getSexo())); //Converto CHAR para STRING
                comando.setString(6, String.valueOf(cli.getNewsLetter()));

                comando.execute();//Executo a minha query
                comando.close(); //fecha o comando
                this.conexao.getConnection().close(); //fecha a conexão com o banco
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Erro:" + e);
            return false;
        }
        return false;
    }

    //DELETAR PRODUTO
    public void deletar(Cliente cli) {
        try {
            //Se conectar ele deleta
            if (this.conexao.conectar()) {

                //Preparo minha QUERY
                String sql = "delete from clientes where id = ?;";
                PreparedStatement comando = this.conexao.getConnection().prepareStatement(sql);

                //Seto os valores da Query com oq temos no obj 'cliente'
                comando.setInt(1, (int) cli.getId());

                comando.execute();//Executo a minha query
                comando.close(); //fecha o comando
                this.conexao.getConnection().close(); //fecha a conexão com o banco
            }

        } catch (SQLException e) {
            System.out.println("Erro:" + e);
        }
    }

    //ATUALIZAR
    public boolean atualizar(Cliente cli) {
        // ELE FAZ MAS PODE DAR ERRO
        try {
            //Se conectar ele atualiza
            if (this.conexao.conectar()) {

                //Preparo minha QUERY
                String sql = "update clientes set nome = ?, email = ?, endereco = ?, telefone = ?, sexo = ?, newsLetter = ? where id = ?;";
                PreparedStatement comando = this.conexao.getConnection().prepareStatement(sql);

                //Seto os valores da Query com oq temos no obj 'Cliente'
                comando.setString(1, cli.getNome());
                comando.setString(2, cli.getEmail());
                comando.setString(3, cli.getEndereco());
                comando.setString(4, cli.getTelefone());
                comando.setString(5, String.valueOf(cli.getSexo()));
                comando.setString(6, String.valueOf(cli.getNewsLetter()));
                comando.setInt(7, cli.getId());

                comando.execute();//Executo a minha query
                comando.close(); //fecha o comando
                this.conexao.getConnection().close(); //fecha a conexão com o banco
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro:" + e);
            return false;
        }
        return false;
    }

//    //CONSULTAR TODOS
    public boolean verificarEmail(String email) {
        // ELE FAZ MAS PODE DAR ERRO
        try {
            //Se conectar ele verifica e-mail
            if (this.conexao.conectar()) {

                //Preparo minha QUERY
                String sql = "select id, nome, email, telefone from clientes where email = ?;";
                PreparedStatement comando = this.conexao.getConnection().prepareStatement(sql);

                //Preparo minha QUERY
                comando.setString(1, email);

                //Para executar uma QUERY de seleção, devo usar a classe 'ResultSet' e comando de 'executeQuery'
                ResultSet resultado = comando.executeQuery();

                // Crio lista de Clientes, com nome de 'todos produtos'
                List<Cliente> todosClientes = new ArrayList<Cliente>();

                //Se existir qualquer e-mail ele retornar verdadeiro
                if (resultado.next()) {
                    return true;
                }

                comando.close(); //fecha o comando
                this.conexao.getConnection().close(); //fecha a conexão com o banco
            }
        } catch (SQLException e) {
            System.out.println("Erro:" + e);
        }
        return false;
    }

    //CONSULTAR TODOS
    public List<Cliente> consultarTodos() {
        // ELE FAZ MAS PODE DAR ERRO
        try {
            //Se conectar ele consulta todos
            if (this.conexao.conectar()) {

                //Preparo minha QUERY
                String sql = "select id, nome, email, telefone from clientes;";
                PreparedStatement comando = this.conexao.getConnection().prepareStatement(sql);

                //Para executar uma QUERY de seleção, devo usar a classe 'ResultSet' e comando de 'executeQuery'
                ResultSet resultado = comando.executeQuery();

                // Crio lista de Clientes, com nome de 'todos produtos'
                List<Cliente> todosClientes = new ArrayList<Cliente>();

                //Enquanto houver próxima linha, ele fará o processo dentro do while
                while (resultado.next()) {
                    Cliente objCli = new Cliente();

                    //Seto os atributo do objeto com dados que veio do BDs
                    objCli.setId(resultado.getInt("id"));
                    objCli.setNome(resultado.getString("nome"));
                    objCli.setEmail(resultado.getString("email"));
                    objCli.setTelefone(resultado.getString("telefone"));

                    //Adiciono objCli na lista de clientes
                    todosClientes.add(objCli);
                }

                comando.close(); //fecha o comando
                this.conexao.getConnection().close(); //fecha a conexão com o banco
                return todosClientes; // Retorna um ARRAY com os OBJ que foram consultados no banco
            }
        } catch (SQLException e) {
            System.out.println("Erro:" + e);
        }
        return null;//Se não der certo volta 'nulo'
    }
    //CONSULTAR POR EMAIL

    public List<Cliente> findByEmail(String email) {
        // ELE FAZ MAS PODE DAR ERRO
        try {
            //Se conectar ele consulta por email
            if (this.conexao.conectar()) {

                String sql = "SELECT id, nome, email, telefone FROM CLIENTES WHERE upper(email) like upper(?);";
                PreparedStatement comando = this.conexao.getConnection().prepareStatement(sql);

                comando.setString(1, email + '%');

                //Para executar uma QUERY de seleção, devo usar a classe 'ResultSet' e comando de 'executeQuery'
                ResultSet resultado = comando.executeQuery();

                // Crio lista de Clientes, com nome de 'todos produtos'
                List<Cliente> todosClientes = new ArrayList<Cliente>();

                //Enquanto houver próxima linha, ele fará o processo dentro do while
                while (resultado.next()) {
                    Cliente objCli = new Cliente();

                    //Seto os atributo do objeto com dados que veio do BDs
                    objCli.setId(resultado.getInt("id"));
                    objCli.setNome(resultado.getString("nome"));
                    objCli.setEmail(resultado.getString("email"));
                    objCli.setTelefone(resultado.getString("telefone"));

                    //Adiciono objCli na lista de clientes
                    todosClientes.add(objCli);
                }

                comando.close(); //fecha o comando
                this.conexao.getConnection().close(); //fecha a conexão com o banco
                return todosClientes; // Retorna um ARRAY com os OBJ que foram consultados no banco
            }
        } catch (SQLException e) {
            System.out.println("Erro:" + e);
        }
        return null;
    }

    //CONSULTAR POR NOME
    public List<Cliente> findByNome(String nome) {
        // ELE FAZ MAS PODE DAR ERRO
        try {
            //Se conectar ele consulta por nome
            if (this.conexao.conectar()) {

                String sql = "SELECT id, nome, email, telefone FROM CLIENTES WHERE upper(nome) like upper(?);";
                PreparedStatement comando = this.conexao.getConnection().prepareStatement(sql);

                comando.setString(1, nome + '%');

                //Para executar uma QUERY de seleção, devo usar a classe 'ResultSet' e comando de 'executeQuery'
                ResultSet resultado = comando.executeQuery();

                // Crio lista de Clientes, com nome de 'todos produtos'
                List<Cliente> todosClientes = new ArrayList<Cliente>();

                //Enquanto houver próxima linha, ele fará o processo dentro do while
                while (resultado.next()) {
                    Cliente objCli = new Cliente();

                    //Seto os atributo do objeto com dados que veio do BDs
                    objCli.setId(resultado.getInt("id"));
                    objCli.setNome(resultado.getString("nome"));
                    objCli.setEmail(resultado.getString("email"));
                    objCli.setTelefone(resultado.getString("telefone"));

                    //Adiciono objCli na lista de clientes
                    todosClientes.add(objCli);
                }

                comando.close(); //fecha o comando
                this.conexao.getConnection().close(); //fecha a conexão com o banco
                return todosClientes; // Retorna um ARRAY com os OBJ que foram consultados no banco
            }
        } catch (SQLException e) {
            System.out.println("Erro:" + e);
        }
        return null;
    }

    public Cliente findById(int id) {
        Cliente objCli = null; //Declaro meu cliente
        try {
            if (this.conexao.conectar()) {

                String sql = "select * from clientes where id = ?;";
                PreparedStatement comando = this.conexao.getConnection().prepareStatement(sql);

                comando.setInt(1, id);

                //Para executar uma QUERY de seleção, devo usar a classe 'ResultSet' e comando de 'executeQuery'
                ResultSet resultado = comando.executeQuery();

                if (resultado.next()) {
                    objCli = new Cliente();

                    //Seto os valores do banco no meu CLIENTE
                    objCli.setId(resultado.getInt("id"));
                    objCli.setNome(resultado.getString("nome"));
                    objCli.setEmail(resultado.getString("email"));
                    objCli.setEndereco(resultado.getString("endereco"));
                    objCli.setTelefone(resultado.getString("telefone"));
                    objCli.setSexo(resultado.getString("sexo").charAt(0));
                    objCli.setNewsLetter(resultado.getString("newsLetter").charAt(0));
                }
                comando.close(); //fecha o comando
                this.conexao.getConnection().close(); //fecha a conexão com o banco
                return objCli; // Retorna um ARRAY com os OBJ que foram consultados no banco
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
}
