/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.elpoli.ap3.polinomios.forma1;

import javax.swing.*;

/**
 *
 * @author sala306
 */
public class PolinomioForma1 {
  private int Du, VPF1[];

    public PolinomioForma1() {
        this.Du = 0;
        this.VPF1 = new int[0];
    }

    public PolinomioForma1(int grado) {
        this.Du =grado +1;
        this.VPF1 = new int[this.Du +1];
        this.VPF1[0] = grado;
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
        } while (vs[i] != null && !vs[i].isEmpty());
    }
    
    public PolinomioForma1 sumar(PolinomioForma1 f1){
        int mayor = Math.max(f1.getDu() - 1, this.getDu() - 1);
        PolinomioForma1 sf1 = new PolinomioForma1(mayor);
        int i =1 , j=1 , k=1 , expA=0,expB=0;
        while(i <= Du || j <= f1.getDu()){
            expA= getDu()-i;
            expB= f1.getDu()-j;
            if (expA == expB){
                sf1.setVPF1(k,getVPF1(i)+f1.getVPF1(j));
                i++;
                j++;
                k++;
            }else{
                if (expA>expB){
                    sf1.setVPF1(k,getVPF1(i));
                    i++;
                }else {
                    sf1.setVPF1(k,f1.getVPF1(j));
                    j++;
                }
                k++;
            }

        }
        return sf1;
    }

    public PolinomioForma1 multiplicarPolinomiosForma1(PolinomioForma1 f1){
        PolinomioForma1 mp1 = new PolinomioForma1((f1.getDu() - 1) + (getDu() - 1));
        int  expA=0,expB=0, expC,pos;
        for (int i = 1; i <= Du; i++) {
            expA= getDu()-i;
            for (int j = 1; j <= f1.Du; j++) {
                expB= f1.getDu()-j;
                expC = expA+expB;
                pos = mp1.getDu()-expC;
                mp1.setVPF1(pos, mp1.getVPF1(pos) + getVPF1(i)*f1.getVPF1(j));
            }
        }
        return mp1;
    }

    public void reconstruirPolinomio(){
        System.out.println("forma 1 :");
        for (int i = 1; i <= getDu(); i++) {
            if (getVPF1(i) == 0  )continue;
            if (getDu()-i == 0 ) {
                System.out.print("|" + getVPF1(i) + "|");
                continue;
            }
            if (getDu()-i ==1) {
                System.out.print("|"+getVPF1(i) +"x"+"|");
                continue;
            }
            System.out.print("|"+getVPF1(i) +"x^"+(getDu()-i)+"|" );
        }
    }

    public void insertarTermino(int coe, int exp) {
        if (exp < Du) {
            VPF1[Du - exp] += coe;
        } else {
            int nuevoDu = exp + 1;
            int[] nuevo = new int[nuevoDu + 1];
            int diff = nuevoDu - Du;
            for (int i = 1; i <= Du; i++) {
                nuevo[i + diff] = VPF1[i];
            }
            nuevo[1] += coe;
            VPF1 = nuevo;
            Du = nuevoDu;
        }
    }

    public void eliminarTermino(int exp) {
        if (exp < Du) {
            VPF1[Du - exp] = 0;
        }
    }

    public void redimencionar(int gradonuevo){
        PolinomioForma1 f1 = new PolinomioForma1(gradonuevo);
        for (int i = 1; i < gradonuevo; i++) {
            if (i > getDu()) break;
            f1.setVPF1(i,getVPF1(i));
        }
        setVPF1(f1.getVPF1());
        setDu(f1.Du);
    }

    public void calcularValorX(){
        int x = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el valor de x"));
        long valorFinal = 0;
        for (int i = 0; i <= getDu(); i++) {
            if (getVPF1(i) == 0  )continue;
            if (getDu()-i == 0 ) {
                System.out.println("|" + getVPF1(i) + "|");
                valorFinal+=getVPF1(i);
                continue;
            }
            if (getDu()-i ==1) {
                System.out.println("|"+getVPF1(i) +"("+x+")"+"|");
                valorFinal += ((long) getVPF1(i) * x);
                continue;
            }
            System.out.println("|"+getVPF1(i) +"("+x+")"+"^"+(getDu()-i)+"|" );
            valorFinal += (long) (getVPF1(i) * Math.pow(x,getDu()-i));
        }
        System.out.println("el valor final del polinomio es " +valorFinal);
    }
}
