package aesimagecrypt;
import javax.swing.JFileChooser;

public class FilePath {
	
	public JFileChooser getFilePath()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("WeareSoGoingToEncrptThis");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return chooser;
		} else {
		  return null;
		}
		
		
	}
}
