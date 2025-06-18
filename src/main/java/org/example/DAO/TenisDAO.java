package org.example.dao;

import org.example.Classes.Tenis;
import org.example.conexao.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TenisDAO {

    public List<Tenis> listarTodos() {
        List<Tenis> tenis = new ArrayList<>();
        String sql = "SELECT * FROM tenis";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tenis t = new Tenis(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("cor"),
                        rs.getInt("tamanho")
                );
                tenis.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tenis;
    }

    public void adicionar(Tenis tenis) {
        String sql = "INSERT INTO tenis (marca, cor, tamanho) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tenis.getMarca());
            ps.setString(2, tenis.getCor());
            ps.setInt(3, tenis.getTamanho());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Tenis tenis) {
        String sql = "UPDATE tenis SET marca = ?, cor = ?, tamanho = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tenis.getMarca());
            ps.setString(2, tenis.getCor());
            ps.setInt(3, tenis.getTamanho());
            ps.setInt(4, tenis.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM tenis WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
