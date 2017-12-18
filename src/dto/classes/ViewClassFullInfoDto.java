package dto.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewClassFullInfoDto {
    private String name;
    private double multiplierHP;
    private double multiplierMP;
    private double multiplierSTR;
    private double multiplierDEF;
    private double spellPowerMultiplier;
}
