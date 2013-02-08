package be.vodelee.belote.ihm;

import javax.swing.table.AbstractTableModel;

import be.vodelee.belote.entity.Game;
import be.vodelee.belote.entity.Run;

public class RunTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Run run;

	private TeamTableModel teamTableModel;

	public RunTableModel(Run run, TeamTableModel teamTableModel) {
		super();
		this.teamTableModel = teamTableModel;
		this.run = run;
	}

	public int getRowCount() {
		return run.getGames().size();
	}

	public int getColumnCount() {
		// TEAM_ID + NAME + NAME + Team_ID
		return 4;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Game game = run.getGames().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return game.getTeam1().getId();
		case 1:
			return game.getTeam1().getName();
		case 2:
			return game.getTeam2().getName();
		case 3:
			return game.getTeam2().getId();
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// Nothing to do
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0 || columnIndex == 3) {
			return Integer.class;
		}
		if (columnIndex == 1 || columnIndex == 2) {
			return String.class;
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "ID Equipe 1";
		}
		if (column == 1) {
			return "Nom Equipe 1";
		}
		if (column == 2) {
			return "Nom Equipe 2";
		}
		if (column == 3) {
			return "ID Equipe 2";
		}
		return null;

	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	
}
