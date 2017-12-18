package dto.npc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewNpcGeneralInfoDto {
    private long id;
    private String name;
}
