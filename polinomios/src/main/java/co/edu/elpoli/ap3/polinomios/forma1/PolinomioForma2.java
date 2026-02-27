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
        for (int i = 0; i <= vs.length; i++) {
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

    public PolinomioForma2 sumarPolinomiof2(PolinomioForma2 polinomioForma2) {
        PolinomioForma2 aux = new PolinomioForma2(VPF2[0]);
        aux.setVPF2(this.VPF2);
        for (int i = 1; i <= polinomioForma2.Du; i += 2) {
            aux.insertarTermino(polinomioForma2.getVPF2(i), polinomioForma2.getVPF2(i + 1));
        }
        return aux;
    }

}
