/**
 * author: Ameer Eleyan
 * ID: 1191076
 * created: 12/20/2022    1:55 PM
 */

public record Point(int x, int y) {
    /**
     * @param obj the reference object with which to compare.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point)
            return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
        return false;
    }
}
