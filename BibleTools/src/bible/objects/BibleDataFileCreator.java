package bible.objects;

import java.io.IOException;

public class BibleDataFileCreator {
	private Bible _bible;
	private BibleObjectLoader _loader;
	private String _infile = "";
	private String _outfile = "";
	
	public static void main(String[] args) {
		if(args.length != 2) ShowUsage();
		BibleDataFileCreator myData = new BibleDataFileCreator(args[0],args[1]);
		myData.CreateFile();
		System.out.print("\nProcess complete\n");
	}

	public BibleDataFileCreator(String sInFile, String sOutFile){
		_infile = sInFile;
		_outfile = sOutFile;
		_loader = new BibleObjectLoader();
		_bible = new Bible("KJV",true);
		_loader.loadBibleObjects(_bible, _infile);
		try {
			System.out.print("\n" + BibleDataFileCreator.rtfToHtml(new java.io.StringReader(_bible.getBook(1).getChapter(1).toString()))+ "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This loads the file into memory
	 * @param sFile path and name of the file
	 */
	public void CreateFile(){
		try {
			java.io.FileOutputStream fos = new java.io.FileOutputStream(_outfile);
			java.util.zip.GZIPOutputStream gso = new java.util.zip.GZIPOutputStream(fos);
			java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(gso);
			
			oos.writeObject(_bible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String rtfToHtml(java.io.Reader rtf) throws java.io.IOException {
		javax.swing.JEditorPane p = new javax.swing.JEditorPane();
		javax.swing.text.rtf.RTFEditorKit kitRtf = new javax.swing.text.rtf.RTFEditorKit();
		p.setEditorKit(kitRtf);
		try {
			kitRtf.read(rtf, p.getDocument(), 0);
			kitRtf = null;
			javax.swing.text.html.HTMLEditorKit kitHtml = new javax.swing.text.html.HTMLEditorKit();
			p.setEditorKit(kitHtml);
			java.io.Writer writer = new java.io.StringWriter();
			kitHtml.write(writer, p.getDocument(), 0, p.getDocument().getLength());
			return writer.toString();
		} catch (javax.swing.text.BadLocationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Shows how to use this program
	 */
	public static void ShowUsage(){
		System.console().writer().print("Usage: java -jar BibleDataFileCreator.jar inputfilename.txt outputfilename.bin");
	}
}