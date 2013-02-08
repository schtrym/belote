package be.vodelee.belote.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import be.vodelee.belote.entity.Contest;
import be.vodelee.belote.entity.Run;
import be.vodelee.belote.entity.Team;

public class RunControllerTest {

	private RunController runController;

	@Before
	public void init() {
		runController = new RunController();
	}

	@Test
	public void testBuildRun() {

		List<Run> runs = new ArrayList<Run>();

		List<Team> teams = new ArrayList<Team>();
		Team team = new Team();
		team.setId(1);
		teams.add(team);
		team = new Team();
		team.setId(2);
		teams.add(team);
		team = new Team();
		team.setId(3);
		teams.add(team);
		team = new Team();
		team.setId(4);
		teams.add(team);

		Run actualRun = runController.buildRun(teams, runs);
		Assert.assertEquals(2, actualRun.getGames().size());
		runs.add(actualRun);

		actualRun = runController.buildRun(teams, runs);
		Assert.assertEquals(2, actualRun.getGames().size());
		runs.add(actualRun);

		actualRun = runController.buildRun(teams, runs);
		Assert.assertEquals(2, actualRun.getGames().size());
		runs.add(actualRun);
		
	}

}
