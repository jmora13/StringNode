//Jose Mora
//project 3

import java.util.Scanner;
import java.io.*;

public class Driver {
    
    public static void main (String[] args)  throws IOException{
        
        Scanner keyboard = new Scanner(System.in);
        
        // prompts user for file name
        System.out.println("Enter name of input file"); //prompts user for input file name
        String file= keyboard.nextLine();   
        BufferedReader inFile = new BufferedReader(new FileReader(file));
        
        System.out.println("Enter name of output file"); //prompts user for output filename
        String otherFile = keyboard.nextLine(); 
        PrintWriter outFile = new PrintWriter(new FileWriter(otherFile));
        
        // creates LineEditor object and fills it with txt from file
        LineEditor editor = new LineEditor();
        editor.fill(inFile);
         
  
        boolean keepGoing = true;
        do {
              
        System.out.println(
            "Pick an option:\n\n" + 
                "1. Print out entire file\n" +
                "2. Print out current number and line\n" +
                "3. Add new first line\n" +
                "4. Add new last line\n" +
                "5. Insert new line as current line\n" +
                "6. Deletes the first line\n" +
                "7. Deletes the last line\n" +
                "8. Deletes the current line\n" +
                "9. Find first occurance in file of substring\n" +
                "10. Replace one string from current line with another\n" +
                "11. Print out number of lines in file\n" +
                "12. Save & Exit");
        
        int choice = keyboard.nextInt();
        
        if (choice == 1) {
        	String line = null;
	    	 while ((line = inFile.readLine()) != null) {
	    	   System.out.println(line);
	    	 }
	    	   
        } else if (choice == 2) {
          editor.printCurrent(outFile);
          
        } else if (choice == 3) {
          System.out.println("What new first line would you like to add?");
          String line = keyboard.nextLine();
          keyboard.nextLine();
          editor.insertFirst(line);
          
        } else if (choice == 4) {
          System.out.println("What new last line would you like to add?");
          String line = keyboard.nextLine();
          keyboard.nextLine();
          editor.insertLast(line);
          
        } else if (choice == 5) {
          System.out.println("What new line would you like to add?");
          String line = keyboard.nextLine();
          keyboard.nextLine();
          editor.insertCur(line);
          
        } else if (choice == 6) {
            editor.delFirst();
            
        } else if (choice == 7) {
            editor.delLast();
            
        } else if (choice == 8) {
            editor.delCur();
            
        } else if (choice == 9) {
          System.out.println("Find which string?");
          String substring = keyboard.nextLine();
          keyboard.nextLine();
          editor.findStr(substring);
          
        } else if (choice == 10) {
          System.out.println("Choose string to replace: ");
          String oldString = keyboard.nextLine();
          keyboard.nextLine();
          System.out.println("Type new line: ");
          String newString = keyboard.nextLine();
          keyboard.nextLine();
          editor.replaceStr(oldString, newString);
          
        } else if (choice == 11) {
            editor.numLines();
            
        } else if (choice == 12) {
            inFile.close();
        	outFile.close(); 
        	keepGoing = false;	
        	System.exit(0);
        	
        } else {
            System.out.println("Invalid number!");
        }
        
        } while (keepGoing);
  
    }
}