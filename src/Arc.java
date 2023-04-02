public class Arc {
    private final int valeur;
    private NoeudSysteme noeudDepart;
    private NoeudSysteme noeudArrive;
    public Arc(int valeur,NoeudSysteme noeudDepart,NoeudSysteme noeudArrive){
        this.valeur=valeur;
        this.noeudDepart = noeudDepart;
        this.noeudArrive = noeudArrive;
    }

    public int getValeur() {
        return valeur;
    }

    public NoeudSysteme noeudDestination(NoeudSysteme noeudCourant){
        NoeudSysteme noeudDestination = this.noeudArrive;
        if (noeudDestination == noeudCourant){
            noeudDestination = this.noeudDepart;
        }
        return noeudDestination;
    }

    @Override
    public String toString() {
        return "Arc{" +
                "valeur=" + valeur +
                ", IDnoeudDepart=" + noeudDepart.getId() +
                ", IDnoeudArrive=" + noeudArrive.getId() +
                '}';
    }
}
