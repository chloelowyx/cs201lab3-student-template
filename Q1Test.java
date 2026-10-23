
import java.util.*;

public class Q1Test {

    public static void main(String[] args) {

        String result, traversal1, traversal2, traversal3;

        traversal1 = "1-2";
        traversal2 = "2-1-3";
        traversal3 = "1-3-2";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println("Expected Result : Invalid traversals");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "1-2-3";
        traversal2 = "2-1-3";
        traversal3 = "1-3-2";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
                "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "1-2-3";
        traversal2 = "3-2-1";
        traversal3 = "2-3-1";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println("Expected Result : Invalid traversals");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "3-1-2-5-4";
        traversal2 = "2-1-4-5-3";
        traversal3 = "1-2-3-4-5";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
                "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "10-20-30-40-50";
        traversal2 = "20-10-30-40-50";
        traversal3 = "10-50-40-30-20";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
                "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();
    }

    // write your codes here
    public static String verify(String traversal1, String traversal2, String traversal3) {
        String[] t1 = traversal1.split("-");
        String[] t2 = traversal2.split("-");
        String[] t3 = traversal3.split("-");

        if (t1.length != t2.length || t1.length != t3.length) {
            return "Invalid traversals";
        }

        int length = t1.length;
        int[] a1 = parseArray(t1);
        int[] a2 = parseArray(t2);
        int[] a3 = parseArray(t3);

        if (a1 == null || a2 == null || a3 == null) {
            return "Invalid traversals";
        }

        String[] output = new String[3];
        boolean hasPreAndPost = false;

        if (a1[0] == a2[length - 1] || a1[0] == a3[length - 1]) {
            if (a1[0] == a2[length - 1] && a1[0] == a3[length - 1]) {
                return "Invalid traversals";
            }
            output[0] = "Preorder";
            if (a1[0] == a2[length - 1]) {
                if (!isValidInorder(a3))
                    return "Invalid traversals";
                if (matchesPostorder(a1, a2)) {
                    output[1] = "Postorder";
                    output[2] = "Inorder";
                    hasPreAndPost = true;
                }
            } else {
                if (!isValidInorder(a2))
                    return "Invalid traversals";
                if (matchesPostorder(a1, a3)) {
                    output[2] = "Postorder";
                    output[1] = "Inorder";
                    hasPreAndPost = true;
                }
            }
        } else if (a2[0] == a3[length - 1] || a2[0] == a1[length - 1]) {
            if (a2[0] == a3[length - 1] && a2[0] == a1[length - 1]) {
                return "Invalid traversals";
            }
            output[1] = "Preorder";
            if (a2[0] == a3[length - 1]) {
                if (!isValidInorder(a1))
                    return "Invalid traversals";
                if (matchesPostorder(a2, a3)) {
                    output[2] = "Postorder";
                    output[0] = "Inorder";
                    hasPreAndPost = true;
                }
            } else {
                if (!isValidInorder(a3))
                    return "Invalid traversals";
                if (matchesPostorder(a2, a1)) {
                    output[0] = "Postorder";
                    output[1] = "Inorder";
                    hasPreAndPost = true;
                }
            }
        } else if (a3[0] == a2[length - 1] || a3[0] == a1[length - 1]) {
            if (a3[0] == a2[length - 1] && a3[0] == a1[length - 1]) {
                return "Invalid traversals";
            }
            output[2] = "Preorder";
            if (a3[0] == a2[length - 1]) {
                if (!isValidInorder(a1))
                    return "Invalid traversals";
                if (matchesPostorder(a3, a2)) {
                    output[1] = "Postorder";
                    output[0] = "Inorder";
                    hasPreAndPost = true;
                }
            } else {
                if (!isValidInorder(a2))
                    return "Invalid traversals";
                if (matchesPostorder(a3, a1)) {
                    output[0] = "Postorder";
                    output[1] = "Inorder";
                    hasPreAndPost = true;
                }
            }
        }

        if (hasPreAndPost) {
            return String.format("Traversal 1 - %s, Traversal 2 - %s, Traversal 3 - %s", output[0], output[1],
                    output[2]);
        }
        return "Invalid traversals";
    }

    private static int[] parseArray(String[] arr) {
        int[] nums = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = Integer.parseInt(arr[i]);
        }
        return nums;
    }

    private static boolean isValidInorder(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] >= arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    private static boolean matchesPostorder(int[] pre, int[] expectedPost) {
        if (pre.length == 0)
            return true;

        Node root = new Node(pre[0]);
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        int lowerBound = Integer.MIN_VALUE;

        for (int i = 1; i < pre.length; i++) {
            int val = pre[i];
            if (val < lowerBound)
                return false;

            Node curr = new Node(val);
            if (val < stack.peek().val) {
                stack.peek().left = curr;
                stack.push(curr);
            } else {
                Node last = null;
                while (!stack.isEmpty() && val > stack.peek().val) {
                    last = stack.pop();
                    lowerBound = last.val;
                }
                last.right = curr;
                stack.push(curr);
            }
        }

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(root);

        while (!s1.isEmpty()) {
            Node curr = s1.pop();
            s2.push(curr);
            if (curr.left != null)
                s1.push(curr.left);
            if (curr.right != null)
                s1.push(curr.right);
        }

        for (int i = 0; i < expectedPost.length; i++) {
            if (s2.pop().val != expectedPost[i]) {
                return false;
            }
        }

        return true;
    }
}
