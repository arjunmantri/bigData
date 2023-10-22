Search google or leetcode based on high level words like Zero, Interval, etc...as encountered in the  interview.
sra-internships@sisa.samsung.com
SRA Resume Submission

Link to DP questions - https://www.quora.com/What-are-the-top-10-most-popular-dynamic-programming-problems-among-interviewers

------------------------------------------------------------------
//In this take two pointers, incr 1 by one and 2 by two and keep checking for data if equal 
//loop exists
//loop  @ 15
 slow 50
 fast 15
 slow 20
 fast 4
Linked List after removing loop : 
50 20 15 4 10
//http://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/

public void detectLoop(Node head){
    if(head == null) return;
    Node curr = head;
    Node incrByTwo = head;
    while(incrByTwo != null && incrByTwo.next!=null){
        incrByTwo=incrByTwo.next.next;
        curr = curr.next;
        if(curr.data == incrByTwo.data){
            System.out.println("Loop Exists");
            break;
        }
        if(incrByTwo==null|| incrByTwo.next==null){
           System.out.println("Loop doesnt Exists"); 
           return;
        }
    }
/* If loop exists */
		if (curr == incrByTwo) {
			curr = node;
			while (curr != incrByTwo.next) {
				curr = curr.next;
				incrByTwo = incrByTwo.next;
			}

			/* since incrByTwo>next is the looping point */
			incrByTwo.next = null; /* remove loop */

		}

}


-----------------Leetcode detect a loop-------
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;
        ListNode ptr = head;
        ListNode fastptr = head;
        while(fastptr!=null && fastptr.next!=null){
            fastptr = fastptr.next.next;
            ptr = ptr.next;
            if(fastptr == ptr){
                return true;
            }
        }
        return false;
    }
}
------------------------------------------------------------------
 142. Linked List Cycle II

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.


Example 2:
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.



Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
//In the above example we get a loop detected when fast and slow meet at -4  and then start and slow meets at 2 which is the connection part.
We take the loop detected which is fast and slow. When we find the cycle  we take the start pointer to the head and slow pointer from the above. When they both meet then we return the start ListNode/**

 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
   public ListNode detectCycle(ListNode head) {
       if (head == null || head.next == null) return null;
    ListNode slow = head, fast = head, start = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        System.out.println(slow.val + "---> "+fast.val);
        if (slow == fast) { //loop detected 
            while (slow != start) { 
                System.out.println(slow.val + "-inn--> " + start.val);
                slow = slow.next;
                start = start.next;
            }
            System.out.println(slow.val + "-out--> " + start.val);
            return start;
        }
    }
    return null;
    }
}


------------------------------------------------------
19. Remove Nth Node From End of List
Example:
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
A one pass solution can be done using pointers. Move one pointer fast --> n+1 places forward, to maintain a gap of n between the two pointers and then move both at the same speed. Finally, when the fast pointer reaches the end, the slow pointer will be n+1 places behind - just the right spot for it to be able to skip the next node.
Since the question gives that n is valid, not too many checks have to be put in place. Otherwise, this would be necessary.
public ListNode removeNthFromEnd(ListNode head, int n) {
    
    ListNode start = new ListNode(0);
    ListNode slow = start, fast = start;
    slow.next = head;
    
    //Move fast in front so that the gap between slow and fast becomes n
    for(int i=1; i<=n+1; i++)   {
        fast = fast.next;
    }
    //Move fast to the end, maintaining the gap
    while(fast != null) {
        slow = slow.next;
        fast = fast.next;
    }
    //Skip the desired node
    slow.next = slow.next.next;
    return start.next;
}

------------------------------------------------------------------
61. Rotate List

Given a linked list, rotate the list to the right by k places, where k is non-negative.
Example 1:
Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL

Example 2:
Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
The diagram below is for K = 3


public class RotateListToRight {

	public ListNode rotateRight(ListNode head, int k) {
        //edge case
        if(head == null)
            return null;
        
        //calculate length of linkedlist
        ListNode itrNode = head;
        int lengthOfListNode = 0;
        
        while(itrNode != null){
            lengthOfListNode++;
            itrNode = itrNode.next;
        }
                
        k = k % lengthOfListNode;
                
        if(k == 0)
            return head;
        
        //Using the delete n'th node from end logic
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //here both slow and fast contains -1
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        for(int i = 0; i <= k; i++){
            fast = fast.next;
        }
        
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        
        //now slow is pointing to node before newHead;
        ListNode newHead = slow.next;
        
        //get the last node
        fast = head;
        while(fast.next != null)
            fast = fast.next;
        
        //connect it to head given
        fast.next = head;
        
        //make the slow.next point to null
        slow.next = null;
        
        return newHead;
    }
}



------------------------------------------------------------------
public void middleElement(Node head){
    if(head == null)return;
    Node curr = head;
    Node incrByTwo = head.next;
    while(incrByTwo!=null || incrByTwo.next!=null){
        cur= cur.next;
        incrByTwo = incrByTwo.next.next;
    }
    System.out.println("The middle element is :"+curr.data);
}
------------------------------------------------------------------
public void reverseLinkList(Node head){
    if(head == null)return;
    Node prevNode = null;
    Node nextNode;
    Node current = head;
    while(current!=null){
        nextNode = current.next;
        current.next = prevNode;
        prevNode = current;
        current = nextNode;
    }
}


------------------------------------------------------------------
/*recursive
Output of this program will be same as above program.
Now lets understand logic for above recursive program.
5->6->7->1->2
below function will terminate when last node(2) 's next will be null.so while returning when you reach at node with value 1,If you closely observe node.next.next=node is actually setting 2->1(i.e. reversing the link between node with value 1 and 2) and node.next=null is removing link 1->2. So in each iteration, you are reversing link between two nodes.
Below diagram will make it clear
Read more at http://www.java2blog.com/2014/07/how-to-reverse-linked-list-in-java.html#BX0W7k4YOG4UolyV.99
*/
public static Node reverseLinkedList(Node node) {
     if (node == null || node.next == null) {
         return node;
     }

     Node remaining = reverseLinkedList(node.next);
     node.next.next = node;
     node.next = null;
    return remaining;
 }

------------------------------------------------------------------

public void removeDupLinkList(Node head){
    if(head == null)return;    
    Node current = head;
    while(current.next != null){
        if(current.next.data == current.data){
            current.next = current.next.next;
        }
        else current = current.next;
    }
}
------------------------------------------------------------------
public void removeDupLinkHashmap(Node head){
    if(head == null)return;
    
}
------------------------------------------------------------------

/*Mergesort for the LinkedList 
link - http://www.sanfoundry.com/java-program-implement-merge-sort-algorithm-linked-list/*/
public Node mergeSortLinkList(Node head){
    if(head==null || head.next == null) return head;
    a = head;
    b = head.next;
    while(b!=null && b.next!=null){ //**Finding the middle element of the linkList
        head = head.next;
        b = b.next.next;
    }
    b = head.next; //middle element of the link list
    head.next = null;//setting it to null again to move to loop again.
    merge(mergeSortLinkList(a),(mergeSortLinkList(b));
}
public Node merge(Node a , Node b){
    Node temp = new Node();
    Node head = temp;
    Node c = head;
    while(a!=null && b!= null){
        if(a.data <= b.data){ //comparing the data in linked list
            c.next = a; //this 3 statement replaces the less value with the higher, so that they are at correct position. Storing the address stored in of next element in “a” to “c”
            c = a; //copying a to c
            a = a.next;
        }
        else{
            c.next = b; //this 3 statement replaces the less value with the higher, so that they are correct position.
            c = b;
            b = b.next;
        }
    
    }
    c.next = (a == null) ? b : a; //if any extra ones then in dat case again copy to c
    return head.next;
}
------------------------------------------------------------------

------------------------------------------------------------------
/* Intersection of link list. First calculate the length of two lists and find the difference. Then start from the longer list at the diff offset, iterate through 2 lists and find the node.
A:          a1 -> a2
                    ->
                      c1 -> c2 -> c3
                    ->           
B:     b1 -> b2 -> b3
link - http://www.programcreek.com/2014/02/leetcode-intersection-of-two-linked-lists-java/
(Note that getting a common node is done by comparing the address of the nodes)(Note that getting a common node is done by comparing the address of the nodes)*/
public Node intersectionOfList (Node headA, Node headB){
    Node p1, p2;
    p1 = headA;
    p2 = headB;
    int len1, len2;
    if(p1 == null || p2 == null){
        return null;
    }
    while(p1.next != null){
        len1++;
        p1 = p1.next;
    }
    while(p2.next != null){
        len2++;
        p2 = p2.next;
    }
    int diff;
    p1 = headA;
    p2 = headB;
    
    if(len1 > len2){
        diff = len1 - len2
        int i = 0;
        while(i < diff){//bring to same position.
            p1 = p1.next;
            i++;
        }
      }
    else{
        diff = len2 - len1;
        int i = 0;
        while(i < diff){
            p2 = p2.next;
            i++;
        } 	
    }
    while(p1 != null && p2 != null){ /*this is the reason we move it by difference and den put a && condition here in the while loop. Because we wont know den when to END condition for the while loop, hence the above diff value.*/
        if(p1.val == p2.val)
            return p1;
        p1 = p1.next;
        p2 = p2.next;
    }
    return null;    
}
------------------------------------------------------------------
class Stack{
   int top=-1, size, len=0;
   int array[];
   Scanner kbd = new Scanner(System.in);
   size = kbd.nextInt();
   array =  new [size];
   
   boolean isEmpty(){
       return top == -1;
   }
   
   void push(int data){
       if(!isEmpty()){
       arr[top++] = data;
       len++;
       }
   
       else new IndexOutOfBoundsException("Overflow Exception"); //important to throw an exception from the point of interview as well.
   }
   int pop(){

        if(!isEmpty()){
               len--;
               return arr[top--];
       }
   }
    int min(){
        if(){
            
        }
    }   
} 


------------------------------------------------------------------
/* BFS tree not graph in case of graphs we pass the adjacency matrix which does it
link for graphs - http://www.sanfoundry.com/java-program-traverse-graph-using-bfs/ 
link for tree - http://algorithms.tutorialhorizon.com/breadth-first-searchtraversal-in-a-binary-tree/
Level Order Traversal or BFS tree traversal
store the root and den store the left, right then print it checking if left or right present or not.*/

public void BFSTree(Node root){
    if(root == null) return null;
    Queue<Node> q = new LinkedList <Node>();//cannot initialize(after new) as a queue here only linkedlist
    q.add(root);
    while(!q.isEmpty()){
        Node n = (Node) q.remove(); //remove from queue and store it in n. 
        System.out.println(n.data()+" ");
        if(n.left != null)q.add(n.left);
        if(n.right != null)q.add(n.right);
    }
}



LeetCode BFS
/*Here we need to go over the size of the queue every time and then add it to the result arraylist. Declare a new arraylist everytime and iterate over the size of the queue declared*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while(!q.isEmpty()){
            List<Integer> subList = new ArrayList<>();
            int levelSize = q.size(); //getting size of each level everytime is imp.
            for(int i=0; i<levelSize; i++){//loop thru it to and store in the sublist.
                TreeNode n = q.poll();
                subList.add(n.val);
                if(n.left != null) q.add(n.left);
                if(n.right != null) q.add(n.right);
            }
            result.add(subList);
        }
        return result;
    }
}

993. Cousins in Binary Tree

in a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
Return true if and only if the nodes corresponding to the values x and y are cousins.
 
Example 1:

Input: root = [1,2,3,4], x = 4, y = 3
Output: false

Example 2:

Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true
//Cousin dont have same parent and hence the false condition.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> checkList = new ArrayList();
            for(int i=0; i<size; i++){
                TreeNode n = q.poll();
                checkList.add(n.val);
                if(n.right != null && n.left != null){
                    if(n.left.val == x && n.right.val == y)//this to check if parent same then false
                        return false;
                    if(n.left.val == y && n.right.val == x)//this to check if parent same then false
                        return false;
                }
                if(n.left != null) q.add(n.left);
                if(n.right != null) q.add(n.right);
                
                if(checkList.contains(x) && checkList.contains(y))
                    return true;
            }
        }
        return false;
    }
}


------------------------------------------------------------------
/*Depth first search java tree traversal*/  
------------------------------------------------------------------
/* 2 Largest Increasing Subsequence Interview with Jon. Consider a current and max. if current is less than 0
reset it to zero, if max is less den current set max to current. if current is not less den 0 add elements to current...but dis fails for all -ve numbers in array. so the below approach*/

public int largetOfTwoNumbersSubsequence(int []a){
    int current = 0;
    int max = 0;
    for(int i = 0; i < a.length(); i++){
        if(current < 0)
            current = 0;
            current += a[i];
            if(current > max)
                max = current;
    }
    return max;
}











-------------------------------------------------------

/*Kadene's approach...
Link - https://en.wikipedia.org/wiki/Maximum_subarray_problem
take max_ending_here which is max of first element and itself + a[i]....after dis take max of maxx_so_far and max_ending_here....O(n)*/
public static int largestof2(int[] a){
        int max_ending_here=a[0], max_so_far = a[0];
        for(int i = 1; i < a.length; i++){
            max_ending_here = Math.max(a[i], max_ending_here + a[i]);
             max_so_far = Math.max(max_so_far, max_ending_here);
        }
        return max_so_far;
 }


----------------------------------------------------------------------------------
8tfe

http://collabedit.com/8t8fe

------------------------------------------------------------------
Class Tree{
    void inorder(Node root){
        if(root == null)return;
        stack<Node> s = new stack<Node>();
        Node curr = root;
        while(!s.Empty() || curr != null){
            if(curr != null){
              s.add(curr);
              curr=curr.left;  
            }
            else{
                curr = s.pop();
                System.out.println(curr.data+" ");
                curr=curr.right;
            }
        }
    }
------------------------------------------------------------------
  
    void preorder(Node n){
            if(root == null)return;
            Stack<Node> s = new Stack<Node>();
            Node curr = null;
            s.push(n);
            while(!s.isEmpty()){
                curr = s.pop();
                System.out.println(curr.data);
                if(curr.right!=null)curr=curr.right;
                if(curr.left!=null)curr=curr.left;
            }
        }
}




------------------------------------------------------------------
Has Path Sum (haspathsum)
/*check if sum is zero and also root is null i.e EC, interim sum every time to reduce the sum and pass it */
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)
            return false;
         sum -= root.val;
        if((root.left ==  null) && (root.right == null))
            return (sum == 0);
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}

------------------------------------------------------------------
public class Solution {
	public boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;
 
		if (getHeight(root) == -1)
			return false;
 
		return true;
	}
 
	public int getHeight(TreeNode root) {
		if (root == null)
			return 0;//Return zero is imp and it will be added every at last +1
 
		int left = getHeight(root.left);
		int right = getHeight(root.right);
 
		if (left == -1 || right == -1)
			return -1;
 
		if (Math.abs(left - right) > 1) {
			return -1;
		}
 
		return Math.max(left, right) + 1;//This is were hght is increased every time.
 
	}
}

------------------------------------------------------------------
/*declare an array of length 256 as boolean and if the character found return false else set true if the character is not found. return true if loop completes.*/
public static isUnique(String str){
    boolean[] char_set = new boolean[256];
    int len = str.length();
    for(int i=0;i<len;i++){
        if(char_set[str.charAt(i)])return false;
        else char_set[str.charAt(i)] = true;
    }
    return true;
}
------------------------------------------------------------------
public static String removeDups(String str){
    Hashset<Character> hm = new Hashset<Character>();
    String op="";
    int len = str.length();
    for(int i=0;i<len;i++){
        if(!hm.contains(str.charAt(i)){
            hm.add(str.charAt(i));
            op+=str.charAt(i);
        }
        return op;
    }
}
------------------------------------------------------------------
//Using the utility method
import java.util.ArrayList;
import java.util.Collections;

public class Main {
   public static String reverseWord(String word,int start,int end){
    String rev="";
    for(int i=end-1;i>=start;i--){
        rev+=word.charAt(i);
    }
    return rev;
}
   public static void main(String[] args) {
      String arrayList;
      arrayList =  reverseWord("arjun",0,5);
      System.out.println("Before Reverse Order:" + arrayList);
   }
}


------------------------------------------------------------------
/*Rotate array. for array[1,2,3,4,5,6,7] with num = 2
1. Divide the array two parts: 1,2,3,4 and 5, 6
2. Rotate first part: 4,3,2,1,5,6
3. Rotate second part: 4,3,2,1,6,5
4. Rotate the whole array: 5,6,1,2,3,4 

let a= [1,2,3,4,5,6,7]
k = 3.
we have to first reverse the whole array by swapping the first element with the last one and so on..
you will get[7,6,5,4,3,2,1]

reverse the elements from 0 to k-1
reverse the elements 7,6,5
you will get [5,6,7,4,3,2,1]

reverse the elements from k to n-1
reverse the elements 4,3,2,1
you will get[5,6,7,1,2,3,4]
link - http://www.programcreek.com/2015/03/rotate-array-in-java/ 
equation "(start+end)-i" used to swap first and last
O(1) space and in O(n) time*/
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
//below is there because lets say we have array of len=5 but k=15 then it does not makes sense to do it 15 times since after certain we would get the same rotation.
        k %= n; //This is to get the rotation number.
        rev(nums, 0, n-1); //Rotate part of it
        rev(nums, 0, k - 1);
        rev(nums, k, n-1);
    }
    
    public void rev(int[] nums, int start, int end){
        while(start < end){//Swap start and end, increment both.
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
          }
        }
}

------------------------------------------------------------------
/*Normal method to rotate an array which o(n) in space and o(n) in time. In this example we copy the last 3 elements to the result array and den put first 4 at last of the result in the second for loop.*/
public class Test{
   public static void rotate(int[] nums, int k){
    int[] result = new int[nums.length];
    for(int i=0; i < k; i++){
        result[i] = nums[nums.length-k+i];
    }
    int j=0;
    for(int i=k; i<nums.length; i++){
        result[i] = nums[j];
        j++;
    }
    for(int i=0; i < result.length; i++){
        System.out.print(result[i]);
    }
   }
   public static void main(String args[]){
      int a[] = {1,2,3,4,5,6,7};
      int k = 3;
      rotate(a,k);
   }
}
------------------------------------------------------------------
Class Stack{
    Node top;
    Node pop(){
        while(top != null){
            Object item = top.data;
            top = top.next;
            return item;
        }
        retun null;
    }
    Node push(){
        Node t = new(item);
        t.next = top;
        top = t;
    }
}
------------------------------------------------------------------
/* Matrix multiplication , in this the row for a remains constant while the column for b matrix remains the same and adding the previous value of c to it */
public int[][] matrixMultiplication(int[][] a, int[][] b){
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
             for(int k=0;k<n;k++){
                c[i][j] = c[i][j] + a[ i ][k] * b[k][ j ];
            }   
        }    
    }
}
------------------------------------------------------------------
/*valid-parentheses, stack only contains the key value or opening braces. We den take the key and compare with associated value of the key with the current character. If it matches, den is correct.*/
public boolean validParentheses(String s){
    HashMap<Character, Character> map = new HashMap<Character,Character>();
    map.put('(', ')');
    map.put('[', ']');
    map.put('{', '}');
    
    Stack<Character> parentheses = new Stack<Character>();
    	
    for(int i=0;i<=s.length();i++){
        char curr = s.charAt(i)
        if(map.keySet().contains(curr)) //checking the key and if present push the key to the stack, hence used KeySet()
            parentheses.push(curr);
        else if(map.values().contains(curr)){
                if(!parentheses.isEmpty() && map.get(parentheses.peek()) ==  curr) //check for the peek and compare it with the value present in the map store by using the get map function
                    parentheses.pop();
                else return false //if peek doesn't match return false.
            }
        }
    return parentheses.empty();
}
------------------------------------------------------------------
/* permutation of strings, remember the n-factorial example. swap the elements and do recursion to call the permutation string again do the swap to restore to original position  
http://www.ericleschinski.com/c/java_permutations_recursion/ - link  
public void permutationOfStrings(int[] A,k,n){
    if(k==n) {
        for(int i=0;i<n;i++)
        System.out.println(A[i]);
    }
    else {
        int t =  a[i]; //swap to take the combination of remaining elements
        a[i] = a[k];
        a[k] = t;
        permutationofString(A,k+1,n);
        int t =  a[i]; //swap to restore the original position.
        a[i] = a[k];
        a[k] = t;
      }
}*/
-----------------------------------------------------------------
/*http://www.ericleschinski.com/c/java_permutations_recursion/
Run this code in tutorials point to get. Adding prefix with the first char. Now substring 0 and i location, append substring i+1 to the string...
prefix, str
""      (A)BCD            
A       (B)CD 
AB      (C)D          
ABC     (D)
ABCD    ""           
ABC     D            
AB      C(D)         
ABD     C        D was chopped off, because for loop is at index 1 now. looping over 'C' now
ABDC    ""       Add C to prefix.  BASE CASE!  print: "ABDC".*/
import java.io.*;
public class Main {
    private static void permutation(String prefix, String str){
        int n = str.length();
        if (n == 0) 
            System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++){
                System.out.println("prefix :"+prefix + str.charAt(i));
                System.out.println("append :"+str.substring(0, i) + str.substring(i+1));
                permutation(prefix + str.charAt(i), 
            str.substring(0, i) + str.substring(i+1)); //takes first char 'A' and appends 'BCD' den, 'AB' appends 'CD'and so on.
            }
        }
    }
    public static void main(String[] args) {
        permutation("", "ABCD");
    }
    }

------------------------------------------------------------------
/* Coin problem. Working on one choice and taking combinations with other values in arrayList. In recursion remaining acts as numbers and den partial_rec acts as partial*/
public static void main(String args[]){
    Integer numbers[] = {1,2,3,45,7,2,}; //declaring an Integer to pass as ArrayList to sum_up()
    int target = 48;
    sum_up(new ArrayList<Integer>(Array.asList(numbers)),target);//ArrayList have dynamic size whereas array's don't. When we pass integer to arraylist autoboxing converts the integer primitive to integer objects. ArrayList can only store objects while array can store both.
}

static void sum_up(ArrayList<Integer> numbers,int target){
    sum_up_recursive(numbers,target,new ArrayList<Integer>());
}

static sum_up_recursive(ArrayList<Integer> numbers,int target,ArrayList<Integer> partial){
    int sum = 0;
    for(int x: partial) sum += x;
    if(sum == target){ //matching with the target...base condition.
        System.out.println("Sum" + ArrayList.toString(partial.toArray())+")" +target);
        
    }
    if(sum > target) return;
    for(int i = 0; i <numbers.size() ;i++){
        ArrayList<Integer> remaining = new ArrayList<Integer>();
        int n = number.get(i);
        for(int j = i+1; j < numbers.size(); j++)
            remaining.add(numbers.get(j)); //all the remaining element into it except one.
        ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);//dis builds an arraylist partial_rec with partial.
        partial_rec.add(n);//adding the first element from numbers
        sum_up_recursive(remaining,target,partial_rec);
    }
    
} 
------------------------------------------------------------------
/* above same problem in python */
def sum_set (numbers, target, partial=[]):
    s = sum(partial)
    if s == target
        print "s%s = %s" %s (partial,target)
    if s > target 
        return
    for i in range(len(numbers)):
        n = numbers[i]
        remaining = numbers[i+1:]
        sum_set(remaining, target, partial + [n])

if __name__ == "__main__":
    subset_sum([3,9,8,4,5,7,10],15)
------------------------------------------------------------------
#sample python with main class
class Example(Object):
    def add()
        print "Hi how are you" #if not we return .
    
if __name__ == "__main__":
    Example().add()
------------------------------------------------------------------
/* remove duplicates from a large file, 1) first answer that u can use hashmap but con of it is that it gives javarunoutofmemory for larger set like 15million records.
2)The key is that your data will not fit into memory. You can use an external merge sort for this:Partition your file into multiple smaller chunks that fit into memory. Sort each chunk, eliminate the duplicates (now neighboring elements).Merge the chunks and again eliminate the duplicates when merging. Since you will have an n-way merge here you can keep the next k elements from each chunk in memory, once the items for a chunk are depleted (they have been merged already) grab more from disk.*/

public void removeDuplicateFromFile(){
    
}





----------------------------------------------------------------------------------
b9exc

http://collabedit.com/b9exc

------------------------------------------------------------------
------------------------------------------------------------------
Two pointers concept, hash contains or not used in many problems to find solutions.
------------------------------------------------------------------

public class demo{

Hashtable sample = new Hashtable();
sample.put("arjun","mantri");
sample.get("arjun");
sample.containsKey("key");

}
------------------------------------------------------------------
/* Dijkstra - greedy approach.
Shortest path */
private final List<Vertex> nodes;
private final List<Edge> edges;
/*pseudo code*/
/*In the method below we declare different list for distance, unsettled, settled nodes, predecessor wherein we store the parent node. Loop thru the unsettled, add to settle, remove from unsettle and findtheNeighboures*/
public void dijktras(Vertex source){
    Set unsettledNodes = new HashSet<Vertex>();
    Set settledNodes = new HashSet<Vertex>();
    HashMap distance = new HashMap<Vertex,Integer>();
    HashMap predecessor = new HashMap<Vertex>();
    distance.put(source,0);
    while(unsettledNodes.size() > 0){
        Vertex node = getMinimum(unsettledNodes);
        settledNodes.add(node);
        unsettledNodes.remove(node);
        findMinimalDistance(node);
    } 
}
/*In the method below find the neighbour nodes, get the distance directly or via some node store that into distance list, add that target node to the predesscor which is the parent list and finally adding that to the unsettled nodes list*/
public void findMinimalDistance(Vertex node){
   List <Vertex> adjacentNodes = getNeighbors(node);
   for(Vertex target : adjacentNodes){
       if(getShortDistance(target) > (getShortDistance(node)+ getDistance(node,target))){
           distance.put(target, getShortDistance(node)+ getDistance(node,target));
           predecessor.put(target);
           unsettledNodes.add(target); //adding the remaining from the for loop to the unSettledNodes.
         }
   }
}
/*checks for the source and destination of the edge. Check if the destination is present in the settled or not and then add to the neighbors list. return this list*/
public List<Vertex> getNeighbors(Vertex node){
    List<Vertex> neighbors = ArrayList<Vertex>();
    for(Edge edge: edges){
        if(edge.getSource().equal(node) && !isSettled(edge.getDestination())){
            neighbors.add(edge.getDestination());
        }
    }
    return neighbors;
}
/*Get the edge source and destination, match with values pased. Return the weight. Since we are checking the settled nodes in the getNeighbors so no need to check it here*/
public double getDistance(Vertex node, Vertex target){
    for(Edge edge: edges){
        if(edge.getSource().equal(node) && edge.getDestination().equals(target)){
            return edge.getWeight(); //return the weight of the edge
        }
    }
}
/* get minimum node vertex by comparing the minimum & de vertex */
public Vertex getMinimum(set<Vertex> vertexes){
    Vertex minimum = null;
    for(Vertex vertex : vertexes){
        if(minimum == null){
            minimum = vertex;
        }
        else if(getShortDistance(minimum) > getShortDistance(vertex)){
            minimum = vertex;
        }
    }
    return minimum;
}
/* get the shortest distance and return the corresponding int value*/
public double getShortDistance(Vertex node){
    int d = distance.get(node);
    if(d == null){
        return Integer.MAX_VALUE; 
    }
    else return d;
}

/*returns if the vertex is present in the settled node list or not*/
Private boolean isSettled(Vertex vertex){
    return settledNodes.contains(vertex);
}

public List<Vertex> getPath(Vertex target){
    
}
------------------------------------------------------------------
public void uniqueString(String word){
    boolean[] char_set = new boolean[256];
    for(int i=0;i<word.length();i++){
        int val = word.charAt(i);
        if(char_set[val]) return false;
        char_set[val] = true;
        if()    return true;
    } 

}
------------------------------------------------------------------
public void uniqueString(String word){
    HashMap check = new HashMap<>
}
------------------------------------------------------------------
public String checkDuplicate(String str){
   if(str==null)return null;
   HashMap<Character> checkDup = new HashMap<Character>(); 
   String remove = "";
   for(int i =0; i< str.length(); i++){
       if(!checkDup.contains(str.charAt(i))){
           checkDup.add(str.charAt(i));
           remove+=str.charAt(i);
       }
   }
   return remove;
}
------------------------------------------------------------------
/*isAnagram or not. add to the hashmap if not present with +1 as the count, if already present then do "++" for the same count (for string a). Now for string b check if the char in present in the map, if count is 1 den remove or else do "--"...if empty its anagram*/
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        HashMap<Character, Integer> map = new HashMap<>();
        
for(int i=0; i<s.length(); i++){ 
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for(int i=0; i<t.length(); i++){
            if(map.containsKey(t.charAt(i))){
                map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
                }
            else return false;
        }
            
       for(char c: map.keySet()){
           System.out.println("value of character " +c);
           if(map.get(c)!=0){
               return false;
           }
       }
        return true;
        
    }
}



Group Anagrams

Example:
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
//We will maintain a map. Sort the word every time, check if it's part of the map if it is then add that word to values in the map.

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length == 0) return new ArrayList();
        Map<String, List> map = new HashMap<String, List>();
        
        for(String str : strs){
            System.out.println(" str---- " +str);
            char[ ] ca = str.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            System.out.println(key);
            if(!map.containsKey(key))
                map.put(key, new ArrayList());
            map.get(key).add(str); //Adding the word against that sorted string, so will be added to the map values section and hence at last we will return the map.values()
            
        }
        return new ArrayList(map.values());
    }
}



------------------------------------------------------------------
class Node{
    Node next = null;
    int data;
    Node(int data){
        Node.data = data;
    }
    void appendTail(int data)
        Node end = new Node(data);
        Node n = this;
        while(n.next != null){
            n = n.next;
        }
        n.next = end;
    }
    public Node deleteNode(Node head, int data){
        if(n.data == data){
            return head.next;
        }
        while(n.next != null){
            if(n.data == data){
              n.next = n.next.next;
              return head;  
          }
          n = n.next;
        }
        
    }
}
------------------------------------------------------------------
Remove Duplicate in LinkedList using hashMap
/* use two hashtable to check if the element is present or not if not - add and make value as true*/
public void removeDup(Node head){
    HashTable<Integer, boolean> m = new HashTable();
    Node n = head;
    Node previous = null
    while(n != null){
        if(!m.containsKey(n.data)){
            m.put(n.data,true);
            previous = n; //set previous as n
        }
        else{
            previous.next = n.next.next; //if duplicate den set next to next for current.
        }
        n = n.next;
    }
}
------------------------------------------------------------------
public void removeDupTwoptr(Node head){
    Node curr = head;
    Node runner = curr;
    while(curr != null){
        runner = curr; //setting again runner to the current node.
        while(runner.next != null){
            if(curr.data == runner.data){//checks for the duplicates for current with next to next and if yes places it
                runner.next = runner.next.next;
            }
            else {
                runner = runner.next; //moving the runner to the next node.
            }
        }
        curr = curr.next; //moving the curr to the next node for outer while loop.
    }
}
------------------------------------------------------------------
/* Find Nth element from LinkList */
public Node nThNode(Node head, int n){
    if(head == null || n < 1) return null;
    Node p1 = head;
    Node p2 = head;
    for(int i = 0; i < n-1; i++){
        if(p2 == null){
            System.out.println("Not found");
        }
        p2 = p2.next;
    }
    while(p2 != null){
        p1 = p1.next;
        p2 = p2.next;
    }
    return p1;
}
------------------------------------------------------------------
/*Design an OO parking lot*/.l
------------------------------------------------------------------
/* isbst */ LeetCode

class Solution {
    public boolean helperBST(TreeNode root, Integer min, Integer max){
        if(root == null)
            return true;
        if(min != null && root.val <= min  )
            return false;
        if(max != null && root.val >= max)
            return false;
        return helperBST(root.left, min, root.val) && helperBST(root.right, root.val, max);
    }
    
    public boolean isValidBST(TreeNode root) {
        return helperBST(root, null, null);
    }
     
}

------------------------------------------------------------------
/* Find the minimum value in the binary search tree */
public int minBST(Node root){
    if(root.left == null) return root.val;

   return minBST(root.left);
}
------------------------------------------------------------------
/* need to look into this */
public Node deleteNode(Node n){
    if(n == null || n.next == null) return ;
    
    n.data = n.next.data;
    n.next = n.next.next;
    return true;
}

/* in the above solution the last node is not deleted hence the below method */
void deleteNode(Node node, Node n) {
         
        // When node to be deleted is head node
        if (node == n) { //check for the head condition.
            if (node.next == null) {
                System.out.println("There is only one node. The list "
                                 + "can't be made empty ");
                return;
            }
 
            /* Copy the data of next node to head */
            node.data = node.next.data;
 
            // store address of next node
            n = node.next;
 
            // Remove the link of next node
            node.next = node.next.next;
 
            // free memory
            System.gc();
 
            return;
        }
 
        // When not first node, follow the normal deletion process
        // find the previous node
        Node prev = node;
        while (prev.next != null && prev.next != n) {
            prev = prev.next;
        }
 
        // Check if node really exists in Linked List
        if (prev.next == null) {
            System.out.println("Given node is not present in Linked List");
            return;
        }
 
        // Remove node from Linked List
        prev.next = prev.next.next;
 
        // Free memory
        System.gc();
 
        return;
    }

------------------------------------------------------------------
public boolean addLinkList(Node head1, Node head2){
    if(str1 != str2) return false;
    int count = 0;
    for(int i = 0; i < str1.length(); i++){
        if(str1.charAt(i) != str2.charAt(2))
            count++;
        if(count > 1) return false;
    }
    return true;
}
------------------------------------------------------------------
public void sumList(Node head){
    Node head1 = head;
    while(head1.next != null){
        head1 = head1.next;
    }
    while(head.next != null){
        head.data = head.data + head1.data;
        if(head.data > 9){
            
        }
        head = head.next;
    }
}


     
------------------------------------------------------------------
/*Implementation of hashmap... Implement Hash Tables Chaining with List Heads 
link : -http://www.sanfoundry.com/java-program-implement-hash-tables-chaining-list-heads/ 
*    Java Program to Implement Hash Tables Chaining with List Heads
get key is done with getting the hash value of table, iterating thru the link list, if entry is null return null else return the value for that key.
insertThekey method does the same as said above, only if it finds the same key with the equals den it inserts the and if on the same location after hashing it creates entry.next to store on the next location.
myhash function does the hashing
*/ 
 /* Function to get value of a key */
public int getKey(String key){
    
    int hash = (myhash(key) % Table_Size);
    if(table[hash] == null){
        return -1;//return NotFoundException
    }
    else{//start of else
        LinkedhashEntry entry = table[hash];//linkedhash entry is the class name.
        while(entry != null && !entry.key.equals(key))
        {
            entry = entry.next;
        }
        if(entry ==  null)
            return -1;
        else 
            return entry.value;    
    }//end of else
}
/* Function to insert a key value pair */
public void insert(String key, int value){
    int hash = (myhash (key) % Table_Size);
    if(table[hash] == null)
        table[hash] == new LinkedhashEntry(key, value);
    else{
        LinkedhashEntry entry = table[hash];
        while(entry != null && !entry.key.equals(key)){//checks if the the same key is showing up again or not
            entry = entry.next;
        }
        if(entry.key.equals(key))
            entry.value = value; //key are equal so set the value.
        else
            entry.next = new LinkedhashEntry(key, value); //make an entry when collision occurs
    }
    size++;
}

/* Function myhash which gives a hash value for a given string */
    private int myhash(String x )
    {
        int hashVal = x.hashCode( );
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return hashVal;
    }

------------------------------------------------------------------
/*implementation of hashmap other approach */

 public void put(K newKey, V data){
      if(newKey==null)
          return;    //does not allow to store null.
     
      //calculate hash of key.
      int hash=hash(newKey);
      //create new entry.
      Entry<K,V> newEntry = new Entry<K,V>(newKey, data, null);
     
      //if table location does not contain any entry, store entry there.
       if(table[hash] == null){
        table[hash] = newEntry;
       }else{
          Entry<K,V> previous = null;
          Entry<K,V> current = table[hash];
         
          while(current != null){ //we have reached last entry of bucket.
         if(current.key.equals(newKey)){         
             if(previous==null){  //node has to be insert on first of bucket.
                   newEntry.next=current.next;
                   table[hash]=newEntry;
                   return;
             }
             else{
                newEntry.next=current.next;
                previous.next=newEntry;
                return;
             }
         }
         previous=current;
            current = current.next;
        }
        previous.next = newEntry;
       }
   }
   /**
    * Method returns value corresponding to key.
    * @param key
    */
   public V get(K key){
       int hash = hash(key);
       if(table[hash] == null){
        return null;
       }else{
        Entry<K,V> temp = table[hash];
        while(temp!= null){
            if(temp.key.equals(key))
                return temp.value;
            temp = temp.next; //return value corresponding to key.
        }        
        return null;   //returns null if key is not found.
       }
   }



------------------------------------------------------------------

/*Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray [4,−1,2,1] has the largest sum = 6.
link - http://www.programcreek.com/2013/02/leetcode-maximum-subarray-java/ 
In this we take the sum of previous and current, and take maximum out of this 2 and assign to maximum from it. Now that max value is stored into a sum array first and den into max variable.*/

 public int maxSubArray(int[] A) {
       int newsum=A[0];
       int max=A[0];
       for(int i=1;i<A.length;i++){
           newsum=Math.max(newsum+A[i],A[i]);
           max= Math.max(max, newsum);
       }
       return max;
    }

------------------------------------------------------------------

/*    Longest Increasing Subsequence .
link - http://blog.welkinlan.com/2015/11/05/longest-increasing-subsequence-leetcode-java/
Correct link - https://leetcode.com/problems/longest-increasing-subsequence/solution/
In this compare if the previous is less than the next one i.e nums[i] > nums[j] and take all the elements that are less den i, now get the maximum of the dp[i] and dp[j] + 1 so whichever gives the maximum set to dp and den again in the outer for loop get the max from the dp and max and set it to max. In this kind of problem we usually take the max twice.*/
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int dynamicArray[] = new int[nums.length];//d[] to keep max count so far.
        int maxLength = 1;
        
        for(int outerIndex=0; outerIndex<nums.length; outerIndex++){
            dynamicArray[outerIndex] = 1;//setting to 1 which is added every time...dis is 1 bcoz if nothing is found the largest will be only one number hence 1, Important step here.
            for(int innerIndex=0; innerIndex<outerIndex; innerIndex++){
                if(nums[outerIndex] > nums[innerIndex]){//if it is greater than only add the to dp array.
                    dynamicArray[outerIndex] = Math.max(dynamicArray[outerIndex], dynamicArray[innerIndex] + 1);//Take max of current and next one here.
                }
            }
            maxLength = Math.max(maxLength, dynamicArray[outerIndex]);//Taking the max again since we want subsequence.
        }
        return maxLength;  //longest length of the subsequence.
    }
}


Linkedin Question Prep.

IsomorphicStrings


/*




* Time Complexity = O(N), N is the length of String. Both Strings are of equal length


* Space Complexity = O(1) space, constant array of 256 elements are considered O(1)


*/







/*


* Given two strings s and t, determine if they are isomorphic. Two strings are isomorphic if the characters


* in s can be replaced to get t.


* All occurrences of a character must be replaced with another character while preserving the order of


* characters. No two characters may map to the same character but a character may map to itself.


*


* Example 1:


* Input: s = "egg", t = "add"


* Output: true


*


* Example 2:


* Input: s = "foo", t = "bar"


* Output: false


*


* Example 3:


* Input: s = "paper", t = "title"


* Output: true


*


* Note:


* You may assume both s and t have the same length.


*/












/*


* Trick = Increment the frequency count as the last seen index. both indexes should be the same. This will give the


* one to one mapping effect for O(n)


*/


public class IsomorphicStrings {







	public static boolean isIsomorphic(String s, String t) {


/*


* We need one to one mapping. So we will declare an array of 256 for each string.


* Instead of their frequency, we will store their index values at their respective cells in 2 arrays.


* This will simulate the 1 to 1 mapping.


* If the values at those respective character array positions don't match, we return false.


*/


		int[] frequencyArrayForString1 = new int[256];


		int[] frequencyArrayForString2 = new int[256];


		


		for(int index = 0; index < s.length(); index++) {


			if(frequencyArrayForString1[s.charAt(index)] !=


					frequencyArrayForString2[t.charAt(index)])


				return false;


			


//tricky part. Need to update index + 1 to differentiate between the empty values of the array


//going forward.


			frequencyArrayForString1[s.charAt(index)] = index + 1;


			frequencyArrayForString2[t.charAt(index)] = index + 1;


		}


		


		return true;


	}



// a method for getting key of a target value
public Character getKey(HashMap<Character,Character> map, Character target){
for (Map.Entry<Character,Character> entry : map.entrySet()) { // entryset() method call
returns a set view of the mappings contained in this map.
if (entry.getValue().equals(target)) {
return entry.getKey();
}
}
return null;
}
//
count the occurrence in sorted array: since the array is sorted we can get the first and last
index subtract them to get the count of the number.
/*Method 1 (Linear Search)
Linearly search for x, count the occurrences of x and return the count.
Time Complexity: O(n)
Method 2 (Use Binary Search)
1) Use Binary search to get index of the first occurrence of x in arr[]. Let the index of the first
occurrence be i.
2) Use Binary search to get index of the last occurrence of x in arr[]. Let the index of the last
occurrence be j.
3) Return (j – i + 1); */
package Arrays;
public class OccurrencesInArray {
public int findOccurrences(int [] arrA, int x){
int count = 0;
int startPoint = findFirstOccurrence(arrA,x,0,arrA.length1)
;//findingfirst occurence
if(startPoint<0){
return 1;
}
int endPoint = findLastOccurrence(arrA, x, 0, arrA.length1)
;//findinglast occurence
count = endPoint-startPoint+
1;
return count;
}
public int findFirstOccurrence(int [] arrA, int x,int start, int end ){
if(end>=start){
int mid = (start+end)/2;
if((mid==0||(arrA[mid1]<
x)) && arrA[mid]==x){ //check if the middle elem is eq to x
den return mid and also checkng elem before is less den x den return mid which is index for
frst occurnce.
return mid;
}else if(arrA[mid]<x){
return findFirstOccurrence(arrA, x, mid+1, end);//increase mid value bcoz de frst
occurnce iz on the right
}else{
return findFirstOccurrence(arrA, x, start, mid1)
;//every time reducing the size of
mid and passing it again.
}
}else return 1;//
if not found
}
public int findLastOccurrence(int [] arrA, int x,int start, int end ){
if(end>=start){
int mid = (start+end)/2;
if((mid==arrA.length1||
arrA[mid+1]>x) &&(arrA[mid]==x)){//check if the middle elem
is eq to x den return mid and also checkng elem before is greater den x den return mid
which is index for frst occurnce.
return mid;
}else if(arrA[mid]>x){
return findLastOccurrence(arrA, x, start, mid1)
;//reduce the mid size bcoz de last
occurnce is on the left from last.
}else{
return findLastOccurrence(arrA, x, mid+1, end); //every time reducing the size of
mid and passing it again.
}
}else return 1;
//if not found return 1
}
public static void main(String args[]){
int [] arrA = {1,2,2,2,2,2,2,2,3,4,5,5,6};
int x = 2;
OccurrencesInArray i = new OccurrencesInArray();
int r = i.findOccurrences(arrA, x);
System.out.println("No of Occurrences of number " + x + " is : " + r);
}
}

/*
We do a for loop over all the string's characters and save the current char in the charAt
variable
We check if our HashMap already has a charAt key inside it
If it's true we will just get the current value and add one.. this means the string has already
been found to have this char.
If it's false (we never found a char like this in the string), we add 1 because we found a new
char
Stop! Our HashMap will contains all chars (keys) found and how many times it's repeated
(values)!*/
String str = "Hello World";
int len = str.length();
Map<Character, Integer> numChars = new HashMap<Character, Integer>(Math.min(len,
26));
for (int i = 0; i < len; ++i)
{
char charAt = str.charAt(i);
if (!numChars.containsKey(charAt)) //if it does not contains add to the hashmap
{
numChars.put(charAt, 1);
}
else
{
numChars.put(charAt, numChars.get(charAt) + 1); //already present increment it by one
}
}
System.out.println(numChars)
//
approach 2 for the above given problem.
String str = "Hello World";
int[] counts = new int[(int) Character.MAX_VALUE];
// If you are certain you will only have ASCII characters, I would use `new int[256]` instead
for (int i = 0; i < str.length(); i++) {
char charAt = str.charAt(i);
counts[(int) charAt]++;
}
System.out.println(Arrays.toString(counts));

/*
worddistance. In a given list of tokenized string find the minimum distance betwn dem.
Compare if the word in the list is equal to the target list word if yes set the index to i value.
Now take the value of shortdistance as the difference between the two indexa and indexb.
Take the mininmum of the shortdistance as well the absolute of the difference between
indexa and indexb. //O(n) complexity; O(1) space;
("the", "quick", "brown", "fox", "quick")*/
public class wordDistance{
List <String> lst;
public wordDistance(List<String> lst){
this.lst = lst;
}
public void minWordDistance(String a, String b){
int indexa = 1;
int indexb = 1;
int mindistance = 1;
for(int i = 0; i < lst.length(); i++){
String currWord = lst.get(i);
if(currWord.equals(a)) //match the "a" word and if yes set indexa to i
indexa = i;
else if(currWord.equals(b)) //match the "b" word and if yes set indexa to i
indexb = i;
else
continue;
if(!indexa = 1
&& !indexb = 1){
//initialize the first shortestDistance, at very first.
if(mindistance < 0)
shortestDistance = Math.abs(indexa - indexb);
else
shortDistance = Math.min(shortestDistance, Math.abs(indexa - indexb))
;
}
} //end of for loop.
return mindistance;
}
public static void main(String...arg){
WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList("the", "quick",
"brown", "fox", "quick"));
System.out.println(finder.distance("fox","the"));
System.out.println(finder.distance("quick", "fox"));
}
}



//PostOrder Traversal
Recursive solution is trivial, could you do it iteratively?
 
Example 1:

Input: root = [1,null,3,2,4,null,5,6]
Output: [5,6,3,2,4,1]

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        if(root == null)
            return list;
        LinkedList<TreeNode> s = new LinkedList<TreeNode>();//*stack as Linkedlist
        s.push(root);//push first
        
        while(!s.isEmpty()){
            TreeNode curr = s.pollLast();//this poll Last gives us the postorder.
            list.addFirst(curr.val);//here we do add first
            if(curr.left != null) s.add(curr.left);//here we do s.add() instead of push.
            if(curr.right != null) s.add(curr.right);
        }
        return list;
    }
}



/*
postfix, Reverse polish notation...push the number to stack and if operator den pop,
calculate and push again back to. Remember to convert to integer from string and convert
integer to string while pushing to stack */
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<String> s = new Stack<String>();
        String operators = "+*/-";
        for(String str: tokens){
            if(!operators.contains(str)){
                s.push(str);
            }
            else{
                int a = Integer.valueOf(s.pop());
                int b = Integer.valueOf(s.pop());
                switch(str){
                    case "+":
                        s.push(String.valueOf(a + b));
                        break;
                    case "-":
                        s.push(String.valueOf(b - a));
                        break;
                    case "/":
                        s.push(String.valueOf(b / a));
                        break;
                    case "*":
                        s.push(String.valueOf(b * a));
                }
            }
        }
        return Integer.valueOf(s.pop());
    }
}



/*
Number of triangles that can be formed using a given array of numbers. crude way is to do
brute force using three for loop and den putting the condition which results in complexity O(n3).
First sort the array and then compare if A1<A2+A3 if dis is true it holds true for every other condition that is A2<A1+A3 and A3<A1+A3. If we apply the brute force. Hence instead of O(n3) it takes O(n2logn)
Link https://www.quora.com/Whatistheefficientalgorithmtofindthenumberoftrianglespossib
Leinagivenarrayorvector
*/
public int[] getTriangleSides(int[] segments)
{
int []output={};
if(segments.length < 3)
return throw new IllegalArgumentException (“Number of variables required are less to form tri”)
Arrays.sort(segments);
//the triplet will come in order if exists after sorting
int i=0,j=1,p=0; //to start off with different elements.
for(int k=2;k<segments.length;i++,j++,k++)
 {
//assume segments[i],segments[j],segments[k] are three sides of a triangle
if(segments[i]+segments[j]>segments[k])
{
output[p++]=segments[i];
output[p++]=segments[j];
output[p++]=segments[k];
break; //if asked only for 1.
}
   }//end 
  return output;
}
/*
another brute force approach...Triangle triplet o(n3) complexity.*/
/*
Given a array of positive integers, find all possible triangle triplets that can be formed from
this array.
eg: 9 8 10 7
ans: 9 8 10, 9 8 7, 9 10 7, 7 8 10
Note : array not sorted...this has the complexity of O(n3).
*/
public class TriangleTriplet {
public void triangleTriplet(int a[])
{
int n=a.length;
for(int i=0;i<n;i++)
{
for(int j=0;j<n;j++)
{
for(int k=0;j<n;j++)
{
if(i!=j && j!=k && i!=k) //make sure that the 3 of them do not match which will
cause to take the same variable again and again.
if(a[i]+a[j]>a[k] && a[j]+a[k]>a[i] && a[i]+a[k]>a[j])
{
System.out.println(a[i]+" "+a[j]+" "+a[k]);
}
}
}
}
}
public static void main(String[] args) {
// TODO Autogenerated
method stub
int a[]={9,8,7,10};
new TriangleTriplet().triangleTriplet(a);
}
}

/*
Two sum problem
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution.
Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
Return true for 9

Link 


https://github.com/fcarriedo/algorithms/blob/master/exercises/twosumtest/TwoSumTest.java

/**
* Implementation with linear O(n) complexity on 'store'
* and O(1) complexity on 'test'.
*
* Focuses logic into the 'store' algorithm, not in the 'test'
*/
Add element to the internalStore set and den store suming each of dem into the sum “hashset” , now check if it is present in the ‘sum’ hashset.
*/
Class twoSumLinear{
private final Set<Integer> internalStore = new Set<Integer>();
private final HashSet<Integer> sum = new HashSet<Integer>();
void store(int input){
// We calculate all the sums upon storage
if(internalStore.contains(input)) return;// We already have it
for(int value: internalStore){
sum.add(value+input);
}
// Add it to the store
internalStore.add(input);
}
boolean test(int test){
return (sum.conatins(test));
}
}

/*
Regular expression to check if the string contains number or not
It is a regular expression which means:
^ beginning of the string
()?
the hyphen appear one or no times
[09]+
there are one or more digits sequentially
(\\.)? the period appears one or no times (\ is the escape character for the .)
[09]+
there are one or more digits sequentially
$ end of the string */
//"?" is used to match for one or more occurence while "*" is used for 0 or more occurence,
"$" Matches end of line.
// First approach: with different test cases.
//String regex = "^(\\+|)?[09]*(\\.)?[09]*$";
String str = "123.2"//it can be either of the values from below given.
boolean a = str.matches("^(\\+|)?[09]*(\\.)?[09]*$");
System.out.println(a);
Pattern p = Pattern.compile(regex);
assertTrue (p.matcher("123.123").matches());
assertTrue (p.matcher("123.123").matches());
assertTrue (p.matcher("+123.123").matches());
assertFalse (p.matcher("+123s123").matches());
assertTrue (p.matcher(".123").matches());
assertTrue (p.matcher("0.123").matches());
assertTrue (p.matcher("0.").matches());
assertTrue (p.matcher(".0").matches());
assertTrue (p.matcher(".10").matches());
assertTrue (p.matcher(".").matches());




/*
recurvisly call the number if + pass n1
and if negative pass n+1.
if n == 0 is the base condition. for 2^5 and 2^5...
now in frst case n-1
so 5-1, 4-1, 3-1...
and
second case 5+1, 4+1....*/
public class RaiseDouble {
public static double computePower(double x, double n){
//base case
if(n==0){
return 1;
}else if(n>0){ //recursive condition for postive power
return x*computePower(x, n1)
;
}else if(n<0){ //recursive condition for negative power
return (1/x)*computePower(x, n+1); //1/x
}else{
return 1;
}
}
/**
* <p>Compute power</p>
*
* p(x,n) = 1 if(x=0)
* = x*p(x,n1)
if(n>0)
* = (1/x)*p(x,n+1) if(n<0)
* @param x
* @param n
* @return
*/
public static void main(String[] args) {
// TODO Autogenerated
method stub
System.out.println(computePower(2,5));
System.out.println(computePower(2,5));
System.out.println(computePower(2,5));
System.out.println(computePower(2,-5));
}
}

/*
Middle element of stack using O(1)
We use the concept of stack using linked list but with only change that we check the “size” as well in which we */
public class MiddleElementStackO1 {
 public void push(T item) {
  final Node < T > node = new Node < T > (null, item, top);
  if (top == null) {
   top = node;
   middle = node;
  } else {
   top.left = node;
   top = node;
  }

  size++;
  if (size % 2 == 0) {
   middle = middle.left; //since we are pushing and hence move to the left.
  }
 }


 public T pop() {
  if (top == null) {
   throw new EmptyStackException();
  }
  T item = top.item;
  top = top.right;
  // important, to not leak references.
  if (top != null) {
   top.left = null;
  }

  if (top == null) {
   middle = null;
  }

  if (size % 2 == 0) {
   middle = middle.right; //we are popping hence reduce the size and move to the right
  }

  size--;
  return item;
 }

public T getMiddle() {
 if (top == null) {
  throw new EmptyStackException();
 }
 return middle.item;
}

}

/*
Dependency resolution algorithm, detect cycle graph.
http://www.electricmonk.nl/log/2008/08/07/dependency-resolving-algorithm/
*/

We're keeping a list of all the nodes we've seen in the program above. But we've previously determined that a circular reference is occuring when we see a software package more than once, unless that software package has all its dependencies resolved. This means we don't need to remember the nodes we've seen if they are already resolved. This can save us some memory and processing time, since we only have to check a maximum of 'n' (where 'n' is the number of nodes in the graph) times each iteration. The way to go about this is to simply remove the node from the seen list once it has been resolved. We also rename our 'seen' list to 'unresolved' since that better describes what it does now:
def dep_resolve(node, resolved, unresolved):
   unresolved.append(node)
   for edge in node.edges:
      if edge not in resolved:
         if edge in unresolved:
            raise Exception('Circular reference detected: %s -&gt; %s' % (node.name, edge.name))
         dep_resolve(edge, resolved, unresolved)
   resolved.append(node)
   unresolved.remove(node)






/*link - http://www.careercup.com/question?id=5724684365594624 
Check if an integer array is arithmetic sequence.Example: 1, 2, 3, 4, 5, 6, 7, 8 => true 
1, 3, 5, 7, 9 => true Array may not be sorted. */


boolean isArithemticProgression(int[] nums) {
	    	 if(nums.length <=1)
	    		 	return true;
	    	 int sum = 0;
	    	 int n = nums.length;
	    	 int diff = nums[1] - nums[0];
	    	 int min = Integer.MAX_VALUE;
	    	 for (int num : nums) {
	    		 min = Math.min(num,min);
                         sum += num;
	    	 }
	    	 
	    	 if(sum == (2*min + (n - 1) * diff)*n/2) {
	    		 return true;
	    	 }
	    	 return false;
	     }



/* BFS for graphs  uses the concept of visited node array in case of cycles or to keep track of visited and not visited in the array.
link - http://algorithms.tutorialhorizon.com/breadth-first-searchtraversal-in-a-graph/

Approach:
For Graph as well we will use the Queue for performing the BFS.
We will use the boolean[ ] to keep a track of the nodes because unlike trees dur­ing tra­ver­sal we might keep mov­ing into the cir­cles by vis­it­ing same nodes repeatedly.
In our exam­ple we are using the adja­cency List for the Graph Rep­re­sen­ta­tion.
*/

public void BFS(int startVertex) {
		boolean[ ] visited = new boolean[v];// v is the number of nodes in the graph
		Queue<Integer> s = new LinkedList<Integer>();
		s.add(startVertex);
		while (!s.isEmpty()) {
			int n = s.poll(); //retrieve and remove the head of this queue, or returns null if this queue is empty
			System.out.print(" " + n);
			visited[n] = true;
			Node head = array[n].head; //array => adjust neighbour list 
			while (head != null) {
				if (visited[head.dest] == false) {/if the head.dest not in visited
					s.add(head.dest); //head.dest gives the value of that node.
					visited[head.dest] = true;
				}
				head = head.next;
			}
		}
	}


/*Clone a linked list with next and random pointer, arbiter pointer , deep copy
link - http://www.geeksforgeeks.org/clone-linked-list-next-arbit-pointer-set-2/
The idea is to use Hashing. Below is an algorithm.
1. Traverse the original linked list and make a copy in terms of data.
2. Make a hashmap of key value pair with original linked list node and copied linked list node.
3. Traverse the original linked list again and using the hash map adjust the next and random reference of cloned linked list nodes.
*/

// Actual clone method which returns head
    // reference of cloned linked list.
    public LinkedList clone( )
    {
        // Initialize two references, one with original
        // list's head.
        Node origCurr = this.head, cloneCurr = null;
 
        // Hash map which contains node to node mapping of
        // original and clone linked list.
        Map<Node, Node> map = new HashMap<Node, Node>();
 
        // Traverse the original list and make a copy of that
        // in the clone linked list.
        while (origCurr != null)
        {
            cloneCurr = new Node(origCurr.data);
            map.put(origCurr, cloneCurr);
            origCurr = origCurr.next;
        }
 
        // Adjusting the original list reference again.
        origCurr = this.head;
 
        // Traversal of original list again to adjust the next
        // and random references of clone list using hash map.
        while (origCurr != null)
        {
            cloneCurr = map.get(origCurr);
            cloneCurr.next = map.get(origCurr.next);
            cloneCurr.random = map.get(origCurr.random);
            origCurr = origCurr.next;
        }
 
        //return the head reference of the clone list.
        return new LinkedList(map.get(this.head));
    }


link - http://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/
EXAMPLE
Linked List : 1->2->3->4->5->6->7->8->9->10->11 -> null
For k = 2
Return Value: 2->1->4->3->6->5->8->7->10->9->11 ->null
For k = 3
Return value: 3->2->1->6->5->4->9->8->7->10->11 -> null
//look for python example:
http://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/
In the python for recursion call we write as “ self.pairWiseLink(next, k) “
//Java Solution
//package sample1;
//
//public class PairWiseLinkList {
//	public static void pairWiseLink(Node head, int k){
//		Node current = head;
//		Node next = null;
//		Node prev = null;
//		int count = 0;
//		while(count < k && current != null){ //same as reverse link list only maintain count value.
//			next = current.next;
//			current.next = prev;
//			prev = current;
//			current = next;
//			count++;
//		}
 /* next is now a pointer to (k+1)th node
       Recursively call for the list starting from current.
       And make rest of the list as next of first node */
//		if(next != null){
//			head.next = pairWiseLink(next, k);//here the reverse is called twice
//		}
                      Return prev; //this is the new head of the list.
//		
//	}
//}


//Similar LeetCode problem : 

Swap Nodes in Pairs

Given 1->2->3->4, you should return the list as 2->1->4->3.
The only thing changed from above is that the value of k is constant here which is “2” since we want to swap only the adjacent nodes.
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;
        int count = 0;
        while(count < 2 && curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        if(next != null){
            System.out.println("value of next is:" +next.val);
            head.next = swapPairs(next);
        }
        return prev;
    }
}


//25. Reverse Nodes in k-Group

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.


//recursive approach is the same as above with a tweak.






/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
       if(head == null)
           return null;
//Single node linked list
        if(head == null || k<1)
             return head;
        int sizeOfListNode = 0;
        ListNode itr = head;
        //Calculate the length of the ListNode
        while(itr != null){
            itr = itr.next;
            sizeOfListNode++;
        }
        //Reversal process is a bit different from normal linked list reversal.
//We will swap two nodes in each inner 'for' loop iteration, update the 'head' & connect the end of the reversed linked list to the normal next element of original listnode.
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        for(ListNode previous=dummy, tail=head; sizeOfListNode >=k; sizeOfListNode-=k){
            for(int i=1; i<k; i++){
                ListNode tempNode = tail.next.next;
                tail.next.next = previous.next;
                previous.next = tail.next;//new head for this iteration
                tail.next = tempNode;
            }
            previous = tail;
            tail = tail.next;
        }
        return dummy.next;
    }
}








/*find the maximum value in the Binary Tree*/

public static int maxBinaryTree(Node root){
	int max = Integer.MIN_VALUE;
	if(root != null){
		int leftMax = maxBinaryTree(root.left);
		int rightMax = maxBinaryTree(root.right);
		if(leftMax > rightMax)
			max = leftMax;
		else max = rightMax;
		if(root.data > max)
			max = root.data;
}
return max;
}

/*Find element in Binary Tree*/

public static boolean SearchTree(Node root, int data){
	if(root != null) 
return false;
if(root.data == data)
		return true;
	return SearchTree(root.left, data) || SearchTree(root.right, data);
}




/* Find the size of the binary tree */

public static int sizeOfTree(Node root){
	if(root == null) //check if root is null or not
		return throw new IllegalArgumentException("root is null hence no nodes");
	int left, right;
	if(root.left != null)
			left = sizeOfTree(root.left);
		if(root.right != null)
right = sizeOfTree(root.right);
	return 1 + left + right;
}



1315. Sum of Nodes with Even-Valued Grandparent

Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)
If there are no nodes with an even-valued grandparent, return 0.
 
Example 1:

Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparents while the blue nodes are the even-value grandparents.
/*Its normal DFS, so while calling the recursion 
1. Current becomes Parent
2. Parent becomes GrandParent
*/

class Solution {
    int sum = 0;
    public int sumEvenGrandparent(TreeNode root) {
        sumEvenGrandParentHelper(root, null, null);
        return sum;
    }
    
    public void sumEvenGrandParentHelper(TreeNode current, TreeNode parent, TreeNode grandParent){
        if(current == null) return;
        
        if(grandParent != null && grandParent.val % 2 == 0)//getting even
            sum += current.val;
        //Below current →  parent & parent → grandParent
        sumEvenGrandParentHelper(current.left, current, parent);
        sumEvenGrandParentHelper(current.right, current, parent);
    }
}







/*link - https://github.com/mission-peace/interview/edit/master/src/com/interview/tree/LevelOrderTraversalInReverse.java
Print the tree in level order in reverse order, level order in reverse order
take one stack and remove from the queue, add  
Solution
 * Maintain a stack and queue. Do regular level order traversal but
 * put right first in the queue and den left to de queue. Instead of printing put the result
 * in stack. Finally print contents of the stack.
Time and space complexity is O(n)
e.g             1
 *          2         3
 *        4    5    6   7
 * 
 * Output should be 4 5 6 7 2 3 1

*/
public static void printTheTree(Node root){
	Stack<Node> s = new Stack<Node>;
	Queue<Node> q = new LinkedList<Node>;
	q.add(root);
	while(!q.isEmpty()){
root = q.poll(); //removing from the queue
if(root.right != null) q.add(root.right); //adding right, right to the queue if dey r not null
		if(root.left != null) q.add(root.left);
		s.push(root); //push the root 2 de stack
}
while(!s.isEmpty()){
	System.out.println(s.pop().data); //print de stack
}
}


/*You are given a string. You have to eliminate the pairs (two same chars adjacent to each other).
RGBBGBGR --> RGGBGR-->RBGR 
push first char into the stack den peek it if same den pop if not push*/
package sample1;
import java.util.*;

public class EliminateTheAdjacentPairs {
	public static String eliminatePairs(String a){
		Stack<Character> stack = new Stack<Character>();
		char[] ch = a.toCharArray();
		stack.push(ch[0]);
		for(int i = 1; i < a.length(); i++){
			if(ch[i] == stack.peek()) //if same den pop from the stack
				stack.pop();
			else 
				stack.push(ch[i]); //else push into stack.
		}
		return stack.toString();//returning it as to string.
	}
	
	public static void main(String args[]){
		System.out.println(EliminateTheAdjacentPairs.eliminatePairs("RGBBGBGR"));
	}
}


/*a arjun mantri ->   aarjunmantri
moved de two spaces from arjun mantri to front. Take for loop from back and den check for the space if not den put de char to de count location. Now at de end the count will be at front and hence include dat many space in de front. de value of count is number of spaces. */
public class MoveSpaceTofront {
	public static String moveSpaceToFront(String a){
		int count = a.length() - 1;
		char[] ch = a.toCharArray();
		for(int i = a.length() - 1; i >= 0; i--){
			if(ch[i] != '  ')
				ch[count--] = ch[i];//putting char to the count location if no space.
		}
		while(count >= 0)
			ch[count--] = '  ';
		return new String(ch);
	}
	public static void main(String args[]){
		String a = new String (MoveSpaceTofront.moveSpaceToFront("a arjun mantri"));
		System.out.println(a);
	}
}

//http://www.geeksforgeeks.org/delete-n-nodes-after-m-nodes-of-a-linked-list/
//package sample1;
//
//public class DeleteNnodeM {
//	public static void deleteNodes(Node curr,int M, int N){
//		while(curr != null){
//		for(int i = 0; i < M; i++){//to skip to M nodes
//			curr = curr.next;
//		}
//		Node prev = curr;//need to get the previous location
//		for(int i = 0; i < N; i++){
//			curr = curr.next;
//			}
//		prev.next = curr;//delete the nodes in between.
//		}
//	}
//}






/*Implement stack and queue using the LinkList. stack using linklist. queue using linklist */

//package sample1;
//
//public class StackAndQueueLinkedList {
//	Node top = null;
//	int length = 0;
//	public static void push(int data){
//		Node temp = new Node(data);//store in the temp node
//		temp.next(top);//point it to top of the stack
//		top = temp; //den point top to temp
//		length++; //lenght to give de length of list
//	}
//	
//	public static int pop(){
//		if(top == null)//empty stack
//		int a = top.data;
//		top = top.next;
//		length--;
//		return a;
//	}
//	//dis for queue
//	Node rear = null, front = null;
//	public static void add(int data){
//		Node node = new Node(data);
//		if(rear == null) {
//			front = node;
			rear = node
//			}
//			else {
//			rear.next = node;
//			rear = node;
//			}
//		length++;
//	}
//	public static int remove(){ 
//		if(isEmpty())
//			throw new Exception("queue is Empty");
//		int a = front.data;
//		front = front.next;
//		if(isEmpty())
//			rear = null //when only one element is der in queue den v shld set it 2 null
//		return a;
//	}
//}



////link - http://www.crazyforcode.com/merge-linked-list-linked-list-alternate-positions/
//take two pointers and alternately move from one list to other list.
//Time compleixty: O(N)
//public class MergeAlternateLinkList {
//	Node first_next, sec_next;
//	Node p1 = head1;
//	Node p2 = head2;
//	
//	public static void mergeAlternate(Node head1, Node head2){
//		while(p1 != null && p2 != null){
//		first_next = p1.next;
//		sec_next = p2.next;
//		
//		p2.next = first_next;
//		p1.next = p2;
//		
//		p1 = first_next;
//		p2 = sec_next;
//	}
//		p2 = sec; //setting the head again. 
//}


/* Minimum depth of binary Tree 
link - http://www.geeksforgeeks.org/find-minimum-depth-of-a-binary-tree/ */

int minDepth(Node root){
	// Corner case. Should never be hit unless the code is
    // called on root = NULL
if(root == null)
		return 0;
	 // Base case : Leaf Node. This accounts for height = 1.
if(root.left == null && root.right == null)
		return 1;
	// If left subtree is NULL, recur for right subtree
if(root.left == null) 
		return minDepth(root.right) + 1;
	// If right subtree is NULL, recur for right subtree
if(root.right == null) 
		return minDepth(root.left) + 1;
	return Math.min(minDepth(root.right), minDepth(root.left)) + 1;
}










/**

/* Level order tree traversal in Spiral manner. This can be done using two stacks*/




    * Two stack to print in spiral way


    */


   public void spiralWithTwoStack(Node root) {


       if (root == null) {


           return;


       }


       Stack<Node> s1 = new Stack<>();


       Stack<Node> s2 = new Stack<>();


       s1.push(root); //push root into s1 first.



//check if both de stack r empty or not.
//v take two stacks push left into S1 den 2 right


       while (!s1.isEmpty() || !s2.isEmpty()) {


           while (!s1.isEmpty()) { //check for S1 


               root = s1.pop();//POP S1


               System.out.print(root.data + " ");


               if (root.left != null) {


                   s2.push(root.left);//PUSH S2


               }


               if (root.right != null) {


                   s2.push(root.right);//PUSH S2


               }


           }
    //in case of S2 push first right and den left


           while (!s2.isEmpty()) {//CHECK FOR S2


               root = s2.pop();//POP S2


               System.out.print(root.data + " ");


               if (root.right != null) {


                   s1.push(root.right);//PUSH S1


               }


               if (root.left != null) {


                   s1.push(root.left);//PUSH S1


               }


           }


       }


   }







//Panagram - which contains all the characters in a sentence. 
//We promptly judged antique ivory buckles for the next prize - Yes panagram
//arjun - not a pangram.
//just know the ascii value of z-a and den count++ for every character found
//now if the count length is greater den ALPHA_LEN den yes its panagram.
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        final char[] C = kbd.nextLine().toLowerCase().toCharArray();
        
        final int N = C.length;
        boolean isPangram = false;
        final int ALPHA_LEN = 'z' - 'a' + 1;
        if (N >= ALPHA_LEN){
            int count = 0;
            final boolean[] alpha = new boolean[ALPHA_LEN];
            for(int i = N-1; i >= 0; --i){
                char c = C[i];
                if (c >= 'a' && c <= 'z' && !alpha[c -= 'a']){
                    alpha[c] = true;
                    if (++count >= ALPHA_LEN){
                        isPangram = true;
                        break;
                    }
                }/* else if (count + i+1 < ALPHA_LEN){
                    break;
                }*/
            }
        }
        System.out.print(isPangram ? "pangram" : "not pangram");
    }
}






/* insert a element in to linklist */

 /* function to insert a new_node in a list. */
    void sortedInsert(Node new_node)
    {
         Node current;
 
         /* Special case for head node */
         if (head == null || head.data >= new_node.data)
         {
            new_node.next = head;
            head = new_node;
         }
         else {
 
            /* Locate the node before point of insertion. */
            current = head;
 
            while (current.next != null &&
                   current.next.data < new_node.data)
                  current = current.next;
 
            new_node.next = current.next;
            current.next = new_node;
         }
     }


/*Deadlock describes a situation where two or more threads are blocked forever, waiting for each other. Deadlocks can occur in Java when the synchronized keyword causes the executing thread to block while waiting to get the lock, associated with the specified object. Since the thread might already hold locks associated with other objects, two threads could each be waiting for the other to release a lock. In such case, they will end up waiting forever. - See more at: http://www.java2novice.com/java-interview-programs/thread-deadlock/#sthash.FLTy9qyJ.dpuf 
2. good example best one:-. http://javarevisited.blogspot.com/2010/10/what-is-deadlock-in-java-how-to-fix-it.html
*/


package com.java2novice.algos;
 
public class MyDeadlock {
 
    String str1 = "Java";
    String str2 = "UNIX";
     
    Thread trd1 = new Thread("My Thread 1"){
        public void run(){
            while(true){
                synchronized(str1){
                    synchronized(str2){
                        System.out.println(str1 + str2);
                    }
                }
            }
        }
    };
     
    Thread trd2 = new Thread("My Thread 2"){
        public void run(){
            while(true){
                synchronized(str2){
                    synchronized(str1){
                        System.out.println(str2 + str1);
                    }
                }
            }
        }
    };
     
    public static void main(String a[]){
        MyDeadlock mdl = new MyDeadlock();
        mdl.trd1.start();
        mdl.trd2.start();
    }
}

/* Multithreading and Synchronization: */
http://javarevisited.blogspot.com/2011/04/synchronization-in-java-synchronized.html

/* more about synchrnization */
http://javarevisited.blogspot.com/2011/04/synchronization-in-java-synchronized.html






/* Lowest common ancestor in Binary search Tree, BST */

public static Node LCA(Node node, int n1, int n2){
	 if (node == null) {
            return null;
        }
 	// If either n1 or n2 matches with root's key, report
        // the presence by returning root (Note that if a key is
        // ancestor of other, then the ancestor key becomes LCA
        if (node.data == n1 || node.data == n2)
            return node;

        // If both n1 and n2 are smaller than root, then LCA lies in left
        if (node.data > n1 && node.data > n2) {
            return lca(node.left, n1, n2);
        }
 
        // If both n1 and n2 are greater than root, then LCA lies in right
        if (node.data < n1 && node.data < n2) {
            return lca(node.right, n1, n2);
        }
 
        return node;
}



//LeetCode same problem as above:

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val == p.val || root.val == q.val)
            return root;
        if(root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        if(root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}


236. Lowest Common Ancestor of a Binary Tree
****This is BINARY TREE LCA ONLY******
/*This is for binary tree not binary search tree. Hence in this case we traverse left and right of the tree  with the base condition of finding any root == p or root == q. When the left is null we return right, right is null return left otherwise return root.*/

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)
            return root;
        System.out.println(root.val);
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null)
            return right;
        else if(right == null)
            return left;
            else return root;
    }
}

129. Sum Root to Leaf Numbers

/*The idea is to use recursion. In this when we multiply every node’s value with 10 and add the child node to it, when we reach the leaf node we add this to the sum and return the recursion.*/
//So in the above we get 1*10 + 2 = 12 and then 12*10 + 4 = 124 and when we reach leafs node we will add this to the sum and then return.

class Solution {
    int sum = 0;
    public void dfs(TreeNode root, int value){
        if(root == null)
            return;
        value *= 10;
        value += root.val;
        
        if(root.left == null && root.right == null){
            sum += value;
            return; //since we have added the values to the sum we have to return to the above value. recursion here
        }
        
        dfs(root.left, value);
        dfs(root.right, value);
    }
    public int sumNumbers(TreeNode root) {
        if(root == null)
            return 0;
        dfs(root, 0);//passing zero since the parent of root is nothing but zero;
        return sum;
    }
}



/* Maximum difference between node and its ancestor in Binary Tree. -  O ( N )
Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
(A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
 
Example 1:

Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: 
We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.

Get the minimum value from the left and right of tree and …...go recursively until v find de min….. to get the max diff get the minimum and den subtract with max value in the tree*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val); // initialize both max and min with root.val.
    }
    private int dfs(TreeNode n, int max, int min) {
        if (n == null) { return 0; } // base case.
        max = Math.max(n.val, max); // update max.
        min = Math.min(n.val, min); // update min.
        int l = dfs(n.left, max, min); // recurse down.
        int r = dfs(n.right, max, min); // recurse down.
        return Math.max(max - min, Math.max(l, r)); // compare all super/sub differences to get a result.
    }
        
    }





/**
 * Java Program to Implement Flood Fill Algorithm. der r two apporach, first one is 4 connected in which a point is given and v hav to fill above, below, left and right of the point. 
And also 8 connected in which the diganols are also filled. Now v hav to fill wth ‘O’ as obstacles and ‘P’ as the point to be filled with ‘W’. So the 
left -> r,c-1
right -> r,c+1
above -> r+1,c
below -> r-1,c

733. Flood Fill

An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
At the end, return the modified image.
Example 1:
Input: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected by a path of the same color as the starting pixel are colored with the new color Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.

 **/

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor) return image;
        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    
    public void fill(int[][] image, int sr, int sc, int color ,int newColor){
        if(sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || image[sr][sc] != color)
               return;               
        image[sr][sc] = newColor;
        fill(image, sr+1, sc, color, newColor);
        fill(image, sr-1, sc, color, newColor);
        fill(image, sr, sc+1, color, newColor);
        fill(image, sr, sc-1, color, newColor);
    }
}



/* Reverse a stack using recursion. In this case v will use two methods one for pop and from this pop den insert an element into the other stack.*/

import java.util.Stack;

public class ReverseWithoutExtra {
	
	static class ModifiedStack extends Stack<Integer>{
		private void pushToBottom(int a){ //dis is fr push method
			if(this.isEmpty()){
				this.push(a);
				return;
			}
			int temp = this.pop();
			this.pushToBottom(a);
			this.push(temp);
		}
		public void reverse(){ // this for poping method.
			if(this.isEmpty())
				return;
			int temp = this.pop();
			this.reverse();
			this.pushToBottom(temp);
		}
	}
	public static void main(String[] str){
		ModifiedStack stack = new ModifiedStack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println(stack);
		stack.reverse();
		System.out.println(stack);
	}


/* FizzBuzz problem */
public class fizzbuzz { 
public static void main(String[] args){ 
for(int i = 1; i <= 100; i++){ 
String test = ""; 
test += (i % 3) == 0 ? "fizz" : ""; 
test += (i % 5) == 0 ? "buzz" : ""; 
System.out.println(!test.isEmpty() ? test : i); 
} 
}
 } 







/* Quicksort. Take the pivot as the middle element and den sort such that all the element to the left are less den pivot and elements to the right are greater den pivot. 
- See more at: http://www.java2novice.com/java-sorting-algorithms/quick-sort/#sthash.V8pam0dU.dpuf
*/

public static void quickSort(int lowerIndex, int higherIndex)
	int i = 0;
	int j = a.length();
	int pivot = a[ i + ( j - i ) / 2];
            
/**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */

	while( i <= j ) {
		while ( a[ i ] < pivot ){
			i++;	
}
while( a[ j ] > pivot){
	j--;
}
	if(i <= j){
		exchangeNumbers(i, j);
		i++;
		j--;
}
}
// call quickSort() method recursively
if( lowerIndex < j )
	quickSort(lowerIndex , j);
if(i < higherIndex)
		quickSort(i , higherIndex);

}


/* Mirror Tree
If two trees are structurally identical or not. Check for the right1, right2 of the tree and also left1, 
left2 */

public static boolean identiticalTree(Node root1, Node root2){
	if(root1 == null &&  root2 == null)
		return true;
	if(root1 == null || root2 == null)
		return false;
	return identiticalTree(root1.right, root2.right) && identiticalTree(root1.left, root2.left);
}



572. Subtree of Another Tree
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consisting of a node in s and all of this node's descendants. The tree could also be considered as a subtree of itself.
Example 1:
Given tree s:
    3
    / \
   4   5
  / \
 1   2

Given tree t:
  4 
  / \
 1   2

Return true, because t has the same structure and node values with a subtree of s.
 






Example 2:
Given tree s:
    3
    / \
   4   5
  / \
 1   2
    /
   0

Given tree t:
  4
  / \
 1   2

Return false.

class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t){
        if(s == null) return false;
        if(isSame(s, t)) return true;//we do this to check at every stage.
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    public boolean isSame(TreeNode s, TreeNode t) {
        if(s == null && t == null) return true;
        if(s == null || t == null) return false;
        if(s.val != t.val) return false;
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}


101. Symmetric Tree
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
    1
   / \
  2   2
 / \ / \
3  4 4  3

 
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3


class Solution {
    public boolean isSymmetric(TreeNode root) {
        boolean result;
        result = isBooleanTree(root, root);
        return result;
    }
    
    public boolean isBooleanTree(TreeNode n1, TreeNode n2){
        if(n1 == null && n2 == null)
            return true;
        if(n1 == null || n2 == null)
            return false;
        return (n1.val == n2.val) &&   //this is imp to check val of each node.
            isBooleanTree(n1.left, n2.right) && 
            isBooleanTree(n1.right, n2.left);
    }
}


/*Diameter or width of a binary tree which is the number of nodes between left and right leaf nodes.
link - http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 */


    int ans; //global variable. 
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null)
	return 0;
        ans = 1;
        getDepth(root);
        return ans - 1;
        }
    public int getDepth(TreeNode root){
        if(root == null)
            return 0;
        int left = getDepth(root.left); //go to the extreme left of the tree
        int right = getDepth(root.right); //go to the extreme right of the tree
        ans = Math.max(left+right+1, ans); 
        
        return Math.max(left, right) + 1; //this is to make sure that we get max from either left or right of the subtree. Here that means we are returning the depth of its subtree at the root. 
    }




662. Maximum Width of Binary Tree

 * Imagine each level of the binary tree is an array. We need to calculate the difference between the leftmost non-null node of this array and the rightmost non-null node of this array. That is basically
 * subtracting the rightmost non-null node's array index from leftmost non-null node's array index + 1.
 * +1 is for length calculation because array index starting from zero.
 * 
 * We will keep a hashmap, which will have the levels of binary tree as the key and the leftmost non-null
 * node array index as its value.
 * We then take the difference of each non-null node array index at a particular level with the leftmost
 * non-null array index at that level and update our max width.
 * 
 * Since we are using dfs, we will visit each node only once. Leftmost non-null nodes with root.left and
 * the rest non-null nodes with root.right
 */

/*Store the level and index of current node in map. 
The value of maximum width is leftmostnode - rightmostnode at each and every level :Note Diameter is different. Get the value from the map every time for the leftmost node and subtract the rightmost to get this value for each level. Finally we have to do recursion for to transverse this left and right node.*/

public class MaximumWidthOfBinaryTree {
//global variable.
	int maxWidth = 0;
	Map<Integer, Integer> leftmostNonNullNodeIndexPerLevelMap = new HashMap<>();
	
    public int widthOfBinaryTree(TreeNode root) {
    	//edge case
    	if(root == null)
    		return maxWidth;
    	
    	dfs(root, 0, 0);
    	
    	return maxWidth;
    }
	
    
    //Assuming root's level to be zero
    public void dfs(TreeNode root, int level, int currentRootIndex) {
    	if(root == null)
    		return;
   //Storing in the HashMap. 	
    	if(!leftmostNonNullNodeIndexPerLevelMap.containsKey(level))
    		leftmostNonNullNodeIndexPerLevelMap.put(level, currentRootIndex);
    	
    	int leftMostIndexAtThatLevel = leftmostNonNullNodeIndexPerLevelMap.get(level);
    	
    	maxWidth = Math.max(maxWidth, currentRootIndex - leftMostIndexAtThatLevel + 1);
    	
        //important step is to recurse left first. This is because our hashmap will only store the
        //left most index at each level.
    	dfs(root.left, level + 1, 2 * currentRootIndex);
    	dfs(root.right, level + 1, 2 * currentRootIndex + 1);
    }
}


226. Invert Binary Tree
//recursion below


class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;
        
        TreeNode OriginalLeft = root.left;
        TreeNode Originalright = root.right;
        
        root.left = invertTree(Originalright);
        root.right = invertTree(OriginalLeft);
        
        return root;
    }
}




//Non - recurssion

public TreeNode mirrorBinaryTree(TreeNode root) {
        //edge case
        if(root == null)
            return null;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            
            //Normal swapping of nodes just like any other integer values
            TreeNode temp = currentNode.left;
            currentNode.left = currentNode.right;
            currentNode.right = temp;
            
            //now the nodes are reversed. Add their non-null child in the queue and repeat the process.
            if(currentNode.left != null)
                queue.offer(currentNode.left);
            if(currentNode.right != null)
                queue.offer(currentNode.right);
        }
        return root;
    }


/*Edit Distance dynamic programming*/
package sample;

public class EditDistanceDP {
	public static int editDistance(char[] row, char[] col){
		int[][] temp = new int[row.length+1][col.length+1];
		for(int i = 0; i < temp[0].length; i++){
			temp[0][i] = i;//dis to fill the very first row of it.
		}
		for(int i = 0; i < temp.length; i++){
			temp[i][0] = i;//dis to fill the very first col of it.
		}
		
		for(int i = 1; i <= row.length; i++)
			for(int j = 1; j <= col.length; j++){
				if(row[i-1] == col[j-1])//comparing the same element hence storing de diagonal value into temp
					temp[i][j] = temp[i-1][j-1];
				else //storing the min of the three into the temp array
					temp[i][j] = Math.min(temp[i-1][j-1], Math.min(temp[i][j-1],temp[i-1][j])) + 1;
			}
		return temp[row.length][col.length];//return the last value which is the operation to convert into one string to other
	}
	
	
	public static void main(String args[]){
		String str1 = "azced";
        String str2 = "abcdef";
		System.out.println(EditDistanceDP.editDistance(str1.toCharArray(), str2.toCharArray()));
	}
}



One Edit Distance
Given two strings s and t, determine if they are both one edit distance apart.
Note: 
There are 3 possibilities to satisfy one edit distance apart:
Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:
Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.

Example 2:
Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.
/*
 * There're 3 possibilities to satisfy one edit distance apart: 
 * 
 * 1) Replace 1 char:
 	  s: a B c
 	  t: a D c
 * 2) Delete 1 char from s: 
	  s: a D  b c
	  t: a    b c
 * 3) Delete 1 char from t
	  s: a   b c
	  t: a D b c
 */
public boolean isOneEditDistance(String s, String t) {
    for (int i = 0; i < Math.min(s.length(), t.length()); i++) { 
    	if (s.charAt(i) != t.charAt(i)) {
    		if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
    			return s.substring(i + 1).equals(t.substring(i + 1));
			else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
				return s.substring(i).equals(t.substring(i + 1));	        	
			else // s is longer than t, so the only possibility is deleting one char from s
				return t.substring(i).equals(s.substring(i + 1));
    	}
    }       
    //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t 
    return Math.abs(s.length() - t.length()) == 1;        
}


/*Longest Palindromic Subsequence
Link - https://www.youtube.com/watch?v=_nCsPn7_OgI
*/

package sample1;

public class PalindromecSubsequence {
             public int calculate1(char []str){
        int T[][] = new int[str.length][str.length];
        for(int i=0; i < str.length; i++) {
            T[i][i] = 1;//set all the diagonals as 1...since longest will be 1 only.
        } 
        for(int l = 2; l <= str.length; l++){
            for(int i = 0; i < str.length-l + 1; i++){
                int j = i + l - 1;
                if(l == 2 && str[i] == str[j]){// if aa den max length is 2 hence set t[i][j] as 2
                    T[i][j] = 2;
                }else if(str[i] == str[j]){//same start and end.
                    T[i][j] = T[i + 1][j-1] + 2;//"b d b" since b and b are same hence max + 2 
                }else{
                    T[i][j] = Math.max(T[i + 1][j], T[i][j - 1]);//since it does not match hence max of two.
                }
            }
        }
        return T[0][str.length-1];//returning the 0th row and last column which is the max length 
    }
	public static void main(String args[]){
		PalindromecSubsequence lps = new PalindromecSubsequence();
        String str = "agbdba";
        int r2 = lps.calculate1(str.toCharArray());
        System.out.print("  " + r2);
    }
}


/* Insert into binary search tree */
public static void Insert(int data){
	if(root == null){
		root = new Node();
		root.setData(data);
root.setLeft(null);
root.setRight(null);
}
else {
	if(data < root.data){
	Insert(root.Left, data);
}
else
	Insert(root.right, data);
}
return root;
}



/* Given an algorithm counting the number of BST’s possible with n nodes. Also called as Catalan number , This is done using  DP in which we multiply the previous number of possible BST’s and den add to the current one.
link - https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/CountNumberOfTreesInBST.java
link - https://www.youtube.com/watch?v=YDf982Lb84o
*/

public static void countTrees(int n){ //where n is the number of keys the BST can be formed.
	int T[ ] = new int [n+1];
	T[0] = 1;
	T[1] = 1;
	for(int i = 2; i <= n; i++){
		for(int j = 0; j < i ; i++){
			T [ i ] += T [ j ] * T [ i - j - 1] //Now here v take previous number of keys and den multiply and add to the existing one.
}
}
}


/* Doubly link list to Binary search tree (ddl to bst)
building the left subtree first and den recursively again building right subtree, calculate the middle */

public static void constructBSTUtil(Node head, int start, int end){ //de head is de doubly list head
	Node curr = head;
int len = 0;
	while(curr.next != null){
	len++;
	curr = curr.next;
}
return public static void constructBST(head, 0 , len);
}

public static void constructBST(Node head, int start, int end){
	if(start > end)
		return null;
	int mid = start + (end - start)/2;
	//building the left subtree first and den recursively again building right subtree
	Node left = constructBST(head, start, mid - 1);//making mid as the root.
	Node root = new Node(head.data);
	root.left = root;
	if(head.next != null){
		head.data = head.next.data;
		head.next = head.next.next;
}
	root.right = constructBST(head, mid + 1, end); //construct the right subtree
	return root; //returning the head of the root.
}

/* Longest common subsequence….if two character at a ith location in string are not same den take max of left and above of ith location, if the two character at ith location in the string are the same den take  1 + diagonal value, every time compare with max and if greater den max store in the max
LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
.*/

public static int longestCommonSubsequence (char [ ]s1, char [ ]s2){
	int max = 0;
	int[ ][ ] temp = new int[s1.length + 1] [s2.length + 1];
	for(int i = 1; i < temp.length; i++){
		for(int j = 1; j < temp[1].length i++){
			if(s1 [ i - 1] == s2 [ j - 1 ])
				temp[ i ][ j ] = temp[ i-1 ][ j-1 ] + 1;//if char are same den diagonal + 1
			else
				temp[ i ][ j ] = Math.max( temp[ i ][ j - 1 ], temp[ i-1 ][ j ] ); //if different max of left and above.
			if(temp[ i ][ j ] > max)
				max = temp [ i ] [ j ];
		}//end of inner for loop;
	}//end of outer for loop.
	return max;
}


// Java program to find the maximum repeating number. we take the mod of the numbers in the array and den take the maximum element’s index which is the maximum repeating count of that number. */
import java.io.*;

class MaxRepeating {

	// Returns maximum repeating element in arr[0..n-1].
	// The array elements are in range from 0 to k-1
	static int maxRepeating(int arr[], int n, int k)
	{
		// Iterate though input array, for every element
		// arr[i], increment arr[arr[i]%k] by k
		for (int i = 0; i< n; i++)
			arr[(arr[i]%k)] += k; //important step to get that.

		// Find index of the maximum repeating element
		int max = arr[0], result = 0;
		for (int i = 1; i < n; i++)
		{
			if (arr[i] > max)
			{
				max = arr[i];
				result = i;
			}
		}

		// Return index of the maximum element
		return result;
	}

	/*Driver function to check for above function*/
	public static void main (String[] args)
	{

		int arr[] = {2, 3, 3, 5, 3, 4, 1, 7};
		int n = arr.length;
		int k=8;
		System.out.println("Maximum repeating element is: " +
						maxRepeating(arr,n,k));
	}
}


/* power set problem.
link - http://stackoverflow.com/questions/1670862/obtaining-a-powerset-of-a-set-in-java
add head, set to newset and den newset, set to sets */
package sample1;
//O(2^n)
import java.util.*;
public class PowerSet {
	public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
	    Set<Set<T>> sets = new HashSet<Set<T>>();
	    if (originalSet.isEmpty()) {
	    	sets.add(new HashSet<T>());
	    	return sets;
	    }
	    List<T> list = new ArrayList<T>(originalSet);//settng list to origianl list initially.
	    T head = list.get(0);
	    Set<T> rest = new HashSet<T>(list.subList(1, list.size()));//remaining list 
	    for (Set<T> set : powerSet(rest)) {//iterating on the rest of the list
	    	Set<T> newSet = new HashSet<T>(); //in every loop newset is declared. 
	    	System.out.println(" set "+set);
	    	newSet.add(head);//add head first
	    	newSet.addAll(set);//every time adding set 
	    	System.out.println(" newset "+newSet);
	    	sets.add(newSet);
	    	sets.add(set);
	    	System.out.println(" sets "+sets);
	    }		
	    return sets;
	}
	
	public static void main(String args[]){
		 Set<Integer> mySet = new HashSet<Integer>();
		 mySet.add(1);
		 mySet.add(2);
		 mySet.add(3);
		 for (Set<Integer> s : PowerSet.powerSet(mySet)) {
		     //System.out.println(s);
		 }
	}
}


/*Max heap sort, max heapify. In this the root is always greater than its child. we first compare the left node is greater than the parent if yes, replace and den right node is greater den max value(which we got from max of left and parent) if yes set it to the max value. Now check if the.
link - https://www.youtube.com/watch?v=ScF985Y4DJc
 */

public static void heapify(int[] a){
	for (int i = a.length()/2; i >= 0; i--)//for loop to do the max heap everytime
		maxHeap(a, i);
}

public static maxHeap(int[] a, int i){ //a is the array and ‘i’ is the node from which we want to do maxheapify.
	int left = 2i;
	int right = 2i + 1;
	int max = 0;
	if(left <= a.length() && a[left] > a[i]) //first is to chck if left is in the tree or not and den to chck if the left is greater den the parent also
		max = left; //storing the max index to max
	if(right <= a.length() && a[right] > a[max]) //frst chck if right is in tree or not and den chck if right is greater den both the left and de parent
		max = right;
	if(max != i){
		swap(a, max, i); //swapping the elements not in place.
		maxHeap(a, max); //recursively callng to sort as maxheap wth new max
}
}


/*Find Nth largest Element in BST*
To find Kth largest element in a Binary search tree, the simplest logic is to do reverse inorder traversal and while doing reverse inorder traversal simply keep a count of the number of Nodes visited. When the count becomes equal to k, we stop the traversal and print the data. It uses the fact that reverse inorder traversal will give us a list sorted in descending order./
Public static treeNode findNthLargest(treeNode root, int n, int count) {
	
if(root == null || count >= n)
		return;

//search in right subtree first as we need to find Nth Largest
//if Nth smallest we need to find search in left subtree first

findNthLargest(treeNode root.right, n, count);

// Increment count of visited nodes
//if not found in right subtree check if root is Nth largest or not
	
if(++count == n){// Increment count of visited nodes
		System.out.println(root.data);
return; 
}

//if not found in right subtree search left subtree

findNthLargest(treeNode root.left, int n, int count);

}


/* Infix to postfix using stacks. If operand den append to de result if operator den check precedence with the operator in the stack if greater den push if less pop de element from de stack and append to the result. If ‘ ( ‘ den push to stack and when u encounter next ‘ ) ‘ pop until the first ‘ ( ’ bracket */

public static String convertToPostfix(String a){
	Stack<Character> s = new Stack<>();
	StringBuffer result = new StringBuffer();
	Char[] ch = a.toCharArray();
	for(int i = 0; i < ch.length(); i++){
		if( isOperand(ch[ i ]) )
{
			result.append(ch [ i ]);
}
		else if(ch[ i ] == ‘(’ )
{
			s.push(ch[ i ]);
}
else if(ch[ i ] == ‘)’)
{
	while(!s.isEmpty() && s.peek() != ‘(’ )
result.append(s.pop())
s.pop() //only popping the opening brckt
}
else
{
	if(!s.isEmpty() && Prec(ch [ i ]) < Prec(s.peek()) )//if char at stack is greater pop and append to the result.
		result.append(s.pop());
	else
		s.push(ch [ i ]);
}
}

while(!s.isEmpty())
{
	result.append(s.pop());
}

return result.toString();
}

// A utility function to check if the given character is operand
public static int isOperand(char ch)
{
    return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
}
 
// A utility function to return precedence of a given operator
// Higher returned value means higher precedence
public static int Prec(char ch)
{
    switch (ch)
    {
    case '+':
    case '-':
        return 1;
 
    case '*':
    case '/':
        return 2;
 
    case '^':
        return 3;
    }
    return -1;
}




binary heap over the bst for priority queue.
http://www.geeksforgeeks.org/why-is-binary-heap-preferred-over-bst-for-priority-queue/

Finding minimum and maximum are not naturally O(1), but can be easily implemented in O(1) by keeping an extra pointer to minimum or maximum and updating the pointer with insertion and deletion if required. With deletion we can update by finding inorder predecessor or successor.
Inserting an element is naturally O(Logn)
Removing maximum or minimum are also O(Logn)
Decrease key can be done in O(Logn) by doing a deletion followed by insertion. See this for details.
So why is Binary Heap Preferred for Priority Queue?
Since Binary Heap is implemented using arrays, there is always better locality of reference and operations are more cache friendly.
Although operations are of same time complexity, constants in Binary Search Tree are higher.
We can build a Binary Heap in O(n) time. Self Balancing BSTs require O(nLogn) time to construct.
Binary Heap doesn’t require extra space for pointers.
Binary Heap is easier to implement.
There are variations of Binary Heap like Fibonacci Heap that can support insert and decrease-key in Θ(1) time.



/*Kth smallest or largest element in an array. Use quick select in which we pass kth index to the partition function and den it returns pivot, and if the pivot index is same as kth den that is element we are searching */
http://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/

public static int kthSmallest (int [ ] a, int l, int r ){
	if( k > 0 && k <= r - l - 1 ){
		int pos = partition (a, l , r);
		
	if (pos - l == k - 1) //if the position is the same kth den we found it.
            	return arr[pos];
       	 if (pos-l > k-1)  // If position is more, recur for left subarray
            	return kthSmallest(a, l, pos-1, k);
 
       	 // Else recur for right subarray
       		 return kthSmallest(arr, pos+1, r, k-pos+l-1);

}
}
// Standard partition process of QuickSort.
    // It considers the last element as pivot
    // and moves all smaller element to left of
    // it and greater elements to right

public int partition(int [ ] a, int l, int r ){
	int x = a[ r ];
	int i = l;
	for(int j = l; j <= r -1; j++){
		if(a[ j ] <= x)
{
swap(a[ i ], a[ j ]);
		i++;
		}
}//end of for loop
swap(a[ i ], a[ r ] );
return i;
}


/*Find position of an element in a sorted array of infinite numbers 
Since array is sorted, the first thing clicks into mind is binary search, but the problem here is that we don’t know size of array.
If the array is infinite, that means we don’t have proper bounds to apply binary search. So in order to find position of key, first we find bounds and then apply binary search algorithm.
Let low be pointing to 1st element and high pointing to 2nd element of array, Now compare key with high index element,
->if it is greater than high index element then copy high index in low index and double the high index.
->if it is smaller, then apply binary search on high and low indices found.
*/
def binarySearch (a, l, h, key):
	mid = ( l + h ) / 2
	if l <= h:
		if a[mid] == key:
		return mid

		if(key < a[ mid ]) :
			return binarySearch(a, 0, mid-1, key)
		return binarySearch(a, mid+1, len(a), key)
	return -1
def findTheElement(a, key):
l, h, val = 0, 1, a[0]
if val < key:
l = h
h = h * 2
return binarySearch(a, l, h, key)	

if ‘__name__’ == ‘__main__’
	findTheElement(a, 100) //find 100 in list of unknkown size 


/* Immutable class in java example */

public class ImmutableClass {
	private final String mobile;
	private final String number;
	
	public ImmutableClass(String mobile, String number){
		this.mobile = mobile;
		this.number = number;
}

public String getterMobile(){
		return mobile;
}
public String getterNumber(){
		return number;
}


}

/* The only difference between offer and add is the interface they belong to. offer belongs to Queue<E>, whereas add is originally seen in Collection<E> interface. Apart from that both methods do exactly the same thing - insert the specified element into priority queue. */

/* stack using two queue. In this if q1 is empty push element into it else dequeue from q1 and enqueue into q2. Now add the new element to q1 and again add back all the elements from q2 to q1.*/

public static void push(int element){
	Queue<Integer> q1 = new LinkedList<Integer>();
	Queue<Integer> q2 = new LinkedList<Integer>();
	if(q1.isEmpty()){
		q1.add(element);
}
else {    //remove q1 to add to q2, add new elem to q1 and put ol elem back to q1 from q2.
	while(!q1.isEmpty())
		q2.add(q1.remove());
	q1.add(element);
	while(!q2.isEmpty())
		q1.add(q2.remove());
}
}

public static int pop(){
	if(q1.isEmpty())
		throw new NoSuchElementException("Underflow Exception"); //important
	else return q1.remove();
}

/* Queue using two stack. If s1 is empty den push into s1 else pop all de elem from s1 and push into s2, add new element to s1, now push all de elem from s2 to s1 */

public static void add(int element){
	Stack<Integer> s1 = new Stack<Integer>();
	Stack<Integer> s2 = new Stack<Integer>();
	if(s1.isEmpty())
		s1.push(element);
	else{
		while(!s1.isEmtpy())
			s2.push(s1.pop());
		s1.push(element);
		while(!s2.isEmpty())
			s1.push(s2.pop());
}
}

public static int remove(){
	if(s1.isEmpty())
		throw new NoSuchElementException(“Underflow exception”);
	return s1.pop();
}



/**




    * Uses bottom up DP to find the edit distance


    */


   public int dynamicEditDistance(char[] str1, char[] str2){


       int temp[][] = new int[str1.length+1][str2.length+1];


      


       for(int i=0; i < temp[0].length; i++){


           temp[0][i] = i;


       }


      


       for(int i=0; i < temp.length; i++){


           temp[i][0] = i;


       }


      


       for(int i=1;i <=str1.length; i++){


           for(int j=1; j <= str2.length; j++){


               if(str1[i-1] == str2[j-1]){


                   temp[i][j] = temp[i-1][j-1];


               }else{


                   temp[i][j] = 1 + min(temp[i-1][j-1], temp[i-1][j], temp[i][j-1]);


               }


           }


       }


       printActualEdits(temp, str1, str2);


       return temp[str1.length][str2.length];


      


   }




295. Find Median from Data Stream
https://leetcode.com/problems/find-median-from-data-stream/discuss/74047/JavaPython-two-heap-solution-O(log-n)-add-O(1)-find 

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:
MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 
Example 1:
Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
Priority Queue
O(log n) time for the enqueing and dequeing methods (offer, poll, remove() and add)
O(n) for the remove(Object) and contains(Object) methods
O(1) for the retrieval methods (peek, element, and size)

private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder()); //this is making max heap
private PriorityQueue<Integer> large = new PriorityQueue<>(); //min heap default
private boolean even = true;

public double findMedian() {
    if (even)
        return (small.peek() + large.peek()) / 2.0;
    else
        return small.peek();
}

public void addNum(int num) {
    if (even) {
        large.offer(num);//this is adding to large if even
        small.offer(large.poll());
    } else {
        small.offer(num);//this is adding to small if even
        large.offer(small.poll());
    }
    even = !even;
}


198. House Robber
The below written explanation -  approach is the same for the “HOUSE ROBBER” problem.
/* Maximum sum such that no two elements are adjacent. 
Take the Maximum of ( Add the current number to the exclusive and inclusive). Now set the exclusive to previous value of inclusive value.
3 2 7 10 should return 13 (sum of 3 and 10)
link - http://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
youtube - https://www.youtube.com/watch?v=UtGtF6nc35g
*/

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:
Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.



public int maxSum(int arr[]) {
        int excl = 0;
        int incl = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int temp = incl; //we have temp because they should not be adjacent.
            incl = Math.max(excl + arr[i], incl);
            excl = temp;//since we  want to retain the  old number here.
        }
        return incl;
    }


visa
http://www.geeksforgeeks.org/find-number-of-islands/
http://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-twice/
//link - https://rosettacode.org/wiki/Power_set#Python
//PowerSet in python:
def powersetlist(s):
   r = [[]]
   for e in s:
       print "r: %-55r e: %r" % (r,e)
       r += [x+[e] for x in r] //here taking the combination of e with x
   return r
 print "\npowersetlist(%r) =\n  %r" % (s, powersetlist(s))
Output:
r: [[]]                                                    e: 0
r: [[], [0]]                                               e: 1
r: [[], [0], [1], [0, 1]]                                  e: 2
r: [[], [0], [1], [0, 1], [2], [0, 2], [1, 2], [0, 1, 2]]  e: 3

powersetlist([0, 1, 2, 3]) =
  [[], [0], [1], [0, 1], [2], [0, 2], [1, 2], [0, 1, 2], [3], [0, 3], [1, 3], [0, 1, 3], [2, 3], [0, 2, 3], [1, 2, 3], [0, 1, 2, 3]]

//Sieve of Eratosthenes. Find prime number for given limit of numbers.
 // Return boolean list of prime numbers
//O(n log log n)
 private int[] calcPrimes(int N)
   {
       int[] arr = new int[N + 1];
       for (int i = 2; i <= Math.sqrt(N); i++)
       {
           if (arr[i] == 0)//initially ol are set to zero only
           {
               for (int j = i * i; j <= N; j += i)//here setting multiple of 2, 3, 4 and so on and setting it to 1
               {
                   arr[j] = 1;
               }
           }
       }
       return arr;
   }
   /** Function to get all primes **/
   public void getPrimes(int N)
   {
       int[] primes = calcPrimes(N);
       display(primes);//or display here itself
   }
public void display(int[] primes)
   {
       System.out.print("\nPrimes = ");
       for (int i = 2; i < primes.length; i++)
           if (primes[i] == 0)
               System.out.print(i +" ");
       System.out.println();
   }






Powertset:
https://www.quora.com/How-do-I-find-powerset-for-a-given-set-in-Java

// Copyright (C) 2014 Toby Thain, toby@telegraphics.com.au
object Powerset {


 def powerset[T](s:Set[T]):Set[Set[T]] = {
   if(s.isEmpty)
     Set(Set())
   else
     s.flatMap( e => {
       val ps = powerset(s - e)
       ps ++ ps.map(_ + e)
     } )
 }


 def main(args:Array[String]) {
   println(powerset(Set('apple, 'banana, 'orange)))
 }


}


//http://www.geeksforgeeks.org/find-pythagorean-triplet-in-an-unsorted-array/
//We can solve this in O(n2) time by sorting the array first.
1) Do square of every element in the input array. This step takes O(n) time.
2) Sort the squared array in increasing order. This step takes O(nLogn) time.
3) To find a triplet (a, b, c) such that a = b + c, do the following.
Fix ‘a’ as the last element of the sorted array.
Now search for pair (b, c) in the subarray between the first element and ‘a’. A pair (b, c) with a given sum can be found in O(n) time using the meet in the middle algorithm discussed in method 1 of this post.
If no pair is found for current ‘a’, then move ‘a’ one position back and repeat step 3.b.


static boolean isTriplet(int arr[], int n)
    {
        // Square array elements
        for (int i=0; i<n; i++)
            arr[i] = arr[i]*arr[i];
  
        // Sort array elements
        Arrays.sort(arr);
  
        // Now fix one element one by one and find the other two
        // elements
        for (int i = n-1; i >= 2; i--)
        {
            // To find the other two elements, start two index
            // variables from two corners of the array and move
            // them toward each other
            int l = 0; // index of the first element in arr[0..i-1]
            int r = i-1; // index of the last element in arr[0..i-1]
            while (l < r)
            {
                // A triplet found
                if (arr[l] + arr[r] == arr[i])
                    return true;
  
                // Else either move 'l' or 'r'
                if (arr[l] + arr[r] < arr[i])
                   l++;
                else
                   r--;
            }
        }
  
        // If we reach here, then no triplet found
        return false;
    }
     


//LeetCode – Maximum Product Subarray (Java)
//For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
//Link - http://www.programcreek.com/2014/03/leetcode-maximum-product-subarray-java/
//The brute force approach wud b 2 find the product of first element with all others den store the maximum in the max variable which would result in  O( n3 ).

public int maxProduct(int[] A) {
    int max = Integer.MIN_VALUE;
 
    for(int i=0; i<A.length; i++){
        for(int l=0; l<A.length; l++){
            if(i+l < A.length){ // addition of index is should be within de length
                int product = calProduct(A, i, l); // 2’s product iz takn wth ol until j
                max = Math.max(product, max);
            }
 
        }
 
    }
    return max;
}
 
public int calProduct(int[] A, int i, int j){ //dis does product from i to j and stores to result.
    int result = 1;
    for(int m=i; m<=j; m++){
        result = result * A[m];
    }
    return result;
}


/*Java Solution 2 - Dynamic Programming (This is good solution)
If the current number is greater than 0 then we do multiplication of two positive numbers and when negative values then negative multiplication */


class Solution {
    public int maxProduct(int[] nums) {
        int max_so_far = nums[0];
        int curr_min = nums[0];
        int curr_max = nums[0];
        int result = nums[0];
        int min_so_far = nums[0];
        
        for(int i=1; i<nums.length; i++){
            if(nums[i] > 0){
                curr_max = Math.max(nums[i], nums[i] * max_so_far); // here we use max_so_far because of the positive number.
                curr_min = Math.min(nums[i], nums[i] * min_so_far);
            }
            else{
                curr_max = Math.max(nums[i], nums[i] * min_so_far); //since its negative number we use min_so_far.
                curr_min = Math.min(nums[i], nums[i] * max_so_far);
            }
            max_so_far = curr_max; 
            min_so_far = curr_min;
            result = Math.max(result, max_so_far);
        } 
        return result;
    }
}




3sum problem:
https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/ 
Sort the array and then take the left & right element from the array. Add this  L and R element with the current element a[ i ] from the array to get the target value.

class FindTriplet {
 
    // returns true if there is triplet with sum equal
    // to 'sum' present in A[]. Also, prints the triplet
    boolean find3Numbers(int A[], int arr_size, int sum)
    {
        int l, r;
 
        /* Sort the elements */
        quickSort(A, 0, arr_size - 1);
 
        /* Now fix the first element one by one and find the
           other two elements */
        for (int i = 0; i < arr_size - 1; i++) {
 
            // To find the other two elements, start two index variables
            // from two corners of the array and move them toward each
            // other
            l = i + 1; // index of the first element in the remaining elements
            r = arr_size - 1; // index of the last element
            while (l < r) {
                if (A[i] + A[l] + A[r] == sum) {
                    System.out.print("Triplet is " + A[i] +
                                 ", " + A[l] + ", " + A[r]);
                    return true;
                }
                else if (A[i] + A[l] + A[r] < sum)
                    l++;
 
                else // A[i] + A[l] + A[r] > sum
                    r--;
            }
        }
 
        // If we reach here, then no triplet was found
        return false;
    }
 
    int partition(int A[], int si, int ei)
    {
        int x = A[ei];
        int i = (si - 1);
        int j;
 
        for (j = si; j <= ei - 1; j++) {
            if (A[j] <= x) {
                i++;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        int temp = A[i + 1];
        A[i + 1] = A[ei];
        A[ei] = temp;
        return (i + 1);
    }
 
    /* Implementation of Quick Sort
    A[] --> Array to be sorted
    si  --> Starting index
    ei  --> Ending index
     */
    void quickSort(int A[], int si, int ei)
    {
        int pi;
 
        /* Partitioning index */
        if (si < ei) {
            pi = partition(A, si, ei);
            quickSort(A, si, pi - 1);
            quickSort(A, pi + 1, ei);
        }
    }
 
    // Driver program to test above functions
    public static void main(String[] args)
    {
        FindTriplet triplet = new FindTriplet();
        int A[] = { 1, 4, 45, 6, 10, 8 };
        int sum = 22;
        int arr_size = A.length;
 
        triplet.find3Numbers(A, arr_size, sum);
    }
}

//**leet solution**//

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int l, r;
        
        List<List<Integer>> solutions = new ArrayList();
        HashSet<String> s = new HashSet();
        Arrays.sort(nums);//important step for the this 2pointer method.
        for(int i=0; i<nums.length - 1; i++){
            l = i + 1;
            r = nums.length - 1;
            while(l < r){
                if(nums[l] + nums[i] + nums[r]  == 0)
                {
                    List<Integer> eachSolution = Arrays.asList(nums[i], nums[l], nums[r]);
                    //System.out.println("found");
                    
                    String curr_string = String.valueOf(nums[i])  + String.valueOf(nums[l]) + String.valueOf(nums[r]);
                    if(!s.contains(curr_string))
                    {
                       s.add(curr_string);
                       solutions.add(eachSolution);
                    }
                    l++;
                    r--;
                    
                }
                else if(nums[l] + nums[i] + nums[r] > 0)
                        r--;
                else l++;
            }
            
        }
 return solutions;       
}
}




/*LRU cache 
LRU cache will have HashMap and Doubly LinkedList. In Which HashMap will hold the keys and address of the Nodes of Doubly LinkedList . And Doubly LinkedList will hold the values of keys
We will remove element from bottom and add element on start of LinkedList and whenever any entry is accessed , it will be moved to top. so that recently used entries will be on Top and Least used will be on Bottom..*/



package com.learning;









/* package whatever; // don't place the package name! */







import java.util.HashMap;


class Entry {


	int value;


	int key;


	Entry left;


	Entry right;


}


public class LRUCache {







	HashMap<Integer, Entry> hashmap;


	Entry start, end;


	int LRU_SIZE = 4; // Here i am setting 4 to test the LRU cache, implementation, it can make be dynamic






	public LRUCache() {


		hashmap = new HashMap<Integer, Entry>();


	}







	public int getEntry(int key) {


		if (hashmap.containsKey(key)) // Key Already Exist, just update it.


		{


			Entry entry = hashmap.get(key);


			removeNode(entry);


			addAtTop(entry);


			return entry.value;


		}


		return -1;


	}







	public void putEntry(int key, int value) {


		if (hashmap.containsKey(key)) // Key Already Exist, just update the value and move it to top


		{


			Entry entry = hashmap.get(key);


			entry.value = value;


			removeNode(entry);


			addAtTop(entry);


		} else {


			Entry newnode = new Entry();


			newnode.left = null;


			newnode.right = null;


			newnode.value = value;


			newnode.key = key;


			if (hashmap.size() > LRU_SIZE) // We have reached maxium size so need to make room for new element.


			{


				hashmap.remove(end.key);


				removeNode(end);				


				addAtTop(newnode);







			} else {


				addAtTop(newnode);


			}







			hashmap.put(key, newnode);


		}


	}


	public void addAtTop(Entry node) {


		node.right = start;


		node.left = null;


		if (start != null)


			start.left = node;


		start = node;


		if (end == null)


			end = start;


	}







	public void removeNode(Entry node) {







		if (node.left != null) {


			node.left.right = node.right;


		} else {


			start = node.right;


		}







		if (node.right != null) {


			node.right.left = node.left;


		} else {


			end = node.left;


		}


	}


	public static void main(String[] args) throws java.lang.Exception {


		// your code goes here


		LRUCache lrucache = new LRUCache();


		lrucache.putEntry(1, 1);


		lrucache.putEntry(10, 15);


		lrucache.putEntry(15, 10);


		lrucache.putEntry(10, 16);


		lrucache.putEntry(12, 15);


		lrucache.putEntry(18, 10);


		lrucache.putEntry(13, 16);







		System.out.println(lrucache.getEntry(1));


		System.out.println(lrucache.getEntry(10));


		System.out.println(lrucache.getEntry(15));







	}


}



Trie data structures:

https://java2blog.com/trie-data-structure-in-java/
See the Leetcode comments (first comment only) section for the same. It's much better.

Implement Trie (Prefix Tree)
Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
With my solution I took the simple approach of giving each TrieNode a 26 element array of each possible child node it may have. I only gave 26 children nodes because we are only working with lowercase 'a' - 'z'. If you are uncertain why I made the root of my Trie an empty character this is a standard/typical approach for building out a Trie it is somewhat arbitrary what the root node is.
For insert I used the following algorithm. Loop through each character in the word being inserted to check if the character is a child node of the current TrieNode i.e. check if the array has a populated value in the index of this character. If the current character ISN'T a child node of my current node add this character representation to the corresponding index location then set the current node equal to the child that was added. However if the current character IS a child of the current node only set the current node equal to the child. After evaluating the entire String the Node we left off on is marked as a word this allows our Trie to know which words exist in our "dictionary"
For search I simply navigate through the Trie if I discover the current character isn't in the Trie I return false.
After checking each Char in the String I check to see if the Node I left off on was marked as a word returning the result.
Starts with is identical to search except it doesn't matter if the Node I left off was marked as a word or not if entire string evaluated i always return true;
 //Look into the comment section on leetcode.

class TrieNode{
    boolean isWord;
    char val;
    public TrieNode[] child = new TrieNode[26];
    
    public TrieNode(){}
    public TrieNode(char c){//So whenever we create the object we need to update val, isWord and Child[] array for that object.
        TrieNode node = new TrieNode();
        node.val = c;
    }
}

class Trie {
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode ws = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(ws.child[c - 'a'] == null){//This subtract - ‘a’ gives the actual position in the 26 list.
                ws.child[c - 'a'] = new TrieNode(c); //passing it to the object from here.
            }
            ws = ws.child[c - 'a']; //setting the value of child here in the object ws.
        }
        ws.isWord = true; //marking isWord as true for that word, object that we created above.
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode ws = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(ws.child[c - 'a'] == null) return false;//This subtract - ‘a’ gives the actual position in the 26 list.
            ws = ws.child[c - 'a']; //setting the value of child here in the object ws.
        }
        return ws.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode ws = root;
        for(int i=0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            if(ws.child[c - 'a'] == null) return false;//This subtract - ‘a’ gives the actual position in the 26 list.
            ws = ws.child[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */








Word Break :
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
/* This is the DP problem and in this we create the matrix, we check if the current boolean array value i.e dp[] is true and also if the substring of the word contains in the dictionary given; if yes we make dp[] as true and then finally return the value of the dp[s.length()] */


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordsDict = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j < i ; j++){
                if(dp[j] && wordsDict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}









215. Kth Largest Element in an Array
Example 1:
Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

class Solution {
    public int findKthLargest(int[] nums, int k) {
        if(nums.length == 0)
            return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();//min heap
        
        for(int i = 0; i<nums.length; i++){
            pq.add(nums[i]);
            if(k < pq.size())
                pq.poll();
        }
        return pq.peek();
    }
}


K-Closest Points to Origin
/*
We need to find the k closest points. The best way to achieve this would be to use PriorityQueue. It would give us K - closest distance. But we need a mapping between distance and the point. This mapping can be used by using HashMap. In the map we are storing ‘Key’ as distance and value as the location or the coordinates.
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
(Here, the distance between two points on a plane is the Euclidean distance.)
You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 
Example 1:
Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].


Example 2:
Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)

Link - https://medium.com/algorithm-and-datastructure/k-closest-points-to-origin-30da05aaff1d

*/
/*Better solution with Priority Queue (which is basically max heap in this case)*/
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((arr1, arr2) -> {
           return (arr2[0] * arr2[0] + arr2[1] * arr2[1]) - (arr1[0] * arr1[0] + arr1[1] * arr1[1]);
        });
        
        for(int[] point : points){
            maxHeap.add(point);
            
            if(maxHeap.size() > K)
                maxHeap.poll();
        }
        
        int[][] output = new int[K][2];
        
        while(K-- > 0){
            output[K] = maxHeap.poll();
        }
        return output;
    }
}





public class KClosestPointsToOrigin {
   public static void main(String[] args) {
       KClosestPointsToOrigin kClosestPointsToOrigin = new KClosestPointsToOrigin();
       int[][] output = kClosestPointsToOrigin.kClosest(new int[][]{{1, 3}, {-2, 2}}, 1);
       for (int i = 0; i < output.length; i++) {
           System.out.println(output[i][0] + "," + output[i][1]);
       }
   }

   public int[][] kClosest(int[][] points, int K) {
       HashMap<Double, List<Integer>> map = new HashMap<>();
       PriorityQueue<Double> pq = new PriorityQueue<>();
       for (int i = 0; i < points.length; i++) {
           int[] p = points[i];
           Double distance = Math.sqrt((p[1] - 0) * (p[1] - 0) + (p[0] - 0) * (p[0] - 0));
           map.compute(distance, (k, v) -> v == null ? new ArrayList<>() : v).add(i); //compute and add the value to the list.
           pq.add(distance);
       }
       System.out.println(map);
       int[][] result = new int[K][2];
       int i = 0;
       while (!pq.isEmpty() && i < K) {
           List<Integer> pos = map.get(pq.poll());
           for (int j : pos) {
               result[i++] = points[j];
           }
       }
       return result;
   }
}




Find K Closest Elements:
Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]

Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]

/*There are two approaches to it: first is to use two pointers, lo and hi and then find the difference at each stage from lo and hi...increment lo or decrement hi based on the conditions. It’s like moving the pointers based on */
Approach 1:
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int lo = 0;
        int hi = arr.length - 1;
        while(hi - lo >= k){//this is the number of elements which should be within range of K.
            if(Math.abs(arr[lo] - x) > Math.abs(arr[hi] - x)){//difference of lo greater than hi
                lo++;
            }
            else{
                hi--;
            }
        }
        
        for(int i=lo; i<=hi; i++)
            list.add(arr[i]);
        return list;
    }
}


/*
Approach2: Intuitively, we can sort the elements in list arr by their absolute difference values to the target x. Then the sublist of the first k elements is the result after sorting the elements by the natural order.

        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(list, (a, b) -> Math.abs(a - x) != Math.abs(b - x) ? Math.abs(a - x) - Math.abs(b - x) : a - b);
        list = list.subList(0, k);
        Collections.sort(list);
        return list;
*/



/*Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
We have Node Queue and Level List to maintain list at each level. And then finally stored into the results. 
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7


return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        
        LinkedList<Integer> levelList = new LinkedList<Integer>();
        LinkedList<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        
        boolean isOrderLeft = true;
        
        nodeQueue.addLast(root);
        nodeQueue.addLast(null);
        
        while(nodeQueue.size() > 0){
            TreeNode curr = nodeQueue.pollFirst();
            if(curr != null){
                if(isOrderLeft)
                    levelList.addLast(curr.val); //we do here “add last” since the left element will at last. Important step
                else 
                    levelList.addFirst(curr.val);////we do here “add first” since right element should be before left to happen to be zigzag
                
                if(curr.left != null)
                    nodeQueue.addLast(curr.left);
                if(curr.right != null)
                    nodeQueue.addLast(curr.right);
            }
            else{d
                results.add(levelList);
                levelList = new LinkedList<Integer>();
                
                if(nodeQueue.size() > 0)
                    nodeQueue.addLast(null);//this states separation after every level.
                isOrderLeft = !isOrderLeft;
            }
        }
        return results;
    }
}





/*
Problem Description:

Given an (unsorted) integer array and one integer, please return whether you can find 3 integers in this array of which their sum equals to the given integer.

For example, given
Array = (6, 4, -1, 8) and sum = 11
Return true, since 4 + 8 + (-1) = 11

Given
Array = (6, 4, -1, 8) and sum = 7
Return false, since we cannot find 3 integers that sum up to 7

 */https://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
See the comment section as well for the same.
From the comment section -: The condition curr_sum - A[j] != (int)s.toArray()[s.size() - 1] is wrong in solution 2.
Its is chekcing that the last added element in HashSet should not be the third element.
We dont need this check as that can happen. Try to find out sum 50 in arr 1, 4, 45, 6, 10, 8 or try to find out sum for any three consecutive elements. We wont get any output in this case.


// Java program to find a triplet using Hashing
import java.util.*;
  
class GFG {
  
    // returns true if there is triplet
    // with sum equal to 'sum' present
    // in A[]. Also, prints the triplet
    static boolean find3Numbers(int A[],
                                int arr_size, int sum)
    {
        // Fix the first element as A[i]
        for (int i = 0; i < arr_size - 2; i++) {
  
            // Find pair in subarray A[i+1..n-1]
            // with sum equal to sum - A[i]
            HashSet<Integer> s = new HashSet<Integer>();
            int curr_sum = sum - A[i];
            for (int j = i + 1; j < arr_size; j++) {
                if (s.contains(curr_sum - A[j])){
                    System.out.printf("Triplet is %d, %d, %d", A[i],A[j], curr_sum - A[j]);
                    return true;
                }
                s.add(A[j]);
            }
        }
  
        // If we reach here, then no triplet was found
        return false;
    }
  


Roman to Integer - http://www.goodtecher.com/leetcode-13-roman-to-integer/


class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);
        
        int length = s.length();
        int result = romanValues.get(s.charAt(length - 1));
        
        for (int i = length - 2; i >= 0; i--) {//Imp- “i = len - 2”
            if (romanValues.get(s.charAt(i)) >= romanValues.get(s.charAt(i + 1))) {
                result += romanValues.get(s.charAt(i));
            } else {
                result -= romanValues.get(s.charAt(i));    
            }            
        }
        
        return result;
    }
}





 Integer to Roman

class Solution {
    public String intToRoman(int num) {
        String m[] = {"", "M", "MM", "MMM"}; 
        String c[] = {"", "C", "CC", "CCC", "CD", "D",  
                            "DC", "DCC", "DCCC", "CM"}; 
        String x[] = {"", "X", "XX", "XXX", "XL", "L",  
                            "LX", "LXX", "LXXX", "XC"}; 
        String i[] = {"", "I", "II", "III", "IV", "V",  
                            "VI", "VII", "VIII", "IX"}; 
              
        // Converting to roman 
        String thousands = m[num/1000]; 
        String hundreds = c[(num%1000)/100]; 
        String tens = x[(num%100)/10]; 
        String ones = i[num%10]; 
              
        String ans = thousands + hundreds + tens + ones; 
              
        return ans;
    }
}




Search an element in a sorted and rotated array
Search in Rotated Sorted Array

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
/* In this case we should find first if the mid > start if it true then we know for sure that everything left of mid is not rotated, while everything on right of mid is rotated. Now check if the target is between start and mid, adjust start and end accordingly. */

class Solution {
  public int search(int[] nums, int target) {
    int start = 0, end = nums.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) return mid;
      else if (nums[mid] >= nums[start]) 
      {
            if (target >= nums[start] && target < nums[mid]) end = mid - 1;//cmpr targt - strt, mid
        	else start = mid + 1;
      }
      else 
     {
        	if (target <= nums[end] && target > nums[mid]) start = mid + 1;
        	else end = mid - 1;
      }
    }
    return -1;
  }
}



//Find First and Last Position of Element in Sorted Array
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
Your algorithm's runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1].
Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
// Brute force would be to use the left and right pointer, until we find that element. 
As soon as we target and search, to do it O(Log n) we will use the binary search in this case. Find the starting and ending index calling the binary search method for both of them separately.

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findStartingIndex(nums, target);
        result[1] = findEndingIndex(nums, target);
        return result;
    }
    
    public int findStartingIndex(int[] nums, int target){
        int start = 0, end = nums.length - 1;
        int index = -1;//This is the index value we have find for the target
        
        while(start <= end){ // This is the condition 
            int mid = start + (end - start)/2;
            if(nums[mid] >= target){ //since we are looking for the start index we first look for the first part of the mid.
                end = mid - 1; //this are the same conditions for Binary search in general for starting it. Look into the just above example of a rotated array.
            }
            else{
                start = mid + 1;
            }
            if(nums[mid] == target) index = mid; //change the Index value here
        }
        return index;
    }
    
    public int findEndingIndex(int[] nums, int target){
        int start = 0, end = nums.length - 1;
        int index = -1;//This is the index value we have find for the target
        
        while(start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] <= target){//since we are looking for the end index we will look for the second part of the mid.
                start = mid + 1;
            }
            else{
                end = mid  - 1;
            }
            if(nums[mid] == target) index = mid; //change the Index value here
        }
        return index;
    }
}





Merge Interval. Leet code
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
https://www.youtube.com/watch?v=qKczfGUrFY4

/*Given a collection of intervals, merge all overlapping intervals.
The idea is to sort the list and then check if the end of the first interval is less then or equal to the beginning of the next interval, if it is then we can merge if not then we just add it to the output array*. After sorting it returns as like this [[1,3],[2,6],[8,10],[15,18]] so sorting all the first element in the 2D list as shown above/

class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1)
            return intervals;
        
        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0])); //This is to compare for the arrays.

        
        List<int[]> output_array = new ArrayList();
        int[] current_interval = intervals[0];
        
        output_array.add(current_interval);
        
        for(int[] interval : intervals){
            int current_begin = current_interval[0];
            int current_end = current_interval[1];
            int next_begin = interval[0];
            int next_end = interval[1];
            
            if(current_end >= next_begin){
                current_interval[1] = Math.max(current_end, next_end);//updte current_interval
            }
            else{
                current_interval = interval; //we cannot merge since no overlap hence making curr as interval only and adding it to the output array in the next step.
                output_array.add(current_interval);
            }
        }
        return output_array.toArray( new int[ output_array.size() ] [ ] ); //In this way we can convert output_array to Array and in there we can mention the size of the array as well.
    }
}





Number of Islands
Example 1:
Input:
11110
11010
11000
00000

Output: 1

Example 2:
Input:
11000
11000
00100
00011

Output: 3

/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
In this we are using DFS so as soon as we find 1 it triggers all the 1’s and mark that as visited while and go around the entire array*/


class Solution {
    
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        
        if(r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0'){
            return;
        }
        grid[r][c] = '0'; //marking this as visited. 
        dfs(grid, r-1, c);
        dfs(grid, r+1, c);
        dfs(grid, r, c-1);
        dfs(grid, r, c+1);
        
    }
    
    public int numIslands(char[][] grid) {
        
        if(grid == null || grid.length == 0)
            return 0;
        
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        
        for(int r = 0; r < nr; r++)
        {
           for(int c = 0; c < nc; c++){
               if(grid[r][c] == '1'){
                   ++num_islands;
                   dfs(grid, r, c);
               }
           } 
        }
        return num_islands;
    }
}


695. Max Area of Island
Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]

Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.
/*It is the same as the number of islands but for every island we find the area and only consider the maximum area using the DFS approach.*/
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int nr = grid.length;
        int nc = grid[0].length;
        int max_area = 0;
        for(int r=0; r<nr; r++){
            for(int c=0; c<nc; c++){
                if(grid[r][c] == 1)
                    max_area = Math.max(max_area, maxAreaDFS(grid, r, c));//only max
            }
        }
        return max_area;
    }
    
    public int maxAreaDFS(int[][] grid, int r, int c){
        int nr = grid.length;
        int nc = grid[0].length;
        if(r >= 0 && c >= 0 && r < nr && c < nc && grid[r][c] == 1){//all inside if condition
            grid[r][c] = 0; //visited
        
            return 1 + maxAreaDFS(grid, r+1, c) + maxAreaDFS(grid, r-1, c) + maxAreaDFS(grid, r, c+1) + maxAreaDFS(grid, r, c-1);//finding all the area for connected one’s and adding 1 to it.
        }
        return 0;
    }
}



547. Friend Circle

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.The 2nd student himself is in a friend circle. So return 2.

Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
/*We will use DFS for this and traverse the matrix, maintain the visited array to see if the friends have already visited or not. When we encounter visited “0” we search for friends i.e do dfs*/

class Solution {
    public void dfs (int[][] M, int[] visited, int i){
        for(int j=0; j<M.length; j++){
            if(M[i][j] == 1 && visited[j] == 0){ //this goes thru column & rows, looking @visit
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
    public int findCircleNum(int[][] M) {
        int count = 0;
        int[] visited = new int[M.length];
        for(int i=0; i<M.length; i++){
            if(visited[i] == 0){
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }
}






26. Remove Duplicates from Sorted Array
Given a sorted array nums, remove the duplicates in-place such that each element appears only once and return the new length.

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the returned length.

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.

/*If both the numbers are different then put that in the ith location otherwise we need to increment.*/

class Solution {
    public int removeDuplicates(int[] nums) {
        int i=0;
        for(int j=0; j<nums.length; j++){
            if(nums[i] != nums[j]){ //both are different then put that in the ith location.
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}



675. Cut Off Trees for Golf Event

You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
In one step you can walk in any of the four directions top, bottom, left and right also when standing in a point which is a tree you can decide whether or not to cut off the tree.
You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).
You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.
You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

Points to note for above -  always cut off the tree with lowest height first (priority queue)  and  you should output the minimum steps you need to walk to cut off all the trees. (BFS for traverse and get min)

/*This is the same concept as the number of islands or the above 2d matrix problem. Here in this case we have to do BFS for traversing the 2d matrix and use priorityQueue to store the tree’s with the min hght. 
Steps 1. Store all the tree with min hght into the priority queue with value greater then 1
Step 2. Call the BFS and check for the min step required. 
We use here Start[] to locate if the tree/value has been visited or not.*/

class Solution {
    static int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1, 0}};
    public int cutOffTree(List<List<Integer>> forest) {
        if(forest == null || forest.size() == 0)
            return 0;
        int m = forest.size(), n = forest.get(0).size();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if (forest.get(i).get(j) > 1){ //only getting the values greater than 1 in the priority queue.
                    pq.add(new int[] {i, j, forest.get(i).get(j)});
                }
            }
        }
        int[] start = new int[2];// this is to store for to know we have visited the node or not in the minstep.
        int sum = 0;
        
        while(!pq.isEmpty()){
            int[] tree = pq.poll();
            int step = minStep(forest, tree, start, m, n);
            
            if(step < 0) return -1;
            sum += step;
            
            start[0] = tree[0];
            start[1] = tree[1];
        }
        return sum;
    }
    
    public int minStep(List<List<Integer>> forest, int[] tree, int[] start, int m, int n){
        int step = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];//t
        q.add(start);
        visited[start[0]][start[1]] = true;//here is we wud req the start array declared above.
//normal BFS get the size and iterate to the queue as shown below.
        while(!q.isEmpty()){
            int size = q.size();//travese for each of the level.
            for(int i=0; i<size; i++){
                int[] curr = q.poll();
//below step is to get the minimum(lowest height) from the priority queue and if it matches then we return the steps.
                if(curr[0] == tree[0] && curr[1] == tree[1]) return step;
                
                for(int[] d : dir){
                    int nr = curr[0] + d[0];//get each of the row value
                    int nc = curr[1] + d[1];//get each of the col value
                    if(nr < 0 || nc < 0 || nr >= m || nc >= n || 
                       forest.get(nr).get(nc) == 0 || visited[nr][nc]) continue;
                    q.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
            step++;
        }
        return -1;
    }
}



79. Word Search
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.


/*The logic here is same using DFS same as Island. In this case we keep visited since we have to get the character only once in our word. We use if condition to traverse through left, right, above & below the given character
Time Complexity = time is O(M * N * 4^L) where M*N is the size of the board and we have 4^L for each cell because of the recursion. Space Complexity = O(L), where L is the length of the word to be searched.*/

class Solution {
    boolean[][] visited;
    public boolean dfsWordSearch(char[][] board, int r, int c, String word, int index){
        if(index == word.length())//index eventually becomes the word length
            return true;
        int nr = board.length;
        int nc = board[0].length;
        if(r< 0 || c < 0 || r >= nr || c >= nc || board[r][c] != word.charAt(index) || visited[r][c])
            return false;
        visited[r][c] = true;//since char cannot be repeated.
        //below if condition for any of the word is found we return true and hunt for another word now - dfs.
        if(dfsWordSearch(board, r+1, c, word, index+1) ||
        dfsWordSearch(board, r-1, c, word, index+1) ||
        dfsWordSearch(board, r, c+1, word, index+1) ||
        dfsWordSearch(board, r, c-1, word, index+1)){
            return true;
        }
        visited[r][c] = false;//incorrect char hence setting that char as false and return false.
        return false;
    }
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        int nr = board.length;
        int nc = board[0].length;
        for(int r=0; r<nr; r++){
            for(int c=0; c<nc; c++){
                if(board[r][c] == word.charAt(0) && dfsWordSearch(board, r, c, word, 0)){
                    return true;//found the  word match hence return true.
                }
            }
        }
        return false;//word not found hence return false.
    }
}





Sliding Window. (Minimum Window Substring)
-- MUCH BETTER LINK - https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems/25816
TEMPLATE FOR SLIDING WINDOW PROBLEMS - IN COMMENTS AT START FROM ABOVE LINK.
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n). Sliding Window.
Example:
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"


This approach is way better.
/*Here we take count of each target string character in the map. Declare standard variables start, end, minLen and counter. Now go in loop and check if the char is present in the map, if yes increment end++ , reduce the counter and count of that char. Lets say if counter == 0 that means string valid is found in the source side. Now we will think about moving the start pointer here. Find the minLen and update it. Now finally when start is moved take the new character from start and check things all over again. This is basically sliding the window to get the min.*/
class Solution {
    public String minWindow(String s, String t) {
        int[] map = new int[128];
     // counter represents the number of chars of t to be found in s.
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter= t.length();
        for(char c :t.toCharArray()){ //standard to update the map with target string. Count char
            map[c]++;
        }
        
        while(end < s.length()){
            char c1 = s.charAt(end);
            if(map[c1] > 0)counter--;//counter-- since we want min window.
            map[c1]--;
            end++;
            
            while(counter == 0){//This means we have found the valid string.
                  if(minLen > end - start)
                  {
                    minLen = end - start;
                    minStart = start;
                     }
                char c2 = s.charAt(start); //Here we now take the next character new one from the new position of start.
                map[c2]++; //Increment that value of char in map.
                if(map[c2] > 0)counter++; //In this we will increment everything here
                start++;//moving start to ahead.
            }//end (while count == 0)
        }//end while(end < s.length())
        return minLen == Integer.MAX_VALUE ? ""  : s.substring(minStart, minStart + minLen);
    }
}


1297. Maximum Number of Occurrences of a Substring
/*Same template and logic as sliding window problem, we have a ch array to count the frequency of the String, we will have an inner while loop to check for maxLetters and also if it is greater then the minSize.*/
class Solution {
    public static int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int l=0, r=0, res=0, letter=0;
        int[] ch = new int[128];
        HashMap<String, Integer> map = new HashMap<>();


        while(r < s.length()){
            if(ch[s.charAt(r++)]++ == 0) letter++;
            while(letter > maxLetters || (r-l) > minSize){
                if(ch[s.charAt(l++)]-- == 1) letter--;
            }
            if((r-l) == minSize){
                String sb = s.substring(l, r);
                map.put(sb, map.getOrDefault(sb, 0) + 1);
                res = Math.max(res, map.get(sb));
            }
        }
        return res;
    }
}



Trapping Rain Water
https://www.geeksforgeeks.org/trapping-rain-water/
/*The brute force would be to get the min height of the bar from left and right, Take the smaller of two heights, subtract each of the current elements adding it to the results - O(N^2). The optimal way would 
Instead of maintaining two arrays of size n for storing left and right max of each element, we will just maintain two variables to store the maximum till that point. Since water trapped at any element = min( max_left, max_right) – arr[i] we will calculate water trapped on smaller elements out of A[lo] and A[hi] first and move the pointers till lo doesn’t cross hi.
*/
class Solution {
    public int trap(int[] height) {
        int res = 0;
        int n = height.length;
        int left_max = 0, right_max = 0;
        int lo = 0, hi = n - 1;
        
        while(lo <= hi){
            if(height[lo] < height[hi]){
                if(height[lo] > left_max)
                    left_max = height[lo];
                else 
                    res += left_max - height[lo]; //Adding min height to the results.
                lo++;
            }
            else{
                if(height[hi] > right_max)
                    right_max = height[hi];
                else
                    res += right_max - height[hi];//Adding min height to the results.
                hi--;
            }
        }
        return res;
    }
}




Longest Substring Without Repeating Characters
In the case of a sliding window problem, we have two pointers starting from left, as we increment the right pointer ahead and as soon as we find duplicates we then remove a duplicate character from the set and then increment the left pointer.

Example 1:
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a
/*We will use a sliding window approach here. In this we will keep on adding the new char to a set and keep incrementing the right pointer “ j ” in this case. When we find the existing character we then contract our window size and remove the character from the set.*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0, i = 0, j = 0;
        HashSet<Character> set = new HashSet<Character>();
        int n = s.length();
        
        while(i < n && j < n){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++)); // Increase the size of window 
                ans = Math.max(ans, j - i); //This gives the length of the string.
            }
            else{
                set.remove(s.charAt(i++)); //Moving the left pointer to right when char is already present. Moving the window.
            }
        }
        return ans;
    }
}

Longest Substring with At Most K Distinct Characters
Given a string, find the length of the longest substring T that contains at most k distinct characters.
Example 1:
Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:
Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.

/*In this we use the sliding window  concept. We use the count array to get the frequency of the character in the string. Now we start with j and i is behind j. We move i when num is greater than K so here we slide our window and move i*/
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int i=0, num=0, res=0; //i is behind j
        int[] count = new int[256];
        for(int j=0; j<s.length(); j++){
            if(count[s.charAt(j)]++ == 0) //making count of the characters.
                num++;
            while(num > k && i < s.length()){ //sliding window here.
                count[s.charAt(i)]--;
                if(count[s.charAt(i)] == 0)
                    num--;
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}


424. Longest Repeating Character Replacement
Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
Example 1:
Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.

 
Example 2:
Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.





/*This is the same as the sliding window problem.
https://www.youtube.com/watch?v=00FmUN1pkGE */
class Solution {
    public int characterReplacement(String s, int k) {
        char[] char_count = new char[26];
        int window_start=0, max_len=0, max_count = 0;
        int N = s.length();
        for(int window_end=0; window_end<N; window_end++){
            char_count[s.charAt(window_end) - 'A']++;
            int current_char_count = char_count[s.charAt(window_end) - 'A'];//getting the count of each char.
            max_count = Math.max(max_count, current_char_count);//max count for each character
            
            while(window_end - window_start - max_count + 1 > k){
                char_count[s.charAt(window_start) - 'A']--;//changing the window, start++.
                window_start++;
            }
            max_len = Math.max(max_len, window_end-window_start + 1);
        }
        return max_len;
    }
}



/*Longest Common Prefix
Input: ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
In this we check if the prefix is present in the string in the while condition to check using indexOf. Then we get -1 if the prefix DOES NOT match and hence we reduce the size of the prefix.*/

class Solution {
     public String longestCommonPrefix(String[] strs) {
    if(strs.length == 0)
        return "";
    String prefix = strs[0];
    for(int i = 1; i < strs.length; i++)
        while(strs[i].indexOf(prefix) != 0){ //check if the prefix is in the string or not.
            prefix = prefix.substring(0, prefix.length() - 1); //reduce the size of the prefix to find the match.
            System.out.println(" " + prefix + " " +strs[i]);
        if(prefix.isEmpty()) return "";  //if it’s empty then return no string.
    }
         return prefix;
     }
}



/*Binary Tree Maximum Path Sum
Example 1:
Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:
Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
Best link to explain it - https://www.youtube.com/watch?v=mOdetMWwtoI
We traverse left recursively and then traverse right side, this gives us left and right values. Now we keep the max value every time by doing Math.max. Finally we return max of the left or the right side plus the node.val (we do this since when we go one step above we take either left side or the right side) 
*/

class Solution {
    int max;
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        findMaxPath(root);
        return max;
    }
    
    public int findMaxPath(TreeNode node){
        if(node == null)
            return 0;
        int left = Math.max(0, findMaxPath(node.left)); //go left entire, give pathsum left
        int right = Math.max(0, findMaxPath(node.right)); //go right entire,give pathsum right
        
        max = Math.max(max, left + right + node.val);
        
        return Math.max(left, right) + node.val;  //we do this since when we go one step above we take either left side or the right side
    }
}

Flatten Binary Tree to Linked List
Solution
Given a binary tree, flatten it to a linked list in-place.
Link - https://www.youtube.com/watch?v=vssbwPkarPQ&t=213s 
For example, given the following tree:
   1
   / \
  2   5
 / \   \
3   4   6


The flattened tree should look like:


1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
/*In this we want to make sure we move the left side nodes and put them to the right nodes of the tree. Finally make the right node as the peek of the stack and left node as null afterwards. Since we need to move the left node to right first put the right node on the stack and then left node so that they are first removed.*/

class Solution {
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            
            if(curr.right != null) stack.push(curr.right);
            
            if(curr.left != null) stack.push(curr.left);
            
            if(!stack.isEmpty()){
                curr.right = stack.peek();
            }
            
            curr.left = null;
        }
    }
}


//Product of Array Except Self
Very important link - https://www.youtube.com/watch?v=tSRFtR3pv74 
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.Note: Please solve it without division and in O(n).


//The idea is to get the left side product of every element and then the right side product of every element. After getting this then we multiply each of the left and right product array to get the solution. (Brute force would be to get the total multiplication and then divide then value by each of the number storing in the output array)

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        output[0] = 1;
        int n = nums.length;
        for(int i=1 ; i<n; i++){
            output[i] = nums[i-1] * output[i-1];  //this gives the left side product
        }
//Now the output array has all left side products.
        int R = 1;
        for(int i=n-1; i>=0; i--){
            output[i] = output[i] * R; //this gives the output.
            R = R * nums[i]; //this gives the right side product.
        }
        return output;
    }
}



findLongestSubarrayBySum
Solution - sliding window
https://www.youtube.com/watch?v=XFPHg5KjHoo&t=51s




public int[] findLongestSumArray(int[] arr, int s){
    int[] result = new int[]{-1};
    
    int sum = 0;
    int left = 0;
    int right = 0;
    
    while(right < arr.length){
        sum += arr[right];
        while(left < right && sum > s){
            sum -= arr[left++]; //reducing sum and moving left pointer.
        }
        
        if(sum == s && (result[1] - result[0] < right - left || result.length == 1){
            result = new int[]{left + 1, right + 1};
        }
           right++;
    }
           return result;
}




Letter Combinations of a Phone Number
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Link - https://www.youtube.com/watch?v=imD5XeNaJXA 

/*Loop through the input numbers, get the corresponding index from the String array. Now we check peek of output array so the length are the same. Grab/remove from output everytime and them permutate thru the other char, place it on the output then again. */

class Solution {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> output = new LinkedList();
        if(digits.length() == 0) return output;
        String[] map = new String[]{"0" , "1", "abc", "def", "ghi", "jkl", "mno" ,"pqrs", "tuv", "wxyz"};
        output.add("");
        for(int i=0; i<digits.length(); i++){
            int index = Character.getNumericValue(digits.charAt(i));
            while(output.peek().length() == i){
                String permutation = output.remove();  // Take one character
                for(char c: map[index].toCharArray()){
                    output.add(permutation + c); //Combination done here in the for loop.
                }
            }
        }
        return output;
    }
}






Add Binary
Solution
Given two binary strings, return their sum (also a binary string).
The input strings are both non-empty and contain only characters 1 or 0.
Example 1:
Input: a = "11", b = "1"
Output: "100"
Example 2:
Input: a = "1010", b = "1011"
Output: "10101"
/*Actually this works the same as English wordings literally. To add binary we do 
Xor operation with 1 and 0 ….and for carry we move carry to left. Here we have BigInteger because we do xor( ), and ( ) and to String( ) operation using BigInteger and also since it can get easily bigger that might not fit in the  primitive data type.*/
import java.math.BigInteger;

class Solution {
    public String addBinary(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        BigInteger answer, carry;
//We are taking y as carry so as long as carry is not zero we will continue the loop
        while(y.compareTo(zero) != 0){
            answer = x.xor(y);
            carry = x.and(y).shiftLeft(1);
            x = answer;
            y = carry;
        }
        return x.toString(2);
    }
}

22. Generate Parentheses
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
Link - https://www.youtube.com/watch?v=qBbZ3tS0McI 

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> output = new ArrayList();
        backTrack(output, "", 0, 0, n);
        return output;
    }
    public void backTrack(List<String> output, String curr, int open, int close, int max){
        if(curr.length() == max * 2){ //this is base condition wherein we check the total len as  in example above 3 * 2 =6 is max len.
            output.add(curr);
	return;
        }
        
        if(open < max) backTrack(output, curr + '(', open + 1, close, max); //adding “('' to the curr string since open is less then max and we need open to be added to curr. Also increment the value of open by 1.
        if(close < open) backTrack(output, curr + ')', open, close + 1, max); //same for close.
    }
}



301. Remove Invalid Parentheses

Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
Note: The input string may contain letters other than the parentheses ( and ).
Example 1:
Input: "()())()"
Output: ["()()()", "(())()"]

Example 2:
Input: "(a)())()"
Output: ["(a)()()", "(a())()"]

Example 3:
Input: ")("
Output: [""]
Key Points:
Generate unique answer once and only once, do not rely on Set.
Do not need preprocess.
Runtime 3 ms.
Explanation:
We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.
To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix. However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.
After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string. However, we need to keep another information: the last removal position. If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
For this, we keep tracking the last removal position and only remove ‘)’ after that.
Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
The answer is: do the same from right to left.
However a cleverer idea is: reverse the string and reuse the code!
Here is the final implement in Java.



class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> output = new ArrayList<>();
        removeHelper(s, output, 0, 0, '(', ')');
        return output;
    }

    public void removeHelper(String s, List<String> output, int iStart, int jStart, char openParen, char closedParen) {
        int numOpenParen = 0, numClosedParen = 0;
        for (int i = iStart; i < s.length(); i++) {
            if (s.charAt(i) == openParen) numOpenParen++;
            if (s.charAt(i) == closedParen) numClosedParen++;
            if (numClosedParen > numOpenParen) { // We have an extra closed paren we need to remove
                for (int j = jStart; j <= i; j++) // Try removing one at each position, skipping duplicates
                    
if (s.charAt(j) == closedParen && (j == jStart || s.charAt(j - 1) != closedParen))
                    // Recursion: iStart = i since we now have valid # closed parenthesis thru i. jStart = j prevents duplicates
                        
removeHelper(s.substring(0, j) + s.substring(j + 1, s.length()), output, i, j, openParen, closedParen);
                return; // Stop here. The recursive calls handle the rest of the string.
            }
        }
        // No invalid closed parenthesis detected. Now check opposite direction, or reverse back to original direction.
        String reversed = new StringBuilder(s).reverse().toString();
        if (openParen == '(')
            removeHelper(reversed, output, 0, 0, ')','(');
        else
            output.add(reversed);
    }
}



Longest Palindromic Substring

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
Example 1:
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:
Input: "cbbd"
Output: "bb"
//In this we take from the middle element and then expand to the right or left. To get the longest substring palindrome. WARNING  - This is different from the longest Subsequence problem.
Link - https://www.youtube.com/watch?v=y2BD4MJqV20&t=2s 

class Solution {
    public String longestPalindrome(String s) {
        if(s.length() < 1 || s == null) return "";
        
        int start = 0;
        int end = 0;
        
        for(int i=0; i<s.length(); i++){//loop thru
        int len1 = longestHelper(s, i, i); //eg. is "racecar" 
        int len2 = longestHelper(s, i, i+1); //eg. is "abbbba"
        int len = Math.max(len1, len2); //get the max length from len1 and len2 as required.
        if(len > end - start){ //this step is to go to the left or right and get the max len everytime. eg. "racecar" or "abbbba"
                start = i - ((len - 1)/2);
                end = i + (len/2);
            }
        }
        return s.substring(start, end + 1);
    }
    
    public int longestHelper(String s, int left, int right){
        if(s == null || left > right) return 0;
        
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        
        return right - left - 1;
    }
}


Decode Ways
Solution
A message containing letters from A-Z is being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26


Given a non-empty string containing only digits, determine the total number of ways to decode it.
Example 1:
Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).



/* we will use the recursive approach to check if the numbers converted are within the given list. We keep a map to check if we have that integer already in the map or not. Also after converting it to int for two integers we check if it's within under 26 or not and then do the recursive call. See the comments below against the code.*/
Link https://www.youtube.com/watch?v=qli-JCrSwuk 
class Solution {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    
    public int numDecodingsHelper(int index, String s) {
        
        if(s.length() == index)
            return 1;
        if(s.charAt(index) == '0')
            return 0;
        if(s.length()-1 == index)
            return 1;
        
        if(map.containsKey(index))
            return map.get(index);
        
        
        int ans = numDecodingsHelper(index+1, s); //for eg: in “123456” it checks only for “1” hence index+1
        if(Integer.parseInt(s.substring(index, index+2)) <= 26){ //where as here it checks for index+2 which is “12” and is within “26” refers to “L”
            ans += numDecodingsHelper(index+2, s);
        }
        map.put(index, ans); //store in map.
        return ans;
    }
    
    public int numDecodings(String s){
        if(s.length() == 0 || s == null)
            return 0;
        
        return numDecodingsHelper(0, s);
    }
    
}







Binary Tree Paths

Given a binary tree, return all root-to-leaf paths.
Note: A leaf is a node with no children.
Example:
Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<String> binaryTreePathsHelper(TreeNode root, String path, List<String> paths) {
        if(root != null){
        path += Integer.toString(root.val);
        if(root.left == null && root.right == null)
            paths.add(path);
        else{
            path += "->";
            binaryTreePathsHelper(root.left, path, paths);
            binaryTreePathsHelper(root.right, path, paths);
            }
        }
        return paths;
    }
    
    public List<String> binaryTreePaths(TreeNode root){
        List<String> paths = new LinkedList();
        binaryTreePathsHelper(root, "", paths);
        return paths;
    }
}


Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Example:
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
/*In this we do addition of two numbers we can take carry by doing “%” whereas put numbers in the list using “/” . This is done using the traditional approach of adding two numbers how we do from left to right.  */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        int carry=0;
        ListNode p = l1, q = l2, curr = dummyNode;//Sentinel node
        
        while(p != null || q != null){
            int a = (p != null) ? p.val:0;
            int b = (q != null) ? q.val:0;
            int sum = a + b + carry;
            carry = (sum / 10); //This is for carry to add to the next number on left.
            curr.next = new ListNode(sum % 10); //This puts the actual number
            curr = curr.next;
            if(p != null) p = p.next;
            if(q != null) q = q.next;
        }
        if(carry > 0){ //Remaining number at the last.
            curr.next = new ListNode(carry);
        }
        return dummyNode.next;
    }
}



445. Add Two Numbers II

/*Similar as above but the list is not given in reverse order so we have to reverse it first and then solve it using the same approach. Also while returning we have to reverse it .*/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null)
            return new ListNode(0);
        ListNode dummyNode = new ListNode(-1);
        ListNode p = reverseLinkedList(l1);
        ListNode q = reverseLinkedList(l2);
        ListNode curr = dummyNode;
        int sum = 0, carry = 0;
        
        while(p != null || q!= null){
        int a = (p != null) ? p.val : 0;
        int b = (q != null) ? q.val : 0;
        sum = a + b + carry;
        carry = sum/10;
        curr.next = new ListNode(sum%10);
        curr = curr.next;
        if(p != null) p = p.next;
        if(q != null) q = q.next;
        }
        if(carry > 0){
            curr.next = new ListNode(carry);
        }
        return reverseLinkedList(dummyNode.next);//reverse it while returning it
    }
    
    public ListNode reverseLinkedList(ListNode head){
        ListNode prev = null, next, curr = head;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}



Clone Graph

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/


/*First we create the copy of the current node as the cloneCurr and then loop thru the node to add neighbors of that node to the cloneCurr. We use hashMap to check if we have already visited the node or not.*/
class Solution {
//Global hashmap declared below.
    HashMap<Node, Node> visited = new HashMap<Node, Node>();
    public Node cloneGraph(Node node) {
        if(node == null)
            return node;
        
 // If the node was already visited before.
        // Return the clone from the visited dictionary.

        if(visited.containsKey(node)){
            return visited.get(node);
        }
        
 // Create a clone for the given node.
        // Note that we don't have cloned neighbors as of now, hence [].

        Node cloneCurr = new Node(node.val, new ArrayList());
         // The key is the original node and value being the clone node.
        visited.put(node, cloneCurr);
        
 // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.

        for(Node neighbor : node.neighbors){
            cloneCurr.neighbors.add(cloneGraph(neighbor));
        }
        
        return cloneCurr;
    }
}


Verifying an Alien Dictionary
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:
Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

//https://www.youtube.com/watch?v=qSbJZWENtX4 
// In this we are comparing strings and we want to do it in lexical order. This means we have to use a comparator. Now we first map all the characters in the “order” array. We then pass it to the compare method. We maintain a compare_val to test if the values are less then or equal to zero. If it's Zero it's sorted and if greater than zero it's not sorted, since the first word is greater then next one.

class Solution {
    int[] map;
    public boolean isAlienSorted(String[] words, String order) {
        map = new int[26];
        for(int i=0; i<order.length(); i++){ //do the mapping of the char here.
            map[order.charAt(i) - 'a'] = i;
        }
        
        for(int i=1; i<words.length; i++){
            if(compare(words[i-1], words[i]) > 0){ //calling the compare here and check if the first word is greater than zero that means not sorted.
                return false;
            }
        }
        return true;
    }
    
    public int compare(String word1, String word2){
        int i=0, j=0;
        int compare_val = 0;
        
        while(i<word1.length() && j<word2.length() && compare_val == 0){ //compare and update the value.
             System.out.println(map[word1.charAt(i) - 'a']+" "+map[word2.charAt(j) - 'a']);
             compare_val = map[word1.charAt(i) - 'a'] - map[word2.charAt(j) - 'a'];
             i++;
             j++;
        }
        
        if(compare_val == 0){ //if it's zero, that is of the same size we return the length.
            //System.out.println(map[word1.charAt(i) - 'a']+" "+map[word2.charAt(j) - 'a']);
            return word1.length() - word2.length();
        }
        else return compare_val; //return the actual value.
    }
}



Combination Sum

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
The same repeated number may be chosen from candidates an unlimited number of times.
Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

Example 2:
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

https://www.youtube.com/watch?v=MTI2wc8s0BY 

https://leetcode.com/problems/combination-sum-ii/discuss/16878/Combination-Sum-I-II-and-III-Java-solution-(see-the-similarities-yourself) 
//This is backtrack recursion tree problem where we have the tree created

class Solution {
    List<List<Integer>> list = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(list, new ArrayList<>(), candidates, target, 0);
        return list;
    }
    
    public void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] candidates, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));//BASE condition
        else{     //important i=start since we start from next element always.
            for(int i=start; i<candidates.length; i++){
                tempList.add(candidates[i]);//adding element to temp.
    backtrack(list, tempList, candidates, remain - candidates[i], i);//pass remaining.
                tempList.remove(tempList.size() - 1);//remove the last item
            }
        }
    }
}


40. Combination Sum II

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]


/*Similar to combination 1 but in this only the candidates are allowed to be used once, hence we pass start as i+1(next one) and also check for duplicate in for loop.*/

class Solution {
    List<List<Integer>> list = new ArrayList();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); //sorting to be done first
        backtrack(list, new ArrayList<>(), candidates, target, 0); //backtracking
        return list;
    }
    
    public void backtrack(List<List<Integer>> list, ArrayList<Integer> temp, int[] candidates, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(temp));
        else {
            for(int i=start; i<candidates.length; i++){
                if(i > start && candidates[i] == candidates[i-1]) continue;/** skip duplicates */
                temp.add(candidates[i]);
                backtrack(list, temp, candidates, remain - candidates[i], i+1);//passing i+1
                temp.remove(temp.size() - 1);
            }
        }
    }
}



Top K Frequent Words
Solution
Given a non-empty list of words, return the k most frequent elements.
Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.


Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Count the frequency of each word, then add it to heap that stores the best k candidates. Here, "best" is defined with our custom ordering relation, which puts the worst candidates at the top of the heap. At the end, we pop off the heap up to k times and reverse the result so that the best candidates are first.
use priority queue to hold k elements at a time so that the queue always holds the top-k frequent items, we need to customize the comparator of the priority queue to make it a min heap and sort the words with same frequency by their alphabetical ordering
	-always make the size of the heap equal to K by popping the smallest frequency element from the top
	-pop the k items from the queue
	Complexity: O(n) to collect the count of words
	Space: O(n) for the hash, and O(k) for the heap


https://stackoverflow.com/questions/52510140/can-someone-explain-priorityqueue-in-this-example-to-me 
//In this we are using Priority Queue to store the words and hashMap to store the count of the words. And then we add to the heap (priority queue) which is implemented as heap. Finally we have to reverse the list and then return it.
Min heapify.

The below given priority queue can also be broken down into 
---------------------------------------------------------------------
        //worst candidates should be placed at the top of the min heap, to achieve results of max heap.
        PriorityQueue<String> minHeap = new PriorityQueue<>((str1, str2) -> {
            if(keywordCountMap.get(str1) == keywordCountMap.get(str2))
                return str2.compareTo(str1); //when count tied, send lexicographically smaller elements down the heap.
            
            return keywordCountMap.get(str1) - keywordCountMap.get(str2); //large count should be down the heap.
        }) ;
     ---------------------------------------------------------------------   



class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        //if(words.length == 0 || words == null) return;
        List<String> result = new ArrayList<String>();
        
        Map<String, Integer> map = new HashMap();
        
        for(String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        PriorityQueue<String> heap = new PriorityQueue<>(
            (w1, w2) -> map.get(w1).equals(map.get(w2)) 
            ? w2.compareTo(w1) : map.get(w1) - map.get(w2)); //If counts are equal then compare alphabetically, otherwise compare by counts. 
        
        for(String word: map.keySet()){
            heap.offer(word);
            if(heap.size() > k) heap.poll();
        }
        //or its better result.add(0, heap.poll() ) so need 
        while(!heap.isEmpty()) result.add(heap.poll()); 
        Collections.reverse(result);
        return result;
    }
}







Top K Frequent Elements
//Similar to the above one using heap (priority  queue)
Given a non-empty array of integers, return the k most frequent elements.
Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i],  0) + 1);
        }
        
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((a1, a2) -> map.get(a1) - map.get(a2)); //The less frequent element first
        
        for(int n : map.keySet()){
            heap.add(n);
            if(heap.size() > k)
                heap.poll();
        }
        List<Integer> result_list = new LinkedList<>();
        
        while(!heap.isEmpty()){
            result_list.add(heap.poll());
        }
        Collections.reverse(result_list);
        int j = 0;
        int[] result = new int[result_list.size()];
        for(int val : result_list)
            result[j++] = val;
        return result;
    }
}



Intersection of Two Arrays

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        HashSet<Integer> set2 = new HashSet<Integer>();
        
        for(int i=0; i<nums2.length; i++){
                set2.add(nums2[i]);
            }
        
           for(int i=0; i<nums1.length; i++){
                set1.add(nums1[i]);
            }
        
        set1.retainAll(set2);
        
        int[] result = new int[set1.size()];
        int c = 0;
        for(int x : set1) result[c++] = x;
        return result;
    }
}


 322. Coin Change
You are given coins of different denominations and a total amount of money. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
Example 1:
Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:
Input: coins = [2], amount = 3
Output: -1
//This can be solved using DP with a bottom up approach and in this case.    DP[ ] stores the total coins that equal that sum. Firstly we need to compare if the coins[ j ] <= i (in this case “ i ” is the actual coins given. Then we need to take the minimum of the current coins or the previous coins.



class Solution {
    public int coinChange(int[] coins, int amount) {
        int max = amount+1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0; //make sure you initialize with 0 at start.
            
        for(int i=1; i<=amount; i++){
            for(int j=0; j<coins.length; j++){
                if(coins[j] <= i){
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1); // we need to take the minimum of the current coin or previous denomination coins.
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount]; 
    }
}



Container With Most Water

//The brute force would be to have two loops to get the max_Area and in by max_area it means we have to get length * breath = area. This is similar to the trapping rain water but in this we get the max area directly. To remove 2 for loops we will use two pointers. We need to get the minimum height from left or right and then how many hops we need to make from that minimum to the next one and then multiply it.
So in this example of [1, 8, 2 balh blah.. 6, 5, 7] we get the min from right and left. Then we do L++ or R-- based on which is smaller. 


class Solution {
    public int maxArea(int[] height) {
        int max_area = 0;
        int l = 0, r = height.length - 1;
        while(l < r){
            if(height[l] < height[r]){
                max_area = Math.max(max_area, height[l] * (r-l));
                l++;
            }
            else{
                max_area = Math.max(max_area, height[r] * (r-l));
                r--;
            }
        }
        return max_area;
    }
}



46. Permutations
https://www.youtube.com/watch?v=KukNnoN-SoY&list=RDKukNnoN-SoY&start_radio=1&t=886 

Template - https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning) 
Given a collection of distinct integers, return all possible permutations.
Example:
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]


//Whenever you encounter permutations or combinations kind of problems. We will use backtracking for the same. In case of backtrack the template is pretty much the same. We will have the base condition and then recursively call the method again, with (output list, temp list, start + something, end values). In the above figure as shown we first go one level very deep and then its folds to the above one. “Yes, a List that you pass to a method is passed by reference. Any objects you add to the List inside the method will still be in the List after the method returns.”


Better Solution for Permutation :

class Solution {
    public void backtrack(List<List<Integer>> result, int[] num, List<Integer> tempList){
        if(tempList.size() == num.length){
        result.add(new ArrayList(tempList));
        }
        else{
        for(int i=0; i<num.length; i++){//since we have all hence i=0
            if(tempList.contains(num[i])) continue;//we want distinct so continue on duplicate
            tempList.add(num[i]);
            backtrack(result, num, tempList);
            tempList.remove(tempList.size() - 1);
            }
        }
    }
    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, nums, new ArrayList<>());
        return result;
    }
}
--------Other Solution-----
class Solution {
    public void backtrack(List<List<Integer>> result, List<Integer> num, int first, int n){
        if(n == 0)return ;
        if(first == n) result.add(new ArrayList<>(num));
        for(int i=first; i<n; i++){ //It's important to start with i=first since we start with the next number next time.
            Collections.swap(num, first, i);
            backtrack(result, num, first+1, n);
            Collections.swap(num, first, i);
        }
    }
    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> num = new ArrayList<Integer>();
        for(int n : nums){
            num.add(n);
        }
        int n = nums.length;
        backtrack(result, num, 0, n);
        return result;
    }
}








Permutations II (contains duplicates) : https://leetcode.com/problems/permutations-ii/
Given a collection of numbers that might contain duplicates, return all possible unique permutations.
Example:
Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]



public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
    if(tempList.size() == nums.length){
        list.add(new ArrayList<>(tempList));
    } else{
        for(int i = 0; i < nums.length; i++){
//make sure its used boolean and if prev and next is same in that case contd
            if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
            used[i] = true; 
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, used);
            used[i] = false; 
            tempList.remove(tempList.size() - 1);
        }
    }
}



78. Subsets

Given a set of distinct integers, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.
Example:
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]


class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        backTrack(result, new ArrayList<>(), nums, 0);
        return result;
    }
    //same as permutation but only for loop from i=start.
    private void backTrack(List<List<Integer>> result, List<Integer> tempList, int[] num, int start){
        result.add(new ArrayList(tempList));
        for(int i=start; i<num.length; i++){
            tempList.add(num[i]);//add one element to the list.
            backTrack(result, tempList, num, i + 1);//backtrack with the tempList, with new start value.
            tempList.remove(tempList.size() - 1);//reducing it from the tempList again.
        }
    }
}





Rotate Image

You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).
Note:
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
Example 1:
Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]

Example 2:
Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]

//In this we are going to use the transpose matrix technique to do the rotation. Here the important thing is setting "j=i" we need to move along the row. We have to now swap the first and last column in the second for loop.
https://www.youtube.com/watch?v=SA867FvqHrM 

class Solution {
    public void rotate(int[][] matrix) {
        int N = matrix.length;
        
        for(int i=0; i<N; i++){
            for(int j=i; j<N; j++){ // We set j = i because we want to move along with ROW
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        for(int i=0; i<N; i++){ //We need to go thru every ROW and Column only half of the length hence j=N/2. Same as the two pointer approach.
            for(int j=0; j<(N/2); j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][N-1-j];
                matrix[i][N-1-j] = temp;
            }
        }
      
    }
}








 Sort Colors
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
Note: You are not supposed to use the library's sort function for this problem.
Example:
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]


/*For this question remember:-
see below code as low and high are inclusive boundaries of number 1
and (0,low-1) is contained with 0
and (high+1,nums.length-1) is contained with 2
and (low,high) is contained with 1
In this case it's like lo should be always before i and hi should be after i. Hence when we find "0" we increment lo and i,....and when we find "2" we decrement hi and keep i as same. but during "1" we only increment i.*/
class Solution {
    public void sortColors(int[] nums) {
        int lo=0, hi = nums.length - 1, i = 0;
        while(i <= hi){
            if(nums[i] == 0) swap(nums, lo++, i++);
            else if(nums[i] == 2) swap(nums, i, hi--);
            else if(nums[i] == 1) i++;
        }
    }
    
    public void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}


class Solution {
    public void sortColors(int[] nums) {
        int zero=0, one = 0, two = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] == 0)
                zero++;
            else { if(nums[i] == 1){
                one++;
            }
                  else two++;
                 }
            
        }
        
        for(int i=0; i<nums.length; i++){
            if(zero > 0){
                nums[i] = 0;
                zero--;
            }
            else{
                if(one > 0){
                    nums[i] = 1;
                    one--;
                }
                else{
                    nums[i] = 2;
                }
            }
        }
    }
}






Construct Binary Tree from Preorder and Inorder Traversal
Given preorder and inorder traversal of a tree, construct the binary tree.
Note:
You may assume that duplicates do not exist in the tree.
For example, given
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:
   3
   / \
  9  20
    /  \
   15   7







/*
Consider this input:
preorder: [1, 2, 4, 5, 3, 6]
inorder: [4, 2, 5, 1, 6, 3]

The obvious way to build the tree is:
Use the first element of preorder, the 1, as root.
Search it in inorder.
Split inorder by it, here into [4, 2, 5] and [6, 3].
Split the rest of the preorder into two parts as large as the inorder parts, here into [2, 4, 5] and [3, 6].
Use preorder = [2, 4, 5] and inorder = [4, 2, 5] to add the left subtree.
Use preorder =[3, 6] and inorder = [6, 3] to add the right subtree.
*/
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/34543/Simple-O(n)-without-map look for the java based solution in the comments. And also explanation above it

class Solution {
    int in = 0;
    int pre = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder , Integer.MIN_VALUE);
    }
    
    public TreeNode helper(int[] preorder, int[] inorder, int stop){
        if(pre >= preorder.length) return null;
        
        //Below condition is to stop if we reach the leaf or null nodes and hence nothing to go forward. so in this example when are done taking the value of 3 and 9 after which the left side is done and hence we need to move to the next index in the inorder array.

        if(inorder[in] == stop){ 
            in++;
            return null;
        }
        TreeNode node = new TreeNode(preorder[pre++]);
        node.left = helper(preorder, inorder, node.val);//updating with node.val here.
        node.right = helper(preorder, inorder, stop); //passing stop as it is here.
        
        return node;
    }
}




Populating Next Right Pointers in Each Node II

/*
Follow this video - https://www.youtube.com/watch?v=vy2mnT3TEXQ 
Childhead is always the leftmost node in the code.
Time complexity is O(N) since we are visiting each node  only once.
And space is O(1)

// Definition for a Node.
//we will maintain 3 pointers that are childhead, child and parent. And in this case the childhead is always the leftmost node.

class Solution {
    public Node connect(Node root) {
        Node childhead = null, child = null;
        Node parent = root;
        while(parent != null){
            while(parent != null){
                if(parent.left != null){
                    if(childhead == null){
                        childhead = parent.left;
                    }
                    else{
                        child.next = parent.left;
                    }
                    child =  parent.left;
                } //end while loop -- if( parent.left != null)
                if(parent.right != null){
                    if(childhead == null){
                        childhead = parent.right;
                    }
                    else{
                        child.next = parent.right; //This is where we assign left one to right.
                    }
                    child = parent.right;  
                }//end while loop -- if(parent.right != null)
             parent = parent.next;   
            } //end of the while loop just after main while loop -- while(parent!= null)
            parent = childhead;
            child = null; 
            childhead = null;r
        } 			//main outer while loop --- while(parent != null)
        return root;
    }
}


Convert Binary Search Tree to Sorted Doubly Linked List
/*Here we will do inorder traversal, go to the left and then point the node.left to pre and pre.right to node. Then we move pre to node. Finally after getting out of the loop we have to point tail to head and head to tail.*/


class Solution {
    public Node treeToDoublyList(Node root) {
        if(root == null)
            return null;
        Node dummy = new Node();
        Node pre = dummy;
        Node curr = root;
        Stack<Node> stack = new Stack<Node>();
        
        while(curr != null || !stack.isEmpty())
        {
            while(curr != null)
            {
                stack.push(curr);
                curr = curr.left;
            }
            



//this the steps we do that double connection
Node node = stack.pop();
            curr = node.right;
            
            node.left = pre;
            pre.right = node;
            pre = node;
        }
//below step to point head to tail and tail to head.
        Node head = dummy.right;
        pre.right = head;
        head.left = pre;
        
        return head;
    }
}



173. Binary Search Tree Iterator

implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
Calling next() will return the next smallest number in the BST.
 
Example:

/*We can achieve this by using stack. Wherein for stack we store the TreeNode. We will use inorder here to get the next smallest number, since we know inorder returns the result in the sorted order fashion, so next( ) will return the next smallest only every time.*/

class BSTIterator {
    Stack<TreeNode> stack;
    TreeNode node;
    public BSTIterator(TreeNode root) {
       stack = new Stack<TreeNode>();
        node = root;
    }
    
    /** @return the next smallest number */
    public int next() {
        while(node != null){
            stack.push(node);
            node = node.left;
        }
        node = stack.pop();
        int next_val = node.val;
        node = node.right;
        return next_val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || node != null;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */




Largest Number
/*we will take combinations of numbers like if 3, 30 and this is string  so what we can do is compare them each time, like for example a = 3 and b = 30 so a+b=330 but b+a=303; we can clearly see that  a+b is greater. We will use the same logic across for all numbers in the string. For this we have to override the comparator method here.
*/

https://www.youtube.com/watch?v=qEIGhVtZ-sg 
class Solution {
    private class LargestNumberComprator implements Comparator<String>{
        @Override
        public int  compare(String a, String b){
            String o1 = a + b;
            String o2 =  b + a;
            return o2.compareTo(o1);
        }
    }
    public String largestNumber(int[] nums) {
//Convert it to a String array from int as given below.
String strArray[] = Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);
        
        Arrays.sort(strArray, new  LargestNumberComprator());
        
        if(strArray[0].equals("0")){
            return "0";
        }
        
        String result = "";
        for(String str: strArray){
            result += str;
        }
        
        return result;
    }
}







Course Schedule
https://www.youtube.com/watch?v=0LjVxtLnNOk 
Time Complexity:
If we use adjacency matrix : O(V2)
If we use adjacency list: O(E+V)

There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 
Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.

/*We are going to use the Topological sorting here.*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int count = 0;
        for(int i=0; i<prerequisites.length; i++){
            indegree[prerequisites[i][0]]++; //We are incrementing all the pre-req courses. Like incrementing all the incoming arrow's.
        }
        
        Stack<Integer> s = new Stack<Integer>();
        
        for(int i=0; i<indegree.length; i++){
            if(indegree[i] == 0){ //The courses which don't have any connections move them to stack.
                s.push(i);
            }
        }
        
        while(!s.isEmpty())
        {
            int curr = s.pop();
            count++;//courses complete count.
            for(int i=0; i<prerequisites.length; i++)
            {
                if(prerequisites[i][1] == curr)//we should match course hence we have [i][1]
                {
                    indegree[prerequisites[i][0]]--;//We are making 5 as zero & add 2 to stack.
                    if(indegree[prerequisites[i][0]] == 0)
                        s.push(prerequisites[i][0]);
                }
            }
        }
        return count == numCourses;
    }
 }








ZigZag Conversion
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:
string convert(string s, int numRows);
Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I

/*We have to traverse through each character
1) Create an array of n strings, arr[n]
2) Initialize direction as "down" and row as 0. The 
   direction indicates whether we need to move up or 
   down in rows. 
3) Traverse the input string, do following for every
   character.
   a) Append current character to string of current row.
   b) If row number is n-1, then change direction to 'up'
   c) If row number is 0, then change direction to 'down'
   d) If direction is 'down', do row++.  Else do row--.
4) append all the strings of arr[]. 
https://www.youtube.com/watch?v=LhacuzXRVKI 
*/
class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1)
            return s;
        boolean down = true;
        int row = 0;
        char[] ch = s.toCharArray();
        int len = s.length();
        String[] arr = new String[len];
        Arrays.fill(arr, "");
        for(int i=0; i<len; ++i){
            arr[row] += (ch[i]);
            // If last row is reached,change direction to 'up'
            if(row == numRows-1){
                down = false;
            }//If we are at top then set down as true.
            else if(row == 0){
                down = true;
            }
            //row++ if down is true, more rows are there.
            if(down){
                row++;
            }
            else {
                row--;
            }
        }
        String output = "";
        for(String str: arr)
            output += str;
        
        return output;
    }
}








Odd Even Linked List

/*Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
Example 1:
Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL

Example 2:
Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
*/
Here we will  maintain two pointers odd and even...go throu while loop until even is not null….set odd.next to even.next (since after even always we will have odd) and vice a versa. Finally we need to set odd.next = LastEvenHead since there will be last even remaining and we need to point odd to that node. Return head then.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null)
            return null;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode lastEvenHead = even;
        while(even != null && even.next != null){
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
        }
        odd.next = lastEvenHead; //important step.
        return head;
    }
}



Counting Bits
Given a non negative integer number num. For every number i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
Example 1:
Input: 2
Output: [0,1,1]
Example 2:
Input: 5
Output: [0,1,1,2,1,2]
/*We use idea of bit operator here. We do left shift operation on binary number "1" to get the number of one's in the target number have by doing "and" operation because when we do "and" operation with binary number 1 it will always give us the count of 1's in the target number.
Take a example of input = 5 so the binary for that is 0101, when we do and operation with 0001 we get count as 1, in second iteration when we do 0101 with left shifted 0010 we get zero when we do and operation....so this concludes we can get count of 1's by doing this.*/

class Solution {
    public int[] countBits(int num) {
        int arrIndex = num+1;
        int[] output = new int[arrIndex];
        
        while(num >= 0){
            int numOfOnes = 0;
            
            for(int i=0; i<32; i++){
                int x = (1 << i); //this for binary number 1 shifting by value of i
                
                if((num & x) != 0)//this is to check in the target to see how many 1's we have.
                    numOfOnes++;
            }
            System.out.println(arrIndex);
            output[arrIndex-1] = numOfOnes;
            arrIndex--;
            num -= 1;
        }
        return output;
    }
}






Nth Highest Salary:

with cte as
(
  select rank() over (order by salary asc) as dr, salary, id
    from sandbox.amantri.testemp
)
select id,salary from cte where dr = 2;



The Trips table holds all taxi trips. Each trip has a unique Id, while Client_Id and Driver_Id are both foreign keys to the Users_Id at the Users table. Status is an ENUM type of (‘completed’, ‘cancelled_by_driver’, ‘cancelled_by_client’).
+----+-----------+-----------+---------+--------------------+----------+
| Id | Client_Id | Driver_Id | City_Id |        Status      |Request_at|
+----+-----------+-----------+---------+--------------------+----------+
| 1  |     1     |    10     |    1    |     completed      |2013-10-01|
| 2  |     2     |    11     |    1    | cancelled_by_driver|2013-10-01|
| 3  |     3     |    12     |    6    |     completed      |2013-10-01|
| 4  |     4     |    13     |    6    | cancelled_by_client|2013-10-01|
| 5  |     1     |    10     |    1    |     completed      |2013-10-02|
| 6  |     2     |    11     |    6    |     completed      |2013-10-02|
| 7  |     3     |    12     |    6    |     completed      |2013-10-02|
| 8  |     2     |    12     |    12   |     completed      |2013-10-03|
| 9  |     3     |    10     |    12   |     completed      |2013-10-03| 
| 10 |     4     |    13     |    12   | cancelled_by_driver|2013-10-03|
+----+-----------+-----------+---------+--------------------+----------+

The Users table holds all users. Each user has an unique Users_Id, and Role is an ENUM type of (‘client’, ‘driver’, ‘partner’).
+----------+--------+--------+
| Users_Id | Banned |  Role  |
+----------+--------+--------+
|    1     |   No   | client |
|    2     |   Yes  | client |
|    3     |   No   | client |
|    4     |   No   | client |
|    10    |   No   | driver |
|    11    |   No   | driver |
|    12    |   No   | driver |
|    13    |   No   | driver |
+----------+--------+--------+

Write a query to find the cancellation rate of requests made by unbanned users (both client and driver must be unbanned) between Oct 1, 2013 and Oct 3, 2013. The cancellation rate is computed by dividing the number of canceled (by client or driver) requests made by unbanned users by the total number of requests made by unbanned users.
For the above tables, your SQL query should return the following rows with the cancellation rate being rounded to two decimal places.
+------------+-------------------+
|     Day    | Cancellation Rate |
+------------+-------------------+
| 2013-10-01 |       0.33        |
| 2013-10-02 |       0.00        |
| 2013-10-03 |       0.50        |
+------------+-------------------+



select Request_at as Day,
ROUND(( count(IF (t.STATUS != 'completed', TRUE, null))/count(*)),2 ) as 'Cancellation Rate'
from Trips t
where t.Client_Id in (select Users_Id from Users where Banned = 'No')
and t.Driver_Id in (select Users_Id from Users where Banned = 'No')
and t.Request_at between '2013-10-01' and '2013-10-03'
group by t.Request_at;




We can remove the duplicate rows using the following CTE (Remove duplicates only and keep one record query below)
WITH CTE([FirstName], 
    [LastName], 
    [Country], 
    DuplicateCount)
AS (SELECT [FirstName], 
       	[LastName], 
       	[Country], 
       	ROW_NUMBER() OVER(PARTITION BY [FirstName], 
                                          [LastName], 
                                          [Country]
       	ORDER BY ID) AS DuplicateCount
    FROM [SampleDB].[dbo].[Employee])
DELETE FROM CTE
WHERE DuplicateCount > 1;


Team Scores in Football Tournament:-

You would like to compute the scores of all teams after all matches. Points are awarded as follows:
A team receives three points if they win a match (Score strictly more goals than the opponent team).
A team receives one point if they draw a match (Same number of goals as the opponent team).
A team receives no points if they lose a match (Score less goals than the opponent team).
Write an SQL query that selects the team_id, team_name and num_points of each team in the tournament after all described matches. Result table should be ordered by num_points (decreasing order). In case of a tie, order the records by team_id (increasing order).
The query result format is in the following example:
Teams table:
+-----------+--------------+
| team_id   | team_name    |
+-----------+--------------+
| 10        | Leetcode FC  |
| 20        | NewYork FC   |
| 30        | Atlanta FC   |
| 40        | Chicago FC   |
| 50        | Toronto FC   |
+-----------+--------------+

Matches table:
+------------+--------------+---------------+-------------+--------------+
| match_id   | host_team    | guest_team    | host_goals  | guest_goals  |
+------------+--------------+---------------+-------------+--------------+
| 1          | 10           | 20            | 3           | 0            |
| 2          | 30           | 10            | 2           | 2            |
| 3          | 10           | 50            | 5           | 1            |
| 4          | 20           | 30            | 1           | 0            |
| 5          | 50           | 30            | 1           | 0            |
+------------+--------------+---------------+-------------+--------------+

Result table:
+------------+--------------+---------------+
| team_id    | team_name    | num_points    |
+------------+--------------+---------------+
| 10         | Leetcode FC  | 7             |
| 20         | NewYork FC   | 3             |
| 50         | Toronto FC   | 3             |
| 30         | Atlanta FC   | 1             |
| 40         | Chicago FC   | 0             |
+------------+--------------+---------------+

/*Declare a CTE with matching host and guest team*/
with tournament as (
select t.team_id,
        t.team_name,
     case when team_id = host_team and m.host_goals > m.guest_goals then 3
             when team_id = guest_team and m.guest_goals > m.host_goals then 3
             when m.host_goals = m.guest_goals then 1
              else 0 end num_points
from Teams t left join Matches m 
on t.Team_Id = m.host_team or t.Team_Id = m.guest_team)
select sum(num_points) as num_points, team_id, team_name from tournament
group by team_id, team_name


1270. All People Report to the Given Manager
Write an SQL query to find employee_id of all employees that directly or indirectly report their work to the head of the company.
The indirect relation between managers will not exceed 3 managers as the company is small.
Return result table in any order without duplicates.
The query result format is in the following example:
Employees table:
+-------------+---------------+------------+
| employee_id | employee_name | manager_id |
+-------------+---------------+------------+
| 1           | Boss          | 1          |
| 3           | Alice         | 3          |
| 2           | Bob           | 1          |
| 4           | Daniel        | 2          |
| 7           | Luis          | 4          |
| 8           | Jhon          | 3          |
| 9           | Angela        | 8          |
| 77          | Robert        | 1          |
+-------------+---------------+------------+

Result table:
+-------------+
| employee_id |
+-------------+
| 2           |
| 77          |
| 4           |
| 7           |
+-------------+

The head of the company is the employee with employee_id 1.
The employees with employee_id 2 and 77 report their work directly to the head of the company.
The employee with employee_id 4 report his work indirectly to the head of the company 4 --> 2 --> 1. 
The employee with employee_id 7 report his work indirectly to the head of the company 7 --> 4 --> 2 --> 1.
The employees with employee_id 3, 8 and 9 don't report their work to head of company directly or indirectly.


//going into second level to find out who are the managers for the employees we found in cte1. 
//Then we follow the same for cte3 were we find the employee id who are manager we found in cte2.
//First cte1 will give you only boss 2, 77 (direct reports) 
//cte2 will give you who are employee of manager - 2,77 and //they are 2,77,4 and then cte3 we have to find manager are //2,77,4 and those employee are 2,77,4,7

with
cte1 as (
select employee_id from employees
where manager_id = 1 and employee_id<>1),
cte2 as (
select e.employee_id from employees e, cte1
where manager_id = cte1.employee_id),
cte3 as (
select e.employee_id from employees e, cte2
where manager_id = cte2.employee_id)
select * from cte1
union 
select * from cte2
union 
select  * from cte3





Delete Duplicate Emails

Write a SQL query to delete all duplicate email entries in a table named Person, keeping only unique emails based on its smallest Id.
+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
Id is the primary key column for this table.

For example, after running your query, the above Person table should have the following rows:
+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+


delete p1 from Person p1, Person p2
where p1.Email = p2.Email and p1.Id > p2.Id



626. Exchange Seats

Mary wants to change seats for the adjacent students.
 
Can you write a SQL query to output the result for Mary?
 
+---------+---------+
|    id   | student |
+---------+---------+
|    1    | Abbot   |
|    2    | Doris   |
|    3    | Emerson |
|    4    | Green   |
|    5    | Jeames  |
+---------+---------+

For the sample input, the output is:
 
+---------+---------+
|    id   | student |
+---------+---------+
|    1    | Doris   |
|    2    | Abbot   |
|    3    | Green   |
|    4    | Emerson |
|    5    | Jeames  |
+---------+---------+

Algorithm
There are 3 cases

For students with even-numbered ids, subtract 1 from their id
For students with odd-numbered ids, add 1 to their id (Except if this odd-numbered student has the highest id in the class
For students with odd-numbered id who also have the highest id in the class, don't change the id.
MySQL Solution






SELECT
   CASE
   WHEN id % 2 = 0 THEN id - 1
   WHEN id % 2 = 1 AND id != (SELECT COUNT(*) FROM seat) THEN id + 1
        ELSE id
    END as id,
	  student
FROM seat
ORDER BY id




597. Friend Requests I: Overall Acceptance Rate
https://leetcode.com/problems/friend-requests-i-overall-acceptance-rate/discuss/358575/Detailed-Explaination-for-Question-and-2-follow-up 

In social network like Facebook or Twitter, people send friend requests and accept others’ requests as well. Now given two tables as below:
 
Table: friend_request
| sender_id | send_to_id |request_date|
|-----------|------------|------------|
| 1         | 2          | 2016_06-01 |
| 1         | 3          | 2016_06-01 |
| 1         | 4          | 2016_06-01 |
| 2         | 3          | 2016_06-02 |
| 3         | 4          | 2016-06-09 |

 
Table: request_accepted
| requester_id | accepter_id |accept_date |
|--------------|-------------|------------|
| 1            | 2           | 2016_06-03 |
| 1            | 3           | 2016-06-08 |
| 2            | 3           | 2016-06-08 |
| 3            | 4           | 2016-06-09 |
| 3            | 4           | 2016-06-10 |

 
Write a query to find the overall acceptance rate of requests rounded to 2 decimals, which is the number of acceptance divide the number of requests.
 
For the sample data above, your query should return the following result.
 
|accept_rate|
|-----------|
|       0.80|

# Write your MySQL query statement below
#1
select round(ifnull(count(distinct requester_id, accepter_id)/count(distinct sender_id, send_to_id), 0), 2) as accept_rate
from request_accepted, friend_request

#2 write a query to return the accept rate but for every month?

select round(ifnull(c.acp/d.scp, 0), 2) as accept_rate, c.month from 
(select count(distinct requester_id, accepter_id) as acp, Month(accept_date) as month from request_accepted) c,
(select count(distinct sender_id, send_to_id) as scp, Month(request_date) as month from friend_request) d
where c.month = d.month
group by c.month


 602. Friend Requests II: Who Has the Most Friends
able request_accepted
+--------------+-------------+------------+
| requester_id | accepter_id | accept_date|
|--------------|-------------|------------|
| 1            | 2           | 2016_06-03 |
| 1            | 3           | 2016-06-08 |
| 2            | 3           | 2016-06-08 |
| 3            | 4           | 2016-06-09 |
+--------------+-------------+------------+
This table holds the data of friend acceptance, while requester_id and accepter_id both are the id of a person.

 
Write a query to find the the people who has most friends and the most friends number under the following rules:
It is guaranteed there is only 1 people having the most friends.
The friend request could only been accepted once, which mean there is no multiple records with the same requester_id and accepter_id value.
For the sample data above, the result is:
Result table:
+------+------+
| id   | num  |
|------|------|
| 3    | 3    |
+------+------+
The person with id '3' is a friend of people '1', '2' and '4', so he has 3 friends in total, which is the most number than any others.
/*In the below query since the accepter and the requester when they send the friend request both of them can be friends so in this case that count as one friend. Hence we take union of requester with accepter as id1 and different for id2. Finally in the outer query counting of both would give us the final result.*/
select id1 as id, count(*) as num
from(
    select requester_id as id1, accepter_id as id2 from         request_accepted
    union 
    select accepter_id as id1, requester_id as id2 from request_accepted
) a 
group by 1
order by count(*) desc
limit 1

/*Snowflake Dedup example using ROW_NUMBER( ) */

select CUST_FIRST_NAME,
CUST_EMAIL, 
POS,
LANGUAGE,
OPT_IN_STATUS,
OPT_IN_TIMESTAMP,
META_CREATED_DATE,
META_UPDATED_DATE,
IS_CURRENT,META_SCD_IS_CURRENT
from(
select CUST_FIRST_NAME,
CUST_EMAIL,
POS,LANGUAGE,OPT_IN_STATUS,OPT_IN_TIMESTAMP,META_CREATED_DATE,META_UPDATED_DATE,IS_CURRENT,META_SCD_IS_CURRENT,

row_number() over (partition by cust_email order by OPT_OUT_TIMESTAMP desc) rn 
from "SANDBOX"."RAHPRASAD"."DIM_CUSTOMER"
where cust_email in ('antocapo@gmail.com', 'maikelaj@gmail.com') 
) dedup
where dedup.rn=1




/*Remove duplicate product and only print the non-duplicate one’s, asked in the HBO max*/

Select name,
	id,
	brand_name,
	date from 
(
Select id,
     row_number over (partition by name order by date desc)rn,
	brand_name,
	date,
	name
	from products
) dedup 
where dedup.rn = 1


/*Get the top 2nd or third or any highest selling product from the product table, asked in the HBO max*/

Select sum(qty), product_id
from products
group by product_id, date_part(‘month’, date)
having date_part(‘month’, date) = ‘01’
order by sum(qty) desc
limit 1
Offset 1 //here offset gives me the range.


/*Query to get the self join and know who reports to who. This give list of employees and their manager
https://www.zentut.com/sql-tutorial/sql-self-join/  */



SELECT
    e.name as employee,
    m.name as manager
FROM
    employees e
INNER JOIN
    employees m ON m.employeeid = e.reportsto;



/*Get the cohort number from the list of the employee table. For example the 
*/

Employees who have joined during the same time in ascending order are the cohort.
//below will give you result for the second cohort
with cte as
(
  select name,start_date,
  dense_rank() over (order by start_date desc) as d_rank
  from sandbox.amantri.order_customer
)
select name,start_date from cte where d_rank = 2;








/*Roku interview question-*/
Given tables below, write query that provides total streaming seconds and number of unique device_ids with streaming seconds by date_key and channel_name for the month of June 2020.
 
if no channel_name row for a channel_id in channels table then return channel_name as "no name".
sort results by date_key & channel_name.
 
 
device_streaming
 
 
 
device_id (string)
channel_id (int)
streaming_seconds(int)
date_key (string) 
xyz001
12
2200
2020-06-01
xyz001
28
1834
2020-06-01
xyz009
12
830
2020-06-02
xyz009
60
400
2020-06-02
xyz001
12
1234
2020-06-02
xyz005
60
4321
2020-06-02
xyz006
85
830
2020-06-03
xyz001
12
760
2020-06-03
 
channels
 
 
 
channel_id (int)
channel_name (string)
 
 
12
sports
 
 
13
news
 
 
28
music


















select a.date_key,
case when b.channel_name is NULL then "no name" 
else b.channel_name end, 
sum(a.streaming_seconds) as total_seconds,
count(distinct a.device_id) as devices
from device_streaming a 
left join channels b
on a.channel_id = b.channel_id
where 1=1
and a.date_key between '2020-06-01' and '2020-06-30'
group by a.date_key, b.channel_name
order by a.date_key, b.channel_name desc;
/**/


Identify the primary device for each account in June 2019. Primary device is defined as the device with most usage in the given period. If account used only 1 device, then it would automatically be the primary device.
 
Table: streaming_logs
Schema:  
account_id varchar(40),
device_id varchar(40),
channel_id Integer,
play_hours FLOAT,
date_key varchar(10)
No. of Records: 283
Note: Dates are in yyyy-MM-dd format.



#Design tables/data structures to capture an order placed by a customer in Washington for 1 shirt and 2 bags of cat food.


customer_Table 
 cust_id
 cust_first_name
 cust_last_name
 cust_city
 cust_state
 cust_country
 cust_address
 cust_zipcode
 cust_email
 order_id
 
order_table
 order_id
 order_number
 order_status
 order_date
 total_order_price
 
product_table
 order_id
 product_id
 product_name
 product_description
 product_price
 product_availablity
 product_category
 
 item_table
  order_id
  item_name
  item_quantity
  item_price
    
 
 


 #Write a query that returns all customers that live in WA and include all of their orders with all of the order details
 
 select c.cust_id, 
        c.cust_state,
        o.order_number,
        p.product_id,
        p.product_name,
        c.order_id
from customer_table c
join order_table o on c.order_id = o.order_id
join product_table p on p.order_id = c.order_id
where lower(c.cust_state) = 'WA'
 
#order_id is unique to a product purchased by customer
#Write a query that finds all customers that have at least two items in the order
select cust_email,
    cust_id,
    cust_first_name,
    cust_last_name,
    count(i.item_quantity)
    #count(order_id) as order_placed
from customer_table 
join item i on c.order_id = i.order_id
having count(i.item_quantity) >= 2
group by cust_email, cust_id, cust_first_name, cust_last_name




 
 cust_email - arjun@gmail.com 1232
               arjun@gmail.com 3432
                arjun@gmail.com 343
 
                
                
 #Turn the order and customer transactional data into a data mart to be used for reporting.  Design the tables/data structures that will make it easy for the business to calculate business #metrics.  Here are some metrics they want to measure:   the number of customers by state, the number of customers who ordered, the average amount customers spent per order.
 
 fact_order_table
  #country_key
  cust_id
  cust_fist_name
  cust_last_name
  cust_city
  cust_state
  order_id
  product_id
  product_price
  total_order_price
   
  dim table country


#Write a query that returns the number of customers by state
select count(cust_id), cust_state
from fact_order_table
group by cust_state;


#Write a query that returns the number of customers who ordered
select count(cust_id)
from fact_order_table
where order_id is not null;








//SQL to find the minimum number of meeting room required 
Let's say you have a table called 'meeting' like this -

Then You can use this query to get the minimum number of meeting Rooms required to accommodate all Meetings.
SELECT Max(minimum_rooms_required)
FROM  (SELECT Count(*) minimum_rooms_required
        FROM   meetings t
               LEFT JOIN meetings y
                      ON t.start_time >= y.start_time
                         AND t.start_time < y.end_time
        GROUP  BY t.id) z; 


 





Binary Tree Right Side View
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
Its is the discussion section - https://leetcode.com/problems/binary-tree-right-side-view/discuss/56012/My-simple-accepted-solution(JAVA) 
Example:
Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 BFS way is much more intuitive. Do the level order traversal, and add the last node on every layer.
 https://www.youtube.com/watch?v=eBdKNoW3VJg*/

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
       TreeNode curr = root;
        List<Integer> list = new LinkedList<Integer>();
        if(root == null) return list; 
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();//everytime initialize new size. Get size to work on each level.
            while(size-- > 0){ //this is post decrement.
                TreeNode n = q.remove();
                if(size == 0) //When size is zero we have the last element and hence add to list
                    list.add(n.val);
                if(n.left != null)q.add(n.left);
                if(n.right != null)q.add(n.right);
            }
        }
        return list;
    }
}


Similarly we have a left side level view tree.
Code - https://www.geeksforgeeks.org/print-left-view-binary-tree/ 
https://www.youtube.com/watch?v=AIokcTT0LuE 

static int max_level = 0;
  
    // recursive function to print left view
    void leftViewUtil(Node node, int level)
    {
        // Base Case
        if (node == null)
            return;
  
        // If this is the first node of its level
        if (max_level < level) {
            System.out.print(" " + node.data);
            max_level = level;
        }
  
        // Recur for left and right subtrees
        leftViewUtil(node.left, level + 1);
        leftViewUtil(node.right, level + 1);
    }
  
    // A wrapper over leftViewUtil()
    void leftView()
    {
        leftViewUtil(root, 1);
    }




Binary Tree Vertical Order Traversal

https://www.youtube.com/watch?v=QWbVCqIhTO4 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */


/*So the idea is to have left = curr - 1 and right = curr + 1. So in this way then we store all the same column elements into the map against that column value.*/

class Solution {
    class Node {
        TreeNode root;
        int hd; //this is the horizontal distance, basically this is the column value.
        Node(TreeNode root, int hd){
            this.root = root;
            this.hd = hd;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;
        Queue<Node> q = new LinkedList<>();
//below TreeMap to store the column value and all the list of integer on that column   
//since we want the order as well.        
        TreeMap<Integer, List<Integer>> tmap = new TreeMap<>();
        q.add(new Node(root, 0));
        
        while(!q.isEmpty()){
            Node curr = q.poll();
            tmap.putIfAbsent(curr.hd, new ArrayList<>());
//getting the value of the current column and adding the actual value of node to the map.            
            tmap.get(curr.hd).add(curr.root.val);
            if(curr.root.left != null)
            {//since for the left the hd or column would be -1 from the current node
                q.offer(new Node(curr.root.left, curr.hd - 1));
            }
            
            if(curr.root.right != null)
            {//since for the right the hd or column would be +1 from the current node
                q.offer(new Node(curr.root.right, curr.hd + 1));
            }
        }
        
        for(Map.Entry<Integer, List<Integer>> map : tmap.entrySet())
            res.add(map.getValue());
        return res;
    }
}


Merge k sorted linked lists and return it as one sorted list. 
Analyze and describe its complexity.
Example:
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6


/*In this case of comparison make sure you can use a "Priority Queue" which is a general approach in case of compare and add.*/

https://www.youtube.com/watch?v=tDn9O7UQ4E8 
Time complexity : O(N\log k) O(Nlogk) where k is the number of linked lists. The comparison cost will be reduced to O(logk) for every pop and insertion to priority queue. But finding the node with the smallest value just costs O(1) time.  There are N nodes in the final linked list. Space - O(n) Creating a new linked list costs O(n) space.


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0)
            return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);//Here this is Min-Heap.
        
        for(ListNode n : lists)
            if(n != null) //Check for not null of head.
                pq.offer(n);
            
        ListNode dummy = new ListNode(-1);//Creating dummy since we return it then.
        ListNode curr = dummy;
        while(!pq.isEmpty()){
            ListNode n = pq.poll();
            curr.next = n;//Pointing curr to the start of list
            curr = n;
            if(n.next != null) //Going over reaming node in the list for eg: after 1 we have 4,5 - 1 -> 4 -> 5
                pq.add(n.next);
        }
        return dummy.next;
    }
}




Convert Integer to English words.
/*We always denote the numbers separated by thousand for example "123,234,456,789" notice the comma here which can always be achieved by % or / by 1000. Hence we do that and need to define the static for less_then_20, Tens and Thousand's as well.*/






class Solution {

private final String[] Less_Then_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

private final String[] Tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

private final String[] Thousands = {"", "Thousand", "Million", "Billion"};
      
    public String numberToWords(int num) {  
        if(num == 0)
            return "Zero";
        int i=0;
        String words = "";
        
        while(num > 0){
            if(num % 1000 != 0)
                words = helper(num%1000) + Thousands[i] + " " + words;//here we % by 1000 everytime
            num /= 1000;//Reducing the numbers here.
            i++;
        }
        
        return words.trim();
    }
    public String helper(int num){
        if(num == 0)
            return "";
        else if(num < 20)
            return Less_Then_20[num] + " ";
        else if(num < 100)
            return Tens[num/10] + " " + helper(num % 10);//less than 100 hence     always /10 and then %10
        else return Less_Then_20[num/100] + " Hundred " + helper(num % 100);//for everything else /100 & %100
        }
    }








Alien Dictionary
Example 1:
Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"

Example 2:
Input:
[
  "z",
  "x"
]

Output: "zx"

Example 3:
Input:
[
  "z",
  "x",
  "z"
] 

Output: "" 

Explanation: The order is invalid, so return "".



/*In any such order related problem we use comparators to compare the words. First we need to find the relation between words and for that we would need graph (so we create an adjacency list) and to traverse & sort we will be using topological sort here. In Build graph method first we get minimum of the two words and find the different character*/
https://www.youtube.com/watch?v=LA0X_N-dEsg 
https://happygirlzt.com/code/269.html 

class Solution {
    public String alienOrder(String[] words) {
        int[] indegree = new int[26];
        Map<Character, Set<Character>> graph = new HashMap<>();
        buildGraph(graph, words, indegree);
        return bfs(graph, indegree);
    }
    
    public String bfs(Map<Character, Set<Character>> graph, int[] indegree){
        StringBuilder s = new StringBuilder();
        int totalChars = graph.size();//this is to get the valid length.
        Queue<Character> q = new LinkedList<>();
        
        for(char c : graph.keySet()){
            if(indegree[c - 'a'] == 0){
                s.append(c);
                q.offer(c);
            }
        }
        //Now we will do standard BFS
        while(!q.isEmpty()){
            char curr = q.poll();
            if(graph.get(curr) == null ||  graph.get(curr).size() == 0) 
continue;//This is to check if they have neighbor nodes or not.
            for(char neigh : graph.get(curr)){
                indegree[neigh - 'a']--;
                if(indegree[neigh - 'a'] == 0){//When de connection is 0 we move 2 queue
                    q.offer(neigh);
                    s.append(neigh);
                }
            }
            
        }
        return s.length() == totalChars ? s.toString() : "";
    }
    public void buildGraph(Map<Character, Set<Character>> graph, String[] words, int[] indegree){
        for(String word : words){
            for(char c : word.toCharArray()){
                graph.putIfAbsent(c, new HashSet<>());//Creating  the graph first. Adjacency list.
            }
        }
        
        for(int i=1; i<words.length; i++){
            String first = words[i - 1];
            String second = words[i];
            int len = Math.min(first.length(), second.length());//We get length here for the "for loop" below.
            
            for(int j=0; j<len; j++){
                if(first.charAt(j) != second.charAt(j)){
                    char out = first.charAt(j);
                    char in = second.charAt(j);
                    
                    if(!graph.get(out).contains(in)){//to check if this relation is added to the graph or not.
                        graph.get(out).add(in);
                        indegree[in - 'a']++;//Incoming arrow hence indegree++
                }
                    break;
            }//in case length is different we need to clear the  graph.
                if(j+1 == len && first.length() > second.length()){
                    graph.clear();
        }
    }
  }
}
}



Rotting Oranges
       min0	     min1	   min2 	min3	          min4

Input: [[2,1,1],
 [1,1,0],
 [0,1,1]]
Output: 4

Example 2:
Input: [[2,1,1],
 [0,1,1],
 [1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.


Example 3:
Input: [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
/*The idea is to use the BFS approach here. We need to first count the fresh and rotten oranges. Store the rotten in the queue. Perform BFS on the queue at each level. In BFS 2D matrix check for boundary conditions for any empty cell or rotten oranges if not make the orange rotten and reduce the fresh count.*/
public class RottenOranges {

	public static int orangesRotting(int[][] grid) {
        //BFS approach
        
        //edge case
        if(grid == null || grid.length == 0)
            return 0;
        
        int numberOfRows = grid.length;
        int numberOfColumns = grid[0].length;
        int timestamp = 0;
        int countFresh = 0;
        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = {{1,0}, {-1, 0}, {0,1}, {0, -1}};
        //{1,0} -> down, {-1, 0} -> up, {0,1} -> right, {0, -1} -> left
        
//to begin, add rotten oranges to the queue and count the number of fresh oranges.
        for(int row = 0; row < numberOfRows; row++){
            for(int col = 0; col < numberOfColumns; col++){
                if(grid[row][col] == 2)
                    queue.offer(new int[] {row, col});
                else if(grid[row][col] == 1)
                    countFresh++;
            }
        }
        
        //if there are no fresh oranges then return 0;
        if(countFresh == 0)
            return 0;
        
        while(!queue.isEmpty()){
            timestamp++;
            int qSize = queue.size();
            
            for(int i = 0; i < qSize; i++){
                int[] point = queue.poll();
                
                //check for the 4 directions
                for(int[] direction : directions){
                    int x = point[0] + direction[0];
                    int y = point[1] + direction[1];
                    
                    //check for out of bounds, rotten oranges and empty cell exit
                    if(x < 0 || y < 0 || x >= numberOfRows || y >= numberOfColumns || 
                       grid[x][y] == 2 || grid[x][y] == 0)
                        continue;
                    
     //this means we are at a grid point where the orange is fresh and can be rotten
                    grid[x][y] = 2;
                    //reduce the fresh orange count
                    countFresh--;
                    //add the new rotten orange to the queue for the next level
                    queue.offer(new int[]{x, y});
                }
            }
        }
        
        return countFresh == 0 ? timestamp - 1 : -1;
    }


Walls and Gates
Solution
You are given a m x n 2D grid initialized with these three possible values.
-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
Example: 
Given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF


After running your function, the 2D grid should be:
 3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4


/*This is similar to the rotten oranges problem, here we create directions arrays and then do BFS. For this we create a queue and store the gates in the queue and when we encounter the gate we do BFS around it to find the EMPTY rooms. Now its the same "continue" condition we use r<0,c<0 blah blah with the condition that it should not be equal to empty. Then we add the room to the queue*/

class Solution {
    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int Gate = 0;
    private static final List<int []> Directions = Arrays.asList(
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, 1},
        new int[]{0, -1}
    );
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if(m == 0)
            return;
        int n = rooms[0].length;
        Queue<int []> q = new LinkedList<>();
        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(rooms[r][c] == Gate){
                    q.add(new int[]{r, c});
                }
            }
        }
        while(!q.isEmpty()){
            int[] point = q.poll();
            int row = point[0];
            int col = point[1];
            for(int[] direction : Directions){
                int r = row + direction[0];
                int c = col + direction[1];
                
                if(r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY){
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                q.add(new int[]{r, c});
            }
        }
    }
}









Meeting Room II
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
Example 1:
Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:
Input: [[7,10],[2,4]]
Output: 1

/*General RULE : In this case of the interval problem it's better to seperate the  start and end indexes in general as we do with the merge interval problems. Remember sorting of arrays is required in each and every case.
Here we separate start and end, loop through the arrays and if we know that after sorting the end time is greater then start time we will need a room.*/
class Solution {
    public int minMeetingRooms(int[][] intervals) {
         if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        // Get starts and ends separately
        int len = intervals.length;
        int[] starts = new int[len];
        int[] ends = new int[len];
        for (int i = 0; i < len; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        
        // Sort starts and ends array. Important.
// // Sort starts and ends array. What it does is take all the start elements and sort it and same for the end arrays. so for 
eg: [[0, 30],[5, 10],[15, 20]] in this case start = [0, 5, 15] and end = [10, 20, 30]     
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        // Find min needed rooms
        int rooms = 0;
        for (int startIndex = 0, endIndex = 0; startIndex < len; startIndex++) {
            if (starts[startIndex] < ends[endIndex]) {
                // Start is smaller than end, add one room. Start end time is greater then start time so we have to increase the rooms.
                rooms++;
            } else {
                // Otherwise, move endIndex
                endIndex++;
            }
        }
        return rooms;
    }
}


Shortest Distance from All Buildings

/*This is similar to any 2d array problem. In this we have to go through the 2d array take perform BFS on each of the level (size), do the validation on the boundaries for each row and column standard one.
Go thro array and when we find == “1” then do bfs and totalBuilding++
Declare queue, visited and go thro each size in bfs
Have reach[][] and dist[][] array to maintain the number of buildings and distance for newly created buildings*/
https://www.youtube.com/watch?v=LBZJdtDZmVw 
class Solution {
//Direction for row and col below.
    private final int[] rowDir = new int[]{0, 0, 1, -1};
    private final int[] colDir = new int[]{1, -1, 0, 0};
    public int shortestDistance(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] reach = new int[row][col];//reach from buildings “1”. Storing existing buildings.
        int[][] dist = new int[row][col];//how much is distance from new building
        int totalBuildings = 0;
        for(int r=0; r<row; r++){
            for(int c=0; c<col; c++){
                if(grid[r][c] == 1){ //when we find the building we do totalBuilding++
                    bfs(grid, r, c, reach, dist);
                    totalBuildings++;
                }
            }
        }
            int minDistance = Integer.MAX_VALUE;
            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    if(reach[i][j] == totalBuildings && dist[i][j] < minDistance)
                        minDistance = dist[i][j];
                }
            }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
    //Do the BFS here
    private void bfs(int[][] grid, int r, int c, int[][] reach, int[][] dist){
        int rows=grid.length, cols=grid[0].length;
        Queue<int[]> q = new LinkedList<int[]>();
        boolean[][] visited = new boolean[rows][cols];
        visited[r][c] = true;
        int d=0;
        q.add(new int[]{r, c});
        while(!q.isEmpty()){
            d++;
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] curr = q.poll();
                for(int k=0; k<4; k++){
                    int rr = curr[0] + rowDir[k];
                    int cc = curr[1] + colDir[k];
                    if(!isValid(grid, rr, cc, visited)) continue; 
                    q.add(new int[]{rr, cc});
                    visited[rr][cc] = true;
                    reach[rr][cc]++;
                    dist[rr][cc] += d;
                }
            }
        }
    }
    //Check the boundary conditions
    private boolean isValid(int[][] grid, int r, int c, boolean[][] visited){
        //here grid[r][c] != 0 since we need to find all empty space. Anything but 0 
        if(r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || visited[r][c] == true || grid[r][c] != 0)
            return false;
        return true;
    }
}



Spiral Matrix
Example 1:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

/*So when we do 
1. Left Right that means we going column wise and row is constant
2. up and down that means we are going row wise and column is constant*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0)
            return result;
        int n = matrix.length;
        int m = matrix[0].length;
        int left = 0, right = m -1;
        int  up = 0, down = n -1;
        
        while(result.size() < n * m){
            for(int i=left; i<=right && result.size() < n * m; i++){//LRU(left right up)
                result.add(matrix[up][i]);
            }
            
            for(int j=up + 1; j<=down - 1 && result.size() < n * m; j++){//UDR(up down right)
                result.add(matrix[j][right]);
            }
            
            for(int i=right; i>=left && result.size() < n * m; i--){//RLD(right left down)
                result.add(matrix[down][i]);
            }
            
            for(int j=down-1; j>=up + 1 && result.size() < n * m; j--){//DUL(down up left)
                result.add(matrix[j][left]);
            }
            
            left++;right--;up++;down--;
        }
        return result;
    }
}




Serialize and Deserialize Binary Tree
You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *
 The idea is simple: print the tree in pre-order traversal and use "X" to denote null node and split node with ",". We can use a StringBuilder for building the string on the fly. For deserializing, we use a Queue to store the pre-order traversal and since we have "X" as null node, we know exactly how to where to end building subtress.*/

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serial(new StringBuilder(), root).toString();
    }
    // Generate pre-order string
    public StringBuilder serial(StringBuilder str, TreeNode root){
       //base condition below.
        if(root == null) return str.append("#");//if root null we put # in StringBuilder
        str.append(root.val).append(",");
        serial(str, root.left).append(",");
        serial(str, root.right);
        return str;
    }

    // Decodes your encoded data to a tree.
    public TreeNode deserialize(String data) {
        return deserial(new LinkedList<>(Arrays.asList(data.split(","))));
    }
    // Use queue to simplify position move                               
    public TreeNode deserial(Queue<String> q){
        String val = q.poll();
        if("#".equals(val)) return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = deserial(q);//getting the left node here.
        root.right = deserial(q);//getting the right node here.
        return root;    
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));







Reverse a integer
class Solution {
    public int reverse(int x) {
        if(x == 0)
            return 0;
        int sign = x < 0 ? -1 : 1; //Sign for the negative numbers.
        x = Math.abs(x);
        int res = 0;
            while(x > 0){
                if(Integer.MAX_VALUE/10 < res || (Integer.MAX_VALUE - x % 10) < res * 10)
                    return 0;
                res = res * 10 + x % 10;
                x /= 10;
            }
        return sign * res;
    }
}













31. Next Permutation
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such an arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place and use only constant extra memory.
Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
https://www.youtube.com/watch?v=hPd4MFdg8VU (must watch for the problem explanation)

/*In the example above in the image we have [1, 2, 5, 4, 3] and the next permutation would be the next greatest one which would be [1, 3, 2, 4, 5] . As shown above first we start from the end and then compare the previous and the next, and check if like 4>3, 5>4 but it fails at 2>5 and then we swap 3 with 2, and the final step would be to sort elements from 5 to 2 then.*/

class Solution {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1)
            return;
        int end_index = nums.length - 2;
        
        while(end_index >= 0 && nums[end_index] >= nums[end_index + 1]) end_index--;// Find 1st id i that breaks descending order
        
        if(end_index >= 0){
            int intermediate_index = nums.length - 1; // Start from the end
            while(nums[intermediate_index] <= nums[end_index]) intermediate_index--;// Find rightmost first larger id j
            swap(nums, end_index, intermediate_index);// Switch i and j
        }
        reverse(nums, end_index + 1, nums.length - 1); // Reverse the descending sequence
    }
    
    public void swap(int[] nums, int end_index, int intermediate_index){
        int temporary = nums[end_index];
        nums[end_index] = nums[intermediate_index];
        nums[intermediate_index] = temporary;
    }
    
    public void reverse(int[] nums, int end_index, int intermediate_index){
        while(end_index < intermediate_index) swap(nums, end_index++, intermediate_index--);
    }
}









Must watch  - https://www.youtube.com/watch?v=bSdw9rJYf-I 


10. Regular Expression Matching

class Solution {
    public boolean isMatch(String s, String p) {
        if(p.length() == 0) return s.length() == 0; //when s.len == 0 (this will return true only when string is EMPTY, otherwise false.)
        
        boolean firstMatch = (s.length() > 0 && 
                              (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));//This for example ".a" so this is the start one. 
        if(p.length() >= 2 && p.charAt(1) == '*') // here we have lets we have a*
{
            System.out.println("substring value is " + p.substring(1));
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));//we pass in the second AND condition because let's say if we have s=”aaaaaaa” and p = “a*” we do pass the pattern.
}
        else{
            return firstMatch && isMatch(s.substring(1), p.substring(1));//here are match s=aaba with p=aaba so one to one char mapping or comparison.
        }
    }
}




Design a data structure that supports all following operations in average O(1) time.
insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Approach 1: HashMap + ArrayList
Insert
Add value -> its index into dictionary, average
O(1) time.
Append value to array list, average 
O(1) time as well.



class RandomizedSet {
    Map<Integer, Integer> dict;
    List<Integer> list ;
    Random rand = new Random();
    /** Initialize your data structure here. */
    public RandomizedSet() {
        dict = new HashMap<>();
        list = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(dict.containsKey(val)) return false;
        
        dict.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!dict.containsKey(val)) return false;
        
        int lastElement = list.get(list.size() - 1);
        int idx = dict.get(val);
        list.set(idx, lastElement); //basically replacing those val from list and dict and not swaping.
        dict.put(lastElement, idx);
        
        list.remove(list.size() - 1);
        dict.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */









First Missing Positive
Given an unsorted integer array, find the smallest missing positive integer.
Example 1:
Input: [1,2,0]
Output: 3

Example 2:
Input: [3,4,-1,1]
Output: 2

Example 3:
Input: [7,8,9,11,12]
Output: 1


/*This problem is basically for number line -3 -2 -1 0 1 2 3 4 ....so lets say we are given example of [1, 2, 0] so in this case from number line we can say the positive min is 3....another example [1, 2, 3] now in this case the next missing is actually (arrays.length + 1) so 3+1 = 4 as the min +ve number.
https://www.youtube.com/watch?v=2QugZILS_Q8 
*/ Below is Brute force and it uses SET, the problem here is to use without SET.
class Solution {
    public int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++){
                set.add(nums[i]);
        }
        int k;
        for(k=1; k<=nums.length; k++){
            if(!set.contains(k)){
                return k;
            }
        }
        return nums.length+1; //
    }
}






/*This problem is basically for number line -3 -2 -1 0 1 2 3 4 ....so lets say we are given example of      [1, 2, 0] so in this case from number line we can say the positive min is 3....another example              [1, 2, 3] now in this case the next missing is actually (arrays.length + 1) so 3+1 = 4 as the min +ve number.
https://www.youtube.com/watch?v=2QugZILS_Q8  
*/
Dry run example: in img: we return first +ve which 3 and the index for that is 2 (it is answer)

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean oneExists = false;
        
        for(int o: nums){
            if(o == 1){
                oneExists = true;
            }
        }
        if(!oneExists) return 1; //since we can say directly this is a min +ve number.
        //This for preprocessing and setting all the numbers in the array to 1 so we no more have 0 or negative numbers in the array.
        for(int i=0; i<n; i++){//replace all nums not in 1...n so we have no 0 or -ve number
            if(nums[i] <= 0 || nums[i] > n){
                nums[i] = 1;//Setting those to 1.
            }
        }
        
        for(int i=0; i<n; i++){
            int v = Math.abs(nums[i]);
            
            if(v == n){ //if it's the final element, but the array len is n-1 so we replace the 0th index.
                nums[0] = -1 * Math.abs(nums[0]);
            }
            else{
                nums[v] = -1 * Math.abs(nums[v]);
            }
        }
        
        for(int i=1; i<n; i++){
            if(nums[i] > 0) return i;//Return the index value.
        }
        if(nums[0] > 0) return n;
        
        return n + 1;          
 //remember the example [1, 2, 3] so here above the min +ve is length+1 = 3+1 = 4
    }
}




Word Ladder
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//We need to search for the transformed words in constant time, so convert List to Set.
		Set<String> wordSet = new HashSet<>(wordList);
		
		//Edge case check.
		if(!wordSet.contains(endWord))
			return 0;
		
//We will use a BFS approach. Since we can change only one letter at a time, we will go level by
//transformation.
/*
 * 							hit    ----> 0
 *  						 |
 * 							hot    ----> 1
 *  						 |
 * 							/ \
 *						  dot lot  ----> 2
 *						   |   |
 *						  dog log  ----> 3
 *						   |
 *						  cog      ----> 4
 * We are returning "level + 1", so our answer is 5, which is correct.
 */
	Queue<String> queue = new LinkedList<>();
	queue.offer(beginWord);
		
	int transformationLength = 0;
		
	while(!queue.isEmpty()) {
			//doing level traversal, so record size of queue per level
		int qSize = queue.size();
			
		for(int i = 0; i < qSize; i++) {
			String currentWord = queue.poll();
	
			//check if the currentWord is the target word
			if(currentWord.equals(endWord)) {
				return transformationLength + 1;
				}
//now we have to check permutations for each place of the string. For each place we will permute
//from 'a' to 'z'. This goes for all the indices of the String. String immutable in java, cannot
//be converted to changed. Hence convert to character array first.
				char[] wordArr = currentWord.toCharArray();
				
				for(int index = 0; index < wordArr.length; index++) {
//Need to store original character, so that the char array can be restored to the original
					//state when permutated for next index.
				char originalValueAtIndexBeforPermutation = wordArr[index];
					for(char permute = 'a'; permute <= 'z'; permute++) {
						wordArr[index] = permute;
						
						//Don't check for the originalCharacter
				if(wordArr[index] == originalValueAtIndexBeforPermutation)
							continue;
						
				String wordAfterPermutation = new String(wordArr);
//check if the permutated word exists in the wordSet. If yes, add it to the queue because
//it is an candidate for the next transformation and also remove that word from the wordSet
						//to avoid duplicate candidates.
			if(wordSet.contains(wordAfterPermutation)) {
					queue.add(wordAfterPermutation);
					wordSet.remove(wordAfterPermutation);
						}
					}
wordArr[index] = originalValueAtIndexBeforPermutation;//resetting to actual value char
				}
			}
	transformationLength++; //this marks that we have completed one level.
		}
		return 0;
    }
}





636. Exclusive Time of Functions

Input:
n = 2
logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3, 4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time. 
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
https://www.youtube.com/watch?v=VqN4cqa3vgI 
/*For this example : logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
So the execution time is calculated using lets for 1 would be  6-5+2+1 = 4 and for 0 would be 
6-5+2 = 3*/
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<Integer> stack = new Stack<>();//push function on the stack.
        int prev = 0;
        int[] res = new int[n];
        
        String[] s = logs.get(0).split(":");
        stack.push(Integer.valueOf(s[0]));//pushing initial function.
        prev = Integer.valueOf(s[2]);//get the time for that function.
        
        for(int i=1; i<logs.size(); i++){
            
            s = logs.get(i).split(":");
            
            int func = Integer.valueOf(s[0]);//get the current function everytime
            int time = Integer.valueOf(s[2]);//get the current time for every function.
            
            if(s[1].equals("start")){
                if(!stack.isEmpty()) res[stack.peek()] += time - prev; 
//above line for example to get the exec time we do (6 - 5 + 2) + 1 = 4 for the function 1
                stack.push(func);//push the function to the stack.
                prev = time;
            }
            else{//this is for end time and we calculate it current - prev_time + 1
                res[stack.pop()] += time - prev + 1;
                prev = time + 1;
            }
        }
        return res;
    }
}








Minimum Remove to Make Valid Parentheses
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:
Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
https://www.youtube.com/watch?v=thL70BR3yMA&list=PLU_sdQYzUj2keVENTP0a5rdykRSgg9Wp-&index=176 
/*We need to use string builder here to add the char to it. We do iterate and from the back to append it to StringBuilder.  While returning the answer we have to reverse the String builder and return the answer.*/
class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int open = 0;
        for(char c : s.toCharArray()){
            if(c == '('){
                open++;
            }
           else if(c == ')'){
                if(open == 0) continue; //this means the braces are balanced and no need to remove it.
                open--;
            }
            sb.append(c);
            System.out.println("String contains = "+ sb.toString()); 
        }
        System.out.println(" op = " +open);
        StringBuilder result = new StringBuilder();
        for(int i=sb.length()-1; i>=0; i--){
             System.out.println(" i = " +open);
            if(sb.charAt(i) == '(' && open-- > 0)  continue; //this means we are trying to remove the excess opening braces.
            //System.out.println(" i = " +sb.charAt(i));
            result.append(sb.charAt(i));
        }
        
        return result.reverse().toString();
    }
}



32. Longest Valid Parentheses

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
Example 1:
Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"

Example 2:
Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"



Instead of finding every possible string and checking its validity, we can make use of stack while scanning the given string to check if the string scanned so far is valid, and also the length of the longest valid string. In order to do so, we start by pushing -1 onto the stack.


For every ‘(’ encountered, we push its index onto the stack.
For every  ‘)’ encountered, we pop the topmost element and subtract the current element's index from the top element of the stack, which gives the length of the currently encountered valid string of parentheses. If while popping the element, the stack becomes empty, we push the current element's index onto the stack. In this way, we keep on calculating the lengths of the valid substrings, and return the length of the longest valid string at the end.
https://www.youtube.com/watch?v=r0-zx5ejdq0 


class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();//for storing index.
        stack.push(-1);
        int max_len = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);//push for every opening “ ( ”
            }
            else{
                stack.pop();//pop for every closing “ ) ”
                if(stack.isEmpty()){
                    stack.push(i);
                }
                else{
                    max_len = Math.max(max_len, i - stack.peek());// max(max, i - top_element)
                }
            }
        }
        return max_len;
    }
}




Moving Average from Data Stream

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
https://www.youtube.com/watch?v=-1PlYrjLz5E 
class MovingAverage {

    /** Initialize your data structure here. */
    Deque<Integer> deque;
    double sum;
    int sizeLimit;
    public MovingAverage(int size) {
        this.sizeLimit = size;
        deque = new LinkedList<>();
        sum = 0.0;
    }
    
    public double next(int val) {
        if(deque.size() == sizeLimit){
            sum -= (double)deque.removeFirst();
        }
        sum += (double)val;
        deque.add(val);
        return sum / (double)deque.size();
    }
}

Contiguous Array
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal numbers of 0 and 1.


/*here we replace 0 with -1 and 1 with +1 so when we sum them up and if we get zero then we can say that the number of zero's and one's are equal. we maintain a hashmap because if we encounter the same sum again then we take the value as 
(i - map.get(count))...this will avoid use of the another for loop.
https://www.youtube.com/watch?v=nSEO5zOwP7g  */
class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> counts = new HashMap<>();//key - sum, value - index
        counts.put(0, -1);
        int max_len = 0, count = 0;
        
        for(int i=0; i<nums.length; i++){
            if(nums[i] == 0){
                count += -1; //here adding to count as -1 when we encounter 0
            }
            else{
                count += 1;//here adding to count as +1 when we encounter 1
            }
            if(counts.containsKey(count)){
                max_len = Math.max(max_len, i - counts.get(count));
            }
            else {
                counts.put(count, i);
            }
        }
        return max_len;
    }
}


845. Longest Mountain in Array

Example 1:
Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.

Example 2:
Input: [2,2,2]
Output: 0
Explanation: There is no mountain.
/*We will keep increasing and decreasing for uphill and downhill which will decide if its the mountain and maxLength for the longest one. When we see next number greater we do increasing++ and when previous number is greater decreasing++, we then take maxLength everytime.*/

class Solution {
    public int longestMountain(int[] A) {
        int maxLength=0;
        int i=1;
        
        while(i < A.length){
            int increasing=0, decreasing=0;
            
            while(i < A.length && A[i-1] < A[i]) //uphill
            {
                i++;
                increasing++;
            }
            while(i < A.length && A[i-1] > A[i]) //downhill
            {
                i++;
                decreasing++;
            }
            
            if(increasing > 0 && decreasing > 0) maxLength = Math.max(maxLength, increasing + decreasing + 1);
            while(i < A.length && A[i-1] == A[i]) i++; //when same increment the i
        }
        return maxLength;
    }
}


 Reorganize String

Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
If possible, output any possible result.  If not possible, return the empty string.
Example 1:
Input: S = "aab"
Output: "aba"

Example 2:
Input: S = "aaab"
Output: ""


/*	
count letter appearance and store in hash[i]
find the letter with largest occurrence.
put the letter into even index number (0, 2, 4 ...) char array
put the rest into the array
*/

class Solution {
    public String reorganizeString(String S) {
        if (S == null || S.length() == 1) return S;
        
        Map<Character, Integer> dic = new HashMap<>();
        char maxChar = S.charAt(0);
        int L = S.length();
        
        // count chars in map, get max
        for (char c: S.toCharArray()) {
            dic.put(c, dic.getOrDefault(c, 0) +1);
            if (dic.get(c) > dic.get(maxChar)) {
                maxChar = c;
            }
        }
        
        if (dic.get(maxChar) > (L+1)/2) return "";
        
        int idx = 0;
        char[] ret = new char[L];
        
        //put all maxChar's into array (may not reach end of S) at even place
        while (idx < L && dic.get(maxChar) > 0) {
            ret[idx] = maxChar;
            dic.put(maxChar, dic.get(maxChar)-1);
            idx +=2;
        }
        
        // loop through dic, may go through a key where val is 0, but won't do anything
        for (Character c: dic.keySet()) {
            while (dic.get(c) > 0) {
                if (idx >= L) idx = 1; // First time it reaches L, reset it. Won't be inf loop cause not looping on idx
                ret[idx] = c;
                dic.put(c, dic.get(c)-1);
                idx += 2;
            }
        }
        return new String(ret);
    }
}


Partition Labels

A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
/*We want to first get the last indices of that character so for that we maintain a indices array. For example in the above one for “a” we will have the index value as 8, stored in the indices array. Then to keep track of our substring we keep an starting and ending variable then we will move this start and end*/

/*https://www.youtube.com/watch?v=5NCjHqx2v-k 
must watch*/
class Solution {
    public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() == 0)
            return null;
        List<Integer> output_arr = new ArrayList<>();
        int[] listIndices = new int[26];
        
        for(int i=0; i<S.length(); i++){
            listIndices[S.charAt(i) - 'a'] = i;//getting the last index here for that char
        }
        
        int start = 0;
        int end = 0;
        for(int i=0; i<S.length(); i++){
//Here we get the last index of that char and set that value to end.
            end = Math.max(end, listIndices[S.charAt(i) - 'a']);
            if(i == end){
                output_arr.add(end - start + 1);
                start = end + 1;//setting the value of start and moving to end now, which is new start.
            }
        }
        return output_arr;
    }
}





4Sum II
//This is bases on A + B + C + D = 0
So A + B = -C-D.   So here we will precompute and store the sum of A and B, then compare it with C and D.
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int a : A){
            for(int b : B){
                int s = a+b;
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        
        int res=0;
        for(int c: C){
            for(int d: D){
                int s = -c-d;
                res += map.getOrDefault(s, 0);
            }
        }
        return res;
    }
}

560. Subarray Sum Equals K

Solution 1. Brute force. We just need two loops (i, j) and test if SUM[i, j] = k. Time complexity O(n^2), Space complexity O(1). I bet this solution will TLE.
Solution 2. From solution 1, we know the key to solve this problem is SUM[i, j]. So if we know SUM[0, i - 1] and SUM[0, j], then we can easily get SUM[i, j]. To achieve this, we just need to go through the array, calculate the current sum and save number of all seen PreSum to a HashMap. Time complexity O(n), Space complexity O(n).
/*Here we keep the calculated preSum in map and then iterate to see if we find the sum or not. This concept is used in the LinkedList problem below.*/
public static int subarraySum(int[] nums, int k) {
        //edge case
        if(nums == null || nums.length == 0)
            return 0;
        
        //We will be using the sum seen so far and reduce it from the target 'k', similar to
        //two sum technique. As we are calculating the running sum, our sum will go beyond the target //and hence we are searching for 'sum - k'
        //We only need to initialize the HashMap with key 0 and value 1. This is for the case when our
 //sum equals the target itself. It can happen naturally as well in the array if we hadn't //initialized it, but we need to take care of it when it happens the first time.
 //For the first time when sum == k, we won't have an entry in the HashMap for sure. So just //handle that by pre initializing HashMap with (0,1)
        
        int sum = 0;
        int result = 0;
        Map<Integer, Integer> hmap = new HashMap<>();
        hmap.put(0,1);
        
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            
            if(hmap.containsKey(sum - k))
                result += hmap.get(sum - k);
            
            hmap.put(sum, hmap.getOrDefault(sum, 0) + 1);
        }
        return result;
    }




Remove Zero Sum Consecutive Nodes from Linked List
Example 1:
Input: head = [1,2,-3,3,1]
Output: [3,1]
Note: The answer [1,2,1] would also be accepted.

Example 2:
Input: head = [1,2,3,-3,4]
Output: [1,2,4]

Example 3:
Input: head = [1,2,3,-3,-2]
Output: [1]

 /*Similar to finding subarray sum = 0. sums keep track of prefix sums seen so far. If we find the current sum sum in the prefix sums map, then we found a subarray sum = 0 from the matching entry till the current element. So remove that portion and move on exploring other nodes.
 */
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        if(head == null)
            return null;
        HashMap<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        ListNode curr = head;
        while(curr != null){
            sum += curr.val;
            if(sum == 0)
                head = curr.next;//move head everytime to the curr.next we find the sum as zero.
            if(map.containsKey(sum)){
                map.get(sum).next = curr.next;
                return removeZeroSumSublists(head);//exploring other nodes hence call recursion.
            }
            map.put(sum, curr);//store the sum against the node.
            curr = curr.next;
        }
        return head;
    }
}





Daily Temperatures

Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
/*We use stack in this case of problems to store the index of the temperature, */
class Solution {
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> s = new Stack<>();//push index of temperature to the stack.
        int[] output_arr = new int[T.length];
        for(int i=0; i<T.length; i++){
            while(!s.isEmpty() && T[i] > T[s.peek()]){//here always comparing next with previous (on stack)
                int index = s.pop();
                output_arr[index] = i - index;//this gets the days from the difference.
            }
            s.push(i);//push to the stack.
        }
        return output_arr;
    }
}



 Peeking Iterator
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> itr;
    Integer next;
    boolean done = false;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    itr = iterator;
        if(itr.hasNext())
            next = itr.next();
        else
            done = true;
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
       return next;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
        Integer result = next;
	    if(itr.hasNext()){
            next = itr.next();
        }
        else{
            next = null;
            done = true;
        }
        return result;
	}
	
	@Override
	public boolean hasNext() {
	   return !done;
	}
}






Decode String

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
https://www.youtube.com/watch?v=0iQqj5egK9k&t=17s 

/*In many cases of parentheses we do use Stack. The logic is to have two stacks one for integer and the other for the strings. now push integer to the int one and string to the string stack. when we encounter [, ], character and number do the following actions. */
class Solution {
    public String decodeString(String s) {
        Stack<Integer> counts = new Stack();
        Stack<String> result = new Stack();
        int index = 0;
        String res = "";
        
        while(index < s.length()){
            if(Character.isDigit(s.charAt(index))){
                int count = 0;
                while(Character.isDigit(s.charAt(index))){
                    count = 10 * count + (s.charAt(index) - '0');//because we can eg: 30[a] or 3243[fd] hence 10 *..
                    index += 1;
                }
                counts.push(count);
            }
            //When opening "[" we have to add character to the string stack.
            else if(s.charAt(index) == '['){
                result.push(res); //pushing the string to the string stack.
                res = ""; //make the string result empty again. Reset.
                index += 1;
            }
//When we find the "]" now we take the number and string from both the stacks and set it to the res.            
            else if(s.charAt(index) == ']'){
                StringBuilder temp = new StringBuilder(result.pop());
                int count = counts.pop();
                for(int i=0; i<count; i++){
                    temp.append(res);
                }
                res = temp.toString();//setting the value of res.
                index += 1;
            }
            
            else{
                res += s.charAt(index);
                index += 1;
            }
        }
        return res;
    }
}

/*Same as https://leetcode.com/problems/3sum
This is similar to the 3sum problems and in this we sort the array first. Here we start left from 0 while right from i-1 since we should get all the elements from that position to until i and then go on increasing it.*/


class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int l,r;
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        for(int i=0; i<nums.length-1; i++){
            l = i+1;
            r = nums.length - 1;
            while(l < r){
                int sum = nums[l] + nums[r] + nums[i];
                if(sum > target){
                    r--;
                }
                else {
                    l++;
                }
                if(Math.abs(result - target) > Math.abs(sum - target)){
                    result = sum;
                }
            }
        }
        return result;
    }
}




611. Valid Triangle Number
Medium
899
91
Add to List
Share
Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3

/*Same as https://leetcode.com/problems/3sum-closest 
This is similar to the 3sum problems and in this we sort the array first. Here we start left from 0 while right from i-1 since we should get all the elements from that position to until i and then go on increasing it.*/

class Solution {
    public int triangleNumber(int[] nums) {
        int result = 0;
        if(nums.length < 3)
            return result;
        Arrays.sort(nums);
        for(int i=2; i<nums.length; i++){
            int left = 0, right = i-1;
            while(left < right){
                if(nums[left] + nums[right] > nums[i]){
                    result += (right - left);
                    right--;
                }
                else{
                    left++;
                }
            }
        }
        return result;
    }
}


Find Pivot Index
Input: 
nums = [1, 7, 3, 6, 5, 6]
Output: 3
Explanation: 
The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
Also, 3 is the first index where this occurs.


class Solution {
    public int pivotIndex(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum * 2 == total + nums[i]) { //this like we already have element in total but again adding that num and hence we do sum * 2.
                return i;
            }
        }
        return -1;
    }
}


Jump Game:

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 We will use Greedy Approach here to get the optimal solutions.

class Solution {
    public boolean canJump(int[] nums) {
        int max = 0;
        for(int i=0; i<nums.length; i++){
            if(i>max)return false;
            max = Math.max(nums[i]+i, max);//we need to use the local optimum solution, max of (A[i] + i) shows the biggest index we can get to.
        }
        }
        return true;
    }
}





Split a String in Balanced Strings
Balanced strings are those who have equal quantity of 'L' and 'R' characters.
Given a balanced string s split it in the maximum amount of balanced strings.
Return the maximum amount of splitted balanced strings.
 
Example 1:
Input: s = "RLRRLLRLRL"
Output: 4
Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.

Example 2:
Input: s = "RLLLLRRRLR"
Output: 3
Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.

Example 3:
Input: s = "LLLLRRRR"
Output: 1
Explanation: s can be split into "LLLLRRRR".

class Solution {
    public int balancedStringSplit(String s) {
        int res = 0, cnt = 0;
        for(int i=0; i<s.length(); ++i){
            cnt += s.charAt(i) == 'L' ? 1 : -1;
            if(cnt == 0) ++res;
        }
        return res;
    }
}





Sliding Window Maximum
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
Follow up:
Could you solve it in linear time?
Example:
Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7      5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

/*We declare deque in the case, We have to remove the last index from the deque if the value of the current elements coming are greater than in the queue, we remove the first index from the deque if the window is moving.*/
https://www.youtube.com/watch?v=fbkvdWUS5Ic 

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k > nums.length || nums == null)
            return new int[0];
        Deque<Integer> dq = new ArrayDeque<>();//storing the index in the deque.
        int[] result = new int[nums.length  - k + 1];//numsize:3, k:2 -> 3-2+1
        int i = 0;
        while(i < nums.length){
//if window size is moving we will remove the first element from the deque.            
            if(!dq.isEmpty() && dq.peekFirst() == i - k){
                dq.pollFirst();
            }
//If the incoming value is greater then we poll all the last elements.            
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]){
                dq.pollLast();
            }
            
            dq.offerLast(i);
            
            if(i >= k - 1)
                result[i-k+1] = nums[dq.peekFirst()];
            i++;
        }
        return result;
    }
}




 Reconstruct Itinerary
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.


Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

Example 2:
Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.

/*For storing the arrival and departure in the lexical order we use priority queue. We use dfs for getting the arrival and departure in the order accordingly*/

class Solution {

//declare map with key as string and value as priority queue for all the arrival & depar.
    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;
    public List<String> findItinerary(List<List<String>> tickets) {
        flights = new HashMap();
        path = new LinkedList();
        for(List<String> ticket : tickets){
            flights.putIfAbsent(ticket.get(0), new PriorityQueue());
            flights.get(ticket.get(0)).add(ticket.get(1));
        }
       System.out.println(Collections.singletonList(flights));
        dfs("JFK");
        return path;
    }
    
    public void dfs(String departure){
        PriorityQueue<String> arrivals = flights.get(departure);
        while(arrivals != null && !arrivals.isEmpty()){//need to take all the values for key.
            System.out.println("--->"+arrivals.peek());
dfs(arrivals.poll());
        }
        System.out.println("depart---"+departure); //at the end of the recursion hence addFirst
        path.addFirst(departure);
    }
}




937. Reorder Data in Log Files

The rules are:
Letter-logs come before digit-logs;
Letter-logs are sorted alphanumerically, by content then identifier;
Digit-logs remain in the same order.


Example 1:
Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
This splits into split1 and split2 as “digi1” and “8 1 5 1” respectively and so on for the other strings.

class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (s1, s2) -> {
            String[] split1 = s1.split(" ", 2);
            String[] split2 = s2.split(" ", 2);
            
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            
           
 if(!isDigit1 && !isDigit2){
                //both are letters, arrange lexicographically.
                int comp = split1[1].compareTo(split2[1]);
                if(comp == 0) return split1[0].compareTo(split2[0]);
                    else return comp;
                
            } else if(isDigit1 && isDigit2){
                // both digit-logs. So keep them in original order
                return 0;
            }
            else if(!isDigit1 && isDigit2){
                //first is letter, second is digit. keep them in this order.
                return -1;
            }
            else {
                //first is digit, second is letter bring letter to the front
                return 1;
            }
        });
        return logs;
    }
}







Find Peak Element
Solution

Example 1:
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:
Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.

I find it useful to reason about binary search problems using invariants. While there are many solutions posted here, neither of them provide (in my opinion) a good explanation about why they work. I just spent some time thinking about this and I thought it might be a good idea to share my thoughts.
Assume we initialize left = 0, right = nums.length - 1. The invariant I'm using is the following:
nums[left - 1] < nums[left] && nums[right] > nums[right + 1]
That basically means that in the current interval we're looking, [left, right] the function started increasing to left and will eventually decrease at right. The behavior between [left, right] falls into the following 3 categories:
nums[left] > nums[left + 1]. From the invariant, nums[left - 1] < nums[left] => left is a peak
The function is increasing from left to right i.e. nums[left] < nums[left + 1] < .. < nums[right - 1] < nums[right]. From the invariant, nums[right] > nums[right + 1] => right is a peak
the function increases for a while and then decreases (in which case the point just before it starts decreasing is a peak) e.g. 2 5 6 3 (6 is the point in question)
As shown, if the invariant above holds, there is at least a peak between [left, right]. Now we need to show 2 things:
I) the invariant is initially true. Since left = 0 and right = nums.length - 1 initially and we know that nums[-1] = nums[nums.length] = -oo, this is obviously true
II) At every step of the loop the invariant gets reestablished. If we consider the code in the loop, we have mid = (left + right) / 2 and the following 2 cases:
a) nums[mid] < nums[mid + 1]. It turns out that the interval [mid + 1, right] respects the invariant (nums[mid] < nums[mid + 1] -> part of the cond + nums[right] > nums[right + 1] -> part of the invariant in the previous loop iteration)
b) nums[mid] > nums[mid + 1]. Similarly, [left, mid] respects the invariant (nums[left - 1] < nums[left] -> part of the invariant in the previous loop iteration and nums[mid] > nums[mid + 1] -> part of the cond)
As a result, the invariant gets reestablished and it will also hold when we exit the loop. In that case we have an interval of length 2 i.e. right = left + 1. If nums[left] > nums[right], using the invariant (nums[left - 1] < nums[left]), we get that left is a peak. Otherwise right is the peak (nums[left] < nums[right] and nums[right] < nums[right + 1] from the invariant).

class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < nums[mid+1]){ //This like  searching  and comparing middle with left and left-1
                left = mid+1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }
}




Convert Sorted List to Binary Search Tree

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

/*Look at the list, it is the same as the inorder traversal list. Since this is sorted, we can use Binary Search. We need to first get the end of the List since we would to calculate the  mid for binary search. */

class Solution {
    ListNode node;
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)
            return null;
        node =  head;
        ListNode curr = head;
        int counter = 0;//In LinkedList need to first find the len of the list,for “end” 
        while(curr != null){
            counter++;
            curr = curr.next;
        }
        
        return sortListBinarySearch(0, counter - 1);//we use counter for end
    }
    
   public TreeNode sortListBinarySearch(int start, int end){
       if(start > end)//base condition for recursion here.
           return null;
       int mid = start + (end - start)/2;
       TreeNode left = sortListBinarySearch(start, mid - 1);//left node
       TreeNode tnode = new TreeNode(node.val);
       tnode.left = left;//setting that left to the node created
       node = node.next;
       TreeNode right = sortListBinarySearch(mid+1, end);
       tnode.right = right;//right node
       return tnode;//return the new node created for left and right
   } 
}




227. Basic Calculator II

https://www.youtube.com/watch?v=LED1RRZytzY 

/*so for example if you have 3+2*2 so we take plus at front for +3 and then 2*2 there itself and then finally add it so making prefix for ‘+’ or ‘-’ */

class Solution {
    public int calculate(String s) {
        Character operator = '+';
        int tail = 0;
        int res = 0;
        char[] chs = s.toCharArray();
        int num = 0;
        int n = chs.length;
        
        for(int i=0; i<chs.length; i++){
            char c = chs[i];
            if(c == ' ') continue;
            if(Character.isDigit(c)){
                num = (int)(c - '0');
//32 * 3 , we can have like this as well for 32 hence we need to loop thru..like 30+2 = 32
                while(i + 1 < n && Character.isDigit(chs[i + 1])){//check if i < n and also if digit.
                    num = num * 10 + (int) (chs[i + 1] - '0');
                    i++;
                } 
            
            switch(operator){
                case '+':
                   res += tail;
                   tail = num;
                   break;
                case '-':
                    res += tail;
                    tail = -num;
                    break;
                case '*':
                    tail *= num;
                    break;
                case '/':
                    tail /= num;
                    break;
                }
            }
            else{
                operator = c;
            }
        }
        return res + tail;
    }
}



415. Add Strings
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
Note:
The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.


class Solution {
    public String addStrings(String num1, String num2) {
        int carry = 0;
        int i = num1.length()-1;
        int j = num2.length()-1;
        char[] num1_array = num1.toCharArray();
        char[] num2_array = num2.toCharArray();
        StringBuilder sb = new StringBuilder();
        
        while(i >= 0 || j>=0 || carry == 1){
            int a = i>=0 ? num1_array[i--] - '0' : 0;
            int b = j>=0  ? num2_array[j--] - '0' : 0;
            int sum = a + b + carry;
            sb.insert(0, sum%10);//insert the number.
            carry = sum/10;//get the carry from the number.
        }
        return sb.toString();
    }
}




Employee Free Time

https://aaronice.gitbook.io/lintcode/sweep-line/employee-free-time 

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 
Example 1:
Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.

Example 2:
Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]

/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<Interval> pq = new PriorityQueue<Interval>((a, b) -> a.start - b.start);
        schedule.forEach(e -> pq.addAll(e));
        List<Interval> result = new ArrayList<>(); //result in the final output
        Interval temp = pq.poll();
/* [[[1,2],[5,6]],[[1,3]],[[4,10]]]
After storing it in the priority Queue the example List looks like [1,3] [4,10] [5,6]  */
        while(!pq.isEmpty()){//so below it compares 4 > 3 yes so does not intersect.
            if(temp.end < pq.peek().start){//it does not intersect hence free time.
                result.add(new Interval (temp.end, pq.peek().start));
                temp = pq.poll();//moving temp to new interval.
            }
            else{//This for intersection.// intersect or sub merged
                temp = temp.end < pq.peek().end ? pq.peek() : temp;
                pq.poll();
            }
        }
        return result;
    }
}









560. Subarray Sum Equals K
https://leetcode.com/problems/subarray-sum-equals-k/discuss/102106/Java-Solution-PreSum-%2B-HashMap 
//We will be using the sum seen so far and reduce it from the target 'k', similar to
//two sum technique. As we are calculating the running sum, our sum will go beyond the target
//and hence we are searching for 'sum - k'
//We only need to initialize the HashMap with key 0 and value 1. This is for the case when our sum equals the target itself. It can happen naturally as well in the array if we hadn't
//initialized it, but we need to take care of it when it happens the first time.

class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return 0;
        int result = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);//for the first time itself we can get it hence put(0,1)
        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            
            if(map.containsKey(sum - k)){
                result += map.get(sum - k);
            }
                map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}




28. Implement strStr()

class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0 || needle == null)
            return 0;
        
        int m = haystack.length(), n = needle.length();
        for(int i=0; i<m-n+1; i++){ //go until 
//get the substring from current i location to i + len(needle)
            if(haystack.substring(i, i+n).equals(needle))
                return i;
        }
        return -1;
        
}
}



Compare Version Numbers

Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

Input: version1 = "0.1", version2 = "1.1"
Output: -1
Example 2:
Input: version1 = "1.0.1", version2 = "1"
Output: 1
Example 3:
Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1
Example 4:
Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”


/*If version2 is smaller as compare to version1 then version2 is the latest version*/
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] level1 = version1.split("\\.");
        String[] level2 = version2.split("\\.");
        
        int length = Math.max(level1.length, level2.length);
        for(int i=0; i<length; i++){
            Integer v1 = i < level1.length ? Integer.parseInt(level1[i]) : 0;
            Integer v2 = i < level2.length ? Integer.parseInt(level2[i]) : 0;
//CompareTo method below - if they are same it returns 0, if v1 is greater then v2 it will return 1 and when v1 is less then v2 it will return -1            
            int compare = v1.compareTo(v2);            
            if(compare != 0) 
                return compare;
        }
        return 0;
    }
}


268. Missing Number
/*here we will use the sum of all n number formula = (n * (n+1)) / 2   
And when we get the total sum and then subtract each number from it we will get the missing number*/

class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = (n * (n+1))/2;
        for(int i:nums)
            sum = sum - i;
        return sum;
    }
}

125. Valid Palindrome

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
Note: For the purpose of this problem, we define empty string as valid palindrome.
Example 1:
Input: "A man, a plan, a canal: Panama"
Output: true


Example 2:
Input: "race a car"
Output: false

/*We will have to replace all the punct here, spaces and make sure everything is lower case only. Then check for a normal palindrome using the two pointer technique.*/

class Solution {
    public boolean isPalindrome(String s) {
        String word = s.toLowerCase();
        String word1 = word.replaceAll("\\p{Punct}", "").replaceAll(" ", ""); //IMPRTNT
        char[] ch = word1.toCharArray();
        int l=0,r=ch.length-1;
        while(l < r){
            if(ch[l] != ch[r])
                return false;
            l++;
            r--;
        }
        return true;
    }
}



819. Most Common Word

/*\\p{Punct} is a class in java which deals with all the punctuation and replaces it. And basically this class matches punctuation marks. i.e.!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~*/

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String word1 = paragraph.toLowerCase();
        String word = word1.replaceAll("\\p{Punct}", " ");
        System.out.println(word);
        String[] words = word.split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        List<String> bannedList = Arrays.asList(banned);//important in creating list from array string.
        for(String str : words){
            if(str.equals("")) continue;
            if(!bannedList.contains(str)){
                map.put(str, map.getOrDefault(str, 0) + 1);
            }
        }
        
        int max = Integer.MIN_VALUE;
        String output = "";
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(max < entry.getValue()){
                max = entry.getValue();
                output = entry.getKey();
            }
        }
        return output;
    }
}

43. Multiply Strings
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
Example 1:Input: num1 = "2", num2 = "3"
Output: "6"
Example 2: Input: num1 = "123", num2 = "456" 
Output: "56088"



/*The idea is to use the same multiplication when we do it manually. We need to start from the right and multiply each number of num2 with all the number of num1. We need to store the product in the a temporary and take % to get only one digit and '\' to get the carry part of it as well. Finally we need to check for the leading zero's and have a String Builder to store the final string and return it.*/

https://www.youtube.com/watch?v=hDueaAjITi4



class Solution {
    public String multiply(String num1, String num2) {
        char[] chs1 = num1.toCharArray();
        char[] chs2 = num2.toCharArray();
        int n1 = chs1.length, n2 = chs2.length;
        char[] res = new char[n1+n2];
        Arrays.fill(res, '0');//initialize the array with 0 at the start.
        for(int j=n2-1; j>=0; j--){
            for(int i=n1-1;i>=0; i--){
                int product = (chs1[i] - '0') * (chs2[j] - '0');//getting the product of the first numbers
                int temp = (res[i+j+1] - '0') + product;//storing in temp since we need to % and keep the carry.
                res[i+j+1] = (char) (temp % 10 + '0');//getting only one digit and store that in the result char array.
                res[i+j] = (char) ((res[i+j] - '0' + temp / 10) + '0'); //this is adding the carry to then next number.
            }
        }
        
        StringBuilder sb = new StringBuilder();
        boolean seen = false;
        for(char c : res){
            if(c == '0' && !seen) continue;
            sb.append(c);
            seen = true;
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
}



























137. Single Number II

https://github.com/bhushanpawar007/Leetcode_By_Companies/blob/master/Leetcode_By_Companies/src/bitwise_operator/ThriceOccurringAndSingleElement.java


Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
Example:
Input:  [1,2,1,3,2,5]
Output: [3,5]

/*Basically the logic is to count the number of 1’s column wise and then do mod 3 when get that as zero we have to perform XOR on the BitIndex */
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        
        final int intIndex = 32;
        
        for(int i=0; i<intIndex; i++){
            int sumOfOneColumnWise = 0;
            int bitIndex = (1 << i); //left shift operator
            
            for(int num : nums)
                if((num & bitIndex) == 0)
                    sumOfOneColumnWise++;
            if(sumOfOneColumnWise % 3 == 0)
                result = result | bitIndex; //XOR operation if same then 1 else 0
        }
        return result;
    }
}



169. Majority Element

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.
Example 1:
Input: [3,2,3]
Output: 3
Example 2:
Input: [2,2,1,1,1,2,2]
Output: 2
https://www.youtube.com/watch?v=n5QY3x_GNDg 

//This is using the boyre algo and in this the candidate is the final answer. We have to set the candidate to first element at start. If we find that element is same as the previous element we can increase the count. If its different we decrement the count. When the count is zero we make candidate as new element and then increase the count.

class Solution {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int countOfCandidate = 1;
        
        for(int i=1; i<nums.length; i++){
            if(countOfCandidate == 0){
                candidate = nums[i];
                countOfCandidate++;
            }
            else if(candidate == nums[i]){
                countOfCandidate++;
            }
            else countOfCandidate--;
        }
        return candidate;
    }
}




151. Reverse Words in a String

Given an input string, reverse the string word by word.
 
Example 1:
Input: "the sky is blue"
Output: "blue is sky the"

Example 2:
Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
/*The idea is to reverse the entire string first and reverse every word by word  Make sure you remove the excess  space using -- s.replaceAll("\\s+", " ");*/
class Solution {
    public String reverseWords(String s) {
        if(s == null || s.length() == 0)
            return "";
        String result = "";
        s = s.replaceAll("\\s+", " ");
        System.out.println(s);
        for(int i=s.length()-1; i>=0; i--){ //reverse the entire string.
            result += s.charAt(i);
        }
        
        String[] final_result = result.split(" ");
        String output = "";
        for(String str : final_result){//reveresing word by word and for each word rev char’s
            System.out.println("1. "+str);
            int i = str.length()-1;
            while(i >= 0){
                if(str.charAt(i) == ' ') continue;
                output +=str.charAt(i);
                i--;
            }
            output += " ";//since we would need space after each word.
        }
        //System.out.println(output);
        return output.trim();
    }
}



127. Word Ladder
/*We take the current word, convert it into char array. We need to loop thru this char array, now within this loop we check char by char with permutation. We check that word in the WordSet dictionary if present add to the DFS queue and remove from WordSet. 
So for example we have first word as ‘hit’ so making that into 
char_array = [‘h’, ‘i’, ‘t’] and then taking permutation of this as [‘a’ , ‘i’ , ‘t’] so the string would become “ait” and we check this word in the WordSet dictionary if not then we set [‘b’ , ‘i’ , ‘t’] → “bit”and check again in WordSet we repeat this same process.*/

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord))
            return 0;
        Queue<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        int transformationLength = 0;
        
        while(!queue.isEmpty()){
            int qSize = queue.size();//doing level traversal, so record size of queue per level
            
            for(int i=0; i<qSize; i++){
                String currentWord = queue.poll();
                //check if the currentWord is the target word
                if(currentWord.equals(endWord))
                    return transformationLength + 1;



//now we have to check permutations for each place of the string. For each place we will permute.//from 'a' to 'z'. This goes for all the indices of the String. String immutable in java, cannot//be converted to changed. Hence convert to character array first.



 
                char[] wordArr = currentWord.toCharArray();
                
                for(int index=0; index<wordArr.length; index++){
//Need to store original character, so that the char array can be restored to the original
//state when permutated for next index.
                    char originalCharBeforePermutation = wordArr[index];
                    
                    for(char permute = 'a'; permute <= 'z'; permute++){
                        wordArr[index] = permute;
                        //Don't check for the originalCharacter
                        if(wordArr[index] == originalCharBeforePermutation)
                            continue;
                        
                        String wordAfterPermutation = new String(wordArr);
//check if the permutated word exists in the wordSet. If yes, add it to the queue because
//it is an candidate for the next transformation and also remove that word from the wordSet
//to avoid duplicate candidates.                        
                        if(wordSet.contains(wordAfterPermutation)){
                            queue.add(wordAfterPermutation);
                            wordSet.remove(wordAfterPermutation);
                        }
                    }
                    wordArr[index] = originalCharBeforePermutation;
                }
                
            }
//this marks that we have completed one level.
            transformationLength++;//completed in the while loop out of the every for loop.
        }
        return 0;
    }
}



88. Merge Sorted Array
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
Note:
The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
/*We take two pointers and start from the back of both arrays and also fill the nums1 array from back only.*/

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m-1;
        int q = n-1;
        int r = m+n-1;
        
        while((p >= 0) && (q>=0)){
            nums1[r--] = nums1[p] > nums2[q] ? nums1[p--] : nums2[q--];
        }
        System.arraycopy(nums2, 0, nums1, 0, q+1);
    }
}


621. Task Scheduler
there is a non-negative integer n that represents the cool down period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
You need to return the least number of units of times that the CPU will take to finish all the given tasks.


Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: 
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.

Example 2:
Input: tasks = ["A","A","A","B","B","B"], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
And so on.


/*Idea is to first get the frequency of each character in the array. We need to sort our map. Now suppose everything is filled with idle_slot, now we have to subtract or reduce the idle_slot and fill it with the units(alphabet basically). Finally if the idle_slots are greater then zero we can return the idle_slot + length otherwise just the length. So everything has a idle_slot and we need to fill that with character like reverse way.*/

https://www.youtube.com/watch?v=eGf-26OTI-A 

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] char_map = new int[26];
        
        for(char c : tasks){
            char_map[c - 'A']++;
        }
        Arrays.sort(char_map);
        int max_value = char_map[25] - 1;//-1 here because we don't care of the very last 
        int idle_slots = max_value * n;
//Reducing the idle_slot since we need to reduce the value and replace it with char’s.     
        for(int i=24; i>=0; i--){
            idle_slots -= Math.min(char_map[i], max_value);
        }
        
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
    }
}

287. Find the Duplicate Number

/*
 * Algorithm similar to detecting loop in a LinkedList with fast and slow pointers. Since, all the array
 * elements are indexed in the range, there is a cycle present if we jump through indices using values
 * with fast and slow pointer.
 * First we will detect if there exists a cycle in the array. Similar to LinkedList's fast ptr which
 * advances fast.next.next, in our array we will jump two indexes arr[value at[value at index]].
 * For slow ptr which goes slow.next, in array it will go arr[value at index]
 */

class Solution {
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length == 0)
            return -1;
        
        if(nums.length == 1)
            return nums[0];
//using Floyd's Hare and Tortoise algorithm.
//tortoise goes nums[value at index]
        int tortoise = nums[0];
//hare goes twice the speed of tortoise. two index jumps.
//nums[value at [value at index]]
        int hare = nums[nums[0]];
//first detect if there is a cycle and where do hare and tortoise meet.      
        while(tortoise != hare){
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        }
//now make hare 'zero' and advance each of the animals one at a time. Keep the tortoise where we proved. //cycle exists.
//their meeting point is duplicate entry and the entrance point to the cycle.        
        hare = 0;
        
        while(tortoise != hare){
            tortoise = nums[tortoise];
            hare = nums[hare];
        }
        return tortoise;
    }
}





441. Arranging Coins
Example 1:
n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.

Example 2:
n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
/*reduce the coins every for that level hence we do n -= i and i += 1 is for increasing the levels.*/

Solution : 1

public class Solution {
    public int arrangeCoins(int n) {
        int i=0;
        while(n > 0){
            i+=1;
            n-=i;
        }
        
        return n==0 ? i : i-1;
        
    }
}




Solution : 2

class Solution {
    public int arrangeCoins(int n) {
        if(n == 0)
            return 0;
        int coins = n;
        for(int i=1; i<=(coins/2+1); i++){
            int j = 1;
            while(j <= i){
                j++;
                n--;
            }
            if(n < i+1)
                return i;
            else continue;
        }
        return 0;
    }
}

279. Perfect Squares
/*In this case we will do DP,  basically the dp in the array stores the solution. We need to subtract the i from “j * j” everytime in dp to get the minimum. Think of this as         current - sum to get the min value from it. To make sure it does not goes array index out of bound we do “j*j <= i” in the inner for loop.*/
public static int numSquares(int n) {
        if(n <= 3)
            return n;
        
        int possiblePerfectSquare = (int) Math.sqrt(n);
        
        //Example: 25 = 5's square (5x5), so directly return 1
        if(possiblePerfectSquare * possiblePerfectSquare == n)
        	return 1;
        
        //for every positive integer, it can be represented as sum of 1 squares.
        //Ex: 7 = 1 + 1 + 1 + 1 + 1 + 1 + 1, 7 times, so this is the worst case for every element.
        //We need to minimize this.
        
        int[] dp = new int[n + 1]; //elements at dp[i] represent the number of elements required to
        //sum up that number, which is represented by its index.
        
        dp[0] = 0;
        dp[1] = 1; // 1 = 1. In terms of perfect square addition
        dp[2] = 2; // 2 = 1 + 1. No other combination is possible
        dp[3] = 3; // 3 = 1 + 1 + 1. No other combination is possible
        
        for(int i = 4; i <= n; i++){
            dp[i] = i; //starting with the worst case of number of 1's square
            //Since we need to add only perfect square count, we look back into our dp array which has stored
            //only perfect square count for that particular number represented by index.
            //So we reduce (j x j) from i to get the perfect square count. jxj cannot be greater than 'i'
            //because we will run out of array index bounds.
            
            //So first j = 1, so it will be [i - 1], that is we look at the value right before 'i'
            //then j = 2, so we will look at the value from the index[i -jxj]
            //in this fashion we use the sub problems to solve the bigger problem, so dynammic programming
            for(int j = 1; j * j <= i; j++){
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        
        return dp[n];
    }



1041. Robot Bounded In Circle
Input: "GGLLGG"
Output: true
Explanation: 
The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.

Example 2:
Input: "GG"
Output: false
Explanation: 
The robot moves north indefinitely.

class Solution {
    public boolean isRobotBounded(String instructions) {
        //North = 1, East = 2, South = 3, West = 4
        int dir = 1;
        int y = 0;
        int x = 0;
        for(int i = 0; i < instructions.length(); i++) {
            char c = instructions.charAt(i);
//When L and dir == 1 then its 4 & dir--
            if(c == 'L') {
                if(dir == 1) dir = 4;
                else dir--;
            }
//When R and dir == 4 then its 1 & dir++           
            else if(c == 'R') {
                if(dir == 4) dir = 1;
                else dir++;
            }
            else {
                if(dir == 1) y++;
                else if(dir == 2) x++;
                else if(dir == 4) x--;
                else if(dir == 3) y--;
            }
        }
        if(dir == 1) {
            if(y == 0 && x == 0) return true;
            return false;
        }
        return true;
    }
}



166. Fraction to Recurring Decimal

Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:
Input: numerator = 2, denominator = 1
Output: "2"
Example 3:
Input: numerator = 2, denominator = 3
Output: "0.(6)"


/*We need to handle the edge condition for the negative values at first and then get the divisor & dividend. We will use the HashMap to store the remainder and indexNumber (which is the length of the string builder). When the remainder is not zero then we loop thru it to store in the map and then append it to the stringBuilder*/

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) return "0";
        StringBuilder sb = new StringBuilder();
        if(numerator < 0 && denominator > 0 ||
           numerator > 0 && denominator < 0)
            sb.append("-");
        long divisor = Math.abs((long) numerator);
        long dividend = Math.abs((long) denominator);
        long remainder = divisor % dividend;
        sb.append(divisor/dividend);
        
        if(remainder == 0)
            return sb.toString();
        sb.append(".");
        HashMap<Long, Integer> mapToStoreRemainderIndex = new HashMap<>();
        while(remainder != 0){
            if(mapToStoreRemainderIndex.containsKey(remainder)){
                sb.insert(mapToStoreRemainderIndex.get(remainder), "(");
                sb.append(")");
                break;
            }
//adding the remainder and length of stringBuilder here            
            mapToStoreRemainderIndex.put(remainder, sb.length());
            remainder *= 10;//since we continue to divide by dividend we need to multiply by 10
            sb.append(remainder/dividend);
            remainder %= dividend;//updating the remainder everytime
        }
        return sb.toString();
    }
}












73. Set Matrix Zeroes


/*We need to traverse the matrix by col and row. We keep two marker row=false and col=false, when we encounter any "0" then we set the start of the row and column as zero. Then we traverse all the rows and columns separately to check for the zero we marked, after which we set that particular column and rows to zero.*/
class Solution {
    public void setZeroes(int[][] matrix) {
        boolean row = false, col = false;
        int m = matrix.length, n = matrix[0].length;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
//setting the start of row & col as zero at start.                
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                    if(i == 0) row = true;
                    if(j == 0) col = true;
                }
            }
        }
//setting the respective row to zero         
        for(int i=1; i<m; i++){
            if(matrix[i][0] == 0){
                for(int j=1; j<n; j++)
                matrix[i][j] = 0;
            }
        }
        
//setting the respective col to zero                 
        for(int j=1; j<n; j++){
            if(matrix[0][j] == 0){
                for(int i=1; i<m; i++)
                matrix[i][j] = 0;
            }
        }
//this is when only certain row is zero
        if(row){
            for(int j=0; j<n; j++)
                matrix[0][j] = 0;
        }
        
//this is when only certain col is zero      
        if(col){
            for(int i=0; i<m; i++)
                matrix[i][0] = 0;
        }
    }
}



977. Squares of a Sorted Array

/*We will use two pointer here and compare from left and right. If left is greater then right store the square of it in the result array, then we do l++ otherwise r--*/

class Solution {
    public int[] sortedSquares(int[] A) {
        int[] result = new int[A.length];
        int n = A.length;
        int l=0, r=n-1;
        for(int i=n-1; i>=0; i--){
            if(Math.abs(A[l]) > Math.abs(A[r])){
                result[i] = A[l] * A[l];
                l++;
            }
            else {
                result[i] = A[r] * A[r];
                r--;
            }
        }
        return result;
    }
}








/*Scrible Tile game - bloomberg*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
//"abcde" => char[] => ['a', 'b', 'c', 'd', 'e']
//["be", "bed", "a", "bead"]
// root
//  b   a 
// e e
//     a
//      d 

public class Solution {
    static List<String> result = new ArrayList();
    static final int SIZE = 26;
static class TrieNode{
    TrieNode[] child = new TrieNode[SIZE];
    boolean leaf;
    public TrieNode(){
        leaf = false;
        for(int i=0; i<SIZE; i++)
            child[i] = null;
    }
}

    static void insert(TrieNode root, String Key){
        int n = Key.length();
        TrieNode pChild = root;
        
        for(int i=0; i<n; i++){
            int index = Key.charAt(i) - 'a';
            
            if(pChild.child[index] == null)
            pChild.child[index] = new TrieNode();
            
            pChild = pChild.child[index];
        }
        pChild.leaf = true;
    }
    
    static void searchWord(TrieNode root, boolean Hash[], String str){
        if(root.leaf == true){
         System.out.println(str);
         result.add(str);
        }
        
        for(int K=0; K<SIZE; K++){
            if(Hash[K] == true && root.child[K] != null)
            {
                char c = (char) (K + 'a');
                
                searchWord(root.child[K], Hash, str+c);
            }
        }
    }
    
    static void getAllWords(char[] Arr, TrieNode root, int n){
        boolean[] hash = new boolean[SIZE];
        for(int i=0; i<n; i++)
            hash[Arr[i] - 'a'] = true;
            
            TrieNode pChild = root;
            
            String str = "";
            
            for(int i=0; i<SIZE; i++){
                if(hash[i] == true && pChild.child[i] != null)
                {
                    str = str+(char)(i + 'a');
                    searchWord(pChild.child[i], hash, str);
                    str = "";
                }
            }
    } 
    
    public static void main(String args[] ) throws Exception {
       
        TrieNode root = new TrieNode();
        String Dict[] = {"go", "bat", "me", "eat", "goal", "boy", "run"};
        char[] arr = {'e', 'o', 'b', 'a', 'm', 'g', 'l'};
        int n = Dict.length;
        for(int i=0; i<n; i++)
        insert(root, Dict[i]);
        int N = arr.length;
        //N is the length of the character array.
        getAllWords(arr, root, N);
        int max = Integer.MIN_VALUE;
        String resultWord = "";
        for(String str : result){
            if(max < str.length()){
                max = str.length();
                resultWord = str;
            }
                
        }
        System.out.println("The max word is :" + resultWord + " the max points you get are " + max);
    }
}




785. Is Graph Bipartite?


/*Our goal is trying to use two colors to color the graph and see if there are any adjacent nodes having the same color.
Initialize a color[] array for each node. Here are three states for colors[] array:
0: Haven't been colored yet.
1: Blue.
-1: Red.
For each node,

If it hasn't been colored, use a color to color it. Then use the other color to color all its adjacent nodes (DFS).
If it has been colored, check if the current color is the same as the color that is going to be used to color it.
DFS Solution:
*/
class Solution {
    public boolean isBipartite(int[][] graph) {
        int nodes = graph.length;
        int[] colors = new int[nodes];
        
        for(int i=0; i<nodes; i++){
            if(colors[i] == 0 && !dfs(graph, colors, 1, i)){
                return false;
            }
        }
        return true;
    }
    
    public static boolean dfs(int[][] graph, int[] colors, int color, int node){
        if(colors[node] != 0)
            return colors[node] == color; //if the color matches then true else false
        else
        {
            colors[node] = color;
            for(int neigh : graph[node]){
                if(!dfs(graph, colors, -color, neigh))
                    return false;
            }
        }
        return true;
    }
}



