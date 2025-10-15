package model;

import java.util.List;

public class Radiologo extends TrabajadorMedico {
    private List<String> equiposCertificados;
    private double tarifaPorEstudio;
    private int estudiosRealizados;

    public Radiologo(String idEmpleado, String nombre, String departamento,
                     int aniosExperiencia, double salarioBase,
                     List<String> equiposCertificados, double tarifaPorEstudio, int estudiosRealizados) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.equiposCertificados = equiposCertificados;
        this.tarifaPorEstudio = tarifaPorEstudio;
        this.estudiosRealizados = estudiosRealizados;
    }

    
    @Override
    public double calcularSalario() {
        return salarioBase + (tarifaPorEstudio * estudiosRealizados);
    }

    // Getters y setters
    public List<String> getEquiposCertificados() { return equiposCertificados; }
    public void setEquiposCertificados(List<String> equiposCertificados) { this.equiposCertificados = equiposCertificados; }

    public double getTarifaPorEstudio() { return tarifaPorEstudio; }
    public void setTarifaPorEstudio(double tarifaPorEstudio) { this.tarifaPorEstudio = tarifaPorEstudio; }

    public int getEstudiosRealizados() { return estudiosRealizados; }
    public void setEstudiosRealizados(int estudiosRealizados) { this.estudiosRealizados = estudiosRealizados; }

    @Override
    public String toString() {
        String equipos = (equiposCertificados == null) ? "[]" : equiposCertificados.toString();
        return super.toString() + " | Radiólogo (Estudios: " + estudiosRealizados + ", Equipos: " + equipos + ")";
    }
}
