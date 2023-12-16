package a03b.e2;

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
                if(button.getText().equals("*") && !logic.isOver()) {
                    logic.hit(position);
                    refresh(logic.getPlayerPawns(), logic.getEnemyPawns());
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
        refresh(logic.getPlayerPawns(), logic.getEnemyPawns());
    }
    protected void refresh(Set<Coord> playerPawns, Set<Coord> enemyPawns) {
        for(var cell : cells.entrySet()) {
            if(playerPawns.contains(cell.getValue())) {
                cell.getKey().setText("*");
            } else if(enemyPawns.contains(cell.getValue())) {
                cell.getKey().setText("o");
            } else {
                cell.getKey().setText("");
            }
        }
    }    
}
