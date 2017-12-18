package dto.avatar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAvatarInfoDto {
    private int level;
    private int EXP;
    private int money;
    private int HP;
    private int MP;
    private int STR;
    private int DEF;
    private int spellPower;
    private double magicResistance;
    private double hitChance;
    private double evadeChance;
    private double critChance;
    private double multiplierCrit;
    private long idArmor;
    private long idWeapon;
}
