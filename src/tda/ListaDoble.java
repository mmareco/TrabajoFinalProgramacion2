package tda;
import tda.interfaces.IListaDoble;

public class ListaDoble<T> implements IListaDoble<T> {

    private class Nodo {
        T elemento;
        Nodo anterior, siguiente;

        Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    private Nodo cabeza, cola;
    private int tamanio;

    @Override
    public void agregarInicio(T elemento) {
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
        tamanio++;
    }

    @Override
    public void agregarFin(T elemento) {
        Nodo nuevo = new Nodo(elemento);
        if (cola == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        tamanio++;
    }

    @Override
    public boolean eliminar(T elemento) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.elemento.equals(elemento)) {
                if (actual.anterior != null) actual.anterior.siguiente = actual.siguiente;
                else cabeza = actual.siguiente;

                if (actual.siguiente != null) actual.siguiente.anterior = actual.anterior;
                else cola = actual.anterior;

                tamanio--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public void mostrar() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.elemento);
            actual = actual.siguiente;
        }
    }

    @Override
    public boolean estaVacia() {
        return cabeza == null;
    }

    @Override
    public int tamanio() {
        return tamanio;
    }
}