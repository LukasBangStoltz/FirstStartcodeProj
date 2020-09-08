package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import DTO.MovieDTO;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final MovieFacade FACADE = MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieCount() {

        long count = FACADE.getMovieCount();

        return GSON.toJson(count);

    }

    @Path("addmovie")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String addMovie() {

        MovieDTO movie = new MovieDTO(FACADE.addMovie());

        return GSON.toJson(movie);

    }

    @Path("id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeById(@PathParam("id") int id) {

        MovieDTO movie = new MovieDTO(FACADE.getMovieById(id));

        return GSON.toJson(movie);

    }

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllMovies() {

        List <Movie> testList = FACADE.getAllMovies();
        List <MovieDTO> dtoList = new ArrayList<>();
        
        for (Movie movie : testList) {
         dtoList.add(new MovieDTO(movie));
        }
        

        return GSON.toJson(dtoList);
    }
}
