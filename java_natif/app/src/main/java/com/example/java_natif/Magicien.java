package com.example.java_natif;

import androidx.annotation.NonNull;

public class Magicien extends Personnage {
    public Magicien() {}

    public Magicien(String nom, int vita, int force, int mana) {
        super(nom, vita, force, mana);
    }

    public Magicien(String nom) {
        super(nom);
    }

    @NonNull
    @Override
    public String toString() {
        return "Nom: " + this.getNom() + "\n" +
                "Pt de vie : " + this.getVita() + "\n" +
                "Pt de force : " + this.getForce() + "\n" +
                "Pt de mana : " + this.getMana() + "\n";
    }

}
