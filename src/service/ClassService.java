package service;

import dao.ClassDao;
import dto.classes.ViewClassFullInfoDto;
import dto.classes.ViewClassGeneralInfoDto;
import entity.Class;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClassService {
    private static ClassService INSTANCE = null;

    private ClassService() {}

    public static ClassService getInstance() {
        if (INSTANCE == null) {
            synchronized (ClassService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClassService();
                }
            }
        }
        return INSTANCE;
    }

    public List<ViewClassGeneralInfoDto> getAllClass() {
        return ClassDao.getInstance().findAll().stream()
                .map(aClass -> new ViewClassGeneralInfoDto(aClass.getId(), aClass.getName()))
                .collect(Collectors.toList());
    }

    public ViewClassFullInfoDto getFullInfo(long id) {
        Optional<Class> classOptional = ClassDao.getInstance().findById(id);
        if (!classOptional.isPresent()) {
            throw new IllegalArgumentException("No class with provided id!");
        }
        Class aClass = classOptional.get();
        return new ViewClassFullInfoDto(aClass.getName(), aClass.getMultiplierHP(), aClass.getMultiplierMP(),
                aClass.getMultiplierSTR(), aClass.getMultiplierDEF(), aClass.getSpellPowerMultiplier());
    }
}
