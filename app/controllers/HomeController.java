package controllers;

import model.Actor;
import model.Movie;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.Transaction;
import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    //  Configuration info for connecting to the Neo4J database
    static private final String SERVER_URI = "bolt://localhost";
    static private final String SERVER_USERNAME = "neo4j";
    static private final String SERVER_PASSWORD = "1234";

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public Result initialize() {
        Configuration configuration = new Configuration.Builder().uri(SERVER_URI).credentials(SERVER_USERNAME, SERVER_PASSWORD).build();
        SessionFactory sessionFactory = new SessionFactory(configuration, Movie.class.getPackage().getName());
        Session session = sessionFactory.openSession();
        session.purgeDatabase();

        Transaction txn = session.beginTransaction();

        Actor actor_kai = new Actor("Leonardo");
        Actor actor_yao = new Actor("Kate");

        Movie movie = new Movie("Titanic", 1997);

        actor_kai.actsIn(movie);
        actor_yao.actsIn(movie);
        session.save(actor_kai);
        session.save(actor_yao);

        txn.commit();
        return ok("Database initialized.");
    }

    public Result getActorsIn(String movieName) {
        Configuration configuration = new Configuration.Builder().uri(SERVER_URI).credentials(SERVER_USERNAME, SERVER_PASSWORD).build();
        SessionFactory sessionFactory = new SessionFactory(configuration, Movie.class.getPackage().getName());
        Session session = sessionFactory.openSession();

        Filter filter = new Filter ("title", ComparisonOperator.EQUALS, movieName);
        Movie movie = session.loadAll(Movie.class, filter).iterator().next();
        String result = "";
        for (Actor actor : movie.getActors()) result += actor.getName() + "\n";
        return ok(result);
    }
}
