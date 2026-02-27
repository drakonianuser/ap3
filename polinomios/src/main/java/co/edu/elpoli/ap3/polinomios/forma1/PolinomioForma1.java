/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.elpoli.ap3.polinomios.forma1;

/**
 *
 * @author sala306
 */
public class PolinomioForma1 {
  private int Du, VPF1[];

    public PolinomioForma1(int Du, int[] VPF1) {
        this.Du = Du;
        this.VPF1 = VPF1;
    }

    public PolinomioForma1(int grado) {
        this.Du =grado +1;
        this.VPF1 = new int[this.Du +1];
    }

    public int getDu() {
        return Du;
    }

    public void setDu(int Du) {
        this.Du = Du;
    }

    public int[] getVPF1() {
        return VPF1;
    }

    public void setVPF1(int[] VPF1) {
        this.VPF1 = VPF1;
    }

     public int getVPF1(int i) {
        return this.VPF1[i];
    }

    public void setVPF1(int i, int valor) {
        this.VPF1[i] = valor;
    }

    
    public void llenarVector(String[] vs){
       int i = 0;
        do {          
            int pos = Du - Integer.parseInt(vs[i+1]);
            setVPF1(pos, Integer.parseInt(vs[i]));
            i+=2;
        } while (!vs[i].isEmpty());
    }
    
    public void sumar(PolinomioForma1 f2){
        
    }
   
}
