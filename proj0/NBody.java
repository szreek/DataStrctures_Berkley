public class NBody {

	private static final int RADIUS_POSITION = 1;

	/**
	* A method that reads universe radius from path
	* to planets data file
	**/
	public static double readRadius(String path){
		double uniRadius = 0.0;
		In stream = openStream(path);
		for (int i = 1; i < 3; i++) {
			if (i % 2 != 0) {
				stream.readInt();
			} else {
				uniRadius = stream.readDouble();
			}
		}
		return uniRadius;
	}


	public static Planet[] readPlanets(String path){
		return null;
	}
	
	//#################_private_helper_methods_######################
	private static In openStream(String path){
		In instream = new In(path);
		if (!instream.exists()) {
			throw new IllegalArgumentException("No stream with the given path: " + path);
		}
		return instream;
	}

}