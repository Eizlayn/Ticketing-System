import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class WunsayTicketingSystem extends JFrame {
    // Queues to hold customers at different counters
    private Queue<CustomerInformation> counter1Queue = new LinkedList<>();
    private Queue<CustomerInformation> counter2Queue = new LinkedList<>();
    private Queue<CustomerInformation> counter3Queue = new LinkedList<>();
    // Stack to store completed customer processing
    private Stack<CustomerInformation> completeStack = new Stack<>();
    // Lists to store customer information and paid customers
    private List<CustomerInformation> customerList = new ArrayList<>();
    private List<CustomerInformation> paidCustomers = new ArrayList<>();
    // Flag to track changes to data
    private boolean dataChanged = false;

    // GUI components
    private JButton showReceiptButton;
    private JTextArea customerInfoTextArea;
    private DefaultListModel<String> serviceListModel = new DefaultListModel<>();
    private JList<String> serviceListDisplay;
    private JButton showDataButton;
    private JTextField customerIdTextField;
    private JButton findReceiptButton;

    private int customersProcessedAtCounter1 = 0;
    private int customersProcessedAtCounter2 = 0;
    private int customersProcessedAtCounter3 = 0;

    public WunsayTicketingSystem() {
        // Set up the main frame
        setTitle("Wunsay Ticketing System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(7, 1));

        // Create a button to load customer data from a file
        JButton dataButton = new JButton("Call Customers");
        dataButton.setBackground(Color.PINK);
        dataButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (customerList.isEmpty()) {
                        loadCustomerDataFromFile("customerList.txt");
                    }
                }
            });

        // Create buttons for different counters
        JButton counter1Button = new JButton("Express Counter 1");
        counter1Button.setBackground(Color.PINK);
        counter1Button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    processCounter(counter1Queue, completeStack, "Counter 1");
                }
            });

        JButton counter2Button = new JButton("Express Counter 2");
        counter2Button.setBackground(Color.PINK);
        counter2Button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    processCounter(counter2Queue, completeStack, "Counter 2");
                }
            });

        JButton counter3Button = new JButton("Regular Counter");
        counter3Button.setBackground(Color.PINK);
        counter3Button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    processCounter(counter3Queue, completeStack, "Counter 3");
                }
            });

        // Create a button to close the system
        JButton exitButton = new JButton("Close System");
        exitButton.setBackground(Color.RED);
        exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(WunsayTicketingSystem.this, "The System is Shut Down.");
                    System.exit(0);
                }
            });

        // Create buttons for showing receipts and customer data
        showReceiptButton = new JButton("All Receipt");
        showReceiptButton.setBackground(Color.PINK);
        showReceiptButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showRecentReceipts();
                }
            });

        showDataButton = new JButton("Customer List");
        showDataButton.setBackground(Color.PINK);
        showDataButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayDataFromFile("customerList.txt");
                }
            });

        //Create text ield and button for finding a receipt by customer's ID
        customerIdTextField = new JTextField();
        customerIdTextField.setPreferredSize(new Dimension(150, 30));
        customerIdTextField.setText("Enter Customer ID");

        customerIdTextField.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    if (customerIdTextField.getText().equals("Enter Customer ID")) {
                        customerIdTextField.setText("");
                    }
                }

                public void focusLost(FocusEvent e) {
                    if (customerIdTextField.getText().isEmpty()) {
                        customerIdTextField.setText("Enter Customer ID");
                    }
                }
            });

        findReceiptButton = new JButton("Find Receipt");
        findReceiptButton.setBackground(Color.GREEN);
        findReceiptButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    findAndShowReceiptForCustomer();
                }
            });

        // Add buttons and components to the menu panel
        menuPanel.add(customerIdTextField);
        menuPanel.add(findReceiptButton);
        menuPanel.add(dataButton);
        menuPanel.add(counter1Button);
        menuPanel.add(counter2Button);
        menuPanel.add(counter3Button);
        menuPanel.add(exitButton);
        menuPanel.add(showDataButton);
        menuPanel.add(showReceiptButton);

        // Create the service list panel to display customer information
        JPanel serviceListPanel = new JPanel();
        serviceListPanel.setLayout(new BorderLayout());
        serviceListPanel.setBackground(Color.PINK);

         // Create a JList and JScrollPane for displaying customer information
        serviceListDisplay = new JList<>(serviceListModel);
        JScrollPane scrollPane = new JScrollPane(serviceListDisplay);
        serviceListPanel.add(scrollPane, BorderLayout.CENTER);

        // Create a text area for displaying customer details
        customerInfoTextArea = new JTextArea();
        customerInfoTextArea.setEditable(false);
        customerInfoTextArea.setLineWrap(true);
        customerInfoTextArea.setWrapStyleWord(true);
        JScrollPane customerInfoScrollPane = new JScrollPane(customerInfoTextArea);
        customerInfoScrollPane.setPreferredSize(new Dimension(250, 700));

         // Create the main panel to organize the menu and service list
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(serviceListPanel, BorderLayout.CENTER);

        // Set background colors for panels and components
        menuPanel.setBackground(new Color(144, 238, 144));
        serviceListPanel.setBackground(new Color(255, 192, 203));

        serviceListDisplay.setBackground(new Color(255, 165, 0));

        JPanel showDataPanel = new JPanel(new BorderLayout());
        JPanel exitPanel = new JPanel(new BorderLayout());
        showDataPanel.add(showDataButton, BorderLayout.CENTER);
        exitPanel.add(exitButton, BorderLayout.CENTER);
        showDataButton.setPreferredSize(new Dimension(120, 30));
        exitButton.setPreferredSize(new Dimension(120, 30));

        mainPanel.add(showDataPanel, BorderLayout.NORTH);
        mainPanel.add(exitPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Add a window listener to handle window closing
        addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    e.getWindow().dispose();
                }
            });

        // Make the frame visible
        setVisible(true);
    }

    // Methods for processing customers, loading data, displaying customers, generating receipts, and more...
    private void processCounter(Queue<CustomerInformation> counterQueue, Stack<CustomerInformation> completeStack, String counterName) {
        // Number of customers to process
        int customersToProcess = 5;

        // List to store processed customers
        List<CustomerInformation> processedCustomers = new ArrayList<>();

        while (!counterQueue.isEmpty() && customersToProcess > 0) {
            // Dequeue a customer from the counter queue
            CustomerInformation customer = counterQueue.poll();
            
            // Increment the number of tickets paid for by the customer
            customer.incrementCounterPaid();
            processedCustomers.add(customer);

            // If the customer has paid for all tickets, add them to the list of paid customers
            if (customer.isPaid()) {
                paidCustomers.add(customer);
            }

            // Update the count of customers processed at the respective counter
            if (counterQueue == counter1Queue) {
                customersProcessedAtCounter1++;
            } else if (counterQueue == counter2Queue) {
                customersProcessedAtCounter2++;
            } else if (counterQueue == counter3Queue) {
                customersProcessedAtCounter3++;
            }

            // Push the customer onto the complete stack
            completeStack.push(customer);
            customersToProcess--;
        }

        // Update the display, mark data as changed
        displayCustomers();
        dataChanged = true;

        // Ask for confirmation to pay customers
        int confirmPay = JOptionPane.showConfirmDialog(this, "Pay Now All Customers At " + counterName + "?", "Confirm Payment", JOptionPane.YES_NO_OPTION);

        if (confirmPay == JOptionPane.YES_OPTION) {
            // Mark processed customers as paid
            for (CustomerInformation customer : processedCustomers) {
                customer.setPaid(true);
            }
            // Generate receipts for processed customers
            generateReceiptForProcessedCustomers(processedCustomers);
        } else {
            // Add unprocessed customers back to the queue
            counterQueue.addAll(processedCustomers);
        }
    }

    // Method to load customer data from a file
    private void loadCustomerDataFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            customerList.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String custId = parts[0];
                    String custName = parts[1];
                    int ticketId = Integer.parseInt(parts[2]);
                    String rideName = parts[3];
                    int ticketPrice = Integer.parseInt(parts[4]);
                    int purchasedQuantity = Integer.parseInt(parts[5]);
                    String purchaseDate = parts[6];

                    CustomerInformation customer = new CustomerInformation(custId, custName, 0, rideName, ticketPrice, purchaseDate, purchasedQuantity);
                    TicketInformation ticket = new TicketInformation(1, rideName, ticketPrice, purchaseDate, purchasedQuantity);
                    for (int i = 0; i < purchasedQuantity; i++) {
                        customer.addItem(ticket);
                    }

                    for (int i = 0; i < ticketId; i++) {
                        customer.incrementCounterPaid();
                    }

                    if (purchasedQuantity <= 5) {
                        if (counter1Queue.size() <= counter2Queue.size()) {
                            counter1Queue.add(customer);
                            customer.setCounterNumber(1);
                        } else {
                            counter2Queue.add(customer);
                            customer.setCounterNumber(2);
                        }
                    } else {
                        counter3Queue.add(customer);
                        customer.setCounterNumber(3);
                    }

                    customerList.add(customer);
                }
            }
            reader.close();
            displayCustomers();
            JOptionPane.showMessageDialog(this, "Calling All Customers to Queue.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading customer data: " + e.getMessage());
        }
    }

    // Method to display customer information in the GUI
    private void displayCustomers() {
        int counter = 0;
        int counterLimit = 5;
        String currentCounter = "";
        String currentCounterMain = "";

        customerInfoTextArea.setText("");
        serviceListModel.clear();

        for (CustomerInformation customer : completeStack) {
            if (counter % counterLimit == 0) {
                customerInfoTextArea.append("**\n");

                if (customer.getCounterNumber() == 1) {
                    currentCounter = "Counter 1";
                    currentCounterMain = "<html>========================<br>Processed By Counter 1<br>========================</html>";
                } else if (customer.getCounterNumber() == 2) {
                    currentCounter = "Counter 2";
                    currentCounterMain = "<html>========================<br>Processed By Counter 2<br>========================</html>";
                } else if (customer.getCounterNumber() == 3) {
                    currentCounter = "Counter 3";
                    currentCounterMain = "<html>========================<br>Processed By Counter 3<br>========================</html>";
                }
                serviceListModel.addElement(currentCounterMain);
                customerInfoTextArea.append("================================\n");
                customerInfoTextArea.append("            PROCESSED BY " + currentCounter + "\n");
                customerInfoTextArea.append("================================\n");
            }

            serviceListModel.addElement(customer.toString());

            counter++;
        }
    }

    // Method to display a selected customer's receipt
    private void showSelectedCustomerReceipt() {
        List<CustomerInformation> processedCustomers = new ArrayList<>();

        generateReceiptForProcessedCustomers(processedCustomers);
    }

    // Method to generate a receipt for a list of processed customers
    private void generateReceiptForProcessedCustomers(List<CustomerInformation> processedCustomers) {
        StringBuilder receipt = new StringBuilder();

        receipt.append("+===============================================+\n");
        receipt.append("|        RECEIPT FOR PROCESSED CUSTOMERS        |\n");
        receipt.append("+===============================================+\n");
        int totalTicketPrice = 0;
        int totalPurchasedQuantity = 0;

        for (CustomerInformation customer : processedCustomers) {
            if (customer.isPaid()) {
                receipt.append("\nCustomer ID: ").append(customer.getCustId()).append("\n");
                receipt.append("Customer Name: ").append(customer.getCustName()).append("\n");
                receipt.append("Counter Number: ").append(customer.getCounterNumber()).append("\n");
                receipt.append("Ride Name: ").append(customer.getRideName()).append("\n");
                receipt.append("Price Per Ticket: ").append(customer.getTicketPrice()).append("\n");
                receipt.append("Purchased Quantity: ").append(customer.getPurchasedQuantity()).append("\n");
                receipt.append("Purchase Date: ").append(customer.getPurchaseDate()).append("\n");

                totalTicketPrice = customer.getTicketPrice() * customer.getPurchasedQuantity();

                receipt.append("Total Price: RM" + totalTicketPrice).append("\n");
            }
        }
        receipt.append("+===============================================+\n");

        JTextArea receiptTextArea = new JTextArea(receipt.toString());
        receiptTextArea.setEditable(false);
        receiptTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        receiptTextArea.setMargin(new Insets(10, 10, 10, 10));
        receiptTextArea.setForeground(Color.BLACK);
        receiptTextArea.setBackground(new Color(173, 216, 230));

        JScrollPane receiptScrollPane = new JScrollPane(receiptTextArea);
        receiptScrollPane.setPreferredSize(new Dimension(400, 600));

        JOptionPane.showMessageDialog(this, receiptScrollPane, "Receipt for All Paid Customers", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to display formatted customer data from a file
    private void displayDataFromFile(String filename) {
        String data = readDataFromFile(filename);
        if (!data.isEmpty()) {
            displayFormattedData(data);
        }  // ... (Read and display formatted customer data from a file)
    }

    // Method to show recent receipts for paid customers
    private void showRecentReceipts() {
        if (paidCustomers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Receipts to Display.");
            return;
        }
        generateReceiptForProcessedCustomers(paidCustomers);
    }

    // Method to display formatted data from a file
    private void displayFormattedData(String data) {
        JTextArea dataTextArea = new JTextArea(data);
        dataTextArea.setEditable(false);
        dataTextArea.setLineWrap(true);
        dataTextArea.setWrapStyleWord(true);
        JScrollPane dataScrollPane = new JScrollPane(dataTextArea);
        dataScrollPane.setPreferredSize(new Dimension(400, 600));

        dataTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Using fonts for receipt
        dataTextArea.setMargin(new Insets(10, 10, 10, 10));
        dataTextArea.setOpaque(false);
        dataTextArea.setForeground(Color.BLACK);
        dataTextArea.setBackground(Color.PINK);

        JOptionPane.showMessageDialog(this, dataScrollPane, "Customer Data", JOptionPane.INFORMATION_MESSAGE);
    }

    private String readDataFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringBuilder data = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String custId = parts[0];
                    String custName = parts[1];
                    int ticketId = Integer.parseInt(parts[2]);
                    String rideName = parts[3];
                    int ticketPrice = Integer.parseInt(parts[4]);
                    int purchasedQuantity = Integer.parseInt(parts[5]);
                    String purchaseDate = parts[6];

                    data.append("Customer ID: ").append(custId).append("\n");
                    data.append("Customer Name: ").append(custName).append("\n");
                    data.append("Ticket ID: ").append(ticketId).append("\n");
                    data.append("Ride Name: ").append(rideName).append("\n");
                    data.append("Ticket Price: RM").append(ticketPrice).append("\n");
                    data.append("Purchased Quantity: ").append(purchasedQuantity).append("\n");
                    data.append("Purchase Date: ").append(purchaseDate).append("\n");
                    data.append("=====================================").append("\n");
                }
            }

            reader.close();
            return data.toString();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
            return "";
        }
    }

    // Method to find and show a receipt for a customer by their ID
    private void findAndShowReceiptForCustomer() {
        String customerId = customerIdTextField.getText();

        if (customerId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a customer ID.");
            return;
        }

        CustomerInformation foundCustomer = findCustomerById(customerId);

        if (foundCustomer != null && foundCustomer.isPaid()) {
            List<CustomerInformation> customerList = new ArrayList<>();
            customerList.add(foundCustomer);
            generateReceiptForProcessedCustomers(customerList);
        } else {
            JOptionPane.showMessageDialog(this, "Customer Did Not Make Any Payment Yet");
        }
    }

    // Method to find a customer by their ID in the list of paid customers
    private CustomerInformation findCustomerById(String customerId) {
        for (CustomerInformation customer : paidCustomers) {
            if (customer.getCustId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(new Color(255, 192, 203));
        button.setForeground(Color.BLACK); // Set color
        button.setBorder(BorderFactory.createLineBorder(new Color(144, 238, 144), 2));
        return button;
    }

    // The main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    WunsayTicketingSystem app = new WunsayTicketingSystem();
                    app.setVisible(true);
                }
            });
    }
}