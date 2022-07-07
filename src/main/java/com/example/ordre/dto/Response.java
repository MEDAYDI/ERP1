package com.example.ordre.dto;

public class Response {
    private String of ;
    private String client ;
    private String lot ;
    private Integer qteLot ;


    public Response(){}

    public Response(String of,  String client, String lot, Integer qteLot) {
        this.of = of;
        this.client = client;
        this.lot = lot;
        this.qteLot=qteLot ;
    }

    public Integer getQteLot() {
        return qteLot;
    }
    public void setQteLot(Integer qteLot) {
        this.qteLot = qteLot;
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
}
