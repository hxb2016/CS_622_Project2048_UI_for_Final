package tool;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Purpose of this class is to return a handler for components to get mouse listener
 * because there are different kind components will use this class, it uses generic class
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/25/2022
 * Course: CS-622
 */
public class MouseListenerForChangeColor<T extends Component> {
    private final T component;
    private Color original;
    private final Color changed;

    public MouseListenerForChangeColor(T com, Color changed) {
        component = com;
        this.changed = changed;

        setListener();
    }

    private void setListener() {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                original = component.getForeground();
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
