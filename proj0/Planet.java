/**
 * A data structure representing planet
 */
public class Planet {

	//	Its current x position
	double xxPos;
	//Its current y position
	double yyPos;
	//Its current velocity in the x direction 
	double xxVel; 
	//Its current velocity in the y direction
	double yyVel;
	//Its mass 
	double mass;
	//The name of an image in the images directory that depicts the planet
	String imgFileName;

	public Planet(double xxPos, double yyPos, double xxVel,
              double yyVel, double mass, String imgFileName){
		this.xxPos = xxPos;
		this.yyPos = yyPos;
		this.xxVel = xxVel;
		this.yyVel = yyVel;
		this.mass = mass;
		this.imgFileName = imgFileName;

	}

	public Planet(Planet p){
		if (p != null) {
			xxPos = p.xxPos;
			yyPos = p.yyPos;
			xxVel = p.xxVel;
			yyVel = p.yyVel;
			mass = p.mass;
			imgFileName = p.imgFileName;
		}
	}


	public double calcDistance(Planet planet){
		double r2 = Math.pow(planet.xxPos - this.xxPos, 2) + Math.pow(planet.yyPos - this.yyPos, 2);
		return Math.sqrt(r2);
	}

	public double calcForceExertedBy(Planet planet){
		double G = 6.67 * Math.pow(10, -11);
		return G * this.mass * planet.mass / Math.pow(calcDistance(planet), 2);
	}
}