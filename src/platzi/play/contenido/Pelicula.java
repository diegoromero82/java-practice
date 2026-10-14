package platzi.play.contenido;

//Concepto de herencia: Pelicula hereda de su clase padre Contenido

public class Pelicula extends Contenido {
    public Pelicula(String titulo, int duracion, Genero genero, double calificacion) {
        super(titulo, duracion, genero, calificacion);
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo la pelicula " + getTitulo());
    }

    @Override
    public String obtenerFichaTecnica() {
        return "Titulo: " + getTitulo() + "\n" +
                "Duración: " + getDuracion() + "\n" +
                "Género: " + getGenero() + "\n" +
                "Calificación: " + getCalificacion() + "/5";
    }

}
