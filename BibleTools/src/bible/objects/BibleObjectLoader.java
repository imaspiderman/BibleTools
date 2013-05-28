/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bible.objects;

/**
 *
 * @author greg
 */
public final class BibleObjectLoader {

    public BibleObjectLoader(){
    }
    
    public Object loadBibleDataObject(java.io.InputStream sStream){
    	Object newObject = new Object();
    	try {			
    		java.util.zip.GZIPInputStream gis = new java.util.zip.GZIPInputStream(sStream);
    		java.io.ObjectInputStream ois = new java.io.ObjectInputStream(gis);
			
			newObject = ois.readObject();			
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return newObject;
    }
    
    @SuppressWarnings("unchecked")
	public java.util.HashMap<Integer, String> loadBibleHashMap(java.io.InputStream sStream){
    	java.util.HashMap<Integer, String> map = new java.util.HashMap<Integer, String>();
    	try {			
    		java.util.zip.GZIPInputStream gis = new java.util.zip.GZIPInputStream(sStream);
			java.io.ObjectInputStream ois = new java.io.ObjectInputStream(gis);
			
			map = (java.util.HashMap<Integer, String>)ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return map;
    }
    
    @SuppressWarnings("unchecked")
	public java.util.HashMap<Integer, Integer> loadBibleHashMapKey(java.io.InputStream sStream){
    	java.util.HashMap<Integer, Integer> map = new java.util.HashMap<Integer, Integer>();
    	try {			
    		java.util.zip.GZIPInputStream gis = new java.util.zip.GZIPInputStream(sStream);
			java.io.ObjectInputStream ois = new java.io.ObjectInputStream(gis);
			
			map = (java.util.HashMap<Integer, Integer>)ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return map;
    }

    public void loadBibleObjects(Bible inBible, java.io.InputStream sFileName){
        LoadBible(inBible,sFileName);
    }

    private void LoadBible(Bible inBible, java.io.InputStream sStream){
        java.io.BufferedReader bReader = null;
        try {
            bReader = new java.io.BufferedReader(new java.io.InputStreamReader(sStream));     
        } catch (Exception ex) {
            System.console().writer().println(ex.toString());
        }

        String sLine = "";
        String[] sParts;
        int iBook = 0;
        int iChap = 0;
        int iVers = 0;
        String sTextRtf = "";
        //For kjb.txt 0 = line number 1 = book 2 = chapter 3 = verse 4 = verse text
        try{
            while((sLine = bReader.readLine()) != null){
                sParts = sLine.split("\\t");
                iBook = Integer.parseInt(sParts[1]);
                iChap = Integer.parseInt(sParts[2]);
                iVers = Integer.parseInt(sParts[3]);
                sTextRtf = sParts[4];
                
                //Strip out rtf fields of the format {<bunch of codes>[text to keep]<usually nothing>}
                sTextRtf = sTextRtf.replaceAll("\\{[^\\s]*\\s{1,}([^\\}]*)\\}", "$1");

                //These are the references
                if(inBible.getBook(iBook)!=null){
                    if(inBible.getBook(iBook).getChapter(iChap) == null){
                        inBible.getBook(iBook).addChapter(new BibleChapter(iChap,inBible.getBook(iBook)));
                    }

                    inBible.getBook(iBook).getChapter(iChap).addVerse(new BibleVerse(iVers, sTextRtf, inBible.getBook(iBook).getChapter(iChap)));
                }
            }
        } catch (java.lang.Exception ex) {
            System.out.print(ex.toString());
            java.lang.StackTraceElement[] ste = ex.getStackTrace();
            for(int i=0; i<ste.length; i++){
                System.out.println(ste[i].toString());
            }
        }
    }
}
