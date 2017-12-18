package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weapon extends BaseEntity {
    private String name;
    private int ATK;

    public Weapon(long id, String name, int ATK) {
        super(id);
        this.name = name;
        this.ATK = ATK;
    }
}
