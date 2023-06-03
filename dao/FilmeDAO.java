import java.sql.*;
import java.util.ArrayList;

class FilmeDAO {

  public Connection conexao;

  public FilmeDAO() {
    ConexaoBanco conexaoBanco = new ConexaoBanco();
    this.conexao = conexaoBanco.conexao;
  }

  // getAll() ao chamar, ele acessar o banco e retorna uma lista
  // de objetos do tipo Filme carregada!
  public ArrayList<Filme> getAll() {
    ArrayList<Filme> filmeLista = new ArrayList<Filme>();

    try {
      // Criar um objeto do tipo Statement e guardar na variável 'comando'
      Statement comando = this.conexao.createStatement();

      // Comando SQL para listar os filmes no database imdb
      String sql = "SELECT id, titulo, ano FROM filme";

      // Vou criar um objeto do tipo ResultSet a partir da consulta no banco
      ResultSet consulta = comando.executeQuery(sql);

      // Enquanto eu estiver acessando .next() e ele retornando true... faça o que
      // estiver dentro do while
      while (consulta.next() == true) {
        int id = consulta.getInt("id");
        String titulo = consulta.getString("titulo");
        short ano = consulta.getShort("ano");

        Filme filme = new Filme(id, titulo, ano);
        filmeLista.add(filme);
      }
    } catch (Exception e) {
      System.out.println("Erro na consulta: " + e.getMessage());
    }

    return filmeLista;
  }

  // add() : Recebe um objeto do tipo Filme como parâmetro e cadastra no banco de
  // dados....
  public void add(Filme filme) {
    try {
      // Comando de INSERT no banco
      String sql = "INSERT INTO filme (titulo, ano) VALUES (?, ?)";
      PreparedStatement insert = this.conexao.prepareStatement(sql);
      insert.setString(1, filme.getTitulo());
      insert.setShort(2, filme.getAno());
      insert.execute();
    } catch (Exception e) {
      System.out.println("Erro na inclusão: " + e.getMessage());
    }

  }

  // getById()
  public Filme getById(int id) {

    Filme filme = new Filme();

    try {
      String sql = "SELECT id, titulo, ano FROM filme WHERE id = ?";
      PreparedStatement consulta = this.conexao.prepareStatement(sql);
      consulta.setInt(1, id);

      // Vou criar um objeto do tipo ResultSet a partir da consulta no banco
      ResultSet retorno = consulta.executeQuery();

      // Enquanto eu estiver acessando .next() e ele retornando true... faça o que
      // estiver dentro do while
      if (retorno.next() == true) {
        String titulo = retorno.getString("titulo");
        short ano = retorno.getShort("ano");

        filme = new Filme(id, titulo, ano);
      }
    } catch (Exception e) {
      System.out.println("Erro na consulta: " + e.getMessage());
    }

    return filme;
  }

  // update() atualizar o filme passado como parâmetro...
  public void update(Filme filme) {
    try {
      // Comando de UPDATE no banco
      String sql = "UPDATE filme SET titulo=?, ano=? WHERE id=?";
      PreparedStatement update = this.conexao.prepareStatement(sql);
      update.setString(1, filme.getTitulo());
      update.setShort(2, filme.getAno());
      update.setInt(3, filme.getId());
      update.execute();
    } catch (Exception e) {
      System.out.println("Erro na alteração: " + e.getMessage());
    }
  }

}