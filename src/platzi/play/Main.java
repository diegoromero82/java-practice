package platzi.play;

import platzi.play.contenido.*;
import platzi.play.excepcion.ContenidoExistenteException;
import platzi.play.plataforma.Plataforma;
import platzi.play.util.FileUtils;
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
    public static final int REPRODUCIR = 6;
    public static final int BUSCAR_TIPO = 7;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        cargarContenido(plataforma);
        System.out.println("Más de " + plataforma.getDuracionTotal() + " minutos de contenido! \n");
        plataforma.getContenidoPromocional().forEach(promocionable -> System.out.println(promocionable.promocionar()));

        while (true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    Seleccione una opción:
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver Populares
                    6. Reproducir
                    7. Buscar por Tipo de contenido
                    8. Eliminar
                    9. Salir
                    ->""");

            switch (opcionElegida) {
                case AGREGAR -> {
                    int tipoContenido = ScannerUtils.capturarNumero("Seleccione el tipo de contenido que desea agregar:\n 1. Pelicula\n 2. Documental ");
                    String nombrePelicula = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duración del contenido");
                    double calificacion = ScannerUtils.capturDecimal("Calificación del contenido");

                    try {
                        if (tipoContenido == 1) {
                            plataforma.agregar(new Pelicula(nombrePelicula, duracion, genero, calificacion));
                        } else {
                            String narrador = ScannerUtils.capturarTexto("Narrador del documental");
                            plataforma.agregar(new Documental(nombrePelicula, duracion, genero, calificacion, narrador));
                        }

                    } catch (ContenidoExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case MOSTRAR -> {
                    List<ResumenContenido> contenidosResumidos = plataforma.getResumenes();
                    contenidosResumidos.forEach(r -> System.out.println(r.toDisplayMoviesSummary()));
                }
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturarTexto("Escriba el nombre de la Contenido");
                    Contenido contenido = plataforma.buscarPorTitulo(nombreBuscado);

                    if (contenido != null){
                        System.out.println(contenido.obtenerFichaTecnica());
                    } else {
                        System.out.println("La contenido llamada " + nombreBuscado + " no existe en la plataforma " + plataforma.getNombre());
                    }

                }
                case BUSCAR_POR_GENERO -> {
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido a buscar");
                    List<Contenido> contenidoPorGenero = plataforma.buscarPorGenero(genero);

                    if (contenidoPorGenero != null){
                        System.out.println(contenidoPorGenero.size() + " coincidencias para el genero " + genero + "\n");
                        contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                    } else {
                        System.out.println("El genero " + genero + " no existe en la plataforma " + plataforma.getNombre());
                    }

                }
                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.capturarNumero("Cantidad de resultados a mostrar");
                    List<Contenido> contenidoPopulares = plataforma.getPopulares(cantidad);
                    contenidoPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }
                case REPRODUCIR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre de la pelicula a reproducir");
                    Contenido contenido = plataforma.buscarPorTitulo(nombre);

                    if (contenido != null){
                        plataforma.reproducir(contenido);
                    } else {
                        System.out.println(nombre + " no existe dentro de " + plataforma.getNombre());
                    }
                }

                case BUSCAR_TIPO -> {
                    int tipoContenido = ScannerUtils.capturarNumero("Seleccione el tipo de contenido que desea buscar:\n 1. Pelicula\n 2. Documental ");
                    if (tipoContenido == 1) {
                        List<Pelicula> peliculas = plataforma.getPeliculas();
                        peliculas.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() + "\n"));
                    } else {
                        List<Documental> documentales = plataforma.getDocumentales();
                        documentales.forEach(documental -> System.out.println(documental.obtenerFichaTecnica() + "\n"));
                    }
                }

                case ELIMINAR -> {
                    String nombreEliminar = ScannerUtils.capturarTexto("Escriba el nombre de la Contenido a eliminar");
                    Contenido contenido = plataforma.buscarPorTitulo(nombreEliminar);

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
//        Contenido pelicula1 = new Contenido("Thor", 180, "Acción", 4);
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
    private static void cargarContenido (Plataforma plataforma) {
        plataforma.getContenido().addAll(FileUtils.leerContenido());
    }

}
