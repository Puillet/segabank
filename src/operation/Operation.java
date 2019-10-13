package operation;

public class Operation {

    private int id;
    private double montant;
    private Transaction transaction;
    private int idCompte;


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

    public Operation() {
    }

    public Operation(double montant, Transaction transaction, int idCompte) {
        this.montant = montant;
        this.transaction = transaction;
        this.idCompte = idCompte;
    }

    public Operation(int id, double montant, Transaction transaction, int idCompte) {
        this.id = id;
        this.montant = montant;
        this.transaction = transaction;
        this.idCompte = idCompte;
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction Transaction) {
        this.transaction = Transaction;
    }


}
