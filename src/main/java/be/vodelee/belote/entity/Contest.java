package be.vodelee.belote.entity;

import java.util.Collection;
import java.util.List;

public class Contest {

	Collection<Team> teams;
	
	List<Run> runs;

	public Collection<Team> getTeams() {
		return teams;
	}

	public void setTeams(Collection<Team> teams) {
		this.teams = teams;
	}

	public List<Run> getRuns() {
		return runs;
	}

	public void setRuns(List<Run> runs) {
		this.runs = runs;
	}

	
	
}
