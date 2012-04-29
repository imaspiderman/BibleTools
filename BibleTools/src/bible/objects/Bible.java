/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bible.objects;

/**
 *
 * @author greg
 */
public class Bible implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String BibleVersion;
    private java.util.LinkedList<BibleBook> BibleBooks;
    private int iCurrentBook;

    public Bible(String BibleVersion, boolean CreateDefaultBooks){
        this.BibleVersion = BibleVersion;
        BibleBooks = new java.util.LinkedList<BibleBook>();
        this.iCurrentBook = 1;
        if(CreateDefaultBooks){CreateDefaultBooks();}
    }

    public void addBook(BibleBook inBook){
        BibleBooks.add(inBook);
    }

    public void nextBook(){
        if(this.iCurrentBook < this.BibleBooks.size()){
            this.iCurrentBook++;
        }
    }

    public void prevBook(){
        if(this.iCurrentBook > 1){
            this.iCurrentBook--;
        }
    }

    public int getCurrentBook(){
        return this.iCurrentBook;
    }

    public BibleBook[] getBooks(){
        return this.BibleBooks.toArray(new BibleBook[0]);
    }

    public BibleBook getBook(int BookNumber){
        BibleBook[] allBooks = this.BibleBooks.toArray(new BibleBook[0]);
        for(int i=0; i<allBooks.length; i++){
            if(allBooks[i].BookNumber == BookNumber){
                this.iCurrentBook = allBooks[i].BookNumber;
                return (BibleBook)allBooks[i];
            }
        }

        return null;
    }

    public BibleBook getBook(String BookName){
        BibleBook[] allBooks = this.BibleBooks.toArray(new BibleBook[0]);
        for(int i=0; i<allBooks.length; i++){
            if(allBooks[i].BookName.toLowerCase().equals(BookName.toLowerCase()) ||
                    allBooks[i].BookAbbreviation.toLowerCase().equals(BookName.toLowerCase())){
                iCurrentBook = allBooks[i].BookNumber;
                return allBooks[i];
            }
        }

        return null;
    }
    
    public boolean isBook(String BookName){
        if(getBook(BookName) != null){
            return true;
        }
        return false;
    }

    public BibleBook getBook(){
        return this.getBook(this.iCurrentBook);
    }

    @Override
    public String toString(){
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        BibleBook[] allBooks = this.getBooks();
        for(int i=0; i<allBooks.length; i++){
            sb.append(allBooks[i].toString());
        }

        return sb.toString();
    }

    private void CreateDefaultBooks(){
        this.addBook(new BibleBook("Genesis","gen",1,"old"));
        this.addBook(new BibleBook("Exodus","exo",2,"old"));
        this.addBook(new BibleBook("Leviticus","lev",3,"old"));
        this.addBook(new BibleBook("Numbers","num",4,"old"));
        this.addBook(new BibleBook("Deuteronomy","deu",5,"old"));
        this.addBook(new BibleBook("Joshua","jos",6,"old"));
        this.addBook(new BibleBook("Judges","jud",7,"old"));
        this.addBook(new BibleBook("Ruth","rut",8,"old"));
        this.addBook(new BibleBook("1 Samuel","1sa",9,"old"));
        this.addBook(new BibleBook("2 Samuel","2sa",10,"old"));
        this.addBook(new BibleBook("1 Kings","1ki",11,"old"));
        this.addBook(new BibleBook("2 Kings","2ki",12,"old"));
        this.addBook(new BibleBook("1 Chronicles","1ch",13,"old"));
        this.addBook(new BibleBook("2 Chronicles","2ch",14,"old"));
        this.addBook(new BibleBook("Ezra","ezr",15,"old"));
        this.addBook(new BibleBook("Nehemiah","neh",16,"old"));
        this.addBook(new BibleBook("Esther","est",17,"old"));
        this.addBook(new BibleBook("Job","job",18,"old"));
        this.addBook(new BibleBook("Psalms","psa",19,"old"));
        this.addBook(new BibleBook("Proverbs","pro",20,"old"));
        this.addBook(new BibleBook("Ecclesiastes","ecc",21,"old"));
        this.addBook(new BibleBook("Song of Solomon","son",22,"old"));
        this.addBook(new BibleBook("Isaiah","isa",23,"old"));
        this.addBook(new BibleBook("Jeremiah","jer",24,"old"));
        this.addBook(new BibleBook("Lamentations","lam",25,"old"));
        this.addBook(new BibleBook("Ezekiel","eze",26,"old"));
        this.addBook(new BibleBook("Daniel","dan",27,"old"));
        this.addBook(new BibleBook("Hosea","hos",28,"old"));
        this.addBook(new BibleBook("Joel","joe",29,"old"));
        this.addBook(new BibleBook("Amos","amo",30,"old"));
        this.addBook(new BibleBook("Obadiah","oba",31,"old"));
        this.addBook(new BibleBook("Jonah","jon",32,"old"));
        this.addBook(new BibleBook("Micah","mic",33,"old"));
        this.addBook(new BibleBook("Nahum","nah",34,"old"));
        this.addBook(new BibleBook("Habakkuk","hab",35,"old"));
        this.addBook(new BibleBook("Zephaniah","zep",36,"old"));
        this.addBook(new BibleBook("Haggai","hag",37,"old"));
        this.addBook(new BibleBook("Zechariah","zec",38,"old"));
        this.addBook(new BibleBook("Malachi","mal",39,"old"));
        this.addBook(new BibleBook("Matthew","mat",40,"new"));
        this.addBook(new BibleBook("Mark","mar",41,"new"));
        this.addBook(new BibleBook("Luke","luk",42,"new"));
        this.addBook(new BibleBook("John","joh",43,"new"));
        this.addBook(new BibleBook("Acts","act",44,"new"));
        this.addBook(new BibleBook("Romans","rom",45,"new"));
        this.addBook(new BibleBook("1 Corinthians","1co",46,"new"));
        this.addBook(new BibleBook("2 Corinthians","2co",47,"new"));
        this.addBook(new BibleBook("Galatians","gal",48,"new"));
        this.addBook(new BibleBook("Ephesians","eph",49,"new"));
        this.addBook(new BibleBook("Philippians","phi",50,"new"));
        this.addBook(new BibleBook("Colossians","col",51,"new"));
        this.addBook(new BibleBook("1 Thessalonians","1th",52,"new"));
        this.addBook(new BibleBook("2 Thessalonians","2th",53,"new"));
        this.addBook(new BibleBook("1 Timothy","1ti",54,"new"));
        this.addBook(new BibleBook("2 Timothy","2ti",55,"new"));
        this.addBook(new BibleBook("Titus","ti",56,"new"));
        this.addBook(new BibleBook("Philemon","ph",57,"new"));
        this.addBook(new BibleBook("Hebrews","heb",58,"new"));
        this.addBook(new BibleBook("James","jam",59,"new"));
        this.addBook(new BibleBook("1 Peter","1pe",60,"new"));
        this.addBook(new BibleBook("2 Peter","2pe",61,"new"));
        this.addBook(new BibleBook("1 John","1jo",62,"new"));
        this.addBook(new BibleBook("2 John","2jo",63,"new"));
        this.addBook(new BibleBook("3 John","3jo",64,"new"));
        this.addBook(new BibleBook("Jude","ju",65,"new"));
        this.addBook(new BibleBook("Revelation","rev",66,"new"));
    }
}
