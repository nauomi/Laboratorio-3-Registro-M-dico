import controller.HospitalManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import model.*;
 
 /**
 @author //Naomi
 @version //v1.0
 */

public class Main {
    
     //Método principal que inicia el programa y muestra el menú de opciones.
 
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            HospitalManager manager = new HospitalManager();
            boolean continuar = true;
            
            while (continuar) {
                System.out.println("\n===== SISTEMA DE GESTIÓN HOSPITALARIA =====");
                System.out.println("1. Registrar trabajador médico");
                System.out.println("2. Registrar cita médica");
                System.out.println("3. Reagendar cita");
                System.out.println("4. Mostrar reporte de personal");
                System.out.println("5. Mostrar reporte de citas");
                System.out.println("6. Generar nómina");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                
                int opcion = sc.nextInt();
                sc.nextLine();
                
                switch (opcion) {
                    case 1 -> registrarTrabajador(sc, manager);
                    case 2 -> registrarCita(sc, manager);
                    case 3 -> reagendarCita(sc, manager);
                    case 4 -> mostrarReporte(manager.reportePersonal(), "REPORTE DE PERSONAL");
                    case 5 -> mostrarReporte(manager.reporteCitas(), "REPORTE DE CITAS");
                    case 6 -> mostrarReporte(manager.generarNomina(), "NÓMINA DEL HOSPITAL");
                    case 0 -> {
                        continuar = false;
                        System.out.println("👋 Saliendo del sistema. ¡Gracias por usar el programa!");
                    }
                    default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
                }
            }
        }
    }
    
    //Permite registrar un nuevo trabajador médico del hospital.
     
    private static void registrarTrabajador(Scanner sc, HospitalManager manager) {
        System.out.println("\n--- REGISTRO DE TRABAJADOR ---");
        System.out.println("Seleccione el tipo de trabajador:");
        System.out.println("1. Doctor General");
        System.out.println("2. Cirujano");
        System.out.println("3. Enfermero");
        System.out.println("4. Radiólogo");
        System.out.print("Opción: ");
        int tipo = sc.nextInt();
        sc.nextLine();

        // Datos comunes
        System.out.print("ID del empleado: ");
        String id = sc.nextLine();
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Departamento: ");
        String depto = sc.nextLine();
        System.out.print("Años de experiencia: ");
        int exp = sc.nextInt();
        System.out.print("Salario base: ");
        double base = sc.nextDouble();
        sc.nextLine();

        // Datos específicos según tipo
        switch (tipo) {
            case 1 -> {
                System.out.print("Especialización: ");
                String esp = sc.nextLine();
                System.out.print("Pacientes por día: ");
                int pacientes = sc.nextInt();
                System.out.print("Tarifa por consulta: ");
                double tarifa = sc.nextDouble();
                manager.registrarTrabajador(new DoctorGeneral(id, nombre, depto, exp, base, esp, pacientes, tarifa));
            }
            case 2 -> {
                System.out.print("Horas de cirugía: ");
                int horas = sc.nextInt();
                System.out.print("Tarifa por hora: ");
                double tarifaHora = sc.nextDouble();
                System.out.print("Bono por riesgo: ");
                double bono = sc.nextDouble();
                manager.registrarTrabajador(new Cirujano(id, nombre, depto, exp, base, horas, tarifaHora, bono));
            }
            case 3 -> {
                sc.nextLine();
                System.out.print("Tipo de turno (diurno/nocturno): ");
                String turno = sc.nextLine();
                System.out.print("Nivel de certificación: ");
                String nivel = sc.nextLine();
                manager.registrarTrabajador(new Enfermero(id, nombre, depto, exp, base, turno, nivel));
            }
            case 4 -> {
                System.out.print("Número de estudios realizados: ");
                int estudios = sc.nextInt();
                System.out.print("Tarifa por estudio: ");
                double tarifaEstudio = sc.nextDouble();
                sc.nextLine();
                System.out.print("Equipos certificados (separados por coma): ");
                String eq = sc.nextLine();
                List<String> equipos = List.of(eq.split(","));
                manager.registrarTrabajador(new Radiologo(id, nombre, depto, exp, base, equipos, tarifaEstudio, estudios));
            }
            default -> {
                System.out.println("❌ Tipo de trabajador no válido.");
                return;
            }
        }

        System.out.println("✅ Trabajador registrado exitosamente.");
    }

     //Registra una nueva cita médica asociada a un médico.
     
    private static void registrarCita(Scanner sc, HospitalManager manager) {
        System.out.println("\n--- REGISTRO DE CITA ---");
        System.out.print("ID de cita: ");
        String idCita = sc.nextLine();
        System.out.print("Nombre del paciente: ");
        String paciente = sc.nextLine();
        System.out.print("ID del médico asignado: ");
        String idMedico = sc.nextLine();

        System.out.print("Fecha y hora (YYYY-MM-DD HH): ");
        String fechaStr = sc.nextLine();
        LocalDateTime fecha = LocalDateTime.parse(fechaStr.replace(" ", "T") + ":00");

        System.out.print("Tipo de cita: ");
        String tipo = sc.nextLine();

        TrabajadorMedico medico = manager.buscarPorId(idMedico);
        if (medico == null) {
            System.out.println("❌ Médico no encontrado. No se registró la cita.");
            return;
        }

        Cita cita = new Cita(idCita, paciente, medico, fecha, tipo, "PROGRAMADA");
        manager.registrarCita(cita);
        System.out.println("✅ Cita registrada correctamente.");
    }

    
     // Permite reagendar una cita existente a una nueva fecha
    private static void reagendarCita(Scanner sc, HospitalManager manager) {
        System.out.println("\n--- REAGENDAMIENTO DE CITA ---");
        System.out.print("Ingrese el ID de la cita: ");
        String id = sc.nextLine();
        System.out.print("Nueva fecha (YYYY-MM-DD HH): ");
        String fechaStr = sc.nextLine();
        LocalDateTime nueva = LocalDateTime.parse(fechaStr.replace(" ", "T") + ":00");

        if (manager.reagendarCita(id, nueva)) {
            System.out.println("✅ Cita reagendada exitosamente.");
        } else {
            System.out.println("❌ No se encontró la cita o hubo un conflicto.");
        }
    }

    
     
     /**
     @param lista
     @param titulo
     */

    private static void mostrarReporte(List<String> lista, String titulo) {
        System.out.println("\n--- " + titulo + " ---");
        if (lista.isEmpty()) {
            System.out.println("No hay datos registrados.");
        } else {
            for (String s : lista) {
                System.out.println(s);
            }
        }
    }
}
