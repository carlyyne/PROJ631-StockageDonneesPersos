


import java.util.*;

public class Dijkstra {

    public List<Arc> plusCourtChemin(NoeudSysteme ns){
        ArrayList<NoeudSysteme> noeudsSysteme = new ArrayList<>();
        ArrayList<Arc> arcsNoeudSysteme = ns.getListeArcs();
        PriorityQueue<NoeudSysteme> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.getDistance()));
        ArrayList<NoeudSysteme> visited = new ArrayList<>();

        // distance du noeud courrant à 0 et ajout dans la liste pq
        ns.setDistance(0);
        pq.add(ns);

        // Recherche des noeuds a proximite du noeud courant qui ne sont pas des Utilisateurs: ajout dans une liste de noeuds systemes
        for (Noeud n : ns.getListeNoeuds()) {
            if (n instanceof NoeudSysteme) {
                noeudsSysteme.add((NoeudSysteme) n);
            }
        }

        // ajout de tous les noeuds à la liste de prioritée
        pq.addAll(noeudsSysteme);

        // boucle tant que la PriorityQueue soit vide
        while (!pq.isEmpty()) {

            // supprime le 1er noeud (donc celui avec la plus petite distance) de la priorityQueue pq
            NoeudSysteme current = pq.poll();

            // Si le noeud a deja ete visite, on continue avec le prochain noeud
            if (visited.contains(current)) {
                continue;
            }else{
                if(current != ns) {
                    // Ajout du noeuds aux noeuds visités
                    visited.add(current);
                }
            }

            // Mise à jour des distances
            for (Arc arc : arcsNoeudSysteme) { // parcours des arcs partant du noeud systeme courrant (en entrée)
                NoeudSysteme voisin = arc.noeudDestination(ns); // pour chaque arc, récupérer le noeud destination (le voisin)
                if (voisin.getDistance() == Integer.MAX_VALUE) { // regarde pour le noeud voisin si la distance est initialisee a l'infini (le noeud n'a pas encore ete traite)
                    int distance = arc.getValeur(); // definition d'une variable distance initialisée avec la valeur de l'arc entre le noeud courrant ns et le noeud destination
                    if (distance < voisin.getDistance()) { // regarde si cette distance est inferieur à celle du noeud destination
                        voisin.setDistance(distance); // mise a jour de la distance du voisin
                    }
                    else{
                        distance = current.getDistance() + arc.getValeur();
                        if (distance < voisin.getDistance()) {
                            voisin.setDistance(distance);
                        }
                    }
                }
            }
        }

        Collections.sort(visited, new Comparator<NoeudSysteme>() {
            public int compare(NoeudSysteme n1, NoeudSysteme n2) {
                return Integer.compare(n1.getDistance(), n2.getDistance());
            }
        });

        // arc avec la plus petite distance
        ArrayList<NoeudSysteme> noeudSystemesPetiteDistance = new ArrayList<>();
        int distanceATrouvee = visited.get(0).getDistance(); // liste triee donc on prend la premiere distance qui est la plus petite
        for (NoeudSysteme n: visited){
            if (n.getDistance() == distanceATrouvee){
                noeudSystemesPetiteDistance.add(n);
            }
        }

        List<Arc> PlusCourtChemin = new ArrayList<>();

        for (NoeudSysteme node : noeudSystemesPetiteDistance) { // parcours des noeud visités avec la plus petite distance
            for (Arc arc : arcsNoeudSysteme) {
                 if (arc.noeudDestination(ns).equals(node)) {
                    PlusCourtChemin.add(arc);
                }
            }
        }

        return PlusCourtChemin;
    }
}
