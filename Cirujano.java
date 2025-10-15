package model;

public class Cirujano extends TrabajadorMedico {
    private int horasCirugia;
    private double tarifaPorHora;
    private double bonoRiesgo;

    public Cirujano(String idEmpleado, String nombre, String departamento,
                    int aniosExperiencia, double salarioBase,
                    int horasCirugia, double tarifaPorHora, double bonoRiesgo) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.horasCirugia = horasCirugia;
        this.tarifaPorHora = tarifaPorHora;
        this.bonoRiesgo = bonoRiesgo;
    }

    
    @Override
    public double calcularSalario() {
        return salarioBase + (horasCirugia * tarifaPorHora) + bonoRiesgo;
    }

    public int getHorasCirugia() { return horasCirugia; }
    public void setHorasCirugia(int horasCirugia) { this.horasCirugia = horasCirugia; }

    public double getTarifaPorHora() { return tarifaPorHora; }
    public void setTarifaPorHora(double tarifaPorHora) { this.tarifaPorHora = tarifaPorHora; }

    public double getBonoRiesgo() { return bonoRiesgo; }
    public void setBonoRiesgo(double bonoRiesgo) { this.bonoRiesgo = bonoRiesgo; }

    @Override
    public String toString() {
        return super.toString() + " | Cirujano (Horas: " + horasCirugia + ", Bono riesgo: Q" + bonoRiesgo + ")";
    }
}
