package org.example.dao;

import org.example.Classes.Flor;
import org.example.conexao.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlorDAO {

    public List<Flor> listarTodos() {
        List<Flor> flores = new ArrayList<>();
        String sql = "SELECT * FROM flor";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Flor flor = new Flor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cor"),
                        rs.getInt("altura")
                );
                flores.add(flor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flores;
    }

    public void adicionar(Flor flor) {
        String sql = "INSERT INTO flor (nome, cor, altura) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, flor.getNome());
            ps.setString(2, flor.getCor());
            ps.setInt(3, flor.getAltura());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Flor flor) {
        String sql = "UPDATE flor SET nome = ?, cor = ?, altura = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, flor.getNome());
            ps.setString(2, flor.getCor());
            ps.setInt(3, flor.getAltura());
            ps.setInt(4, flor.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM flor WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
