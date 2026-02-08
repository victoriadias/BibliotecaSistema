package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClienteDAO {

    public static void inserir(String nome, String email, String telefone,
                               String livro, int quantidade, double valor) throws Exception {

        String sql = """
            INSERT INTO clientes (nome, email, telefone, livro, quantidade, valor, status_pagamento)
            VALUES (?, ?, ?, ?, ?, ?, 'PENDENTE')
        """;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, telefone);
            ps.setString(4, livro);
            ps.setInt(5, quantidade);
            ps.setDouble(6, valor);

            ps.executeUpdate();
        }
    }
}
