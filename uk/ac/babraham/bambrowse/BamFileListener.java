package uk.ac.babraham.bambrowse;

import java.io.File;

public interface BamFileListener {

	public void BamFileLoaded (File file);
	public void BamFileClosed (File file);
	public void AlignmentSelected ();
	
}
