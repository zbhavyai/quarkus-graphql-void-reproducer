package org.acme.microprofile.graphql;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.graphql.Source;
import org.jboss.resteasy.reactive.RestPath;

import io.smallrye.mutiny.Uni;

@Path("rest")
@ApplicationScoped
public class FilmRestResource {

    @Inject
    GalaxyService service;

    @GET
    @Path("allFilms")
    public List<Film> getAllFilms() {
        return service.getAllFilms();
    }

    @GET
    @Path("allHeroes")
    public List<Hero> getAllHeroes() {
        return service.getAllHeroes();
    }

    @GET
    @Path("filmById/{id}")
    public Film getFilm(@RestPath int id) {
        return service.getFilm(id);
    }

    public List<Hero> heroes(@Source Film film) {
        return service.getHeroesByFilm(film);
    }

    @POST
    public Uni<Void> createHero(Hero hero) {
        service.addHero(hero);

        return Uni.createFrom().voidItem();
    }

    @DELETE
    @Path("{id}")
    public Uni<Boolean> deleteHero(@RestPath int id) {
        return Uni.createFrom().item(service.deleteHero(id));
    }
}
