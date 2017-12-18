package service;

import dao.ArmorDao;
import entity.Armor;

import java.util.List;

public class ArmorService {
    private static ArmorService INSTANCE = null;

    private ArmorService() {}

    public static ArmorService getInstance() {
        if (INSTANCE == null) {
            synchronized (ArmorService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ArmorService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Armor> getAllArmor() {
        return ArmorDao.getInstance().findAll();
    }
}
