package bible.objects;

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
		String s = _bible.getBook(1).getChapter(1).toString();;
		System.out.print(s);
	}
	
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
	
	/**
	 * Shows how to use this program
	 */
	public static void ShowUsage(){
		System.console().writer().print("Usage: java -jar BibleDataFileCreator.jar inputfilename.txt outputfilename.bin");
	}
}