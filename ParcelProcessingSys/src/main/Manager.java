package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager {
    private QueueOfCustomers customerQueue;
    private ParcelMap parcelMap;
    private Worker worker;
    private Log log;
    private JFrame frame;
    private JTextArea parcelTextArea;
    private JTextArea customerTextArea;
    private JTextArea currentParcelTextArea;
    private JButton processButton;
    private JLabel statusBar;

    public Manager() {
        this.customerQueue = new QueueOfCustomers();
        this.parcelMap = new ParcelMap();
        this.worker = new Worker(parcelMap);
        this.log = Log.getInstance();
        createGUI();
    }

    public void initializeParcels(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String parcelID = parts[0];
                    int daysInDepot = Integer.parseInt(parts[1]);
                    double weight = Double.parseDouble(parts[2]);
                    double length = Double.parseDouble(parts[3]);
                    double width = Double.parseDouble(parts[4]);
                    double height = Double.parseDouble(parts[5]);

                    Parcel parcel = new Parcel(parcelID, daysInDepot, weight, length, width, height);
                    parcelMap.addParcel(parcel);
                }
            }
            updateParcelTextArea();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeCustomers(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int queueNumber = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String parcelID = parts[2];

                    Customer customer = new Customer(queueNumber, name, parcelID);
                    customerQueue.addCustomer(customer);
                }
            }
            updateCustomerTextArea();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startProcessing() {
        if (!customerQueue.isEmpty()) {
            Customer customer = customerQueue.removeCustomer();
            worker.processCustomer(customer);
            updateCustomerTextArea();
            updateParcelTextArea();
            currentParcelTextArea.setText("Currently Processing: \n" + customer.toString());
            updateStatusBar("Processing customer: " + customer.getName());
        } else {
            currentParcelTextArea.setText("No more customers to process.");
            updateStatusBar("No more customers to process.");
        }
        log.writeLogToFile("log.txt");
    }

    private void createGUI() {
        frame = new JFrame("Parcel Depot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLayout(new BorderLayout());

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadParcels = new JMenuItem("Load Parcels");
        loadParcels.addActionListener(e -> initializeParcels("parcels.txt"));
        JMenuItem loadCustomers = new JMenuItem("Load Customers");
        loadCustomers.addActionListener(e -> initializeCustomers("customers.txt"));
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(loadParcels);
        fileMenu.add(loadCustomers);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Parcel Depot System v1.0", "About", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);

        parcelTextArea = new JTextArea();
        parcelTextArea.setEditable(false);
        parcelTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        parcelTextArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane parcelScrollPane = new JScrollPane(parcelTextArea);
        parcelScrollPane.setBorder(BorderFactory.createTitledBorder("Parcels"));

        customerTextArea = new JTextArea();
        customerTextArea.setEditable(false);
        customerTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        customerTextArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane customerScrollPane = new JScrollPane(customerTextArea);
        customerScrollPane.setBorder(BorderFactory.createTitledBorder("Customers"));

        currentParcelTextArea = new JTextArea();
        currentParcelTextArea.setEditable(false);
        currentParcelTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        currentParcelTextArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane currentParcelScrollPane = new JScrollPane(currentParcelTextArea);
        currentParcelScrollPane.setBorder(BorderFactory.createTitledBorder("Current Parcel"));

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, parcelScrollPane, customerScrollPane);
        splitPane1.setDividerLocation(400);
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, currentParcelScrollPane);
        splitPane2.setDividerLocation(800);

        processButton = new JButton("Process Next Customer");
        processButton.setToolTipText("Click to process the next customer in the queue");
        processButton.setFont(new Font("Arial", Font.BOLD, 14));
        processButton.setIcon(new ImageIcon("icons/process.png"));  // Assuming you have an icon named process.png
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startProcessing();
            }
        });

        JPanel parcelFormPanel = new JPanel();
        parcelFormPanel.setLayout(new GridLayout(7, 2, 10, 10));
        parcelFormPanel.setBorder(BorderFactory.createTitledBorder("Add Parcel"));
        JTextField parcelIDField = new JTextField();
        JTextField daysInDepotField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField lengthField = new JTextField();
        JTextField widthField = new JTextField();
        JTextField heightField = new JTextField();

        JButton addParcelButton = new JButton("Add Parcel");
        addParcelButton.setFont(new Font("Arial", Font.BOLD, 14));
        addParcelButton.setIcon(new ImageIcon("icons/add.png"));  // Assuming you have an icon named add.png
        addParcelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String parcelID = parcelIDField.getText();
                    int daysInDepot = Integer.parseInt(daysInDepotField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                    double length = Double.parseDouble(lengthField.getText());
                    double width = Double.parseDouble(widthField.getText());
                    double height = Double.parseDouble(heightField.getText());

                    Parcel parcel = new Parcel(parcelID, daysInDepot, weight, length, width, height);
                    parcelMap.addParcel(parcel);
                    updateParcelTextArea();
                    parcelIDField.setText("");
                    daysInDepotField.setText("");
                    weightField.setText("");
                    lengthField.setText("");
                    widthField.setText("");
                    heightField.setText("");
                } catch (NumberFormatException ex) {
                    updateStatusBar("Error: Please enter valid numeric values.");
                }
            }
        });

        parcelFormPanel.add(new JLabel("Parcel ID:"));
        parcelFormPanel.add(parcelIDField);
        parcelFormPanel.add(new JLabel("Days in Depot:"));
        parcelFormPanel.add(daysInDepotField);
        parcelFormPanel.add(new JLabel("Weight:"));
        parcelFormPanel.add(weightField);
        parcelFormPanel.add(new JLabel("Length:"));
        parcelFormPanel.add(lengthField);
        parcelFormPanel.add(new JLabel("Width:"));
        parcelFormPanel.add(widthField);
        parcelFormPanel.add(new JLabel("Height:"));
        parcelFormPanel.add(heightField);
        parcelFormPanel.add(new JLabel(""));
        parcelFormPanel.add(addParcelButton);

        JPanel customerFormPanel = new JPanel();
        customerFormPanel.setLayout(new GridLayout(4, 2, 10, 10));
        customerFormPanel.setBorder(BorderFactory.createTitledBorder("Add Customer"));
        JTextField queueNumberField = new JTextField();
        JTextField customerNameField = new JTextField();
        JTextField parcelIDForCustomerField = new JTextField();

        JButton addCustomerButton = new JButton("Add Customer");
        addCustomerButton.setFont(new Font("Arial", Font.BOLD, 14));
        addCustomerButton.setIcon(new ImageIcon("icons/add.png"));  // Assuming you have an icon named add.png
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int queueNumber = Integer.parseInt(queueNumberField.getText());
                    String name = customerNameField.getText();
                    String parcelID = parcelIDForCustomerField.getText();

                    Customer customer = new Customer(queueNumber, name, parcelID);
                    customerQueue.addCustomer(customer);
                    updateCustomerTextArea();
                    queueNumberField.setText("");
                    customerNameField.setText("");
                    parcelIDForCustomerField.setText("");
                } catch (NumberFormatException ex) {
                    updateStatusBar("Error: Please enter valid numeric values.");
                }
            }
        });

        customerFormPanel.add(new JLabel("Queue Number:"));
        customerFormPanel.add(queueNumberField);
        customerFormPanel.add(new JLabel("Customer Name:"));
        customerFormPanel.add(customerNameField);
        customerFormPanel.add(new JLabel("Parcel ID:"));
        customerFormPanel.add(parcelIDForCustomerField);
        customerFormPanel.add(new JLabel(""));
        customerFormPanel.add(addCustomerButton);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(1, 2, 20, 0));
        formPanel.add(parcelFormPanel);
        formPanel.add(customerFormPanel);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(splitPane2, BorderLayout.CENTER);
        centerPanel.add(processButton, BorderLayout.SOUTH);

        statusBar = new JLabel("Status: Ready");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        statusBar.setFont(new Font("Arial", Font.PLAIN, 12));
        frame.add(statusBar, BorderLayout.SOUTH);

        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void updateParcelTextArea() {
        parcelTextArea.setText(parcelMap.toString());
    }

    private void updateCustomerTextArea() {
        customerTextArea.setText(customerQueue.toString());
    }

    private void updateStatusBar(String message) {
        statusBar.setText("Status: " + message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Manager());
    }
}
