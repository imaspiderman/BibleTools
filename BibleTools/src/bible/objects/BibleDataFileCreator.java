package bible.objects;

import java.io.FileNotFoundException;

public class BibleDataFileCreator {
	private Bible _bible;
	private BibleObjectLoader _loader;
	private String _infile = "";
	private String _outfile = "";
	
	public static void main(String[] args) throws FileNotFoundException {
		if(args.length != 2) ShowUsage();
		BibleDataFileCreator myData = new BibleDataFileCreator(args[0],args[1]);
		myData.CreateFile();
		myData.CreateFileHashMap();
		System.out.print("\nProcess complete\n");
	}

	public BibleDataFileCreator(String sInFile, String sOutFile) throws FileNotFoundException{
		_infile = sInFile;
		_outfile = sOutFile;
		_loader = new BibleObjectLoader();
		_bible = new Bible("KJV",true);
		_loader.loadBibleObjects(_bible, new java.io.FileInputStream(_infile));
	}
	
	public void CreateFile(){
		try {
			java.io.FileOutputStream fos = new java.io.FileOutputStream(_outfile);
			java.util.zip.GZIPOutputStream gos = new java.util.zip.GZIPOutputStream(fos);
			java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(gos);
			
			oos.writeObject(_bible);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void CreateFileHashMap(){
		try{
			java.io.FileOutputStream fos = new java.io.FileOutputStream("hashmap_" + _outfile);
			java.util.zip.GZIPOutputStream gos = new java.util.zip.GZIPOutputStream(fos);
			java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(gos);
			
			java.util.HashMap<Integer, String> BibleMap = new java.util.HashMap<Integer, String>();
			java.util.HashMap<Integer, Integer>BibleChapters = new java.util.HashMap<Integer, Integer>();
			
			BibleBook[] books = _bible.getBooks();
			for(int b=0; b<books.length; b++){
				BibleChapters.put(b+1, books[b].getMaxChapters());
				for(int c=0; c<books[b].getMaxChapters(); c++){
					int iKey = 0;
					iKey = ((b+1)<<8) | ((c+1));
					BibleMap.put(iKey, books[b].getChapter(c+1).toStringWithBreaks());
				}
			}
			
			System.out.println("Number of Book,Chapter mappings " + BibleChapters.size());
			System.out.println("Number of verses " + BibleMap.size());
			
			oos.writeObject(BibleMap);
			oos.flush();
			oos.close();
			
			fos = new java.io.FileOutputStream("hashmapkey_" + _outfile);
			gos = new java.util.zip.GZIPOutputStream(fos);
			oos = new java.io.ObjectOutputStream(gos);
			oos.writeObject(BibleChapters);
			oos.flush();
			oos.close();
			
		} catch(Exception e) {
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