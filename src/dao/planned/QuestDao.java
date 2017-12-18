//package dao;
//
//import connection.ConnectionManager;
//import entity.Npc;
//import entity.Quest;
//import entity.TypeOfNpc;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class QuestDao {
//    private static final String QUESTS_TABLE_NAME = "quests";
//    private static QuestDao INSTANCE = null;
//
//    public static QuestDao getInstance() {
//        if (INSTANCE == null) {
//            synchronized (QuestDao.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new QuestDao();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    public List<Quest> findAll() {
//        List<Quest> quests = new ArrayList<>();
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "SELECT * FROM quests";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        quests.add(createNpcFromResultSet(resultSet));
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return quests;
//    }
//
//    public Optional<Quest> findById(long id) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "SELECT * FROM quests WHERE quests.id = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setLong(1, id);
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    if (resultSet.next()) {
//                        return Optional.of(createNpcFromResultSet(resultSet));
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return Optional.empty();
//    }
//
//    private Quest createNpcFromResultSet(ResultSet resultSet) throws SQLException {
//        return new Quest(
//                resultSet.getLong(QUESTS_TABLE_NAME + ".id"),
//                resultSet.getString(QUESTS_TABLE_NAME + ".name"),
//                resultSet.getString(QUESTS_TABLE_NAME + ".description"),
//                new TypeOfNpc());
//    }
//}
