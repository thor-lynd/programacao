package org.example.dao;

import org.example.Classes.Celular;
import org.example.conexao.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CelularDAO {

    public List<Celular> listarTodos() {
        List<Celular> celulares = new ArrayList<>();
        String sql = "SELECT * FROM celular";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Celular celular = new Celular(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getDouble("preco")
                );
                celulares.add(celular);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return celulares;
    }

    public void adicionar(Celular celular) {
        String sql = "INSERT INTO celular (marca, modelo, preco) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, celular.getMarca());
            ps.setString(2, celular.getModelo());
            ps.setDouble(3, celular.getPreco());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Celular celular) {
        String sql = "UPDATE celular SET marca = ?, modelo = ?, preco = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, celular.getMarca());
            ps.setString(2, celular.getModelo());
            ps.setDouble(3, celular.getPreco());
            ps.setInt(4, celular.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM celular WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
