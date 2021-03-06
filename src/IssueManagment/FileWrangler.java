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


    public void writeToFile(Issue issue, String fileName) throws FileNotFoundException{
        String filePath = setFilePathFromString(fileName);


        PrintStream ps = new PrintStream(new FileOutputStream(filePath, true));
        ps.append(issue.toString()).append('\n');
    }

    public void overwriteFile(ArrayList<Issue> issues, String fileName) throws FileNotFoundException{
        String filePath = setFilePathFromString(fileName);

        PrintStream ps = new PrintStream(filePath);
        for (Issue issue : issues) {
            ps.append(issue.toString()).append('\n');
        }
    }

    public ArrayList<Issue> getAllIssuesFromFile(String fileName) throws FileNotFoundException{
        String filePath = setFilePathFromString(fileName);

        Scanner scanner = new Scanner(new File(filePath));

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

    private String setFilePathFromString(String string){
        return switch (string) {
            case "stash" -> FILE_PATH_STASH;
            case "board" -> FILE_PATH_BOARD;
            default -> throw new IllegalArgumentException();
        };
    }
}
