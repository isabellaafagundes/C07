package br.clinica.dao;

import br.clinica.db.DBConnection;
import br.clinica.model.Especialidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDAO {

    public void insert(Especialidade e) throws SQLException {
        String sql = "INSERT INTO especialidade (nome, descricao) VALUES (?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getNome());
            ps.setString(2, e.getDescricao());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) e.setId(rs.getInt(1));
            }
        }
    }

    public void update(Especialidade e) throws SQLException {
        String sql = "UPDATE especialidade SET nome=?, descricao=? WHERE id_especialidade=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getNome());
            ps.setString(2, e.getDescricao());
            ps.setInt(3, e.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM especialidade WHERE id_especialidade=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Especialidade findById(int id) throws SQLException {
        String sql = "SELECT * FROM especialidade WHERE id_especialidade=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Especialidade e = new Especialidade();
                    e.setId(rs.getInt("id_especialidade"));
                    e.setNome(rs.getString("nome"));
                    e.setDescricao(rs.getString("descricao"));
                    return e;
                }
            }
        }
        return null;
    }

    public List<Especialidade> findAll() throws SQLException {
        String sql = "SELECT * FROM especialidade";
        List<Especialidade> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Especialidade e = new Especialidade();
                e.setId(rs.getInt("id_especialidade"));
                e.setNome(rs.getString("nome"));
                e.setDescricao(rs.getString("descricao"));
                list.add(e);
            }
        }
        return list;
    }
}
