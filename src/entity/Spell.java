package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spell extends BaseEntity {
    private String name;
    private double power;
    private int cost;

    public Spell(long id, String name, double power, int cost) {
        super(id);
        this.name = name;
        this.power = power;
        this.cost = cost;
    }
}
