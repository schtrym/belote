package be.vodelee.belote.entity;

import java.util.List;

public class Contest {

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
