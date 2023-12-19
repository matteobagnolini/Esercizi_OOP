package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private boolean resetGame = false;
    private final Map<JButton, Coord> cells = new HashMap<>();
    
    public GUI(int size) {
        Logics logic = new LogicsImpl(size);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        JButton check = new JButton("Go");
        main.add(BorderLayout.SOUTH, check);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    if(resetGame) {
                    resetGame = false;
                    logic.reset();
                    reset();
                }
                var button = (JButton)e.getSource();
        	    var pos = cells.get(button);
                button.setText(button.getText().equals("*") ? "" : "*");
                logic.hit(pos);

            }
        };

        ActionListener alCheck = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    Optional<Coord> pos = logic.check();
                if(pos.isPresent()) {
                    disableDiagonal(pos.get());
                    resetGame = true;
                }
            }
        };
                
        for (int i=1; i<size; i++){
            for (int j=1; j<size; j++){
                final JButton jb = new JButton(" ");
                Coord pos = new Coord(j, i);
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        check.addActionListener(alCheck);
        this.setVisible(true);
    }

    protected void disableDiagonal(Coord coord) {
        int x = coord.x();
        int y = coord.y();
        for(;x > 0; x--, y--) {
            var pos = new Coord(x,y);
            for(var cell : cells.entrySet()) {
                if(cell.getValue().x() == pos.x() && cell.getValue().y() == pos.y()) {
                    cell.getKey().setEnabled(false);
                }
            }
        }
    }

    protected void reset() {
        for(var cell : cells.entrySet()) {
            cell.getKey().setText("");
            cell.getKey().setEnabled(true);
        }
    }    
}
