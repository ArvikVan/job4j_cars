package store;

import models.Advertisement;
import models.Body;
import models.Brand;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.function.Function;

/**
 * @author ArvikV
 * @version 1.0
 * @since 22.01.2022
 */
public class AdRepository implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final Store INST = new AdRepository();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl =  command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public List<Advertisement> advertisementListForLastDay() {
        return this.tx(session -> session.createQuery(
                "select distinct a from Advertisement a "
                        + "join fetch a.brands "
                        + "join fetch a.bodies "
                        + "join fetch a.users "
                        + " where day(current_timestamp - a.created) <= 1").list());
    }

    @Override
    public List<Advertisement> advertisementListWithPhotos() {
        return this.tx(session -> session.createQuery(
                        "select distinct a from Advertisement a "
                                + "join fetch a.brands "
                                + "join fetch a.bodies "
                                + "join fetch a.users "
                                + "where a.photo = true",
                        Advertisement.class)
                .getResultList());
    }

    @Override
    public List<Advertisement> advertisementByBrand(int brand) {
        return this.tx(session -> session.createQuery(
                "select distinct a from Advertisement a "
                        + "join fetch a.brands "
                        + "join fetch a.bodies "
                        + "join fetch a.users "
                        + "where a.brands.id =: aId"
                ) .setParameter("aId", brand).getResultList());
    }
}
