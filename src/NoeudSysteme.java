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

    // Enregistrer l'emplacement des données
    public void ajouterDonneeNoeudSysteme (Donnee d){
        if (this!=null) {
            ArrayList<Donnee> donneesSysteme = this.getDonnees();
            donneesSysteme.add(d);
            this.setListeDonnees(donneesSysteme);
            d.setNoeudSyst(this);
            this.setCapaciteMemoire(this.getCapaciteMemoire() - d.getTaille());
        }
    }

    // cherche le noeud le plus proche d'un noeud systeme pouvant contenur la donnee
    public NoeudSysteme noeudPlusProche(Donnee d,ArrayList<NoeudSysteme> noeudSystemesDejaVu){
        NoeudSysteme plusProche = null;

        if (this!=null) {

            Dijkstra dijkstra = new Dijkstra();
            ArrayList<NoeudSysteme> noeudsSysteme = new ArrayList<>();
            List<Arc> arcs = dijkstra.plusCourtChemin(this);


            // Recherche des noeuds a proximite du noeud courant qui ne sont pas des Utilisateurs: creation d'une liste de noeud systeme
            for (Noeud n : this.getListeNoeuds()) {
                if (n instanceof NoeudSysteme) {
                    noeudsSysteme.add((NoeudSysteme) n);
                }
            }

            //on regarde si la donnée peut être contenue dans le noeud
            for (Arc a : arcs) {
                if (a.noeudDestination(this).getCapaciteMemoire() >= d.getTaille()) {
                    plusProche = a.noeudDestination(this);
                    plusProche.setDistance(1); // mise a jour de la distance du noeud le plus proche
                }
            }

            if (plusProche == null) {
                for (NoeudSysteme n : noeudsSysteme) {
                    if (!(n.getCapaciteMemoire() >= d.getTaille()) && !noeudSystemesDejaVu.contains(n)) {
                        noeudSystemesDejaVu.add(n);
                        dijkstra.plusCourtChemin(n);
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
