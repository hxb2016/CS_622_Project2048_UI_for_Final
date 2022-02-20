package mainui;

import game2048_test.App;
import users.RegisteredUser;
import users.User;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * purpose of this class is to create a table which includes usernames and whose best record's taken time
 *
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class UsersTable extends JTable {
    private final String[] title = new String[]{"Username", "Best record"};

    /**
     * purpose of this method is to set the data into the table and style of the table
     */
    public UsersTable(Map<String, User> usersData) {

        DefaultTableModel model = new DefaultTableModel(dealWithData(usersData), title) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        this.setModel(model);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        sorter.setComparator(1, new Comparator<Object>() {
            public int compare(Object arg0, Object arg1) {
                try {
                    int a = Integer.parseInt(arg0.toString());
                    int b = Integer.parseInt(arg1.toString());
                    return a - b;
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        });

        this.setRowSorter(sorter);

        this.setRowHeight(25);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        this.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));

        UsersTable finalThis = this;
        DefaultTableCellRenderer defaultCell = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (App.currentUser != null) {
                    if (finalThis.getValueAt(row, 0).equals(App.currentUser.username)) {
                        setForeground(Color.red);
                    } else {
                        setForeground(Color.BLACK);
                    }
                }
                setHorizontalAlignment(JLabel.CENTER);
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        this.setDefaultRenderer(Object.class, defaultCell);

        ((DefaultTableCellRenderer) this.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setSelectionBackground(new Color(184, 207, 229));


    }

    /**
     * purpose of this method is to deal with the data needed by the table
     */
    private String[][] dealWithData(Map<String, User> usersData) {
        // create a Comparator for sorting the data by users' taken timer
        Comparator<Map.Entry<String, User>> comparator = new Comparator<Map.Entry<String, User>>() {
            @Override
            public int compare(Map.Entry<String, User> o1, Map.Entry<String, User> o2) {
                return ((RegisteredUser) o1.getValue()).bestTakeTime - ((RegisteredUser) o2.getValue()).bestTakeTime;
            }
        };
        // use stream() to sort the userdata (Map<String, User>) and return a new Map
        Map<String, User> newUsersData = usersData.entrySet()
                .stream().sorted(comparator)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (c1, c2) -> c1, LinkedHashMap::new));

        // Get a array for the table
        String[][] data = new String[newUsersData.size()][title.length];
        int index = 0;
        for (String username : newUsersData.keySet()) {
            data[index][0] = username;
            data[index][1] = ((RegisteredUser) usersData.get(username)).bestTakeTime == 0 ? "null" : ((RegisteredUser) usersData.get(username)).bestTakeTime + " s";
            index++;
        }
        return data;
    }


}
