package platzi.play.plataforma;

import platzi.play.contenido.Pelicula;

import java.time.LocalDateTime;

public class Usuario {

    //Atributos
    public String nombre;
    public int edad;
    public int telefono;
    public String email;
    public LocalDateTime fechaRegistro;

    //Metodos
    public void ver(Pelicula pelicula){
        System.out.println(nombre + " registrado en " + fechaRegistro + " esta viendo: ");
        pelicula.reproducir();
    }

}
