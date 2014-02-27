BST-implementation-Team-A
=========================

This project shows how a bookshop database is implemented using a Binary Search Tree.

Using this project code, users can insert/ add new books to the system, remove books from the system and 
search particular books according to user's choice.

A single book record cotains: ISBN, Book Title, Author Name, Author Surname

Classes used: class Node
              class BinarySearchTree
             
Methods used: public void insertNode(int ISBN,String bookTitle, String firstName, String surName){.....}
              public void inOrderTraverseTree(Node tempNode){.....}
              public Node searchNode(int ISBN){.....}
              public boolean removeNode(int ISBN){.....}
              public Node getReplacementNode(Node replacedNode){.....}
              
              
A 'switch' coditional statement is used to get user's choice, whether to insert a node, remove a node,search or to exit from the programme.


