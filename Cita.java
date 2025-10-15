package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Info del paciente
public class Cita {
    private String idCita;
    private String nombrePaciente;
    private TrabajadorMedico medicoAsignado;
    private LocalDateTime fechaHora;
    private String tipoCita;
    private String estado;
    private List<String> historialCambios = new ArrayList<>();

    public Cita(String idCita, String nombrePaciente, TrabajadorMedico medicoAsignado,
                LocalDateTime fechaHora, String tipoCita, String estado) {
        this.idCita = idCita;
        this.nombrePaciente = nombrePaciente;
        this.medicoAsignado = medicoAsignado;
        this.fechaHora = fechaHora;
        this.tipoCita = tipoCita;
        this.estado = estado;
    }

    
     /**
     @param nuevaFecha
     */
     
    public void reagendar(LocalDateTime nuevaFecha) {
        historialCambios.add("Reagendada de " + fechaHora + " a " + nuevaFecha);
        this.fechaHora = nuevaFecha;
        this.estado = "REAGENDADA";
    }

    public String getIdCita() { return idCita; }
    public TrabajadorMedico getMedicoAsignado() { return medicoAsignado; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public List<String> getHistorialCambios() { return historialCambios; }

    @Override
    public String toString() {
        return "Cita " + idCita + " [" + tipoCita + "] - Paciente: " + nombrePaciente +
               " | Médico: " + medicoAsignado.getNombre() + " | Estado: " + estado +
               " | Fecha: " + fechaHora;
    }
}
