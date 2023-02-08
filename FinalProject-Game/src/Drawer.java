/**
 * author: Ameer Eleyan
 * ID: 1191076
 * created: 12/20/2022    1:26 PM
 */

import DataStructure.*;
import DataStructure.Point;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;
import java.util.HashSet;

import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class Drawer implements GLEventListener {

    private FPSAnimator animator;

    private boolean isPlaying = false;
    private GL2 gl;

    private LinkedList linkedList;
    private Stack stack;

    private Queue queue;

    public void setAnimator(FPSAnimator animator) {
        this.animator = animator;
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        this.gl = glAutoDrawable.getGL().getGL2();
        this.linkedList = new LinkedList(this.gl, this.animator);
        this.stack = new Stack(this.gl, this.animator);
        this.queue = new Queue(this.gl, this.animator);
        this.gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        this.gl.glMatrixMode(GL_PROJECTION);
        // Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();
    }
    TextRenderer renderer;
    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {}

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        this.gl.glMatrixMode(GL2.GL_PROJECTION);
        this.gl.glLoadIdentity();
        this.gl.glOrtho(0, 100, 0, 100, -1, 1);
        this.gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {

        if (!isPlaying) {
            gl.glBegin(GL2.GL_LINES);
            gl.glColor3f(1f, 0, 1f);
            gl.glVertex2i(0, 1);
            gl.glVertex2i(100, 1);
            gl.glVertex2i(100, 1);
            gl.glVertex2i(100, 99);
            gl.glEnd();
           this.linkedList.initialize();
            isPlaying = true;
        } else {
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            gl.glLoadIdentity();
            this.linkedList.initialize();
           // this.linkedList.removeAtFirst();
            this.linkedList.removeAtLast();
        }
    }


    private void fill(Color color, Polygon polygon, HashSet<DataStructure.Point> visitedPoints, int x, int y) {
        DataStructure.Point p = new Point(x, y);
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