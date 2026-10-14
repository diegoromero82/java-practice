package platzi.play.plataforma;

import platzi.play.contenido.*;
import platzi.play.excepcion.ContenidoExistenteException;
import platzi.play.util.FileUtils;

import java.util.*;

public class Plataforma {
    private String nombre;
    private List<Contenido> contenido;
    private Map<Contenido, Integer> visualizaciones;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();
    }

    public void agregar(Contenido elemento){
        Contenido contenido = this.buscarPorTitulo(elemento.getTitulo());

        if (contenido != null){
            throw new ContenidoExistenteException(elemento.getTitulo());
        }

        FileUtils.escribirContenido(elemento);
        this.contenido.add(elemento);
    }

    public void reproducir(Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproducido " + conteoActual + " veces.");

        this.contarVisualizaciones(contenido);
        contenido.reproducir();
    }

    private void contarVisualizaciones (Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActual + 1);
    }

    public List<String> getTitulo() {
        return contenido.stream()
                .map(Contenido::getTitulo)
                .toList();
    }

    public List<ResumenContenido> getResumenes(){
        return contenido.stream()
                .map(c -> new ResumenContenido(c.getTitulo(), c.getDuracion(), c.getGenero()))
                .toList();
    }

//    public void mostrarTitulo() {
//        //Forma tradicional
////        for (int i = 0; i < contenido.size(); i++) {
////            System.out.println(contenido.get(i).getTitulo());
////        }
//
//        //Forma mejorada por medio programación funcional (lambda)
//        contenido.forEach(contenido -> System.out.println(contenido.getTitulo()));
//    }

    public void eliminar(Contenido elemento) {
        this.contenido.remove(elemento);
    }

    public Contenido buscarPorTitulo(String titulo){

        //Forma tradicional
//        for (Contenido pelicula : contenido) {
//            if (pelicula.getTitulo().equalsIgnoreCase(titulo)){
//                return pelicula;
//            }
//        }

        //Forma funcional
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

    }

    public List<Contenido> buscarPorGenero (Genero genero){
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();

    }

    public int getDuracionTotal(){
        return contenido.stream()
                .mapToInt(Contenido::getDuracion)
                .sum();
    }

    public List<Contenido> getPopulares(int cantidad) {
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Contenido::getCalificacion).reversed())
                .limit(cantidad)
                .toList();
    }

    public List<Pelicula> getPeliculas(){
        return contenido.stream()
                .filter(contenido -> contenido instanceof Pelicula )
                .map(contenidoFiltrado -> (Pelicula) contenidoFiltrado)
                .toList();
    }

    public List<Documental> getDocumentales(){
        return contenido.stream()
                .filter(contenido -> contenido instanceof Documental )
                .map(contenidoFiltrado -> (Documental) contenidoFiltrado)
                .toList();
    }

    public List<Promocionable> getContenidoPromocional() {
        return contenido.stream()
                .filter(contenido -> contenido instanceof Promocionable)
                .map(contenidoProm -> (Promocionable) contenidoProm)
                .toList();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }
}
