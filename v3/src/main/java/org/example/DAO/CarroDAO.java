package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

    private final String URL = "jdbc:mysql://localhost:3306/seubanco"; // ajuste o banco
    private final String USER = "seuusuario"; // ajuste o usuário
    private final String PASSWORD = "suasenha"; // ajuste a senha

    public CarroDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Inserir novo carro
    public void inserir(Carro c) throws SQLException {
        String sql = "INSERT INTO carro (modelo, marca, ano) VALUES (?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getModelo());
            ps.setString(2, c.getMarca());
            ps.setInt(3, c.getAno());
            ps.executeUpdate();
        }
    }

    // Listar todos carros
    public List<Carro> listarTodos() throws SQLException {
        List<Carro> lista = new ArrayList<>();
        String sql = "SELECT modelo, marca, ano FROM carro";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Carro c = new Carro(
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getInt("ano")
                );
                lista.add(c);
            }
        }
        return lista;
    }

    // Atualizar carro (usando modelo como chave - simplificado, ideal usar ID)
    public void atualizar(Carro c, String modeloAntigo) throws SQLException {
        String sql = "UPDATE carro SET modelo = ?, marca = ?, ano = ? WHERE modelo = ?";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getModelo());
            ps.setString(2, c.getMarca());
            ps.setInt(3, c.getAno());
            ps.setString(4, modeloAntigo);
            ps.executeUpdate();
        }
    }
}
