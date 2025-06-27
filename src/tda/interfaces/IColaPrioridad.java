package tda.interfaces;

public interface IColaPrioridad<T> {
    void insertar(T elemento, int prioridad);
    T atender();
    boolean estaVacia();
    int tamanio();
    void mostrar();
}
