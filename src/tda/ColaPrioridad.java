package tda;
import tda.interfaces.IColaPrioridad;

public class ColaPrioridad<T> implements IColaPrioridad<T> {

    private class Nodo {
        T elemento;
        int prioridad;
        Nodo siguiente;

        Nodo(T elemento, int prioridad) {
            this.elemento = elemento;
            this.prioridad = prioridad;
        }
    }

    private Nodo frente;
    private int tamanio;

    @Override
    public void insertar(T elemento, int prioridad) {
        Nodo nuevo = new Nodo(elemento, prioridad);
        if (frente == null || prioridad > frente.prioridad) {
            nuevo.siguiente = frente;
            frente = nuevo;
        } else {
            Nodo actual = frente;
            while (actual.siguiente != null && actual.siguiente.prioridad >= prioridad) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamanio++;
    }

    @Override
    public T atender() {
        if (estaVacia()) return null;
        T elemento = frente.elemento;
        frente = frente.siguiente;
        tamanio--;
        return elemento;
    }

    @Override
    public boolean estaVacia() {
        return frente == null;
    }

    @Override
    public int tamanio() {
        return tamanio;
    }

    @Override
    public void mostrar() {
        Nodo actual = frente;
        while (actual != null) {
            System.out.println(actual.elemento + " | Prioridad: " + actual.prioridad);
            actual = actual.siguiente;
        }
    }
}