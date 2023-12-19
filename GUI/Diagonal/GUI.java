package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Coord> cells = new HashMap<>();
    Logics logic = new LogicsImpl();
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var pos = cells.get(button);
                logic.hit(pos);
                if(!logic.isOver()) {    
                    if(button.getText().equals("*")) {
                        button.setText("");
                    } else {
                        button.setText("*");
                    }
                } else {
                    System.exit(0);
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
}
