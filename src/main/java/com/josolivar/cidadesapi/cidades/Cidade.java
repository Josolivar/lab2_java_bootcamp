package com.josolivar.cidadesapi.cidades;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.geo.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cidade")
@TypeDefs(value = {
        @TypeDef(name = "point", typeClass = PointType.class)
})
public class Cidade {

    @Id
    private long id;
    @Column
    private String nome;
    private Integer uf;
    private Integer ibge;
    @Column(name = "lat_lon")
    private String geolocalizacao;
    @Type(type = "point")
    @Column(name = "lat_lon", updatable = false, insertable = false)
    private Point localizacao;

    public Cidade() {
    }

    public Cidade(final long id, final String nome, final int uf, final int ibge,
                final String geolocalizacao, final Point localizacao) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.ibge = ibge;
        this.geolocalizacao = geolocalizacao;
        this.localizacao = localizacao;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getUf() {
        return uf;
    }

    public Integer getIbge() {
        return ibge;
    }

    public String getGeolocalizacao() {
        return geolocalizacao;
    }

    public Point getLocalizacao() {
        return localizacao;
    }
}