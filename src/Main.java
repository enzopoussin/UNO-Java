import java.util.Scanner;

//classe main

public class Main {
    public static void main(String[] args) {
       Scanner entreeClavier = new Scanner(System.in);
       System.out.println("Bienvenue dans le UNO. La partie va commencer veuillez entrer votre pseudo. ");


       //boucle pour faire relancer la partie

       while (true){
           System.out.println("Nom du joueur : ");
           String nomJoueur = entreeClavier.nextLine();
           Partie partie = new Partie(nomJoueur);
           partie.presenterPartie();
           partie.lancerPartie();
       }
    }
}