package operation;

public class Operation {

    private int id;
    private double montant;
    private int idCompte;
    private int idAgence;
    private Transaction transaction;

    public Operation() {

    }


    public enum Transaction{
        D("Depot"), R("Retrait");

        private String type;

        Transaction(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static Transaction fromString(String text) {
            for (Transaction t : Transaction.values()) {
                if (t.type.equalsIgnoreCase(text)) {
                    return t;
                }
            }
            return null;
        }


    }

    public Operation(double montant, int idAgence, Transaction transaction) {
        this.montant = montant;
        this.idAgence = idAgence;
        this.transaction = transaction;
    }

    public Operation(int id, double montant, int idCompte, int idAgence, Transaction transaction) {
        this.id = id;
        this.montant = montant;
        this.idCompte = idCompte;
        this.idAgence = idAgence;
        this.transaction = transaction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public int getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
