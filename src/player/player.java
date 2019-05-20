package player;

import javax.swing.*;
import java.awt.*;

public class player extends JFrame {
//    public player(String name, Color color) {
//        this.name = name;
//        this.color = color;
//    }
    
    private String name;
    private Color color;
    private int score = 0;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public void addScore() {
        this.score += 1;
    }
    
    //    public class GameMouseListener extends MouseInputAdapter {
//        //MouseClick
//        @Override
//        public void mouseClicked(MouseEvent event) {
//            event = SwingUtilities.convertMouseEvent(MainFrame.this, event, getContentPane());
//            Component component = getContentPane().getComponentAt(event.getPoint());//get component at the point's position
//            if (component instanceof EdgeComponent) {//if current component is a kind of Component.EdgeComponent
//                EdgeComponent edgeComponent = (EdgeComponent) component;
//                //when the select edge is not occupied
//                if (edgeComponent.isFree()) {
////                    edgeComponent.setColor(currentColor);
//                    edgeComponent.setFree(false);//occupy
//                    edgeComponent.setVisible(true);
//                    edgeComponent.repaint();//paint again
//                }
//            }
//        }
//
//        //MouseMovement
//        @Override
//        public void mouseMoved(MouseEvent event) {
//            event = SwingUtilities.convertMouseEvent(MainFrame, event, getContentPane());
//            Component component = getContentPane().getComponentAt(event.getPoint());
//
//            for (EdgeComponent e : edges) {
//                if (e.isFree()) {
//                    if (e == component) {
//                        e.setColor(currentColor);
//                        e.setVisible(true);
//                    } else {
//                        e.setVisible(false);
//                    }
//                }
//
//            }
//            edges.stream().filter(Component.EdgeComponent::isFree).forEach((e) -> {
//                if (e == component) {
//                    e.setColor(currentColor);
//                    e.setVisible(true);
//                } else {
//                    e.setVisible(false);
//                }
//            });
    //MouseEnd
    public void move() {
//        MainFrame.GameMouseListener mouseListener = new MainFrame.GameMouseListener();
//        addMouseListener(mouseListener);
//        addMouseMotionListener(mouseListener);
    }
}
