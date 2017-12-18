package dto.npc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewNpcFullInfoDto {
    private String name;
    private int money;
    private int EXP;
    private String typeName;
}
