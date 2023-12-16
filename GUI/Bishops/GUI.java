package a02a.e2;

import javax.swing.*;
import java.util.*;
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
        	    var pos = cells.get(button);
                if(!button.getText().equals("B")) {
                button.setText("B");
                logic.hit(pos);
                refresh(logic.getMap());
                
            }
             else {
                if(logic.isOver()) {
                    logic.reset();
                    reset();
                }
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

    protected void reset() {
        for(var elem : cells.entrySet()) {
            elem.getKey().setText("");
            elem.getKey().setEnabled(true);
        }
    }

    protected void refresh(Set<Coord> map) {
        for(var elem : cells.entrySet()) {
            if(map.contains(elem.getValue())) {
                elem.getKey().setEnabled(false);
            } 
        }
    }    
}
