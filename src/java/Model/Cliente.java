package Model;

public class Cliente {

    //Atributos
    private int id;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private char sexo;
    private char newsLetter;

    // CONSTRUTOR COM PARAMETROS
    public Cliente() {
    }

    //Get's e Set's
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public char getNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(char newsLetter) {
        this.newsLetter = newsLetter;
    }

    //EQUALS compara para ser usado em metodos de DELETAR etc.
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Cliente other = (Cliente) obj;
//        if (this.getId() != other.getId()) {
//            return false;
//        }
//        return true;
//    }
  
}