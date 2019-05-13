import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private class GameMouseListener extends MouseInputAdapter {
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

        @Override
        public void mouseMoved(MouseEvent event) {
            event = SwingUtilities.convertMouseEvent(MainFrame.this, event, getContentPane());
            Component component = getContentPane().getComponentAt(event.getPoint());

            for (EdgeComponent e:edges) {
                if(e.isFree()){
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

//    private List<EdgeComponent> edges = new ArrayList<>();
    private ArrayList<EdgeComponent> edges = new ArrayList<>();
    private Color currentColor;

    public MainFrame() {
        initialize();
        currentColor = Color.RED;
        GameMouseListener mouseListener = new GameMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    public void initialize() {
        setTitle("CS102A Project Demo");
        setSize(350, 375);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                DotComponent dotComponent = new DotComponent(75 + 150 * i, 75 + 150 * j, 30);
                getContentPane().add(dotComponent); // Add the component to the frame.
            }
        }

        edges.add(new EdgeComponent(100, 82, 150, 16));
        edges.add(new EdgeComponent(100, 232, 150, 16));
        edges.add(new EdgeComponent(82, 100, 16, 150));
        edges.add(new EdgeComponent(232, 100, 16, 150));
        for (EdgeComponent e:edges) {
            e.setVisible(false);
            getContentPane().add(e);
        }
//        edges.stream().peek(e -> e.setVisible(false)).forEach(getContentPane()::add);
    }




    public static void main(String[] args) {
        Runnable runnable=new Runner();
        SwingUtilities.invokeLater(runnable);

//        SwingUtilities.invokeLater(() -> {
//            MainFrame mainFrame = new MainFrame();
//            mainFrame.setVisible(true);
//        });
    }

    static class Runner implements Runnable{
        @Override
        public void run() {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        }
    }
}
