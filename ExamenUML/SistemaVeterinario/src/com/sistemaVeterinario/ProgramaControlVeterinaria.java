package com.sistemaVeterinario;

public class ProgramaControlVeterinaria {
    public static void main(String[] args) {

        PersonalVeterinario sistema = new PersonalVeterinario();
        Dueño d1 = new Dueño("Carlos Rojas", "10424547", "312456789");
        Dueño d2 = new Dueño("Ana López", "51515548", "315987654");
        Dueño d3 = new Dueño("Carlos Rojas", "13135325", "312456789"); 

        sistema.registrarDueño(d1);
        sistema.registrarDueño(d2);
        sistema.registrarDueño(d3); 


        Mascota m1 = new Mascota("Max", "Perro", 3, d1);
        Mascota m2 = new Mascota("Luna", "Gato", 2, d2);
        Mascota m3 = new Mascota("Max", "Perro", 3, d1); 

        sistema.registrarMascota(m1);
        sistema.registrarMascota(m2);
        sistema.registrarMascota(m3); 

        
        ControlVeterinario c1 = new ControlVeterinario(new java.util.Date(), TipoControl.VACUNA, "Vacuna antirrábica aplicada.");
        sistema.registrarControl(c1, m1);

        System.out.println();
        System.out.println("Estado final del sistema:");
        System.out.println(sistema);
    }
}
