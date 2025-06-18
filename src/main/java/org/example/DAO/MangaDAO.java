package org.example.dao;

import org.example.Classes.Manga;
import org.example.conexao.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MangaDAO {

    public List<Manga> listarTodos() {
        List<Manga> mangas = new ArrayList<>();
        String sql = "SELECT * FROM manga";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Manga manga = new Manga(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("capitulo"),
                        rs.getInt("nota")
                );
                mangas.add(manga);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mangas;
    }

    public void adicionar(Manga manga) {
        String sql = "INSERT INTO manga (nome, capitulo, nota) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, manga.getNome());
            ps.setInt(2, manga.getCapitulo());
            ps.setInt(3, manga.getNota());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Manga manga) {
        String sql = "UPDATE manga SET nome = ?, capitulo = ?, nota = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, manga.getNome());
            ps.setInt(2, manga.getCapitulo());
            ps.setInt(3, manga.getNota());
            ps.setInt(4, manga.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM manga WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
