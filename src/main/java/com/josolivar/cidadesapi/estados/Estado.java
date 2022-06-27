package com.josolivar.cidadesapi.estados;

import com.josolivar.cidadesapi.paises.Pais;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Estado")
@Table(name = "estado")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Estado {
    @Id
    private long id;
    @Column
    private String nome;
    @Column
    private String uf;
    @Column
    private int ibge;
    @Column(name = "pais")
    private int paisId;
    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "id")
    private Pais pais;
    @Type(type = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ddd", columnDefinition = "jsonb")
    private List<Integer> ddd;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUf() {
        return uf;
    }

    public int getIbge() {
        return ibge;
    }

    public int getPaisId() {
        return paisId;
    }

    public Pais getPais() {
        return pais;
    }

    public List<Integer> getDdd() {
        return ddd;
    }
}
