public class TicketInformation {
    // Private instance variables to store ticket details
    private int ticketId;         // Unique ticket identifier
    private String rideName;      // Name of the ride associated with the ticket
    private int ticketPrice;      // Price of the ticket
    private String purchaseDate;  // Date of purchase
    private int purchasedQuantity; // Quantity of tickets purchased

    public TicketInformation(int ticketId, String rideName, int ticketPrice, String purchaseDate, int purchasedQuantity) {
        this.ticketId = ticketId;
        this.rideName = rideName;
        this.ticketPrice = ticketPrice;
        this.purchaseDate = purchaseDate;
        this.purchasedQuantity = purchasedQuantity;
    }

    // Getter method to retrieve the unique ticket identifier.
    // @return The ticket identifier.
    public int getTicketId() {
        return ticketId;
    }

    // Getter method to retrieve the name of the ride associated with the ticket.
    // @return The ride name.
    public String getRideName() {
        return rideName;
    }

    // Getter method to retrieve the price of the ticket.
    // @return The ticket price.
    public int getTicketPrice() {
        return ticketPrice;
    }

    // Getter method to retrieve the date of purchase.
    // @return The purchase date.
    public String getPurchaseDate() {
        return purchaseDate;
    }

    // Getter method to retrieve the quantity of tickets purchased.
    // @return The purchased quantity.
    public int getPurchasedQuantity() {
        return purchasedQuantity;
    }
}