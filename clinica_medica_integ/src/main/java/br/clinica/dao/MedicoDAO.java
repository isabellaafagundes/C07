package br.clinica.dao;

import br.clinica.db.DBConnection;
import br.clinica.model.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    public void insert(Medico m) throws SQLException {
        String sql = "INSERT INTO medico (nome, crm, telefone, email) VALUES (?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getNome());
            ps.setString(2, m.getCrm());
            ps.setString(3, m.getTelefone());
            ps.setString(4, m.getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) m.setId(rs.getInt(1));
            }
        }
    }

    public void update(Medico m) throws SQLException {
        String sql = "UPDATE medico SET nome=?, crm=?, telefone=?, email=? WHERE id_medico=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, m.getNome());
            ps.setString(2, m.getCrm());
            ps.setString(3, m.getTelefone());
            ps.setString(4, m.getEmail());
            ps.setInt(5, m.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM medico WHERE id_medico=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Medico findById(int id) throws SQLException {
        String sql = "SELECT * FROM medico WHERE id_medico=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Medico m = new Medico();
                    m.setId(rs.getInt("id_medico"));
                    m.setNome(rs.getString("nome"));
                    m.setCrm(rs.getString("crm"));
                    m.setTelefone(rs.getString("telefone"));
                    m.setEmail(rs.getString("email"));
                    return m;
                }
            }
        }
        return null;
    }

    public List<Medico> findAll() throws SQLException {
        String sql = "SELECT * FROM medico";
        List<Medico> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Medico m = new Medico();
                m.setId(rs.getInt("id_medico"));
                m.setNome(rs.getString("nome"));
                m.setCrm(rs.getString("crm"));
                m.setTelefone(rs.getString("telefone"));
                m.setEmail(rs.getString("email"));
                list.add(m);
            }
        }
        return list;
    }

    // JOIN example: list medico with specialties (returns string rows)
    public List<String> listMedicosComEspecialidades() throws SQLException {
        String sql = "SELECT m.id_medico, m.nome AS medico, e.nome AS especialidade " +
                     "FROM medico m " +
                     "JOIN medico_especialidade me ON me.id_medico = m.id_medico " +
                     "JOIN especialidade e ON e.id_especialidade = me.id_especialidade";
        List<String> out = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(rs.getInt("id_medico") + " | " + rs.getString("medico") + " | " + rs.getString("especialidade"));
            }
        }
        return out;
    }
}
