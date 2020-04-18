import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 * @author Josaphat Mayuba
 *
 */
public class Main implements ActionListener {

	
	public static final String[] tmois = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août",
			"Septembre", "Octobre", "Novembre", "Décembre" };

	public final static int nbrJour[] = { 31, 28, 31, 30,
			31, 30, 31, 31, 
			30, 31, 30, 31 
	};

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frame;

	private calendrier calpane;
	@SuppressWarnings("rawtypes")
	private JComboBox mois, annee, jour;
	private JButton today;
	private int anneeC = 1900;
	private int anneeT = 2100;

	
	public Main() {
		initialize();
	}

	public void actionPerformed(ActionEvent evt) {
		frame.getContentPane().repaint();
		frame.remove(calpane);
		int m = mois.getSelectedIndex();
		int a = annee.getSelectedIndex() + anneeC;
		int j = jour.getSelectedIndex() + 1;

		calpane = new calendrier(j, m, a);
		frame.getContentPane().add(calpane);
		calpane.setBounds(10, 93, 452, 382);
		if (evt.getSource() == mois || evt.getSource() == annee) {
			jour.removeAllItems();
			metjour(a, m);
			jour.setSelectedItem(j);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		System.out.println("init");
		Calendar calendar = new GregorianCalendar();

		int moisr = calendar.get(Calendar.MONTH);
		int jourr = calendar.get(Calendar.DAY_OF_MONTH);
		int anneer = calendar.get(Calendar.YEAR);

		calendar.set(Calendar.DAY_OF_MONTH, 1);

		frame = new JFrame();
		frame.setBounds(100, 100, 486, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		annee = new JComboBox();
		annee.setBackground(Color.WHITE);
                annee.setForeground(Color.PINK);
		for (int i = anneeC; i <= anneeT; i++) {
			annee.addItem(i);
		}

		annee.setSelectedItem(anneer);
		annee.addActionListener(this);
		annee.setBounds(322, 24, 104, 20);
		frame.getContentPane().add(annee);

		mois = new JComboBox(tmois);
                mois.setForeground(Color.PINK);
		mois.setBackground(Color.WHITE);
		mois.setSelectedItem(tmois[moisr]);
		mois.addActionListener(this);
		mois.setBounds(182, 24, 104, 20);
		frame.getContentPane().add(mois);

		jour = new JComboBox();
		jour.setForeground(Color.PINK);
		jour.setBackground(Color.WHITE);
		jour.setSelectedItem(jourr);
		jour.setBounds(46, 24, 104, 20);

		metjour(anneer, moisr);
		jour.setSelectedIndex(jourr - 1);
		jour.addActionListener(this);
		frame.getContentPane().add(jour);
		calpane = new calendrier(jourr, moisr, anneer);

		calpane.setBackground(Color.WHITE);
		calpane.setBounds(10, 93, 452, 382);
		frame.getContentPane().add(calpane);

		today = new JButton("Aujourd'hui");
		today.setBackground(Color.WHITE);
		today.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().repaint();
				frame.remove(calpane);
				calpane = new calendrier(jourr, moisr, anneer);
				calpane.setBounds(10, 93, 452, 382);
				jour.setSelectedItem(jourr);
				mois.setSelectedItem(tmois[moisr]);
				annee.setSelectedItem(anneer);
				frame.getContentPane().add(calpane);
			}
		});
		today.setBounds(175, 55, 127, 30);
		frame.getContentPane().add(today);

	}

	@SuppressWarnings("unchecked")
	public void metjour(int a, int m) {
		if (a % 4 == 0)
			nbrJour[1] = 29;
		else
			nbrJour[1] = 28;
		for (int i = 1; i <= nbrJour[m]; i++) {
			jour.addItem(i);

		}
	}
}
