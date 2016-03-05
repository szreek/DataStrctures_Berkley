import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.Scanner;
import java.util.regex.*;
import java.util.function.*;

public class NBody {

	private static final int RADIUS_POSITION = 2;
	private static final int NUM_METADATA_ROWS = 2;
	private static int numPlanets;
	private static double uniRadius;


	public static void main(String[] args) {
		double t = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		uniRadius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		drawMovingUniverse(planets, t, dt);
		printCurrentStateOfUniverse(planets);
	}


	/**
	* A method that reads universe radius from
	* planets data file
	* @param  path to planets.txt
	* @return radius
	**/
	public static double readRadius(String path){
		double uniRadius = 0.0;
		In stream = openStream(path);
		for (int i = 1; i <= NUM_METADATA_ROWS; i++) {
			if (i % RADIUS_POSITION != 0) {
				numPlanets = stream.readInt();
			} else {
				uniRadius = stream.readDouble();
			}
		}
		return uniRadius;
	}

	/**
	* A method that reads Planets from
	* planets data file
	* @param path to planets.txt
	* @return array of planets
	**/
	public static Planet[] readPlanets(String path){
		In stream = openStream(path);
		numPlanets = stream.readInt();
		stream.close();

		stream = openStream(path);
		Planet[] planets = new Planet[numPlanets];
		String[] data = stream.readAllLines();

		for (int i = 0; i < numPlanets; i++){
			planets[i] = strLineToPlanet(data[NUM_METADATA_ROWS+i]);

		}
		stream.close();
		return planets;
	}

		/**
		* A method that creates an animation
		* presenting planet movements for a given amount of time t.
		* Each movement reduces remaining time by time period dt.
		* @param array of planets
		* @param time interval
		* @param time of a single movement  
		**/
		public static void drawMovingUniverse(Planet[] planets, double time, double timeDiff){
		Double[] xForces = null;
		Double[] yForces = null;
			
		for (double i = 0; i < time ; i = i + timeDiff) {
			xForces = Arrays.asList(planets).stream()
								  			.mapToDouble(p -> p.calcNetForceExertedByX(planets))
								  			.boxed()
								  			.collect(Collectors.toCollection(ArrayList::new))
								  			.toArray(new Double[planets.length]);

			yForces = Arrays.asList(planets).stream()
								  			.mapToDouble(p -> p.calcNetForceExertedByY(planets))
								  			.boxed()
								  			.collect(Collectors.toCollection(ArrayList::new))
								  			.toArray(new Double[planets.length]);								  		

			
			updatePlanetMovements(planets, xForces, yForces, timeDiff);
			drawBackground();
			drawPlanets(planets);
			setAnimationPauseInterval(10);			
		} 
	}

	/**
	* A method that prints state of the lanets in the Universe
	* @param array of planets
	**/
	public static void printCurrentStateOfUniverse(Planet[] planets){
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", uniRadius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}	
	}
	
	//#################_private_helper_methods_######################
	private static void drawBackground(){
		StdDraw.setScale(-uniRadius, uniRadius);
		StdDraw.clear();
		StdDraw.picture(0, 75, "./images/starfield.jpg");
	}

	private static void drawPlanets(Planet[] planets){
		Arrays.asList(planets).forEach(p -> p.draw());
	}

	private static void updatePlanetMovements(Planet[] planets, Double[] xForces, Double[] yForces, double timeDiff){
		for (int j = 0; j < planets.length; j++) planets[j].update(timeDiff, xForces[j], yForces[j]);
	}

	private static void setAnimationPauseInterval(int interval){
		StdDraw.show(10);
	}

	private static In openStream(String path){
		In instream = new In(path);
		if (!instream.exists()) {
			throw new IllegalArgumentException("No stream with the given path: " + path);
		}
		return instream;
	}


	private static Planet strLineToPlanet(String strPlanet){
		String[] tokens = strPlanet.split("\\s+");
		String imgFileName = tokens[tokens.length - 1];

		Arrays.asList(tokens).forEach(x->System.out.println(x));

		Double[] values = Arrays.asList(tokens).stream()
									.filter(s -> !Pattern.matches("^$|(.*\\.gif)", s))
									.map(x -> Double.parseDouble(x))
									.collect(Collectors.toCollection(ArrayList::new)).toArray(new Double[tokens.length - 1]);


		return new Planet(values[0], values[1], values[2], values[3], values[4], imgFileName);
	}

}