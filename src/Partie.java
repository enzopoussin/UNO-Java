import java.util.Random;
import java.util.Scanner;

public class Partie {
    private Player joueur;
    private Player pc1;
    private Player pc2;
    private Player pc3;
    private int sens;                   //entre 0 et 1 pour le sens


    //constructeur


    public Partie(String nomJoueur) {
        joueur = new Player(nomJoueur);
        pc1 = new Player(0);
        pc2 = new Player(1);
        pc3 = new Player(2);
        sens = 0;
    }

    //Methodes de la classe partie


    public void presenterPartie() {
        joueur.presenterJoueur();
        System.out.println("\n\n Vous allez jouer contre : \n\n");
        pc1.presenterAdversaire();
        pc2.presenterAdversaire();
        pc3.presenterAdversaire();
        System.out.println("\n\n\n\n");
    }

    public void lancerPartie() {
        Scanner saisi = new Scanner(System.in);

        //permet d'initialiser le joueur qui va commencer la partie par defaut c'est l'utilisateur
        int joueurActuelle = 0;

        //genere la carte du debut (entre 1 et 9)
        Carte carteEnJeu = new Carte();
        while (carteEnJeu.getValeur() == 10 || carteEnJeu.getValeur() == 11 || carteEnJeu.getValeur() == 12) {
            carteEnJeu = new Carte();
        }

        //(debut du jeux, systeme de tour par tour
        while (true) {
            if (this.sens == 0) {

                //cas pour l'utlisateur

                if (joueurActuelle == 0) {

                    //afficher info du joueur

                    System.out.println("\nC'est a " + joueur.getNom() + " de jouer");
                    joueur.getToutesCartes();
                    System.out.println(pc1.getNom() + " à " + pc1.getNombreCarte() + " cartes || " + pc2.getNom() + " à " + pc2.getNombreCarte() + " cartes || " + pc3.getNom() + " à " + pc3.getNombreCarte() + " cartes");
                    System.out.println("\nCarte en jeu : ");
                    carteEnJeu.presenterCarte();
                    System.out.println("\nPiocher : -1 || Jouer : [numero de carte]");
                    int choixJoueur = saisi.nextInt();

                    //permet de gerer l'entrée du joueur

                    while (choixJoueur < -1 || choixJoueur > joueur.getNombreCarte() || choixJoueur == 0) {
                        System.out.println("Votre choix ne peux pas être pris en compte. entrez à nouveau une carte");
                        choixJoueur = saisi.nextInt();
                    }

                    //le joueur pioche si -1

                    if (choixJoueur == -1) {
                        joueur.piocher();
                        joueurActuelle = 1;
                    }

                    //le joueur joueur joue mais on reverifie si il donne une carte valide

                    else if (choixJoueur >= 1 || choixJoueur <= joueur.getNombreCarte()) {
                        while (!joueur.peutJouer(choixJoueur, carteEnJeu)) {
                            System.out.println("La carte ne peut pas être jouée, entrez à nouveau une carte");
                            choixJoueur = saisi.nextInt();
                        }
                        Carte carte_temp = joueur.jouer(choixJoueur, carteEnJeu);
                        carteEnJeu = carte_temp;
                        System.out.println("Vous avez joué " + carteEnJeu.getValeur() + carteEnJeu.getCouleur());
                        joueurActuelle = 1;

                        //verifie si partie gagnée

                        if (joueur.getNombreCarte() == 0){
                            System.out.println("Vous avez gagné");
                            break;
                        }

                        //verifie si la carte en jeu est un "passe ton tour" et on fait +2 au joueur actuel

                        if(carteEnJeu.getValeur() == 11){
                            System.out.println("le joueur " + pc1.getNom() + " passe son tour");
                            joueurActuelle = 2;
                        }

                        //cas pour la double pioche

                        if(carteEnJeu.getValeur() == 12){
                            System.out.println("le joueur " + pc1.getNom() + " se prend une double pioche");
                            System.out.println("Le joueur " + pc1.getNom() + " a pioché 2x");
                            pc1.piocherAdversaire();
                            pc1.piocherAdversaire();
                            joueurActuelle = 2;
                        }
                    }
                }

                //les 3 cas suivants sont pour les 3 ordinateurs

                else if (joueurActuelle == 1) {
                    System.out.println("\nC'est a " + pc1.getNom() + " de jouer");

                    //Cas si le pc peut jouer

                    if (pc1.peutJouerPc(carteEnJeu)) {
                        Carte carte_temp =  pc1.jouerPc(carteEnJeu);
                        carteEnJeu = carte_temp;
                        System.out.println("\n" + pc1.getNom() + " a joué : " + carteEnJeu.getCouleur() + "/" + carteEnJeu.getValeur());
                        joueurActuelle = 2;

                        //verification si partie gagné

                        if (pc1.getNombreCarte() == 0){
                            System.out.println("Le joueur " + pc1.getNom() + " a gagné");
                            break;
                        }

                        //verifie si la carte en jeu est un "passe ton tour" et on fait +2 au joueur actuel

                        if(carteEnJeu.getValeur() == 11){
                            System.out.println("le joueur " + pc2.getNom() + " passe son tour");
                            joueurActuelle = 3;
                        }

                        //cas pour la doublie pioche

                        if(carteEnJeu.getValeur() == 12){
                            System.out.println("le joueur " + pc2.getNom() + " se prend une double pioche");
                            System.out.println("Le joueur " + pc2.getNom() + " a pioché 2x");
                            pc2.piocherAdversaire();
                            pc2.piocherAdversaire();
                            joueurActuelle = 3;
                        }
                        //le pc pioche

                    } else  {
                        System.out.println("Le joueur " + pc1.getNom() + " a pioché");
                        pc1.piocherAdversaire();
                        joueurActuelle = 2;
                    }
                }



                else if (joueurActuelle == 2) {
                    System.out.println("\nC'est a " + pc2.getNom() + " de jouer");
                    if (pc2.peutJouerPc(carteEnJeu)) {
                        Carte carte_temp =  pc2.jouerPc(carteEnJeu);
                        carteEnJeu = carte_temp;
                        System.out.println("\n" + pc2.getNom() + " a joué : " + carteEnJeu.getCouleur() + "/" + carteEnJeu.getValeur());
                        joueurActuelle = 3;
                        if (pc2.getNombreCarte() == 0){
                            System.out.println("Le joueur " + pc2.getNom() + " a gagné");
                            break;
                        }
                        if(carteEnJeu.getValeur() == 11){
                            System.out.println("le joueur " + pc3.getNom() + " passe son tour");
                            joueurActuelle = 0;
                        }
                        if(carteEnJeu.getValeur() == 12){
                            System.out.println("le joueur " + pc3.getNom() + " se prend une double pioche");
                            System.out.println("Le joueur " + pc3.getNom() + " a pioché 2x");
                            pc3.piocherAdversaire();
                            pc3.piocherAdversaire();
                            joueurActuelle = 0;
                        }

                    } else  {
                        System.out.println("Le joueur " + pc2.getNom() + " a pioché");
                        pc2.piocherAdversaire();
                        joueurActuelle = 3;

                    }
                }



                else if (joueurActuelle == 3) {
                    System.out.println("\nC'est a " + pc3.getNom() + " de jouer");
                    if (pc3.peutJouerPc(carteEnJeu)) {
                        Carte carte_temp =  pc3.jouerPc(carteEnJeu);
                        carteEnJeu = carte_temp;
                        System.out.println("\n" + pc3.getNom() + " a joué : " + carteEnJeu.getCouleur() + "/" + carteEnJeu.getValeur());
                        joueurActuelle = 0;
                        if (pc3.getNombreCarte() == 0){
                            System.out.println("Le joueur " + pc3.getNom() + " a gagné");
                            break;
                        }
                        if(carteEnJeu.getValeur() == 11){
                            System.out.println("le joueur " + joueur.getNom() + " passe son tour");
                            joueurActuelle = 1;
                        }
                        if(carteEnJeu.getValeur() == 12){
                            System.out.println("le joueur " + joueur.getNom() + " se prend une double pioche");
                            System.out.println("Le joueur " + joueur.getNom() + " a pioché 2x");
                            joueur.piocherAdversaire();
                            joueur.piocherAdversaire();
                            joueurActuelle = 1;
                        }

                    } else  {
                        System.out.println("Le joueur " + pc3.getNom() + " a pioché");
                        pc3.piocherAdversaire();
                        joueurActuelle = 0;

                    }
                }
            }
        }
    }
}
