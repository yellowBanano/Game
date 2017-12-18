package service;

import dao.SpellDao;
import dto.spell.ViewSpellFullInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class SpellService {
    private static SpellService INSTANCE = null;

    private SpellService() {}

    public static SpellService getInstance() {
        if (INSTANCE == null) {
            synchronized (SpellService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SpellService();
                }
            }
        }
        return INSTANCE;
    }

    public List<ViewSpellFullInfoDto> getAllSpell() {
        return SpellDao.getInstance().findAll().stream()
                .map(spell -> new ViewSpellFullInfoDto(spell.getName(), spell.getPower(), spell.getCost()))
                .collect(Collectors.toList());
    }
}
