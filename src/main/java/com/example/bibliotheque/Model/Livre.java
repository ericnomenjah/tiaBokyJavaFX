package com.example.bibliotheque.Model;

public class Livre {
    public Livre(int numero, String titre, String autheur, String edition, Boolean disponible, int nbPret) {
        this.numero = numero;
        this.nbPret = nbPret;
        this.titre = titre;
        this.autheur = autheur;
        this.edition = edition;
        this.disponible = disponible;
    }

    int numero,nbPret;
    String titre;
    String autheur;
    String edition;
    Boolean disponible;


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNbPret() {
        return nbPret;
    }

    public void setNbPret(int nbPret) {
        this.nbPret = nbPret;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAutheur() {
        return autheur;
    }

    public void setAutheur(String autheur) {
        this.autheur = autheur;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public String getFormattedDisponible(){
        if(disponible) return "oui";
        return "non";
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
