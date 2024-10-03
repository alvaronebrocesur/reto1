package org.example;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        leer(peliculas, "peliculas.csv");
        vaciar("salida");
        escribir(peliculas, "template.html", "salida");
    }

    /**
     * Lee un archivo .csv el cual se pasa por "pelis" y guarda las peliculas en el array "peliculas"
     * @param peliculas Array en el que se guardan las peliculas
     * @param pelis     Ruta al archivo .csv
     */
    private static void leer(ArrayList<Pelicula> peliculas, String pelis) {
        try (BufferedReader br = new BufferedReader(new FileReader(pelis))) {
            String st;
            while ((st = br.readLine()) != null) {
                String[] aux = st.split(",");
                peliculas.add(new Pelicula(Integer.parseInt(aux[0]),aux[1],Integer.parseInt(aux[2]),aux[3],aux[4]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Escribe las peliculas pasadas dentro del array "peliculas" y se escriben cada una en un archivo html
     * identificado por su nombre y codigo con el template del cual se pasa la ruta por "template"
     * @param peliculas Array donde se encuentran todas las peliculas a guardar
     * @param template  Ruta donde se encuentra el template html a usar
     * @param salida    Ruta donde se guardaran los html de cada pelicula
     */
    private static void escribir(ArrayList<Pelicula> peliculas, String template, String salida) {
        String aux;
        try (BufferedReader br = new BufferedReader(new FileReader(template))) {
            template = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Pelicula p : peliculas) {
            aux = template.replaceAll("%%1%%",String.valueOf(p.getId())).replaceAll("%%2%%",p.getTitulo())
                    .replaceAll("%%3%%",String.valueOf(p.getAno())).replaceAll("%%4%%",p.getDirector()).replaceAll("%%5%%",p.getGenero());
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(salida + "/"+ p.getTitulo().replaceAll(":","") + " - " + p.getId() + ".html"))) {
                bw.write(aux);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Metodo para eliminar todos los archivos de un directorio "dir" sin directorios
     * @param dir   Ruta del directorio que se quiere vaciar
     */
    private static void vaciar(String dir) {
        File directorio = new File(dir);
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                archivo.delete();
            }
        }
    }
}