package co.edu.elpoli.ap3.polinomios.forma1;

public class PolinomioForma2 {
    private int Du, VPF2[];

    public PolinomioForma2(int terminos) {
        Du = terminos * 2;
        this.VPF2 = new int[Du+1];
        VPF2[0] = terminos;
    }

    public int getDu() {
        return Du;
    }

    public void setDu(int du) {
        Du = du;
    }

    public int[] getVPF2() {
        return VPF2;
    }

    public void setVPF2(int[] VPF2) {
        this.VPF2 = VPF2;
    }

    public int getVPF2(int pos) {
        return VPF2[pos];
    }

    public void setVPF2(int pos,int valor) {
        this.VPF2[pos] = valor;
    }

    public void llenarPolinomio(String[] vs){
        for (int i = 0; i < vs.length; i++) {
            if (vs[i] == null || vs[i].isBlank()) break;
            this.setVPF2(i+1,Integer.parseInt(vs[i]));
        }
    }

    public void insertarTermino(int coe, int exp){
        for (int j = 2; j <= this.Du; j += 2) {
            if (VPF2[j] == exp) {
                VPF2[j - 1] += coe;
                return;
            }
        }

        int[] VPF2Aux = new int[Du + 3];
        VPF2Aux[0] = VPF2[0] + 1;

        for (int j = 2; j <= this.Du; j += 2) {
            if (VPF2[j] < exp) {
                System.arraycopy(VPF2, 1, VPF2Aux, 1, j - 2);
                VPF2Aux[j - 1] = coe;
                VPF2Aux[j] = exp;
                System.arraycopy(VPF2, j - 1, VPF2Aux, j + 1, Du - j + 2);
                VPF2 = VPF2Aux;
                Du += 2;
                return;
            }
        }

        System.arraycopy(VPF2, 1, VPF2Aux, 1, Du);
        VPF2Aux[Du + 1] = coe;
        VPF2Aux[Du + 2] = exp;
        VPF2 = VPF2Aux;
        Du += 2;
    }

    public void eliminarTermino(int exp) {
        for (int j = 2; j <= Du; j += 2) {
            if (VPF2[j] == exp) {
                int[] nuevo = new int[Du - 1];
                nuevo[0] = VPF2[0] - 1;
                System.arraycopy(VPF2, 1, nuevo, 1, j - 2);
                System.arraycopy(VPF2, j + 1, nuevo, j - 1, Du - j);
                VPF2 = nuevo;
                Du -= 2;
                return;
            }
        }
    }

    public PolinomioForma2 sumarPolinomiof2(PolinomioForma2 polinomioForma2) {
        PolinomioForma2 aux = new PolinomioForma2(VPF2[0]);
        aux.setVPF2(this.VPF2);
        for (int i = 1; i <= polinomioForma2.Du; i += 2) {
            aux.insertarTermino(polinomioForma2.getVPF2(i), polinomioForma2.getVPF2(i + 1));
        }
        return aux;
    }
    
    public PolinomioForma1 sumarConFormaTres(PolinomioForma3 f3){
        int i =2 , pos , mayor;
        Nodo p = f3.getCabeza();
        mayor = Math.max(getVPF2(i),p.getExp());
        PolinomioForma1 f1 = new PolinomioForma1(mayor);
        while (i<=getDu() && p != null){
            if (getVPF2(i)==p.getExp()){
                pos = f1.getDu()- getVPF2(i);
                f1.setVPF1(pos,getVPF2(i-1)+ p.getCoe());
                i+= 2;
                p = p.getLiga();
            }else {
                if (getVPF2(i)>p.getExp()){
                    pos= f1.getDu() - getVPF2(i);
                    f1.setVPF1(pos, getVPF2(i-1));
                    i += 2;
                } else {
                    pos= f1.getDu() - p.getExp();
                    f1.setVPF1(pos, p.getCoe());
                    p = p.getLiga();
                }
            }
        }
        while (i <= getDu()) {
            pos = f1.getDu() - getVPF2(i);
            f1.setVPF1(pos, getVPF2(i - 1));
            i += 2;
        }
        while (p != null) {
            pos = f1.getDu() - p.getExp();
            f1.setVPF1(pos, p.getCoe());
            p = p.getLiga();
        }
        System.out.println("forma1");
        for (int j = 0; j <= f1.getDu(); j++) {
            System.out.print("|"+f1.getVPF1(j)+"|");
        }
        return f1;
    }
            

}
