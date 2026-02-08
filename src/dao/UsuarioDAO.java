package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public static ResultSet login(String username, String senha) throws Exception {
        String sql = "SELECT id, username, perfil FROM usuarios WHERE username=? AND senha=? AND ativo=1";

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, senha);

        return ps.executeQuery();
    }

    public static void criarUsuario(String username, String senha, String perfil) throws Exception {
        String sql = "INSERT INTO usuarios (username, senha, perfil) VALUES (?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, senha);
            ps.setString(3, perfil);

            ps.executeUpdate();
        }
    }
}
