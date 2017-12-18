package dao;

import connection.ConnectionManager;
import entity.Item;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class ItemDao {
    private static final String ITEMS_TABLE_NAME = "items";
    private static ItemDao INSTANCE = null;

    public static ItemDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ItemDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ItemDao();
                }
            }
        }
        return INSTANCE;
    }

//    public void create(Item item) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "INSERT INTO items (name, cost) VALUES (?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, item.getName());
//                preparedStatement.setInt(2, item.getCost());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void update(Item item) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "UPDATE items SET name = ?, cost = ? WHERE id = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, item.getName());
//                preparedStatement.setInt(2, item.getCost());
//                preparedStatement.setLong(3, item.getId());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM items";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        items.add(createItemFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Optional<Item> findById(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM items WHERE items.id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(createItemFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Item createItemFromResultSet(ResultSet resultSet) throws SQLException {
        return new Item(
                resultSet.getLong(ITEMS_TABLE_NAME + ".id"),
                resultSet.getString(ITEMS_TABLE_NAME + ".name"),
                resultSet.getInt(ITEMS_TABLE_NAME + ".cost"));
    }
}
