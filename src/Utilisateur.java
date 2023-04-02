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

    //Calcul la distance entre un utilisateur et un noeud systeme
    public int distance (NoeudSysteme n){
        int distance = 2; // l'utilisateur est a une distance de 2 par rapport a son noeud accessible (comme schema)

        // cas où le noeud accessible depuis l'utilisateur n'est pas le noeud n en paramètre
        if (!(this.noeudSystemeAccessible == n)) {

            ArrayList<Arc> arcs = this.noeudSystemeAccessible.getListeArcs(); // recuperation de tous les arcs dont le noeud systeme accessible à l'utilisateur fait partit

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

            NoeudSysteme noeudDestination = a.noeudDestination(noeudPlusOptimal);

            if (noeudDestination.tailleAccepte(d)) {

                // calcul nouvelle distance entre noeudPlusProcheU1 et l'utilisateur u1
                distanceU1 = this.distance(noeudDestination);

                // calcul distance entre noeudPlusProcheU1 et l'utilisateur u2
                distanceU2 = u2.distance(noeudDestination);

                // changement de la distance totale et du noeud optimal si la distance entre les deux utilisateurs est inferieur à l'ancienne
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
