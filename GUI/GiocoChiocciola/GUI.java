package a02a.e2;

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
        final Logics logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	Optional<Coord> pos = logic.hit();
            if(pos.isEmpty()) {
                System.exit(0);
            }
            cells.get(pos.get()).setText(""+counter++);
            
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
        this.setVisible(true);
    }
    
}
