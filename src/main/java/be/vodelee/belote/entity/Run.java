package be.vodelee.belote.entity;

import java.io.Serializable;
import java.util.List;

public class Run implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Game> games;

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	
}
