package com.henrysuher.andriod.aplicacion2.Modelo;

public class Registro {
    private String Id;
    private String Concepto;
    private String Valor;
    private boolean Ingreso;

    public Registro(){
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getConcepto() {
        return Concepto;
    }

    public void setConcepto(String concepto) {
        Concepto = concepto;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    public boolean isIngreso() {
        return Ingreso;
    }

    public void setIngreso(boolean ingreso) {
        Ingreso = ingreso;
    }
}
