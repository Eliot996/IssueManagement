package IssueManagment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileWrangler {
    private final String FILE_PATH_STASH = "data/stash.txt";
    private final String FILE_PATH_BOARD = "data/board.txt";


    public void writeToStash(Issue issue) throws FileNotFoundException{
        PrintStream ps = new PrintStream(new FileOutputStream(FILE_PATH_STASH, true));
        ps.append(issue.toString()).append('\n');
    }

    public void overwriteStash(ArrayList<Issue> issues) throws FileNotFoundException{
        PrintStream ps = new PrintStream(FILE_PATH_STASH);
        for (Issue issue : issues) {
            ps.append(issue.toString()).append('\n');
        }
    }

    public ArrayList<Issue> getAllIssuesFromStash() throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(FILE_PATH_STASH));

        ArrayList<Issue> issues = new ArrayList<>();
        while(scanner.hasNextLine()){
            String issueString = scanner.nextLine();
            issues.add(new Issue(extract("id",           issueString),
                                 extract("title",        issueString),
                                 extract("description",  issueString),
                                 extract("status",       issueString)));
        }

        return issues;
    }

    private String extract(String type, String from){
        // gets the start and end indexes of the value we want
        int indexStart = from.indexOf(type + "='") + type.length() + 2;
        int indexEnd = from.indexOf("'", indexStart);

        return  from.substring(indexStart, indexEnd);
    }


}
