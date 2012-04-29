/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bible.objects;

/**
 *
 * @author greg
 */
public class BibleReference implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ReferenceSource;
    private String ReferenceText;

    public BibleReference(String Source, String Text){
        this.ReferenceSource = Source;
        this.ReferenceText = Text;
    }

    @Override
    public String toString(){
        return this.ReferenceSource + ": " + this.ReferenceText;
    }
}
