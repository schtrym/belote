package be.vodelee.belote.entity;

import java.io.Serializable;


public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Team team1;
	
	Team team2;
	
	Integer scoreTeam1;

	Integer scoreTeam2;

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public Integer getScoreTeam1() {
		return scoreTeam1;
	}

	public void setScoreTeam1(Integer scoreTeam1) {
		this.scoreTeam1 = scoreTeam1;
	}

	public Integer getScoreTeam2() {
		return scoreTeam2;
	}

	public void setScoreTeam2(Integer scoreTeam2) {
		this.scoreTeam2 = scoreTeam2;
	}
	
	
}
