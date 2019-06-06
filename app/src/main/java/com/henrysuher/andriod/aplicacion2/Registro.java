package com.henrysuher.andriod.aplicacion2;

public class Registro {
    private int Id;
    private String Concepto;
    private float Valor;
    private boolean Ingreso;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getConcepto() {
        return Concepto;
    }

    public void setConcepto(String concepto) {
        Concepto = concepto;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float valor) {
        Valor = valor;
    }

    public boolean isIngreso() {
        return Ingreso;
    }

    public void setIngreso(boolean ingreso) {
        Ingreso = ingreso;
    }
}
