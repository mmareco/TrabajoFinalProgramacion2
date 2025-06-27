package modelo;

public class Emergencia {

    private int id;
    private Paciente paciente;
    private String tipoEmergencia; // descripcion hospitalaria
    private int gravedad; // define prioridad en la cola

    public Emergencia(int id, Paciente paciente, String tipoEmergencia, int gravedad) {
        this.id = id;
        this.paciente = paciente;
        this.tipoEmergencia = tipoEmergencia;
        this.gravedad = gravedad;
    }

    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getTipoEmergencia() {
        return tipoEmergencia;
    }

    public int getGravedad() {
        return gravedad;
    }

    @Override
    public String toString() {
        return "Emergencia [ID: " + id + ", Tipo: " + tipoEmergencia + ", Gravedad: " + gravedad + ", Paciente: " + paciente + "]";
    }
}
