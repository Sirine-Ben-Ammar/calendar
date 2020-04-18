import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;

/**
 * @author Josaphat Mayuba
 *
 */
@SuppressWarnings("serial")
public class calendrier extends JPanel {
	
    
	int jour, mois, annee, dom;
	Calendar calendar;

	
	public calendrier(int jour, int mois, int annee) {
		repaint();
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;

	}

	
	private static final int TAILLE = 60;

	static int ymoni = 500;

	public static int postitle = 450;

	public static final String[] jours = { "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim" };

	
	public final static int DernierJour[] = { 31, 28, 31,
			30, 
			31, 30, 31, 31, 
			30, 31, 30, 31 
	};

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 500, 500);
		
		int first = new GregorianCalendar(annee, mois, 0).get(Calendar.DAY_OF_WEEK) - 1;
		
		int ajustx = 30;

		int jrcount = 1;
		g.setColor(Color.PINK);
		g.fillRect(0, 0, ymoni, 40);
		
		if (annee % 4 == 0)
			DernierJour[1] = 29;
		else
			DernierJour[1] = 28;
		for (int i = 0; i < jours.length; i++) {
			g.setColor(Color.white);
			g.drawString(jours[i], i * TAILLE + ajustx, 25);

		}
		int monte = 50;
	
		g.setColor(Color.PINK);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < jours.length; j++) {

				if ((j >= first || i > 0) && jrcount <= DernierJour[mois])
					if (jrcount == jour) {
						if (jour < 10)
							g.fillRect(j * TAILLE + ajustx - 2, i * TAILLE + monte, 20, 30);
						else
							g.fillRect(j * TAILLE + ajustx - 2, i * TAILLE + monte, 30, 30);
						g.setColor(Color.white);
						g.drawString("" + jrcount++, j * TAILLE + ajustx + 5, i * TAILLE + monte + 20);
					} else {
						g.setColor(Color.PINK);
						g.drawString("" + jrcount++, j * TAILLE + ajustx + 5, i * TAILLE + monte + 20);

					}
			}
		}

	}

}