import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        /***************************************** QUESTION 1 *****************************************/

        // initialisation des elements necessaires à partir des classes demandées (et certains ajouts)
        // Donnees
        Donnee d0 = new Donnee(0,10);
        Donnee d1 = new Donnee(1,5);
        Donnee d2 = new Donnee(2,20);
        Donnee d3 = new Donnee(3,15);
        Donnee d4 = new Donnee(4,5);
        Donnee d5 = new Donnee(5,10);
        Donnee d6 = new Donnee(6,30);
        Donnee d7 = new Donnee(7,20);
        Donnee d8 = new Donnee(8,10);
        ArrayList<Donnee> donneesSysteme = new ArrayList<>();
        donneesSysteme.add(d0);
        donneesSysteme.add(d1);
        donneesSysteme.add(d2);
        donneesSysteme.add(d3);
        donneesSysteme.add(d4);
        donneesSysteme.add(d5);
        donneesSysteme.add(d6);
        donneesSysteme.add(d7);
        donneesSysteme.add(d8);

        // Listes de donnees appartenant aux utilisateurs
        ArrayList<Donnee> listeDonneesUtilisateur1 = new ArrayList<>();
        ArrayList<Donnee> listeDonneesUtilisateur2 = new ArrayList<>();
        ArrayList<Donnee> listeDonneesUtilisateur3 = new ArrayList<>();
        ArrayList<Donnee> listeDonneesUtilisateur4 = new ArrayList<>();
        ArrayList<Donnee> listeDonneesUtilisateur5 = new ArrayList<>();
        ArrayList<Donnee> listeDonneesUtilisateur6 = new ArrayList<>();
        ArrayList<Donnee> listeDonneesUtilisateur7 = new ArrayList<>();
        listeDonneesUtilisateur1.add(d0);
        listeDonneesUtilisateur2.add(d1);
        listeDonneesUtilisateur2.add(d2);
        listeDonneesUtilisateur3.add(d3);
        listeDonneesUtilisateur3.add(d5);
        listeDonneesUtilisateur4.add(d4);
        listeDonneesUtilisateur4.add(d7); // modification question 3
        listeDonneesUtilisateur5.add(d6);
        listeDonneesUtilisateur6.add(d8);
        listeDonneesUtilisateur7.add(d7);

        // Utilisateurs
        Utilisateur u1 = new Utilisateur(1,listeDonneesUtilisateur1);
        Utilisateur u2 = new Utilisateur(2,listeDonneesUtilisateur2);
        Utilisateur u3 = new Utilisateur(3,listeDonneesUtilisateur3);
        Utilisateur u4 = new Utilisateur(4,listeDonneesUtilisateur4);
        Utilisateur u5 = new Utilisateur(5,listeDonneesUtilisateur5);
        Utilisateur u6 = new Utilisateur(6,listeDonneesUtilisateur6);
        Utilisateur u7 = new Utilisateur(7,listeDonneesUtilisateur7);
        ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<>();
        listeUtilisateurs.add(u1);
        listeUtilisateurs.add(u2);
        listeUtilisateurs.add(u3);
        listeUtilisateurs.add(u4);
        listeUtilisateurs.add(u5);
        listeUtilisateurs.add(u6);
        listeUtilisateurs.add(u7);

        // Noeuds systeme
        NoeudSysteme n1 = new NoeudSysteme(1,50);
        NoeudSysteme n2 = new NoeudSysteme(2,5);
        NoeudSysteme n3 = new NoeudSysteme(3,50);
        NoeudSysteme n4 = new NoeudSysteme(4,40);
        NoeudSysteme n5 = new NoeudSysteme(5,5);
        //NoeudSysteme n6 = new NoeudSysteme(6,40);
        ArrayList<NoeudSysteme> listeNoeudsSysteme = new ArrayList<>();
        listeNoeudsSysteme.add(n1);
        listeNoeudsSysteme.add(n2);
        listeNoeudsSysteme.add(n3);
        listeNoeudsSysteme.add(n4);
        listeNoeudsSysteme.add(n5);
        //listeNoeudsSysteme.add(n6);

        // Association d'un noeud systeme à un utilisateur
        u1.setNoeudSystemeAccessible(n1);
        u2.setNoeudSystemeAccessible(n1);
        u3.setNoeudSystemeAccessible(n1);
        u4.setNoeudSystemeAccessible(n2);
        u5.setNoeudSystemeAccessible(n3);
        u6.setNoeudSystemeAccessible(n4);
        u7.setNoeudSystemeAccessible(n4);

        // Arcs
        Arc a1 = new Arc(1,n1,n5);
        Arc a2 = new Arc(3,n1,n4);
        Arc a3 = new Arc(3,n1,n3);
        Arc a4 = new Arc(1,n1,n2);
        Arc a5 = new Arc(3,n2,n5);
        Arc a6 = new Arc(3,n3,n5);
        Arc a7 = new Arc(1,n4,n5);
        Arc a8 = new Arc(3,n2,n4);
        Arc a9 = new Arc(1,n3,n4);
        Arc a10 = new Arc(1,n2,n3);
        /*Arc a1 = new Arc(1,n1,n5);
        Arc a2 = new Arc(3,n1,n4);
        Arc a3 = new Arc(3,n1,n3);
        Arc a4 = new Arc(1,n1,n2);
        Arc a5 = new Arc(3,n2,n5);
        Arc a6 = new Arc(3,n3,n5);
        Arc a7 = new Arc(1,n4,n5);
        Arc a8 = new Arc(3,n2,n4);
        Arc a9 = new Arc(3,n4,n3);
        Arc a10 = new Arc(1,n2,n3);
        Arc a11 = new Arc(1,n6,n3);
        Arc a12 = new Arc(3,n6,n2);
        Arc a13 = new Arc(3,n6,n1);
        Arc a14 = new Arc(3,n6,n5);
        Arc a15 = new Arc(1,n6,n4);*/

        //Définition des arcs accessibles depuis un noeud systeme
        ArrayList<Arc> listeArcs1 = new ArrayList<>();
        ArrayList<Arc> listeArcs2 = new ArrayList<>();
        ArrayList<Arc> listeArcs3 = new ArrayList<>();
        ArrayList<Arc> listeArcs4 = new ArrayList<>();
        ArrayList<Arc> listeArcs5 = new ArrayList<>();
        ArrayList<Arc> listeArcs6 = new ArrayList<>();
        listeArcs1.add(a1);
        listeArcs1.add(a2);
        listeArcs1.add(a3);
        listeArcs1.add(a4);
        //listeArcs1.add(a13);
        listeArcs2.add(a4);
        listeArcs2.add(a5);
        listeArcs2.add(a8);
        listeArcs2.add(a10);
        //listeArcs2.add(a12);
        listeArcs3.add(a3);
        listeArcs3.add(a6);
        listeArcs3.add(a9);
        listeArcs3.add(a10);
        //listeArcs3.add(a11);
        listeArcs4.add(a2);
        listeArcs4.add(a7);
        listeArcs4.add(a8);
        listeArcs4.add(a9);
        //listeArcs4.add(a15);
        listeArcs5.add(a1);
        listeArcs5.add(a5);
        listeArcs5.add(a6);
        listeArcs5.add(a7);
        //listeArcs5.add(a14);
        //listeArcs6.add(a11);
        //listeArcs6.add(a12);
        //listeArcs6.add(a13);
        //listeArcs6.add(a14);
        //listeArcs6.add(a15);

        n1.setListeArcs(listeArcs1);
        n2.setListeArcs(listeArcs2);
        n3.setListeArcs(listeArcs3);
        n4.setListeArcs(listeArcs4);
        n5.setListeArcs(listeArcs5);
        //n6.setListeArcs(listeArcs6);

        //Définition des noeuds accessibles depuis un noeud systeme
        ArrayList<Noeud> listeNoeuds1 = new ArrayList<>();
        ArrayList<Noeud> listeNoeuds2 = new ArrayList<>();
        ArrayList<Noeud> listeNoeuds3 = new ArrayList<>();
        ArrayList<Noeud> listeNoeuds4 = new ArrayList<>();
        ArrayList<Noeud> listeNoeuds5 = new ArrayList<>();
        ArrayList<Noeud> listeNoeuds6 = new ArrayList<>();
        listeNoeuds1.add(u1);
        listeNoeuds1.add(u2);
        listeNoeuds1.add(u3);
        listeNoeuds1.add(n2);
        listeNoeuds1.add(n3);
        listeNoeuds1.add(n4);
        listeNoeuds1.add(n5);
        //listeNoeuds1.add(n6);
        listeNoeuds2.add(u4);
        listeNoeuds2.add(n1);
        listeNoeuds2.add(n3);
        listeNoeuds2.add(n4);
        listeNoeuds2.add(n5);
        //listeNoeuds2.add(n6);
        listeNoeuds3.add(u5);
        listeNoeuds3.add(n1);
        listeNoeuds3.add(n2);
        listeNoeuds3.add(n4);
        listeNoeuds3.add(n5);
        //listeNoeuds3.add(n6);
        listeNoeuds4.add(u6);
        listeNoeuds4.add(u7);
        listeNoeuds4.add(n1);
        listeNoeuds4.add(n2);
        listeNoeuds4.add(n3);
        listeNoeuds4.add(n5);
        //listeNoeuds4.add(n6);
        listeNoeuds5.add(n1);
        listeNoeuds5.add(n2);
        listeNoeuds5.add(n3);
        listeNoeuds5.add(n4);
        //listeNoeuds5.add(n6);
        listeNoeuds6.add(n1);
        listeNoeuds6.add(n2);
        listeNoeuds6.add(n3);
        listeNoeuds6.add(n4);
        listeNoeuds6.add(n5);

        n1.setListeNoeuds(listeNoeuds1);
        n2.setListeNoeuds(listeNoeuds2);
        n3.setListeNoeuds(listeNoeuds3);
        n4.setListeNoeuds(listeNoeuds4);
        n5.setListeNoeuds(listeNoeuds5);
        //n6.setListeNoeuds(listeNoeuds6);

        // Systeme
        Systeme systeme = new Systeme();
        systeme.setUtilisateurs(listeUtilisateurs);
        systeme.setDonnees(donneesSysteme);
        systeme.setNoeudSystemes(listeNoeudsSysteme);

        /***************************************** QUESTION 2/3/4 *****************************************/

        // Affichage du MENU
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu:");
        System.out.println("1 - Placement de données au plus près des utilisateurs (deux utilisateurs peuvent être intéressés par la même donnée)");
        System.out.println("2 - Placement de données permettant de repartir efficacement l’espace disponible");
        System.out.print("Entrez votre choix (1 ou 2):");
        Integer choix = Integer.valueOf(scanner.nextLine());
        if (choix==1) {
            systeme.placementParDonnees();
        }
        if (choix==2){
            systeme.placementDonneeOptimise();
        }
        scanner.close();

        // Verifications des placements
        System.out.println("Données Noeud Système 1: " + n1.getDonnees());
        System.out.println("Capacité restante du Noeud Système 1: " +  n1.getCapaciteMemoire());
        System.out.println("Données Noeud Système 2: " + n2.getDonnees());
        System.out.println("Capacité restante du Noeud Système 2: " + n2.getCapaciteMemoire());
        System.out.println("Données Noeud Système 3: " + n3.getDonnees());
        System.out.println("Capacité restante du Noeud Système 3: " +  n3.getCapaciteMemoire());
        System.out.println("Données Noeud Système 4: " + n4.getDonnees());
        System.out.println("Capacité restante du Noeud Système 4: " + n4.getCapaciteMemoire());
        System.out.println("Données Noeud Système 5: " + n5.getDonnees());
        System.out.println("Capacité restante du Noeud Système 5: " + n5.getCapaciteMemoire());
        //System.out.println("Données Noeud Système 6: " + n6.getDonnees());
        //System.out.println("Capacité restante du Noeud Système 6: " + n6.getCapaciteMemoire());

    }
}

