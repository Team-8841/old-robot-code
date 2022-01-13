package team1065.robot.lib;

public class Util {
	// Prevent this class from being instantiated.
    private Util() {
    }
    
    /**
     * Limits the given input to the given magnitude.
     */
    public static double limit(double v, double limit) {
        return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
    }

}
