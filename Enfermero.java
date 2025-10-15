package model;

public class Enfermero extends TrabajadorMedico {
    private String tipoTurno;         // si es: diurno o nocturno
    private String nivelCertificacion;

    // Bonificación aplicable por turno nocturno
    private static final double BONIFICACION_NOCTURNA = 500.0;

    public Enfermero(String idEmpleado, String nombre, String departamento,
                     int aniosExperiencia, double salarioBase,
                     String tipoTurno, String nivelCertificacion) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.tipoTurno = tipoTurno;
        this.nivelCertificacion = nivelCertificacion;
    }

    
    @Override
    public double calcularSalario() {
        double bonificacion = (tipoTurno != null && tipoTurno.equalsIgnoreCase("nocturno"))
                             ? BONIFICACION_NOCTURNA
                             : 0.0;
        return salarioBase + bonificacion;
    }

    public String getTipoTurno() { return tipoTurno; }
    public void setTipoTurno(String tipoTurno) { this.tipoTurno = tipoTurno; }

    public String getNivelCertificacion() { return nivelCertificacion; }
    public void setNivelCertificacion(String nivelCertificacion) { this.nivelCertificacion = nivelCertificacion; }

    @Override
    public String toString() {
        return super.toString() + " | Enfermero (Turno: " + tipoTurno + ", Cert: " + nivelCertificacion + ")";
    }
}
