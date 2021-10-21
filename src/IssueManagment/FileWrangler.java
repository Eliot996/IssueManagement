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
            issues.add(new Issue(scanner.nextLine()));
        }

        return issues;
    }


}
