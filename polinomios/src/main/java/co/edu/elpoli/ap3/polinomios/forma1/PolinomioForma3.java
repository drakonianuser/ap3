package co.edu.elpoli.ap3.polinomios.forma1;

public class PolinomioForma3 {
    private Nodo cabeza;

    public PolinomioForma3() {
        this.cabeza =null;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public void insertarAlFinal(int coe,int exp){
        Nodo p =cabeza,nodo =new Nodo(coe,exp);
        if (this.cabeza!=null){
            this.cabeza = nodo;
        }else{
              while (p.getLiga() != null) {
                  p = p.getLiga();
              }
              p.setLiga(nodo);
        }
    }

    public void llenarPolinomio(String[] vs){
        for (int i = 0; i < vs.length; i=+2) {
            if (vs[i].isEmpty()) {
                break;
            }
            insertarAlFinal(Integer.parseInt(vs[i]),Integer.parseInt(vs[++i]));
        }
    }

}
