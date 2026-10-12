package platzi.play.plataforma;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
    }

    public void agregar(Pelicula elemento){
        this.contenido.add(elemento);
    }

    public List<String> getTitulo() {
        return contenido.stream()
                .map(Pelicula::getTitulo)
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

    public void eliminar(Pelicula elemento) {
        this.contenido.remove(elemento);
    }

    public Pelicula buscarPorTitulo(String titulo){

        //Forma tradicional
//        for (Pelicula pelicula : contenido) {
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

    public List<Pelicula> buscarPorGenero (Genero genero){
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();

    }

    public int getDuracionTotal(){
        return contenido.stream()
                .mapToInt(Pelicula::getDuracion)
                .sum();
    }

    public List<Pelicula> getPopulares(int cantidad) {
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Pelicula::getCalificacion).reversed())
                .limit(cantidad)
                .toList();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
