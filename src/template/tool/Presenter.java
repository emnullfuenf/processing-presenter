/**
 * you can put a one sentence description of your tool here.
 *
 * ##copyright##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author		##author##
 * @modified	##date##
 * @version		##version##
 */

 package template.tool;
 
 import processing.app.Base;
 import processing.app.tools.Tool;
 import processing.app.ui.Editor;
 //
 import processing.app.*;
import processing.app.tools.*;
import processing.core.*;
import java.awt.*;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.event.WindowListener;
import java.io.File;
import processing.mode.java.*;
 
 
 public class Presenter implements Tool {
	 
	 PresenterWindow p;
	 Editor editor;
	 Base base;
	 
	 String folder = "/Users/m/Projekte/FH/Code/OpenProcessing/classroom_3266/Aufgabe\\ 5:\\ Farbe";
 
 // when creating a tool, the name of the main class which implements Tool
 // must be the same as the value defined for project.name in your build.properties
 
 
	public String getMenuTitle() {
		return "Presenter";
	}
 
	public void init(Base base) {
		this.base = base;
	}
 
	public void run() {
		System.out.println("Presenter. ##name## ##version## by ##author##");
		
		editor = base.getActiveEditor();
		
		final Frame f = new Frame("Presenter");
		  PresenterWindow p = new PresenterWindow(this, 350, 162);
		  /*
		  f.add((Component)p, 0);
		  p.init();
		  f.setTitle("Presenter");
		  f.setSize(p.w, p.h);
		  f.setLocation(50, 50);
		  f.setResizable(false);
		  f.setVisible(true);
		  */
		  p.setBase(base);
		  
		  // All this stuff only to close the Tool window
		  f.addWindowListener(new WindowListener()
		  {
			  public void windowOpened(WindowEvent we)
		      {
		          
		      }
			  
			  public void windowActivated(WindowEvent we)
		      {
		          
		      }
			  
			  public void windowDeactivated(WindowEvent we)
		      {
		          
		      }
			  
			  public void windowClosed(WindowEvent we)
		      {
		          
		      }
			  
			  public void windowIconified(WindowEvent we)
		      {
		          
		      }
			  
			  public void windowDeiconified(WindowEvent we)
		      {
		          
		      }
			  
		      public void windowClosing(WindowEvent we)
		      {
		          f.dispose();
		      }
		  });
		
		//editor.get
		Sketch sketch = editor.getSketch();
		//editor.ha
		//File f = new File("/Users/m/Projekte/Processing/Ballon_Wiesweiher/Ballon_Wiesweiher.pde");
		//sketch.addFile(f);
		//Editor currentEditor = editor.getBase().handleOpen("/Users/m/Projekte/Processing/Ballon_Wiesweiher/Ballon_Wiesweiher.pde");
		//currentEditor.
		//base.getActiveEditor().prepareRun();
		System.out.println("prepareRun");
		
		JavaEditor je = (JavaEditor)base.getActiveEditor();
		//je.handleRun();
		
		//System.out.println("handleRun");
		
		// handlePresent
		// editor.internalCloseRunner();
	}
	
	public void set_folder(String new_folder)
	{
		folder = new_folder;
	}
	
	public synchronized void play()
	{
		//JavaEditor je = (JavaEditor)base.getActiveEditor();
		//je.handleRun();
		//System.out.println("play");
	}
	
	public synchronized void stop()
	{
		//base.getActiveEditor().internalCloseRunner();
		//System.out.println("stop");
	}
 
 }



