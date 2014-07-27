package ch.vanakh.hiragana;


/**
 * Implementierung der Klasse <code>Hiragana</code>.
 * @author vanakh
 *
 */
public class Hiragana {
	//Objektvariablen
	/**
	 * eindeutige ID
	 */
	private int id;
	/**
	 * Romanji
	 */
	private String romanji;
	/**
	 * Hiragana
	 */
	private String hiragana;
	
	
	//Konstruktor
	/**
	 * Konstruktor der Klasse <code>Hiragana</code>
	 * @param id eindeutige ID
	 * @param romanji Romanji
	 * @param hiragana Hiragana-Zeichen
	 */
	public Hiragana (int id, String romanji, String hiragana) {
		System.setProperty("file.encoding", "UTF-8");
		this.id=id;
		this.romanji=romanji;
		this.hiragana=hiragana;
	}
	/**
	 * Liefert die <code>id</code> zurück
	 * @return <code>id</code> ID
	 */
	public int get_id(){
		return id;
	}
	/**
	 * Liefert das <code>romanji</code> zurück
	 * @return <code>romanji</code> Romanji
	 */
	public String get_romanji(){
		return romanji;
	}
	/**
	 * Liefert das <code>hiragana</code> -Zeichen zurück
	 * @return <code>hiragana</code> 
	 */
	public String get_hiragana(){
		return hiragana;
	}

}