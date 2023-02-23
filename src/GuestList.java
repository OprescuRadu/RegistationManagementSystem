import java.util.ArrayList;


public class GuestList {

    private final int capacity;
    private String eventName;
    // One list for guests both registered and on the waiting list
    private ArrayList<Guest> guestList;


    public GuestList(String eventName, int capacity) {
        this.eventName = eventName;
        this.capacity = capacity;
        this.guestList = new ArrayList<>(capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return this.guestList.size();
    }

    public int addGuest(Guest guest) {
        if (isRegistered(guest)) {
            return -1;
        }
        guestList.add(guest);
        if (guestList.size() <= capacity) {
            return 0;
        } else {
            return guestList.size() - capacity;
        }
    }

    public boolean isRegistered(Guest guest) {
        for (Guest g : guestList) {
            if (g.equals(guest)) {
                return true;
            }
        }
        return false;
    }

    public Guest searchByName(String lastName, String firstName) {
        for (Guest guest : this.guestList) {
            if (guest.hasSameName(lastName, firstName)) {
                return guest;
            }
        }
        return null;
    }

    public Guest searchByEmail(String email) {
        for (Guest guest : this.guestList) {
            if (guest.getEmail().equalsIgnoreCase(email)) {
                return guest;
            }
        }
        return null;
    }

    public Guest searchByPhoneNumber(String phoneNumber) {
        for (Guest guest : this.guestList) {
            if (guest.getPhoneNumber().equals(phoneNumber)) {
                return guest;
            }
        }
        return null;
    }

    public boolean remove(Guest guest) {
        for (int i = 0; i < guestList.size(); i++) {
            if (guestList.get(i).equals(guest)) {
                guestList.remove(i);
                return true;
            }
        }
        return false;
    }

    public void notifyParticipantIsOnGuestlist() {
        System.out.println(guestList.get(capacity - 1));
    }

    public void changeGuestField(Guest guest, int option, String newValue) {
        switch (option) {
            case 1 -> guest.setLastName(newValue);
            case 2 -> guest.setFirstName(newValue);
            case 3 -> guest.setEmail(newValue);
            case 4 -> guest.setPhoneNumber(newValue);
            default -> System.out.println("Invalid Option");
        }
    }

    public void guests() {
        if (guestList.size() < capacity) {
            for (Guest guest : guestList) {
                System.out.println(guest);
            }
        } else {
            for (int i = 0; i < capacity; i++) {
                System.out.println(guestList.get(i));
            }
        }
    }

    public void waitingList() {
        for (int i = capacity; i < guestList.size(); i++) {
            System.out.println(guestList.get(i));
        }
    }

    public int availableSpots() {
        return this.capacity - this.guestList.size();
    }

    public int waitingListNumber() {
        if (guestList.size() > capacity) {
            return this.guestList.size() - capacity;
        }
        return 0;
    }

    public int registeredTotalNumber() {
        return this.guestList.size();
    }

    public ArrayList<Guest> smartSearch(String str) {
        ArrayList<Guest> matches = new ArrayList<Guest>();
        for (Guest guest : this.guestList) {
            if (guest.partialSearch(str)) {
                matches.add(guest);
            }
        }
        return matches;
    }
}


