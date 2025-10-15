package model;

/**
 @author Naomi
 @version 1.0
*/

public abstract class TrabajadorMedico {
    protected String idEmpleado;
    protected String nombre;
    protected String departamento;
    protected int aniosExperiencia;
    protected double salarioBase;

    public TrabajadorMedico(String idEmpleado, String nombre, String departamento,
                            int aniosExperiencia, double salarioBase) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.departamento = departamento;
        this.aniosExperiencia = aniosExperiencia;
        this.salarioBase = salarioBase;
    }

    // Método abstracto
    public abstract double calcularSalario();

    
    public String getIdEmpleado() { return idEmpleado; }
    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
    public int getAniosExperiencia() { return aniosExperiencia; }
    public double getSalarioBase() { return salarioBase; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public void setAniosExperiencia(int aniosExperiencia) { this.aniosExperiencia = aniosExperiencia; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    
    @Override
    public String toString() {
        return nombre + " (" + idEmpleado + "), Depto: " + departamento +
               ", Experiencia: " + aniosExperiencia + " años, Base: Q" + salarioBase;
    }
}
