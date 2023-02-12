/**
 * author: Ameer Eleyan, Mohammad AbuBader
 * ID: 1191076, 1190478
 * created: 1/31/2023    8:20 PM
 */
package DataStructure;

import Interface.UiLoader;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
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
            new Pointer(gl, "Head", this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
        }
        if (!this.queue.isEmpty() && this.tailPoint != null) {
            new Pointer(gl, "Tail", this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
        }
        if (!this.queue.isEmpty() && this.queue.getLast().getCenter().x() == 11) {
            JOptionPane.showMessageDialog(null, "The new node out of view range", "Warning Message", JOptionPane.WARNING_MESSAGE);
            this.animator.pause();
            this.handleResetButtons();
            return;
        }
        if (xMoveEnqueue <= xTail) {
            int val = (this.queue.isEmpty()) ? 1 : (this.queue.getLast().getValue() + 1);
            node = new Node(gl, val + "", new Point(++xMoveEnqueue, Y_POSITION), Color.WHITE, null);
            node.draw();
        } else {
            node.draw();
            this.headPoint = new Point((this.queue.isEmpty()) ? 90 : (this.queue.peek().getCenter().x()), Y_POSITION + 15);
            this.head = new Pointer(gl, "Head", this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
            if (this.queue.isEmpty()) {
                this.tailPoint = new Point(this.tailPoint.x(), Y_POSITION + 15);
                if (this.headPoint.x() == this.tailPoint.x()) {
                    Point point = new Point(this.tailPoint.x() + 4, this.tailPoint.y());
                    this.tail = new Pointer(gl, "Tail", point, Color.MAGENTA, DIRECTION.BOTTOM);
                } else {
                    this.tail = new Pointer(gl, "Tail", this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
                }
                this.queue.add(node);
                xMoveEnqueue = 2;
                xTail -= 20;
                this.animator.pause();
                this.handleResetButtons();
            } else {
                this.queue.getLast().setDirection(DIRECTION.LEFT);
                if (this.tailPoint.x() > xTail + 1) {
                    this.tail = new Pointer(gl, "Tail", this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
                    this.tailPoint = new Point(this.tailPoint.x() - 1, Y_POSITION + 15);
                } else {
                    this.queue.add(node);
                    xMoveEnqueue = 2;
                    xTail -= 20;
                    this.animator.pause();
                    this.handleResetButtons();
                }
            }
        }
    }


    public void dequeue() {
        if (this.queue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can't dequeue, the queue is empty", "Warning Message", JOptionPane.WARNING_MESSAGE);
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            gl.glLoadIdentity();
            this.animator.pause();
            return;
        }

        this.head = new Pointer(gl, "Head", this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
        this.tail = new Pointer(gl, "Tail", this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
        this.queue.forEach(Node::draw);

        Node temp = this.queue.poll();
        if (this.queue.isEmpty()) {
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            gl.glLoadIdentity();
            this.animator.pause();
            this.handleResetButtons();
            restQueue();
        } else {
            int nextNode = this.queue.peek().getCenter().x();
            this.queue.addFirst(temp);
            if (this.headPoint.x() > nextNode) {
                this.headPoint = new Point(this.headPoint.x() - 1, Y_POSITION + 15);
                this.head = new Pointer(gl, "Head", this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
            } else {
                this.animator.pause();
                this.handleResetButtons();
                this.queue.poll();
                gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
                gl.glLoadIdentity();
                if (!this.queue.isEmpty()) {
                    this.head = new Pointer(gl, "Head", this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
                    this.tail = new Pointer(gl, "Tail", this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
                    this.queue.forEach(Node::draw);
                } else {
                    restQueue();
                }
            }
        }
    }

    private void handleResetButtons() {
        UiLoader.pauseButton.setEnabled(false);
        UiLoader.runButton.setEnabled(true);
        UiLoader.takeExamButton.setEnabled(true);
    }

    private void restQueue() {
        this.xTail = 90;
        this.xMoveEnqueue = 5;
        this.node = null;
        this.head = null;
        this.tail = null;
        this.tailPoint = new Point(xTail, Y_POSITION + 15);
        this.headPoint = null;
    }

}
