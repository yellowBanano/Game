package service;

import dao.NpcSpellsDao;
import dto.spell.ViewSpellFullInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class NpcSpellsService {
    private static NpcSpellsService INSTANCE = null;

    private NpcSpellsService() {}

    public static NpcSpellsService getInstance() {
        if (INSTANCE == null) {
            synchronized (NpcSpellsService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NpcSpellsService();
                }
            }
        }
        return INSTANCE;
    }

    public List<ViewSpellFullInfoDto> getAllSpell(long idNpc) {
        return NpcSpellsDao.getInstance().findAllById(idNpc).stream()
                .map(spell -> new ViewSpellFullInfoDto(spell.getName(), spell.getPower(), spell.getCost()))
                .collect(Collectors.toList());
    }
}
