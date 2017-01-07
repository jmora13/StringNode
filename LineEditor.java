
import java.io.*;

public class LineEditor { //LineEditor object is created
    
    private StringNode head;
    private int currentLine;
    
    LineEditor()
    {
        head = null;
        currentLine = 0;
    }
    
    public void fill(BufferedReader name) throws IOException //LineEditor object initialized with data
    {
        if (name == null) //if theres no name
        {
            currentLine = 0;
            System.out.println("File is empty");//file is empty 
        } else  {
        String line;
        line= name.readLine();
        head = new StringNode (line, head);
        
          while (line != null) {               
            line = name.readLine();
            StringNode.list_tail_insert(head, line);
          }
        currentLine = 1;       
        } name.close();
    }
    
    public int numLines(){ //determines the number of lines stored in the file
        return(StringNode.listLength(head));
    }
    
    public int currentLine(){ //access method for current line number
        return currentLine;
    }
    
    public void printCurrent(PrintWriter out){ // prints the current line to the object ‘out’;  if object is empty, prints “No Lines Stored”
        System.out.println(StringNode.listPosition(head, currentLine).getData());
        System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    
    public void insertFirst(String line) { //adds new first line
        head = new StringNode (line, head);
        currentLine = 1;
    }
    
    public void insertLast(String line) { //adds new last new line 
        StringNode.list_tail_insert(head,line);
        currentLine = numLines();
    }
    
    public void insertCur(String line) { //adds a new line following the current line 
        StringNode.listPosition(head, currentLine-1).addNodeAfter(line);
    }
    
    public void delFirst() { //deletes the first line
        if (head == null)
        {
            System.out.println("Nothing in the list, no lines were delted");
            return;
        }
        else
        {
            head = head.getLink();
            if (StringNode.listLength(head) == 0)
            {
                currentLine = 0;
            } else
            {
                currentLine = 1;
            }
        }
    }
    
    public void delLast() //deletes the last line
    {
        if (head == null)
        {
            return;
        }
        else
        {
            StringNode.list_tail_remove(head);
            if (StringNode.listLength(head) == 0)
            {
                currentLine = 0;
            } else
            {
                currentLine = numLines();
            }
        }
    }
    
    public void delCur() //deletes the current line
    {
//might be head.listPosition(head,currentLine-1).removeNodeAfter(); if code under doesn't work try this       
StringNode.listPosition(head,currentLine-1).removeNodeAfter();
    }
    
    public boolean findStr(String str) //finds first instance of string 
    {
        int count = 0;
        boolean found = false;
        StringNode cursor;
        for (cursor = head; cursor != null; cursor = cursor.getLink()){
          count++;  
          if (str.equals(cursor.getData()))
          {
             found = true;
             break;
          }
        }    
        currentLine = count;
        return found;
    }
    
    public void replaceStr(String old, String newOne) //replaces string 
    {
        if(currentLine !=0)
        {
            StringNode cursor = StringNode.listSearch(head,old);
              if(cursor != null)
              {
                  StringNode.listSearch(head,old).setData(newOne);
              }
        } 
    }
     
    @Override
    public String toString() // tostrings
    {
        String result = "";
            StringNode current = head;
            while(current!= null){
                current = current.getLink();
                result += current.getData() + "\n ";
            }
            return "List: " + result;
    }
}
