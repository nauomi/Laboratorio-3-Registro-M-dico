package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.*;


public class HospitalManager {
    private final List<TrabajadorMedico> personal = new ArrayList<>();
    private final List<Cita> citas = new ArrayList<>();

    // Agrega un nuevo trabajador médico a la lista
    public void registrarTrabajador(TrabajadorMedico t) {
        personal.add(t);
    }

    //Registra una nueva cita médica
    public void registrarCita(Cita c) {
        citas.add(c);
    }

    // Busca un trabajador médico por su ID
    public TrabajadorMedico buscarPorId(String id) {
        for (TrabajadorMedico t : personal) {
            if (t.getIdEmpleado().equals(id)) return t;
        }
        return null;
    }


     // Reagenda una cita a una nueva fecha si no hay conflicto
     
    public boolean reagendarCita(String idCita, LocalDateTime nuevaFecha) {
        for (Cita c : citas) {
            if (c.getIdCita().equals(idCita)) {
                if (hayConflicto(c.getMedicoAsignado(), nuevaFecha)) {
                    LocalDateTime sugerida = nuevaFecha.plusHours(1);
                    c.reagendar(sugerida);
                } else {
                    c.reagendar(nuevaFecha);
                }
                return true;
            }
        }
        return false;
    }

    // Verifica si un médico tiene otra cita en la misma fecha/hora
    private boolean hayConflicto(TrabajadorMedico medico, LocalDateTime fecha) {
        for (Cita c : citas) {
            if (c.getMedicoAsignado().equals(medico) &&
                c.getFechaHora().equals(fecha)) {
                return true;
            }
        }
        return false;
    }

    //Devuelve una lista con la información de todo el personal
    public List<String> reportePersonal() {
        List<String> r = new ArrayList<>();
        for (TrabajadorMedico t : personal) {
            r.add(t.toString());
        }
        return r;
    }

    //Devuelve una lista con las citas registradas
    public List<String> reporteCitas() {
        List<String> r = new ArrayList<>();
        for (Cita c : citas) {
            r.add(c.toString());
        }
        return r;
    }

    //Calcula la nómina de todos los trabajadores del hospital
    public List<String> generarNomina() {
        List<String> r = new ArrayList<>();
        for (TrabajadorMedico t : personal) {
            r.add(t.getNombre() + ": Q" + t.calcularSalario());
        }
        return r;
    }
}
