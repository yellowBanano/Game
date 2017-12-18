package servlet;

import service.AvatarReportGeneratorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/report-download")
public class DownloadServlet extends HttpServlet {
    private static final int BYTES_DOWNLOAD = 1024;
    private static final long serialVersionUID = 6389607689490085582L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String report = AvatarReportGeneratorService.getInstance().generateReport((long)request.getSession().getAttribute("idAvatar"));
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment;filename=report.txt");
        InputStream inputStream = new ByteArrayInputStream(report.getBytes("UTF8"));
        int read;
        byte[] bytes = new byte[BYTES_DOWNLOAD];
        OutputStream outputStream = response.getOutputStream();
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        outputStream.flush();
        outputStream.close();
    }
}
