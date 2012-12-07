package ousl.group4.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PromotionCampaign {

    private String type;
    private String user;
    private String message;
    private List<MultipartFile> files;
    private MultipartFile spreadsheet;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public MultipartFile getSpreadsheet() {
        return spreadsheet;
    }

    public void setSpreadsheet(MultipartFile spreadsheet) {
        this.spreadsheet = spreadsheet;
    }
}
