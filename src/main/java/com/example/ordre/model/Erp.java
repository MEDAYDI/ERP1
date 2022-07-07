package com.example.ordre.model;


import javax.persistence.*;

@Entity
@Table(name="erp")
public class Erp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String of ;
    private String client ;
    private String lot ;
    private Integer qteLot ;


    public Erp() {

    }

    public Erp(String of, String client, String lot, Integer qteLot) {
        this.of = of;
        this.client = client;
        this.lot = lot;
        this.qteLot = qteLot;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOf() {
        return of;
    }

    public void setOf(String of) {
        this.of = of;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public Integer getQteLot() {
        return qteLot;
    }

    public void setQteLot(Integer qteLot) {
        this.qteLot = qteLot;
    }

}

