package tda.interfaces;

public interface IPila<T> {
    void push(T elemento);
    T pop();
    boolean estaVacia();
    int tamanio();
}
