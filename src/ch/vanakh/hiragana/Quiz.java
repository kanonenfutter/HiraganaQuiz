package ch.vanakh.hiragana;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Random;
import java.util.Scanner;

public class Quiz {
	private static final int pool_max=69;
	/**
	 * Kana-Pool
	 */
	public static Hiragana[] pool = new Hiragana[pool_max];
	public static Hiragana[] quiz;
	private static Random rand = new Random();
	private static Scanner scanner = new Scanner(System.in);
	private static int score;

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, NumberFormatException {
		
		loadhiragana();
		create_set(10);
//		try {
//			int input = scanner.nextInt();
//		} catch (Exception e) {
//			System.out.println("Eingabe muss eine Zahl sein");
//		}
		String string1 = quiz[1].get_hiragana();
		String string2 = quiz[1].get_hiragana();
		String string3 = "FOO";
		String string4 = "FOO";
		System.out.println("\n" + string1 + string2);
		System.out.println(string1.compareTo(string2));
		System.out.println(string3.compareTo(string4));
		ask_hira();
		
		
		
	
	}
	
	public static String loadhiragana() throws FileNotFoundException{
		String msg;
		try {
			//FileReader fr= new FileReader("src/resources/hiraganas.txt");
			//FileReader fr= new FileReader("./hiraganas.txt");
			System.setProperty("file.encoding", "UTF-8");
			Reader in = new InputStreamReader(new FileInputStream("/Hiragana/src/resources/hiraganas.txt"), "UTF-8");
			BufferedReader br = new BufferedReader(in);
			System.out.println("Hiragana");
			for (int i = 0; i < pool.length; i++) {
				String rom_puffer= br.readLine();
				String hir_puffer= br.readLine();
				if ((rom_puffer!=null)&&(hir_puffer!=null)){
					pool[i]= new Hiragana(i+1, rom_puffer, hir_puffer);
					System.out.println(pool[i].get_romanji()+pool[i].get_hiragana());
//					progress=i;
//					System.out.print(progress);
				}
			}
			br.close();
//			System.out.print("Daten geladen..");
			msg="Zeichen erfolgreich geladen";
			
			
		}
		catch (Exception e) {
//			JOptionPane.showMessageDialog(null,
//					"Datei konnte nicht gefunden werden.",
//					"Error",
//					JOptionPane.ERROR_MESSAGE);
			msg="Fehler.";
		}
		return msg;
	}

	
	public static void create_set(int number) {
		quiz = new Hiragana[number];
		for (int i = 0; i < number; i++) {
			quiz[i]=pool[rand.nextInt(pool_max)];
			System.out.println(quiz[i].get_hiragana());
		}
	}
	public static void ask_hira(){
		for (int i = 0; i < quiz.length; i++) {
			String question = quiz[i].get_hiragana();
			String answer = quiz[i].get_romanji();
			System.out.println("Wie lautet das Romanji zu " + question + " ? ");
			String input = scanner.nextLine();
			if(input.compareTo(answer)==0){
				score+=1;
				System.out.println("Korrekt!");
			}
			else{
				System.out.println("Falsche Antwort! " + answer + " waere korrekt!");
			}
		}
		System.out.println(score);
	}
	public static int evaluate(String question, String answer){
		int point;
		System.out.println(question + answer);
		if(question.compareTo(answer)==0){
			point=1;
			System.out.println("Korrekt!");
		}
		else{
			System.out.println("Falsche Antwort! " + question + " waere korrekt!");
			point=0;
		}
		return point;
		
	}
	
	public static float result(int score, int amount){
		float percent= (float) ((score/amount)*100);
		percent= Math.round(percent);
		System.out.println(percent);
		return percent;
	}
	
}
