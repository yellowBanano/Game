package dao;

import connection.ConnectionManager;
import entity.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClassDao {
    private static final String CLASSES_TABLE_NAME = "classes";
    private static ClassDao INSTANCE = null;

    public static ClassDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ClassDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClassDao();
                }
            }
        }
        return INSTANCE;
    }

//    public void create(Class avatarClass) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "INSERT INTO classes (name, HP_multiplier, MP_multiplier, STR_multiplier," +
//                    " DEF_multiplier, spell_power_multiplier) VALUES (?, ?, ?, ?, ?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, avatarClass.getName());
//                preparedStatement.setDouble(2, avatarClass.getMultiplierHP());
//                preparedStatement.setDouble(3, avatarClass.getMultiplierMP());
//                preparedStatement.setDouble(4, avatarClass.getMultiplierSTR());
//                preparedStatement.setDouble(5, avatarClass.getMultiplierDEF());
//                preparedStatement.setDouble(6, avatarClass.getSpellPowerMultiplier());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void update(Class avatarClass) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "UPDATE classes SET name = ?, HP_multiplier = ?, MP_multiplier = ?, STR_multiplier = ?," +
//                    " DEF_multiplier = ?, spell_power_multiplier = ?  WHERE id = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, avatarClass.getName());
//                preparedStatement.setDouble(2, avatarClass.getMultiplierHP());
//                preparedStatement.setDouble(3, avatarClass.getMultiplierMP());
//                preparedStatement.setDouble(4, avatarClass.getMultiplierSTR());
//                preparedStatement.setDouble(5, avatarClass.getMultiplierDEF());
//                preparedStatement.setDouble(6, avatarClass.getSpellPowerMultiplier());
//                preparedStatement.setLong(7, avatarClass.getId());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Class> findAll() {
        List<Class> classes = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM classes";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        classes.add(createClassFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public Optional<Class> findById(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM classes WHERE classes.id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(createClassFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Class createClassFromResultSet(ResultSet resultSet) throws SQLException {
        return new Class(
                resultSet.getLong(CLASSES_TABLE_NAME + ".id"),
                resultSet.getString(CLASSES_TABLE_NAME + ".name"),
                resultSet.getDouble(CLASSES_TABLE_NAME + ".HP_multiplier"),
                resultSet.getDouble(CLASSES_TABLE_NAME + ".MP_multiplier"),
                resultSet.getDouble(CLASSES_TABLE_NAME + ".STR_multiplier"),
                resultSet.getDouble(CLASSES_TABLE_NAME + ".DEF_multiplier"),
                resultSet.getDouble(CLASSES_TABLE_NAME + ".spell_power_multiplier"));
    }
}
