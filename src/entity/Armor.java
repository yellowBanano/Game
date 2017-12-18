package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Armor extends BaseEntity {
    private String name;
    private int DEF;
    private double magicResistance;

    public Armor(long id, String name, int DEF, double magicResistance) {
        super(id);
        this.name = name;
        this.DEF = DEF;
        this.magicResistance = magicResistance;
    }
}
