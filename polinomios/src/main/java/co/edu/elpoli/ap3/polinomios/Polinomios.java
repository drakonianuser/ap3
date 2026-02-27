/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package co.edu.elpoli.ap3.polinomios;

import co.edu.elpoli.ap3.polinomios.forma1.PolinomioForma1;
import java.util.Arrays;

import javax.swing.JOptionPane;

/**
 *
 * @author sala306
 */
public class Polinomios {

    public static void main(String[] args) {
        PolinomioForma1 polinomioForma1;

        String[] vectorStrings = insertarPolinomio();
        polinomioForma1 = new PolinomioForma1(Integer.parseInt(vectorStrings[1]));
        polinomioForma1.llenarVector(vectorStrings);

        int op = 0;
        do {
            op = menu();
            switch (op) {
                case 0:
                    JOptionPane.showConfirmDialog(null, "bye bye");
                    break;
                case 1:
                    PolinomioForma1 polinomio2;
                    String[] vs2 = insertarPolinomio();
                    polinomio2 = new PolinomioForma1(Integer.parseInt(vs2[1]));
                    polinomio2.llenarVector(vs2);
                    polinomioForma1.sumar(polinomio2);
                    break;
                default:
                    JOptionPane.showConfirmDialog(null, "opcion invalida");
            }
        } while (op != 0);
    }

    public static String[] insertarPolinomio() {
        String entrada;
        do {
            entrada = JOptionPane.showInputDialog(null, "ingrese el polinomio").toLowerCase();
        } while (entrada.equals(""));
        char[] vectorChar = entrada.toCharArray();
        String[] vectorString = new String[vectorChar.length];
        StringBuilder stringAux = new StringBuilder();
        int j = 0;
        for (int i = 0; i < vectorChar.length; i++) {
            j = validarIndependiente(stringAux, vectorChar, i, vectorString, j);
            if (Character.isDigit(vectorChar[i]) || vectorChar[i] == '-') {
                stringAux.append(vectorChar[i]);
            } else {
                if (vectorChar[i] == 'x' && vectorChar[i + 1] == '^') {
                    validarTerminoUno(stringAux);
                    vectorString[j++] = stringAux.toString();
                    stringAux = new StringBuilder();
                } else if (vectorChar[i] == '^') {
                    vectorString[j++] = Character.toString(vectorChar[++i]);
                } else if (vectorChar[i] == 'x' && vectorChar[i + 1] != '^') {
                    validarTerminoUno(stringAux);
                    vectorString[j++] = stringAux.toString();
                    stringAux = new StringBuilder();
                    vectorString[j++] = "1";
                }
            }
        }
        ordenarParejas(vectorString);
        return vectorString;
    }

    private static int validarIndependiente(StringBuilder stringAux, char[] vectorChar, int i, String[] vectorString, int j) {
        if (!stringAux.isEmpty() && (vectorChar[i] == '-' || vectorChar[i] == '+')) {
            vectorString[j++] = stringAux.toString();
            vectorString[j++] = "0";
        }
        return j;
    }

    public static int menu() {
        return Integer.parseInt(JOptionPane.showInputDialog(null, """
                                                                        Escoja una de las siguientes opciones: 
                                                                        1.sumar
                                                                        2.Multiplicar
                                                                        3.Insertar Termino
                                                                        4.Eliminar Termino
                                                                        0.salir
                                                                        """));
    }

    public static void ordenarParejas(String[] vs){
        String auxCoe , auxExpo;
        for (int i = 0; i < vs.length; i++) {
            for (int j = 1 ;j < vs.length-2; j+=2) {
                if (vs[j].isEmpty() || vs[j].isBlank()){
                    break;
                }
                if (Integer.parseInt(vs[j]) < Integer.parseInt(vs[j+2])){
                    auxCoe = vs[j-1];
                    auxExpo = vs[j];
                    vs[j-1]= vs[j+1];
                    vs[j]= vs[j+2];
                    vs[j+1] = auxCoe;
                    vs[j+2]= auxExpo;
                }
            }
        }
        Arrays.stream(vs).forEach(x -> System.out.print("|" + x + "|"));
    }

    public static int numeroTerminos(String[] vs){
        int num =0 ;
        while(!vs[num].isEmpty() || !vs[num].isBlank()){
            num++;
        }
        return num;
    }

    public static void validarTerminoUno(StringBuilder aux){
        if (aux.toString().equals("-") || aux.toString().isEmpty()) {
            aux.append('1');
        }
    }
}
