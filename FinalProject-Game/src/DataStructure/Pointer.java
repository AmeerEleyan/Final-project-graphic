/**
 * @author: Ameer Eleyan
 * ID: 1191076
 * @created: 1/30/2023    5:57 PM
 */
package DataStructure;

import com.jogamp.opengl.GL2;

import java.awt.*;


public class Pointer {

    private final GL2 gl;
    private Point startPoint;
    private DIRECTION direction;
    private static final int POINTER_LENGTH = 10;

    public Pointer(GL2 gl, Color color) {
        this.gl = gl;
        this.gl.glColor3f(color.getRed() / 256f,
                color.getGreen() / 256f, color.getBlue() / 256f);
    }

    public Pointer(GL2 gl, Point startPoint, Color color, DIRECTION direction) {
        this.gl = gl;
        this.startPoint = startPoint;
        this.direction = direction;
        this.gl.glColor3f(color.getRed() / 256f,
                color.getGreen() / 256f, color.getBlue() / 256f);
        this.drawPointer();
    }

    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * Draw pointer in any direction
     */
    private void drawPointer() {
        Point end;
        if (this.direction == DIRECTION.TOP) {
            end = new Point(this.startPoint.x(), this.startPoint.y() + POINTER_LENGTH);
        } else if (this.direction == DIRECTION.BOTTOM) {
            end = new Point(this.startPoint.x(), this.startPoint.y() - POINTER_LENGTH);
        } else if (this.direction == DIRECTION.RIGHT) {
            end = new Point(this.startPoint.x() + POINTER_LENGTH, this.startPoint.y());
        } else {
            end = new Point(this.startPoint.x() - POINTER_LENGTH, this.startPoint.y());
        }
        this.drawLine(this.startPoint, end);
        this.drawLine(this.startPoint, end);
        this.drawPointerHead(end);
        this.drawPointerHead(end);
    }

    /**
     * Draw head of pointer(>)
     */
    private void drawPointerHead(Point end) {
        if (this.direction == DIRECTION.TOP) {
            this.drawLine(end, new Point(end.x() + 1, end.y() - 2));
            this.drawLine(end, new Point(end.x() - 1, end.y() - 2));
        } else if (this.direction == DIRECTION.BOTTOM) {
            this.drawLine(end, new Point(end.x() + 1, end.y() + 2));
            this.drawLine(end, new Point(end.x() - 1, end.y() + 2));
        } else if (this.direction == DIRECTION.RIGHT) {
            this.drawLine(end, new Point(end.x() - 1, end.y() + 2));
            this.drawLine(end, new Point(end.x() - 1, end.y() - 2));
        } else {
            this.drawLine(end, new Point(end.x() + 1, end.y() + 2));
            this.drawLine(end, new Point(end.x() + 1, end.y() - 2));
        }
    }

    /**
     * Draw line using presnham  algorithm
     */
    public void drawLine(Point start, Point end) {
        float dx, dy, i, p;
        float stepX, stepY, twoDyDx, twoDy;
        float x, y;
        dx = end.x() - start.x();
        dy = end.y() - start.y();

        if (dx < 0) dx = -dx;
        if (dy < 0) dy = -dy;

        stepX = 0.001f;
        if (end.x() < start.x()) stepX = -0.001f;
        stepY = 0.001f;
        if (end.y() < start.y()) stepY = -0.001f;

        x = start.x();
        y = start.y();
        this.gl.glBegin(GL2.GL_POINTS);
        this.gl.glVertex2f(x, y);
        if (dx > dy) {
            p = (2 * dy) - dx;
            twoDyDx = 2 * (dy - dx);
            twoDy = 2 * dy;
            for (i = 0; i < dx; i += 0.001) {
                if (p >= 0) {
                    y += stepY;
                    p += twoDyDx;
                } else {
                    p += twoDy;
                }
                x += stepX;
                this.gl.glVertex2f(x, y);
            }
        } else {
            p = (2 * dx) - dy;
            twoDyDx = 2 * (dx - dy);
            twoDy = 2 * dx;
            for (i = 0; i < dy; i += 0.001) {
                if (p >= 0) {
                    x += stepX;
                    p += twoDyDx;
                } else {
                    p += twoDy;
                }
                y += stepY;
                this.gl.glVertex2f(x, y);
            }
        }
        this.gl.glEnd();
    }
}
