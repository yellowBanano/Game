package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avatar extends BaseEntity {
    private String name;
    private Gender gender = Gender.MALE;
    private int level = 1;
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
    private Armor armor;
    private Weapon weapon;
    private Class avatarClass;

    public Avatar(long id, String name, Gender gender, int level, int EXP, int money, int HP, int MP, int STR, int DEF,
                  int spellPower, double magicResistance, double hitChance, double evadeChance, double critChance,
                  double multiplierCrit, Armor armor, Weapon weapon, Class avatarClass) {
        super(id);
        this.name = name;
        this.gender = gender;
        this.level = level;
        this.EXP = EXP;
        this.money = money;
        this.HP = HP;
        this.MP = MP;
        this.STR = STR;
        this.DEF = DEF;
        this.spellPower = spellPower;
        this.magicResistance = magicResistance;
        this.hitChance = hitChance;
        this.evadeChance = evadeChance;
        this.critChance = critChance;
        this.multiplierCrit = multiplierCrit;
        this.armor = armor;
        this.weapon = weapon;
        this.avatarClass = avatarClass;
    }

    public Avatar(String name, Gender gender, int level, int EXP, int money, int HP,
                  int MP, int STR, int DEF, int spellPower, double magicResistance,
                  double hitChance, double evadeChance, double critChance, double multiplierCrit) {
        this.name = name;
        this.gender = gender;
        this.level = level;
        this.EXP = EXP;
        this.money = money;
        this.HP = HP;
        this.MP = MP;
        this.STR = STR;
        this.DEF = DEF;
        this.spellPower = spellPower;
        this.magicResistance = magicResistance;
        this.hitChance = hitChance;
        this.evadeChance = evadeChance;
        this.critChance = critChance;
        this.multiplierCrit = multiplierCrit;
    }
}
