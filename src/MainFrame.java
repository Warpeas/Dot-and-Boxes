import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class MainFrame extends JFrame {
    //Mouse
    private class GameMouseListener extends MouseInputAdapter {
        //MouseClick
        @Override
        public void mouseClicked(MouseEvent event) {
            event = SwingUtilities.convertMouseEvent(MainFrame.this, event, getContentPane());
            Component component = getContentPane().getComponentAt(event.getPoint());
            if (component instanceof EdgeComponent) {
                EdgeComponent edgeComponent = (EdgeComponent) component;
                if (edgeComponent.isFree()) {
                    edgeComponent.setColor(currentColor);
                    edgeComponent.setFree(false);
                    edgeComponent.setVisible(true);
                    edgeComponent.repaint();
                    currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
                }
            }
        }
        
        //MouseMovement
        @Override
        public void mouseMoved(MouseEvent event) {
            event = SwingUtilities.convertMouseEvent(MainFrame.this, event, getContentPane());
            Component component = getContentPane().getComponentAt(event.getPoint());
            
            for (EdgeComponent e : edges) {
                if (e.isFree()) {
                    if (e == component) {
                        e.setColor(currentColor);
                        e.setVisible(true);
                    } else {
                        e.setVisible(false);
                    }
                }
                
            }
//            edges.stream().filter(EdgeComponent::isFree).forEach((e) -> {
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
    
    //    private List<EdgeComponent> edges = new ArrayList<>();
    private ArrayList<EdgeComponent> edges = new ArrayList<>();
    private Color currentColor;
    
    public MainFrame(int m, int n) {
        initialize(m, n);
        currentColor = Color.RED;
        GameMouseListener mouseListener = new GameMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }
    
    public void initialize(int m, int n) {
        setTitle("CS102A Project");//window title
        setSize(m * 175, n * 375 / 2);//window size
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Exit when close the window
        setLayout(null);
        
        //paint dots
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                DotComponent dotComponent = new DotComponent(75 + 150 * i, 75 + 150 * j, 30);
                getContentPane().add(dotComponent); // Add the component to the frame.
            }
        }
        
        //paint edges
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                edges.add(new EdgeComponent(100 + 150 * i, 82 + 150 * j, 150, 16));
                edges.add(new EdgeComponent(100 + 150 * i, 232 + 150 * j, 150, 16));
                edges.add(new EdgeComponent(82 + 150 * i, 100 + 150 * j, 16, 150));
                edges.add(new EdgeComponent(232 + 150 * i, 100 + 150 * j, 16, 150));
            }
        }
        for (EdgeComponent e : edges) {
            e.setVisible(false);
            getContentPane().add(e);
        }
//        edges.stream().peek(e -> e.setVisible(false)).forEach(getContentPane()::add);
    }
    
    
    public static void main(String[] args) {
        Runnable runnable = new Runner();
        SwingUtilities.invokeLater(runnable);

//        SwingUtilities.invokeLater(() -> {
//            MainFrame mainFrame = new MainFrame();
//            mainFrame.setVisible(true);
//        });
    }
    
    static class Runner implements Runnable {
        @Override
        public void run() {
            Scanner in = new Scanner(System.in);
            int m = in.nextInt();
            int n = in.nextInt();
            
            MainFrame mainFrame = new MainFrame(m, n);//construct mainFrame
            mainFrame.setVisible(true);//make mainFrame visible
        }
    }
}
