package service;

import dao.AvatarSpellsDao;
import dto.spell.ViewSpellFullInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class AvatarSpellsService {
    private static AvatarSpellsService INSTANCE = null;

    private AvatarSpellsService() {}

    public static AvatarSpellsService getInstance() {
        if (INSTANCE == null) {
            synchronized (AvatarSpellsService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AvatarSpellsService();
                }
            }
        }
        return INSTANCE;
    }

    public List<ViewSpellFullInfoDto> getAllSpell(long idAvatar) {
        return AvatarSpellsDao.getInstance().findAllById(idAvatar).stream()
                .map(spell -> new ViewSpellFullInfoDto(spell.getName(), spell.getPower(), spell.getCost()))
                .collect(Collectors.toList());
    }
}
