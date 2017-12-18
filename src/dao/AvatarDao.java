package dao;

import connection.ConnectionManager;
import entity.Avatar;
import entity.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class AvatarDao {

    private static final String AVATARS_TABLE_NAME = "avatars";
    private static AvatarDao INSTANCE = null;

    private AvatarDao() {
    }

    public static AvatarDao getInstance() {
        if (INSTANCE == null) {
            synchronized (AvatarDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AvatarDao();
                }
            }
        }
        return INSTANCE;
    }

    public long create(Avatar avatar, long idArmor, long idWeapon, long idClass) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO avatars (name, gender, level, EXP, money, HP, MP, STR, DEF, spell_power," +
                    " magic_resistance, hit_chance, evade_chance, crit_chance, multiplier_crit," +
                    " id_armor, id_weapon, id_class) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, avatar.getName());
                preparedStatement.setString(2, avatar.getGender().toString());
                preparedStatement.setInt(3, avatar.getLevel());
                preparedStatement.setInt(4, avatar.getEXP());
                preparedStatement.setInt(5, avatar.getMoney());
                preparedStatement.setInt(6, avatar.getHP());
                preparedStatement.setInt(7, avatar.getMP());
                preparedStatement.setInt(8, avatar.getSTR());
                preparedStatement.setInt(9, avatar.getDEF());
                preparedStatement.setInt(10, avatar.getSpellPower());
                preparedStatement.setDouble(11, avatar.getMagicResistance());
                preparedStatement.setDouble(12, avatar.getHitChance());
                preparedStatement.setDouble(13, avatar.getEvadeChance());
                preparedStatement.setDouble(14, avatar.getCritChance());
                preparedStatement.setDouble(15, avatar.getMultiplierCrit());
                preparedStatement.setLong(16, idArmor);
                preparedStatement.setLong(17, idWeapon);
                preparedStatement.setLong(18, idClass);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idArmor;
    }

    public void update(Avatar avatar, long idArmor, long idWeapon) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "UPDATE avatars SET level = ?, EXP = ?, money = ?, HP = ?, MP = ?," +
                    " STR = ?, DEF = ?, spell_power = ?, magic_resistance = ?, hit_chance = ?, evade_chance = ?," +
                    " crit_chance = ?, multiplier_crit = ?, id_armor = ?, id_weapon = ?" +
                    " WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, avatar.getLevel());
                preparedStatement.setInt(2, avatar.getEXP());
                preparedStatement.setInt(3, avatar.getMoney());
                preparedStatement.setInt(4, avatar.getHP());
                preparedStatement.setInt(5, avatar.getMP());
                preparedStatement.setInt(6, avatar.getSTR());
                preparedStatement.setInt(7, avatar.getDEF());
                preparedStatement.setInt(8, avatar.getSpellPower());
                preparedStatement.setDouble(9, avatar.getMagicResistance());
                preparedStatement.setDouble(10, avatar.getHitChance());
                preparedStatement.setDouble(11, avatar.getEvadeChance());
                preparedStatement.setDouble(12, avatar.getCritChance());
                preparedStatement.setDouble(13, avatar.getMultiplierCrit());
                preparedStatement.setLong(14, idArmor);
                preparedStatement.setLong(15, idWeapon);
                preparedStatement.setLong(16, avatar.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Avatar> findAll() {
        List<Avatar> avatars = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM avatars JOIN armors ON avatars.id_armor = armors.id " +
                    "JOIN weapons ON avatars.id_weapon = weapons.id " +
                    "JOIN classes ON avatars.id_class = classes.id ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        avatars.add(createAvatarFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avatars;
    }

    public Optional<Avatar> findById(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM avatars JOIN armors ON avatars.id_armor = armors.id " +
                    "JOIN weapons ON avatars.id_weapon = weapons.id " +
                    "JOIN classes ON avatars.id_class = classes.id WHERE avatars.id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(createAvatarFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Avatar createAvatarFromResultSet(ResultSet resultSet) throws SQLException {
        return new Avatar(
                resultSet.getLong(AVATARS_TABLE_NAME + ".id"),
                resultSet.getString(AVATARS_TABLE_NAME + ".name"),
                Gender.valueOf(resultSet.getString(AVATARS_TABLE_NAME + ".gender")),
                resultSet.getInt(AVATARS_TABLE_NAME + ".level"),
                resultSet.getInt(AVATARS_TABLE_NAME + ".EXP"),
                resultSet.getInt(AVATARS_TABLE_NAME + ".money"),
                resultSet.getInt(AVATARS_TABLE_NAME + ".HP"),
                resultSet.getInt(AVATARS_TABLE_NAME + ".MP"),
                resultSet.getInt(AVATARS_TABLE_NAME + ".STR"),
                resultSet.getInt(AVATARS_TABLE_NAME + ".DEF"),
                resultSet.getInt(AVATARS_TABLE_NAME + ".spell_power"),
                resultSet.getDouble(AVATARS_TABLE_NAME + ".magic_resistance"),
                resultSet.getDouble(AVATARS_TABLE_NAME + ".hit_chance"),
                resultSet.getDouble(AVATARS_TABLE_NAME + ".evade_chance"),
                resultSet.getDouble(AVATARS_TABLE_NAME + ".crit_chance"),
                resultSet.getDouble(AVATARS_TABLE_NAME + ".multiplier_crit"),
                ArmorDao.createArmorFromResultSet(resultSet),
                WeaponDao.createWeaponFromResultSet(resultSet),
                ClassDao.createClassFromResultSet(resultSet));
    }
}
