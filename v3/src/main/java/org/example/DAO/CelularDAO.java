package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CelularDAO {

    private final String URL = "jdbc:mysql://localhost:3306/seubanco"; // ajuste seu banco
    private final String USER = "seuusuario";
    private final String PASSWORD = "suasenha";

    public CelularDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Inserir novo celular
    public void inserir(Celular c) throws SQLException {
        String sql = "INSERT INTO celular (marca, modelo, preco) VALUES (?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCelMarca());
            ps.setString(2, c.getCelModelo());
            ps.setDouble(3, c.getCelPreco());
            ps.executeUpdate();
        }
    }

    // Listar todos celulares
    public List<Celular> listarTodos() throws SQLException {
        List<Celular> lista = new ArrayList<>();
        String sql = "SELECT marca, modelo, preco FROM celular";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Celular c = new Celular(
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getDouble("preco")
                );
                lista.add(c);
            }
        }
        return lista;
    }

    // Atualizar celular (usando modelo como chave)
    public void atualizar(Celular c, String modeloAntigo) throws SQLException {
        String sql = "UPDATE celular SET marca = ?, modelo = ?, preco = ? WHERE modelo = ?";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCelMarca());
            ps.setString(2, c.getCelModelo());
            ps.setDouble(3, c.getCelPreco());
            ps.setString(4, modeloAntigo);
            ps.executeUpdate();
        }
    }
}
