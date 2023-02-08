/**
 * @author: Ameer Eleyan
 * ID: 1191076
 * @created: 1/30/2023    8:09 PM
 */
package DataStructure;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;

public class Node {
    private final GL2 gl;
    private final Color color;
    private final Point center;
    private static final int radius = 5;
    private DIRECTION direction;

    private final String value;
    public Node(GL2 gl, String value,Point center, Color color, DIRECTION direction) {
        this.gl = gl;
        this.center = center;
        this.value=value;
        this.direction = direction;
        this.color = color;
    }

    public void draw() {
        this.gl.glColor3f(color.getRed() / 256f,
                color.getGreen() / 256f, color.getBlue() / 256f);
        this.midPoint();
        if (this.direction != null) {
            if (this.direction == DIRECTION.RIGHT)
                new Pointer(gl, new Point(this.center.x() + 5, this.center.y()), Color.blue, this.direction);
            else if (this.direction == DIRECTION.LEFT)
                new Pointer(gl, new Point(this.center.x() - 5, this.center.y()), Color.blue, this.direction);
            else if (this.direction == DIRECTION.TOP)
                new Pointer(gl, new Point(this.center.x(), this.center.y() + 5), Color.blue, this.direction);
            else new Pointer(gl, new Point(this.center.x(), this.center.y() - 5), Color.blue, this.direction);
        }
        TextRenderer renderer = new TextRenderer(new Font("Times New Roman", Font.PLAIN, 10));
        renderer.beginRendering(100, 100);
        renderer.setColor(1.0f, 0, 0, 1);
        renderer.draw(this.value, center.x()-radius+3, center.y()-radius+2);
        renderer.endRendering();

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
