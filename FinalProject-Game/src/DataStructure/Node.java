/**
 * @author: Ameer Eleyan
 * ID: 1191076
 * @created: 1/30/2023    8:09 PM
 */
package DataStructure;

import com.jogamp.opengl.GL2;

import java.awt.*;

public class Node {
    private final GL2 gl;
    private final Color color;
    private final Point center;
    private static final int radius = 5;

    private DIRECTION direction;

    public Node(GL2 gl, Point center, Color color, DIRECTION direction) {
        this.gl = gl;
        this.center = center;
        this.direction = direction;
        this.color = color;
    }

    public void draw() {
        this.gl.glColor3f(color.getRed() / 256f,
                color.getGreen() / 256f, color.getBlue() / 256f);
        this.midPoint();
        if (this.direction != null)
            new Pointer(gl, new Point(this.center.x() + 5, this.center.y()), Color.blue, this.direction);

    }

    public Point getCenter() {
        return center;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    private void midPoint() {
        float x = 0;
        float y = radius;
        float p = 1 - radius;
        circlePlotPoint(this.gl, this.center.x(), this.center.y(), x, y);
        while (x <= y) {
            x += 0.001;
            if (p < 0) {
                p = p + 2 * x + 0.001f;
            } else {
                y -= 0.001f;
                p = p + 2 * (x - y) + 0.001f;
            }
            circlePlotPoint(this.gl, this.center.x(), this.center.y(), x, y);
        }
    }

    private void circlePlotPoint(final GL2 gl, float cx, float cy, float x, float y) {
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(cx + x, cy + y);
        gl.glVertex2f(cx - x, cy + y);
        gl.glVertex2f(cx + x, cy - y);
        gl.glVertex2f(cx - x, cy - y);
        gl.glVertex2f(cx + y, cy + x);
        gl.glVertex2f(cx - y, cy + x);
        gl.glVertex2f(cx + y, cy - x);
        gl.glVertex2f(cx - y, cy - x);
        gl.glEnd();
    }
}