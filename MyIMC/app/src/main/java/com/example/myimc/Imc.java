package com.example.myimc;

public class Imc {
    private int taille;
    private int poids;
    private double imc;
    private String date;

    public Imc(int taille, int poids, double imc, String date) {
        this.taille = taille;
        this.poids = poids;
        this.imc = imc;
        this.date = date;
    }

    public int getTaille() {
        return taille;
    }
    public int getPoids() {
        return poids;
    }
    public double getIMC() {
        return imc;
    }

    public String getDate() {
      return date;
    }
}
