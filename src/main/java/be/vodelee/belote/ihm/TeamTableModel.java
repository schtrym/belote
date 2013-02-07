package be.vodelee.belote.ihm;

import javax.swing.table.AbstractTableModel;

import be.vodelee.belote.entity.Contest;
import be.vodelee.belote.entity.Team;

public class TeamTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Contest contest;

	public TeamTableModel(Contest contest) {
		super();
		this.contest = contest;
	}

	public int getRowCount() {
		// The size of the table must be equal to the number of team.
		return contest.getTeams() == null ? 0 : contest.getTeams().size();
	}

	public int getColumnCount() {
		// TEAM_ID + NAME + # OF RUN + #TOTALPOINT
		return 1 + 1 + contest.getRuns().size() + 1;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (contest.getTeams() == null) {
			return null;
		} else {
			Team team = contest.getTeams().get(rowIndex);
			switch (columnIndex) {
			case 0:
				return team.getId();
			case 1:
				return team.getName();
			}

			for (int runIndex = 0; runIndex < contest.getRuns().size() ; runIndex++) {
				if (columnIndex == runIndex + 2) {
					return team.getScores().get(runIndex);
				}
			}

			if (columnIndex == getColumnCount() - 1) {
				return team.getTotalScore();
			}
			return null;
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Team team = contest.getTeams().get(rowIndex);
		
		if (columnIndex == 1) {
			team.setName(aValue.toString());
		}

		for (int runIndex = 0; runIndex < contest.getRuns().size(); runIndex++) {
			if (columnIndex == runIndex + 2) {
				team.getScores().set(runIndex, (Integer) aValue);
			}
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);
		// update too the total
		fireTableCellUpdated(rowIndex, getColumnCount()-1);
	}

	
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0) {
			return Integer.class;
		}
		if (columnIndex == 1) {
			return String.class;
		}

		for (int runIndex = 0; runIndex < contest.getRuns().size(); runIndex++) {
			if (columnIndex == runIndex +2) {
				return Integer.class;
			}
		}

		if (columnIndex == getColumnCount() - 1) {
			return Integer.class;
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "Numéro";
		}
		if (column == 1) {
			return "Nom";
		}

		for (int runIndex = 0; runIndex < contest.getRuns().size(); runIndex++) {
			if (column == runIndex + 2) {
				return "Tour n°" + (runIndex + 1);
			}
		}

		if (column == getColumnCount() - 1) {
			return "Total";
		}
		return null;

	}

	public boolean isCellEditable(int row, int col) {
		if (col > 0 && col < getColumnCount() - 1) {
			return true;
		}
		else {
			return false;
		}
	}
}
