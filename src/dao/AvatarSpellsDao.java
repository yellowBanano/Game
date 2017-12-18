package dao;

import connection.ConnectionManager;
import entity.AvatarSpell;
import entity.Spell;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AvatarSpellsDao {
    private static AvatarSpellsDao INSTANCE = null;

    public static AvatarSpellsDao getInstance() {
        if (INSTANCE == null) {
            synchronized (AvatarSpellsDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AvatarSpellsDao();
                }
            }
        }
        return INSTANCE;
    }

    public void create(AvatarSpell spell) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO avatar_spells (id_avatar, id_spell) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, spell.getAvatar().getId());
                preparedStatement.setLong(2, spell.getSpell().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(AvatarSpell spell) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "DELETE FROM avatar_spells WHERE id_avatar = ? AND id_spell = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, spell.getAvatar().getId());
                preparedStatement.setLong(2, spell.getSpell().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Spell> findAllById(long idAvatar) {
        List<Spell> spells = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM spells JOIN avatar_spells ON spells.id = avatar_spells.id_spell WHERE id_avatar = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, idAvatar);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        spells.add(SpellDao.createSpellFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spells;
    }
}
