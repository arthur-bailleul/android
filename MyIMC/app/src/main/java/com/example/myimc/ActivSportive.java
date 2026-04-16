package com.example.myimc;

public class ActivSportive {
    private String nom;
    private String date;
    private int duree;
    private String commentaire;
    public ActivSportive(String nom, String date, int duree, String commentaire) {
       this.nom = nom;
        this.date = date;
        this.duree = duree;
        this.commentaire = commentaire;
    }

    public String getNom() {
        return nom;
    }

    public String getInfo() {
        return "Date: " + date + "\n"
        + "Duree: " + duree + "m\n"
        + "Commentaire: " + commentaire;
    }

    @Override
    public String toString() {
        return "" + date + " - " + nom + " - " + duree + "m";
    }
}
