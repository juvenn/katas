package katas;

import java.util.ArrayList;

public class UndirectedGraphNode {
    public int label;
    public ArrayList<UndirectedGraphNode> neighbors;
    public UndirectedGraphNode(int n) {
        label = n;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
