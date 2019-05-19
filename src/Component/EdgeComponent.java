package Component;

import javax.swing.*;
import java.awt.*;

public class EdgeComponent extends JComponent {
    private Color color = Color.WHITE;
    private boolean free = true;
    
    public EdgeComponent(int x, int y, int width, int height) {
        super();
        setLocation(x, y);
        setSize(width, height);
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public boolean isFree() {
        return free;
    }
    
    public void setFree(boolean free) {
        this.free = free;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        boolean horizontal = getWidth() > getHeight();
        int midValue = (horizontal ? getHeight() : getWidth()) / 2;
        int alphaStep = free ? 255 / midValue : 0;
        for (int i = 0; i < midValue; i++) {
            g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 255 - alphaStep * i));//alphaStep represent the transparent of the edges
            if (horizontal) {
                g.drawLine(0, midValue + i, getWidth(), midValue + i);
                g.drawLine(0, midValue - i, getWidth(), midValue - i);
            } else {
                g.drawLine(midValue + i, 0, midValue + i, getHeight());
                g.drawLine(midValue - i, 0, midValue - i, getHeight());
            }
        }
    }
}
