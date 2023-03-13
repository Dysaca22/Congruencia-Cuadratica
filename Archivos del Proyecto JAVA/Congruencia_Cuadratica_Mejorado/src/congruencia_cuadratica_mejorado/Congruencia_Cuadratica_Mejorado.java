package congruencia_cuadratica_mejorado;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @Universidad del Norte
 * @Abril 05 del 2021
 * @NRC: 8055
 * @COD: 200085471
 * @Author: Dylan Cantillo
 */
public class Congruencia_Cuadratica_Mejorado {

    private static BigInteger expoA, expoP, gcd;
    private static final BigInteger DOS = new BigInteger(2 + "");
    private static final BigInteger CUATRO = new BigInteger(4 + "");
    private static final BigInteger OCHO = new BigInteger(8 + "");

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Ecuación general : x^2 = a (mod p)");
        System.out.println("");
        System.out.println("Ingrese a (residuo)");
        BigInteger a = new BigInteger(sc.next());
        System.out.println("Ingrese p (modular)");
        BigInteger p = new BigInteger(sc.next());
        System.out.println("");

        String existencia = inicial(a, p);
        System.out.println(existencia);
    }

    private static String inicial(BigInteger a, BigInteger p) {

        String respuesta = "";

        String[] factores = factoresPrimos(p).split(" ");

        for (String factor : factores) {
            BigInteger f = new BigInteger(factor);
            if (!congruenciaCuadratica(a, f)) {
                respuesta = "No existe una respuesta";
                break;
            }
        }

        if (respuesta.equals("")) {
            respuesta = "Si existe un x, tal que x^2 (mod " + p + ") = " + a;
        }
        return respuesta;
    }

    private static boolean congruenciaCuadratica(BigInteger a, BigInteger p) {

        a = a.mod(p);
        if (a.compareTo(BigInteger.ZERO) == 0) {
            return true;
        } else if (a.compareTo(BigInteger.ONE) == 0) {
            return true;
        } else {
            gcd = maximoComunDivisor(a, p);
            if (gcd.compareTo(BigInteger.ONE) == 0) {
                if (p.mod(DOS).compareTo(BigInteger.ONE) == 0) {
                    if (legendre(a, new BigInteger(encontrarBase(p) + "")) == 1) {
                        return true;
                    }
                } else {
                    if (a.compareTo(BigInteger.ONE) == 0 && p.compareTo(DOS) == 0) {
                        return true;
                    } else if (a.compareTo(BigInteger.ONE) == 0 && p.compareTo(CUATRO) == 0) {
                        return true;
                    } else {
                        if (a.mod(OCHO).compareTo(BigInteger.ONE) == 0) {
                            return true;
                        }
                    }
                }
            } else {
                expoA = encontrarPotencia(gcd);
                expoP = encontrarPotencia(p);
                if (expoA.mod(DOS).compareTo(BigInteger.ONE) == 0) {
                    return false;
                } else {
                    if (p.mod(DOS).compareTo(BigInteger.ONE) == 0) {
                        return true;
                    } else if (expoP.subtract(expoA).compareTo(DOS) == 0) {
                        return true;
                    } else if (expoP.subtract(expoA).compareTo(CUATRO) == 0) {
                        return true;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static String factoresPrimos(BigInteger numero) {
        String resp = "";
        int cont;
        for (BigInteger i = DOS; i.compareTo(numero) != 1; i = i.add(BigInteger.ONE)) {
            cont = 0;
            while (numero.mod(i).compareTo(BigInteger.ZERO) == 0) {
                numero = numero.divide(i);
                cont++;
                if (numero.compareTo(BigInteger.ONE) == 0) {
                    break;
                }
            }
            if (cont != 0) {
                resp += i.pow(cont) + " ";
            }
        }
        return resp;
    }

    private static int legendre(BigInteger a, BigInteger p) {
        BigInteger num = (p.subtract(BigInteger.ONE)).divide(DOS);

        int contador = 0;
        for (BigInteger i = BigInteger.ONE; i.compareTo(num) != 1; i = i.add(BigInteger.ONE)) {
            if ((a.multiply(i)).mod(p).compareTo(num) == 1) {
                contador++;
            }
        }
        return (int) Math.pow(-1, contador);
    }

    private static BigInteger maximoComunDivisor(BigInteger a, BigInteger p) {

        if (p.compareTo(BigInteger.ZERO) == 0) {
            return a;
        } else {
            return maximoComunDivisor(p, a.mod(p));
        }
    }

    private static BigInteger encontrarPotencia(BigInteger numero) {
        int base = 0;
        int numeroDigitos = (numero + "").length();

        base = encontrarBase(numero);

        int valorInferior, valorSuperior;
        valorInferior = (int) ((numeroDigitos - 1) / Math.log10(base));
        valorSuperior = (int) (numeroDigitos / Math.log10(base));

        for (int i = valorInferior; i <= valorSuperior; i++) {
            if (new BigInteger(base + "").pow(i).compareTo(numero) == 0) {
                return new BigInteger(i + "");
            }
        }
        return BigInteger.ZERO;
    }

    private static int encontrarBase(BigInteger numero) {
        BigInteger i = new BigInteger("2");
        while (numero.mod(i).compareTo(BigInteger.ZERO) != 0) {
            i = i.add(BigInteger.ONE);
        }
        return Integer.parseInt(i.toString());
    }
}
