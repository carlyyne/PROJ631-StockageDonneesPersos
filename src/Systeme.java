import java.util.*;

public class Systeme {

    private ArrayList<Utilisateur> utilisateurs;
    private ArrayList<Donnee> donnees;
    private ArrayList<NoeudSysteme> noeudSystemes;
    public Systeme (){
        this.utilisateurs = new ArrayList<>();
        this.donnees = new ArrayList<>();
        this.noeudSystemes = new ArrayList<>();
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }
    public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
    public ArrayList<Donnee> getDonnees() {
        return donnees;
    }
    public void setDonnees(ArrayList<Donnee> donnees) {
        this.donnees = donnees;
    }
    public ArrayList<NoeudSysteme> getNoeudSystemes() {
        return noeudSystemes;
    }
    public void setNoeudSystemes(ArrayList<NoeudSysteme> noeudSystemes) {
        this.noeudSystemes = noeudSystemes;
    }


    // places les donnees par ordre d'ID en prenant en compte la priorité de chaque utilisateur
    // priorité: toutes les données d'un utilisateur sont placées dans son noeud accessible si possible
    public void placementParDonnees(){
        ArrayList<Donnee> donnees = getDonnees(); // triee par id lors de sa creation
        ArrayList<Utilisateur> utilisateurs = getUtilisateurs(); // liste de tous les utilisateurs du système
        ArrayList<Utilisateur>  utilisateursContenantDonnee = new ArrayList<>();

        // parcourir une première fois les données
        for(Donnee d: donnees){
            // verifier que la donnee n'a pas de noeud système associé
            if (d.getNoeudSyst()==null){
                // parcourir les utilisateurs et vérifier si d'autres utilisateurs contiennent la même donnée
                for (Utilisateur u: utilisateurs){
                    if(u.getListeDonnees().contains(d)){
                        utilisateursContenantDonnee.add(u); // si oui: l'ajouter dans la liste utilisateursContenantDonnee
                    }
                }
                // verifier si un seul utilisateur possède la donnée
                if (utilisateursContenantDonnee.size() == 1) {
                    Utilisateur ud = utilisateursContenantDonnee.get(0); // recupérer l'utilisateur en question
                    if (ud.getNoeudSystemeAccessible().tailleAccepte(d)) { // verifier si le noeud système accessible depuis l'utilisateur peut contenir la donnée
                        // si oui: ajouter la donnée au noeud systeme et réinitialiser la liste des utilisateurs contenant la donnée
                        ud.getNoeudSystemeAccessible().ajouterDonneeNoeudSysteme(d);
                        utilisateursContenantDonnee.clear();
                    } else{
                        // si non: réinitialiser tout de même la liste: nous traitons les cas où il faut placer la donnée dans un autre noeud que le noeud accessible depuis l'utilisateur plus tard
                        utilisateursContenantDonnee.clear();
                    }
                } else {
                    // si deux utilisateurs possèdent la donnée: utiliser la méthode permettant de placer la donnée dont deux utilisateurs sont intéressés
                    placementDonneeDeuxInteresses(utilisateursContenantDonnee, d);
                    utilisateursContenantDonnee.clear();
                }
            }
        }

        //parcours des données pour la seconde fois: nous traitons les cas où il faut placer la donnée dans un autre noeud que le noeud accessible depuis l'utilisateur
        for(Donnee d: donnees) {
            if (d.getNoeudSyst()==null){
                for (Utilisateur u: utilisateurs) {
                    if (u.getListeDonnees().contains(d)) {
                        // recherche du noeud le plus proche grâce à la méthode noeudPlusProche()
                        NoeudSysteme plusProche = u.getNoeudSystemeAccessible().noeudPlusProche(d);
                        if (plusProche != null) {
                            // ajouter la donnée au noeud le plus proche si c'est possible
                            plusProche.ajouterDonneeNoeudSysteme(d);
                        }
                    }
                }
            }
        }
    }


    // place une donnee dont deux Utilisateurs sont interressés
    public void placementDonneeDeuxInteresses( ArrayList<Utilisateur> utilisateursContenantDonnee, Donnee d) {
        NoeudSysteme plusProche;
        Utilisateur utilisateur1 = utilisateursContenantDonnee.get(0);
        Utilisateur utilisateur2 = utilisateursContenantDonnee.get(1);

        // recherche le noeud le plus proche entre les deux utilisateurs
        plusProche = utilisateur1.noeudSystemePlusProcheDeuxUtilisateurs(utilisateur2,d,utilisateur1.getNoeudSystemeAccessible());

        //ajout du noeud le plus proche
        plusProche.ajouterDonneeNoeudSysteme(d);
    }

    public void placementDonneeOptimise() {
        // recuperation de toutes les données et de tous les noeuds système
        ArrayList<NoeudSysteme> noeudSysteme = getNoeudSystemes();
        ArrayList<Donnee> donnees = getDonnees();

        // tri des Noeuds Systeme (sac à dos) par ordre croissant
        noeudSysteme.sort(Comparator.comparingInt(NoeudSysteme::getCapaciteMemoire));

        // tri des donnees par ordre decroissant
        donnees.sort((d1, d2) -> Integer.compare(d2.getTaille(), d1.getTaille()));

        for (NoeudSysteme ns : noeudSysteme) {

            // ajoute la donnee dans le noeud systeme si la donnee et le noeud systeme ont la meme taille
            // Utilisation d'un Iterator pour parcourir la liste des données, afin d'éviter une ConcurrentModificationException
            for (Iterator<Donnee> iterator = donnees.iterator(); iterator.hasNext();) {
                Donnee d = iterator.next();
                if (ns.getCapaciteMemoire() == d.getTaille()) {
                    ns.ajouterDonneeNoeudSysteme(d);
                    iterator.remove(); // enlever la donnée de la liste pour éviter une ConcurrentModificationException
                }
            }

            // parcours des donnees restantes (celles qui n'avaient pas la meme taille que l'un des noeuds systemes)
            HashMap<ArrayList<Donnee>, Integer> tailles = new HashMap<>(); // liste contenant toutes les possibilités
            //for (Donnee d : donnees) {
                // création d'une liste permettant de faire des tests sur les combinaisons de donnees de capacité suffisante pour le noeud système
                ArrayList<Donnee> donneesTest = new ArrayList<>();
                // taille totale des combinaisons de données initialisée à 0
                int taille = 0;
                //parcours de toutes les données pour trouver la meilleure combinaison
                for (Donnee D : donnees) {
                    if (taille + D.getTaille() <= ns.getCapaciteMemoire()) {
                        taille += D.getTaille();
                        donneesTest.add(D);
                    }
                }
                // ajout de cette liste à la map des tailles possibles
                if (!donneesTest.isEmpty()) {
                    tailles.put(donneesTest, taille);
                }
            //}

            // recherche de la liste de données avec la taille maximale
            ArrayList<Donnee> donneesRecuperees = new ArrayList<>();
            int valeurMax = 0;
            for (Map.Entry<ArrayList<Donnee>, Integer> entry : tailles.entrySet()) {
                if (entry.getValue() > valeurMax) {
                    valeurMax = entry.getValue();
                    donneesRecuperees = entry.getKey();
                }
            }

            // ajout des données de la liste de taille minimale dans le noeud système
            for (Donnee d : donneesRecuperees) {
                ns.ajouterDonneeNoeudSysteme(d);
                donnees.remove(d);
            }
        }
    }


}
