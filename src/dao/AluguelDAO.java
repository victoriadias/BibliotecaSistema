package dao;

import java.sql.*;

public class AluguelDAO {

    public static int buscarLivroIdPorTitulo(Connection con, String titulo) throws Exception {
        String sql = "SELECT id FROM livros WHERE titulo = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, titulo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
                return -1;
            }
        }
    }

    public static int buscarQtdLivro(Connection con, int livroId) throws Exception {
        String sql = "SELECT quantidade FROM livros WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, livroId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt("quantidade");
            }
        }
    }

    public static void baixarEstoque(Connection con, int livroId, int qtd) throws Exception {
        String sql = "UPDATE livros SET quantidade = quantidade - ? WHERE id = ? AND quantidade >= ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qtd);
            ps.setInt(2, livroId);
            ps.setInt(3, qtd);
            int afetadas = ps.executeUpdate();
            if (afetadas == 0) throw new Exception("Estoque insuficiente.");
        }
    }

    public static void devolverEstoque(Connection con, int livroId, int qtd) throws Exception {
        String sql = "UPDATE livros SET quantidade = quantidade + ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qtd);
            ps.setInt(2, livroId);
            ps.executeUpdate();
        }
    }

    public static int inserirCliente(Connection con, String nome, String email, String telefone,
                                    String livroTitulo, int quantidade, double valor) throws Exception {
        String sql = """
            INSERT INTO clientes (nome, email, telefone, livro, quantidade, valor, status_pagamento)
            VALUES (?, ?, ?, ?, ?, ?, 'PENDENTE')
        """;
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, telefone);
            ps.setString(4, livroTitulo);
            ps.setInt(5, quantidade);
            ps.setDouble(6, valor);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
                return keys.getInt(1); // id do cliente
            }
        }
    }

    public static void inserirAluguel(Connection con, int clienteId, int livroId, int qtd) throws Exception {
        String sql = "INSERT INTO alugueis (cliente_id, livro_id, quantidade, status) VALUES (?, ?, ?, 'ALUGADO')";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            ps.setInt(2, livroId);
            ps.setInt(3, qtd);
            ps.executeUpdate();
        }
    }

    // Operação completa: cadastrar cliente + baixar estoque + criar aluguel
    public static void cadastrarClienteComAluguel(String nome, String email, String telefone,
                                                 String livroTitulo, int qtd, double valor) throws Exception {

        try (Connection con = ConnectionFactory.getConnection()) {
            con.setAutoCommit(false);

            try {
                int livroId = buscarLivroIdPorTitulo(con, livroTitulo);
                if (livroId == -1) throw new Exception("Livro não encontrado no cadastro de livros.");

                // baixa estoque (com trava no SQL)
                baixarEstoque(con, livroId, qtd);

                // cria cliente pendente
                int clienteId = inserirCliente(con, nome, email, telefone, livroTitulo, qtd, valor);

                // cria aluguel
                inserirAluguel(con, clienteId, livroId, qtd);

                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    // Quando clicar "Pago": marcar pago + devolver estoque + marcar aluguel devolvido
    public static void marcarPagoEDevolver(int clienteId) throws Exception {
        try (Connection con = ConnectionFactory.getConnection()) {
            con.setAutoCommit(false);

            try {
                // achar aluguel ALUGADO desse cliente
                int livroId = -1;
                int qtd = 0;

                String q1 = "SELECT id, livro_id, quantidade FROM alugueis WHERE cliente_id=? AND status='ALUGADO' ORDER BY id DESC LIMIT 1";
                int aluguelId;
                try (PreparedStatement ps = con.prepareStatement(q1)) {
                    ps.setInt(1, clienteId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) throw new Exception("Nenhum aluguel ALUGADO encontrado para este cliente.");
                        aluguelId = rs.getInt("id");
                        livroId = rs.getInt("livro_id");
                        qtd = rs.getInt("quantidade");
                    }
                }

                // marcar cliente como PAGO
                try (PreparedStatement ps = con.prepareStatement("UPDATE clientes SET status_pagamento='PAGO' WHERE id=?")) {
                    ps.setInt(1, clienteId);
                    ps.executeUpdate();
                }

                // devolver estoque
                devolverEstoque(con, livroId, qtd);

                // marcar aluguel devolvido
                try (PreparedStatement ps = con.prepareStatement("UPDATE alugueis SET status='DEVOLVIDO', devolvido_em=NOW() WHERE id=?")) {
                    ps.setInt(1, aluguelId);
                    ps.executeUpdate();
                }

                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }
}
