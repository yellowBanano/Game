package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Npc extends BaseEntity {
    private String name;
    private int money;
    private int EXP;
    private TypeOfNpc type;

    public Npc(long id, String name, int money, int EXP, TypeOfNpc type) {
        super(id);
        this.name = name;
        this.money = money;
        this.EXP = EXP;
        this.type = type;
    }

    public Npc(String name, int money, int EXP) {
        this.name = name;
        this.money = money;
        this.EXP = EXP;
    }
}
