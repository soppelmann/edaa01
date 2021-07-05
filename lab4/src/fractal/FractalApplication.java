package fractal;

import koch.Koch;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[1];
		fractals[0] = new Koch(300);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
