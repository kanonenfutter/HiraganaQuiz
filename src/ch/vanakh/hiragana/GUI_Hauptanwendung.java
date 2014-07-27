package ch.vanakh.hiragana;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
/**
 * Grafische Oberfläche von HiraganaViewer
 * @author vanakh
 *
 */
public class GUI_Hauptanwendung extends JFrame {
	private JPanel contentPane;
	private  JTextField txt_input;
	private JTextPane txt_hiragana;
	private JTextPane txt_romanji;
	public static int pool_max=69;
	/**
	 * Array <code>pool</code>. Beinhaltet die Objekte der Klasse <code>Hiragana</code>
	 */
	public static Hiragana[] pool = new Hiragana[pool_max];
	private JButton bt_anzeigen;
	private  JButton bt_next;
	private  JButton bt_back;
	private JTextField txt_debug;
	private JLabel lblHiraganaViewer;
	private JLabel lblNewLabel;
	public static int progress;
	public static JProgressBar progressBar;
	public static String defaultCharacterEncoding;
	/**
	 * Überprüft, ob das erste/letzte Element angezeigt wird.
	 * Wenn wahr, dann werden die Navigationsbuttons entsprechend
	 * de-/aktiviert.
	 */
	public void check_index() {
		int index=Integer.parseInt(txt_input.getText());
		System.out.print(index);
		bt_back.setEnabled(true);
		bt_next.setEnabled(true);
		if (index == 0){
			bt_back.setEnabled(false);
			System.out.println("-1. Element");
		}
		else if (index==pool.length-1){
			bt_next.setEnabled(false);
			System.out.println("-Letztes Element");
		}
		else {
			bt_back.setEnabled(true);
			bt_next.setEnabled(true);
			System.out.println("-OK");
		}
			
	}
	
	/*
	 * Hit Show Kana button.
	 */
	public void show_kana(){
		bt_anzeigen.setVisible(false);
		bt_anzeigen.doClick();
		bt_anzeigen.setVisible(true);
	}
	


	/**
	 * Launch the application.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Hauptanwendung frame = new GUI_Hauptanwendung();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.setProperty("file.encoding", "UTF-8");
		
		/*
		 * Reads the Hiraganas
		 */
		try {
			//FileReader fr= new FileReader("src/resources/hiraganas.txt");
			//FileReader fr= new FileReader("./hiraganas.txt");
			//Reader in = new InputStreamReader(new FileInputStream("src/resources/hiraganas.txt"), "UTF-8");
			Reader in = new InputStreamReader(new FileInputStream("src/resources/hiraganas.txt"), "UTF-8");
			BufferedReader br = new BufferedReader(in);
			System.out.println("Hiragana");
			for (int i = 0; i < pool.length; i++) {
				String rom_puffer= br.readLine();
				String hir_puffer= br.readLine();
				if ((rom_puffer!=null)&&(hir_puffer!=null)){
					pool[i]= new Hiragana(i+1, rom_puffer, hir_puffer);
					System.out.println(pool[i].get_romanji()+pool[i].get_hiragana());
					progress=i;
					System.out.print(progress);
				}
			}
			br.close();
			System.out.print("Daten geladen..");
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Datei konnte nicht gefunden werden.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			System.out.print("Fehler.");
		}
		defaultCharacterEncoding = System.getProperty("file.encoding");
        System.out.println("\ndefaultCharacterEncoding by property: " + defaultCharacterEncoding);
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	public GUI_Hauptanwendung() {
		setResizable(false);
		setTitle("Hiragana Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bt_anzeigen = new JButton("Show Kana");
		bt_anzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int input=Integer.parseInt(txt_input.getText());
					txt_hiragana.setText(pool[input-1].get_hiragana());
					txt_romanji.setText(pool[input-1].get_romanji());
					check_index();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Entweder wurde keine Zahl eingegeben oder es konnte kein Zeichen gefunden werden.",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					System.out.print("Fehler.");
					txt_input.setText("1");
					show_kana();
				}
			}
		});
		bt_anzeigen.setBounds(218, 243, 117, 29);
		contentPane.add(bt_anzeigen);
		
		txt_input = new JTextField();
		txt_input.setAlignmentY(1.0f);
		txt_input.setAlignmentX(1.0f);
		txt_input.setText("1");
		txt_input.setBounds(110, 242, 32, 28);
		contentPane.add(txt_input);
		txt_input.setColumns(10);
		
		JLabel lblHiragana = new JLabel("Hiragana:");
		lblHiragana.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHiragana.setBounds(260, 77, 77, 16);
		contentPane.add(lblHiragana);
		
		txt_hiragana = new JTextPane();
		txt_hiragana.setContentType("text/plain; charset:utf-8");
		txt_hiragana.setEditable(false);
		txt_hiragana.setBackground(UIManager.getColor("CheckBox.background"));
		txt_hiragana.setFont(new Font("Lucida Grande", Font.PLAIN, 88));
		txt_hiragana.setBounds(360, 17, 137, 104);
		contentPane.add(txt_hiragana);
		
		JLabel lblRomanji = new JLabel("Romanji:");
		lblRomanji.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRomanji.setBounds(260, 178, 77, 16);
		contentPane.add(lblRomanji);
		
		txt_romanji = new JTextPane();
		txt_romanji.setBackground(UIManager.getColor("CheckBox.background"));
		txt_romanji.setFont(new Font("Lucida Grande", Font.BOLD, 35));
		txt_romanji.setEditable(false);
		txt_romanji.setBounds(367, 161, 60, 45);
		contentPane.add(txt_romanji);
		
		bt_next = new JButton(">>");
		bt_next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index=Integer.parseInt(txt_input.getText());
				index+=1;
				String new_index=String.valueOf(index);
				txt_input.setText(new_index);
				show_kana();
			}
		});
		
		bt_back = new JButton("<<");
		bt_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index=Integer.parseInt(txt_input.getText());
				index-=1;
				String new_index=String.valueOf(index);
				txt_input.setText(new_index);
				show_kana();
			}
		});
		bt_back.setBounds(46, 243, 52, 29);
		contentPane.add(bt_back);
		bt_next.setBounds(154, 243, 52, 29);
		contentPane.add(bt_next);
		
		txt_debug = new JTextField();
		txt_debug.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txt_debug.setEnabled(false);
		txt_debug.setText(defaultCharacterEncoding + "か" + pool[0].get_hiragana());
		txt_debug.setEditable(false);
		txt_debug.setBounds(30, 189, 195, 28);
		//contentPane.add(txt_debug);
		txt_debug.setColumns(10);
		
		lblHiraganaViewer = new JLabel("Hiragana Viewer");
		lblHiraganaViewer.setHorizontalAlignment(SwingConstants.LEFT);
		lblHiraganaViewer.setFont(new Font("Dax", Font.PLAIN, 27));
		lblHiraganaViewer.setBounds(30, 64, 202, 45);
		contentPane.add(lblHiraganaViewer);
		
		lblNewLabel = new JLabel("by Vanakh Chea / vanakh.ch");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Dax", Font.PLAIN, 13));
		lblNewLabel.setBounds(30, 108, 202, 16);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 230, 541, 29);
		contentPane.add(separator);
		
		progressBar = new JProgressBar();
		progressBar.setMaximum(120);
		progressBar.setValue(30);
		progressBar.setBounds(30, 144, 195, 20);
		//contentPane.add(progressBar);
		
		JLabel label = new JLabel("あ");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 45));
		label.setBounds(110, 21, 52, 68);
		contentPane.add(label);
		
		JButton btnRandomKana = new JButton("Random Kana");
		btnRandomKana.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Random rand= new Random();
				String rand_input=String.valueOf(rand.nextInt(pool.length-1));
				txt_input.setText(rand_input);
				show_kana();
			
			}
		});
		btnRandomKana.setBounds(345, 243, 117, 29);
		contentPane.add(btnRandomKana);
		
		//check_index();
		show_kana();
	}
}