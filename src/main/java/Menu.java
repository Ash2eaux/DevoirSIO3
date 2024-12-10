import javax.annotation.processing.SupportedSourceVersion;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Menu
{
    public static void main(String[] args)
    {
        int choix;
        Scanner scanner = new Scanner(System.in);
        do
        {
            do
            {
                System.out.println("1 - Exercice 1");
                System.out.println("2 - Exercice 2");
                System.out.println("0 - Quitter");
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();
            } while (choix > 4);

            switch (choix)
            {
                case 0:
                    System.out.println("Fin du programme");
                    break;
                case 1:

                    int[] paquet = new int[]{1,2,3,4,5,6,7,8,9,10};
                    int[] paquetJoueur1 = new int[5];
                    int[] paquetJoueur2 = new int[5];
                    melangerTableau(paquet);

                    System.out.println("Voici le paquet mélangé :");
                    afficherTableau(paquet);

                    dispatcherTableau(paquet, paquetJoueur1, paquetJoueur2);

                    System.out.println("\n Voici le déroulement de la partie:");
                    deroulerBataille(paquetJoueur1, paquetJoueur2);
                    break;
                case 2:
                    String[][] genres = new String[5][2];
                    String[][] films = new String[5][4];
                    int[][] notes = new int[5][7];

                    remplirTableauxExo2(genres,films,notes);
                    int choixMenuExo2;
                    do {
                        do {
                            System.out.println("1 - Afficher informations");
                            System.out.println("2 - Noter un film");
                            System.out.println("0 - Menu principal");
                            System.out.print("Votre choix : ");
                            choixMenuExo2 = scanner.nextInt();
                        } while (choixMenuExo2 > 2);
                        switch (choixMenuExo2) {
                            case 0:
                                break;
                            case 1:
                                afficherInfos(genres, films, notes);
                                break;

                            case 2:
                                System.out.print("Entrez le numéro du film à noter : ");
                                int numeroFilm = scanner.nextInt();
                                int indiceFilm = rechercherFilm(numeroFilm, films);
                                if (indiceFilm == -1) {
                                    System.out.println("Erreur : le film n'existe pas");
                                } else {
                                    int note;
                                    do{
                                        System.out.print("Note du film: ");
                                        note = scanner.nextInt();
                                    }while (note < 1 || note >5);

                                    if (note < 1 || note > 5) {
                                    } else {
                                        int resultat = noterFilm(note, indiceFilm, notes, films);
                                        if (resultat != -1) {
                                            afficherInfos(genres,films,notes);
                                        } else {
                                            System.out.println("Impossible de noter ce film!");
                                        }
                                    }
                                }
                                break;
                        }
                    }while (choixMenuExo2 != 0);
                    break;
            }
        }while (choix != 0);
    }

    public static void remplirTableauxExo2(String[][] genres,String[][] films,int[][] notes)
    {
        genres[0] = new String[]{"1", "Genre A"};
        genres[1] = new String[]{"2", "Genre B"};
        genres[2] = new String[]{"3", "Genre C"};
        genres[3] = new String[]{"4", "Genre D"};
        genres[4] = new String[]{"5", "Genre E"};

        films[0] = new String[]{"1", "Film A", "1","21"};
        films[1] = new String[]{"2", "Film B", "2","19"};
        films[2] = new String[]{"3", "Film C", "3","24"};
        films[3] = new String[]{"4", "Film D", "4","20"};
        films[4] = new String[]{"5", "Film E", "5","14"};

        notes[0] = new int[]{5, 4, 5, 4, 3, 0, 0};
        notes[1] = new int[]{3, 4, 4, 5, 3, 0, 0};
        notes[2] = new int[]{5, 5, 5, 4, 5, 0, 0};
        notes[3] = new int[]{4, 4, 3, 4, 5, 0, 0};
        notes[4] = new int[]{3, 2, 3, 2, 4, 0, 0};
    }

    ////EXERCICE 1
    public static int[] melangerTableau(int[] tableau) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(tableau.length);
            int temp = tableau[i];
            tableau[i] = tableau[index];
            tableau[index] = temp;
        }
        return tableau;
    }

    public static void afficherTableau(int[] tableau) {
        for (int elem : tableau) {
            System.out.print(elem + "-");
        }
        System.out.println();
    }

    public static void dispatcherTableau(int[] paquet, int[] PJ1, int[] PJ2) {
        int indicePJ1 = 0, indicePJ2 = 0;
        for (int i = 0; i < paquet.length; i++) {
            if (i % 2 == 0 && indicePJ1 < PJ1.length) {
                PJ1[indicePJ1++] = paquet[i];
            } else if (i % 2 != 0 && indicePJ2 < PJ2.length) {
                PJ2[indicePJ2++] = paquet[i];
            }
        }
    }

    public static void deroulerBataille(int[] PJ1, int[] PJ2) {
        System.out.println("\nDébut du jeu !");
        System.out.println("Pli\tJoueur 1\tJoueur 2\tRésultat");
        int nbvictoire1 = 0;
        int nbvictoire2 = 0;
        for (int i = 0; i < PJ1.length; i++) {
            String message;
            if (PJ1[i] > PJ2[i]) {
                message = "Joueur 1 gagne";
                nbvictoire1 +=1;
            } else {
                message = "Joueur 2 gagne";
                nbvictoire2 +=1;
            }
            System.out.println((i + 1) + "\t" + PJ1[i] + "\t\t\t" + PJ2[i] + "\t\t\t" + message);
        }
        System.out.println("Score finale ==> Joueur 1 = " + nbvictoire1 + "  Joueurs 2 ==> " + nbvictoire2 );

    }

    //Exercice 2

        public static void afficherInfos(String[][] genres, String[][] films, int[][] notes) {
            for (int i = 0; i < films.length; i++) {
                int total = 0;
                String notesAffichage = "";

                for (int j = 0; j < notes[i].length; j++) {
                    total += notes[i][j];
                    if (notes[i][j] != 0) {
                        notesAffichage += notes[i][j] + " ";
                    }
                }
                System.out.println(films[i][0] + "\t\t" + films[i][1] + "\t" + genres[Integer.parseInt(films[i][2]) - 1][1] + "\t\t" + total + "\t\t" + notesAffichage);

            }
        }

    public static int rechercherFilm(int numeroFilm, String[][] unTableau) {
        for (int i = 0; i < unTableau.length; i++) {
            if (Integer.parseInt(unTableau[i][0]) == numeroFilm) {
                return i;
            }
        }
        return -1;
    }


    public static int trouverProchaineCaseVide(int[][] notes, int indice) {
        for (int i = 1; i < notes[indice].length; i++) {
            if (notes[indice][i] == 0) {
                return i;
            }
        }
        return -1;
    }

    //probleme de notation qui se fait selon l'indice
    public static int noterFilm(int uneNote, int numeroFilm, int[][] desNotes, String[][] desFilms) {
        int indiceFilm = rechercherFilm(numeroFilm, desFilms);
        if (indiceFilm == -1) {
            System.out.println("Film non trouvé.");
            return -1;
        }
        int ajoutNote = trouverProchaineCaseVide(desNotes, indiceFilm);
        if (ajoutNote == -1) {
            System.out.println("Impossible de noter ce film");
            return -1;
        }
        desNotes[indiceFilm][ajoutNote] = uneNote;
        return 0;
    }
    }

