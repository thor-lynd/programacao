package org.example.dao;

import org.example.Classes.Album;
import org.example.conexao.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    public List<Album> listarTodos() {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT * FROM album";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Album album = new Album(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("musicas"),
                        rs.getString("artista")
                );
                albums.add(album);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return albums;
    }

    public void adicionar(Album album) {
        String sql = "INSERT INTO Album (nome, musicas, artista) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, album.getNome());
            ps.setInt(2, album.getMusicas());
            ps.setString(3, album.getArtista());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Album album) {
        String sql = "UPDATE Album SET nome = ?, musicas = ?, artista = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, album.getNome());
            ps.setInt(2, album.getMusicas());
            ps.setString(3, album.getArtista());
            ps.setInt(4, album.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Album WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
