package tda.interfaces;

public interface IListaDoble<T> {
    void agregarInicio(T elemento);
    void agregarFin(T elemento);
    boolean eliminar(T elemento);
    void mostrar();
    boolean estaVacia();
    int tamanio();
}
