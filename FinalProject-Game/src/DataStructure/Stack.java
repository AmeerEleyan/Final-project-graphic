/**
 * author: Ameer Eleyan
 * ID: 1191076
 * created: 1/31/2023    8:19 PM
 */
package DataStructure;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;

public class Stack {
    private final GL2 gl;

    private final java.util.Stack<Node> stack;

    private Pointer head;

    private Point headPoint;

    private Node node;

    private final FPSAnimator animator;

    private int yNodePush, yMoveHead;

    public Stack(GL2 gl, FPSAnimator animator) {
        this.gl = gl;
        this.yNodePush = 90;
        this.yMoveHead = 15;
        this.animator = animator;
        this.stack = new java.util.Stack<>();
        this.drawContainer();
    }

    public void push() {
        this.drawCurrentStack();
        if (this.headPoint != null) {
            this.head = new Pointer(this.gl, "Head", this.headPoint, Color.MAGENTA, DIRECTION.RIGHT);
        }
        if (!this.stack.isEmpty() && this.stack.peek().getCenter().y() == 75) {
            JOptionPane.showMessageDialog(null,
                    "New node out of range, please reset view"
                    , "Warning Message", JOptionPane.WARNING_MESSAGE);
            this.animator.pause();
            return;
        }
        if (this.yNodePush >= ((this.stack.isEmpty()) ? 15 : this.stack.peek().getCenter().y() + 20)) {
            this.node = new Node(this.gl, this.stack.size() + 1 + "", new Point(50, this.yNodePush--), Color.WHITE, null);
            this.node.draw();
        } else {
            if (!this.stack.isEmpty()) {
                this.node = new Node(this.gl, this.stack.size() + 1 + "", new Point(50, 1 + this.yNodePush), Color.WHITE, DIRECTION.BOTTOM);
            }
            this.node.draw();
            if (this.stack.isEmpty()) {
                this.headPoint = new Point(node.getCenter().x() - 15, this.yMoveHead);
                this.stack.push(node);
                this.yNodePush = 90;
                this.head = new Pointer(this.gl, "Head", this.headPoint, Color.MAGENTA, DIRECTION.RIGHT);
                this.animator.pause();
            } else {
                if (this.yMoveHead < this.node.getCenter().y()) {
                    this.headPoint = new Point(this.node.getCenter().x() - 15, ++this.yMoveHead);
                } else {
                    this.stack.push(this.node);
                    this.yNodePush = 90;
                    this.head = new Pointer(this.gl, "Head", this.headPoint, Color.MAGENTA, DIRECTION.RIGHT);
                    this.animator.pause();
                }
            }
        }
    }

    public void pop() {
        if (this.stack.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Can't pop, the stack is empty"
                    , "Warning Message", JOptionPane.WARNING_MESSAGE);
            this.animator.pause();
            return;
        }
        this.drawCurrentStack();

        Node temp = this.stack.pop();
        if (this.stack.isEmpty()) {
            this.headPoint = null;
            this.gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            this.gl.glLoadIdentity();
            this.animator.pause();
            return;
        }
        int y = this.stack.peek().getCenter().y();
        this.stack.push(temp);
        temp.draw();
        if (this.yMoveHead > y) {
            this.headPoint = new Point(this.node.getCenter().x() - 15, --this.yMoveHead);
            this.head = new Pointer(this.gl, "Head", this.headPoint, Color.MAGENTA, DIRECTION.RIGHT);
        } else {
            this.stack.pop();
            this.gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            this.gl.glLoadIdentity();
            this.head = new Pointer(this.gl, "Head", this.headPoint, Color.MAGENTA, DIRECTION.RIGHT);
            this.animator.pause();
            this.drawCurrentStack();
        }
    }

    private void drawCurrentStack() {
        this.stack.forEach(Node::draw);
        this.drawContainer();
    }

    private void drawContainer() {
        Pointer pointer = new Pointer(this.gl, Color.WHITE);
        pointer.drawLine(new Point(45, 10), new Point(55, 10));
        pointer.drawLine(new Point(45, 10), new Point(45, 90));
        pointer.drawLine(new Point(55, 10), new Point(55, 90));
    }
}
