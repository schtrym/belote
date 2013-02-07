package be.vodelee.belote.runner;

import be.vodelee.belote.controller.ContestController;
import be.vodelee.belote.entity.Contest;
import be.vodelee.belote.ihm.MainScreen;

public class Runner {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Contest contest = ContestController.initNewContest();
		new MainScreen(contest).setVisible(true);
	}
}
