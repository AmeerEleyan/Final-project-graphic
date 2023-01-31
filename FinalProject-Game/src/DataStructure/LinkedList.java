package DataStructure;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.*;


/**
 * author: Ameer Eleyan
 * ID: 1191076
 * created: 1/31/2023    10:58 AM
 */

public class LinkedList {
    private final GL2 gl;

    private final java.util.LinkedList<Node> linkedList;

    private Pointer head;

    private Pointer tail;

    private Point headPoint, tailPoint;

    private static final int Y_POSITION = 50;

    private int xMoveFirst = 5, xMoveLast = 65;

    private Pointer currPointer;

    private final FPSAnimator animator;


    public LinkedList(GL2 gl, FPSAnimator animator) {
        this.gl = gl;
        this.animator = animator;

        this.linkedList = new java.util.LinkedList<>();

        this.linkedList.add(new Node(gl, new Point(30, Y_POSITION), Color.black, DIRECTION.RIGHT));
        this.linkedList.add(new Node(gl, new Point(50, Y_POSITION), Color.black, DIRECTION.RIGHT));
        this.linkedList.add(new Node(gl, new Point(70, Y_POSITION), Color.black, null));

        //15 => radius(5)+pointer length(10)
        headPoint = new Point(30, Y_POSITION + 15);
        tailPoint = new Point(70, Y_POSITION + 15);

        this.initialize();
    }

    public void insertAtFirst() {
        Node newNode = new Node(gl, new Point(10, Y_POSITION), Color.RED, null);
        newNode.draw();
        if (this.xMoveFirst < 15) {
            this.xMoveFirst += 1;
            new Pointer(gl, new Point(xMoveFirst, Y_POSITION), Color.blue, DIRECTION.RIGHT);
        } else {
            newNode = new Node(gl, new Point(10, Y_POSITION), Color.RED, DIRECTION.RIGHT);
            newNode.draw();
            if (this.head.getStartPoint().x() > 10) {
                this.headPoint = new Point(this.head.getStartPoint().x() - 1, this.head.getStartPoint().y());
            } else {
                newNode.draw();
                this.linkedList.addFirst(newNode);
                this.animator.stop();
            }
        }

    }

    public void removeAtFirst() {
        if (this.headPoint.x() < linkedList.get(1).getCenter().x()) {
            this.headPoint = new Point(this.head.getStartPoint().x() + 1, this.head.getStartPoint().y());
        } else {
            this.linkedList.removeFirst();

            gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            this.gl.glClearColor(1f, 1f, 1f, 1.0f);
            gl.glLoadIdentity();
            initialize();
            this.animator.stop();
        }
    }

    public void insertBetween(){

    }

    public void insertAtLast() {
        Node newNode = new Node(gl, new Point(90, Y_POSITION), Color.RED, null);
        newNode.draw();
        if (this.xMoveLast < 75) {
            this.xMoveLast += 1;
            new Pointer(gl, new Point(xMoveLast, Y_POSITION), Color.blue, DIRECTION.RIGHT);
        } else {
            newNode.draw();
            new Pointer(gl, new Point(xMoveLast, Y_POSITION), Color.blue, DIRECTION.RIGHT);
            if (this.tail.getStartPoint().x() < 90) {
                this.tailPoint = new Point(this.tail.getStartPoint().x() + 1, this.tail.getStartPoint().y());
            } else {
                newNode = linkedList.getLast();
                newNode.setDirection(DIRECTION.RIGHT);
                newNode.draw();
                newNode = new Node(gl, new Point(90, Y_POSITION), Color.RED, null);
                this.linkedList.add(newNode);
                this.animator.stop();
            }
        }
    }

    public void removeAtLast() {
        if (this.currPointer == null) {
            this.currPointer = new Pointer(gl, headPoint, new Color(150, 75, 0), DIRECTION.BOTTOM);
        } else {
            Point currentPoint = new Point(this.currPointer.getStartPoint().x() + 1, this.currPointer.getStartPoint().y());
            if (currentPoint.x() < this.linkedList.get(this.linkedList.size() - 2).getCenter().x()) {
                currentPoint = new Point(this.currPointer.getStartPoint().x() + 1, this.currPointer.getStartPoint().y());
                this.currPointer = new Pointer(gl, currentPoint, new Color(150, 75, 0), DIRECTION.BOTTOM);
            } else {
                this.currPointer = new Pointer(gl, new Point(this.currPointer.getStartPoint().x(), this.currPointer.getStartPoint().y()), new Color(150, 75, 0), DIRECTION.BOTTOM);
                if (this.tailPoint.x() > this.linkedList.get(this.linkedList.size() - 2).getCenter().x()) {
                    this.tailPoint = new Point(this.tail.getStartPoint().x() - 1, this.tail.getStartPoint().y());
                } else {
                    this.linkedList.removeLast();
                    this.linkedList.getLast().setDirection(null);
                    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
                    this.gl.glClearColor(1f, 1f, 1f, 1.0f);
                    gl.glLoadIdentity();
                    initialize();
                    this.animator.stop();
                }
            }
        }
    }

    public void initialize() {
        linkedList.forEach(Node::draw);
        this.head = new Pointer(gl, this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
        this.tail = new Pointer(gl, this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
    }
}
