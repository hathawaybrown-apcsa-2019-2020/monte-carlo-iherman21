
/**
 * Runs a simulation of 100,000 flights and calculates number of seats, tickets sold, and no show probability and returns number of seats overbooked, number of times overbooked, and percent of times overbooked
 *
 * @author (Isy Herman)
 * @version (2019-10-24)
 */
public class AirlineMonteCarlo
{
    private final int NUM_SIMULATIONS = 100000;
    private int ticketsSold;
    private int seats;
    private double noShowProbability;
    private int overbooked;
    private double averageSeatsFilled;
    private double percentage;
    
    /**
     * Constructor for seats, tickets sold, and no show probability
     * @param seats             number of available seats
     * @param ticketsSold       number of tickets sold
     * @param noShowProbability probability of people not showing up
     */
    public AirlineMonteCarlo (int s, int t, double p)
    {
        this.seats = s;
        this.ticketsSold = t;
        this.noShowProbability = p;
    }
    
    /**
     * calculates and returns number of people who show up for their flight
     * @return numShow
     */
    public int simulateOneFlight()
    {
        int numShow = 0;
        for (int i = 1; i <= ticketsSold; i++)
        {
            if (Math.random() >= noShowProbability)
            {
               numShow = numShow + 1;
            }
        }
        return numShow;
    }

    /**
     * calculates if flight is overbooked, average seats filled, and percentage of times flight is overbooked
     */
    public void runSimulation()
    {
        int runningTotal = 0;
        overbooked = 0;
        for (int k = 1; k <= NUM_SIMULATIONS; k++)
        {
            int numShow = simulateOneFlight();
            runningTotal = runningTotal +numShow;
            
            if (numShow > seats)
            {
                overbooked = overbooked +1;
            }
        }
        averageSeatsFilled = (double) runningTotal / NUM_SIMULATIONS;
        percentage = (double) overbooked / NUM_SIMULATIONS * 100;
    }
    
    /**
     * Prints number of tickets sold, number of seats, no show probability, average seats filled, and percent and number of times of overbooked.
     */
    public void reportResults()
    {
        System.out.println("Simulation: " + ticketsSold + " tickets sold for " + seats + " seats; no-show probability = " + noShowProbability);
        System.out.println("Based on " + NUM_SIMULATIONS + " simulations: ");
        System.out.println("Average seats filled: " + averageSeatsFilled);
        System.out.println("Number of times overbooked: " + overbooked +" (" + percentage +" percent)");
    }
    
    /**
     * Runs and reports simulation with 136 seats, 140 tickets sold,0.04 no show probability
     */
    public static void main (String[] args)
    {
        AirlineMonteCarlo mySim = new AirlineMonteCarlo (136,140,0.04);
        mySim.runSimulation();
        mySim.reportResults();
    }
}