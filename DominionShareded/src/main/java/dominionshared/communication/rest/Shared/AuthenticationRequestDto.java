package dominionshared.communication.rest.Shared;

import dominionshared.communication.rest.dto.BaseRequestDto;

public class AuthenticationRequestDto extends BaseRequestDto {

    private String userName;
    private String hashedPassword;

    public AuthenticationRequestDto(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public AuthenticationRequestDto(String userName, String hashedPassword) {
        this.userName = userName;
        this.hashedPassword = hashedPassword;
    }

    public AuthenticationRequestDto(String userName) {
        this.userName = userName;
    }
}
