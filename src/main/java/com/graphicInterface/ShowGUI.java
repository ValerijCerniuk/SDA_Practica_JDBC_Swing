package com.graphicInterface;

import com.repository.Repository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

public class ShowGUI extends JFrame
        implements ActionListener {

    final private String ADD = "ADD";
    final private String REMOVE = "remove";
    final private String ALTER = "alter";
    final private String UPDATE = "update";
    final String IMAGE_PATH = "src/image/";
    private Repository repository = new Repository();
    private JFrame frame = new JFrame("Database Manager");
    private Container contentPane = frame.getContentPane();
    private JToolBar toolbar = new JToolBar();
    private AtomicReference<JTable> activeTable = new AtomicReference<>(new JTable());
    AtomicReference<JComboBox<String>> jComboBox =
            new AtomicReference<>(new JComboBox<>());
    private String selectedTable;

    public void createFrame() {
        //  JFrame frame = new JFrame("Database Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        toolbar.setRollover(true);

        addButtons(toolbar);
        contentPane.add(toolbar, BorderLayout.NORTH);

        frame.pack();
        frame.setSize(650, 350);
        frame.setVisible(true);
    }


    protected JToolBar addButtons(JToolBar toolBar) {
        JButton button ;

        button = makeActionButton(ADD,
                "ADD");
        toolBar.add(button);

        button = makeActionButton(REMOVE,
                "remove");
        toolBar.add(button);

        button = makeActionButton(ALTER,
                "alter");
        toolBar.add(button);

        button = makeActionButton(UPDATE,
                "update");
        toolBar.add(button);

        toolBar.add((createJComboBox()));

        return toolBar;
    }

    protected JButton makeActionButton(String actionCommand, String altText) {

        JButton button = new JButton();
        button.setIcon(new ImageIcon(IMAGE_PATH + altText + ".gif"));
        button.setActionCommand(actionCommand);
        button.setToolTipText(altText);
        button.addActionListener(this);

        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (ADD.equals(cmd)) {
            new AddRowWindow().createWindows(selectedTable);

        } else if (REMOVE.equals(cmd)) {
            removeRowFromTable();
            drawTableByComboBoxSelection();

        } else if (ALTER.equals(cmd)) {
            addRowToTable();
            drawTableByComboBoxSelection();

        } else if (UPDATE.equals(cmd)) {
            updateDataFromTable();
            drawTableByComboBoxSelection();

        }
    }


    public DefaultTableModel buildTableModel(ResultSet rs) {

        ResultSetMetaData metaData;
        Vector<Vector<Object>> data = new Vector<>();
        Vector<String> columnNames = new Vector<>();
        try {
            metaData = rs.getMetaData();
            // names of columns
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }
            // data of the table
            while (rs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(data, columnNames);
    }

    private JComboBox<String> createJComboBox() {
        String[] comboBoxChooses = {"Flower", "Customer", "FlowersOrder"};
        jComboBox = new AtomicReference<>(
                new JComboBox<>(comboBoxChooses));

        jComboBox.get().addItemListener(e ->
            drawTableByComboBoxSelection());
        return jComboBox.get();
    }

    private void drawTableByComboBoxSelection() {
        selectedTable = (String) jComboBox.get().getSelectedItem();
        contentPane.remove(activeTable.get());
        activeTable.set(new JTable(buildTableModel(repository.findAllInTable(selectedTable))));
        contentPane.add(activeTable.get());
        contentPane.repaint();
        contentPane.validate();
    }

    private void refreshActiveTable() {
        contentPane.remove(activeTable.get());
        contentPane.add(activeTable.get());
        contentPane.repaint();
        contentPane.validate();
    }

    private void addRowToTable() {
        DefaultTableModel table = (DefaultTableModel) activeTable.get().getModel();
        table.addRow(new Vector<>());
        String sql = "INSERT INTO " + selectedTable + " values ( null ";
        for (int i = 0; i < columnCountInActiveTable() - 1; ++i) {
            sql = sql.concat(", null");
        }
        sql = sql.concat(" )");
        repository.addNewRowToTable(sql);
        drawTableByComboBoxSelection();
    }

    private int columnCountInActiveTable() {
        return activeTable.get().getColumnCount();
    }

    private void removeRowFromTable() {
        DefaultTableModel table = (DefaultTableModel) activeTable.get().getModel();
        String result = JOptionPane.showInputDialog(frame, "Enter row ID to delete:");
        int rowId = Integer.parseInt(result);

        repository.deleteRowInTableByRowId(selectedTable, rowId);

    }

    protected void updateDataFromTable() {
        DefaultTableModel table = (DefaultTableModel) activeTable.get().getModel();
        Vector<Vector> dataVector = table.getDataVector();
        String sql;

        for (Vector rowData : dataVector) {
            sql = "UPDATE " + selectedTable + " SET ";
            for (int i = 1; i < table.getColumnCount(); ++i) {
                sql = sql.concat(table.getColumnName(i))
                        .concat(" = ")
                        .concat("\"" + rowData.get(i) + "\"");
                if (i != table.getColumnCount() - 1) {
                    sql = sql.concat(", ");
                }
            }
            sql = sql.concat(" where ").concat(table.getColumnName(0))
                    .concat(" = ")
                    .concat(String.valueOf(rowData.get(0)))
                    .concat(" ;");
            repository.updateTable(sql);
            System.out.println("Update query is : ");
            System.out.println(sql);
        }

    }

}


