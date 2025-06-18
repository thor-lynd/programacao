package org.example.dao;

import org.example.Classes.Cachorro;
import org.example.conexao.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CachorroDAO {

    public List<Cachorro> listarTodos() {
        List<Cachorro> cachorros = new ArrayList<>();
        String sql = "SELECT * FROM cachorro";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cachorro c = new Cachorro(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("raca"),
                        rs.getInt("idade")
                );

                cachorros.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cachorros;
    }

    public void adicionar(Cachorro cachorro) {
        String sql = "INSERT INTO cachorro (nome, idade, raca) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cachorro.getNome());
            ps.setInt(2, cachorro.getIdade());
            ps.setString(3, cachorro.getRaca());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Cachorro cachorro) {
        String sql = "UPDATE cachorro SET nome = ?, idade = ?, raca = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cachorro.getNome());
            ps.setInt(2, cachorro.getIdade());
            ps.setString(3, cachorro.getRaca());
            ps.setInt(4, cachorro.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM cachorro WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
