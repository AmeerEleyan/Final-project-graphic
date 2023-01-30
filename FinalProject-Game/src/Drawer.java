/**
 * author: Ameer Eleyan
 * ID: 1191076
 * created: 12/20/2022    1:26 PM
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.*;
import java.util.HashSet;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import static java.lang.Math.abs;
import static jogamp.opengl.util.av.EGLMediaPlayerImpl.TextureType.GL;

public class Drawer implements GLEventListener {

    private GL2 gl;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        this.gl = glAutoDrawable.getGL().getGL2();
        this.gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        this.gl.glClearColor(1f, 1f, 1f, 1.0f);
        this.gl.glMatrixMode(GL_PROJECTION);
        // Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        this.gl.glMatrixMode(GL2.GL_PROJECTION);
        this.gl.glLoadIdentity();
        this.gl.glOrtho(0, 100, 0, 100, -1, 1);
        this.gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        this.gl.glClearColor(1f, 1f, 1f, 1.0f);
        gl.glLoadIdentity();
        new Pointer(gl, new Point(50, 50), Color.BLUE, DIRECTION.TOP);
        new Pointer(gl, new Point(50, 50), Color.BLUE, DIRECTION.RIGHT);

        new Node(gl, new Point(70,70), Color.green,5);


        this.gl.glRasterPos2i(20,20);

    }


    private void fill(Color color, Polygon polygon, HashSet<Point> visitedPoints, int x, int y) {
        Point p = new Point(x, y);
        if (!visitedPoints.contains(p)) {
            visitedPoints.add(p);
            if (polygon.contains(x * 100, y * 100)) {

                gl.glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
                gl.glBegin(GL2.GL_POINTS);
                gl.glVertex2f(x, y);
                gl.glEnd();
                fill(color, polygon, visitedPoints, x + 1, y);
                fill(color, polygon, visitedPoints, x - 1, y);
                fill(color, polygon, visitedPoints, x, y + 1);
                fill(color, polygon, visitedPoints, x, y - 1);
            }
        }
    }

}