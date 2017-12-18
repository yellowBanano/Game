package dao;

import connection.ConnectionManager;
import entity.Armor;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class ArmorDao {
    public static final String ARMORS_TABLE_NAME = "armors";
    private static ArmorDao INSTANCE = null;

    public static ArmorDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ArmorDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ArmorDao();
                }
            }
        }
        return INSTANCE;
    }

//    public void create(Armor armor) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "INSERT INTO armors (name, DEF, magic_resistance) VALUES (?, ?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, armor.getName());
//                preparedStatement.setInt(2, armor.getDEF());
//                preparedStatement.setDouble(3, armor.getMagicResistance());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void update(Armor armor) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "UPDATE armors SET name = ?, DEF = ?, magic_resistance = ? WHERE id = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, armor.getName());
//                preparedStatement.setInt(2, armor.getDEF());
//                preparedStatement.setDouble(3, armor.getMagicResistance());
//                preparedStatement.setLong(4, armor.getId());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Armor> findAll() {
        List<Armor> armors = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM armors";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        armors.add(createArmorFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return armors;
    }

    public Optional<Armor> findById(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM armors WHERE armors.id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(createArmorFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Armor createArmorFromResultSet(ResultSet resultSet) throws SQLException {
        return new Armor(
                resultSet.getLong(ARMORS_TABLE_NAME + ".id"),
                resultSet.getString(ARMORS_TABLE_NAME + ".name"),
                resultSet.getInt(ARMORS_TABLE_NAME + ".DEF"),
                resultSet.getDouble(ARMORS_TABLE_NAME + ".magic_resistance"));
    }
}