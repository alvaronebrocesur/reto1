package org.example;

import lombok.Data;

@Data
public class Pelicula {
    private int id;
    private String titulo;
    private int ano;
    private String Director;
    private String Genero;

    public Pelicula(int id, String titulo, int ano, String director, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        Director = director;
        Genero = genero;
    }
}
