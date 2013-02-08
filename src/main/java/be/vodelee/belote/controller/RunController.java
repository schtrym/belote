package be.vodelee.belote.controller;

import java.util.ArrayList;
import java.util.List;

import be.vodelee.belote.entity.Game;
import be.vodelee.belote.entity.Run;
import be.vodelee.belote.entity.Team;

public class RunController {

	public Run buildRun(List<Team> teamList, List<Run> runList) {
		Run run = new Run();
		run.setGames(new ArrayList<Game>());

		// # of game in a run = # team /2
//		int numberOfGames = (Integer) teamList.size() / 2;

		List<TeamWithAlreadyPlayedTeamList> teamWithAlreadyPlayedTeamList = buildAlreadyPlayedList(teamList, runList);

		boolean isEveryTeamAssigned = false;
		while (!isEveryTeamAssigned) {
			Game game = new Game();
			boolean isGameValid = false;
			game.setTeam1(teamWithAlreadyPlayedTeamList.remove(0).getTeam());
			int i=0;
			while (!isGameValid) {
				if (!teamWithAlreadyPlayedTeamList.get(i).getAlreadyPlayedList()
						.contains(game.getTeam1())) {
					game.setTeam2(teamWithAlreadyPlayedTeamList.remove(i).getTeam());
					isGameValid = true;
				} else {
					i++;
				}
			}
			run.getGames().add(game);

			if (teamWithAlreadyPlayedTeamList.isEmpty()) {
				isEveryTeamAssigned = true;
			}
		}

		return run;
	}

	private List<TeamWithAlreadyPlayedTeamList> buildAlreadyPlayedList(List<Team> teamList, List<Run> runList) {
		List<TeamWithAlreadyPlayedTeamList> teamWithAlreadyPlayedTeamList = new ArrayList<TeamWithAlreadyPlayedTeamList>();
		for (Team t : teamList) {
			TeamWithAlreadyPlayedTeamList couple = new TeamWithAlreadyPlayedTeamList();
			couple.setTeam(t);
			List<Team> alreadyPlayedList = new ArrayList<Team>();
			for (Run r : runList) {
				for (Game g : r.getGames()) {
					// For this game, if the team 1 is the team.
					if (g.getTeam1().equals(t)) {
						alreadyPlayedList.add(g.getTeam2());
					}
					if (g.getTeam2().equals(t)) {
						alreadyPlayedList.add(g.getTeam1());
					}
				}
			}
			couple.setAlreadyPlayedList(alreadyPlayedList);
			teamWithAlreadyPlayedTeamList.add(couple);
		}
		return teamWithAlreadyPlayedTeamList;
	}

	private class TeamWithAlreadyPlayedTeamList {
		private Team team;

		private List<Team> alreadyPlayedList;

		public Team getTeam() {
			return team;
		}

		public void setTeam(Team team) {
			this.team = team;
		}

		public List<Team> getAlreadyPlayedList() {
			return alreadyPlayedList;
		}

		public void setAlreadyPlayedList(List<Team> alreadyPlayedList) {
			this.alreadyPlayedList = alreadyPlayedList;
		}
	}
}
