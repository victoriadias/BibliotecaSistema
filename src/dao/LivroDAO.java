package dao;

import java.sql.*;

public class LivroDAO {

    public static void inserir(String titulo, String genero, int quantidade) throws Exception {
        String sql = "INSERT INTO livros (titulo, genero, quantidade) VALUES (?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, titulo);
            ps.setString(2, genero);
            ps.setInt(3, quantidade);
            ps.executeUpdate();
        }
    }

    public static void atualizarQuantidade(int id, int novaQtd) throws Exception {
        String sql = "UPDATE livros SET quantidade=? WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, novaQtd);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public static void excluirPorId(int id) throws Exception {
        String sql = "DELETE FROM livros WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static ResultSet listar() throws Exception {
        String sql = "SELECT id, titulo, genero, quantidade FROM livros ORDER BY id DESC";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
    
    public static ResultSet buscar(String titulo, String genero, Integer qtdMin) throws Exception {
    StringBuilder sql = new StringBuilder(
        "SELECT id, titulo, genero, quantidade FROM livros WHERE 1=1"
    );

    boolean filtraTitulo = titulo != null && !titulo.trim().isEmpty();
    boolean filtraGenero = genero != null && !genero.trim().isEmpty();
    boolean filtraQtd = qtdMin != null;

    if (filtraTitulo) sql.append(" AND titulo LIKE ?");
    if (filtraGenero) sql.append(" AND genero LIKE ?");
    if (filtraQtd)    sql.append(" AND quantidade = ?");

    sql.append(" ORDER BY id DESC");

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement ps = con.prepareStatement(sql.toString());

    int i = 1;
    if (filtraTitulo) ps.setString(i++, "%" + titulo.trim() + "%");
    if (filtraGenero) ps.setString(i++, "%" + genero.trim() + "%");
    if (filtraQtd)    ps.setInt(i++, qtdMin);

    return ps.executeQuery();
}
}
