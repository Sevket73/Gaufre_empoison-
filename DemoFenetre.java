import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import view.*;
import controller.*;


// L'interface runnable declare une methode run
public class DemoFenetre implements Runnable {

	JToggleButton ajouterBouton(String contenu) {
		JToggleButton IA = new JToggleButton(contenu);
		IA.setAlignmentX(Component.CENTER_ALIGNMENT);
		IA.setFocusable(false);
		return IA;
	}

	JButton ajouterBoutonPressoir(String contenu) {
		JButton IA = new JButton(contenu);
		IA.setAlignmentX(Component.CENTER_ALIGNMENT);
		IA.setFocusable(false);
		return IA;
	}

	public void run() {
		// Creation d'une fenetre
		JFrame frame = new JFrame("Ma fenetre a moi");
		int nbCarreauLargeur = 10;
		int nbCarreauHauteur = 10;
		gaufre g = new gaufre(nbCarreauHauteur,nbCarreauLargeur);
		JLabel monLabel = new JLabel("Joueur 1");
		Box boiteTexte = Box.createHorizontalBox();
		Component c1 = Box.createHorizontalStrut(290);
		Component c2 = Box.createHorizontalStrut(300);
		niveau_graphique aire = new niveau_graphique(g, monLabel, c1, c2);
		frame.add(boiteTexte, BorderLayout.NORTH);
		JToggleButton IA = ajouterBouton("IA");
		IA.addActionListener(new test(aire));
		boiteTexte.add(IA);
		String nomsIA[] = {"IA aleatoire", "IA difficile"};
		JComboBox<String> nivIA = new JComboBox<String>(nomsIA);
		nivIA.addActionListener(new GestionIA(aire));
		boiteTexte.add(nivIA);
		boiteTexte.add(Box.createGlue());
		JButton replay = ajouterBoutonPressoir("replay");
		replay.addActionListener(new replay(g, aire));
		boiteTexte.add(c1);
		boiteTexte.add(monLabel);
		boiteTexte.add(c2);
		boiteTexte.add(Box.createGlue());
		boiteTexte.add(replay);
		// Ajout du bouton save
		JButton save = ajouterBoutonPressoir("Sauvegarder");
		save.addActionListener(new save(g, aire));
		boiteTexte.add(save);

		// Ajout du bouton charger
		JButton charger = ajouterBoutonPressoir("Charger");
		charger.addActionListener(new charger(g, aire));
		boiteTexte.add(charger);

		boiteTexte.add(monLabel);
		// Ajout de notre composant de dessin dans la fenetre
		frame.add(aire);
		aire.addMouseListener(new myListener(g, aire));
		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// On fixe la taille et on demarre
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new DemoFenetre());
	}
}
