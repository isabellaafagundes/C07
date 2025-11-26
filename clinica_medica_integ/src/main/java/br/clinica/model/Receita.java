package br.clinica.model;

import java.sql.Date;

public class Receita {
    private Integer id;
    private String descricao;
    private Date dataEmissao;
    private Date validade;
    private Integer idConsulta;

    public Receita() {}
    public Receita(Integer id, String descricao, Date dataEmissao, Date validade, Integer idConsulta) {
        this.id = id; this.descricao = descricao; this.dataEmissao = dataEmissao; this.validade = validade; this.idConsulta = idConsulta;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Date getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(Date dataEmissao) { this.dataEmissao = dataEmissao; }
    public Date getValidade() { return validade; }
    public void setValidade(Date validade) { this.validade = validade; }
    public Integer getIdConsulta() { return idConsulta; }
    public void setIdConsulta(Integer idConsulta) { this.idConsulta = idConsulta; }
}
