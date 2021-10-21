package IssueManagment;

import java.util.UUID;

public class Issue {

    private final UUID id;
    private String title;
    private String description;
    private StatusCode status;

    public Issue(String title, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.status = StatusCode.PENDING;
    }

    public Issue(String id, String title, String description, String status) { // takes the raw string and converts it into an Issue
        this.id = UUID.fromString(id);
        this.title = title;
        this.description = description;
        this.status = StatusCode.valueOf(status);
    }

    @Override
    public String toString() {
        return "{id='" + id.toString() + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }



    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public StatusCode getStatus() {
        return status;
    }

    public void setStatus(StatusCode status) {
        this.status = status;
    }
}
