/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bible.objects;

/**
 *
 * @author greg
 */
public class BibleVerse implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int VerseNumber;
    public String VerseText;
    private java.util.LinkedList<BibleReference> References;
    private BibleChapter parentChapter;

    public BibleVerse(int VerseNumber, String VerseText, BibleChapter inChapter){
        this.VerseNumber = VerseNumber;
        this.VerseText = VerseText;
        this.References = new java.util.LinkedList<BibleReference>();
        this.parentChapter = inChapter;
    }

    public void addReference(BibleReference in){
        this.References.add(in);
    }

    public BibleChapter getContainingChapter(){
        return parentChapter;
    }

    public BibleReference[] getReferences(){
        return this.References.toArray(new BibleReference[0]);
    }

    public String getReferenceBySource(String source){
        BibleReference[] allRef = this.getReferences();
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        for(int i=0; i<allRef.length; i++){
            sb.append(allRef[i].toString()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public String toString(){
        String sRef = " ";
        if(this.References.size() > 0) sRef = "*";
        return Integer.toString(VerseNumber) + sRef + " " + VerseText;
    }
}
