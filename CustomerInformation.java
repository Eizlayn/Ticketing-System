import java.util.*;

public class CustomerInformation {
    // Private instance variables to store customer and ticket details
    private String custId;
    private String custName;
    private int counterNumber;
    private int ticketId; 
    private String rideName;
    private int ticketPrice;
    private String purchaseDate;
    private int purchasedQuantity;
    private boolean paid; // Indicates whether the customer has paid
    private List<TicketInformation> purchasedTickets; // List of purchased tickets
    private int counterPaid; // New attribute to track counter payments

public CustomerInformation(String custId, String custName, int ticketId, String rideName, int ticketPrice, String purchaseDate, int purchasedQuantity) {
        this.custId = custId;
        this.custName = custName;
        this.ticketId = 0; 
        this.rideName = rideName;
        this.ticketPrice = ticketPrice;
        this.purchaseDate = purchaseDate;
        this.purchasedQuantity = purchasedQuantity;
        this.purchasedTickets = new ArrayList<>();
        this.paid = false; // Initialize as unpaid
    }

    // Method to add a purchased ticket to the customer's list of purchased tickets
    public void addItem(TicketInformation ticket) {
        purchasedTickets.add(ticket);
    }

    // Getter method to retrieve the customer's ID
    public String getCustId() {
        return custId;
    }

    // Getter method to retrieve the customer's name
    public String getCustName() {
        return custName;
    }

    // Getter method to retrieve the unused ticket ID
    public int getTicketId() {
        return ticketId;
    }

    // Getter method to retrieve the name of the ride associated with the ticket
    public String getRideName() {
        return rideName;
    }

    // Getter method to retrieve the price of the ticket
    public int getTicketPrice() {
        return ticketPrice;
    }

    // Getter method to retrieve the date of purchase
    public String getPurchaseDate() {
        return purchaseDate;
    }

    // Getter method to retrieve the quantity of tickets purchased
    public int getPurchasedQuantity() {
        return purchasedQuantity;
    }

    // Method to increment the counterPaid, typically used for tracking counter payments
    public void incrementCounterPaid() {
        ticketId++;
        paid = true; // Set as paid when processed
    }
    
    // Getter method to check if the customer has paid
    public boolean isPaid() {
        return paid;
    }

    // Setter method to update the paid status
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    // Getter method to retrieve the list of purchased tickets
    public List<TicketInformation> getPurchasedTickets() {
        return purchasedTickets;
    }

    // Getter method to retrieve the counter number, but it should be initialized and updated
    public int getCounterNumber() {
        return counterNumber;
    }

    // Setter method to update the counter number
    public void setCounterNumber(int counterNumber) {
        this.counterNumber = counterNumber;
    }

    // Override the toString method to represent the customer as a string
    @Override
    public String toString() {
        return custId + " - " + custName;
    }
}