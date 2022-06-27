package com.josolivar.cidadesapi.paises;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pais")
public class Pais {

    @Id
    private long id;
    @Column(name = "nome")
    private String nomeEmIngles;
    @Column(name = "nome_pt")
    private String nomeEmPortugues;
    @Column
    private String sigla;
    @Column
    private long bacen;

    public long getId() { return id; }

    public String getNomeEmIngles() { return nomeEmIngles; }

    public String getNomeEmPortugues() { return nomeEmPortugues; }

    public String getSigla() { return sigla; }

    public long getBacen() { return bacen; }
}
