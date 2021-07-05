package lpt;

import java.util.Comparator;

public class DescTimeComp implements Comparator<Job> {

	@Override
	public int compare(Job j1, Job j2) {
		return j1.getTime() - j2.getTime();
	}

}
