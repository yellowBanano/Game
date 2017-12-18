package dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewAccountGeneralInfo {
    private long id;
    private String email;
    private String login;
}
