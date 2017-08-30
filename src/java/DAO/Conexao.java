package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    //Atributos
    private String servidor = "localhost";
    private String banco = "tetra";
    private String usuario = "root";
    private String senha = "root";
    private Connection conexao;

    public Conexao() {
    } //Construtor

    // Método de conexão
    public boolean conectar() {
        try {
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.conexao = DriverManager.getConnection("jdbc:mysql://" + this.servidor + "/" + this.banco, this.usuario, this.senha);
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Método para utilizar nas classes
    public Connection getConnection() {
        return conexao;
    }
}
