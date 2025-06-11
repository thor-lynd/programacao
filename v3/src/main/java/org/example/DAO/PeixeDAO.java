package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeixeDAO {

    private Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/seubanco"; // ajuste seu banco
        String user = "root";
        String password = "sua_senha";
        return DriverManager.getConnection(url, user, password);
    }

    public void inserir(Peixe peixe) throws SQLException {
        String sql = "INSERT INTO peixe (nome, tipo, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, peixe.getNomePeixe());
            ps.setString(2, peixe.getTipoPeixe());
            ps.setInt(3, peixe.getQuantidadePeixe());
            ps.executeUpdate();
        }
    }

    public List<Peixe> listarTodos() throws SQLException {
        List<Peixe> lista = new ArrayList<>();
        String sql = "SELECT nome, tipo, quantidade FROM peixe";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");
                int quantidade = rs.getInt("quantidade");
                lista.add(new Peixe(nome, tipo, quantidade));
            }
        }
        return lista;
    }

    public void atualizar(Peixe peixe, String nomeAntigo) throws SQLException {
        String sql = "UPDATE peixe SET nome = ?, tipo = ?, quantidade = ? WHERE nome = ?";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, peixe.getNomePeixe());
            ps.setString(2, peixe.getTipoPeixe());
            ps.setInt(3, peixe.getQuantidadePeixe());
            ps.setString(4, nomeAntigo);
            ps.executeUpdate();
        }
    }
}
