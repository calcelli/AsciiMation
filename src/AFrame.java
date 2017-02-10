import java.io.Serializable;

public class AFrame implements Serializable {
	private String data;//string that holds a frame of ascii animation
	private AFrame prev;//the previous AFrame
	private AFrame next;//the next AFrame
	
	public AFrame(){//constructor for new AFrame
		data = "";//sets the text field to blank
		prev = null;//the previous node is null
		next = null;//the next node is null
	}//end constructor
	public String getData(){//returns the data string
		return data;//return data
	}//end getData()
	
	public void setData(String text){//sets the data of the current frame. brings in text
		data = text;//sets the frames data to be the brought in text string
	}//end setData()
	
	public AFrame getPrev(){//returns the previous AFrame
		return prev;//return prev
	}//end getPrev()
	
	public void setPrev(AFrame newAFrame){//sets the previous AFrame as the sent AFrame
		prev = newAFrame;//sets prev AFrame as newAFrame
	}//end setPrev()
	
	public void setNext(AFrame newAFrame){//sets the next AFrame as the send AFrame
		next = newAFrame;//sets next AFrame as newAFrame
	}//end setNext()
	
	public AFrame getNext(){//returns the next AFRame
		return next;//returns next
	}//end getNext()
}//end AFrame
