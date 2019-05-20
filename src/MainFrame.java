import Component.DotComponent;
import Component.EdgeComponent;
import player.*;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

public class MainFrame extends JFrame {
    private static int m, n, round = 1, amount, mode, turn;
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
            currentColor = players.get((round + 1) % amount).getColor();
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
                    loop:
                    for (; i < 2 * m - 1; i++) {
                        for (j = 0; j < 2 * n - 1; j++) {
                            if (components[i][j] instanceof EdgeComponent) {
                                if (edgeComponent.equals((EdgeComponent) components[i][j])) {
                                    break loop;
                                }
                            }
                        }
                    }
                    if (isClosed(i, j)) {
                        scoreAdd(i, j);
//                        players.get((round + 1) % amount).addScore();
                    } else {
                        round++;
                        currentColor = players.get((round + 1) % amount).getColor();
                        
                        if (mode == 1) {
//                            try {
//                            Thread.sleep(500);
//                        } catch (InterruptedException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
                            move();
                            round++;
                        }
                    }
//                    currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;//change color
//                    if (isEnd()) {
//                        sort();
//                        for (int k = 0; k < players.size(); k++) {
//                            System.out.printf("%-3d%8s%3d\n", k + 1, players.get(k).getName(), players.get(k).getScore());
//                        }
//                        if (players.get(0).getScore() > players.get(1).getScore())
//                            System.out.printf("%s win", players.get(0).getName());
//                        else
//                            System.out.println("draw");
//                        setVisible(false);
//                        System.exit(0);
////                        setVisible(false);
//                    }
                    endTest();
                }
            }
        }
        
        //MouseMovement
        @Override
        public void mouseMoved(MouseEvent event) {
            currentColor = players.get((round + 1) % amount).getColor();
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
            
            for (int i = 0; i < components.length; i++) {
                for (int j = 0; j < components[i].length; j++) {
                    if (components[i][j] instanceof EdgeComponent) {
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
            }

//            for (int i = 1; i < components.length; i += 2) {
//                for (int j = 0; j < components[i].length; j += 2) {
//                    EdgeComponent e = (EdgeComponent) components[i][j];
//                    if (e.isFree()) {
//                        if (e == component) {
//                            e.setColor(currentColor);
//                            e.setVisible(true);
//                        } else {
//                            e.setVisible(false);
//                        }
//                    }
//                }
//            }

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
        currentColor = players.get((round + 1) % amount).getColor();
        if (mode != 2) {
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
        System.out.print("Mode:");
        mode = Integer.parseInt(in.nextLine().trim());
        System.out.print("Player's amount: ");
        amount = Integer.parseInt(in.nextLine().trim());
        System.out.print("row: ");
        m = Integer.parseInt(in.nextLine().trim());
        System.out.print("column: ");
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
                turn = Integer.parseInt(in.nextLine().trim());
                switch (turn) {
                    case 1:
                        players.add(new human());
                        players.add(new robot());
                        players.get(0).setName(name);
                        players.get(0).setColor(Color.red);
                        break;
                    case 2:
                        Color color;
                        players.add(new robot());
                        if (isCreated(Color.red)) {
                            color = Color.BLUE;
                        } else
                            color = Color.red;
                        players.add(new human());
                        players.get(1).setName(name);
                        players.get(1).setColor(color);
                        break;
                }
//                amount += 1;
                break;
            }
            case 2: {
                for (int i = 0; i < amount; i++) {
                    robot robot = new robot();
                    if (!isCreated(robot.getColor())) {
                        players.add(robot);
                    } else i--;
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
//            if (mode == 1 && turn == 2) {
//                mainFrame.move();
//                round++;
//            }
            mainFrame.setVisible(true);//make mainFrame visible
            if (mode == 1 && turn == 2) {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                mainFrame.move();
                round++;
            } else if (mode == 2) {
                while (!mainFrame.isEnd()) {
                    mainFrame.move();
                    round++;
                    mainFrame.currentColor = players.get((round + 1) % amount).getColor();
                }
                mainFrame.endTest();
            }
        }
    }
    
    private void scoreAdd(int i, int j) {
        if (i % 2 == 0) {
            try {
                EdgeComponent e1 = (EdgeComponent) components[i - 2][j];
                EdgeComponent e5 = (EdgeComponent) components[i - 1][j + 1];
                EdgeComponent e6 = (EdgeComponent) components[i - 1][j - 1];
                if (!e1.isFree() && !e5.isFree() && !e6.isFree()) {
                    players.get((round + 1) % amount).addScore();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            
            }
            try {
                EdgeComponent e2 = (EdgeComponent) components[i + 2][j];
                EdgeComponent e3 = (EdgeComponent) components[i + 1][j + 1];
                EdgeComponent e4 = (EdgeComponent) components[i + 1][j - 1];
                if (!e2.isFree() && !e3.isFree() && !e4.isFree()) {
                    players.get((round + 1) % amount).addScore();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            
            }
        } else
            try {
                EdgeComponent e1 = (EdgeComponent) components[i][j - 2];
                EdgeComponent e5 = (EdgeComponent) components[i - 1][j - 1];
                EdgeComponent e6 = (EdgeComponent) components[i + 1][j - 1];
                if (!e1.isFree() && !e5.isFree() && !e6.isFree()) {
                    players.get((round + 1) % amount).addScore();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            
            }
        try {
            EdgeComponent e2 = (EdgeComponent) components[i][j + 2];
            EdgeComponent e3 = (EdgeComponent) components[i - 1][j + 1];
            EdgeComponent e4 = (EdgeComponent) components[i + 1][j + 1];
            if (!e2.isFree() && !e3.isFree() && !e4.isFree()) {
                players.get((round + 1) % amount).addScore();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        
        }
    }
    
    
    private boolean isClosed(int i, int j) {
        EdgeComponent e0 = (EdgeComponent) components[i][j];
        if (i % 2 == 0) {
            try {
                EdgeComponent e1 = (EdgeComponent) components[i - 2][j];
                EdgeComponent e5 = (EdgeComponent) components[i - 1][j + 1];
                EdgeComponent e6 = (EdgeComponent) components[i - 1][j - 1];
                if (!e1.isFree() && !e5.isFree() && !e6.isFree() && !e0.isFree()) {
                    components[i - 1][j] = new JLabel(players.get((round + 1) % amount).getName());
                    components[i - 1][j].setSize(50, 50);
                    components[i - 1][j].setLocation((components[i - 2][j].getX() + components[i][j].getX()) / 2, (components[i - 1][j + 1].getY() + components[i - 1][j - 1].getY()) / 2);
                    components[i - 1][j].setForeground(players.get(round % amount).getColor());
                    getContentPane().add(components[i][j - 1]);
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            
            }
            try {
                EdgeComponent e2 = (EdgeComponent) components[i + 2][j];
                EdgeComponent e3 = (EdgeComponent) components[i + 1][j + 1];
                EdgeComponent e4 = (EdgeComponent) components[i + 1][j - 1];
                if (!e2.isFree() && !e3.isFree() && !e4.isFree() && !e0.isFree()) {
                    components[i + 1][j] = new JLabel(players.get(round % amount).getName());
                    components[i + 1][j].setForeground(players.get(round % amount).getColor());
                    getContentPane().add(components[i][j - 1]);
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            
            }
        } else
            try {
                EdgeComponent e1 = (EdgeComponent) components[i][j - 2];
                EdgeComponent e5 = (EdgeComponent) components[i - 1][j - 1];
                EdgeComponent e6 = (EdgeComponent) components[i + 1][j - 1];
                if (!e1.isFree() && !e5.isFree() && !e6.isFree() && !e0.isFree()) {
                    components[i][j - 1] = new JLabel(players.get(round % amount).getName());
                    components[i][j - 1].setForeground(players.get(round % amount).getColor());
                    getContentPane().add(components[i][j - 1]);
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            
            }
        try {
            EdgeComponent e2 = (EdgeComponent) components[i][j + 2];
            EdgeComponent e3 = (EdgeComponent) components[i - 1][j + 1];
            EdgeComponent e4 = (EdgeComponent) components[i + 1][j + 1];
            if (!e2.isFree() && !e3.isFree() && !e4.isFree() && !e0.isFree()) {
                components[i][j + 1] = new JLabel(players.get(round % amount).getName());
                components[i][j + 1].setForeground(players.get(round % amount).getColor());
                getContentPane().add(components[i][j - 1]);
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        
        }
        return false;
    }
    
    private boolean isEnd() {
//        try {
//            Thread.sleep(1000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        int flag = 1;
        for (int i = 0; i < components.length; i++) {
            for (int j = 0; j < components[i].length; j++) {
                if (components[i][j] instanceof EdgeComponent) {
                    EdgeComponent e = (EdgeComponent) components[i][j];
                    if (e.isFree()) {
                        flag = 0;
                    }
                }
            }
        }

//        for (int i = 1; i < components.length; i += 2) {
//            for (int j = 0; j < components[i].length; j += 2) {
//                EdgeComponent e = (EdgeComponent) components[i][j];
//                if (e.isFree()) {
//                    flag = 0;
//                }
//            }
//        }
        
        if (flag == 1)
            return true;
        else
            return false;
    }
    
    private void move() {
        int i = -1;
        int j = -1;
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        if (!isEnd()) {
            i = random.nextInt(2 * m - 1);
            j = random.nextInt(2 * n - 1);
            EdgeComponent edgeComponent;
            if (components[i][j] instanceof EdgeComponent) {
                edgeComponent = (EdgeComponent) components[i][j];
            } else {
                edgeComponent = new EdgeComponent(0, 0, 0, 0);
                edgeComponent.setFree(false);
            }
            while (!edgeComponent.isFree()) {
                i = random.nextInt(2 * m - 1);
                j = random.nextInt(2 * n - 1);
                if (components[i][j] instanceof EdgeComponent)
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
        }
        if (i != -1) {
            if (isClosed(i, j)) {
                scoreAdd(i, j);
                move();
            }
        }
//        } else {
//            if (MainFrame.mode == 2) {
//                sort();
//                for (int k = 0; k < players.size(); k++) {
//                    System.out.printf("%-3d%8s%3d\n", k + 1, players.get(k).getName(), players.get(k).getScore());
//                }
//                if (players.get(0).getScore() > players.get(1).getScore())
//                    System.out.printf("%s win", players.get(0).getName());
//                else
//                    System.out.println("draw");
//                setVisible(false);
//                System.exit(0);
//            }
//        }
    }
    
    private void endTest() {
        if (isEnd()) {
            sort();
            for (int k = 0; k < players.size(); k++) {
                System.out.printf("%-3d%8s%3d\n", k + 1, players.get(k).getName(), players.get(k).getScore());
            }
            if (players.get(0).getScore() > players.get(1).getScore())
                System.out.printf("%s win", players.get(0).getName());
            else
                System.out.println("draw");
            setVisible(false);
            System.exit(0);
//                        setVisible(false);
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
    
    private static void sort() {
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size() - 1; j++) {
                if (players.get(j).getScore() < players.get(j + 1).getScore()) {
                    player temporary = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, temporary);
                }
            }
        }
    }
}
