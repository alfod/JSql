package me.alfod.basedao;

/**
 * @author Yang Dong
 * @createTime 2018/9/25  18:14
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2018/9/25  18:14
 * @note
 */
public class LinkedStringBuilder {

    private int length;

    private Node first;

    private Node last;

    public void append(CharSequence charSequence) {
        if (first == null) {
            first = new Node(charSequence);
            last = first;
            length = charSequence.length();
            return;
        }
        last = last.append(charSequence);
        length += charSequence.length();
    }

    @Override
    public String toString() {
        char[] chars = new char[length];
        Node node = first;
        int index = 0;
        while (node != null && node.s != null) {
            for (int i = 0; i < node.s.length(); i++) {
                chars[index + i + 1] = node.s.charAt(i);
            }
            node = node.next;
        }
        return new String(chars);
    }

    private int length() {
        return length;
    }

    private static class Node {
        CharSequence s;
        Node next;

        public Node(CharSequence s) {
            this.s = s;
        }

        public Node append(CharSequence charSequence) {
            this.next = new Node(charSequence);
            return this.next;
        }
    }
}
