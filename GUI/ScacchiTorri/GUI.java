package a03a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Coord> cells = new HashMap<>();
    
    public GUI(int size) {
        Logics logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
                if(!logic.isOver()) {
                    if(logic.hit(position)) {
                        init(logic.getTowers());
                    }
                } else {
                    logic.init();
                    init(logic.getTowers());
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
        logic.init();
        init(logic.getTowers());
        this.setVisible(true);
    }

    private void init(Pair<Coord, Coord> towers) {
        for(var cell : cells.entrySet()) {
            if(cell.getValue().equals(towers.getX())) {
                cell.getKey().setText("*");
            } else if(cell.getValue().equals(towers.getY())) {
                cell.getKey().setText("o");
            } else {
                cell.getKey().setText("");
            }
        }
    }    
}
