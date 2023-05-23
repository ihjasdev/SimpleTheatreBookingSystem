import java.util.Arrays;
import java.util.Scanner;

public class TheatreBookingSystem {
    private boolean[][] seats;
    private int numRows;
    private int numColumns;

    public TheatreBookingSystem(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        seats = new boolean[numRows][numColumns];
        // Initialize all seats as available
        for (boolean[] row : seats) {
            Arrays.fill(row, true);
        }
    }

    public void bookSeats(int numSeats) {
        int seatsBooked = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (seats[i][j]) {
                    seats[i][j] = false;
                    seatsBooked++;
                    System.out.println("Seat booked: Row " + (i + 1) + ", Column " + (j + 1));
                    if (seatsBooked == numSeats) {
                        return;
                    }
                }
            }
        }
        System.out.println("Sorry, there are not enough available seats.");
    }

    public void cancelBooking(int row, int column) {
        if (isValidSeat(row, column)) {
            if (!seats[row - 1][column - 1]) {
                seats[row - 1][column - 1] = true;
                System.out.println("Booking canceled: Row " + row + ", Column " + column);
            } else {
                System.out.println("No booking found for the specified seat.");
            }
        } else {
            System.out.println("Invalid seat.");
        }
    }

    public void showCurrentStatus() {
        System.out.println("Current seat status:");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print(seats[i][j] ? "O " : "X ");
            }
            System.out.println();
        }
    }

    private boolean isValidSeat(int row, int column) {
        return row >= 1 && row <= numRows && column >= 1 && column <= numColumns;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows in the theater: ");
        int numRows = scanner.nextInt();

        System.out.print("Enter the number of columns in the theater: ");
        int numColumns = scanner.nextInt();

        TheatreBookingSystem bookingSystem = new TheatreBookingSystem(numRows, numColumns);

        int option;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Initialize Theatre");
            System.out.println("2. Book Seats");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Show Current Status");
            System.out.println("5. Exit");
            System.out.print("Enter your option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Re-initialize the theater
                    bookingSystem = new TheatreBookingSystem(numRows, numColumns);
                    System.out.println("Theater initialized successfully.");
                    break;
                case 2:
                    System.out.print("Enter the number of seats to book: ");
                    int numSeats = scanner.nextInt();
                    bookingSystem.bookSeats(numSeats);
                    break;
                case 3:
                    System.out.print("Enter the row number: ");
                    int row = scanner.nextInt();
                    System.out.print("Enter the column number: ");
                    int column = scanner.nextInt();
                    bookingSystem.cancelBooking(row, column);
                    break;
                case 4:
                    bookingSystem.showCurrentStatus();
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (option != 5);

        scanner.close();
    }
}

