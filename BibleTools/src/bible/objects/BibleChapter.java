/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bible.objects;

/**
 *
 * @author greg
 */
public class BibleChapter implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int ChapterNumber;
    private int iCurrentVerse;
    private java.util.LinkedList<BibleVerse> ChapterVerses;
    private BibleBook parentBook;

    public BibleChapter(int ChapterNumber, BibleBook inBook){
        this.ChapterNumber = ChapterNumber;
        this.iCurrentVerse = 1;
        ChapterVerses = new java.util.LinkedList<BibleVerse>();
        this.parentBook = inBook;
    }

    public BibleBook getContainingBook(){
        return parentBook;
    }

    public void nextVerse(){
        if(this.iCurrentVerse < this.ChapterVerses.size()){
            this.iCurrentVerse++;
        }
    }

    public int getMaxVerses(){
        return this.ChapterVerses.size();
    }

    public void prevVerse(){
        if(this.iCurrentVerse > 1){
            this.iCurrentVerse--;
        }
    }

    public int getCurrentVerse(){
        return this.iCurrentVerse;
    }

    public void addVerse(BibleVerse inVerse){
        ChapterVerses.add(inVerse);
    }

    public BibleVerse[] getVerses(){
        return this.ChapterVerses.toArray(new BibleVerse[0]);
    }

    public BibleVerse getVerse(int VerseNumber){
        BibleVerse[] allVerses = ChapterVerses.toArray(new BibleVerse[0]);
        for(int i=0; i<allVerses.length; i++){
            if(((BibleVerse)allVerses[i]).VerseNumber == VerseNumber){
                this.iCurrentVerse = allVerses[i].VerseNumber;
                return allVerses[i];
            }
        }

        return null;
    }

    public BibleVerse getVerse(){
        return this.getVerse(this.iCurrentVerse);
    }

    @Override
    public String toString(){
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        BibleVerse[] allVerses = this.getVerses();
        sb.append("\n").append("Chapter ").append(Integer.toString(this.ChapterNumber)).append("\n");
        for(int i=0; i<allVerses.length; i++)
        {
            sb.append(allVerses[i].toString());
        }

        return sb.toString();
    }

    public String toStringWithBreaks(){
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        BibleVerse[] allVerses = this.getVerses();
        sb.append("\n").append("Chapter ").append(Integer.toString(this.ChapterNumber)).append("\n");
        for(int i=0; i<allVerses.length; i++)
        {
            sb.append(allVerses[i].toString()).append("\n");
        }

        return sb.toString();
    }
}
