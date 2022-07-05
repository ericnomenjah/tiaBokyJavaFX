package com.example.bibliotheque.Model;

import com.example.bibliotheque.Repository.LecteurRepository;
import com.example.bibliotheque.Repository.LivreRepository;

import java.sql.Date;
import java.sql.SQLException;

public class Pret {
    int numero, numeroLivre,numeroLecteur;
    String datePret, dateRetour, designation,autheur,lecteur;
    Boolean rendu;

    public String getDesignation() throws SQLException {
        LivreRepository livreRepository = new LivreRepository();
        Livre livre = livreRepository.getLivreByNumero(String.valueOf(this.numeroLivre));
        return livre.getTitre();
    }

    public void setDesignation(String designation) throws SQLException {
        this.designation = getDesignation();
    }

    public String getAutheur() throws SQLException {
        LivreRepository livreRepository = new LivreRepository();
        Livre livre = livreRepository.getLivreByNumero(String.valueOf(this.numeroLivre));
        return livre.getAutheur();
    }

    public void setAutheur(String autheur) {
        this.autheur = autheur;
    }

    public String getLecteur() throws SQLException {
        LecteurRepository lecteurRepository = new LecteurRepository();
        Lecteur lecteur = lecteurRepository.getLecteurByNumero(String.valueOf(this.numeroLecteur));
        return lecteur.getNom();
    }

    public void setLecteur(String lecteur) {
        this.lecteur = lecteur;
    }

    public Pret(int numero, int numeroLecteur, int numeroLivre, String datePret, String dateRetour, Boolean rendu) {
        this.numero = numero;
        this.numeroLecteur = numeroLecteur;
        this.numeroLivre = numeroLivre;
        this.datePret = datePret;
        this.dateRetour = dateRetour;
        this.rendu = rendu;
    }

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
