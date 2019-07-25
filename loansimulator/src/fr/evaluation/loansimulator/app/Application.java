package fr.evaluation.loansimulator.app;

/**
 * Main application of the amortization simulator.
 * 
 * @author Thierry VILLEPREUX
 */
public class Application {

    /**
     * Main method.
     * <p>
     * Runs the application by calling static {@code run()} method of the
     * simulator.
     * 
     * @param args no args needed, as everything is given by the user.
     */
    public static void main(String[] args) {
	Simulator.run();
    }
}
