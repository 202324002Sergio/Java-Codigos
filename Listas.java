package Listas;

import java.util.Scanner;

public class Listas {

    private int tam;
    private int cont;
    private String[] lista;

    public Listas(int tam) {
        this.tam = tam;
        this.lista = new String[tam];
        this.cont = 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa el tamano de la lista: ");
        int t = sc.nextInt();

        Listas so = new Listas(t);

        int opt;
        do {
            System.out.println("1 - Add");
            System.out.println("2 - Delete");
            System.out.println("3 - Edit");
            System.out.println("4 - Show");
            System.out.println("5 - Exit");
            System.out.print("Opcion: ");
            opt = sc.nextInt();

            switch (opt) {
                case 1:
                    System.out.print("Valor a agregar: ");
                    String valor = sc.next();
                    so.agregar(valor);
                    break;
                case 2:
                    so.eliminar();
                    break;
                case 3:
                    System.out.print("Posicion a editar: ");
                    int pos = sc.nextInt();
                    System.out.print("Nuevo valor: ");
                    String nuevo = sc.next();
                    so.editar(pos, nuevo);
                    break;
                case 4:
                    so.mostrar();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion inválida.");
            }
        } while (opt != 5);

        sc.close();
    }

    public void agregar(String p) {
        if (cont < lista.length) {
            lista[cont] = p;
            System.out.println("Dato agregado: " + p + " en pos: " + cont);
            cont++;
        } else {
            System.out.println("Error: la lista está llena.");
        }
    }

    public void eliminar() {
        if (cont == 0) {
            System.out.println("Error: la lista esta vacía, no se puede eliminar.");
            return;
        }

        System.out.println("Dato eliminado: " + lista[0]);
        for (int i = 0; i < cont - 1; i++) {
            lista[i] = lista[i + 1];
        }
        lista[cont - 1] = null; 
        cont--;
    }

    public void editar(int pos, String p) {
        if (pos >= 0 && pos < cont) {
            System.out.println("Dato en posición " + pos + " cambiado de " + lista[pos] + " a " + p);
            lista[pos] = p;
        } else {
            System.out.println("Error: posicion invalida.");
        }
    }

    public void mostrar() {
        if (cont == 0) {
            System.out.println("La lista está vacia.");
            return;
        }

        System.out.println("\nContenido de la lista:");
        for (int i = 0; i < cont; i++) {
            System.out.println("[" + i + "] " + lista[i]);
        }
    }
}

