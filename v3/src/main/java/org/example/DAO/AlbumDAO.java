package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    private final String url = "jdbc:mysql://localhost:3306/seu_banco";
    private final String user = "seu_usuario";
    private final String password = "sua_senha";

    public void inserir(Album album) throws SQLException {
        String sql = "INSERT INTO album (nome, musicas, artista) VALUES (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, album.getNome());
            pst.setInt(2, album.getMusicas());
            pst.setString(3, album.getArtista());
            pst.executeUpdate();
        }
    }

    public void atualizar(Album album) throws SQLException {
        String sql = "UPDATE album SET nome = ?, musicas = ?, artista = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, album.getNome());
            pst.setInt(2, album.getMusicas());
            pst.setString(3, album.getArtista());
            pst.setInt(4, album.getId());
            pst.executeUpdate();
        }
    }

    public List<Album> listar() throws SQLException {
        List<Album> albuns = new ArrayList<>();
        String sql = "SELECT * FROM album";
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Album a = new Album(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("musicas"),
                        rs.getString("artista"));
                albuns.add(a);
            }
        }
        return albuns;
    }
}
