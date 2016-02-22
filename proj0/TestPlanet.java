public class TestPlanet {
	
	 public static void main(String[] args) {
        testPairForceOfTwoPlanets();
    }

	public static void testPairForceOfTwoPlanets(){
		Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");

        System.out.println(p1.calcForceExertedBy(p2));
        
	}
}