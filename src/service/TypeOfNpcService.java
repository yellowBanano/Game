package service;

import dao.TypeOfNpcDao;
import dto.types_of_npc.ViewTypeGeneralInfoDto;
import entity.TypeOfNpc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TypeOfNpcService {
    private static TypeOfNpcService INSTANCE = null;

    private TypeOfNpcService() {}

    public static TypeOfNpcService getInstance() {
        if (INSTANCE == null) {
            synchronized (TypeOfNpcService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TypeOfNpcService();
                }
            }
        }
        return INSTANCE;
    }

    public List<ViewTypeGeneralInfoDto> getAllTypes() {
        return TypeOfNpcDao.getInstance().findAll().stream()
                .map(type -> new ViewTypeGeneralInfoDto(type.getId(), type.getName()))
                .collect(Collectors.toList());
    }

    public TypeOfNpc getFullInfo(long id) {
        Optional<TypeOfNpc> typeOfNpcOptional = TypeOfNpcDao.getInstance().findById(id);
        if (!typeOfNpcOptional.isPresent()) {
            throw new IllegalArgumentException("No type with provided id!");
        }
        TypeOfNpc typeOfNpc = typeOfNpcOptional.get();
        return new TypeOfNpc(typeOfNpc.getName(), typeOfNpc.getMultiplierHP(), typeOfNpc.getMultiplierMP(),
                typeOfNpc.getMultiplierATK(), typeOfNpc.getMultiplierDEF(), typeOfNpc.getSpellPowerMultiplier(),
                typeOfNpc.getMagicResistance(), typeOfNpc.getHitChance(), typeOfNpc.getEvadeChance(),
                typeOfNpc.getCritChance(), typeOfNpc.getCritMultiplier());
    }
}
