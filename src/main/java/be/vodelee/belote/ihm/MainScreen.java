package be.vodelee.belote.ihm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.xml.transform.Templates;

import be.vodelee.belote.controller.ContestController;
import be.vodelee.belote.controller.RunController;
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
	private List<JPanel> runPanelList;
	private JPanel inscriptionPanel;
	private JLabel totalTeamNbr;
	private JPanel inscriptionButtonsPanel;
	private JButton newTeamButton;
	private JButton deleteTeamButton;
	private JButton generateOneRun;
	private JButton removeOneRun;

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
		runPanelList = new ArrayList<JPanel>();
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
		generateOneRun = new JButton("Generer un tour");
		generateOneRun.addActionListener(this);
		inscriptionButtonsPanel.add(generateOneRun);
		removeOneRun = new JButton("Supprimer le derniertour");
		removeOneRun.addActionListener(this);
		inscriptionButtonsPanel.add(removeOneRun);

		this.teamTableModel = new TeamTableModel(contest);
		teamTable = new JTable(teamTableModel);
		teamTable.getTableHeader().setReorderingAllowed(false);
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
		if (e.getSource() == deleteTeamButton) {
			List<Team> teamsToRemove = new ArrayList<Team>();
			for (int rowIndex : teamTable.getSelectedRows()) {
				// retrieve the team id
				int teamId = (Integer) teamTable.getValueAt(rowIndex, 0);
				Team teamToRemove = new Team();
				teamToRemove.setId(teamId);
				teamsToRemove.add(teamToRemove);
			}
			contest.getTeams().removeAll(teamsToRemove);

			teamTableModel.fireTableDataChanged();

			// Update the label with number of teams.
			totalTeamNbr.setText("Nombre d'équipe =" + contest.getTeams().size());
		}
		if (e.getSource() == generateOneRun) {
			if (contest.getTeams().size() == 0) {
				JOptionPane.showMessageDialog(container, "Il faut au moins 2 équipe pour générer un tour !");
			} else if (contest.getTeams().size() % 2 != 0) {
				JOptionPane.showMessageDialog(container, "Il faut un nombre d'équipe pair pour générer un tour !");
			} else {
				newTeamButton.setEnabled(false);
				deleteTeamButton.setEnabled(false);
				Run run = new RunController().buildRun(contest.getTeams(), contest.getRuns());
				run.setRunId(contest.getRuns().size());
				for (Team t : contest.getTeams()) {
					t.getScores().add(null);
				}
				contest.getRuns().add(run);

				// Build interface;
				JPanel runPanel = new JPanel();
				RunTableModel runTableModel = new RunTableModel(run, teamTableModel);
				JTable runTable = new JTable(runTableModel);
				runTable.getTableHeader().setReorderingAllowed(false);
				JScrollPane runSP = new JScrollPane(runTable);
				runPanel.add(runSP, BorderLayout.CENTER);
				jtp.add("Tour n°" + contest.getRuns().size(), runPanel);

				// Update the table structure.
				teamTableModel.fireTableStructureChanged();
				removeOneRun.setEnabled(true);
			}
		}
		if ( e.getSource() == removeOneRun ){
			jtp.remove(contest.getRuns().size());
			contest.getRuns().remove(contest.getRuns().size()-1);
			teamTableModel.fireTableStructureChanged();
			if (contest.getRuns().size() == 0) {
				newTeamButton.setEnabled(true);
				deleteTeamButton.setEnabled(true);
				removeOneRun.setEnabled(false);
			}
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
