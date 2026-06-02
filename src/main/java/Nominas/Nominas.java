/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package Nominas;
import Nominas.Tabla;
import Nominas.Complementos;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;
//import nominas.Todos;

/**
 * 
 * 
 * 
 * @author macky
 */

public class Nominas {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        // Declara las instancias.
        Scanner kl = new Scanner(System.in);
    
        int opcion;
        
        do{
        
        // Imprime el menú.
        System.out.println("Indique La opción:");
        System.out.println();
        System.out.println("1 - Imprimir nómina");
        System.out.println();
        System.out.println("2 - Añadir Datos");
        System.out.println();
        System.out.println("3 - Terminar");
        
        // Seleción de Opción.
        opcion = Integer.parseInt(kl.nextLine());
            
        switch (opcion) {
        
        case 1: imprimirNomina(seleccionarDatos(),seleccionaMes());break;
        
        case 2: System.out.println("Opción no disponible.");break;
    
        case 3: break;
        
        default: System.out.println("opcion no valida"); 

        }
    
        }while(opcion != 3);
        
    }    
        
    
    public static int seleccionarDatos(){
        
        Todos todos = new Todos();  
        Scanner kl = new Scanner(System.in);
        int i, seleccion;
        
        System.out.println();System.out.println();
        System.out.println("Empleado");
        System.out.println();
        
        for (i = 0; i < todos.getPersonal().size(); i++){
        
            System.out.printf("%d - %s\n\n", i + 1, todos.getPersonal().get(i).getNombreCompleto());
        
        }
        do{

            seleccion = kl.nextInt();
                     
        }while(seleccion <= 0 || seleccion > todos.getPersonal().size());
        
        return seleccion - 1;

    }
    
    
    public static int seleccionaMes(){
        
        Scanner kl = new Scanner(System.in);
        int mesNomina = 0;

        System.out.printf("\n\nSeleccione Mes\n");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");

        System.out.printf("1 - %s (mes anterior)\n\n",
                Month.of(LocalDate.now().plusMonths(-1).getMonthValue()).getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase());
        System.out.printf("2 - %s (mes actual)\n\n",
                Month.of(LocalDate.now().plusMonths(0).getMonthValue()).getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase());
        System.out.printf("3 - %s (próximo mes)\n",
                Month.of(LocalDate.now().plusMonths(1).getMonthValue()).getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase());        
        System.out.println("----------------------------------------------------------------------------------------------------------------------");

        do {
            
            mesNomina = Integer.parseInt(kl.nextLine());
        
        } while (mesNomina < 1 || mesNomina > 3 );
        
        mesNomina -= 2;

        //System.out.printf("Se ha seleccionado el mes de %s\n",
        //        Month.of(LocalDate.now().plusMonths(mesNomina).getMonthValue()).getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase());
        //System.out.println("----------------------------------------------------------------------------------------------------------------------");        
 
        return mesNomina;        
        
    }
    
    
    
    public static void imprimirNomina(int i, int mesNomina){

        Tabla tS25 = new Tabla();
        Complementos cpt = new Complementos();
        LocalDate fechaActual = LocalDate.now().plusMonths(mesNomina);
        Todos todos = new Todos();
        double sb = tS25.getBaseMes(todos.getPersonal().get(i).getGrupo(),todos.getPersonal().get(i).getNivel());
        double cd = cpt.getComplementoDestino();
        double ce =cpt.getComplementoEspecifico(todos.getPersonal().get(i).getCategoria());
        double bccc = sb + sb /6 + cd + ce;
        double bccp = bccc + tS25.getHora(todos.getPersonal().get(i).getGrupo(),todos.getPersonal().get(i).getNivel()) * 0;
        double qbccc = bccc * 0.0483;
        double qbccp = bccp * 0.0165;
        double irpf = (sb + ce + cd) * 0.1984;
        int dias = fechaActual.lengthOfMonth();
        Month mesActual = fechaActual.getMonth();
        String mes = mesActual.getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase();
        
        System.out.printf("\n\n\n\n");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Empresa. \t\t\t\t\t\t\t\t\t Trabajador\n");
        System.out.printf("MAZCU.FIT S.L. \t\t\t\t\t\t\t\t\t %s\n", todos.getPersonal().get(i).getNombreCompleto());
        System.out.printf("Dirección de Empresa \t\t\t\t\t\t\t\t %s, %d\n", todos.getPersonal().get(i).getCalle(), todos.getPersonal().get(i).getNumero());
        System.out.printf("46940 Manises (Valencia)\t\t\t\t\t\t\t %d %s %s\n", todos.getPersonal().get(i).getCp(), todos.getPersonal().get(i).getCiudad(), todos.getPersonal().get(i).getProvincia());
        System.out.printf("CIF: A12345678 \t\t\t\t\t\t\t\t\t NIF: %s\t NSS: %s\n", todos.getPersonal().get(i).getDni(), todos.getPersonal().get(i).getNss());
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Cuenta de Contratación \t\t\t\t\t\t\t\t Puesto  %s\n", todos.getPersonal().get(i).getPuesto());     
        System.out.printf("46 / 1234567 / 25 \t\t\t\t\t\t\t\t Grupo %d   Nivel %d   Categoria %d\n", todos.getPersonal().get(i).getGrupo(),todos.getPersonal().get(i).getNivel(), todos.getPersonal().get(i).getCategoria());
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Periodo de liquidación\t 1/ %s/ %d     al     %d/ %s/ %d\t\t\tNº de días %d\n", mes, fechaActual.getYear(), dias, mes, fechaActual.getYear(), dias);
        System.out.println("----------------------------------------------------------------------------------------------------------------------");        
        System.out.printf("Clave  Concepto \t\t\t\t\t\tCantidad       Importe \t\t Retenciones\n\n");
        System.out.printf("1\tSueldo Base\t\t\t\t\t\t\t\t%.2f\n", sb, sb);
        System.out.printf("2\tComplemento de Destino\t\t\t\t\t\t\t %.2f\n", cpt.getComplementoDestino());
        System.out.printf("2\tComplemento Especìfico\t\t\t\t\t\t\t %.2f\n", cpt.getComplementoEspecifico(todos.getPersonal().get(i).getCategoria()));
        System.out.printf("909\tQuota Empleado Contingencias Comunes\t\t\t 4.83%%\t\t\t\t    %.2f\n",qbccc);
        System.out.printf("931\tQuota Empleado Contingencias Profesionales\t\t 1.65%%\t\t\t\t     %.2f\n",qbccp);
        System.out.printf("962\tRetenciones IRPF\t\t\t\t\t19.84%%\t\t\t\t    %.2f\n", irpf);
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("\t\t\t\t\t\t\t\t\tTotal   %.2f\t\t    %.2f\n", sb + ce + cd, qbccc + qbccp + irpf);
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("\n\n\t\t\t\t\t\t\t\t\t\tLiquido a Percibir %.2f\n\n\n", sb + ce + cd - qbccc - qbccp - irpf);
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Remuneración mensual \t %.2f\n\n", sb + ce + cd);
        System.out.printf("Prorrata  pagas extras \t  %.2f\n\n", sb/6);
        System.out.printf("Base IT\n");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Total Base Bruta\t %.2f\t\t\t\tTrabajador \t\t       Empresa\n\n",bccc );
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Conceptos\t\t\t Bases \t\t\t Tipo \t\t Quota \t\t Tipo \t\t Quota \n");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("\nContingencias Comunes\t\t %.2f\t\t4.83%%\t\t %.2f\t\t24.27%%\t\t %.2f\n\n", bccc, qbccc, bccc * 0.2427);
        System.out.printf("Contingencias Profesionales\t %.2f\t\t1.65%%\t\t  %.2f\t\t 7,80%%\t\t %.2f\n\n", bccp, qbccp, bccp * 0.078);
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Total\t\t\t\t\t\t\t\t\t %.2f\t\t\t\t %.2f\n\n", qbccc + qbccp, bccp * 0.2427 + bccp * 0.078);
        System.out.printf("\n\n\n\n");
          
    }
        
}
