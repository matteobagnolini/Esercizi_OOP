package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Coord> cells = new HashMap<>();
    
    public GUI(int size) {
        Logics logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button = (JButton)e.getSource();
            var pos = cells.get(button);
            if(logic.hit(pos)) {
                refresh(logic.getMap());
                if(logic.isOver()){
                    disableAll();
                }
            } else {
                button.setText("1");
            }
            button.setEnabled(false);
        };
                
        for (int i=1; i<size; i++){
            for (int j=1; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Coord(j, i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void disableAll() {
        for(var elem : cells.entrySet()) {
            elem.getKey().setEnabled(false);
        }
    }

    private void refresh(Set<Coord> map) {
        for(var elem : cells.entrySet()) {
            if(map.contains(elem.getValue())) {
                elem.getKey().setText("*");
            }
        }
    }
    
}
