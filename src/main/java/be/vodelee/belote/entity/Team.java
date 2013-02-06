package be.vodelee.belote.entity;

import java.util.List;

public class Team {

	private int id;

	private String name;

	private List<Integer> scores;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getScores() {
		return scores;
	}

	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}

	public int getTotalScore() {
		if (scores == null || scores.isEmpty()) {
			return 0;
		} else {
			int total = 0;
			for (Integer i : scores) {
				int runscore = i == null ? 0 : i;
				total = total + runscore;
			}
			return total;
		}
	}
}
