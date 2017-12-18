package dao;

import connection.ConnectionManager;
import entity.Inventory;
import entity.Item;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class InventoryDao {
    private static InventoryDao INSTANCE = null;

    public static InventoryDao getInstance() {
        if (INSTANCE == null) {
            synchronized (InventoryDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new InventoryDao();
                }
            }
        }
        return INSTANCE;
    }

    public void create(Inventory inventory) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO inventories (id_avatar, id_item) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, inventory.getAvatar().getId());
                preparedStatement.setLong(2, inventory.getItem().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Inventory inventory) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "DELETE FROM inventories WHERE id_avatar = ? AND id_item = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, inventory.getAvatar().getId());
                preparedStatement.setLong(2, inventory.getItem().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> findAllById(long idAvatar) {
        List<Item> items = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM items JOIN inventories ON items.id = inventories.id_item WHERE id_avatar = ?";
            createItem(idAvatar, items, connection, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static void createItem(long idAvatar, List<Item> items, Connection connection, String sql) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, idAvatar);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(ItemDao.createItemFromResultSet(resultSet));
                }
            }
        }
    }
}
