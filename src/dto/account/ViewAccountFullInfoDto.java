package dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewAccountFullInfoDto {
    private String email;
    private String login;
    private String password;
    private String avatarName;
    private long avatarId;
}
