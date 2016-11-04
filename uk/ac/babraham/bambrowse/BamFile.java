package uk.ac.babraham.bambrowse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import net.sf.samtools.SAMFileHeader;
import net.sf.samtools.SAMFileReader;
import net.sf.samtools.SAMFileSpan;
import net.sf.samtools.SAMRecord;
import net.sf.samtools.SAMRecordIterator;

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

		
		InputStream is = new FileInputStream(file);
		
		
//		is.skip(1000);
		
		
		reader = new SAMFileReader(is); 				
		
		
		for (BamFileListener l : listeners) {
			l.BamFileLoaded(bamFile);
		}
			
	}
	
	public SAMFileHeader getHeader () {
		if (bamFile != null) {
			return(reader.getFileHeader());
		}
		return null;
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
