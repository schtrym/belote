package be.vodelee.belote.controller;

import java.util.ArrayList;

import be.vodelee.belote.entity.Contest;
import be.vodelee.belote.entity.Run;
import be.vodelee.belote.entity.Team;

public class ContestController {

	public static int MAX_TEAM_ID = 1;
	
	public static Contest initNewContest() {
		Contest contest = new Contest();
		contest.setTeams(new ArrayList<Team>());
		contest.setRuns(new ArrayList<Run>());
		
		return contest;
	}
}
