package com.example.cadastrocartola;

public class Time {

    private int id;
    private String nome;
    private int ano;
    private String km;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setKm(String km) {this.km = km;}

    public String getKm() {
        return km;
    }


    public double getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String toString() {
        if ( ano == 0){
            return this.nome;
        }else{
            return "" + this.ano + " - " + this.nome + " - " + this.km;
        }
    }
}
