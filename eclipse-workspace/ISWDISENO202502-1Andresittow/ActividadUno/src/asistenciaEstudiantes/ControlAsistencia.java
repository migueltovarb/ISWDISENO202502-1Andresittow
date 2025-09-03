package asistenciaEstudiantes;

import java.util.Scanner;

public class ControlAsistencia {
    public static void main(String[] args) {
        final int DIAS_SEMANA = 5;
        final int NUM_ESTUDIANTES = 4;
        String[][] asistencia = new String[NUM_ESTUDIANTES][DIAS_SEMANA];
        Scanner miScanner = new Scanner(System.in);

        System.out.println("Registro de Asistencia");
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            System.out.println("Estudiante " + (i + 1) + ":");
            for (int j = 0; j < DIAS_SEMANA; j++) {
                String entrada = "";
                while (!(entrada.length() == 1 && (entrada.charAt(0) == 'P' || entrada.charAt(0) == 'A'))) {
                    System.out.print("Día " + (j + 1) + " (P: presesnte, A: ausente): ");
                    entrada = miScanner.next();
                }
                asistencia[i][j] = entrada;
            }
        }
        
       int opcion = 0;
        
        do {
            System.out.println("Menú de opciones");
            System.out.println("1. Ver asistencia individual");
            System.out.println("2. Ver resumen general");
            System.out.println("3. Volver a registrar");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            
            
            if (!miScanner.hasNextInt() ) {
            	System.out.println("Error: ingrese una opción válida, intente nuevamente.");
            	miScanner.next();
            	continue;
            	}
            
            opcion = miScanner.nextInt();

            
            if (opcion == 1) {
                System.out.print("Ingresa el número de estudiante (1 a 4): ");
                int estudiante = miScanner.nextInt() - 1;
                if (estudiante >= 0 && estudiante < NUM_ESTUDIANTES) {
                    System.out.println("Asistencia del Estudiante " + (estudiante + 1) + ":");
                    for (int dia = 0; dia < DIAS_SEMANA; dia++) {
                        if (asistencia[estudiante][dia].length() == 1 && asistencia[estudiante][dia].charAt(0) == 'P') {
                            System.out.println("Día " + (dia + 1) + ": Presente");
                        } else {
                            System.out.println("Día " + (dia + 1) + ": Ausente");
                        }
                    }
                } else {
                    System.out.println("Número de estudiante inválido.");
                }
                
            } else if (opcion == 2) {
                int[] totalAsistencias = new int[NUM_ESTUDIANTES];
                int[] ausenciasPorDia = new int[DIAS_SEMANA];
                int estudiantesCompletos = 0;

                for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                    int asistencias = 0;
                    boolean completo = true;
                    for (int j = 0; j < DIAS_SEMANA; j++) {
                        if (asistencia[i][j].length() == 1 && asistencia[i][j].charAt(0) == 'P') {
                            asistencias++;
                        } else {
                            ausenciasPorDia[j]++;
                            completo = false;
                        }
                    }
                    
                    totalAsistencias[i] = asistencias;
                    System.out.println("Estudiante " + (i + 1) + " - Asistencias: " + asistencias);
                    if (completo) {
                        estudiantesCompletos++;
                    }
                }

                System.out.println("Estudiantes que asistieron todos los días en semana: " + estudiantesCompletos);

                int maxAusencias = 0;
                for (int j = 0; j < DIAS_SEMANA; j++) {
                    if (ausenciasPorDia[j] > maxAusencias) {
                        maxAusencias = ausenciasPorDia[j];
                    }
                }

                System.out.println("Días con mayor número de ausencias:");
                for (int j = 0; j < DIAS_SEMANA; j++) {
                    if (ausenciasPorDia[j] == maxAusencias) {
                        System.out.println("Día " + (j + 1) + ": " + ausenciasPorDia[j] + " ausencias");
                    }
                }

            } else if (opcion == 3) {
                System.out.println("Registro de Asistencia");
                for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                    System.out.println("Estudiante " + (i + 1) + ":");
                    for (int j = 0; j < DIAS_SEMANA; j++) {
                        String entrada = "";
                        while (!(entrada.length() == 1 && (entrada.charAt(0) == 'P' || entrada.charAt(0) == 'A'))) {
                            System.out.print("Día " + (j + 1) + " (P para presente, A para ausente): ");
                            entrada = miScanner.next();
                        }
                        asistencia[i][j] = entrada;
                    }
                }
                
            } else if (opcion == 4) {
                System.out.println("Está saliendo del programa.");
                
            } else {
                System.out.println("Opción inválida, intente nuevamente.");
                
            }

        } while (opcion != 4);
    }
}