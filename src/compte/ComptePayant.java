package compte;

public class ComptePayant extends Compte  {

    private double pourcentageInteret = 5;
    private int idCompte;

    public ComptePayant(int id, double solde,int idAgence, double pourcentageInteret, int idCompte) {
        super(id, solde, idAgence);
        this.pourcentageInteret = pourcentageInteret;
        this.idCompte = idCompte;
    }

    public ComptePayant(double solde, double pourcentageInteret, int idCompte) {
        super(solde);
        this.pourcentageInteret = pourcentageInteret;
        this.idCompte = idCompte;
    }

    public ComptePayant(double pourcentageInteret, int idCompte) {
        this.pourcentageInteret = pourcentageInteret;
        this.idCompte = idCompte;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public double getPourcentageInteret() {
        return pourcentageInteret;
    }

    public void setPourcentageInteret(double pourcentageInteret) {
        this.pourcentageInteret = pourcentageInteret;
    }
}
