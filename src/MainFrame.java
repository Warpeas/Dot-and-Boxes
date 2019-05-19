import Component.DotComponent;
import Component.EdgeComponent;
import com.sun.org.apache.bcel.internal.generic.NEW;
import player.*;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainFrame extends JFrame {
    private static int m, n, round = 0, turn = 0, amount, mode;
    private static ArrayList<player> players = new ArrayList<player>();
    //        private List<EdgeComponent> edges = new ArrayList<>();
//    private ArrayList<EdgeComponent> edges = new ArrayList<>();
    //    private ArrayList<ArrayList<JComponent>>components = new ArrayList<>();
    private JComponent[][] components;
    private Color currentColor;
    private static Random random = new Random();
    
    //Mouse
    public class GameMouseListener extends MouseInputAdapter {
        //MouseClick
        @Override
        public void mouseClicked(MouseEvent event) {
            event = SwingUtilities.convertMouseEvent(MainFrame.this, event, getContentPane());
            Component component = getContentPane().getComponentAt(event.getPoint());//get component at the point's position
            if (component instanceof EdgeComponent) {//if current component is a kind of Component.EdgeComponent
                EdgeComponent edgeComponent = (EdgeComponent) component;
                //when the select edge is not occupied
                if (edgeComponent.isFree()) {
                    edgeComponent.setColor(currentColor);
                    edgeComponent.setFree(false);//occupy
                    edgeComponent.setVisible(true);
                    edgeComponent.repaint();//paint again
                    int i = 0, j = 0;
                    for (; i < 2 * m - 1; i++) {
                        for (; j < 2 * n - 1; j++) {
                            if (components[i][j].equals(edgeComponent)) {
                                break;
                            }
                        }
                    }
                    if (isClosed(i, j)) {
                        players.get(turn % amount).addScore();
                        round += 1;
                    } else {
                        turn += 1;
                        round += 1;
                        currentColor = players.get(turn % amount).getColor();
                        if (mode == 1) {
                            move();
                        }
                    }
//                    currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;//change color
                    if (isEnd()) {
//                        setVisible(false);
                    }
                }
            }
        }
        
        //MouseMovement
        @Override
        public void mouseMoved(MouseEvent event) {
            event = SwingUtilities.convertMouseEvent(MainFrame.this, event, getContentPane());
            Component component = getContentPane().getComponentAt(event.getPoint());

//            for (EdgeComponent e : edges) {
//                if (e.isFree()) {
//                    if (e == component) {
//                        e.setColor(currentColor);
//                        e.setVisible(true);
//                    } else {
//                        e.setVisible(false);
//                    }
//                }
            
            for (int i = 0; i < components.length; i += 2) {
                for (int j = 1; j < components[i].length; j += 2) {
                    EdgeComponent e = (EdgeComponent) components[i][j];
                    if (e.isFree()) {
                        if (e == component) {
                            e.setColor(currentColor);
                            e.setVisible(true);
                        } else {
                            e.setVisible(false);
                        }
                    }
                }
            }
            
            for (int i = 1; i < components.length; i += 2) {
                for (int j = 0; j < components[i].length; j += 2) {
                    EdgeComponent e = (EdgeComponent) components[i][j];
                    if (e.isFree()) {
                        if (e == component) {
                            e.setColor(currentColor);
                            e.setVisible(true);
                        } else {
                            e.setVisible(false);
                        }
                    }
                }
            }

//            edges.stream().filter(Component.EdgeComponent::isFree).forEach((e) -> {
//                if (e == component) {
//                    e.setColor(currentColor);
//                    e.setVisible(true);
//                } else {
//                    e.setVisible(false);
//                }
//            });
        }
    }
    //MouseEnd
    
    
    public MainFrame() {
        initialize();
        currentColor = players.get(round).getColor();
        if (mode == 2) {
        
        } else {
            GameMouseListener mouseListener = new GameMouseListener();
            addMouseListener(mouseListener);
            addMouseMotionListener(mouseListener);
        }
    }
    
    public void initialize() {
        setTitle("CS102A Project");//window title
        setSize(m * 175, n * 375 / 2);//window size
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Exit when close the window
        setLayout(null);
        
        components = new JComponent[2 * m - 1][2 * n - 1];
        
        //paint dots
        for (int i = 0; i < components.length; i += 2) {
            for (int j = 0; j < components[i].length; j += 2) {
//                DotComponent dotComponent = new DotComponent(75 + 150 * i, 75 + 150 * j, 30);
//                getContentPane().add(dotComponent); // Add the component to the frame.
                components[i][j] = new DotComponent(75 + 150 * j / 2, 75 + 150 * i / 2, 30);
                getContentPane().add(components[i][j]);
            }
        }
        
        //paint edges
        //horizontal
        for (int i = 0; i < components.length; i += 2) {
            for (int j = 1; j < components[i].length; j += 2) {
//                edges.add(new EdgeComponent(100 + 150 * i, 82 + 150 * j, 150, 16));
//                edges.add(new EdgeComponent(100 + 150 * i, 232 + 150 * j, 150, 16));
//                edges.add(new EdgeComponent(82 + 150 * i, 100 + 150 * j, 16, 150));
//                edges.add(new EdgeComponent(232 + 150 * i, 100 + 150 * j, 16, 150));
                components[i][j] = new EdgeComponent(82 + 150 * (j - 1) / 2, 82 + 150 * i / 2, 150, 16);
                components[i][j].setVisible(false);
                getContentPane().add(components[i][j]);
            }
        }
        //perpendicular
        for (int i = 1; i < components.length; i += 2) {
            for (int j = 0; j < components[i].length; j += 2) {
                components[i][j] = new EdgeComponent(82 + 150 * j / 2, 100 + 150 * (i - 1) / 2, 16, 150);
                components[i][j].setVisible(false);
                getContentPane().add(components[i][j]);
            }
        }
        //Occupy information
        for (int i = 1; i < components.length; i += 2) {
            for (int j = 1; j < components[i].length; j += 2) {
                components[i][j] = new JLabel("");
            }
        }
//        for (EdgeComponent e : edges) {
//            e.setVisible(false);
//            getContentPane().add(e);
//        }
//        edges.stream().peek(e -> e.setVisible(false)).forEach(getContentPane()::add);
    }
    
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        mode = Integer.parseInt(in.nextLine().trim());
        amount = Integer.parseInt(in.nextLine().trim());
        m = Integer.parseInt(in.nextLine().trim());
        n = Integer.parseInt(in.nextLine().trim());
        
        switch (mode) {
            case 0: {
                for (int i = 0; i < amount; i++) {
                    System.out.print("Name: ");
                    String name = in.nextLine();
                    Color color;
                    switch (i) {
                        case 0:
                            color = Color.red;
                            break;
                        case 1:
                            color = Color.blue;
                            break;
                        case 3:
                            color = Color.GREEN;
                            break;
                        case 4:
                            color = Color.yellow;
                            break;
                        default:
                            color = Color.black;
                    }
                    if (!isCreated(name)) {
                        players.add(new human());
                        players.get(i).setName(name);
                        players.get(i).setColor(color);
                    } else {
                        System.out.println("The name has been occupied, please input again.");
                        i--;
                    }
                }
                break;
            }
            case 1: {
                System.out.print("Name: ");
                String name = in.nextLine();
                System.out.println("1 or 2");
                int turn = Integer.parseInt(in.nextLine().trim());
                switch (turn) {
                    case 1:
                        players.add(new human());
                        players.get(0).setName(name);
                        players.get(0).setColor(Color.red);
                        break;
                    case 2:
                        players.add(new robot());
                        players.add(new human());
                        players.get(1).setName(name);
                        if (isCreated(Color.red)) {
                            players.get(1).setColor(Color.BLUE);
                        } else
                            players.get(1).setColor(Color.red);
                        break;
                }
                break;
            }
        }
        Runnable runnable = new Runner();
        SwingUtilities.invokeLater(runnable);
//        play();


//        SwingUtilities.invokeLater(() -> {
//            MainFrame mainFrame = new MainFrame();
//            mainFrame.setVisible(true);
//        });
    }

//    public static void play() {
//        for (int i = 0; i < 4 + (m - 3) * 3; i++) {
//            int j = i % 2;
//            players.get(j).move();
//        }
//    }
    
    static class Runner implements Runnable {
        @Override
        public void run() {
            MainFrame mainFrame = new MainFrame();//construct mainFrame
            mainFrame.setVisible(true);//make mainFrame visible
        }
    }
    
    private boolean isClosed(int i, int j) {
        if (i % 2 == 0) {
            try {
                EdgeComponent e1 = (EdgeComponent) components[i - 2][j];
                EdgeComponent e5 = (EdgeComponent) components[i - 1][j + 1];
                EdgeComponent e6 = (EdgeComponent) components[i - 1][j - 1];
                if (!e1.isFree() && !e5.isFree() && e6.isFree()) {
                    components[i-1][j] = new JLabel(players.get(turn % amount).getName());
                    components[i-1][j].setForeground(players.get(turn % amount).getColor());
                    getContentPane().add(components[i][j-1]);
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            
            }
            try {
                EdgeComponent e2 = (EdgeComponent) components[i + 2][j];
                EdgeComponent e3 = (EdgeComponent) components[i + 1][j + 1];
                EdgeComponent e4 = (EdgeComponent) components[i + 1][j - 1];
                if (!e2.isFree() && !e3.isFree() && !e4.isFree()) {
                    components[i+1][j] = new JLabel(players.get(turn % amount).getName());
                    components[i+1][j].setForeground(players.get(turn % amount).getColor());
                    getContentPane().add(components[i][j-1]);
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            
            }
        } else
            try {
                EdgeComponent e1 = (EdgeComponent) components[i][j - 2];
                EdgeComponent e5 = (EdgeComponent) components[i - 1][j - 1];
                EdgeComponent e6 = (EdgeComponent) components[i + 1][j - 1];
                if (!e1.isFree() && !e5.isFree() && e6.isFree()) {
                    components[i][j-1] = new JLabel(players.get(turn % amount).getName());
                    components[i][j-1].setForeground(players.get(turn % amount).getColor());
                    getContentPane().add(components[i][j-1]);
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            
            }
        try {
            EdgeComponent e2 = (EdgeComponent) components[i][j + 2];
            EdgeComponent e3 = (EdgeComponent) components[i - 1][j + 1];
            EdgeComponent e4 = (EdgeComponent) components[i + 1][j + 1];
            if (!e2.isFree() && !e3.isFree() && !e4.isFree()) {
                components[i][j+1] = new JLabel(players.get(turn % amount).getName());
                components[i][j+1].setForeground(players.get(turn % amount).getColor());
                getContentPane().add(components[i][j-1]);
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        
        }
        return false;
    }
    
    private boolean isEnd() {
        int flag = 1;
        for (int i = 0; i < components.length; i += 2) {
            for (int j = 1; j < components[i].length; j += 2) {
                EdgeComponent e = (EdgeComponent) components[i][j];
                if (e.isFree()) {
                    flag = 0;
                }
            }
        }
        
        for (int i = 1; i < components.length; i += 2) {
            for (int j = 0; j < components[i].length; j += 2) {
                EdgeComponent e = (EdgeComponent) components[i][j];
                if (e.isFree()) {
                    flag = 0;
                }
            }
        }
        
        if (flag == 1)
            return true;
        else
            return false;
    }
    
    private void move() {
        int i = random.nextInt(2 * m - 1);
        int j = random.nextInt(2 * n - 1);
        EdgeComponent edgeComponent = (EdgeComponent) components[i][j];
        while (!edgeComponent.isFree()) {
            i = random.nextInt(2 * m - 1);
            j = random.nextInt(2 * n - 1);
            edgeComponent = (EdgeComponent) components[i][j];
        }
        edgeComponent.setFree(false);

//    getContentPane().remove(components[i][j]);
//    components[i][j].setVisible(true);
//    getContentPane().add(components[i][j]);
//    getContentPane().repaint();
        
        //change from array to componentPane
        for (int k = 0; k < getContentPane().getWidth(); k++) {
            for (int l = 0; l < getContentPane().getHeight(); l++) {
                if (getContentPane().getComponentAt(k, l).equals(edgeComponent)) {
                    edgeComponent = (EdgeComponent) getContentPane().getComponentAt(k, l);
                    break;
                }
            }
        }
        edgeComponent.setColor(currentColor);
        edgeComponent.setFree(false);//occupy
        edgeComponent.setVisible(true);
        edgeComponent.repaint();//paint again
        if (isClosed(i, j)) {
            players.get(turn % amount).addScore();
            move();
        }
    }
    
    private static boolean isCreated(String name) {
        int flag = 0;
        for (player p : players) {
            if (p.getName().equals(name))
                flag = 1;
        }
        if (flag == 1)
            return true;
        else
            return false;
    }
    
    private static boolean isCreated(Color color) {
        int flag = 0;
        for (player p : players) {
            if (p.getColor().equals(color))
                flag = 1;
        }
        if (flag == 1)
            return true;
        else
            return false;
    }
}
