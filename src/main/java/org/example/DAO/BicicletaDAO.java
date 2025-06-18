package org.example.dao;

import org.example.Classes.Bicicleta;
import org.example.conexao.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BicicletaDAO {

    public List<Bicicleta> listarTodos() {
        List<Bicicleta> bicicletas = new ArrayList<>();
        String sql = "SELECT * FROM bicicleta";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Bicicleta bicicleta = new Bicicleta(
                        rs.getInt("id"),
                        rs.getString("modelo"),
                        rs.getString("cor"),
                        rs.getInt("marchas")
                );
                bicicletas.add(bicicleta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bicicletas;
    }

    public void adicionar(Bicicleta bicicleta) {
        String sql = "INSERT INTO bicicleta (modelo, cor, marchas) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bicicleta.getModelo());
            ps.setString(2, bicicleta.getCor());
            ps.setInt(3, bicicleta.getMarchas());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Bicicleta bicicleta) {
        String sql = "UPDATE bicicleta SET modelo = ?, cor = ?, marchas = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bicicleta.getModelo());
            ps.setString(2, bicicleta.getCor());
            ps.setInt(3, bicicleta.getMarchas());
            ps.setInt(4, bicicleta.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM bicicleta WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
