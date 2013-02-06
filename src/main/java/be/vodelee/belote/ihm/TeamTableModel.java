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
			
			
			for (int runIndex = 2 ; runIndex <= 2+ contest.getRuns().size() -1; runIndex ++) {
				if (columnIndex == runIndex) {
					return team.getScores().get(runIndex - 2);
				}
			}
			
			if (columnIndex == getColumnCount() -1) {
				return team.getTotalScore();
			}
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "Numéro";
		}
		if (column == 1) {
			return "Nom";
		}
		
		for (int runIndex = 2 ; runIndex <= 2+ contest.getRuns().size() -1; runIndex ++) {
			if (column == runIndex) {
				return "Tour n°"+ (runIndex-1);
			}
		}
		
		if (column == getColumnCount() -1) {
			return "Total";
		}
		return null;
		
	}

	
}
