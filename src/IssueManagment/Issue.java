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

    public Issue(String fromFileWrangler) { // takes the raw string and converts it into an Issue
        this.id = UUID.fromString(extract("id", fromFileWrangler));
        this.title = extract("title", fromFileWrangler);
        this.description = extract("description", fromFileWrangler);
        this.status = StatusCode.valueOf(extract("status", fromFileWrangler));
    }

    @Override
    public String toString() {
        return "{id='" + id.toString() + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    private String extract(String string, String from){
        // gets the start and end indexes of the value we want
        int indexStart = from.indexOf(string + "='") + string.length() + 2;
        int indexEnd = from.indexOf("'", indexStart);

        return  from.substring(indexStart, indexEnd);
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
