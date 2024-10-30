import java.util.ArrayList;

/**
 *  Class Musical <br>
 *  New performance are set up in this class
 */
public class Musical {

    /**
     * An ArrayList that stores the performance instances
     */
    public static ArrayList<Musical> performList = new ArrayList<>();

    private String playName;

    /**
     *  i.e., Monday, Sunday
     */
    private String day;

    /**
     * Format: yyyymmdd
     */
    private int date;

    /**
     * Format: hhmm
      */
    private int time;

    /**
     * Create a 2D array representing the seat chart for an [orchestra],
     * with each element representing a seat status (occupied 0 or available 1).
     */
    private int[][] orchestra;
    private double priceOfOrchestra;

    /**
     * Create a 2D array representing the seat chart for a [mezzanine],
     * with each element representing a seat status (occupied 0 or available 1).
     */
    private int[][] mezzanine;
    private double priceOfMezzanine;

    /**
     * Create a 2D array representing the seat chart for a [balcony],
     * with each element representing a seat status (occupied 0 or available 1).
     */
    private int[][] balcony;
    private double priceOfBalcony;



    /**
     * Constructor
     * @param playName
     * @param date
     * @param time
     * @param priceOfOrchestra
     * @param priceOfMezzanine
     * @param priceOfBalcony
     */
    public Musical(String playName, int date, int time,
                   double priceOfOrchestra, double priceOfMezzanine, double priceOfBalcony) {

        this.playName = playName;

        // param "year", "month", "day" were extracted from the param "date"
        this.day = calculateDayOfWeek(date % 4, date%4 - date%2, date % 2);
        this.date = date;
        this.time = time;
        this.orchestra = new int[15][50];
        this.priceOfOrchestra = priceOfOrchestra;
        this.mezzanine = new int[10][50];
        this.priceOfMezzanine = priceOfMezzanine;
        this.balcony = new int[10][50];
        this.priceOfBalcony = priceOfBalcony;

        performList.add(this);

    }


    /**
     * @param musical an instance of class Musical
      */
    public Musical(Musical musical) {
        this.playName = musical.playName;

        // param "year", "month", "day" were extracted from musical's var "date"
        this.day = calculateDayOfWeek(date % 4, date % 4 - date % 2, date % 2);
        this.date = musical.date;
        this.time = musical.time;
        this.orchestra = new int[15][50];
        this.priceOfOrchestra = musical.priceOfOrchestra;
        this.mezzanine = new int[10][50];
        this.priceOfMezzanine = musical.priceOfMezzanine;
        this.balcony = new int[10][50];
        this.priceOfBalcony = musical.priceOfBalcony;

    }




    /** Zeller's Congruence: given a date, calculate the day of the week for the respective day
     *
     * @param year
     * @param month
     * @param day
     * @return the day of week
     *
     * @author Zeller
     */
    public static String calculateDayOfWeek(int year, int month, int day) {

        if (month < 3) {
            month += 12;
            year --;
        }

        int h = (day + (13 * (month + 1)) / 5 + year + year / 4 - year / 100 + year / 400) % 7;

        // convert h into day of week
        String[] daysOfWeek = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        return daysOfWeek[h];
    }




    /**
     *
     * getter methods
     */

    public String getPlayName() {
        return playName;
    }

    public String getDay() {
        return day;
    }

    public int getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

    public int[][] getOrchestra() {
        return orchestra;
    }

    public double getPriceOfOrchestra() {
        return priceOfOrchestra;
    }

    public int[][] getMezzanine() {
        return mezzanine;
    }

    public double getPriceOfMezzanine() {
        return priceOfMezzanine;
    }

    public int[][] getBalcony() {
        return balcony;
    }

    public double getPriceOfBalcony() {
        return priceOfBalcony;
    }


    /**
     * setter methods
     * @param row
     * @param colum
     */
    public void setOrchestra(int row, int colum) {
        this.orchestra[row - 1][colum - 1] += 1;
    }

    public void setMezzanine(int row, int colum) {
        this.mezzanine[row - 1][colum - 1] += 1;
    }

    public void setBalcony(int row, int colum) {
        this.balcony[row - 1][colum - 1] += 1;
    }


    @Override
    public String toString() {
        return this.getPlayName();
    }


    /**
     *  only used during development stage
     */
    public static void print2DArray(int[][] array) {
        for (int[] row : array) {
            for (int item : row) {
                System.out.printf("%2d ", item);
            }
            System.out.println();
        }
    }
}
