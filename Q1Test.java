
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
                if (checkTree(t1, t3, t2)) {
                    hasPreAndPost = true;
                }
            } else {
                if (!isValidInorder(t2)) {
                    return "Invalid traversals";
                }
                output[2] = "Postorder";
                output[1] = "Inorder";
                if (checkTree(t1, t2, t3)) {
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
                if (checkTree(t2, t1, t3)) {
                    hasPreAndPost = true;
                }
            } else {
                if (!isValidInorder(t3)) {
                    return "Invalid traversals";
                }
                output[0] = "Postorder";
                output[2] = "Inorder";
                if (checkTree(t2, t3, t1)) {
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
                if (checkTree(t3, t1, t2)) {
                    hasPreAndPost = true;
                }
            } else {
                if (!isValidInorder(t2)) {
                    return "Invalid traversals";
                }
                output[0] = "Postorder";
                output[1] = "Inorder";
                if (checkTree(t3, t2, t1)) {
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

    private static boolean checkTree(String[] pre, String[] ino, String[] pos) {
        Map<String, Integer> inoMap = new HashMap<>();
        for (int i = 0; i < ino.length; i++) {
            inoMap.put(ino[i], i);
        }
        return isValidTree(pre, 0, pre.length - 1,
                ino, 0, ino.length - 1,
                pos, 0, pos.length - 1, inoMap);
    }

    private static boolean isValidTree(String[] pre, int pStart, int pEnd,
            String[] ino, int iStart, int iEnd,
            String[] pos, int posStart, int posEnd,
            Map<String, Integer> inoMap) {
        if (pStart > pEnd) {
            return true;
        }

        String root = pre[pStart];
        if (!pos[posEnd].equals(root)) {
            return false;
        }

        Integer rootIndex = inoMap.get(root);
        if (rootIndex == null || rootIndex < iStart || rootIndex > iEnd) {
            return false;
        }

        int leftLen = rootIndex - iStart;

        return isValidTree(pre, pStart + 1, pStart + leftLen, ino, iStart, rootIndex - 1, pos, posStart, posStart + leftLen - 1, inoMap)
                && isValidTree(pre, pStart + leftLen + 1, pEnd, ino, rootIndex + 1, iEnd, pos, posStart + leftLen, posEnd - 1, inoMap);
    }
}
