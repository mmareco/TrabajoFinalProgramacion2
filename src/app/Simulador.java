package app;

import modelo.Paciente;
import modelo.Emergencia;
import tda.ColaPrioridad;
import tda.ListaDoble;
import tda.Pila;
import tda.interfaces.IColaPrioridad;
import tda.interfaces.IListaDoble;
import tda.interfaces.IPila;

import java.util.Scanner;

public class Simulador {

    private static final Scanner sc = new Scanner(System.in);
    private static final IColaPrioridad<Emergencia> cola = new ColaPrioridad<>();
    private static final IListaDoble<Emergencia> historial = new ListaDoble<>();
    private static final IPila<Emergencia> pilaDeshacer = new Pila<>();
    private static int contadorEmergencias = 1;

    public static void main(String[] args) {

        Paciente p1 = new Paciente("12345678", "Juan Perez", 35);
        Paciente p2 = new Paciente("87654321", "Maria Lopez", 42);
        Paciente p3 = new Paciente("11223344", "Carlos Gomez", 29);

        Emergencia e1 = new Emergencia(contadorEmergencias++, p1, "Trauma", 8);
        historial.agregarFin(e1);
        pilaDeshacer.push(e1);
        Emergencia e2 = new Emergencia(contadorEmergencias++, p2, "Cardiaca", 10);
        historial.agregarFin(e2);
        pilaDeshacer.push(e2);
        Emergencia e3 = new Emergencia(contadorEmergencias++, p3, "Respiratoria", 6);
        historial.agregarFin(e3);
        pilaDeshacer.push(e3);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            System.out.println();
            switch (opcion) {
                case 1 -> registrarEmergencia();
                case 2 -> atenderEmergencia();
                case 3 -> verHistorial();
                case 4 -> deshacerUltimaAtencion();
                case 5 -> buscarPaciente();
                case 6 -> verEstadoCola();
                case 7 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida. Intente nuevamente.\n");
            }
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("*** Sistema de Gestion de Emergencias Medicas ***");
        System.out.println("1. Registrar llegada de paciente");
        System.out.println("2. Atender proxima emergencia");
        System.out.println("3. Ver historial de pacientes atendidos");
        System.out.println("4. Deshacer ultima atencion");
        System.out.println("5. Buscar paciente por DNI");
        System.out.println("6. Ver estado de la cola de emergencias");
        System.out.println("7. Salir");
    }

    private static void registrarEmergencia() {
        System.out.println("--- Registro de nueva emergencia ---");
        String dni = leerTexto("DNI del paciente: ");
        String nombre = leerTexto("Nombre del paciente: ");
        int edad = leerEntero("Edad del paciente: ");
        String tipo = leerTexto("Tipo de emergencia (Trauma, Cardiaca, etc.): ");
        int gravedad = leerEntero("Gravedad (1 a 10, siendo 10 la mayor gravedad): ");

        Paciente paciente = new Paciente(dni, nombre, edad);
        Emergencia emergencia = new Emergencia(contadorEmergencias++, paciente, tipo, gravedad);
        cola.insertar(emergencia, gravedad);

        System.out.println("Emergencia registrada exitosamente.\n");
    }

    private static void atenderEmergencia() {
        System.out.println("--- Atendiendo proxima emergencia ---");
        if (cola.estaVacia()) {
            System.out.println("No hay emergencias en la cola.\n");
            return;
        }
        Emergencia e = cola.atender();
        historial.agregarFin(e);
        pilaDeshacer.push(e);
        System.out.println("Se atendio la siguiente emergencia:");
        System.out.println(e);
        System.out.println();
    }

    private static void verHistorial() {
        System.out.println("--- Historial de emergencias atendidas ---");
        if (historial.estaVacia()) {
            System.out.println("No se han atendido emergencias aun.\n");
            return;
        }
        historial.mostrar();
        System.out.println();
    }

    private static void deshacerUltimaAtencion() {
        System.out.println("--- Deshacer ultima atencion ---");
        if (pilaDeshacer.estaVacia()) {
            System.out.println("No hay acciones para deshacer.\n");
            return;
        }
        Emergencia e = pilaDeshacer.pop();
        historial.eliminar(e);
        cola.insertar(e, e.getGravedad());
        System.out.println("Se deshizo la atencion de la siguiente emergencia:");
        System.out.println(e);
        System.out.println();
    }

    private static void buscarPaciente() {
        System.out.println("--- Buscar paciente por DNI ---");
        String dni = leerTexto("Ingrese el DNI a buscar: ");

        System.out.println("Buscando en la cola de emergencias...");
        boolean encontrado = false;

        IColaPrioridad<Emergencia> colaAux = new ColaPrioridad<>();

        while (!cola.estaVacia()) {
            Emergencia e = cola.atender();
            if (e.getPaciente().getDni().equals(dni)) {
                System.out.println("Paciente encontrado en la cola:");
                System.out.println(e);
                encontrado = true;
            }
            colaAux.insertar(e, e.getGravedad());
        }

        // Restaurar la cola original
        while (!colaAux.estaVacia()) {
            Emergencia emergenciaAux = colaAux.atender();
            cola.insertar(emergenciaAux, emergenciaAux.getGravedad());
        }

        if (!encontrado) System.out.println("Paciente no encontrado en la cola.");

        System.out.println("\nBuscando en el historial de atendidos...");
        historial.mostrar();
        System.out.println();
    }

    private static void verEstadoCola() {
        System.out.println("--- Estado actual de la cola de emergencias ---");
        if (cola.estaVacia()) {
            System.out.println("No hay emergencias en espera.\n");
            return;
        }
        cola.mostrar();
        System.out.println();
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.print("Por favor ingrese un numero valido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine(); // limpia el buffer, saca el salto de linea que queda despues de leer
        return valor;
    }
}
