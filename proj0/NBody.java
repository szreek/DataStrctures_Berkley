import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.Scanner;
import java.util.regex.*;

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
		drawBackground();
	}


	public static void drawBackground(){
		StdDraw.setScale(0, 0);
		StdDraw.clear();
		StdDraw.picture(0, 75, "./images/starfield.jpg");
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
	
	//#################_private_helper_methods_######################
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