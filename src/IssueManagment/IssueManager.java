package IssueManagment;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class IssueManager {

    private final FileWrangler fileWrangler = new FileWrangler();
    private final UserInterface userInterface = new UserInterface();

    public void run(){

        boolean stop = false;
        int command;
        while(!stop){
            userInterface.printMainMenu();
            command = userInterface.getCommandFromUser();

            switch (command){
                case 1: // Create issue
                    createIssue();
                    break;

                case 2: // validate issue
                    validateIssue();
                    break;

                case 20: // change status to any status
                       changeStatusOfIssue();

                case 3: // print all issues
                    try {
                        userInterface.print(fileWrangler.getAllIssuesFromStash());
                    } catch (FileNotFoundException e) {
                        userInterface.print(e);
                    }
                    break;

                case 0: // exit
                    stop = true;
                    break;

                default:
                    userInterface.print("Invalid input!", true);
            }
        }



    }

    private void changeStatusOfIssue() {
        try {
            ArrayList<Issue> issues = fileWrangler.getAllIssuesFromStash();
            userInterface.print(issues);
            userInterface.print("Which one do you wish to change the status of?(by number):");
            int target = userInterface.getCommandFromUser();

            userInterface.print("""
                    What do you want it changed to?:\s
                    1. PENDING
                    2. APPROVED
                    3. REJECTED
                    0. Cancel change of status
                    """);
            int changeTo = userInterface.getCommandFromUser();

            switch (changeTo){
                case 1 -> issues.get(target).setStatus(StatusCode.PENDING);
                case 2 -> issues.get(target).setStatus(StatusCode.APPROVED);
                case 3 -> issues.get(target).setStatus(StatusCode.REJECTED);
            }

            fileWrangler.overwriteStash(issues);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void validateIssue(){
        try {
            ArrayList<Issue> issues = fileWrangler.getAllIssuesFromStash();
            ArrayList<Issue> pendingIssues = new ArrayList<>();

            for (Issue issue : issues) {
                if (issue.getStatus() == StatusCode.PENDING) pendingIssues.add(issue);
            }

            userInterface.print(pendingIssues);
            userInterface.print("Which one do you wish to change the status of?(by number):");
            int target = userInterface.getCommandFromUser();

            userInterface.print("""
                    What do you want it changed to?:\s
                    1. APPROVED
                    2. REJECTED
                    0. Cancel change of status
                    """);
            int changeTo = userInterface.getCommandFromUser();

            switch (changeTo){
                case 1 -> pendingIssues.get(target).setStatus(StatusCode.APPROVED);
                case 2 -> pendingIssues.get(target).setStatus(StatusCode.REJECTED);
            }

            fileWrangler.overwriteStash(issues);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Issue getIssueFromName(String title) throws FileNotFoundException {
        ArrayList<Issue> issues = fileWrangler.getAllIssuesFromStash();
        for (Issue issue : issues) {
            if (issue.getTitle().equals(title)){
                return issue;
            }
        }
        return null;
    }

    public void createIssue(){
        // get title from user
        userInterface.print("Write a title for your new issue: ");
        String title = userInterface.getStringFromUser();

        // get description from user
        userInterface.print("Write a description for your new issue: ");
        String description = userInterface.getStringFromUser();

        // make new issue with the given title and description
        Issue issue = new Issue(title, description);

        // write to the stash
        try {
            fileWrangler.writeToStash(issue);
        }catch (FileNotFoundException e){
            userInterface.print(e);
        }
    }
}