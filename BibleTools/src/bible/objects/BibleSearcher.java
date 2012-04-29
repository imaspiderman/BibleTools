/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bible.objects;

/**
 *
 * @author greg
 */
public class BibleSearcher {
    private java.util.LinkedList<BibleVerse> verses;
    private String searchText = "";
    private String searchRegEx = "";
    private Bible _Bible;
    private boolean bCaseInsensitive = true;
    private boolean bFindWholeWords = true;

    public BibleSearcher(Bible inBible){
        verses = new java.util.LinkedList<BibleVerse>();
        _Bible = inBible;
    }

    private void clearStrings(){
        searchText = "";
        searchRegEx = "";
    }

    public void caseInsensitive(boolean bIn){
        bCaseInsensitive = bIn;
    }

    public void findWholeWords(boolean bIn){
        bFindWholeWords = bIn;
    }

    public void searchString(String inString){
        verses.clear();
        clearStrings();
        if(bCaseInsensitive){
            searchText = inString.toLowerCase();
        } else {
            searchText = inString;
        }
    }

    public void regExString(String inString){
        verses.clear();
        clearStrings();
        searchRegEx = inString;
    }

    public BibleVerse[] getVerses(){
        return verses.toArray(new BibleVerse[0]);
    }

    public int DoSearch(){
        if(searchText.isEmpty() && searchRegEx.isEmpty()) return 0;
        
        int iBooks = _Bible.getBooks().length;
        for(int b=0;b<iBooks;b++){
            BibleBook curBook = _Bible.getBook(b+1);
            int iChapters = curBook.getMaxChapters();
            for(int c=0;c<iChapters;c++){
                BibleChapter curChapter = curBook.getChapter(c+1);
                int iVerses = curChapter.getMaxVerses();
                for(int v=0;v<iVerses;v++){
                    BibleVerse curVerse = curChapter.getVerse(v+1);
                    if(curVerse != null){
                        if(!searchText.isEmpty()){
                            CharSequence chars = searchText.subSequence(0, searchText.length()-1);
                            if(bCaseInsensitive){
                                if(bFindWholeWords){
                                    if(curVerse.VerseText.toLowerCase().matches("^.*[^a-z]" + searchText.trim() + "[^a-z].*$")){
                                        verses.add(curVerse);
                                    }
                                } else {
                                    if(curVerse.VerseText.toLowerCase().contains(chars)){
                                        verses.add(curVerse);
                                    }
                                }
                            } else {
                                if(bFindWholeWords){
                                    if(curVerse.VerseText.matches("^.*[^a-zA-Z]" + searchText.trim() + "[^a-zA-Z].*$")){
                                        verses.add(curVerse);
                                    }
                                } else {
                                    if(curVerse.VerseText.contains(chars)){
                                        verses.add(curVerse);
                                    }
                                }
                            }
                        }
                        if(!searchRegEx.isEmpty() && curVerse.VerseText.matches(searchRegEx.toLowerCase())){
                            verses.add(curVerse);
                        }
                    }
                }
            }
        }

        return verses.size();
    }
}
