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

        String[] output = new String[3];
        int length = t1.length;
        boolean hasPreAndPost = false;

        if (t1[0].equals(t2[length - 1]) || t1[0].equals(t3[length - 1])) {
            if (t1[0].equals(t2[length - 1]) && t1[0].equals(t3[length - 1])) {
                return "Invalid traversals";
            }
            output[0] = "Preorder";
            if (t1[0].equals(t2[length - 1])) {
                if (!isValidInorder(t3)) {
                    return "Invalid traversals";
                }
                output[1] = "Postorder";
                output[2] = "Inorder";
                if (isValidTree(t1, t3, t2)) {
                    hasPreAndPost = true;
                }
            } else {
                if (!isValidInorder(t2)) {
                    return "Invalid traversals";
                }
                output[2] = "Postorder";
                output[1] = "Inorder";
                if (isValidTree(t1, t2, t3)) {
                    hasPreAndPost = true;
                }
            }
        } else if (t2[0].equals(t3[length - 1]) || t2[0].equals(t1[length - 1])) {
            if (t2[0].equals(t3[length - 1]) && t2[0].equals(t1[length - 1])) {
                return "Invalid traversals";
            }
            output[1] = "Preorder";
            if (t2[0].equals(t3[length - 1])) {
                if (!isValidInorder(t1)) {
                    return "Invalid traversals";
                }
                output[2] = "Postorder";
                output[0] = "Inorder";
                if (isValidTree(t2, t1, t3)) {
                    hasPreAndPost = true;
                }
            } else {
                if (!isValidInorder(t3)) {
                    return "Invalid traversals";
                }
                output[0] = "Postorder";
                output[2] = "Inorder";
                if (isValidTree(t2, t3, t1)) {
                    hasPreAndPost = true;
                }
            }
        } else if (t3[0].equals(t2[length - 1]) || t3[0].equals(t1[length - 1])) {
            if (t3[0].equals(t2[length - 1]) && t3[0].equals(t1[length - 1])) {
                return "Invalid traversals";
            }
            output[2] = "Preorder";
            if (t3[0].equals(t2[length - 1])) {
                if (!isValidInorder(t1)) {
                    return "Invalid traversals";
                }
                output[1] = "Postorder";
                output[0] = "Inorder";
                if (isValidTree(t3, t1, t2)) {
                    hasPreAndPost = true;
                }
            } else {
                if (!isValidInorder(t2)) {
                    return "Invalid traversals";
                }
                output[0] = "Postorder";
                output[1] = "Inorder";
                if (isValidTree(t3, t2, t1)) {
                    hasPreAndPost = true;
                }
            }
        }

        if (hasPreAndPost) {
            return String.format("Traversal 1 - %s, Traversal 2 - %s, Traversal 3 - %s", output[0], output[1], output[2]);
        }
        return "Invalid traversals";
    }

    private static boolean isValidInorder(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (Integer.parseInt(arr[i]) >= Integer.parseInt(arr[i + 1])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidTree(String[] pre, String[] ino, String[] pos) {
        if (pre.length == 0) {
            return true;
        }

        String root = pre[0];
        if (!pos[pos.length - 1].equals(root)) {
            return false;
        }

        int rootIndex = -1;
        for (int i = 0; i < ino.length; i++) {
            if (ino[i].equals(root)) {
                rootIndex = i;
                break;
            }
        }

        if (rootIndex == -1) {
            return false;
        }

        String[] leftIno = Arrays.copyOfRange(ino, 0, rootIndex);
        String[] rightIno = Arrays.copyOfRange(ino, rootIndex + 1, ino.length);

        int leftLen = leftIno.length;
        int rightLen = rightIno.length;

        String[] leftPre = Arrays.copyOfRange(pre, 1, 1 + leftLen);
        String[] rightPre = Arrays.copyOfRange(pre, 1 + leftLen, pre.length);

        String[] leftPos = Arrays.copyOfRange(pos, 0, leftLen);
        String[] rightPos = Arrays.copyOfRange(pos, leftLen, leftLen + rightLen);

        return isValidTree(leftPre, leftIno, leftPos) && isValidTree(rightPre, rightIno, rightPos);
    }
}

