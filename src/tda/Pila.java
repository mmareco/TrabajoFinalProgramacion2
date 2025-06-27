package tda;
import tda.interfaces.IPila;

public class Pila<T> implements IPila<T> {

    private class Nodo {
        T elemento;
        Nodo siguiente;

        Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    private Nodo tope;
    private int tamanio;

    @Override
    public void push(T elemento) {
        Nodo nuevo = new Nodo(elemento);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamanio++;
    }

    @Override
    public T pop() {
        if (estaVacia()) return null;
        T elemento = tope.elemento;
        tope = tope.siguiente;
        tamanio--;
        return elemento;
    }

    @Override
    public boolean estaVacia() {
        return tope == null;
    }

    @Override
    public int tamanio() {
        return tamanio;
    }
}
