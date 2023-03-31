import throwableObjects.AlreadyOnList;
import throwableObjects.EmailFormatException;
import throwableObjects.GuestNotFound;
import throwableObjects.PhoneFormatException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Display {
    private final GuestList event;
    private final Scanner sc = new Scanner(System.in);

    public Display(String eventName, int capacity) {
        this.event = new GuestList(eventName, capacity);
    }

    public void execute() {
        boolean quit = false;
        System.out.println("Welcome to " + event.getEventName() + "!");
        while (!quit) {
            System.out.println();
            System.out.println("Waiting for command: (type 'help' and press 'Enter' to see full list of commands) ");
            String command = sc.next();
            switch (command) {
                case "help":
                    help();
                    break;
                case "add":
                    try {
                        add();
                    } catch (AlreadyOnList e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "check":
                    try {
                        check();
                    } catch (GuestNotFound e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "remove":
                    try {
                        remove();
                    } catch (GuestNotFound e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "update":
                    try {
                        update(search());
                    } catch (GuestNotFound e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "guests":
                    showGuests();
                    break;
                case "waitlist":
                    showWaitingList();
                    break;
                case "available":
                    available();
                    break;
                case "guests_no":
                    guests_no();
                    break;
                case "waitlist_no":
                    waitlist_no();
                    break;
                case "registered_no":
                    registered_no();
                    break;
                case "search":
                    searchByString();
                    break;
                case "quit":
                    quit = true;
                    break;
                default:
                    System.out.println("Command not found. Please try again!");
            }
        }
    }

    public void help(){
        System.out.println("""
                            add           - Registers a new person
                            check         - Verifies if a person is registered for the event
                            remove        - Removes an existing person from the list
                            update        - Updates a specific field of a person
                            guests        - Shows the list of people attending the event
                            waitlist      - Shows the list of people in the waiting list
                            available     - Number of available spots at the event
                            guests_no     - Number of people attending
                            waitlist_no   - Number of people in the waiting list
                            registered_no - Total number of people registered
                            search        - Searches the guests by entering specific text
                            quit          - Closes the application""");
    }

    public void add() throws AlreadyOnList {
        System.out.println("We will first check to see if you are already registered on the list.");
        Guest newGuest = create();
        int addingGuest = this.event.addGuest(newGuest);
        if (addingGuest == -1) {
            throw new AlreadyOnList();
        } else if (addingGuest == 0) {
            System.out.println(newGuest + "\nYour place is confirmed! See you soon!");
        } else {
            System.out.println(newGuest + "\nYou are number " + addingGuest + " on our waiting list. We will " +
                    "notify you if a spot becomes available!");
        }
    }

    public Guest create() throws EmailFormatException, PhoneFormatException {
        Scanner sc = new Scanner(System.in);
        System.out.println(("Please enter your Last Name:"));
        String lastName = sc.next();
        System.out.println(("Please enter your First Name:"));
        String firstName = sc.next();
        String email;
        String phoneNumber;
        while (true) {
            System.out.println("Please enter your Email address:");
            try {
                email = sc.next();
                if (!email.contains("@")) throw new EmailFormatException();
                break;
            } catch (EmailFormatException e) {
                System.err.println(e.getMessage());
            }
        }
        while (true) {
            System.out.println("Please enter your Phone Number:");
            try {
                phoneNumber = sc.next();
                sc.nextLine();
                if (phoneNumber.length() != 10) throw new PhoneFormatException();
                for (int i = 0; i < phoneNumber.length(); i++) {
                    if (!Character.isDigit(phoneNumber.charAt(i))) throw new PhoneFormatException();
                }
                break;
            } catch (PhoneFormatException e) {
                System.err.println(e.getMessage());
            }
        }
        return new Guest(lastName, firstName, email, phoneNumber);
    }

    public void check() throws GuestNotFound {
        Guest checkGuest = search();
        if (checkGuest == null) {
            throw new GuestNotFound();
        }
        System.out.println("Guest is registered!");
        System.out.println(checkGuest);
    }

    public Guest search() {
        int option = chooseSearchField();
        switch (option) {
            case 1 -> {
                System.out.println(("Please enter your Last Name:"));
                String lastName = sc.next();
                System.out.println(("Please enter your First Name:"));
                String firstName = sc.next();
                return event.searchByName(lastName, firstName);
            }
            case 2 -> {
                System.out.println("Please enter you Email:");
                return event.searchByEmail(sc.next());
            }
            case 3 -> {
                System.out.println("Please enter your Phone Number:");
                return event.searchByPhoneNumber(sc.next());
            }
        }
        return null;
    }

    public int chooseSearchField() throws IndexOutOfBoundsException, InputMismatchException {
        int option;
        while (true) {
            try {
                System.out.println("Choose the field to find the guest: \n" +
                        "1 - Last name & First name \n" +
                        "2 - Email \n" +
                        "3 - Phone number");
                option = sc.nextInt();
                if (option <= 0 || option > 3) {
                    throw new IndexOutOfBoundsException();
                }
                return option;
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Error, you have to write a number between 1 - 3");
            } catch (InputMismatchException e) {
                System.err.println("Error, this was not a number!!!");
                sc.nextLine();
            }
        }
    }

    public void remove() throws GuestNotFound {
        if (this.event.remove(search())) {
            System.out.println("Guest has been successfully removed!");
            if (this.event.getCapacity() <= this.event.getSize()) {
                System.out.println("The following guest is moved to the guestlist! Please notify him: ");
                this.event.notifyParticipantIsOnGuestlist();
            }
        } else {
            throw new GuestNotFound();
        }
    }

    private void update(Guest guest) throws GuestNotFound {
        if (guest != null) {
            System.out.println("Please specify the field you want to change \n" +
                    "1 - Last name \n" +
                    "2 - First Name \n" +
                    "3 - Email \n" +
                    "4 - Phone number");
            int x = sc.nextInt();
            sc.nextLine();
            System.out.print("Please enter the new details:");
            String str = sc.nextLine();
            this.event.changeGuestField(guest, x, str);
            System.out.println("The field has been successfully modified");
        } else {
            throw new GuestNotFound();
        }
    }

    public void showGuests() {
        if (this.event.getSize() == 0) {
            System.out.println("The list is empty!");
        }
        this.event.guests();
    }

    public void showWaitingList() {
        if (this.event.waitingListNumber() == 0) {
            System.out.println("The waiting list is empty!");
            return;
        }
        this.event.waitingList();
    }

    private void available() {
        if (this.event.availableSpots() > 0) {
            System.out.println("The number of available spots is: " + this.event.availableSpots() + "!");
            return;
        }
        System.out.println("The list is full!");
    }

    private void guests_no() {
        if (this.event.getSize() >= this.event.getCapacity()) {
            System.out.println("We are expecting a full house! Capacity is of " + this.event.getCapacity() + "!");
            return;
        }
        System.out.println("The number of guests attending the event is: " + this.event.getSize() + "!");
    }

    private void waitlist_no() {
        System.out.println("The number of people in the waiting list is: " + this.event.waitingListNumber() + "!");
    }

    private void registered_no() {
        System.out.println("The total number of people registered is: " + this.event.registeredTotalNumber() + "!");
    }

    private void searchByString() {
        System.out.println("Searching all guests according to specified string.");
        System.out.println("Please insert string:");
        String str = sc.next();
        ArrayList<Guest> matches = this.event.smartSearch(str);
        for (Guest match : matches) {
            System.out.println(match);
        }
    }
}