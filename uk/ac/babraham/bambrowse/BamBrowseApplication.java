package uk.ac.babraham.bambrowse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;


public class BamBrowseApplication extends JFrame implements BamFileListener, ActionListener {

	private BamFile bamFile;
	
	private File lastLocation = null;
	
	public BamBrowseApplication () {
		
		super("BamBrowse");
		
		bamFile = new BamFile();
		bamFile.addListener(this);
		
		setJMenuBar(new BamBrowserMenuBar(this));
		
		
		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		
		new BamBrowseApplication();
	}

	private void showError (Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
		
	}
	
	public void BamFileLoaded(File file) {
		setTitle("BamBrowse ["+file.getName()+"]");
	}

	public void BamFileClosed(File file) {
		setTitle("BamBrowse");
	}

	public void AlignmentSelected() {
		// TODO Auto-generated method stub
		
	}
	
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		
		if (command.equals("exit")) System.exit(0);
		
		else if (command.equals("open")) {
			JFileChooser chooser = new JFileChooser(lastLocation);
			chooser.setMultiSelectionEnabled(false);
			chooser.setFileFilter(new FileFilter() {
			
				public String getDescription() {
					return "BAM files";
				}
			
				public boolean accept(File f) {
					if (f.isDirectory() || f.getName().toLowerCase().endsWith(".bam")) {
						return true;
					}
					else {
						return false;
					}
				}
			
			});
			
			int result = chooser.showOpenDialog(this);
			if (result == JFileChooser.CANCEL_OPTION) return;

			File file = chooser.getSelectedFile();
			lastLocation = file;
		
			try {
				bamFile.openFile(file);
			}
			catch (IOException ioe) {
				showError(ioe);
			}
		}
		
		else if (command.equals("close")) {
			bamFile.closeFile();
		}
		else {
			showError(new Exception("Didn't understand command '"+command+"'"));
		}
		
	}

}
