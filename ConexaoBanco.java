import java.sql.*;

class ConexaoBanco {
  public Connection conexao;

  public ConexaoBanco() {
    try {
      Class.forName("org.sqlite.JDBC"); //Aciona o JDBC....

      //Criar um objeto do tipo Connection
      //ele representará a conexão com o banco de dados
      this.conexao = DriverManager.getConnection("jdbc:sqlite:database/imdb.sqlite");
    } catch(Exception e) {
      System.out.println("Deu ruim!");
      System.out.println(e.getMessage());
    }  
  }
}