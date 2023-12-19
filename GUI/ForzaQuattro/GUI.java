package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Coord> cells = new HashMap<>();
    
    
    public GUI(int size) {
        Logics logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button = (JButton)e.getSource();
        	var position = cells.get(button);
        	logic.hit(position);
            refresh(logic.getMap());
            if(logic.isOver()) {
                for(var cell : cells.entrySet()) {
                    cell.getKey().setEnabled(false);
                }
            }
        };
                
        for (int i=1; i<size; i++){
            for (int j=1; j<size; j++){
                final JButton jb = new JButton(" ");
                var pos = new Coord(j, i);
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }


    private void refresh(Pair<Set<Coord>, Set<Coord>> map) {
        var p1 = map.getX();
        var p2 = map.getY();
        for(var cell : cells.entrySet()) {
            if(p1.contains(cell.getValue())) {
                cell.getKey().setText("X");
            } else if(p2.contains(cell.getValue())) {
                cell.getKey().setText("O");
            }
        }
    }
    
}
