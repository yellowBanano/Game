package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity {
    private String name;
    private int cost;

    public Item(long id, String name, int cost) {
        super(id);
        this.name = name;
        this.cost = cost;
    }
}
