package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TenisDAO {

    private final String URL = "jdbc:mysql://localhost:3306/seubanco";
    private final String USER = "seuusuario";
    private final String PASS = "suasenha";

    public TenisDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver MySQL 8+
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public void inserir(Tenis tenis) {
        String sql = "INSERT INTO tenis (marca, cor, tamanho) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, tenis.getMarca());
            ps.setString(2, tenis.getCor());
            ps.setInt(3, tenis.getTamanho());
            ps.executeUpdate();

            // Obter id gerado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    tenis.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Tenis tenis) {
        String sql = "UPDATE tenis SET marca = ?, cor = ?, tamanho = ? WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenis.getMarca());
            ps.setString(2, tenis.getCor());
            ps.setInt(3, tenis.getTamanho());
            ps.setInt(4, tenis.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tenis> listarTodos() {
        List<Tenis> lista = new ArrayList<>();
        String sql = "SELECT * FROM tenis";
        try (Connection conn = conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Tenis tenis = new Tenis(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("cor"),
                        rs.getInt("tamanho")
                );
                lista.add(tenis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
