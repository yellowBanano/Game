package service;

import dao.WeaponDao;
import entity.Weapon;

import java.util.List;

public class WeaponService {
    private static WeaponService INSTANCE = null;

    private WeaponService() {}

    public static WeaponService getInstance() {
        if (INSTANCE == null) {
            synchronized (WeaponService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WeaponService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Weapon> getAllWeapon() {
        return WeaponDao.getInstance().findAll();
    }
}
