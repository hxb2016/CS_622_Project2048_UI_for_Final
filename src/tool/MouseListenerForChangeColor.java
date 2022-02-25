package tool;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Purpose of this class is to return a handler for components to get mouse listener
 * because there are different kind components will use this class, it uses generic class
 *
 * Author: Xiaobing Hou
 * Date: 02/25/2022
 * Course: CS-622
 */
public class MouseListenerForChangeColor<T extends Component> {
    T component;

    public MouseListenerForChangeColor(T com) {
        component = com;
    }

    public void setListener(Color original, Color changed) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                component.setForeground(changed);
            }
        });

        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                component.setForeground(original);
            }
        });
    }
}
