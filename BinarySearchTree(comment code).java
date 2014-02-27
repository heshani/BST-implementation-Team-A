// Date 	: 27.Feb.2014
// Programme 	: BinarySearchTree Implementation
// Author 	: Team'A'-Ashvini, Heshani, Himashika, Piumee, Sachiththa
// Description  : BST is implemented with insert, delete and search operations that can be performed(run) in a 
//                console window


import java.io.*;
import java.util.*;

class Node
{
	int ISBN;
	String bookTitle;
	String firstName;
	String surName;

	Node leftChild;
	Node rightChild;

	Node(int ISBN, String bookTitle, String firstName, String surName)
	{
		this.ISBN=ISBN;
		this.bookTitle=bookTitle;
		this.firstName=firstName;
		this.surName=surName;
	}

	public String toString()
	{
		return "ISBN "+ISBN+" of book "+bookTitle+" written by: "+firstName+" "+surName;
	}


}

public class BinarySearchTree
{
		Node root;

    public void insertNode(int ISBN,String bookTitle, String firstName, String surName)
    {



		Node newNode =new Node(ISBN,bookTitle,firstName,surName);

		if(root==null){

			root=newNode;System.out.println("**Node "+ISBN+" is Root \n");

		}else {

			Node tempNode=root;

			Node parent;

			while(true){

				parent=tempNode;

				if(ISBN < tempNode.ISBN){

					tempNode = tempNode.leftChild;

					if(tempNode==null){

						parent.leftChild=newNode;System.out.println("**Node "+ISBN+" is left child of Node "+parent.ISBN+"\n");

						return;
					}
				}else{

					tempNode=tempNode.rightChild;

					if (tempNode==null){
						parent.rightChild = newNode;System.out.println("**Node "+ISBN+" is right child of Node "+parent.ISBN+"\n");
						return;
					}
				}

			}
		}



    }


	public void inOrderTraverseTree(Node tempNode)	//passes a node that is to be traversed
	{
	  	if (tempNode != null)	//checks whether the node has values
	  	{
	 		inOrderTraverseTree(tempNode.leftChild); 	//locates the left most child of tempNode

	 		System.out.println(tempNode);	//visit tempNode

	        	inOrderTraverseTree(tempNode.rightChild);	//locates the right most child of tempNode
        	}
	}


	public Node searchNode(int ISBN)
	{
		Node tempNode = root;	//starts to search from the top of the tree

		while (tempNode.ISBN != ISBN)
		{
			if (ISBN < tempNode.ISBN) //checks whether the ISBN passed to the method is less than the ISBN of the current node
			{
				tempNode = tempNode.leftChild;	//moves the current node to the left
			}
	        else
	        {
	        	tempNode = tempNode.rightChild;	//moves the current node to the right
	        }

	        if (tempNode == null)	//node not found
	        {
	       	 	return null;	
	        }

	     }

        return tempNode;

    }



	public boolean removeNode(int ISBN)   /*passing the node(ISBN) to be removed*/
	{
		Node tempNode = root;
		Node parent = root;


		boolean isItALeftChild = true;  /* looking the node to the left*/


    	while (tempNode.ISBN != ISBN)   /*while loop runs till the exact node is found*/
		{
			parent = tempNode;

	        if (ISBN < tempNode.ISBN)  /*looking for the node in left side*/
			{
				isItALeftChild = true; /*setting to true*/
	            tempNode = tempNode.leftChild;   /*setting the the temp node to the temp node of the left child*/
			}
			else                           /* if the node is not in the left side looking for the right side*/  
			{
				isItALeftChild = false;      /*not a left child*/
				tempNode = tempNode.rightChild; /*setting the the temp node to the temp node of the right child*/
			}

			if (tempNode == null)       /*node not found*/
	    		return false;
		}


		if (tempNode.leftChild == null && tempNode.rightChild == null) /*if the node doesnt have any children*/
		{
			if (tempNode == root)
	        	root = null;                 /*setting the root to null*/

			else if (isItALeftChild)
	            parent.leftChild = null;        /*setting to null (deleting) if they do not have any children*/

			else
	           	parent.rightChild = null;   /*setting to null (deleting) if they do not have any children*/
	 	}

	 	else if (tempNode.rightChild == null)   /*no right child*/
		{
			if (tempNode == root)
	        root = tempNode.leftChild;            /*root is equal to temp node left child*/

			else if (isItALeftChild)
	        parent.leftChild = tempNode.leftChild;  /*parent left child is equal to temp node left child*/

	       	else
	        parent.rightChild = tempNode.leftChild;  /*parent right child is equal to temp node left child*/
	 	}

		else if (tempNode.leftChild == null)  /*no left child*/
		{
			if (tempNode == root) 
	        root = tempNode.rightChild;         /*root is equal to temp node right child*/

	       	else if (isItALeftChild)
	        parent.leftChild = tempNode.rightChild;  /*parent left child is equal to temp node right child*/

	        else
	        parent.rightChild = tempNode.rightChild;    /*parent right child is equal to temp node right child*/
		}

		else
		{
			Node replacement = getReplacementNode(tempNode);  /*replacing node*/

			if (tempNode == root)          /*if the temp node is equal to root it should ne replaced by roots right child*/       
	        root = replacement;
                                                                                                                                             
			else if (isItALeftChild)
	        parent.leftChild = replacement;      /*left child is equal to the replacement*/

			else
	        parent.rightChild = replacement;      /*right child is equal to the replacement*/  
			replacement.leftChild = tempNode.leftChild;  /*replacement has a left child it is needed to be set as temp node left child*/
		}

		return true;         /*returning true(boolean)*/
	}


	public Node getReplacementNode(Node replacedNode) 
	{

		Node replacementParent = replacedNode; 
		Node replacement = replacedNode;
		Node tempNode = replacedNode.rightChild; /*right child of the replaced node eaqals to temperary node*/
		
		while (tempNode != null)/*no temperary node*/
		{
			replacementParent = replacement; /*parent replacement eaqals to replacement*/
			replacement = tempNode;   /*replacement eaqals to temperary node*/
			tempNode = tempNode.leftChild; /*temperary node eaqals left child of the temperary node*/
		}


		if (replacement != replacedNode.rightChild) /*if replacement is eaqals to right child of the replaced node*/
		{                                                         
			replacementParent.leftChild = replacement.rightChild; /*left child of the parent node replacement eaqals to right child of replacement*/
			replacement.rightChild = replacedNode.rightChild;/*right child of the replacement eaqals to right child of replaced node*/
		}


		return replacement;  /*replacement is returning*/
	}


	public static void main(String args[])
	{

		BinarySearchTree theTree=new BinarySearchTree();   //creating an object(theTree) from the class(BinarySearchTree)


		try
		{

			DataInputStream in=new DataInputStream(System.in);  //creating an instance of DataInputStream, to read user's input from the console window

			int ii;

						for(int i=1;i<=5;i++)	//5 records can be inserted
						{
							System.out.println("");
							System.out.println("Record: "+i);

							//reads user inputs
							System.out.print("Insert ISBN: ");
							int isbn=Integer.parseInt(in.readLine());	//reads ISBN

							System.out.print("Insert Book Title: ");
							String title=in.readLine();	//reads bookTitle

							System.out.print("Insert First Name: ");
							String fName=in.readLine();	//reads firstName

							System.out.print("Insert Surname: ");
							String lName=in.readLine();	//reads surName



							theTree.insertNode(isbn,title,fName,lName);	//insertNode method is called
						}

						System.out.println("Available nodes in the binary tree:");
						theTree.inOrderTraverseTree(theTree.root);	//display all records by the ISBN according to inOrder treaversal
						

						while(true)
						{

							System.out.println("");
							System.out.println("**Select an operation to be performed**");
							System.out.println("* Press '1' to INSERT a new node.");
							System.out.println("* Press '2' to SEARCH a node.");
							System.out.println("* Press '3' to DELETE a node.");
							System.out.println("* Press '0' to EXIT.");


							ii=Integer.parseInt(in.readLine());

							//asks for the user's choice till the programme terminates
							switch(ii)
							{
								
								//inserts another new record 
								case 1:


									System.out.print("Insert ISBN: ");
									int isbn=Integer.parseInt(in.readLine());

									System.out.print("Insert Book Title: ");
									String title=in.readLine();

									System.out.print("Insert First Name: ");
									String fName=in.readLine();

									System.out.print("Insert Surname: ");
									String lName=in.readLine();


									theTree.insertNode(isbn,title,fName,lName);

									theTree.inOrderTraverseTree(theTree.root);

								break;

								//search for a book
								case 2:

									System.out.println("\n");
									System.out.print("Search book with ISBN: ");
									isbn=Integer.parseInt(in.readLine());	//reads the ISBN to be searched
									System.out.println(theTree.searchNode(isbn));	//searchNode method is called


								break;

								//delete a book
								case 3:

									System.out.println("\n");
									System.out.print("Delete book with ISBN: ");
									int disbn=Integer.parseInt(in.readLine());	//reads the ISBN to be deleted
									System.out.println(theTree.removeNode(disbn));	//removeNode method is called

									System.out.println("");
									System.out.println("Remaining nodes in the tree:");
									theTree.inOrderTraverseTree(theTree.root);

								break;


								//exit from the programme
								case 0: System.exit(0);
								break;


							}//end of witch


						}//end of while


		}

		catch(Exception e)
		{

		}
	}
}

