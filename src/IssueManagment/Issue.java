package IssueManagment;

import java.util.UUID;

public class Issue {

    private UUID id;
    private String title;
    private String description;
    private String status;

    public Issue(String title, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.status = "PENDING";
    }

    public Issue(String fromFileWrangler) { // takes the raw string and converts it into an Issue
        this.id = UUID.fromString(extract("id", fromFileWrangler));
        this.title = extract("title", fromFileWrangler);
        this.description = extract("description", fromFileWrangler);
        this.status = extract("status", fromFileWrangler);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
