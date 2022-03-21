package dominionshared.DAO;

import dominionshared.models.Player;

public class PlayerService {

    private static PlayerDAO playerDAO;

    public PlayerService() {
        playerDAO = new PlayerDAO();
    }

    public Player login(String username, String password) {
        playerDAO.openCurrentSessionwithTransaction();
        Player player = playerDAO.login(username, password);
        playerDAO.closeCurrentSessionwithTransaction();
        return player;
    }

    public boolean register(String username, String password) {
        playerDAO.openCurrentSessionwithTransaction();
        boolean success = playerDAO.register(username,password);
        playerDAO.closeCurrentSessionwithTransaction();
        return success;
    }
}
