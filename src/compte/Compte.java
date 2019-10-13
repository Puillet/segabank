package compte;

import java.io.Serializable;

public class Compte implements Serializable {

    private int id;
    private double solde;
    private int idAgence;

    public Compte(){}

    public Compte(int id) {
        this.id = id;
    }

    public Compte(int id, double solde, int idAgence) {
        this.id = id;
        this.solde = solde;
        this.idAgence = idAgence;
    }

    public Compte(double solde) {
        this.solde = solde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public int getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }

    public void ajouterArgent(double solde){
        this.solde += solde;
    }

    public double retirerArgent(double montant){
        if(this.getSolde() - montant > 0){
            this.setSolde(this.solde - montant);
        }
        return this.getSolde();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Compte");
        sb.append("N°").append(id);
        sb.append("\n Solde=").append(solde);
        return sb.toString();
    }
}
