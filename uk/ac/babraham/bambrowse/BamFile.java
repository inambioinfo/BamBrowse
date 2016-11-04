package uk.ac.babraham.bambrowse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.sf.samtools.SAMFileReader;

public class BamFile {

	private ArrayList<BamFileListener> listeners = new ArrayList<BamFileListener>();
	private File bamFile;
	private SAMFileReader reader;
	
	
	public void openFile (File file) throws IOException {
		
		if (bamFile != null) {
			closeFile();
		}
		
		
		bamFile = file;
		
		SAMFileReader.setDefaultValidationStringency(SAMFileReader.ValidationStringency.SILENT);

		reader = new SAMFileReader(file); 		
		
		if (reader.hasIndex()) {
			System.err.println("We have an index");
		}
		else {
			System.err.println("We don't have an index");
		}
		
		
		for (BamFileListener l : listeners) {
			l.BamFileLoaded(bamFile);
		}
			
	}
	
	public void closeFile () {

		if (bamFile != null) {
			for (BamFileListener l : listeners) {
				l.BamFileClosed(bamFile);
			}
		}
		
		reader.close();
		bamFile = null;
		
	}
	
	
	
	
	public void addListener (BamFileListener l) {
		if (l != null && !listeners.contains(l)) {
			listeners.add(l);
		}
	}

	public void removeListener (BamFileListener l) {
		if (l != null && listeners.contains(l)) {
			listeners.remove(l);
		}
	}

	
	
}
