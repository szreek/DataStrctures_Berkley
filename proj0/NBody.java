public class NBody {

	private static final int RADIUS_POSITION = 1;
	private static In instream;

	/**
	* A method that reads universe radius from path
	* to planets data file
	**/
	public static double readRadius(String path){
		String[] lines =  readLines(path);
		if (lines[RADIUS_POSITION] == null) throw new IllegalStateException("There is no radius in planets data file");		
		return Double.parseDouble(lines[RADIUS_POSITION]);
	}


	
	//#################_private_helper_methods_######################
	private static String[] readLines(String path){
		String[] lines = null;
		In instream = new In(path);
		if (instream.exists()) {
			lines = instream.readAllLines();
		}
		return lines;
	}

}