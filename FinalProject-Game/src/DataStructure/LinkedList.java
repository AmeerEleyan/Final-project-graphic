/**
 * author: Ameer Eleyan, Mohammad AbuBader
 * ID: 1191076, 1190478
 * created: 1/31/2023    10:58 AM
 */

package DataStructure;

import Interface.UiLoader;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;

public class LinkedList {
    private final GL2 gl;

    private final java.util.LinkedList<Node> linkedList;

    private Pointer head, tail;

    private Point headPoint, tailPoint;

    private static final int Y_POSITION = 50;

    private int xMoveFirst = 5, xMoveLast = 65, xMoveBetween = 5, xMoveTail = 0;
    private boolean deleteLastNode = true;
    private Pointer currPointer;

    private final FPSAnimator animator;


    public void resetLinkedList() {
        this.linkedList.clear();

        this.linkedList.add(new Node(gl, "2", new Point(30, Y_POSITION), Color.WHITE, DIRECTION.RIGHT));
        this.linkedList.add(new Node(gl, "3", new Point(50, Y_POSITION), Color.WHITE, DIRECTION.RIGHT));
        this.linkedList.add(new Node(gl, "4", new Point(70, Y_POSITION), Color.WHITE, null));

        this.currPointer = null;
        this.xMoveFirst = 5;
        this.xMoveLast = 65;
        this.headPoint = new Point(30, Y_POSITION + 15);
        this.tailPoint = new Point(70, Y_POSITION + 15);
    }

    public LinkedList(GL2 gl, FPSAnimator animator) {
        this.gl = gl;
        this.animator = animator;
        this.linkedList = new java.util.LinkedList<>();
        this.resetLinkedList();
    }

    public void insertAtFirst() {
        if (this.linkedList.getFirst().getCenter().x() == 10) {
            JOptionPane.showMessageDialog(null, "The new node out of view range", "Warning Message", JOptionPane.WARNING_MESSAGE);
            this.animator.pause();
            this.handleResetButtons();
            return;
        }
        Node newNode = new Node(gl, "1", new Point(10, Y_POSITION), Color.WHITE, null);
        newNode.draw();
        if (this.xMoveFirst < 15) {
            this.xMoveFirst += 1;
            new Pointer(gl, new Point(xMoveFirst, Y_POSITION), Color.GREEN, DIRECTION.RIGHT);
        } else {
            newNode = new Node(gl, "1", new Point(10, Y_POSITION), Color.WHITE, DIRECTION.RIGHT);
            newNode.draw();
            if (this.head.getStartPoint().x() > 10) {
                this.headPoint = new Point(this.head.getStartPoint().x() - 1, this.head.getStartPoint().y());
            } else {
                this.linkedList.addFirst(newNode);
                this.animator.pause();
                this.handleResetButtons();
            }
        }
    }

    public void removeAtFirst() {
        if (this.linkedList.size() == 1) {
            this.linkedList.removeFirst();
            this.headPoint = null;
            this.tailPoint = null;
            this.gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            this.gl.glLoadIdentity();
            this.animator.pause();
            this.handleResetButtons();
            return;
        }
        if (this.linkedList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can't remove, the linked list is empty", "Warning Message", JOptionPane.WARNING_MESSAGE);
            this.animator.pause();
            UiLoader.pauseButton.setEnabled(false);
            UiLoader.runButton.setEnabled(true);
            return;
        }
        if (this.headPoint.x() < linkedList.get(1).getCenter().x()) {
            this.headPoint = new Point(this.head.getStartPoint().x() + 1, this.head.getStartPoint().y());
        } else {
            this.linkedList.removeFirst();
            this.gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            this.gl.glLoadIdentity();
            this.animator.pause();
            this.initialize();
            this.handleResetButtons();
        }
    }

    public void insertBetween() {
        if (this.linkedList.size() > 3) {
            JOptionPane.showMessageDialog(null, "The new node out of view range", "Warning Message", JOptionPane.WARNING_MESSAGE);
            this.animator.pause();
            this.handleResetButtons();
            return;
        }
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
                        this.animator.pause();
                        this.initialize();
                        this.handleResetButtons();
                    }
                }

            }
        }
    }

    public void insertAtLast() {
        if (this.linkedList.getLast().getCenter().x() == 90) {
            JOptionPane.showMessageDialog(null, "The new node out of view range", "Warning Message", JOptionPane.WARNING_MESSAGE);
            this.animator.pause();
            this.handleResetButtons();
            return;
        }
        Node newNode = new Node(gl, "5", new Point(90, Y_POSITION), Color.WHITE, null);
        newNode.draw();
        if (this.xMoveLast < 75) {
            this.xMoveLast += 1;
            new Pointer(gl, new Point(xMoveLast, Y_POSITION), Color.GREEN, DIRECTION.RIGHT);
        } else {
            new Pointer(gl, new Point(xMoveLast, Y_POSITION), Color.GREEN, DIRECTION.RIGHT);
            if (this.tail.getStartPoint().x() < 90) {
                this.tailPoint = new Point(this.tail.getStartPoint().x() + 1, this.tail.getStartPoint().y());
            } else {
                newNode = linkedList.getLast();
                newNode.setDirection(DIRECTION.RIGHT);
                newNode = new Node(gl, "5", new Point(90, Y_POSITION), Color.WHITE, null);
                this.linkedList.add(newNode);
                this.animator.pause();
                this.handleResetButtons();
            }
        }
    }

    public void removeAtLast() {
        if (this.linkedList.size() == 1) {
            this.linkedList.removeFirst();
            this.gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            this.gl.glLoadIdentity();
            this.animator.pause();
            this.handleResetButtons();
            return;
        }
        if (this.linkedList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can't remove, the linked list is empty", "Warning Message", JOptionPane.WARNING_MESSAGE);
            this.animator.pause();
            UiLoader.pauseButton.setEnabled(false);
            UiLoader.runButton.setEnabled(true);
            return;
        }
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
                    this.gl.glLoadIdentity();
                    this.animator.pause();
                    this.handleResetButtons();
                    this.initialize();
                }
            }
        }
    }

    private void handleResetButtons() {
        UiLoader.pauseButton.setEnabled(false);
        UiLoader.runButton.setEnabled(true);
        UiLoader.takeExamButton.setEnabled(true);
    }

    public void initialize() {
        this.linkedList.forEach(Node::draw);
        if (this.headPoint != null) this.head = new Pointer(gl, "Head", this.headPoint, Color.CYAN, DIRECTION.BOTTOM);
        if (this.tailPoint != null)
            this.tail = new Pointer(gl, "Tail", this.tailPoint, Color.MAGENTA, DIRECTION.BOTTOM);
    }
}
