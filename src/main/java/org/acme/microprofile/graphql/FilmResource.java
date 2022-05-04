package org.acme.microprofile.graphql;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

import io.smallrye.mutiny.Uni;

@GraphQLApi
@ApplicationScoped
public class FilmResource {

    @Inject
    GalaxyService service;

    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<Film> getAllFilms() {
        return service.getAllFilms();
    }

    @Query("allHeroes")
    @Description("Get all Heroes")
    public List<Hero> getAllHeroes() {
        return service.getAllHeroes();
    }

    @Query
    @Description("Get a Films from a galaxy far far away")
    public Film getFilm(@Name("filmId") int id) {
        return service.getFilm(id);
    }

    public List<Hero> heroes(@Source Film film) {
        return service.getHeroesByFilm(film);
    }

    // return type Uni<Void> OR void throws an exception at build time
    @Mutation
    public Uni<Void> createHero(Hero hero) {
        service.addHero(hero);
        return Uni.createFrom().voidItem();
    }

    @Mutation
    public Uni<Boolean> deleteHero(int id) {
        return Uni.createFrom().item(service.deleteHero(id));
    }
}
