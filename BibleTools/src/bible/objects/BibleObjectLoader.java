/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bible.objects;

import java.io.FileNotFoundException;

/**
 *
 * @author greg
 */
public final class BibleObjectLoader {

    public BibleObjectLoader(){
    }
    
    public void loadBibleDataObject(Bible inBible, String sFileName){
    	try {
			java.io.FileInputStream fis = new java.io.FileInputStream(sFileName);
			java.util.zip.GZIPInputStream gsi = new java.util.zip.GZIPInputStream(fis);
			java.io.ObjectInputStream ois = new java.io.ObjectInputStream(gsi);
			
			inBible = (Bible)ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void loadBibleObjects(Bible inBible, String sFileName){
        LoadBible(inBible,sFileName);
    }

    private void LoadBible(Bible inBible, String sFileName){
        java.io.BufferedReader bReader = null;
        try {
            bReader = new java.io.BufferedReader(new java.io.FileReader(sFileName));     
        } catch (FileNotFoundException ex) {
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
