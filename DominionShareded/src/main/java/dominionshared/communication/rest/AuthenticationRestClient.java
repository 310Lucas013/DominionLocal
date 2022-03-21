package dominionshared.communication.rest;

import dominionshared.communication.rest.Shared.AuthenticationRequestDto;
import dominionshared.communication.rest.Shared.LoginResultDto;
import dominionshared.communication.rest.Shared.RegistrationResultDto;
import dominionshared.models.Player;

public class AuthenticationRestClient extends BaseRestClient implements IAuthRestClient {

    public AuthenticationRestClient() {
    }

    public AuthenticationRestClient(String url) {
        this.url = url;
    }

    private String url;

    public String getBaseUr() {
        return url;
    }

    public boolean register(String username, String password) {
        AuthenticationRequestDto dto = new AuthenticationRequestDto(username, password);
        String query = "/user/register";
        RegistrationResultDto result = executeQueryPost(dto, query, RegistrationResultDto.class);
        return result.isSuccess();
    }

    public Player login(String username, String password) {
        AuthenticationRequestDto dto = new AuthenticationRequestDto(username, password);
        String query = "/user/login";
        LoginResultDto result = executeQueryPost(dto, query, LoginResultDto.class);
        return result.getPlayer();
    }
}
