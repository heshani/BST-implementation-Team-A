/**
 * @(#)Text1.java
 *
 *
 * @author heshani
 * @version 1.00 2014/2/25
 */
import java.io.*;

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
		return "ISBN "+ISBN+" of book "+bookTitle+" is written by: "+firstName+" "+surName;
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
			char input;

						for(int i=1;i<=5;i++)
						{
							System.out.println("");
							System.out.println("Record: "+i);


							System.out.println("Insert ISBN:");
							int isbn=Integer.parseInt(in.readLine());

							System.out.println("Insert Book Title:");
							String title=in.readLine();

							System.out.println("Insert First Name:");
							String fName=in.readLine();

							System.out.println("Insert Surname:");
							String lName=in.readLine();



							theTree.insertNode(isbn,title,fName,lName);
						}

						System.out.println("Available nodes in the binary tree:");
						theTree.inOrderTraverseTree(theTree.root);

						//System.out.println("Enter Input:");
						input=(char)System.in.read();

						//while(input!='e')
						//{


							//if(input=='s')
							//do
							//{


							System.out.println("\n");
							System.out.println("Search book with ISBN:");
							int isbn=Integer.parseInt(in.readLine());
							System.out.println(theTree.searchNode(isbn));
							//}while(input=='s');

							//if(input=='d')
							//do
							//{
							System.out.println("\n");
							System.out.println("Delete book with ISBN:");
							int disbn=Integer.parseInt(in.readLine());
							System.out.println(theTree.removeNode(disbn));
							//}while(input=='d');

							System.out.println("");
							System.out.println("Remaining nodes in the tree:");
							theTree.inOrderTraverseTree(theTree.root);
						//}


		}

		catch(Exception e)
		{

		}
	}
}

