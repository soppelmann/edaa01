package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Holgersson {


	public static final String[] LANDSKAP = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<TextProcessor> list = new ArrayList<TextProcessor>();

		TextProcessor p = new SingleWordCounter("nils"); //initialisera första ordet
		TextProcessor p1 = new SingleWordCounter("norge"); //initialisera andra ordet

		TextProcessor l = new MultiWordCounter(LANDSKAP);


		list.add(p);
		list.add(p1);
		list.add(l);

		Scanner s = new Scanner(new File("lab1/nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for (TextProcessor textProcessor : list) {
				textProcessor.process(word);
			}
		}

		s.close();

		for (TextProcessor textProcessor : list) {
			textProcessor.report();
		}
	}
}