import java.util.ArrayList;
import java.util.List;

/**
 * leecodeNode
 *
 * @author liyongsheng
 * @date 2021/11/11 16:20
 **/
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
