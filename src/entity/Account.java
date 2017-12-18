package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {
    private String email;
    private String login;
    private String password;
    private Avatar avatar;

    public Account(long id, String email, String login, String password, Avatar avatar) {
        super(id);
        this.email = email;
        this.login = login;
        this.password = password;
        this.avatar = avatar;
    }

    public Account(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
