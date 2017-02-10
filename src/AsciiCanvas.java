import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.awt.Color.*;
import java.awt.Font.*;

public class AsciiCanvas extends JTextArea implements Serializable {//extends JTextArea to inherit properties
	private AFrame head;	//declares the head AFrame
	private AFrame current;	//declares the current AFrame
	static Scanner in = new Scanner(System.in);		//static scanner
	
	public AsciiCanvas(){		//constructor
		current = new AFrame();	//creates a new current
		head = current;			//sets the head as the current as it is new
		this.setLineWrap(true);	//allows line wrapping in the text area
	}//end constructor
	
	public void nextFrame(){				//function to move to the next frame. creates a new frame if next node is null
		current.setData(this.getText()); 	//sets the data of the current AFrame to what is on the text area
		if(current.getNext()!=null){		//if next node is not null
			current = current.getNext();	//sets current AFrame as the next AFrame
			this.setText(current.getData());//sets the text area to be the data in the current AFrame
		}//end if
		else{									//if the next frame is null
			AFrame newAFrame = new AFrame();	//creates a new AFrame
			current.setNext(newAFrame);			//sets the current AFrame's next frame as the next AFrame
			newAFrame.setPrev(current);			//sets the new AFrames prev frame as the current AFrame
			current = current.getNext();		//sets current AFrame as the new AFrame
			this.setText(current.getData());	//puts the data of new current in the text area
		}//end else
	}//end nextFrame()
	
	public void prevFrame(){			//function to move to the previous frame
		current.setData(this.getText());//sets the current AFrame's data in the text area
		if(current.getPrev() == null){	//if the previous node is null
			System.out.println("You are alread at the head. Cant go back any further");//print
		}//end if
		else{								//if there is an AFrame in the previous node
			current = current.getPrev();	//sets the current AFrame as it's previous AFrame
			this.setText(current.getData());//sets the data of new current in the text area
		}//else
	}//end prevFrame()
	
	public void anim(){						//runs the animation on a continuous forward loop
		current.setData(this.getText());	//sets the current AFrame's data in the text area
		if(current.getNext() == null){		//if the next node of the current AFrame is null
			current = head;					//sets the current AFrame as the head AFrame
			this.setText(current.getData());//sets the data of the new current in the text area
		}//end if
		else{								//if there is a node after the current node
			current = current.getNext();	//sets the current as its next
			this.setText(current.getData());//sets the data of the new current in the text area
		}//end else
	}//end anim()
	
	public void save(){					//saves the current animation to a file through serialization
		try{//try this stuff
			System.out.print("what would you like the name of the file to be?\nBe sure to include extension [for default type ASCII.dat]");//print statement
			String file = in.next();	//reads in user input and sets it as the String file
			FileOutputStream fstream = new FileOutputStream(file);			//file output stream
			ObjectOutputStream ostream = new ObjectOutputStream(fstream);	//object output stream
			ostream.writeObject(head);										//writes head to file rest follows by nature
			
			ostream.close();	//close ostream
			fstream.close();	//close fstream
		}catch (Exception e){	//if something goes wrong
			System.out.println(e.getMessage());//output this
		}//end try
	}//end save
	
	public void load(){//loads animation from a file
		try{	//try this stuff
			System.out.print("what is the name of the file you would like to load?\n [for default type ASCII.dat]");//print statement
			String file = in.next();									//reads in from the file
			FileInputStream ifstream = new FileInputStream(file);		//file input stream
			ObjectInputStream iostream = new ObjectInputStream(ifstream);//object input stream
			
			head = ((AFrame)iostream.readObject());					//sets the head
			current = head;											//sets the current as the head
			this.setText(head.getData());							//prints the data to the text field
			
			ifstream.close();	//close ifstream
			iostream.close();	//close iostream
		}catch (Exception e){	//if something goes wrong
			System.out.println(e.getMessage());	//print this message
		}//end try	
	}//end load
	
	public void deleteFrame(){			//deletes a node from the link
		current.setData(this.getText());//sets the data
		if(current.getNext() != null){	//if there is a next node
			current.getPrev().setNext(current.getNext());//gets the currents previous and sets its next and the currents next
			current = current.getNext();//sets the current as its next
			current.setPrev(current.getPrev().getPrev());//sets the currents previous as its previous' previous
		}//end if
		else{//if the next node is null
			current = current.getPrev();//sets the current as its previous node
			current.setNext(null);		//sets the next node as null
		}
		this.setText(current.getData());//prints the data to the text area		
	}
	
	public void insertFrame(){						//insets a new node into the link
		current.setData(this.getText());			//sets the data of the current node as this
		if(current.getNext()!=null){				//if there is a next node
			AFrame newAFrame = new AFrame();		//creates a new AFrame
			current.getNext().setPrev(newAFrame);	//sets the current nodes next's previous node as the new AFrame
			newAFrame.setNext(current.getNext());	//sets the new AFrames next node as the current nodes next node
			newAFrame.setPrev(current);				//sets the current node as the new AFrames previous node
			current.setNext(newAFrame);				//sets the current nodes next node as the new AFrame
			current = current.getNext();			//sets the current as node as its next
			this.setText(current.getData());		//prints the data to the text area
		}//end if
		else{	//if the next node is null
			nextFrame();//simply call nextFrame()
		}//end else
		this.setText(current.getData());//prints to the text area
	}//end insertFrame()
	
	public void firstFrame(){			//sets the current frame as the head
		current.setData(this.getText());//sets the current data to the current's data
		current = head;					//sets the current to head
		this.setText(current.getData());//print to the text area
	}//end firstFrame()	
}//end AsciiCanvas
