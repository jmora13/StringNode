/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.*;
/**
 *
 * @author Justin
 */
public class StringNode
{
   // Invariant of the StringNode class:
   //   1. The node's String data is in the instance variable data.
   //   2. For the final node of a list, the link part is null.
   //      Otherwise, the link part is a reference to the
   //      next node of the list.
   private String data;
   private StringNode link;   


   /**
   * Initialize a node with a specified initial data and link to the next
   * node. Note that the initialLink may be the null reference, 
   * which indicates that the new node has nothing after it.
   * @param initialData
   *   the initial data of this new node
   * @param initialLink
   *   a reference to the node after this new node--this reference may be null
   *   to indicate that there is no node after this new node.
   * @postcondition
   *   This node contains the specified data and link to the next node.
   **/   
   public StringNode(String initialData, StringNode initialLink)
   {
      data = initialData;
      link = initialLink;
   }

 
   /**
   * Modification method to add a new node after this node.   
   * @param item
   *   the data to place in the new node
   * @postcondition
   *   A new node has been created and placed after this node.
   *   The data for the new node is item. Any other nodes
   *   that used to be after this node are now after the new node.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for a new 
   *   StringNode. 
   **/
   public void addNodeAfter(String item)   
   {
      link = new StringNode(item, link);
   }          
   
   
   /**
   * Accessor method to get the data from this node.   
   * @param - none
   * @return
   *   the data from this node
   **/
   public String getData( )   
   {
      return data;
   }
   
   
   /**
   * Accessor method to get a reference to the next node after this node. 
   * @param - none
   * @return
   *   a reference to the node after this node (or the null reference if there
   *   is nothing after this node)
   **/
   public StringNode getLink( )
   {
      return link;                                               
   } 
    
    
   /**
   * Copy a list.
   * @param source
   *   the head of a linked list that will be copied (which may be
   *   an empty list in where source is null)
   * @return
   *   The method has made a copy of the linked list starting at 
   *   source. The return value is the head reference for the
   *   copy. 
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.   
   **/ 
   public static StringNode listCopy(StringNode source)
   {
      StringNode copyHead;
      StringNode copyTail;
      
      // Handle the special case of the empty list.
      if (source == null)
         return null;
         
      // Make the first node for the newly created list.
      copyHead = new StringNode(source.data, null);
      copyTail = copyHead;
      
      // Make the rest of the nodes for the newly created list.
      while (source.link != null)
      {
         source = source.link;
         copyTail.addNodeAfter(source.data);
         copyTail = copyTail.link;
      }
 
      // Return the head reference for the new list.
      return copyHead;
   }
   
   
   /**
   * Copy a list, returning both a head and tail reference for the copy.
   * @param source
   *   the head of a linked list that will be copied (which may be
   *   an empty list in where source is null)
   * @return
   *   The method has made a copy of the linked list starting at 
   *   source.  The return value is an
   *   array where the [0] element is a head reference for the copy and the [1]
   *   element is a tail reference for the copy.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.   
   **/
   public static StringNode[ ] listCopyWithTail(StringNode source)
   {
      StringNode copyHead;
      StringNode copyTail;
      StringNode[ ] answer = new StringNode[2];
     
      // Handle the special case of the empty list.   
      if (source == null)
         return answer; // The answer has two null references .
      
      // Make the first node for the newly created list.
      copyHead = new StringNode(source.data, null);
      copyTail = copyHead;
      
      // Make the rest of the nodes for the newly created list.
      while (source.link != null)
      {
         source = source.link;
         copyTail.addNodeAfter(source.data);
         copyTail = copyTail.link;
      }
      
      // Return the head and tail references.
      answer[0] = copyHead;
      answer[1] = copyTail;
      return answer;
   }
   
   
   /**
   * Compute the number of nodes in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list
   *   with a null head)
   * @return
   *   the number of nodes in the list with the given head 
   * @note
   *   A wrong answer occurs for lists longer than Int.MAX_VALUE.     
   **/   
   public static int listLength(StringNode head)
   {
      StringNode cursor;
      int answer;
      
      answer = 0;
      for (cursor = head; cursor != null; cursor = cursor.link)
         answer++;
        
      return answer;
   }
   

   /**
   * Copy part of a list, providing a head and tail reference for the new copy. 
   * @param start/end
   *   references to two nodes of a linked list
   * @param copyHead/copyTail
   *   the method sets these to refer to the head and tail node of the new
   *   list that is created
   * @precondition
   *   start and end are non-null references to nodes
   *   on the same linked list,
   *   with the start node at or before the end node. 
   * @return
   *   The method has made a copy of the part of a linked list, from the
   *   specified start node to the specified end node. The return value is an
   *   array where the [0] component is a head reference for the copy and the
   *   [1] component is a tail reference for the copy.
   * @exception IllegalArgumentException
   *   Indicates that start and end are not references
   *   to nodes on the same list.
   * @exception NullPointerException
   *   Indicates that start is null.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.    
   **/   
   public static StringNode[ ] listPart(StringNode start, StringNode end)
   {
      StringNode copyHead;
      StringNode copyTail;
      StringNode cursor;
      StringNode[ ] answer = new StringNode[2];
      
      // Make the first node for the newly created list. Notice that this will
      // cause a NullPointerException if start is null.
      copyHead = new StringNode(start.data, null);
      copyTail = copyHead;
      cursor = start;
      
      // Make the rest of the nodes for the newly created list.
      while (cursor != end)
      {
         cursor = cursor.link;
         if (cursor == null)
            throw new IllegalArgumentException
            ("end node was not found on the list");
         copyTail.addNodeAfter(cursor.data);
         copyTail = copyTail.link;
      }
      
      // Return the head and tail references
      answer[0] = copyHead;
      answer[1] = copyTail;
      return answer;
   }        
   
   
   /**
   * Find a node at a specified position in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list in
   *   which case the head is null)
   * @param position
   *   a node number
   * @precondition
   *   position > 0.
   * @return
   *   The return value is a reference to the node at the specified position in
   *   the list. (The head node is position 1, the next node is position 2, and
   *   so on.) If there is no such position (because the list is too short),
   *   then the null reference is returned.
   * @exception IllegalArgumentException
   *   Indicates that position is not positive.    
   **/   
   public static StringNode listPosition(StringNode head, int position)
   {
      StringNode cursor;
      int i;
      
      if (position <= 0)
           throw new IllegalArgumentException("position is not positive");
      
      cursor = head;
      for (i = 1; (i < position) && (cursor != null); i++)
         cursor = cursor.link;

      return cursor;
   }


   /**
   * Search for a particular piece of data in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list in
   *   which case the head is null)
   * @param target
   *   a piece of data to search for
   * @return
   *   The return value is a reference to the first node that contains the
   *   specified target. If there is no such node, the null reference is 
   *   returned.     
   **/   
   public static StringNode listSearch(StringNode head, String target)
   {
      StringNode cursor;
      
      for (cursor = head; cursor != null; cursor = cursor.link)
         if (target.equals(cursor.data))
            return cursor;
        
      return null;
   }

   
   /**
   * Modification method to remove the node after this node.   
   * @param - none
   * @precondition
   *   This node must not be the tail node of the list.
   * @postcondition
   *   The node after this node has been removed from the linked list.
   *   If there were further nodes after that one, they are still
   *   present on the list.
   * @exception NullPointerException
   *   Indicates that this was the tail node of the list, so there is nothing
   *   after it to remove.
   **/
   public void removeNodeAfter( )   
   {
      link = link.link;
   }          
   
   
   /**
   * Modification method to set the data in this node.   
   * @param newData
   *   the new data to place in this node
   * @postcondition
   *   The data of this node has been set to newData.
   **/
   public void setData(String newData)   
   {
      data = newData;
   }                                                               
   
   
   /**
   * Modification method to set the link to the next node after this node.
   * @param newLink
   *   a reference to the node that should appear after this node in the linked
   *   list (or the null reference if there is no node after this node)
   * @postcondition
   *   The link to the node after this node has been set to newLink.
   *   Any other node (that used to be in this link) is no longer connected to
   *   this node.
   **/
   public void setLink(StringNode newLink)
   {                    
      link = newLink;
   }
   
   //Here's the last 5 methods we were supposed to do for StringNode, Already tested worked for sure.
   
   public static StringNode list_tail_remove(StringNode headptr)
   {
       if(headptr == null){
           System.out.println("Empty List, Last element does not exist");
           return headptr;
           
       } 
       else 
       {
           
       StringNode current = headptr;
       StringNode previous = null;

           while(current.link != null)
           {
               previous = current;
               current = current.link;
           }
           
       previous.link = null;
       return headptr;
       
       }       
   }
   
   public static StringNode list_tail_insert(StringNode headptr, String entry)
   {
       
       StringNode newNode = new StringNode(entry, null);
       
       if(headptr == null){
           return newNode;
       } 
       else 
       {
           
       StringNode current = headptr; 
       
        while(current.link != null)
       {
           current = current.link;
       }   
        
       current.link = newNode;
       return headptr;
        
       }
   }
   
   public static StringNode getLast(StringNode headptr)
   {
       if(headptr == null){
           return headptr;
       }
       else
       {
           StringNode current = headptr;
           
           while(current.link != null)
           {
               current = current.link;
           }
           
           System.out.println(current.data);
           return current;
       }
   }
   
   public static void outNode(PrintWriter out , StringNode headPtr)
   {
     if (headPtr == null){
           return;
     } else 
      {
       StringNode current = headPtr;
       
       while(current!=null)
        {
           out.print(current.data + " --> ");
           current = current.link;
        }
       out.println(current);
      }
   }
   public static void outList(StringNode headPtr)
   {
       if (headPtr == null)
       {
           return;
       } 
       else 
       {
           StringNode current = headPtr;
           
           while(current!=null)
           {
           System.out.print(current.data + " --> ");
           current = current.link;
           }
         System.out.println(current);           
       }
   }
}
