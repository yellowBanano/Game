package service;

import dao.NpcDao;
import dto.npc.CreateNewNpcDto;
import dto.npc.ViewNpcFullInfoDto;
import dto.npc.ViewNpcGeneralInfoDto;
import entity.Npc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NpcService {
    private static NpcService INSTANCE = null;

    private NpcService() {}

    public static NpcService getInstance() {
        if (INSTANCE == null) {
            synchronized (NpcService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NpcService();
                }
            }
        }
        return INSTANCE;
    }

    public List<ViewNpcGeneralInfoDto> getAllNpcs() {
        return NpcDao.getInstance().findAll().stream()
                .map(npc -> new ViewNpcGeneralInfoDto(npc.getId(), npc.getName()))
                .collect(Collectors.toList());
    }

    public void createNewNpc(CreateNewNpcDto dto) {
        NpcDao.getInstance().create(new Npc(dto.getName(), dto.getMoney(), dto.getEXP()), dto.getIdType());
    }

    public ViewNpcFullInfoDto getFullInfo(long id) {
        Optional<Npc> npcOptional = NpcDao.getInstance().findById(id);
        if (!npcOptional.isPresent()) {
            throw new IllegalArgumentException("No npc with provided id!");
        }
        Npc npc = npcOptional.get();
        return new ViewNpcFullInfoDto(npc.getName(), npc.getMoney(), npc.getEXP(), npc.getType().getName());
    }
}
