/**
 * author: Ameer Eleyan
 * ID: 1191076
 * created: 1/31/2023    8:20 PM
 */
package DataStructure;

import com.jogamp.opengl.GL2;

public class Queue {
    private final GL2 gl;

    private final java.util.Queue<Node> queue;

    private Pointer head;

    private Pointer tail;

    public Queue(GL2 gl) {
        this.gl = gl;
        this.queue = new java.util.LinkedList<>();
    }

    public void add() {

    }

    public void poll() {

    }

    public void initialize(){

    }


}
