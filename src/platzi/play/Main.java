package platzi.play;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Plataforma;
import platzi.play.util.ScannerUtils;

import java.util.List;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "PLATZI PLAY";
    public static final String VERSION = "1.0.0";
    public static final int AGREGAR = 1;
    public static final int MOSTRAR = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        cargarPeliculas(plataforma);
        System.out.println("Más de " + plataforma.getDuracionTotal() + " minutos de contenido! \n");

        while (true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    Seleccione una opción:
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver Populares
                    8. Eliminar
                    9. Salir
                    ->""");

            switch (opcionElegida) {
                case AGREGAR -> {
                    String nombrePelicula = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duración del contenido");
                    double calificacion = ScannerUtils.capturDecimal("Calificación del contenido");

                    plataforma.agregar(new Pelicula(nombrePelicula, duracion, genero, calificacion));
                }
                case MOSTRAR -> {
                    List<String> titulos = plataforma.getTitulo();
                    titulos.forEach(System.out::println);
                }
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturarTexto("Escriba el nombre de la Pelicula");
                    Pelicula pelicula = plataforma.buscarPorTitulo(nombreBuscado);

                    if (pelicula != null){
                        System.out.println(pelicula.obtenerFichaTecnica());
                    } else {
                        System.out.println("La pelicula llamada " + nombreBuscado + " no existe en la plataforma " + plataforma.getNombre());
                    }

                }
                case BUSCAR_POR_GENERO -> {
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido a buscar");
                    List<Pelicula> contenidoPorGenero = plataforma.buscarPorGenero(genero);

                    if (contenidoPorGenero != null){
                        System.out.println(contenidoPorGenero.size() + " coincidencias para el genero " + genero + "\n");
                        contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                    } else {
                        System.out.println("El genero " + genero + " no existe en la plataforma " + plataforma.getNombre());
                    }

                }
                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.capturarNumero("Cantidad de resultados a mostrar");
                    List<Pelicula> contenidoPopulares = plataforma.getPopulares(cantidad);
                    contenidoPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }
                case ELIMINAR -> {
                    String nombreEliminar = ScannerUtils.capturarTexto("Escriba el nombre de la Pelicula a eliminar");
                    Pelicula contenido = plataforma.buscarPorTitulo(nombreEliminar);

                    if (contenido != null){
                        plataforma.eliminar(contenido);
                        System.out.println("La pelicula " + nombreEliminar + " ha sido eliminada." );
                    } else {
                        System.out.println("La pelicula llamada " + nombreEliminar + " no existe en la plataforma " + plataforma.getNombre());
                    }

                }
                case SALIR -> {
                    System.out.println("Gracias");
                    System.exit(0);
                }
            }
        }

//
//
//        Pelicula pelicula1 = new Pelicula("Thor", 180, "Acción", 4);
//
//        plataforma.agregar(pelicula);
//        plataforma.agregar(pelicula1);
//        System.out.println("Numero de elementos en la plataforma " + plataforma.getContenido().size());
//        plataforma.eliminar(pelicula1);
//        plataforma.mostrarTitulo();
//
//        String nombre = "Diego";
//        int edad = 44;
//        int telefono = 12345678;
//        String email = "diego@mail.com";
//        LocalDateTime fechaRegistro = LocalDateTime.now();
//
//        Usuario usuario = new Usuario(nombre, edad, telefono, email, fechaRegistro);
//
//        usuario.setNombre("Diego");
//        usuario.setEdad(44);
//        usuario.setTelefono(12345678);
//        usuario.setEmail("diego@mail.com");
//
//        usuario.ver(pelicula);


    }
    private static void cargarPeliculas (Plataforma plataforma) {
        plataforma.agregar(new Pelicula("Hulk", 210, Genero.ACCION, 5));
        plataforma.agregar(new Pelicula("Sign", 235, Genero.ACCION, 4 ));
        plataforma.agregar(new Pelicula("Saw", 190, Genero.TERROR, 4.7 ));
        plataforma.agregar(new Pelicula("Spider Man", 215, Genero.ACCION, 4.9 ));
        plataforma.agregar(new Pelicula("Ant Man", 195, Genero.ACCION, 4.9 ));
        plataforma.agregar(new Pelicula("Toy Story", 225, Genero.COMEDIA, 5 ));
    }

}
