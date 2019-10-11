package compte;

public class CompteSimple extends Compte  {

    private double decouvert;
    private int idCompte;

    public CompteSimple( int idCompte, double decouvert) {
        this.idCompte = idCompte;
        this.decouvert = decouvert;
    }

    public CompteSimple(double solde, double decouvert, int idCompte) {
        super(solde);
        this.decouvert = decouvert;
        this.idCompte = idCompte;
    }

    public CompteSimple() {

    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    @Override
    public void retirerArgent(double solde) {
        super.retirerArgent(solde);
    }
}
