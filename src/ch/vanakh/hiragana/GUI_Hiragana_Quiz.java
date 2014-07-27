package ch.vanakh.hiragana;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JSeparator;

public class GUI_Hiragana_Quiz extends JFrame{
	private JPanel contentPane;
	private JFrame frame;
	private JLabel lblHiraganaViewer;
	private JLabel lblNewLabel;
	private JTextPane txt_hiragana;
	private JTextField txt_answer;
	private JTextPane txt_status;
	private JLabel lblScore;
	private JTextPane txt_score;
	private JTextField txt_no;
	private int sizeset;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Hiragana_Quiz window = new GUI_Hiragana_Quiz();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_Hiragana_Quiz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setResizable(false);
		setTitle("Hiragana Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/**
		 * Branding
		 */
		lblHiraganaViewer = new JLabel("Hiragana Quiz");
		lblHiraganaViewer.setHorizontalAlignment(SwingConstants.LEFT);
		lblHiraganaViewer.setFont(new Font("Dax", Font.PLAIN, 27));
		lblHiraganaViewer.setBounds(30, 64, 202, 45);
		contentPane.add(lblHiraganaViewer);
		
		lblNewLabel = new JLabel("by Vanakh Chea / vanakh.ch");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Dax", Font.PLAIN, 13));
		lblNewLabel.setBounds(30, 108, 202, 16);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("あ");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 45));
		label.setBounds(83, 21, 52, 68);
		contentPane.add(label);
		
		/**
		 * Buttons
		 */
		JButton btnquiz = new JButton("Quiz starten");
		btnquiz.setBounds(83, 178, 117, 29);
		contentPane.add(btnquiz);
		btnquiz.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					 txt_status.setText(Quiz.loadhiragana());
					 sizeset=Integer.parseInt(txt_no.getText());
					 Quiz.create_set(sizeset);
					 txt_hiragana.setText(Quiz.quiz[sizeset-1].get_hiragana());
					 txt_answer.setEnabled(true);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e2) {
					txt_status.setText("Fehler. Default-Wert 10 wird verwendet");
					txt_no.setText("10");
					
				}
				
			}
			
		});
		
		txt_hiragana = new JTextPane();
		txt_hiragana.setContentType("text/plain; charset:utf-8");
		txt_hiragana.setEditable(false);
		txt_hiragana.setBackground(UIManager.getColor("CheckBox.background"));
		txt_hiragana.setFont(new Font("Lucida Grande", Font.PLAIN, 88));
		txt_hiragana.setBounds(340, 64, 134, 104);
		contentPane.add(txt_hiragana);
		
		txt_answer = new JTextField();
		txt_answer.setEnabled(false);
		txt_answer.setToolTipText("answer");
		txt_answer.setHorizontalAlignment(SwingConstants.CENTER);
		txt_answer.setBounds(340, 203, 134, 28);
		contentPane.add(txt_answer);
		txt_answer.setColumns(10);
		txt_answer.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				int a = Integer.parseInt(txt_score.getText());
				System.out.println(a);
				int b = Quiz.evaluate(Quiz.quiz[sizeset-1].get_romanji(), txt_answer.getText());
				System.out.println(b);
				int c=a+b;
				System.out.println(c);
				txt_score.setText(Integer.toString(c));
				sizeset--;
				txt_answer.setText("");
				if(sizeset==0){
					txt_answer.setEnabled(false);
					txt_status.setText("Ergebnis: " + Quiz.result(c, Integer.parseInt(txt_no.getText()))+ "%");
				}
				else{
					txt_hiragana.setText(Quiz.quiz[sizeset-1].get_hiragana());
				}
			}
		});
		
		lblScore = new JLabel("Score:");
		lblScore.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScore.setBounds(413, 19, 61, 16);
		contentPane.add(lblScore);
		
		txt_score = new JTextPane();
		txt_score.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txt_score.setText("0");
		txt_score.setEditable(false);
		txt_score.setBounds(482, 21, 52, 14);
		contentPane.add(txt_score);

		//-----
		
		txt_status = new JTextPane();
		txt_status.setEditable(false);
		txt_status.setBounds(291, 243, 256, 29);
		contentPane.add(txt_status);
		
		txt_no = new JTextField();
		txt_no.setText("10");
		txt_no.setBounds(30, 177, 52, 28);
		contentPane.add(txt_no);
		txt_no.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(244, 40, 12, 197);
		contentPane.add(separator);
		
	}
}
