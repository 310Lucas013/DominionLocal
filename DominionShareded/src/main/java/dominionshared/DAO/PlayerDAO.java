package dominionshared.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import dominionshared.models.Player;

import java.util.List;

public class PlayerDAO implements IPlayerDAO<Player, String> {

    private Session currentSession;

    private Transaction currentTransaction;

    PlayerDAO() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Player.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void persist(Player entity) {
        getCurrentSession().save(entity);
    }

    public List<Player> getAll() {
        return getCurrentSession().createCriteria(Player.class).list();
    }

    public Player login(String userName, String password) {
        List<Player> players = getAll();
        for (Player p : players) {
            if (p.getName().equals(userName)) {
                return p;
            }
        }
        return null;
    }

    public boolean register(String username, String password) {
        List<Player> players = getAll();
        for (Player p : players) {
            //USER ALREADY EXISTS
            if (p.getName().equals(username)) {
                return false;
            }
        }
        Player player = new Player(username, password);
        try {
            persist(player);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
