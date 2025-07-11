package template;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class RoundedButton extends JButton {
    private int roundTopLeft = 20;
    private int roundTopRight = 20;
    private int roundBottomLeft = 20;
    private int roundBottomRight = 20;

    // Konstruktor default (untuk GUI Builder)
    public RoundedButton() {
        super();
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    // Konstruktor dengan teks
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    // Getter & Setter untuk properti radius
    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public int getRoundTopRight() {
        return roundTopRight;
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape shape = createRoundedShape();
        g2.setColor(getBackground());
        g2.fill(shape);

        super.paintComponent(g); // Gambar teks, ikon
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape shape = createRoundedShape();
        g2.setColor(getForeground().darker());
        g2.draw(shape);

        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = createRoundedShape();
        return shape.contains(x, y);
    }

    private Shape createRoundedShape() {
        int width = getWidth();
        int height = getHeight();
        int tl = roundTopLeft;
        int tr = roundTopRight;
        int bl = roundBottomLeft;
        int br = roundBottomRight;

        Path2D.Float path = new Path2D.Float();
        path.moveTo(tl, 0);
        path.lineTo(width - tr, 0);
        path.quadTo(width, 0, width, tr);
        path.lineTo(width, height - br);
        path.quadTo(width, height, width - br, height);
        path.lineTo(bl, height);
        path.quadTo(0, height, 0, height - bl);
        path.lineTo(0, tl);
        path.quadTo(0, 0, tl, 0);
        path.closePath();
        return path;
    }
}
