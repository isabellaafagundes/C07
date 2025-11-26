package br.clinica.model;

import java.sql.Date;
import java.sql.Time;

public class Consulta {
    private Integer id;
    private Date data;
    private Time hora;
    private String observacoes;
    private Integer idPaciente;
    private Integer idMedico;

    public Consulta() {}
    public Consulta(Integer id, Date data, Time hora, String observacoes, Integer idPaciente, Integer idMedico) {
        this.id = id; this.data = data; this.hora = hora; this.observacoes = observacoes; this.idPaciente = idPaciente; this.idMedico = idMedico;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
    public Time getHora() { return hora; }
    public void setHora(Time hora) { this.hora = hora; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }
    public Integer getIdMedico() { return idMedico; }
    public void setIdMedico(Integer idMedico) { this.idMedico = idMedico; }
}
