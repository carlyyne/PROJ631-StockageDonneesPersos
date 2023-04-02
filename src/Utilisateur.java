import java.util.ArrayList;
public class Utilisateur extends Noeud{
    private NoeudSysteme noeudSystemeAccessible;

    public Utilisateur(int id, ArrayList<Donnee> listeDonnees) {
        super(id,listeDonnees);
    }

    // Getters and setters necessaires
    public ArrayList<Donnee> getListeDonnees() { return listeDonnees; }
    public NoeudSysteme getNoeudSystemeAccessible() { return noeudSystemeAccessible; }
    public void setNoeudSystemeAccessible(NoeudSysteme noeudSystemeAccessible) { this.noeudSystemeAccessible = noeudSystemeAccessible;}
    public int distance (NoeudSysteme n){
        /*Calcul la distance entre un utilisateur et un noeud systeme*/
        
        int distance = 0;

        if (this.noeudSystemeAccessible.getDistance() == Integer.MAX_VALUE) {
            // distance entre l'utilisateur et son noeud accessible
            distance = 2; // on initialise 2 comme car l'utilisateur est a une distance de 2 par rapport a son noeud accessible (voir schema)
        }
        // si le noeud accessible n'est pas le noeud n en paramètre
        if (this.noeudSystemeAccessible != n){
            ArrayList<Arc> arcs = this.noeudSystemeAccessible.getListeArcs();

            for(Arc a: arcs){
                NoeudSysteme noeudDestination = a.noeudDestination(this.noeudSystemeAccessible);
                if (noeudDestination == n){
                    distance += a.getValeur();
                }
            }
        }

        return distance;
    }

    // cherche le noeud qui minimise le temps d’acces pour deux utilisateurs
    // noeudPlusOptimal = noeud accessible depuis l'utilisateur au depart
    public NoeudSysteme noeudSystemePlusProcheDeuxUtilisateurs(Utilisateur u2, Donnee d, NoeudSysteme noeudPlusOptimal) {
        NoeudSysteme optimal = noeudPlusOptimal;
        int distanceTotale = Integer.MAX_VALUE;

        // calcul distance entre noeudPlusProcheU1 et l'utilisateur u1
        int distanceU1 = this.distance(noeudPlusOptimal);

        // calcul distance entre noeudPlusProcheU1 et l'utilisateur u2
        int distanceU2 = u2.distance(noeudPlusOptimal);

        // recuperation des Arcs du noeud le plus proche de u1
        ArrayList<Arc> arcs = noeudPlusOptimal.getListeArcs();

        //calcul distance totale si le noeudPlusOptimal à de la place
        if(optimal.tailleAccepte(d)) {
            distanceTotale = distanceU1 + distanceU2;
        }
        // parcours des arcs pour trouver les noeuds destinations (depart: noeud le plus proche de u1) pouvant stocker la donnée
        for(Arc a: arcs){

            // verification du bon placement
/*            if (noeudPlusOptimal.tailleAccepte(d) && (distanceU1 + distanceU2 < distanceTotale) ){
                optimal = noeudPlusOptimal;
                distanceTotale = distanceU1 + distanceU2;
            } else {
*/
                NoeudSysteme noeudDestination = a.noeudDestination(noeudPlusOptimal);

                if (d.getTaille() <= noeudDestination.getCapaciteMemoire()) {

                    // calcul nouvelle distance entre noeudPlusProcheU1 et l'utilisateur u1
                    distanceU1 = this.distance(noeudDestination);

                    // calcul distance entre noeudPlusProcheU1 et l'utilisateur u2
                    distanceU2 = u2.distance(noeudDestination);

                    if (distanceU1 + distanceU2 < distanceTotale){
                        distanceTotale = distanceU1 + distanceU2;
                        optimal = noeudDestination;
                    }
            }
        }
        return optimal;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idNoeudSystemeAccessible=" + noeudSystemeAccessible.getId() +
                ", id=" + id +
                ", listeDonnees=" + listeDonnees +
                '}';
    }
}
