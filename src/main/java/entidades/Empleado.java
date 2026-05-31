/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;

/**
 *
 * @author 29160712r
 */
public class Empleado extends Cliente {
  
    private String dni, nss, puesto, calle, ciudad, provincia;
    private int categoria, grupo, nivel, numero, cp;
    private LocalDate fechaContrato, antiguedadAnterior;
    
    
    public Empleado(String nombre, String apellidos, String telefono, String email,
            String dni, String nss, String calle, int numero, String ciudad, 
            String provincia, int cp, int categoria, int grupo, int nivel, 
            LocalDate fechaContrato, LocalDate antiguedadAnterior, String puesto) throws ErrorDatos {

        super.setNombre(nombre);
        super.setApellidos(apellidos);
        super.setTelefono(telefono);
        super.setEmail(email);
        super.setNivelFidelidad(5);
        this.dni = dni;
        this.nss = nss;
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

    public int getNumero() {
        return numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public int getCp() {
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

    public LocalDate getAntiguedadAnterior() {
        return antiguedadAnterior;
    }

    public String getPuesto() {
        return puesto;
    }

    // Setters


    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCp(int cp) {
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

    public void setAntiguedadAnterior(LocalDate antiguedad) {
        this.antiguedadAnterior = antiguedad;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

}
