package platzi.play.contenido;

public record ResumenContenido(String titulo, int duracion, Genero genero) {

    public String toDisplayMoviesSummary(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(titulo).append("\n");
        sb.append("Duración: ").append(duracion).append("\n");
        sb.append("Genero: ").append(genero).append("\n");

        return sb.toString();
    }
}
