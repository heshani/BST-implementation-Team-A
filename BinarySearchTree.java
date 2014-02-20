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
		//return bookTitle +" has an ISBN " + ISBN;
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

	public static void main(String args[])
	{
		BinarySearchTree theTree=new BinarySearchTree();

		System.out.println("Records/ nodes available in the Binary Tree:");
		System.out.println("\n");


		theTree.insertNode(10,"AAA","Adam","Paul");
		theTree.insertNode(50,"BBB","Sam","Carter");
		theTree.insertNode(25,"CCC","Princy","Taylor");
		theTree.insertNode(12,"DDD","Derick","Huffman");
		theTree.insertNode(78,"EEE","Milchah","Lot");
		theTree.insertNode(35,"FFF","Stacy","Roggers");
		theTree.insertNode(45,"GGG","Mooney","Wormtail");
		theTree.insertNode(60,"HHH","Belatrix","Lestranger");


		theTree.inOrderTraverseTree(theTree.root);

		System.out.println("\n");
		System.out.println("Searching for book with ISBN: 50");
		System.out.println(theTree.searchNode(50));


	}
}

