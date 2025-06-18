package org.example.dao;

import org.example.Classes.Carro;
import org.example.conexao.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

    public List<Carro> listarTodos() {
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT * FROM carro";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Carro carro = new Carro(
                        rs.getInt("id"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getInt("ano")
                );
                carros.add(carro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carros;
    }

    public void adicionar(Carro carro) {
        String sql = "INSERT INTO carro (modelo, marca, ano) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, carro.getModelo());
            ps.setString(2, carro.getMarca());
            ps.setInt(3, carro.getAno());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Carro carro) {
        String sql = "UPDATE carro SET modelo = ?, marca = ?, ano = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, carro.getModelo());
            ps.setString(2, carro.getMarca());
            ps.setInt(3, carro.getAno());
            ps.setInt(4, carro.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM carro WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
