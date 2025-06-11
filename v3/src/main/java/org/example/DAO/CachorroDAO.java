package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CachorroDAO {

    private final String URL = "jdbc:mysql://localhost:3306/seubanco"; // altere o nome do banco
    private final String USER = "seuusuario";  // altere seu usuário MySQL
    private final String PASSWORD = "suasenha"; // altere sua senha

    public CachorroDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver MySQL 8+
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Inserir cachorro no banco
    public void inserir(Cachorro c) throws SQLException {
        String sql = "INSERT INTO cachorro (nome, raca, idade) VALUES (?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getRaca());
            ps.setInt(3, c.getIdade());
            ps.executeUpdate();
        }
    }

    // Listar todos cachorros do banco
    public List<Cachorro> listarTodos() throws SQLException {
        List<Cachorro> lista = new ArrayList<>();
        String sql = "SELECT id, nome, raca, idade FROM cachorro";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cachorro c = new Cachorro(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("raca"),
                        rs.getInt("idade")
                );
                lista.add(c);
            }
        }
        return lista;
    }

    // Atualizar cachorro no banco (por id)
    public void atualizar(Cachorro c) throws SQLException {
        String sql = "UPDATE cachorro SET nome = ?, raca = ?, idade = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getRaca());
            ps.setInt(3, c.getIdade());
            ps.setInt(4, c.getId());
            ps.executeUpdate();
        }
    }
}
