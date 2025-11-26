package br.clinica.model;

import java.sql.Date;

public class Paciente {
    private Integer id;
    private String nome;
    private Date dataNascimento;
    private String telefone;
    private String endereco;

    public Paciente() {}
    public Paciente(Integer id, String nome, Date dataNascimento, String telefone, String endereco) {
        this.id = id; this.nome = nome; this.dataNascimento = dataNascimento; this.telefone = telefone; this.endereco = endereco;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
