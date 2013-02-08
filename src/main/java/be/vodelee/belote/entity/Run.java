package be.vodelee.belote.entity;

import java.io.Serializable;
import java.util.List;

public class Run implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer runId;
	
	List<Game> games;
	
	public Integer getRunId() {
		return runId;
	}

	public void setRunId(Integer runId) {
		this.runId = runId;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "Run [games=" + games + "]";
	}
}
