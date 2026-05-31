/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author 29160712r
 */
public class Empleado extends Cliente {
  
    private String dni, nss, puesto, calle, ciudad, provincia, numero, cp;
    private int categoria, grupo, nivel;
    private LocalDate fechaContrato;
    private double antiguedadAnterior;

    /**
     *
     * @throws ErrorDatos
     */
    public Empleado() throws ErrorDatos{
        this.antiguedadAnterior = 0.00;
    }
    
    public Empleado(int id, String nombre, String apellidos, String telefono, String email,
            String dni, String nss, String calle, String numero, String ciudad, 
            String provincia, String cp, int categoria, int grupo, int nivel, 
            LocalDate fechaContrato, String puesto) throws ErrorDatos {
        
        super(email, 5, nombre, apellidos, telefono,id);
        
        this.setDni(dni);
        this.setNss(nss);
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.cp = cp;
        this.categoria = categoria;
        this.grupo = grupo;
        this.nivel = nivel;
        this.fechaContrato = fechaContrato;
        this.antiguedadAnterior = 0.00;
        this.puesto = puesto;

    }

   
    public Empleado(int id, String nombre, String apellidos, String telefono, String email,
            String dni, String nss, String calle, String numero, String ciudad, 
            String provincia, String cp, int categoria, int grupo, int nivel, 
            LocalDate fechaContrato, double antiguedadAnterior, String puesto) throws ErrorDatos {

        super(email, 5, nombre, apellidos, telefono,id);
        
        this.setDni(dni);
        this.setNss(nss);
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.cp = cp;
        this.categoria = categoria;
        this.grupo = grupo;
        this.nivel = nivel;
        this.fechaContrato = fechaContrato;
        this.antiguedadAnterior = antiguedadAnterior;
        this.puesto = puesto;

    }

    // Getters
    public String getNombreCompleto() {
        return this.getApellidos() + ", " + this.getNombre();
    }

    public String getDni() {
        return dni;
    }

    public String getNss() {
        return nss;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCp() {
        return cp;
    }

    public int getCategoria() {
        return categoria;
    }

    public int getGrupo() {
        return grupo;
    }

    public int getNivel() {
        return nivel;
    }

    public LocalDate getFechaContrato() {
        return fechaContrato;
    }

    public double getAntiguedadAnterior() {
        return antiguedadAnterior;
    }

    public String getPuesto() {
        return puesto;
    }

    // Setters


    public void setDni(String dni) throws ErrorDatos {
        dni = dni.trim().toUpperCase();
        if (dni.matches("^[0-9]{8}[A-Z]$"))
            this.dni = dni;
        else
            throw new ErrorDatos("DNI Incorrecto");
        
    }

    public void setNss(String nss) throws ErrorDatos {
        nss = nss.trim();
        if (nss.matches("^[0-9]{2}[-/ ]?[0-9]{8}[-/ ]?[0-9]{2}$"))
            this.nss = nss;
        else
            throw new ErrorDatos("Numero de seguridad social inválido");
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setFechaContrato(LocalDate fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public void setAntiguedadAnterior(double antiguedad) {
        this.antiguedadAnterior = antiguedad;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getTrienios(){
        return Math.abs(this.antiguedadAnterior + ChronoUnit.YEARS.between(fechaContrato, LocalDate.now())/3);
    } 

    
}   
