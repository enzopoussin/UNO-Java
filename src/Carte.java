import java.util.Random;

public class Carte {
    private String couleur;
    private int valeur;


    //Constructeur

    public Carte() {
        Random aleatoire = new Random();
        int aleatoireCouleur = aleatoire.nextInt(4);
        if (aleatoireCouleur == 0) {
            this.couleur = "jaune";
        }else if (aleatoireCouleur == 1) {
            this.couleur = "rouge";
        }else if (aleatoireCouleur == 2) {
            this.couleur = "vert";
        }else{
            this.couleur = "bleu";
        }
        int aleatoireValeur = aleatoire.nextInt(1,13);
        if (aleatoireValeur == 1) {
            this.valeur = aleatoireValeur;
        }else if (aleatoireValeur == 2) {
            this.valeur = aleatoireValeur;
        }else if (aleatoireValeur == 3) {
            this.valeur = aleatoireValeur;
        }else if (aleatoireValeur == 4) {
            this.valeur = aleatoireValeur;
        }else if (aleatoireValeur == 5) {
            this.valeur = aleatoireValeur;
        }else if (aleatoireValeur == 6) {
            this.valeur = aleatoireValeur;
        }else if (aleatoireValeur == 7) {
            this.valeur = aleatoireValeur;
        } else if (aleatoireValeur == 8) {
            this.valeur = aleatoireValeur;
        }else if (aleatoireValeur == 9) {
            this.valeur = aleatoireValeur;
        } else if (aleatoireValeur == 10){
            this.valeur = aleatoireValeur;
        }else if (aleatoireValeur == 11) {
            this.valeur = aleatoireValeur;
        }else if (aleatoireValeur == 12) {
            this.valeur = aleatoireValeur;
        }

    }

    //Methodes

    public void  presenterCarte() {

        //permet d'affichier le nom de la valeur pour les cartes changement de sens | saut de tour | double pioche :

        if (this.valeur == 10) {
            System.out.println(this.couleur + "/changement de sens");
        } else if (this.valeur == 11) {
            System.out.println(this.couleur + "/saut de tour");
        }else if (this.valeur == 12) {
            System.out.println(this.couleur + "/double pioche");
        }else{
            System.out.println(this.couleur + "/" + this.valeur);
        }
    }

    public int getValeur() {
        //recupere la valeur de la carte
        return valeur;
    }

    public String getCouleur() {
        //permet de recupére la coueleur de la carte
        return couleur;
    }
}
