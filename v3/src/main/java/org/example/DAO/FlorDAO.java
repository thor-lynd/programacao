package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlorDAO {

    private Connection conectar() throws SQLException {
        // Ajuste a URL, usuário e senha conforme seu banco de dados
        String url = "jdbc:mysql://localhost:3306/seubanco";
        String user = "root";
        String password = "sua_senha";
        return DriverManager.getConnection(url, user, password);
    }

    public void inserir(Flor flor) throws SQLException {
        String sql = "INSERT INTO flor (nome, cor, altura) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, flor.getNomeFlor());
            ps.setString(2, flor.getCorFlor());
            ps.setInt(3, flor.getAlturaFlor());
            ps.executeUpdate();
        }
    }

    public List<Flor> listarTodos() throws SQLException {
        List<Flor> lista = new ArrayList<>();
        String sql = "SELECT nome, cor, altura FROM flor";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                String cor = rs.getString("cor");
                int altura = rs.getInt("altura");
                Flor flor = new Flor(nome, cor, altura);
                lista.add(flor);
            }
        }
        return lista;
    }

    public void atualizar(Flor flor, String nomeAntigo) throws SQLException {
        String sql = "UPDATE flor SET nome = ?, cor = ?, altura = ? WHERE nome = ?";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, flor.getNomeFlor());
            ps.setString(2, flor.getCorFlor());
            ps.setInt(3, flor.getAlturaFlor());
            ps.setString(4, nomeAntigo);
            ps.executeUpdate();
        }
    }
}
