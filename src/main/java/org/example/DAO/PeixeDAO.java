package org.example.dao;

import org.example.Classes.Peixe;
import org.example.conexao.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeixeDAO {

    public List<Peixe> listarTodos() {
        List<Peixe> peixes = new ArrayList<>();
        String sql = "SELECT * FROM peixe";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Peixe peixe = new Peixe(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getInt("quantidade")
                );
                peixes.add(peixe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return peixes;
    }

    public void adicionar(Peixe peixe) {
        String sql = "INSERT INTO peixe (nome, tipo, quantidade) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, peixe.getNome());
            ps.setString(2, peixe.getTipo());
            ps.setInt(3, peixe.getQuantidade());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Peixe peixe) {
        String sql = "UPDATE peixe SET nome = ?, tipo = ?, quantidade = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, peixe.getNome());
            ps.setString(2, peixe.getTipo());
            ps.setInt(3, peixe.getQuantidade());
            ps.setInt(4, peixe.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM peixe WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
