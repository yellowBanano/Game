package entity.report;

import dto.avatar.ViewAvatarFullInfoDto;
import service.AvatarService;

public class AvatarReport implements Report<ViewAvatarFullInfoDto> {
    @Override
    public ViewAvatarFullInfoDto createReport(long id) {
        return AvatarService.getInstance().getFullInfo(id);
    }
}
