/**
 * @(#)Text1.java
 *
 *
 * @author
 * @version 1.00 2014/2/19
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

			root=newNode;

		}else {

			Node tempNode=root;

			Node parent;

			while(true){

				parent=tempNode;

				if(ISBN < tempNode.ISBN){

					tempNode = tempNode.leftChild;

					if(tempNode==null){

						parent.leftChild=newNode;

						return;
					}
				}else{

					tempNode=tempNode.rightChild;

					if (tempNode==null){
						parent.rightChild = newNode;
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

		/*System.out.println("Enter your choice:");
		System.out.println("'i' to INSERT Nodes.");
		System.out.println("'s' to SEARCH for Node.");
*/
		try
		{

			DataInputStream in=new DataInputStream(System.in);
			/*char input;
			input=(char)System.in.read();*/
			//switch(input)

		/*	int command;
			command=Integer.parseInt(in.readLine());

			do{

				switch(command)
				{
					case 1:
*/
					/*if(input=='i')
					{*/

						System.out.println("Records/ nodes available in the Binary Tree:");
						//System.out.println("\n");


						theTree.insertNode(10,"AAA","Adam","Paul");
						theTree.insertNode(50,"BBB","Sam","Carter");
						theTree.insertNode(25,"CCC","Princy","Taylor");
						theTree.insertNode(12,"DDD","Derick","Huffman");
						theTree.insertNode(78,"EEE","Milchah","Lot");
						theTree.insertNode(35,"FFF","Stacy","Roggers");
						theTree.insertNode(45,"GGG","Mooney","Wormtail");
						theTree.insertNode(60,"HHH","Belatrix","Lestranger");

						theTree.inOrderTraverseTree(theTree.root);
//
//						break;
//				//}
//
//				/*if(input=='s')
//				{*/
//
//					case 2:

						//theTree.String toString();


						System.out.println("\n");
						System.out.println("Searching for book with ISBN: 50");
						System.out.println(theTree.searchNode(50));

						System.out.println("\n");
						System.out.println("Remove book with ISBN: 35");
						System.out.println(theTree.removeNode(35));

						System.out.println("\n");

						theTree.inOrderTraverseTree(theTree.root);

						System.out.println("\n");
						System.out.println("Searching for book with ISBN: 35");
						System.out.println(theTree.searchNode(35));

						/*break;
				//}

				}//end of switch

			}while(command<2);
*/

		}

		catch(Exception e)
		{

		}
	}
}

