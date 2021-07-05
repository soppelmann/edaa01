package lpt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Scheduler {
	private Machine[] machines;

	public void setup(){
		machines = new Machine[3];
		for (int i = 0; i < machines.length; i++) {
			machines[i] = new Machine(i + 1);
		}

		ArrayList<Job> jobList = new ArrayList<Job>();
		String [] names = {"j1", "j2", "j3", "j4", "j5", "j6", "j7"};
		int[] times = {2, 14, 4, 16, 6, 5, 3};
		for (int i = 0; i < names.length; i++) {
			jobList.add(new Job(names[i], times[i]));
		}
	}
	/** Skapar en schemaläggare för maskinerna 
		i vektorn machines. */

	public Scheduler(Machine[] machineArray) {
		Machine[] machines = machineArray;
	}
	
	/* Returnerar den maskin som har minst att göra. */
	private Machine machineWithLeastToDo() {
		int min = Integer.MAX_VALUE;
		int minPos = -1;
		for (int i = 0; i < machines.length; i++) {
			int totalTime = 0;
			totalTime += machines[i].getScheduledTime();

			if (totalTime < min) {
				min = totalTime;
				minPos = i;
			}
		}
		return machines[minPos];
	}
	
	/** Fördelar jobben i listan jobs på maskinerna. */
	public void makeSchedule(List<Job> jobs) {
		List<Job> tempJobList = new ArrayList<>(jobs);
		tempJobList.sort(new DescTimeComp());
		Collections.reverse(tempJobList);
		setup();
		for (Job j : tempJobList) {
			Machine m = machineWithLeastToDo();	
			m.assignJob(j);
		}	
	}
	
	/** Tar bort alla jobb från maskinerna. */
	public void clearSchedule() {
		for(int i = 0; i < machines.length; i++) {
			machines[i].clearJobs();
		}	
	}

	/** Skriver ut maskinernas scheman. */
	public void printSchedule() {
		for (Machine machine : machines) {
			System.out.println(machine);
		}
	}
}
