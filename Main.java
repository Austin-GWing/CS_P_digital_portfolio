import java.util.Scanner;

/* Abbreviation index ( readability enhancement )

    sel = selected

    perform = performance
    performList = performance list

 */

/*
 * This program is designed for the ticket office of The Grand Theater (name of the theater).
 *
 * ArrayList and 2D array are utilized
 * Multiple procedures (methods) are utilized
 *
 *
 * input: User will input with a scanner "userInput" and guidelines.
 *                  Input process contains several steps,
 *                  1. choosing a performance
 *                  2. choosing zone and seat
 *
 *
 * output: Ticket information: Theater, Name of Performance, Date & Day & Time, Seat & Zone, Ticket Price
 *
 */


public class Main {

    // These are assistant methods that are used in main method.


    /** isAvailable()
     *
     * @param selPerform
     * @param selRow
     * @param selColum
     * @return Whether the seat, selected by the ticket buyer, is available. <br>
     * i.e., if the seat [5][7] is already occupied, and the ticket buyer selected it, the method returns false.
     */
    static boolean isSeatAvailable(String selPerform, String selZone, int selRow, int selColum) {
        for (Musical performance : Musical.performList) { // locate the selected performance on performList
            if(performance.getPlayName().equals(selPerform)) { // define which as local var "performance"
                int[][] currentSeat = null;
                if (selZone.equalsIgnoreCase("O")) {
                    currentSeat = performance.getOrchestra();

                } else if (selZone.equalsIgnoreCase("M")) {
                    currentSeat = performance.getMezzanine();

                } else if (selZone.equalsIgnoreCase("B")) {
                    currentSeat = performance.getBalcony();

                }

                if (currentSeat[selRow][selColum] == 0) {
                    return true;
                }
            }
        }
        return false;
    }



    /** isInPerformList() <br>
     * Verify if the performance selected by the ticket buyer is listed in the performance list.
     *
     * @param selPerform
     * @return true if the selected performance is listed in the performList
     */
    static boolean isInPerformList(String selPerform) {
        for (Musical performInList : Musical.performList) {
            if (performInList.getPlayName().equals(selPerform)) {
                return true;
            }
        }
        return false;
    }






    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);


        // Schedule some performance
        Musical ThePhantomOfTheOpera = new Musical("The Phantom of the Opera", 20240705, 2000,
                1000,800,600);

        Musical Cat = new Musical("Cat", 20250101, 1900,
                1100, 700, 600);






        /**
         *  Set up a while loop for the whole ticket-buying procedure
         *  to ensure that the seats availability will be updated after each purchase.
         *  (If re-run this program, the previous operations will disappear)
         */
        boolean continueBuyTicket = true;
        while (continueBuyTicket) {

        // Start to buy ticket
            System.out.println("Press Enter to book ticket");
            userInput.nextLine();

            // Display all available performances for ticket purchase.
            for (Musical item : Musical.performList) {
                System.out.print(" [" + item.toString() + "] ");
            }
            System.out.println();

            // User enter a performance
            System.out.println("Please select a performance then enter: ");
            String selPerform = userInput.nextLine();
            System.out.println();

            // Verify if the user entered the performance that is listed in the performList;
            // if not, reselect.
            while(!isInPerformList(selPerform)) { // Call method isInPerformList()
                System.out.print("Your option is not on the list, please choose another one: ");

                // User enter again
                selPerform = userInput.nextLine();
            }
            System.out.println();

            System.out.println("You have selected the performance: " + selPerform);




        // Seat Selection

            // select zone
            System.out.print("Please select the zone you prefer: Orchestra, Mezzanine, Balcony." +
                             "(enter the capitalized, initial letter): ");

            String selZone = userInput.next();
            // Loop to ensure that the ticket buyer enter the correct letter.
            while(!(selZone.contains("O") || selZone.contains("M") || selZone.contains("B"))){
                System.out.println("Your selected zone does not exist, please try again.");
                selZone = userInput.next();
            }


            // select seat, Row & Colum
            System.out.print("Select your seat row and colum, row: ");
            int selRow = userInput.nextInt();

            System.out.print("Select your seat row and colum, colum: ");
            int selColum = userInput.nextInt();

            /*  To verify if the ticket buyer selected the seat is available or not, by using while loop;
             *  if not available, reselect a seat until the seat available is selected.
             */
            while (!isSeatAvailable(selPerform, selZone, selRow, selColum)) {
                System.out.println("The seat you chose is already occupied, please choose another one.");

                System.out.print("Please select the zone you prefer, enter the initial letter: ");
                selZone = userInput.next();

                System.out.print("Select your seat row and colum, row: ");
                selRow = userInput.nextInt();

                System.out.print("Select your seat row and colum, row: ");
                selColum = userInput.nextInt();
            }
            System.out.println();


            // The ticket buyer successfully booked a ticket. Print notification & set continuer as true.
            System.out.print("Succeeded! Now, you may press Enter to print out your ticket.");
            userInput.nextLine();
            userInput.nextLine();
            System.out.println();

            // Create a Musical object "selPerformObj", that will be used afterward
            Musical selPerformObj = null;
            for (Musical perform : Musical.performList) {
                if (perform.getPlayName().equals(selPerform)) {
                    selPerformObj = new Musical(perform);
                }
            }




            // To store the price of the ticket
            double priceInform;


        /*
         * By now, the program has finished collecting information from the ticket buyer.
         * From now on, the program will focus on processing this information
         *
         * The following if-else statement is to
         * 1. complete selZone. (i.e., before: selZone = "M"; after: selZone = "Mezzanine";)
         * 2. store the price of the ticket. (i.e., The price of musical Cat at zone orchestra is 1100.)
         * 3. update the seat availability. ( ticket (Cat, balcony[1][5]) ==> set Cat.balcony[0][4] as 1 (occupied))
         */

            if (selZone.equalsIgnoreCase("O")) {
                selZone = "Orchestra";
                priceInform = selPerformObj.getPriceOfOrchestra();

                // loop for updating orchestra
                for (Musical perf : Musical.performList) {
                    if (perf.getPlayName().equals(selPerform)) {
                        perf.setOrchestra(selRow,selColum);
                    }
                }

            } else if (selZone.equalsIgnoreCase("M")) {
                selZone = "Mezzanine";
                priceInform = selPerformObj.getPriceOfMezzanine();

                // loop for updating mezzanine
                for (Musical perf : Musical.performList) {
                    if (perf.getPlayName().equals(selPerform)) {
                        perf.setMezzanine(selRow,selColum);
                    }
                }

            } else {
                selZone = "Balcony";
                priceInform = selPerformObj.getPriceOfBalcony();

                // loop for updating balcony
                for (Musical perf : Musical.performList) {
                    if (perf.getPlayName().equals(selPerform)) {
                        perf.setBalcony(selRow,selColum);
                    }
                }
            }




            // The following code segment is to print out all information of the ticket.

            System.out.println();
            System.out.println("Grand Theater" + "\n" +
                    "Performance: " + selPerform + "\n" +
                    "Date: " + selPerformObj.getDate() + " _ " + selPerformObj.getDay() + "\n" +
                    "Time: " + selPerformObj.getTime() + "\n" + "\n" +
                    "Seat: " + selZone + "  Row " + selRow + "  Colum " + selColum + "\n" +
                    "Ticket Price: " + priceInform);
            System.out.println();





            // Asking whether to book more tickets or not.
            // Enter "yes" to book more, enter "no" to stop running the program

            System.out.println("Do you want to book more tickets? (yes/no)");
            if (userInput.next().equals("no")) {
                continueBuyTicket = false; // to stop the program
            }
            System.out.println("\n" + "\n");

        }

    }

}
