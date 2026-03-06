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
        if (this.cabeza==null){
            this.cabeza = nodo;
        }else{
              while (p.getLiga() != null) {
                  p = p.getLiga();
              }
              p.setLiga(nodo);
        }
    }

    public void insertarTermino(int coe, int exp) {
        Nodo nuevo = new Nodo(coe, exp);
        if (cabeza == null || exp > cabeza.getExp()) {
            nuevo.setLiga(cabeza);
            cabeza = nuevo;
            return;
        }
        if (cabeza.getExp() == exp) {
            cabeza.setCoe(cabeza.getCoe() + coe);
            return;
        }
        Nodo p = cabeza;
        while (p.getLiga() != null && p.getLiga().getExp() > exp) {
            p = p.getLiga();
        }
        if (p.getLiga() != null && p.getLiga().getExp() == exp) {
            p.getLiga().setCoe(p.getLiga().getCoe() + coe);
        } else {
            nuevo.setLiga(p.getLiga());
            p.setLiga(nuevo);
        }
    }

    public void eliminarTermino(int exp) {
        if (cabeza == null) return;
        if (cabeza.getExp() == exp) {
            cabeza = cabeza.getLiga();
            return;
        }
        Nodo p = cabeza;
        while (p.getLiga() != null && p.getLiga().getExp() != exp) {
            p = p.getLiga();
        }
        if (p.getLiga() != null) {
            p.setLiga(p.getLiga().getLiga());
        }
    }

    public void llenarPolinomio(String[] vs){
        for (int i = 0; i < vs.length; i++) {
            if (vs[i] == null || vs[i].isBlank()) break;
            insertarAlFinal(Integer.parseInt(vs[i]),Integer.parseInt(vs[++i]));
        }
    }

    public PolinomioForma2 multiplicarPorForma1(PolinomioForma1 f1){
        PolinomioForma2 f2 = new PolinomioForma2(0);
        for (int i = 1; i <= f1.getDu(); i++) {
            if (f1.getVPF1(i) == 0) continue;
            Nodo p = getCabeza();
            while (p != null) {
                int mult = f1.getVPF1(i) * p.getCoe();
                int multExp = (f1.getDu() - i) + p.getExp();
                f2.insertarTermino(mult, multExp);
                p = p.getLiga();
            }
        }
        return f2;
    }

}
