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


	public void inOrderTraverseTree(Node tempNode)
	{
	  	if (tempNode != null)
	  	{
	 		inOrderTraverseTree(tempNode.leftChild);

	 		System.out.println(tempNode);

	        inOrderTraverseTree(tempNode.rightChild);
        }
	}


	public Node searchNode(int ISBN)
	{
		Node tempNode = root;

		while (tempNode.ISBN != ISBN)
		{
			if (ISBN < tempNode.ISBN)
			{
				tempNode = tempNode.leftChild;
			}
	        else
	        {
	        	tempNode = tempNode.rightChild;
	        }

	        if (tempNode == null)
	        {
	       	 	return null;
	        }

	     }

        return tempNode;

    }



	public boolean removeNode(int ISBN)
	{
		Node tempNode = root;
		Node parent = root;


		boolean isItALeftChild = true;


    	while (tempNode.ISBN != ISBN)
		{
			parent = tempNode;

	        if (ISBN < tempNode.ISBN)
			{
				isItALeftChild = true;
	            tempNode = tempNode.leftChild;
			}
			else
			{
				isItALeftChild = false;
				tempNode = tempNode.rightChild;
			}

			if (tempNode == null)
	    		return false;
		}


		if (tempNode.leftChild == null && tempNode.rightChild == null)
		{
			if (tempNode == root)
	        	root = null;

			else if (isItALeftChild)
	            parent.leftChild = null;

			else
	           	parent.rightChild = null;
	 	}

	 	else if (tempNode.rightChild == null)
		{
			if (tempNode == root)
	        root = tempNode.leftChild;

			else if (isItALeftChild)
	        parent.leftChild = tempNode.leftChild;

	       	else
	        parent.rightChild = tempNode.leftChild;
	 	}

		else if (tempNode.leftChild == null)
		{
			if (tempNode == root)
	        root = tempNode.rightChild;

	       	else if (isItALeftChild)
	        parent.leftChild = tempNode.rightChild;

	        else
	        parent.rightChild = tempNode.rightChild;
		}

		else
		{
			Node replacement = getReplacementNode(tempNode);

			if (tempNode == root)
	        root = replacement;

			else if (isItALeftChild)
	        parent.leftChild = replacement;

			else
	        parent.rightChild = replacement;
			replacement.leftChild = tempNode.leftChild;
		}

		return true;
	}


	public Node getReplacementNode(Node replacedNode)
	{

		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		Node tempNode = replacedNode.rightChild;

		while (tempNode != null)
		{
			replacementParent = replacement;
			replacement = tempNode;
			tempNode = tempNode.leftChild;
		}


		if (replacement != replacedNode.rightChild)
		{
			replacementParent.leftChild = replacement.rightChild;
			replacement.rightChild = replacedNode.rightChild;
		}


		return replacement;
	}


	public static void main(String args[])
	{

		BinarySearchTree theTree=new BinarySearchTree();


		try
		{

			DataInputStream in=new DataInputStream(System.in);

			int ii;

						for(int i=1;i<=5;i++)
						{
							System.out.println("");
							System.out.println("Record: "+i);


							System.out.print("Insert ISBN: ");
							int isbn=Integer.parseInt(in.readLine());

							System.out.print("Insert Book Title: ");
							String title=in.readLine();

							System.out.print("Insert First Name: ");
							String fName=in.readLine();

							System.out.print("Insert Surname: ");
							String lName=in.readLine();



							theTree.insertNode(isbn,title,fName,lName);
						}

						System.out.println("Available nodes in the binary tree:");
						theTree.inOrderTraverseTree(theTree.root);

						//************************************************************************************


						while(true)
						{

							System.out.println("");
							System.out.println("**Select an operation to be performed**");
							System.out.println("* Press '1' to INSERT a new node.");
							System.out.println("* Press '2' to SEARCH a node.");
							System.out.println("* Press '3' to DELETE a node.");
							System.out.println("* Press '0' to EXIT.");


							ii=Integer.parseInt(in.readLine());

							switch(ii)
							{

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


								case 2:

									System.out.println("\n");
									System.out.print("Search book with ISBN: ");
									isbn=Integer.parseInt(in.readLine());
									System.out.println(theTree.searchNode(isbn));


								break;


								case 3:

									System.out.println("\n");
									System.out.print("Delete book with ISBN: ");
									int disbn=Integer.parseInt(in.readLine());
									System.out.println(theTree.removeNode(disbn));

									System.out.println("");
									System.out.println("Remaining nodes in the tree:");
									theTree.inOrderTraverseTree(theTree.root);

								break;



								case 0: System.exit(0);
								break;


							}//end switch


						}//end while


		}

		catch(Exception e)
		{

		}
	}
}

