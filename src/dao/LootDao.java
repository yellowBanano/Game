package dao;

import connection.ConnectionManager;
import entity.Item;
import entity.Loot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LootDao {
    private static LootDao INSTANCE = null;

    public static LootDao getInstance() {
        if (INSTANCE == null) {
            synchronized (LootDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LootDao();
                }
            }
        }
        return INSTANCE;
    }

    public void create(Loot loot) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO loot (id_NPC, id_item) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, loot.getNpc().getId());
                preparedStatement.setLong(2, loot.getItem().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Loot loot) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "DELETE FROM loot WHERE id_NPC = ? AND id_item = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, loot.getNpc().getId());
                preparedStatement.setLong(2, loot.getItem().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> findAllById(long idNpc) {
        List<Item> items = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM items JOIN loot ON items.id = loot.id_item WHERE id_NPC = ?";
            InventoryDao.createItem(idNpc, items, connection, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
