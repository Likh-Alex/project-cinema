package ua.mate.cinema;

import ua.mate.cinema.lib.Injector;
import ua.mate.cinema.model.Movie;
import ua.mate.cinema.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("ua.mate.cinema");
    private static MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

    public static void main(String[] args) {
        movieService.getAll().forEach(System.out::println);
        Movie movie = new Movie();
        movie.setTitle("Tor 7");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
