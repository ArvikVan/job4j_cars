package store;

import models.Car;
import models.Driver;
import models.Engine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author ArvikV
 * @version 1.0
 * @since 21.01.2022
 */
public class HStore {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();
            session.beginTransaction();

            Engine engine = Engine.of("WOLOTGF4556-06");
            session.save(engine);
            Car car = Car.of("Opel", "Zafira-A", engine);
            Driver driver = Driver.of("Koromislov");
            car.addDriver(driver);
            session.save(car);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }
}
