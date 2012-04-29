/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bible.objects;

/**
 *
 * @author greg
 */
public class BibleBook implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String BookName;
    public String BookAbbreviation;
    public int BookNumber;
    public String BookTestament;
    private java.util.LinkedList<BibleChapter> BookChapters;
    private int iCurrentChapter;

    public BibleBook(String BookName, String BookAbbreviation, int BookNumber, String BookTestament){
        this.BookName = BookName;
        this.BookAbbreviation = BookAbbreviation;
        this.BookNumber = BookNumber;
        this.BookTestament = BookTestament;
        this.iCurrentChapter = 1;
        BookChapters = new java.util.LinkedList<BibleChapter>();
    }

    public void nextChapter(){
        if(this.iCurrentChapter < this.BookChapters.size()){
            this.iCurrentChapter++;
        }
    }

    public int getMaxChapters(){
        return this.BookChapters.size();
    }

    public void prevChapter(){
        if(this.iCurrentChapter > 1){
            this.iCurrentChapter--;
        }
    }

    public int getCurrentChapter(){
        return this.iCurrentChapter;
    }

    public void addChapter(BibleChapter inChapter){
        BookChapters.add(inChapter);
    }

    public BibleChapter[] getChapters(){
        return BookChapters.toArray(new BibleChapter[0]);
    }

    public BibleChapter getChapter(int ChapterNumber){
        BibleChapter[] allChapters = BookChapters.toArray(new BibleChapter[0]);
        for(int i=0; i<allChapters.length; i++){
            if(allChapters[i].ChapterNumber == ChapterNumber){
               this.iCurrentChapter = allChapters[i].ChapterNumber;
               return allChapters[i];
            }
        }

        return null;
    }

    public BibleChapter getChapter(){
        return this.getChapter(this.iCurrentChapter);
    }

    @Override
    public String toString(){
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        BibleChapter[] allChapters = this.getChapters();
        sb.append("\n").append("Book ").append(this.BookName).append("\n");
        for(int i=0; i<allChapters.length; i++){
            sb.append(allChapters[i].toString());
        }

        return sb.toString();
    }
}
