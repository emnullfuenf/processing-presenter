package template.tool;

import processing.core.*;
import processing.mode.java.JavaEditor;
import processing.app.*;
import processing.app.tools.*;
import java.util.*;
import java.io.*;

import controlP5.*;

public class PresenterWindow extends PApplet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Base base;
	int w, h;
	Presenter t;
	String folder = ""; //"/Users/m/Projekte/FH/Code/OpenProcessing/classroom_3266/Aufgabe\\ 5:\\ Farbe";
	boolean presenting = false;
	int timer = 300000;
	int last_timer = 0;
	
	ControlP5 controlP5;
	Textlabel folderLabel, timeLabel;
	Button playstop;
	Button forward;
	
	
public void setup() {
	  size(w, h);
	  background(0);
	  //folder = base.getSketchbookFolder().getAbsolutePath();

	  controlP5 = new ControlP5(this);
	  controlP5.addButton("Sketch_folder", 0, 20, 20, 72, 19);
	  //  .setCaptionLabel("Sketch_folder");
	  folderLabel = controlP5.addTextlabel("folderLabel", folder.toUpperCase(), 100, 25);
	  folderLabel.setColorValue(0xffffffff);
	  
	  timeLabel = controlP5.addTextlabel("time", "0", 80, 105);
	  timeLabel.setColorValue(0xffffffff);
	          

	  playstop = controlP5.addButton("play", 0, 20, 100, 20, 20);
		playstop.setImages(loadImage("play.png"), loadImage("play_active.png"), loadImage("play_active.png"));
	    playstop.setSwitch(true);

	  forward = controlP5.addButton("forward", 0, 50, 100, 20, 20);
		forward.setImages(loadImage("forward.png"), loadImage("forward_active.png"), loadImage("forward_active.png"));

	  //println("\u25B7");

		float currentTimer = timer/1000;
	  Slider interval = controlP5.addSlider("interval", 0, 1800);
	  interval.setPosition(20, 60);
	  interval.setSize(200, 20);
	  interval.setDecimalPrecision(0);
	  //interval.setRange(0, 1800);
	  interval.setValue(currentTimer);
	  //interval.setNumberOfTickMarks(1801);
	  interval.setCaptionLabel("Interval (sec)");
	  
	  //println("timer: " + timer + " " + currentTimer);
  }

public void setBase(Base b)
{
	base = b;
}

  public void draw() {
	  background(0);
	  
	  /*
	  text(folder, 65, 20);
	  text("1: Select folder with sketches\n" + 
			  "2: Start / Next sketch\n" +
			  "3: Stop presentation\n" +
			  "Arrow UP/DOWN: Set timer\n\n" +
			  "Presenting folder: " + folder + "\n" +
			  "Timer:" + (timer/1000) + "s\n", 20, 40);
	*/
	 
	  if (millis() - last_timer >= timer && presenting == true)
	  {
		  forward();
		  last_timer = millis();
	  }
	  
	  if (presenting == true)
	  {
		  int currentTime = (millis() - last_timer) / 1000;
		  timeLabel.setValue(currentTime+" S / " + (timer/1000) + " S");
	  }
	  else
	  {
		  timeLabel.setValue("0 S / " + (timer/1000) + " S");
	  }
  }
  
  public void Sketch_folder(int theValue) {
	  println("a button event from buttonA: "+theValue);
	  selectFolder("Sketches:", "folderSelected");
  }
  
  void interval(float time) {
	timer = (int)(time*1000);
	//println(timer + " " + time);
}
  
  public void keyPressed()
  {
	  if (key == '1')
	  {
		  println("Presenter: Select folder and run");
		  selectFolder("Sketches:", "folderSelected");
	  }
	  else if (key == '2')
	  {
		  println("Presenter: next sketch");
		  forward();
		  presenting = true;
	  }
	  else if (key == '3')
	  {
		  println("Presenter: Stop");
		  base.getActiveEditor().internalCloseRunner();
		  presenting = false;
	  }
	  else if (keyCode == UP)
	  {
		  timer += 10000;
	  }
	  else if (keyCode == DOWN)
	  {
		  if (timer >= 20000)
			  timer -= 10000;
	  }
		  
  }
  
  public void folderSelected(File selection) {
	  if (selection == null) {
	    println("Window was closed or the user hit cancel.");
	  } else {
	    println("User selected " + selection.getAbsolutePath());
	    folder = selection.getAbsolutePath();
	    folderLabel.setValue(folder.toUpperCase());
	    loadSketches();
	  }
	}
  
  public void play()
  {
	  if (playstop.booleanValue() == true)
	  {
		  println("Presenter: next sketch");
		  forward();
		  presenting = true;
	  }
	  else
	  {
		  println("Presenter: Stop");
		  base.getActiveEditor().internalCloseRunner();
		  presenting = false;
	  }
  }
  
  public void forward()
  {
	  base.getActiveEditor().internalCloseRunner();
	  
	  List editors = base.getEditors();
	  JavaEditor je = (JavaEditor)editors.get( (int)random( editors.size()-1 ) );
	  je.handlePresent();
	  
	  presenting = true;
	  last_timer = millis();
	  
  }
  
  public void loadSketches()
  {
	  try {
		  
		  base.handleClose(base.getActiveEditor(), true);
		  
	  File file = new File(folder);
	  File[] listOfFiles = file.listFiles();
	  println(listOfFiles.length);
	  
	  int distance = 0;
	  
	  for (File f : listOfFiles)
	  {
		    if (f.isDirectory())
		    {
		    	println(file.getName());
		      Editor e = base.handleOpen(folder + "/" + f.getName() + "/" + f.getName() + ".pde");
		      e.setLocation(50 + w + 20 + distance, 50 + distance);
		      //e.toBack();
		      distance += 5;
		    }
		}
	  
	  List editors = base.getEditors();
	  JavaEditor je = (JavaEditor)editors.get( (int)random( editors.size()-1 ) );
	  je.handlePresent();
	  
	  presenting = true;
	  last_timer = millis();
	  
	  } catch (Exception e) { println(e); }
  }
  
  private PresenterWindow(Object theParent, Base b, int w, int h)
  {
	  parent = theParent;
	  this.base = b;
	  this.w = w;
	  this.h = h;
	  println("PresenterWindow");
  }

  public PresenterWindow(Object theParent, int theWidth, int theHeight) {
    parent = theParent;
    w = theWidth;
    h = theHeight;
  }


  Object parent;
}
