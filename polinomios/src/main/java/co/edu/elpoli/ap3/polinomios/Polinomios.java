
package co.edu.elpoli.ap3.polinomios;

import co.edu.elpoli.ap3.polinomios.forma1.PolinomioForma1;
import co.edu.elpoli.ap3.polinomios.forma1.PolinomioForma2;
import co.edu.elpoli.ap3.polinomios.forma1.PolinomioForma3;

import javax.swing.*;
import java.util.Arrays;

/**
 *
 * @author sala306
 */
public class Polinomios {

    public static String[] insertarPolinomio() {
        String entrada;
        do {
            entrada = JOptionPane.showInputDialog(null, "ingrese el polinomio").toLowerCase();
        } while (entrada.isEmpty());
        char[] vectorChar = entrada.toCharArray();
        String[] vectorString = new String[vectorChar.length];
        StringBuilder stringAux = new StringBuilder();
        int j = 0;
        for (int i = 0; i < vectorChar.length; i++) {
            j = validarIndependiente(stringAux, vectorChar, i, vectorString, j);
            if (Character.isDigit(vectorChar[i]) || vectorChar[i] == '-') {
                stringAux.append(vectorChar[i]);
            } else {
                if (vectorChar[i] == 'x' && i + 1 < vectorChar.length && vectorChar[i + 1] == '^') {
                    validarTerminoUno(stringAux);
                    vectorString[j++] = stringAux.toString();
                    stringAux = new StringBuilder();
                } else if (vectorChar[i] == '^') {
                    StringBuilder expStr = new StringBuilder();
                    while (i + 1 < vectorChar.length && Character.isDigit(vectorChar[i + 1])) {
                        expStr.append(vectorChar[++i]);
                    }
                    vectorString[j++] = expStr.toString();
                } else if (vectorChar[i] == 'x' && (i + 1 >= vectorChar.length || vectorChar[i + 1] != '^')) {
                    validarTerminoUno(stringAux);
                    vectorString[j++] = stringAux.toString();
                    stringAux = new StringBuilder();
                    vectorString[j++] = "1";
                }
            }
        }
        if (!stringAux.isEmpty()) {
            vectorString[j++] = stringAux.toString();
            vectorString[j] = "0";
        }
        System.out.println();
        Arrays.stream(vectorString).forEach(x -> System.out.print("|" + x + "|"));
        ordenarParejas(vectorString);
        return vectorString;
    }

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
                    int temp1 = Integer.parseInt(JOptionPane.showInputDialog(null, """ 
                            Elige una de las opciones:
                            1.sumar polinomios de la misma forma
                            2. sumar F2+F3
                            0. atras.
                            """
                ));
                    if(temp1 == 1){
                        PolinomioForma1 polinomio2;
                        String[] vs2 = insertarPolinomio();
                        polinomio2 = new PolinomioForma1(Integer.parseInt(vs2[1]));
                        polinomio2.llenarVector(vs2);
                        PolinomioForma1 sf1 = polinomioForma1.sumar(polinomio2);
                        System.out.println();
                        Arrays.stream(sf1.getVPF1()).forEach(x -> System.out.print("|" + x + "|"));

                        break;
                    } else if (temp1 == 2) {
                        PolinomioForma2 F2;
                        PolinomioForma3 PF3_1 = new PolinomioForma3();
                        String[] VSF3_1 = insertarPolinomio();
                        PF3_1.llenarPolinomio(VSF3_1);

                        String[] VS2_1 = insertarPolinomio();
                        F2 = new PolinomioForma2(VS2_1.length);
                        F2.llenarPolinomio(VS2_1);
                        F2.sumarConFormaTres(PF3_1);

                    }else{
                        break;
                    }
                    break;

                case 2:
                    int temp = Integer.parseInt(JOptionPane.showInputDialog(null, """ 
                            Elige una de las opciones:
                            1.multiplicar polinomios de la misma forma
                            2. multiplicar F3*F1
                            0. atras.
                            """
                    ));
                    if (temp == 1) {
                        PolinomioForma1 PF1_2;
                        String[] VS3 = insertarPolinomio();
                        PF1_2 = new PolinomioForma1(Integer.parseInt(VS3[1]));
                        PF1_2.llenarVector(VS3);
                        PF1_2 = polinomioForma1.multiplicarPolinomiosForma1(PF1_2);
                        Arrays.stream(PF1_2.getVPF1()).forEach(x -> System.out.print("|" + x + "|"));

                    } else if (temp == 2) {
                        PolinomioForma3 PF3 = new PolinomioForma3();
                        String[] VSF3 = insertarPolinomio();
                        PF3.llenarPolinomio(VSF3);
                        PolinomioForma2 f2 = PF3.multiplicarPorForma1(polinomioForma1);
                        Arrays.stream(f2.getVPF2()).forEach(x -> System.out.print("|" + x + "|"));
                    } else {
                        break;
                    }
                    break;
                case 3:
                    int coe = Integer.parseInt(JOptionPane.showInputDialog(null, "ingresa el nuevo coeficiente"));
                    int exp = Integer.parseInt(JOptionPane.showInputDialog(null, "ingresa el exponente"));
                    polinomioForma1.insertarTermino(coe,exp);
                    break;
                case 4:
                    int expeliminar = Integer.parseInt(JOptionPane.showInputDialog(null, "ingresa el exponente a liminar"));
                    polinomioForma1.eliminarTermino(expeliminar);
                    break;
                case 5:
                    polinomioForma1.reconstruirPolinomio();
                    break;
                case 6:
                    polinomioForma1.calcularValorX();
                    break;
                default:
                    JOptionPane.showConfirmDialog(null, "opcion invalida");
            }
        } while (op != 0);
    }

    public static int menu() {
        return Integer.parseInt(JOptionPane.showInputDialog(null, """
                Escoja una de las siguientes opciones: 
                1.sumar
                2.Multiplicar
                3.Insertar Termino
                4.Eliminar Termino
                5. Reconstruir polinomio
                6. evaluar x 
                0.salir
                """));
    }

    public static int numeroTerminos(String[] vs) {
        int num = 0;
        while (!vs[num].isEmpty() || !vs[num].isBlank()) {
            num++;
        }
        return num;
    }

    public static void ordenarParejas(String[] vs) {
        String auxCoe, auxExpo;
        for (int i = 0; i < vs.length; i++) {
            for (int j = 1; j < vs.length - 2; j += 2) {
                if (vs[j] == null || vs[j].isBlank() || vs[j + 2] == null || vs[j + 2].isBlank()) {
                    break;
                }
                if (Integer.parseInt(vs[j]) < Integer.parseInt(vs[j + 2])) {
                    auxCoe = vs[j - 1];
                    auxExpo = vs[j];
                    vs[j - 1] = vs[j + 1];
                    vs[j] = vs[j + 2];
                    vs[j + 1] = auxCoe;
                    vs[j + 2] = auxExpo;
                }
            }
        }
        System.out.println();
        Arrays.stream(vs).forEach(x -> System.out.print("|" + x + "|"));
    }

    private static int validarIndependiente(StringBuilder stringAux, char[] vectorChar, int i, String[] vectorString, int j) {
        if (!stringAux.isEmpty() && (vectorChar[i] == '-' || vectorChar[i] == '+')) {
            vectorString[j++] = stringAux.toString();
            vectorString[j++] = "0";
            stringAux.setLength(0);
        }
        return j;
    }

    public static void validarTerminoUno(StringBuilder aux) {
        if (aux.toString().equals("-") || aux.toString().isEmpty()) {
            aux.append('1');
        }
    }
}
