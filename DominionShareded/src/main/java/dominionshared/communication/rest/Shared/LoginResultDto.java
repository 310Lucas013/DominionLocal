package dominionshared.communication.rest.Shared;

import dominionshared.communication.rest.dto.BaseResultDto;
import dominionshared.models.Player;

public class LoginResultDto extends BaseResultDto {
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
