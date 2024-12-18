import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Carte> cartes = new ArrayList<>();



    //Les constructeurs

    public Player(String name) {
        if (name.length() >= 3 && name.length() <= 20) {
            this.name = name;
        }else {
            this.name = "player";
        }

        for(int i = 0; i < 10; i++){
            //creer le set de cartes du joueur
            Carte nouvelleCarte = new Carte();
            cartes.add(nouvelleCarte);
        }
    }
    public Player(int choix) {
        if (choix == 0){
            this.name = "Virtuel 1";
            for(int i = 0; i < 10; i++) {
                //creer le set de cartes du joueur
                Carte nouvelleCarte = new Carte();
                cartes.add(nouvelleCarte);
            }
        }else if(choix == 1){
            this.name = "Virtuel 2";
            for(int i = 0; i < 10; i++) {
                //creer le set de cartes du joueur
                Carte nouvelleCarte = new Carte();
                cartes.add(nouvelleCarte);
            }
        } else if (choix == 2) {
            this.name = "Virtuel 3";
            for(int i = 0; i < 10; i++) {
                //creer le set de cartes du joueur
                Carte nouvelleCarte = new Carte();
                cartes.add(nouvelleCarte);
            }
        }
    }

    //Méthodes

    public void presenterJoueur(){
        System.out.println(this.name + " Woah quel beau pseudo... \n");
        System.out.println("voici tes cartes : " );
        for (int i = 0 ; i < this.cartes.size(); i++){
            Carte carteActuelle = this.cartes.get(i);
            carteActuelle.presenterCarte();
        }
    }

    public void presenterAdversaire(){
        System.out.println(this.name + ". Et possède " + getNombreCarte() + " carte.");
    }

    public int getNombreCarte(){
        return this.cartes.size();
    }

    public String getNom(){
        return this.name;
    }

    public void getToutesCartes(){
        for (int i = 0 ; i < this.cartes.size(); i++) {

            //affiche toutes les cartes pour le joueur

            Carte carteActuelle = cartes.get(i);
            if (carteActuelle.getValeur() == 10) {
                System.out.println(carteActuelle.getCouleur() + "/changement de sens" + " --> " + (i+1));
            } else if (carteActuelle.getValeur() == 11) {
                System.out.println(carteActuelle.getCouleur() + "/saut de tour" + " --> " + (i+1));
            }else if (carteActuelle.getValeur() == 12) {
                System.out.println(carteActuelle.getCouleur() + "/double pioche" + " --> " + (i+1));
            }else{
                System.out.println(carteActuelle.getCouleur() + "/" + carteActuelle.getValeur() + " --> " + (i+1));
            }
        }
    }

    public void piocher(){

        //permet de generer une carte pour la pioche du joueur

        Carte pioche = new Carte();
        this.cartes.add(pioche);
        System.out.println(this.name + " a pioché :");
        pioche.presenterCarte();
    }

    public void piocherAdversaire(){
            Carte pioche = new Carte();
            this.cartes.add(pioche);
    }

    public boolean peutJouer(int index, Carte carteActuelle){

        //permet de verifier que le joueur peux jouer

        if(carteActuelle.getValeur() == this.cartes.get(index-1).getValeur() || carteActuelle.getCouleur() == this.cartes.get(index-1).getCouleur()){
            return true;
        }
        return false;
    }
    public boolean peutJouerPc(Carte carteActuelle){

        //permet de vérifier si le joueur virtuel à une carte qui le permet de jouer

        for(int i = 0 ; i < this.cartes.size(); i++){
            if(carteActuelle.getValeur() == this.cartes.get(i).getValeur() || carteActuelle.getCouleur().equals(this.cartes.get(i).getCouleur())) {
                return true;
            }
        }
        return false;
    }
    public Carte jouerPc(Carte carteActuelle) {

        //permet au joueur de jouer

        int index = 0;
        for (int i = 0; i < this.cartes.size(); i++) {
            if (carteActuelle.getValeur() == this.cartes.get(i).getValeur() || carteActuelle.getCouleur() == this.cartes.get(i).getCouleur()) {
                carteActuelle = this.cartes.get(i);
                index = i;
                break;
            }
        }

        //suppression de la carte joué dans la main du jooueur

        this.cartes.remove(index);
        return carteActuelle;
    }

    public Carte jouer(int index, Carte carteActuelle){

        //le -1 nous permet d'avoir l'index de l'éléments car un tableau commence à l'index 0

        carteActuelle = this.cartes.get(index - 1);
        this.cartes.remove(index - 1);
        return carteActuelle;
    }
}
