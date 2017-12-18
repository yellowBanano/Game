package service;

import dao.AvatarDao;
import dto.avatar.CreateNewAvatarDto;
import dto.avatar.ViewAvatarFullInfoDto;
import dto.avatar.ViewAvatarGeneralInfoDto;
import entity.Avatar;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AvatarService {
    private static AvatarService INSTANCE = null;

    private AvatarService() {}

    public static AvatarService getInstance() {
        if (INSTANCE == null) {
            synchronized (AvatarService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AvatarService();
                }
            }
        }
        return INSTANCE;
    }

    public List<ViewAvatarGeneralInfoDto> getAllAvatars() {
        return AvatarDao.getInstance().findAll().stream()
                .map(avatar -> new ViewAvatarGeneralInfoDto(avatar.getId(), avatar.getName()))
                .collect(Collectors.toList());
    }

    public long createNewAvatar(CreateNewAvatarDto dto) {
        return AvatarDao.getInstance().create(new Avatar(dto.getName(), dto.getGender(), dto.getLevel(),
                dto.getEXP(), dto.getMoney(), dto.getHP(), dto.getMP(), dto.getSTR(), dto.getDEF(),
                dto.getSpellPower(), dto.getMagicResistance(), dto.getHitChance(), dto.getEvadeChance(),
                dto.getCritChance(), dto.getMultiplierCrit()), dto.getIdArmor(), dto.getIdWeapon(), dto.getIdAvatarClass());
    }

    public ViewAvatarFullInfoDto getFullInfo(long id) {
        Optional<Avatar> avatarOptional = AvatarDao.getInstance().findById(id);
        if (!avatarOptional.isPresent()) {
            throw new IllegalArgumentException("No avatar with provided id!");
        }
        Avatar avatar = avatarOptional.get();
        return new ViewAvatarFullInfoDto(avatar.getName(), avatar.getGender().toString(),
                avatar.getLevel(), avatar.getEXP(), avatar.getMoney(), avatar.getHP(), avatar.getMP(),
                avatar.getSTR(), avatar.getDEF(), avatar.getSpellPower(), avatar.getMagicResistance(),
                avatar.getHitChance(), avatar.getEvadeChance(), avatar.getCritChance(), avatar.getMultiplierCrit(),
                avatar.getArmor().getName(), avatar.getWeapon().getName(), avatar.getAvatarClass().getName());
    }
}
