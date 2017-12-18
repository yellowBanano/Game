package dto.npc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewNpcDto {
    private String name;
    private int money;
    private int EXP;
    private long idType;
}
