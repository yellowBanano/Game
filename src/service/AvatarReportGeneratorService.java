package service;

import entity.report.AvatarReport;

public class AvatarReportGeneratorService {
    private static AvatarReportGeneratorService INSTANCE = null;

    private AvatarReportGeneratorService() {}

    public static AvatarReportGeneratorService getInstance() {
        if (INSTANCE == null) {
            synchronized (AvatarReportGeneratorService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AvatarReportGeneratorService();
                }
            }
        }
        return INSTANCE;
    }

    public String generateReport(long idAvatar) {
        return new AvatarReport().createReport(idAvatar).toString();
    }
}
