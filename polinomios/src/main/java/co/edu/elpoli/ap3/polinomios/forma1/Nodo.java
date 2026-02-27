package co.edu.elpoli.ap3.polinomios.forma1;

public class Nodo {
    private int coe,exp;
    private Nodo liga;

    public Nodo(int coe, int exp) {
        this.coe = coe;
        this.exp = exp;
        this.liga = null;
    }

    public int getCoe() {
        return coe;
    }

    public void setCoe(int coe) {
        this.coe = coe;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }
}
