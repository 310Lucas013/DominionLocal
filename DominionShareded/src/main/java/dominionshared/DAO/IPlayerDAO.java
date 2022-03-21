package dominionshared.DAO;

import org.hibernate.Session;
import dominionshared.models.Player;

import java.io.Serializable;
import java.util.List;

public interface IPlayerDAO<T, Id extends Serializable> {

    Session openCurrentSession();

    Session openCurrentSessionwithTransaction();

    void closeCurrentSession();

    void closeCurrentSessionwithTransaction();

    Session getCurrentSession();

    void persist(T entity);

    List<Player> getAll();

    Player login(String userName, String password);

    boolean register(String username, String password);
}
