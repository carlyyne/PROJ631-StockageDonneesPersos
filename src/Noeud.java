import java.util.ArrayList;

public class Noeud {
    protected int id;
    protected ArrayList<Donnee> listeDonnees = new ArrayList<>();
    public Noeud(int id){
        this.id =id;
    }
    public Noeud(int id,ArrayList<Donnee> listeDonnees){
        this.id =id;
        this.listeDonnees = listeDonnees;
    }
}
