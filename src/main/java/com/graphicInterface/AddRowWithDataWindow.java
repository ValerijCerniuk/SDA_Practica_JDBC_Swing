package com.graphicInterface;

import com.repository.Repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static java.awt.Component.RIGHT_ALIGNMENT;


public class AddRowWithDataWindow {
    Repository repository = new Repository();
    private JFrame dataEntryFrame = new JFrame();

 public void createWindows(String selectedTable){
     dataEntryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

     switch (selectedTable) {
         case "Flower":
             dataEntryFrame.add(flowerDataForm());
             break;
         case "Customer":
             dataEntryFrame.add(customerDataForm());
             break;
         case "FlowersOrder":
             dataEntryFrame.add(flowersOrderDataForm());
             break;
     }

     dataEntryFrame.pack();
     dataEntryFrame.setSize(450, 250);
     dataEntryFrame.setLocationRelativeTo(null);
     dataEntryFrame.setVisible(true);
 }


    protected JPanel customerDataForm() {
        JPanel customerLog = new JPanel();
        customerLog.setLayout(null);
customerLog.setAutoscrolls(true);
dataEntryFrame.setTitle("Enter Customer Data");

        JLabel name = new JLabel("Full Name");
        JTextField nameField = new JTextField(20);
        customerLog.add(name);
        customerLog.add(nameField);
        name.setBounds(20, 20, 90, 20);
        nameField.setBounds(150, 20, 190, 20);

        JLabel email = new JLabel("E-mail");
        JTextField emailField = new JTextField(20);
        customerLog.add(email);
        customerLog.add(emailField);
        email.setBounds(20, 50, 90, 20);
        emailField.setBounds(150, 50, 190, 20);

        JLabel phoneNumber = new JLabel("Phone Number");
        JTextField phoneNumberField = new JTextField(20);
        customerLog.add(phoneNumber);
        customerLog.add(phoneNumberField);
        phoneNumber.setBounds(20, 80, 90, 20);
        phoneNumberField.setBounds(150, 80, 190, 20);

        JLabel address = new JLabel("Address");
        JTextField addressField = new JTextField(20);
        customerLog.add(address);
        customerLog.add(addressField);
        address.setBounds(20, 110, 90, 20);
        addressField.setBounds(150, 110, 190, 20);

        JButton jButton = new JButton("Confirm");
        customerLog.add(jButton);
        jButton.setBounds(200, 150, 90, 20);

        jButton.addActionListener(actionEvent -> {
                    String nameFieldText = nameField.getText();
                    String emailFieldText = emailField.getText();
                    String phoneNumberFieldText = phoneNumberField.getText();
                    String addressFieldText = addressField.getText();
                    String sql = "INSERT INTO Customer ( fullName, email, phoneNumber, billingAddress) " +
                            "VALUES (\"" + nameFieldText + "\", \"" +
                            emailFieldText + "\", \"" +
                            phoneNumberFieldText + "\", \"" +
                            addressFieldText + "\" );";

                    repository.updateTable(sql);
                    dataEntryFrame.dispose();
                }
        );

        return customerLog;
    }


    protected JPanel flowerDataForm() {
        JPanel customerLog = new JPanel();
        customerLog.setLayout(null);
        customerLog.setAutoscrolls(true);
        dataEntryFrame.setTitle("Enter Flower Data");

        JLabel name = new JLabel("Name");
        JTextField nameField = new JTextField(20);
        customerLog.add(name);
        customerLog.add(nameField);
        name.setBounds(20, 20, 90, 20);
        nameField.setBounds(150, 20, 190, 20);

        JLabel flowerPrice = new JLabel("Flower Price");
        JTextField flowerPriceField = new JTextField(20);
        customerLog.add(flowerPrice);
        customerLog.add(flowerPriceField);
        flowerPrice.setBounds(20, 50, 90, 20);
        flowerPriceField.setBounds(150, 50, 190, 20);

        JLabel color = new JLabel("Color");
        JTextField colorField = new JTextField(20);
        customerLog.add(color);
        customerLog.add(colorField);
        color.setBounds(20, 80, 90, 20);
        colorField.setBounds(150, 80, 190, 20);

        JButton jButton = new JButton("Confirm");
        customerLog.add(jButton);
        jButton.setBounds(200, 150, 90, 20);

        jButton.addActionListener(actionEvent -> {
                    String nameFieldText = nameField.getText();
                    Integer flowerPriceFieldText = Integer.parseInt(flowerPriceField.getText());
                    String colorFieldText = colorField.getText();

                    String sql = "INSERT INTO Flower ( name, flowerPrice, color) " +
                            "VALUES (\"" + nameFieldText + "\", \"" +
                            flowerPriceFieldText + "\", \"" +
                            colorFieldText + "\" );";
            repository.updateTable(sql);
            dataEntryFrame.dispose();
                }
        );

        return customerLog;
    }

    protected JPanel flowersOrderDataForm() {
        JPanel customerLog = new JPanel();
        customerLog.setLayout(null);
        customerLog.setAutoscrolls(true);
        dataEntryFrame.setTitle("Enter Flower Order Data");

        JLabel orderDate = new JLabel("Order Date");
        JTextField orderDateField = new JTextField(20);
        customerLog.add(orderDate);
        customerLog.add(orderDateField);
        orderDate.setBounds(20, 20, 90, 20);
        orderDateField.setBounds(150, 20, 190, 20);

        JLabel deliveryDate = new JLabel("Delivery Date");
        JTextField deliveryDateField = new JTextField(20);
        customerLog.add(deliveryDate);
        customerLog.add(deliveryDateField);
        deliveryDate.setBounds(20, 50, 90, 20);
        deliveryDateField.setBounds(150, 50, 190, 20);

        JButton jButton = new JButton("Confirm");
        customerLog.add(jButton);
        jButton.setBounds(200, 150, 90, 20);

        jButton.addActionListener(actionEvent -> {
                    String orderDateFieldText = orderDateField.getText();
                    String deliveryDateFieldText = deliveryDateField.getText();

                    String sql = "INSERT INTO FlowersOrder ( orderDate, deliveryDay) " +
                            "VALUES (\"" + orderDateFieldText + "\", \"" +
                            deliveryDateFieldText + "\" );";
            repository.updateTable(sql);
            dataEntryFrame.dispose();
                }
        );

        return customerLog;
    }

}
