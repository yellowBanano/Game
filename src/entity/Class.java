package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class extends BaseEntity {
    private String name;
    private double multiplierHP;
    private double multiplierMP;
    private double multiplierSTR;
    private double multiplierDEF;
    private double spellPowerMultiplier;

    public Class(long id, String name, double multiplierHP, double multiplierMP,
                 double multiplierSTR, double multiplierDEF, double spellPowerMultiplier) {
        super(id);
        this.name = name;
        this.multiplierHP = multiplierHP;
        this.multiplierMP = multiplierMP;
        this.multiplierSTR = multiplierSTR;
        this.multiplierDEF = multiplierDEF;
        this.spellPowerMultiplier = spellPowerMultiplier;
    }
}
