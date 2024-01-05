package QuanLyDanCu.src.utilities;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.*;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomTable extends JScrollPane {
    private JTable table;

    public CustomTable(int numOfRows, int numOfColumns) {
        Object[][] data = new Object[numOfRows][numOfColumns];
        for (int i = 1; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (i == 1) {
                    // Dòng thứ hai để trống
                    data[i][j] = "";
                } else {
                    data[i][j] = "Row " + i + ", Column " + (j + 1);
                }
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, new String[]{
                "Check",
                "Column 2",
                "Column 3",
                "Column 4",
                "Column 5",
                "Column 6",
                "Column 7",
                "Column 8",
                "Column 9"
        }) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowSelectionAllowed(true);

        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setResizable(false);
        }

        TableColumn checkColumn = table.getColumnModel().getColumn(0);
        checkColumn.setMinWidth(20);
        checkColumn.setMaxWidth(20);

        checkColumn.setCellRenderer(new TableCellRenderer() {
            final JCheckBox checkBox = new JCheckBox();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return checkBox;
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());
                if (column == 0 && row > -1) {
                    boolean currentState = (boolean) table.getValueAt(row, column);
                    table.setValueAt(!currentState, row, column);
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        table.setValueAt(!currentState, row, i);
                    }
                }
            }
        });

        setViewportView(table);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Table Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        CustomTable customTable = new CustomTable(30, 9);
        panel.add(customTable, BorderLayout.CENTER);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
