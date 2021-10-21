package IssueManagment;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private final Scanner input = new Scanner(System.in);

    public int getCommandFromUser(){
        System.out.print("Choice: ");
        String userInput = input.nextLine().trim();
        try {
            return Integer.parseInt(userInput);
        }catch (NumberFormatException e){
            return -1;
        }
    }

    public String getStringFromUser(){
        return input.nextLine().trim();
    }

    public void printMainMenu() {
        System.out.println("1. Create issue (user)");
        System.out.println("2. Validate issue (admin)");
        System.out.println("3. Show all active issues (user)");
        System.out.println("0. Exit");
    }

    public void print(String string){
        System.out.println(string);
    }

    public void print(String string, boolean newline){
        if (!newline){
            System.out.print(string);
        } else {
            print(string);
        }
    }

    public void print(Issue issue){
        System.out.println("\t Title: " + issue.getTitle() + "\n\t Description: " + issue.getDescription() + "\n\t Status: " + issue.getStatus() + '\n');
    }

    public void print(ArrayList<Issue> issues){
        for (Issue issue : issues) {
            print(issues.indexOf(issue) + ".", false);
            print(issue);
        }
    }

    public void print(Exception e){
        e.printStackTrace();
    }
}
