package com.example.bibliotheque.Model;

import java.sql.Date;

public class Pret {
    int numero, numeroLivre,numeroLecteur;
    String datePret, dateRetour;
    Boolean rendu;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumeroLivre() {
        return numeroLivre;
    }

    public void setNumeroLivre(int numeroLivre) {
        this.numeroLivre = numeroLivre;
    }

    public int getNumeroLecteur() {
        return numeroLecteur;
    }

    public void setNumeroLecteur(int numeroLecteur) {
        this.numeroLecteur = numeroLecteur;
    }

    public String getDatePret() {
        return datePret;
    }

    public void setDatePret(String datePret) {
        this.datePret = datePret;
    }

    public String getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Boolean getRendu() {
        return rendu;
    }

    public void setRendu(Boolean rendu) {
        this.rendu = rendu;
    }
}
