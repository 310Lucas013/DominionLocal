package dominionshared.communication.rest;

import dominionshared.models.Player;

public interface IAuthRestClient {

    Player login(String username, String password);

    boolean register(String username, String password);
}
