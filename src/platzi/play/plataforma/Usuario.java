package platzi.play.plataforma;

import platzi.play.contenido.Pelicula;

import java.time.LocalDateTime;

public class Usuario {

    //Atributos
    private String nombre;
    private int edad;
    private int telefono;
    private String email;
    private LocalDateTime fechaRegistro;

    public Usuario(String nombre, int edad, int telefono, String email, LocalDateTime fechaRegistro) {
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
    }

    //Metodos
    public void ver(Pelicula pelicula){
        System.out.println(nombre + " registrado en " + fechaRegistro + " esta viendo: ");
        pelicula.reproducir();
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
