package be.vodelee.belote.ihm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import be.vodelee.belote.entity.Contest;
import be.vodelee.belote.entity.Run;

public class MainScreen extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public MainScreen() {
		super("Belote Contest");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		container = getContentPane();
		// Create and add the menu Bar
		buildMenu();
		
		jtp = new JTabbedPane();
		inscriptionPanel = new JPanel();
		jtp.addTab("Equipes ", inscriptionPanel);
		container.add(jtp);
		
		Contest contest = new Contest();

		List<Run>  runs = new ArrayList<Run>();
		runs.add(new Run());
		runs.add(new Run());
		runs.add(new Run());
		runs.add(new Run());
		contest.setRuns(runs);
		
		TeamTableModel teamTableModel = new TeamTableModel(contest);
		JTable teamTable = new JTable(teamTableModel);
		JScrollPane jspTeamTable = new JScrollPane(teamTable);
		inscriptionPanel.add(jspTeamTable, BorderLayout.CENTER);
		
		pack();
	}
	

	private void buildMenu() {
		jmb = new JMenuBar();
		container.add(jmb,BorderLayout.NORTH);
		// Create and add the File menu + KeyStrokes + ActionListener
		jmFile = new JMenu("Fichier");
		jmb.add(jmFile);
		jmiNew = new JMenuItem("Nouveau tournoi");
		jmiNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//		jmiNew.addActionListener(this);
		jmiOpen= new JMenuItem("Ouvrir tournoi");
		jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
//		jmiOpen.addActionListener(this);
		jmiSave = new JMenuItem("Enregistrer");
		jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
//		jmiSave.addActionListener(this);
		jmiSaveAs = new JMenuItem("Enregistrer sous ...");
//		jmiSaveAs.addActionListener(this);
		jmiQuit = new JMenuItem("Quitter");
		jmiQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
//		jmiQuit.addActionListener(this);
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

	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jmiAbout) {
			System.out.println("test");
			JOptionPane.showMessageDialog(container, "Payez une chope à Stefan !");
		}
	}

}
