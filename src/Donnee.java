public class Donnee {

    private final int id;
    private final int taille;
    private NoeudSysteme noeudSyst;// noeud Systeme dans lequel la donnee est stockee
    public Donnee(int id, int taille){
        this.id = id;
        this.taille = taille;
    }

    // Getters and setters
    public int getTaille() { return taille; }
    public NoeudSysteme getNoeudSyst() {
        return noeudSyst;
    }
    public void setNoeudSyst(NoeudSysteme noeudSyst) {
        this.noeudSyst = noeudSyst;
    }

    @Override
    public String toString() {
        return "Donnee{" +
                "id=" + id +
                ", taille=" + taille +
                //", id noeud systeme=" + noeudSyst.getId() +
                '}';
    }
}
