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
        ArrayList<Utilisateur> utilisateurs = getUtilisateurs();
        ArrayList<Utilisateur>  utilisateursContenantDonnee = new ArrayList<>();

        for(Donnee d: donnees){
            if (d.getNoeudSyst()==null){
                for (Utilisateur u: utilisateurs){
                    if(u.getListeDonnees().contains(d)){
                        utilisateursContenantDonnee.add(u);
                    }
                }
                if (utilisateursContenantDonnee.size() == 1) {
                    Utilisateur ud = utilisateursContenantDonnee.get(0);
                    if (ud.getNoeudSystemeAccessible().tailleAccepte(d)) {
                        ud.getNoeudSystemeAccessible().ajouterDonneeNoeudSysteme(d);
                        utilisateursContenantDonnee.remove(ud);
                    } else{
                        utilisateursContenantDonnee.remove(ud);
                    }
                } else {
                    placementDonneeDeuxInteresses(utilisateursContenantDonnee, d);
                    utilisateursContenantDonnee.clear();
                }
            }
        }

        for(Donnee d: donnees) {
            if (d.getNoeudSyst()==null){
                for (Utilisateur u: utilisateurs) {
                    if (u.getListeDonnees().contains(d)) {
                        ArrayList<NoeudSysteme> noeudSystemesDejaVu = new ArrayList<>();
                        NoeudSysteme plusProche = u.getNoeudSystemeAccessible().noeudPlusProche(d,noeudSystemesDejaVu);
                        if (plusProche != null) {
                            plusProche.ajouterDonneeNoeudSysteme(d);
                        }
                    }
                }
            }
        }
    }


    // place une donnee dont deux Utilisateurs sont interresés
    public void placementDonneeDeuxInteresses( ArrayList<Utilisateur> utilisateursContenantDonnee, Donnee d) {
        NoeudSysteme plusProche;
        Utilisateur utilisateur1 = utilisateursContenantDonnee.get(0);
        Utilisateur utilisateur2 = utilisateursContenantDonnee.get(1);
        plusProche = utilisateur1.noeudSystemePlusProcheDeuxUtilisateurs(utilisateur2,d,utilisateur1.getNoeudSystemeAccessible());
        plusProche.ajouterDonneeNoeudSysteme(d);
    }

    public void placementDonneeOptimise() {
        ArrayList<NoeudSysteme> noeudSysteme = getNoeudSystemes();
        ArrayList<Donnee> donnees = getDonnees();

        // tri des Noeuds Systeme (sac à dos) par ordre croissant
        Collections.sort(noeudSysteme, new Comparator<NoeudSysteme>() {
            public int compare(NoeudSysteme n1, NoeudSysteme n2) {
                return Integer.compare(n1.getCapaciteMemoire(), n2.getCapaciteMemoire());
            }
        });

        // tri des donnees par ordre decroissant
        Collections.sort(donnees, new Comparator<Donnee>() {
            public int compare(Donnee d1, Donnee d2) {
                return Integer.compare(d2.getTaille(), d1.getTaille());
            }
        });

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
            for (Donnee d : donnees) {
                // création d'une liste de donnees de capacité suffisante pour le noeud système
                ArrayList<Donnee> donneesTest = new ArrayList<>();
                int taille = 0;
                for (Donnee d2 : donnees) {
                    if (taille + d2.getTaille() <= ns.getCapaciteMemoire()) {
                        taille += d2.getTaille();
                        donneesTest.add(d2);
                    }
                }
                // ajout de cette liste à la map des tailles possibles
                if (!donneesTest.isEmpty()) {
                    tailles.put(donneesTest, taille);
                }
            }

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
