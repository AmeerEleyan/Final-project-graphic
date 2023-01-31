/**
 * author: Ameer Eleyan
 * ID: 1191076
 * created: 1/31/2023    8:19 PM
 */
package DataStructure;

import com.jogamp.opengl.GL2;

import java.awt.*;

public class Stack {
    private final GL2 gl;

    private final java.util.Stack<Node> stack;

    private Pointer head;
    private Point headPoint;
    Node node;

    public Stack(GL2 gl) {
        this.gl = gl;
        this.stack = new java.util.Stack<>();
        node = new Node(gl, new Point(50, 90), Color.BLACK, null);
        ;
    }

    public void push() {

    }

    public void pop() {

    }


    public void initialize() {
        this.drawContainer();
        node = new Node(gl, new Point(50, 15), Color.BLACK, null);
        node.draw();
        this.stack.push(node);

        node = new Node(gl, new Point(50, 35), Color.BLACK, DIRECTION.BOTTOM);
        node.draw();
        this.stack.push(node);

        this.headPoint = new Point(this.stack.peek().getCenter().x()-15, node.getCenter().y());
        this.head = new Pointer(gl, this.headPoint, Color.MAGENTA, DIRECTION.RIGHT);
        /*if (this.stack.isEmpty()) {
            node.draw();
            if (node.getCenter().y() - 5 > 10) {
                node = new Node(gl, new Point(50, node.getCenter().y() - 5), Color.BLACK, null);
            } else {
                this.stack.push(node);
                this.headPoint = new Point(node.getCenter().x()-15, node.getCenter().y());
            }
        } else {
            node.draw();
            this.head = new Pointer(gl, this.headPoint, Color.MAGENTA, DIRECTION.RIGHT);
        }*/
    }

    private void drawContainer() {
        Pointer pointer = new Pointer(this.gl, Color.BLACK);
        pointer.drawLine(new Point(45, 10), new Point(55, 10));
        pointer.drawLine(new Point(45, 10), new Point(45, 90));
        pointer.drawLine(new Point(55, 10), new Point(55, 90));
    }
}
