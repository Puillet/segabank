package compte;

public class CompteEpargne extends Compte {

    private double tauxInteret;
    private int idCompte;

    public CompteEpargne(double solde, double tauxInteret, int idCompte) {
        super(solde);
        this.tauxInteret = tauxInteret;
        this.idCompte = idCompte;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public void calculInteret(){
        double interet = this.getSolde()*tauxInteret/100;
    }
}
