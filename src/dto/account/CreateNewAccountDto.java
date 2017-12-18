package dto.account;

import entity.Avatar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewAccountDto {
    private String email;
    private String login;
    private String password;
    private long idAvatar;
}
