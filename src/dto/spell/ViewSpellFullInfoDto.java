package dto.spell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewSpellFullInfoDto {
    private String name;
    private double power;
    private double cost;
}
