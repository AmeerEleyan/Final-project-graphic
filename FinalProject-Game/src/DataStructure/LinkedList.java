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

    private Pointer head, tail;

    private Point headPoint, tailPoint;

    private static final int Y_POSITION = 50;

    private int xMoveFirst = 5, xMoveLast = 65;

    private Pointer currPointer;

    private final FPSAnimator animator;


    public LinkedList(GL2 gl, FPSAnimator animator) {
        this.gl = gl;
        this.animator = animator;

        this.linkedList = new java.util.LinkedList<>();

        this.linkedList.add(new Node(gl, "2", new Point(30, Y_POSITION), Color.WHITE, DIRECTION.RIGHT));
        this.linkedList.add(new Node(gl, "3", new Point(50, Y_POSITION), Color.WHITE, DIRECTION.RIGHT));
        this.linkedList.add(new Node(gl, "4", new Point(70, Y_POSITION), Color.WHITE, null));

        //15 => radius(5)+pointer length(10)
        headPoint = new Point(30, Y_POSITION + 15);
        tailPoint = new Point(70, Y_POSITION + 15);

        // this.initialize();
    }

    public void insertAtFirst() {
        Node newNode = new Node(gl, "1", new Point(10, Y_POSITION), Color.WHITE, null);
        newNode.draw();
        if (this.xMoveFirst < 15) {
            this.xMoveFirst += 1;
            new Pointer(gl, new Point(xMoveFirst, Y_POSITION), Color.blue, DIRECTION.RIGHT);
        } else {
            newNode = new Node(gl, "1", new Point(10, Y_POSITION), Color.WHITE, DIRECTION.RIGHT);
            newNode.draw();
            if (this.head.getStartPoint().x() > 10) {
                this.headPoint = new Point(this.head.getStartPoint().x() - 1, this.head.getStartPoint().y());
            } else {
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
            this.gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            this.gl.glClearColor(1f, 1f, 1f, 1.0f);
            this.gl.glLoadIdentity();
            this.initialize();
            this.animator.stop();
        }
    }

    private int xMoveBetween = 5, xMoveTail = 0;
    private boolean deleteLastNode = true;

    public void insertBetween() {
        Node newNode = new Node(gl, "5", new Point(xMoveBetween, Y_POSITION), Color.WHITE, null);
        newNode.draw();
        if (this.currPointer == null) {
            this.currPointer = new Pointer(gl, headPoint, new Color(150, 75, 0), DIRECTION.BOTTOM);
        }
        Point currentPoint = new Point(this.currPointer.getStartPoint().x() + 1, this.currPointer.getStartPoint().y());
        if (currentPoint.x() < this.linkedList.get(this.linkedList.size() - 2).getCenter().x()) {
            currentPoint = new Point(this.currPointer.getStartPoint().x() + 1, this.currPointer.getStartPoint().y());
            this.currPointer = new Pointer(gl, currentPoint, new Color(150, 75, 0), DIRECTION.BOTTOM);
        } else {
            this.currPointer = new Pointer(gl, new Point(this.currPointer.getStartPoint().x(), this.currPointer.getStartPoint().y()), new Color(150, 75, 0), DIRECTION.BOTTOM);
            if (xMoveBetween < 60) {
                xMoveBetween += 1;
                newNode = new Node(gl, "5", new Point(xMoveBetween, Y_POSITION), Color.WHITE, null);
                newNode.draw();
            } else {
                newNode = new Node(gl, "5", new Point(xMoveBetween, Y_POSITION), Color.WHITE, DIRECTION.RIGHT);
                newNode.draw();
                Node tail;
                if (deleteLastNode) {
                    tail = this.linkedList.removeLast();
                    deleteLastNode = false;
                    xMoveTail = tail.getCenter().x();
                }
                if (xMoveBetween < 70) {
                    xMoveBetween += 1;
                    xMoveTail += 1;
                    tail = new Node(gl, "4", new Point(xMoveTail, Y_POSITION), Color.WHITE, null);
                    tail.draw();
                    this.tailPoint = new Point(this.tail.getStartPoint().x() + 1, this.tail.getStartPoint().y());
                    newNode = new Node(gl, "5", new Point(xMoveBetween, Y_POSITION), Color.WHITE, null);
                    newNode.draw();
                } else {
                    newNode = new Node(gl, "5", new Point(xMoveBetween, Y_POSITION), Color.WHITE, null);
                    newNode.draw();
                    if (xMoveTail < 90) {
                        xMoveTail += 1;
                        tail = new Node(gl, "4", new Point(xMoveTail, Y_POSITION), Color.WHITE, null);
                        tail.draw();

                        this.tailPoint = new Point(this.tail.getStartPoint().x() + 1, this.tail.getStartPoint().y());
                    } else {
                        tail = new Node(gl, "4", new Point(xMoveTail, Y_POSITION), Color.WHITE, null);
                        tail.draw();
                        newNode.setDirection(DIRECTION.RIGHT);
                        linkedList.add(newNode);
                        linkedList.add(tail);
                        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
                        gl.glLoadIdentity();
                        this.initialize();
                        this.animator.stop();
                    }
                }

            }
        }
    }

    public void insertAtLast() {
        Node newNode = new Node(gl, "5", new Point(90, Y_POSITION), Color.WHITE, null);
        newNode.draw();
        if (this.xMoveLast < 75) {
            this.xMoveLast += 1;
            new Pointer(gl, new Point(xMoveLast, Y_POSITION), Color.blue, DIRECTION.RIGHT);
        } else {
            new Pointer(gl, new Point(xMoveLast, Y_POSITION), Color.blue, DIRECTION.RIGHT);
            if (this.tail.getStartPoint().x() < 90) {
                this.tailPoint = new Point(this.tail.getStartPoint().x() + 1, this.tail.getStartPoint().y());
            } else {
                newNode = linkedList.getLast();
                newNode.setDirection(DIRECTION.RIGHT);
                newNode = new Node(gl, "5", new Point(90, Y_POSITION), Color.WHITE, null);
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
                    this.gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
                    this.gl.glClearColor(1f, 1f, 1f, 1.0f);
                    this.gl.glLoadIdentity();
                    this.initialize();
                    this.animator.stop();
                }
            }
        }
    }

    public void initialize() {
        linkedList.forEach(Node::draw);
        this.head = new Pointer(gl, "Head", this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
        this.tail = new Pointer(gl, "Tail", this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
    }
}
