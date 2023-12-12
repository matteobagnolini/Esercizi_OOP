package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<Coord, JButton> cells = new HashMap<>();
    private int counter = 0;
    
    public GUI(int size) {
        var logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        
        ActionListener al = e -> {
        	refresh(logic.next());
            if(logic.isOver()) {
                System.exit(0);                
            }
        };
                
        for (int i=1; i<size; i++){
            for (int j=1; j<size; j++){
            	var pos = new Coord(j,i);
                final JButton jb = new JButton(" ");
                this.cells.put(pos, jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        refresh(logic.set());
        this.setVisible(true);
    }

    private void refresh(Map<Coord,String> map) {
        for(var elem : cells.entrySet()) {
            if(map.containsKey(elem.getKey())) {
                elem.getValue().setText(map.get(elem.getKey()));
            }
        }
    }
    
}
