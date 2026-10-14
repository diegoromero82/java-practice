package platzi.play.contenido;

public class Documental extends Contenido implements Promocionable {

    private String narrador;

    public Documental(String titulo, int duracion, Genero genero, double calificacion, String narrador) {
        super(titulo, duracion, genero, calificacion);
        this.narrador =  narrador;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo el documental " + getTitulo() + " narrado por " + getNarrador());
    }

    @Override
    public String obtenerFichaTecnica() {
        return "Titulo: " + getTitulo() + "\n" +
                "Duración: " + getDuracion() + "\n" +
                "Género: " + getDuracion()+ "\n" +
                "Narrador: " + getNarrador() + "\n" +
                "Calificación: " + getCalificacion() + "/5";
    }

    @Override
    public String promocionar() {
        return "Descubre el documental " + this.getTitulo() + " narrado por " + this.getNarrador();
    }

    public String getNarrador() {
        return narrador;
    }
}
