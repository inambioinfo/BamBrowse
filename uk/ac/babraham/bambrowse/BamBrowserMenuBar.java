package uk.ac.babraham.bambrowse;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.sun.glass.events.KeyEvent;

public class BamBrowserMenuBar extends JMenuBar {

	public BamBrowserMenuBar (BamBrowseApplication app) {
		
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem fileOpen = new JMenuItem("Open");
		fileOpen.setMnemonic(KeyEvent.VK_O);
		fileOpen.setActionCommand("open");
		fileOpen.addActionListener(app);
		
		fileMenu.add(fileOpen);

		JMenuItem fileClose = new JMenuItem("Close");
		fileClose.setMnemonic(KeyEvent.VK_C);
		fileClose.setActionCommand("close");
		fileClose.addActionListener(app);

		fileMenu.add(fileClose);

		fileMenu.addSeparator();

		JMenuItem fileExit = new JMenuItem("Exit");
		fileExit.setMnemonic(KeyEvent.VK_X);
		fileExit.setActionCommand("open");
		fileExit.addActionListener(app);
		
		
		fileMenu.add(fileExit);

		add(fileMenu);
		
		
		
		
		
		
	}
	
	
}
