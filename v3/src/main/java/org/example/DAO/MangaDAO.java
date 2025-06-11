package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MangaDAO {

    private Connection conectar() throws SQLException {
        // Configure a conexão com seu banco (ajuste URL, usuário e senha)
        String url = "jdbc:mysql://localhost:3306/seubanco";
        String user = "root";
        String password = "sua_senha";
        return DriverManager.getConnection(url, user, password);
    }

    public void inserir(Manga manga) throws SQLException {
        String sql = "INSERT INTO manga (nome, capitulo, nota) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, manga.getNomeManga());
            ps.setInt(2, manga.getCapituloManga());
            ps.setInt(3, manga.getNotaManga());
            ps.executeUpdate();
        }
    }

    public List<Manga> listarTodos() throws SQLException {
        List<Manga> lista = new ArrayList<>();
        String sql = "SELECT nome, capitulo, nota FROM manga";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                int capitulo = rs.getInt("capitulo");
                int nota = rs.getInt("nota");
                lista.add(new Manga(nome, capitulo, nota));
            }
        }
        return lista;
    }

    public void atualizar(Manga manga, String nomeAntigo) throws SQLException {
        String sql = "UPDATE manga SET nome = ?, capitulo = ?, nota = ? WHERE nome = ?";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, manga.getNomeManga());
            ps.setInt(2, manga.getCapituloManga());
            ps.setInt(3, manga.getNotaManga());
            ps.setString(4, nomeAntigo);
            ps.executeUpdate();
        }
    }
}
