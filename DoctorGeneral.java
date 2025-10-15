package model;

public class DoctorGeneral extends TrabajadorMedico {
    private String especializacion;
    private int pacientesPorDia;
    private double tarifaConsulta;

    public DoctorGeneral(String idEmpleado, String nombre, String departamento,
                         int aniosExperiencia, double salarioBase,
                         String especializacion, int pacientesPorDia, double tarifaConsulta) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.especializacion = especializacion;
        this.pacientesPorDia = pacientesPorDia;
        this.tarifaConsulta = tarifaConsulta;
    }

    /**
    @return El salario total calculado
    */

    @Override
    public double calcularSalario() {
        return salarioBase + pacientesPorDia * tarifaConsulta;
    }

    @Override
    public String toString() {
        return super.toString() + " | Doctor (" + especializacion + ")";
    }
}
