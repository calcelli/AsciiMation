//AsciiAnim.java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class AsciiAnim extends JFrame implements ActionListener{
  
	private static final long serialVersionUID = 4429837192837382438L;

  AsciiCanvas cnvAnim = new AsciiCanvas();
  JButton btnPrev = new JButton("prev");
  JButton btnNext = new JButton("next");
  JButton btnSave = new JButton("save");
  JButton btnLoad = new JButton("load");
  JButton btnAnim = new JButton("animate");
  JButton btnIns = new JButton("insert");
  JButton btnDel = new JButton("delete");
  JButton btnFirst = new JButton("First");
  JTextField fileName = new JTextField("ASCII.dat");
  
  JPanel pnlSouth = new JPanel();
  JPanel pnlWest = new JPanel();
  Timer timer = new Timer(100, this);

  boolean animating = false;

  public AsciiAnim(){
    
    this.setUpGUI(); 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setSize(640, 480);
  } // end constructor
  
  public void setUpGUI(){
    //like it says, set up GUI
    Container pnlMain = this.getContentPane();
    
    pnlSouth.setLayout(new FlowLayout());
    pnlWest.setLayout(new FlowLayout());
    pnlMain.add(cnvAnim, BorderLayout.CENTER);
    pnlMain.add(pnlSouth, BorderLayout.SOUTH);

    
    pnlSouth.add(btnFirst);
    pnlSouth.add(btnPrev);
    pnlSouth.add(btnNext);
    pnlSouth.add(btnAnim);
    pnlSouth.add(btnSave);
    pnlSouth.add(btnLoad);
    pnlSouth.add(btnIns);
    pnlSouth.add(btnDel);


    //add action listeners
    btnFirst.addActionListener(this);
    btnPrev.addActionListener(this);
    btnNext.addActionListener(this);
    btnAnim.addActionListener(this);
    btnSave.addActionListener(this);
    btnLoad.addActionListener(this);
    btnIns.addActionListener(this);
    btnDel.addActionListener(this);

  } // end setUpGUI

  public void actionPerformed(ActionEvent e){
	    if (e.getSource() == btnPrev){
	      cnvAnim.prevFrame();
	    } else if (e.getSource() == btnNext){  
	      cnvAnim.nextFrame();
	    } else if (e.getSource() == btnAnim){
	      //change the animation status
	      if (timer.isRunning()){  
	        btnAnim.setText("animate");
	        timer.stop();
	        this.animating = false;
	      } else {
	        btnAnim.setText("stop");
	        timer.start();
	        this.animating = true;
	      } // end if
	    } else if (e.getSource() == btnSave){
			cnvAnim.save();
	    } else if (e.getSource() == btnLoad){  
			cnvAnim.load();
	    } else if (this.animating == true){
	      cnvAnim.anim();  
    } else if (e.getSource() == btnDel){  
    	cnvAnim.deleteFrame();
    	System.out.println("frame deleted");
    } else if (e.getSource() == btnIns){ 
    	cnvAnim.insertFrame();
    } else if (e.getSource() == btnFirst){
    	cnvAnim.firstFrame();
    } else {  
      System.out.println("action not defined");
      
    } // end if
  } // end actionPerformed

  public static void main(String[] args){
    new AsciiAnim();
  } // end main


} // end class def