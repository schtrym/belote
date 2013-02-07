package be.vodelee.belote.entity;

import java.io.Serializable;
import java.util.List;

public class Contest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<Team> teams;
	
	List<Run> runs;

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Run> getRuns() {
		return runs;
	}

	public void setRuns(List<Run> runs) {
		this.runs = runs;
	}

	
	
}
