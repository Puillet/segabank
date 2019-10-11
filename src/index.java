import agence.Agence;
import compte.Compte;
import compte.CompteEpargne;
import compte.ComptePayant;
import compte.CompteSimple;
import jdbc.dal.*;

import java.io.IOException;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class index {

    private static Scanner sc = new Scanner(System.in);

    private static IDAO<Long, Compte> compteDAO = new CompteDAO();
    private static IDAO<Long, Agence> agenceDAO = new AgenceDAO();
    private static IDAO<Long, CompteSimple> compteSimpleDAO = new CompteSimpleDAO();
    private static IDAO<Long, CompteEpargne> compteEpargneDAO = new CompteEpargneDAO();
    private static IDAO<Long, ComptePayant> comptePayantDAO = new ComptePayantDAO();


    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException{
        menu();
    }

    private static void menu(){
        int response;
        boolean first = true;
        do {
            if ( !first ) {
                System.out.println( "Mauvais choix... merci de recommencer !" );
            }
            System.out.println( "======================================" );
            System.out.println( "=========== MENU - SEGABANK ==========" );
            System.out.println( "======================================" );
            System.out.println( "1 - Retrait d'argent" );
            System.out.println( "2 - Dépôt d'argent" );
            System.out.println( "3 - Créer un nouveau compte" );
            System.out.println( "4 - Modifier un compte" );
            System.out.println( "5 - Supprimer un compte" );
            System.out.println( "6 - Liste des agences" );
            System.out.println( "7 - Liste des comptes" );
            System.out.println( "9 - Quitter" );
            System.out.print( "Entrez votre choix : " );
            try {
                response = sc.nextInt();
            } catch ( InputMismatchException e ) {
                response = -1;
            } finally {
                sc.nextLine();
            }
            first = false;
        } while ( response < 1 || response > 9 );

        switch ( response ) {
            case 1:
                //createContact();
                break;
            case 2:
                //updateContact();
                break;
            case 3:
                compteMenu();
                break;
            case 4:
                //dspContacts( true );
                break;
            case 5:
                //storeCurrentBook();
                break;
            case 6:
                listAgence();
                break;
            case 7:
                listCompte();
                break;
            case 8:
                //sortContacts(false);
                break;
            case 9:
                System.exit(1);
                break;
        }
    }

    private static void compteMenu(){
        int response;
        boolean first = true;
        do {
            if ( !first ) {
                System.out.println( "Mauvais choix... merci de recommencer !" );
            }
            System.out.println( "======================================" );
            System.out.println( "=========== MENU - SEGABANK ==========" );
            System.out.println( "======================================" );
            System.out.println( "1 - Créer un compte simple" );
            System.out.println( "2 - Créer un compte épargne" );
            System.out.println( "3 - Créer un compte payant" );
            System.out.println( "4 - Retour au menu");
            System.out.print( "Entrez votre choix : " );
            try {
                response = sc.nextInt();
            } catch ( InputMismatchException e ) {
                response = -1;
            } finally {
                sc.nextLine();
            }
            first = false;
        } while ( response < 1 || response > 9 );

        switch ( response ) {
            case 1:
                createCompteSimple();
                break;
            case 2:
                createCompteEpargne();
                break;
            case 3:
                createComptePayant();
                break;
            case 4:
                menu();
                break;
        }
    }

    private static void createCompteSimple(){

        System.out.println("Création compte simple");
        try{
            System.out.print("Entrez un solde : ");
            double solde = sc.nextDouble();
            System.out.println("Choisissez une agence : ");
            listAgence();
            int agenceId = sc.nextInt();
            System.out.println("Entrez une valeur de découvert : ");
            double decouvert = sc.nextDouble();
            Compte compte = new Compte(solde);
            compte.setIdAgence(agenceId);
            compteDAO.create(compte);
            CompteSimple compteSimple = new CompteSimple(compte.getSolde(), decouvert, compte.getId());
            compteSimpleDAO.create(compteSimple);
            System.out.println("Compte créé !");

        } catch (InputMismatchException e){
            System.out.println("Une erreur est survenue lors de l'ajout du compte.");
            System.out.println(e.getMessage());
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            sc.nextLine();
        }

    }

    private static void createCompteEpargne(){

        System.out.println("Création compte épargne");
        try{
            System.out.print("Entrez un solde : ");
            double solde = sc.nextDouble();
            System.out.println("Choisissez une agence : ");
            listAgence();
            int agenceId = sc.nextInt();
            System.out.println("Entrez un taux d'intérêt: ");
            double tauxInteret = sc.nextDouble();
            Compte compte = new Compte(solde);
            compte.setIdAgence(agenceId);
            compteDAO.create(compte);
            CompteEpargne compteEpargne = new CompteEpargne(compte.getSolde(), tauxInteret, compte.getId());
            compteEpargneDAO.create(compteEpargne);
            System.out.println("Compte créé !");

        } catch (InputMismatchException e){
            System.out.println("Une erreur est survenue lors de l'ajout du compte.");
            System.out.println(e.getMessage());
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            sc.nextLine();
        }

    }

    private static void createComptePayant(){

        System.out.println("Création compte payant");
        try{
            System.out.print("Entrez un solde : ");
            double solde = sc.nextDouble();
            System.out.println("Choisissez une agence : ");
            listAgence();
            int agenceId = sc.nextInt();
            Compte compte = new Compte(solde);
            compte.setIdAgence(agenceId);
            compteDAO.create(compte);
            ComptePayant comptePayant = new ComptePayant(compte.getSolde(),compte.getId());
            comptePayantDAO.create(comptePayant);
            System.out.println("Compte créé !");

        } catch (InputMismatchException e){
            System.out.println("Une erreur est survenue lors de l'ajout du compte.");
            System.out.println(e.getMessage());
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            sc.nextLine();
        }

    }

    private static void listAgence(){
        try {
            for(Agence item : agenceDAO.findAll()){
                System.out.print("Agence " + item.getId());
                System.out.println("\n Adresse : " + item.getAdresse());
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.out.println("[ERREUR] : Aucune agence trouvée");
        }
    }

    private static void listCompte(){
        try {
            for(Compte item : compteDAO.findAll()){
                System.out.print("Compte " + item.getId());
                System.out.println("\n Solde : " + item.getSolde());
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.out.println("[ERREUR] : Aucune agence trouvée");
        }
    }
}
