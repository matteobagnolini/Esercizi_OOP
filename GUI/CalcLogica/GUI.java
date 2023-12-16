package a05.e2;

import javax.swing.*;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Coord> cells = new HashMap<>();
    Logics logic = new LogicsImpl();
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
        	var position = cells.get(jb);
        	if(logic.hit(position)) {
                jb.setEnabled(false);
            }
            if(logic.isOver()) {
                System.exit(0);
            }
        };
                
        for (int i=1; i<size; i++){
            for (int j=1; j<size; j++){
                var coord = new Coord(j, i);
                final JButton jb = new JButton(logic.setUp(coord));
                this.cells.put(jb, coord);
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        this.setVisible(true);
    }
}