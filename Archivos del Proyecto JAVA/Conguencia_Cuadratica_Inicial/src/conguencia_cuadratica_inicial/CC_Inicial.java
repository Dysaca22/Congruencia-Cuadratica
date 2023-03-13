package conguencia_cuadratica_inicial;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @Universidad del Norte
 * @Abril 05 del 2021
 * @NRC: 8055
 * @COD: 200085471
 * @Author: Dylan Cantillo
 */
public class Conguencia_Cuadratica_Inicial {

    /**
     * Procedimeinto main, donde se leen los valores de entrada. También,
     * muestra el tiempo de demora del algoritmo.
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese a: ");
        BigInteger a = new BigInteger(sc.next());

        System.out.println("Ingrese m: ");
        BigInteger p = new BigInteger(sc.next());

        boolean existencia = congruQuadr(a, p);

        if (!existencia) {
            System.out.println("No existe una respuesta");
        } else {
            System.out.println("Si existe un x, tal que x^2 (mod " + p + ") = " + a);
        }
    }

    /*
    Algoritmo iterativo para grandes valores de entrada donde se va probando
    uno por uno los posibles valores de x<max, comprobando su existencia, donde x=√(mk+a).

    El algoritmo devuelve las posibles respuestas que puede tomar x\<max,
    sin embargo, si no hay posibles respuestas este devolverá una cadena vacía.
     */
    private static boolean congruQuadr(BigInteger a, BigInteger m) {
        BigInteger x = BigInteger.ONE;
        BigInteger potencia = x.pow(2);

        while (x.compareTo(m) == -1 && potencia.mod(m).compareTo(a) != 0) {
            x = x.add(BigInteger.ONE);
            potencia = x.pow(2);
        }

        return potencia.mod(m).compareTo(a) == 0;
    }

}
