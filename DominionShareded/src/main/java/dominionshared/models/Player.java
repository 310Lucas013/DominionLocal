package dominionshared.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Players")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "name", columnDefinition = "NVARCHAR2(255) NOT NULL UNIQUE")
    private String name;
    @Column(name = "password", columnDefinition = "NVARCHAR2(255) NOT NULL")
    private String password;
    @Transient
    private int number;
    @Transient
    private List<Card> allCards;
    @Transient
    private List<Card> stockCards;
    @Transient
    private List<Card> handCards;
    @Transient
    private List<Card> usedCards;
    @Transient
    private boolean ready;

    public Player() {
    }

    public Player(String username) {
        this.name = username;
    }

    public Player(String username, String password) {
        this.name = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}

