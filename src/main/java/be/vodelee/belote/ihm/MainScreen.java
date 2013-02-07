package be.vodelee.belote.ihm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import be.vodelee.belote.controller.ContestController;
import be.vodelee.belote.entity.Contest;
import be.vodelee.belote.entity.Run;
import be.vodelee.belote.entity.Team;

public class MainScreen extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Contest contest;

	private Container container;
	private JMenuBar jmb;
	private JMenu jmFile;
	private JMenuItem jmiNew;
	private JMenuItem jmiOpen;
	private JMenuItem jmiSave;
	private JMenuItem jmiSaveAs;
	private JMenuItem jmiQuit;
	private JMenu jmHelp;
	private JMenuItem jmiAbout;
	private JTabbedPane jtp;
	private JPanel inscriptionPanel;
	private JLabel totalTeamNbr;
	private JPanel inscriptionButtonsPanel;
	private JButton newTeamButton;
	private JButton deleteTeamButton;
	private JButton generateTournamentButton;

	private TeamTableModel teamTableModel;

	private JTable teamTable;

	public MainScreen(Contest contest) {
		super("Belote Contest");

		this.contest = contest;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container = getContentPane();
		// Create and add the menu Bar
		buildMenu();

		jtp = new JTabbedPane();
		inscriptionPanel = new JPanel();
		jtp.addTab("Equipes ", inscriptionPanel);
		container.add(jtp);

		totalTeamNbr = new JLabel("Nombre d'équipe = " + contest.getTeams().size());
		totalTeamNbr.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 35));
		inscriptionPanel.setLayout(new BorderLayout());
		inscriptionPanel.add(totalTeamNbr, BorderLayout.NORTH);
		// panel of inscriptionButtons
		inscriptionButtonsPanel = new JPanel();
		inscriptionButtonsPanel.setLayout(new GridLayout(8, 1, 10, 10));
		inscriptionPanel.add(inscriptionButtonsPanel, BorderLayout.WEST);
		newTeamButton = new JButton("Ajouter une équipe");
		newTeamButton.addActionListener(this);
		inscriptionButtonsPanel.add(newTeamButton);
		deleteTeamButton = new JButton("Supprimer une équipe");
		deleteTeamButton.addActionListener(this);
		inscriptionButtonsPanel.add(deleteTeamButton);
		generateTournamentButton = new JButton("Commencer le tournoi");
		generateTournamentButton.addActionListener(this);
		inscriptionButtonsPanel.add(generateTournamentButton);
		// backToInscriptionButton = new
		// JButton("Retourner dans la phase d'inscription");
		// backToInscriptionButton.addActionListener(this);
		// backToInscriptionButton.setEnabled(false);
		// inscriptionButtonsPanel.add(backToInscriptionButton);

		this.teamTableModel = new TeamTableModel(contest);
		teamTable = new JTable(teamTableModel);
		teamTable.setAutoCreateRowSorter(true);
		JScrollPane jspTeamTable = new JScrollPane(teamTable);
		inscriptionPanel.add(jspTeamTable, BorderLayout.CENTER);

		pack();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jmiAbout) {
			System.out.println("test");
			JOptionPane.showMessageDialog(container, "Payez une chope à Stefan !");
		}
		if (e.getSource() == newTeamButton) {
			Team team = new Team();
			team.setId(ContestController.MAX_TEAM_ID++);
			team.setScores(new ArrayList<Integer>());
			contest.getTeams().add(team);
			teamTableModel.fireTableDataChanged();

			// Update the label with number of teams.
			totalTeamNbr.setText("Nombre d'équipe =" + contest.getTeams().size());
		}
	}

	private void buildMenu() {
		jmb = new JMenuBar();
		container.add(jmb, BorderLayout.NORTH);
		// Create and add the File menu + KeyStrokes + ActionListener
		jmFile = new JMenu("Fichier");
		jmb.add(jmFile);
		jmiNew = new JMenuItem("Nouveau tournoi");
		jmiNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		// jmiNew.addActionListener(this);
		jmiOpen = new JMenuItem("Ouvrir tournoi");
		jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		// jmiOpen.addActionListener(this);
		jmiSave = new JMenuItem("Enregistrer");
		jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		// jmiSave.addActionListener(this);
		jmiSaveAs = new JMenuItem("Enregistrer sous ...");
		// jmiSaveAs.addActionListener(this);
		jmiQuit = new JMenuItem("Quitter");
		jmiQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		// jmiQuit.addActionListener(this);
		jmFile.add(jmiNew);
		jmFile.add(jmiOpen);
		jmFile.add(jmiSave);
		jmFile.add(jmiSaveAs);
		jmFile.add(jmiQuit);
		// Create and add the credits menu
		jmHelp = new JMenu("Aide");
		jmb.add(jmHelp);
		jmiAbout = new JMenuItem("Crédits");
		jmiAbout.addActionListener(this);
		jmHelp.add(jmiAbout);
	}
}
