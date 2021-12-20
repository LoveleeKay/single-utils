public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static ListNode constructByIntArr(int[] arr) {
        ListNode p = new ListNode(0);
        ListNode head = p;
        for (int value : arr) {
            p.next = new ListNode(value);
            p = p.next;
        }
        return head.next;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(val);
        ListNode p = next;
        while (p != null) {
            sb.append(",");
            sb.append(p.val);
            p = p.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
