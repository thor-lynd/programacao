package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TenisDAO {

    private Connection getConnection() throws SQLException {
        // Ajuste a URL, usuário e senha conforme seu banco
        String url = "jdbc:mysql://localhost:3306/seubanco";
        String user = "root";
        String password = "sua_senha";
        return DriverManager.getConnection(url, user, password);
    }

    public void inserirTenis(Tenis tenis) throws SQLException {
        String sql = "INSERT INTO tenis (marca, cor, tamanho) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tenis.getMarcaTenis());
            stmt.setString(2, tenis.getCorTenis());
            stmt.setInt(3, tenis.getTamanhoTenis());
            stmt.executeUpdate();

            // Obter o id gerado e setar no objeto
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    tenis.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Tenis> listarTenis() throws SQLException {
        List<Tenis> lista = new ArrayList<>();
        String sql = "SELECT id, marca, cor, tamanho FROM tenis";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Tenis tenis = new Tenis(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("cor"),
                        rs.getInt("tamanho")
                );
                lista.add(tenis);
            }
        }
        return lista;
    }

    public void atualizarTenis(int id, Tenis tenis) throws SQLException {
        String sql = "UPDATE tenis SET marca = ?, cor = ?, tamanho = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenis.getMarcaTenis());
            stmt.setString(2, tenis.getCorTenis());
            stmt.setInt(3, tenis.getTamanhoTenis());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    public void deletarTenis(int id) throws SQLException {
        String sql = "DELETE FROM tenis WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
