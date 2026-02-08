package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsultaDAO {

    public static ResultSet buscarClientes(String termo) throws Exception {
        String sql = """
            SELECT id, nome, email, telefone, livro, quantidade, valor, status_pagamento
            FROM clientes
            WHERE nome LIKE ?
            ORDER BY id DESC
        """;

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + termo + "%");
        return ps.executeQuery();
    }

    public static void marcarComoPago(int idCliente) throws Exception {
        String sql = "UPDATE clientes SET status_pagamento='PAGO' WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.executeUpdate();
        }
    }
}
