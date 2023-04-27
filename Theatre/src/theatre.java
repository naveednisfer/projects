import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class theatre {
    private static final int ROWS = 3; // creates 3 rows
    private static final int[] SEATS_PER_ROW = {12, 16, 20}; // row 1 has 12 seats, row 2 has 16 seats, row 3 has 20 seats
    private static final int SEATING_CAPACITY = Arrays.stream(SEATS_PER_ROW).sum();
    private static final int[][] seats = new int[ROWS][];



    public static void main(String[] args) {
        for (int i = 0; i < ROWS; i++) {
            seats[i] = new int[SEATS_PER_ROW[i]];
        }


        //int free = 0;
        //int sold = x;


        Scanner sc = new Scanner(System.in);
        System.out.println("welcome to the new theatre!");


        int option = -1;
        while (option != 0) { // while the user doesn't select 0 as an option the menu should be presented
            System.out.println("please select an option:");
            System.out.println("1) buy a ticket");
            System.out.println("2) print seating area");
            System.out.println("3) cancel ticket");
            System.out.println("4) list available seats");
            System.out.println("5) save to file");
            System.out.println("6) load from file");
            System.out.println("7) print ticket information and total price");
            System.out.println("8) sort tickets by price");
            System.out.println("0) quit");
            System.out.println("Enter:");
            option = sc.nextInt();

            switch (option) { // using a switch case to create the options
                case 1:
                    buy_ticket();
                    break;
                case 2:
                    seating_area();
                    break;
                case 3:
                    cancel_ticket();
                    break;
                case 4:
                    available_seats();
                    break;
                case 5:
                    save_file();
                    break;
                case 6:
                    load_file();
                    break;
                case 7:
                    info_price();
                    break;
                case 8:
                    //sort_tickets();
                    break;
                case 0:
                    System.out.println("goodbye");
                    break;
                default:
                    System.out.println("invalid option, try again");
                    break;


            }
        }
        sc.close();
    }



    private static void buy_ticket() {// class for the buy_ticket method
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter row number (1-3):");
        int row = scanner.nextInt();
        if (row < 1 || row > ROWS) {
            System.out.println("Invalid row number.");


            return;
        }
        System.out.println("Enter seat number (1-" + SEATS_PER_ROW[row - 1] + "):"); // it will ask the user to enter a seat number based on which row has been selected.
        int seat = scanner.nextInt();
        if (seat < 1 || seat > SEATS_PER_ROW[row - 1]) { // if the seat number doesnt match up with the amount of seats available on that row it will present as invalid
            System.out.println("Invalid seat number.");
            return;
        } else if (seats[row - 1][seat - 1] == 1) { // if the row and seat number inputed are calculated to be taken already a message saying  seat already taken will appear.
            System.out.println("This seat is already taken.");

        }else {
            seats[row - 1][seat - 1] = 1;// once seat and row inputed it will print out ticket purchased
            System.out.println("Ticket purchased.");
        }



    }


    public static void seating_area() {
        System.out.println(" ***********\n *  STAGE  *\n ***********");// prints out the layout of the stage
        for (int i = 0; i < seats.length; i++) {// works out the amonut of seats needed to be printed for each row.
            System.out.print("Row " + (i + 1) + " ");// print all 3 rows
            for (int j = 0; j < seats[i].length; j++) {// alongside prints all the seats for each row
                if (seats[i][j] == 1) {
                    System.out.print("X ");// sold seats
                } else {
                    System.out.print("O ");// empty seats
                }
            }
            System.out.println();
        }

    }
    private static void cancel_ticket() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter row number (1-3):");
        int row = scanner.nextInt();
        if (row < 1 || row > ROWS) { // checks to see the entered value is within the range
            System.out.println("Invalid row number.");
            return;
        }
        System.out.println("Enter seat number (1-" + SEATS_PER_ROW[row - 1] + "):");
        int seat = scanner.nextInt();
        if (seat < 1 || seat > SEATS_PER_ROW[row - 1]) { // checks to see the entered value is within the range
            System.out.println("Invalid seat number.");
            return;
        }
        if (seats[row - 1][seat - 1] == 0) { // check is if the seat is taken or not
            System.out.println("This seat is already available.");
            return;
        }
        seats[row - 1][seat - 1] = 0; // cancels the seat
        System.out.println("Seat canceled successfully.");
    }


    private static void available_seats() {
        for (int i = 0; i < ROWS; i++) { //takes the data from buy ticket and prints out all the available seats.
            System.out.print("Seats available in row " + (i+1) + ": ");
            for (int j = 0; j < SEATS_PER_ROW[i]; j++) {
                if (seats[i][j] == 0) { // if the seats in each row are equal to 0 which means free it will show those seats
                    System.out.print((j+1) + " ");
                }
            }
            System.out.println();
        }
    }

    private static void save_file() {
        try {
            FileWriter writer = new FileWriter("seats.txt");// saves the seats input data as a txt file called seats.txt and saves it
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < SEATS_PER_ROW[i]; j++) {// take the inputed rows and seats and presents them  in a file.
                    writer.write(seats[i][j] + " ");
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("Seats saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving seats to file.");
            e.printStackTrace();
        }
    }

    private static void load_file() {
        try {
            Scanner scanner = new Scanner(new File("seats.txt")); // finds the file saved seats.txt and loads up the file to view
            for (int i = 0; i < ROWS; i++) {// uses an array to work out the amonut of rows and seats which are purchased and needed to be presented
                for (int j = 0; j < SEATS_PER_ROW[i]; j++) {
                    seats[i][j] = scanner.nextInt();
                }
            }
            scanner.close();
            System.out.println("Seating information loaded from file.");
        } catch (FileNotFoundException e) {// if not true and error will be presented
            System.out.println("Error: seating file not found.");
        }
    }
    // this part is unfisnihed becuase i wasn't sure how to complete it
    public static void info_price(){
        try{
            File myObj = new File("ticketInfo.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("an error has occured");
            e.printStackTrace();
        }

        System.out.println();
    }

}
