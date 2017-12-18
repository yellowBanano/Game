package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeOfNpc extends BaseEntity {
    private String name;
    private double multiplierHP;
    private double multiplierMP;
    private double multiplierATK;
    private double multiplierDEF;
    private double spellPowerMultiplier;
    private double magicResistance;
    private double hitChance;
    private double evadeChance;
    private double critChance;
    private double critMultiplier;

    public TypeOfNpc(long id, String name, double multiplierHP, double multiplierMP, double multiplierATK,
                     double multiplierDEF, double spellPowerMultiplier, double magicResistance, double hitChance,
                     double evadeChance, double critChance, double critMultiplier) {
        super(id);
        this.name = name;
        this.multiplierHP = multiplierHP;
        this.multiplierMP = multiplierMP;
        this.multiplierATK = multiplierATK;
        this.multiplierDEF = multiplierDEF;
        this.spellPowerMultiplier = spellPowerMultiplier;
        this.magicResistance = magicResistance;
        this.hitChance = hitChance;
        this.evadeChance = evadeChance;
        this.critChance = critChance;
        this.critMultiplier = critMultiplier;
    }
}
