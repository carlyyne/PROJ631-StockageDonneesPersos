import java.util.ArrayList;
import java.util.List;

public class NoeudSysteme extends Noeud{
    private int capaciteMemoire;
    private ArrayList<Noeud> listeNoeuds;
    private ArrayList<Arc> listeArcs;
    private int distance;

    public NoeudSysteme(int id, int capaciteMemoire) {
        super(id);
        this.capaciteMemoire = capaciteMemoire;
        this.listeDonnees = new ArrayList<>();
        this.listeArcs =  new ArrayList<>();
        this.distance = Integer.MAX_VALUE;
    }

    // Getters and setters
    public int getId(){return this.id;}
    public ArrayList<Donnee> getDonnees() {return listeDonnees;}

    public void setListeDonnees(ArrayList<Donnee> listeDonnees){
        this.listeDonnees= listeDonnees;
    }
    public int getCapaciteMemoire() {
        return capaciteMemoire;
    }
    public void setCapaciteMemoire(int capaciteMemoire) {
        this.capaciteMemoire = capaciteMemoire;
    }
    public ArrayList<Noeud> getListeNoeuds() {
        return listeNoeuds;
    }
    public void setListeNoeuds(ArrayList<Noeud> listeNoeuds) {
        this.listeNoeuds = listeNoeuds;
    }

    public ArrayList<Arc> getListeArcs() {
        return listeArcs;
    }

    public void setListeArcs(ArrayList<Arc> listeArcs) {
        this.listeArcs = listeArcs;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    //indique si il y a de la place disponible dans un noeud pour stocker certaines données
    public boolean tailleAccepte(Donnee d){
        return this.getCapaciteMemoire() >= d.getTaille();
    }

    // Enregistrer l'emplacement des données d'un noeud système
    public void ajouterDonneeNoeudSysteme (Donnee d){
        // ajout de la nouvelle donnée à la liste des données du noeud système
        ArrayList<Donnee> donneesSysteme = this.getDonnees();
        donneesSysteme.add(d);
        this.setListeDonnees(donneesSysteme);

        // met à jour le noeud système de la donnée
        d.setNoeudSyst(this);

        // modifie la capacité du noeud système
        this.setCapaciteMemoire(this.getCapaciteMemoire() - d.getTaille());
    }

    // cherche le noeud le plus proche d'un noeud systeme pouvant contenir la donnee
    public NoeudSysteme noeudPlusProche(Donnee d){
        NoeudSysteme plusProche = null;

        if (this!=null) {

            Dijkstra dijkstra = new Dijkstra();
            ArrayList<NoeudSysteme> noeudsSysteme = new ArrayList<>();

            //determination des chemins les plus cours
            List<Arc> arcs = dijkstra.plusCourtChemin(this);


            // Recherche des noeuds a proximite du noeud courant qui ne sont pas des Utilisateurs: creation d'une liste de noeud systeme
            for (Noeud n : this.getListeNoeuds()) {
                if (n instanceof NoeudSysteme) {
                    noeudsSysteme.add((NoeudSysteme) n);
                }
            }

            //parmis les chemins les plus courts (arcs) on regarde quel noeud destination peut contenir la donnée
            for (Arc a : arcs) {
                if (a.noeudDestination(this).getCapaciteMemoire() < d.getTaille()) {
                    noeudsSysteme.remove(a.noeudDestination(this)); // enleve les noeuds systèmes qui ont déjà été traités
                }else{
                    plusProche = a.noeudDestination(this);
                    break;
                }
            }

            // si aucun noeud parmis ceux les plus proches ne peut contenir la donnee on parcours les autres noeuds systèmes
            if (plusProche == null) {
                for (NoeudSysteme n : noeudsSysteme) {
                    // le noeud peut contenir la donnée
                    if (n.tailleAccepte(d)) {
                        plusProche = n;
                    }
                }
            }
        } return plusProche;
    }

    @Override
    public String toString() {
        return "NoeudSysteme{" +
                "capaciteMemoire=" + capaciteMemoire +
                ", id=" + id +
                ", listeDonnees=" + listeDonnees +
                ", listeArcs=" + listeArcs +
                ", Distance=" + distance +
                '}';
    }
}
