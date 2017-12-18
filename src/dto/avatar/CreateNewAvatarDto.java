package dto.avatar;

import entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewAvatarDto {
    private String name;
    private Gender gender = Gender.MALE;
    private int level = 1;
    private int EXP = 0;
    private int money = 0;
    private int HP = 100;
    private int MP = 100;
    private int STR = 10;
    private int DEF = 10;
    private int spellPower = 5;
    private double magicResistance = 0.01;
    private double hitChance = 0.9;
    private double evadeChance = 0.05;
    private double critChance = 0.1;
    private double multiplierCrit = 1.5;
    private long idArmor = 1;
    private long idWeapon = 1;
    private long idAvatarClass = 1;

    public CreateNewAvatarDto(String name, String gender, long idAvatarClass) {
        this.name = name;
        this.gender = Gender.valueOf(gender);
        this.idAvatarClass = idAvatarClass;
    }
}
