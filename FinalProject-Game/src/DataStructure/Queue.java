/**
 * author: Ameer Eleyan
 * ID: 1191076
 * created: 1/31/2023    8:20 PM
 */
package DataStructure;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.*;

public class Queue {
    private final GL2 gl;

    private final java.util.LinkedList<Node> queue;

    private Pointer head, tail;

    private Point headPoint, tailPoint;

    private int xMoveEnqueue = 5, xTail = 90;

    private static final int Y_POSITION = 50;

    private final FPSAnimator animator;

    private Node node;

    public Queue(GL2 gl, FPSAnimator animator) {
        this.gl = gl;
        this.animator = animator;
        this.queue = new java.util.LinkedList<>();
        this.tailPoint = new Point(xTail, Y_POSITION + 15);
    }


    public void enqueue() {
        this.queue.forEach(Node::draw);
        if (this.headPoint != null) {
            new Pointer(gl, this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
        }
        if (!this.queue.isEmpty() && this.tailPoint != null) {
            new Pointer(gl, this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
        }
        if (this.queue.size() > 4)
            return;
        if (xMoveEnqueue <= xTail) {
            node = new Node(gl, this.queue.size() + 1 + "", new Point(++xMoveEnqueue, Y_POSITION), Color.WHITE, null);
            node.draw();
        } else {
            node.draw();
            this.headPoint = new Point((this.queue.isEmpty()) ? 90 : (this.queue.peek().getCenter().x()), Y_POSITION + 15);
            this.head = new Pointer(gl, this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
            if (this.queue.isEmpty()) {
                this.tailPoint = new Point(this.tailPoint.x(), Y_POSITION + 15);
                this.tail = new Pointer(gl, this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
                this.queue.add(node);
                xMoveEnqueue = 10;
                xTail -= 20;
                //this.animator.pause();
            } else {
                this.queue.getLast().setDirection(DIRECTION.LEFT);
                if (this.tailPoint.x() > xTail + 1) {
                    this.tail = new Pointer(gl, this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
                    this.tailPoint = new Point(this.tailPoint.x() - 1, Y_POSITION + 15);
                } else {
                    this.queue.add(node);
                    xMoveEnqueue = 10;
                    xTail -= 20;
                }
            }
        }
    }

    public void dequeue() {
        this.head = new Pointer(gl, this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
        this.tail = new Pointer(gl, this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
        this.queue.forEach(Node::draw);
        if (this.queue.isEmpty()) return;
        Node temp = this.queue.poll();
        if (this.queue.isEmpty()) {
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            gl.glLoadIdentity();
            this.animator.pause();
        } else {
            int nextNode = this.queue.peek().getCenter().x();
            this.queue.addFirst(temp);
            if (this.headPoint.x() > nextNode) {
                this.headPoint = new Point(this.headPoint.x() - 1, Y_POSITION + 15);
                this.head = new Pointer(gl, this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
            } else {
                this.queue.poll();
                gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
                gl.glLoadIdentity();
                this.head = new Pointer(gl, this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
                this.tail = new Pointer(gl, this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
                this.queue.forEach(Node::draw);
            }
        }
    }

}
