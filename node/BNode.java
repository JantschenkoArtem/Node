package org.example.node.node;

public class BNode {
    public static void main(String[] args) {
        Integer[] digit = {2, 5, 13, 6, 10, 14};
        BNode bNode = new BNode();
        createNote(bNode, 9);
        preOrderTraversal(bNode);
    }

    public Integer value;
    public BNode left;
    public BNode right;

    private static boolean isNodeExist(BNode bNode) {
        return bNode != null && bNode.value != null;
    }

    private static void createNote(BNode bNode, int value) {
        bNode.left = new BNode();
        bNode.right = new BNode();
        bNode.value = value;
    }

    /**
     * В бинарном дереве поиска,
     * если значение меньше значения корневого узла,
     * то новый узел должен быть вставлен в левое поддерево.
     * Если значение больше, то новый узел должен быть вставлен в правое поддерево.
     */
    private static void insert(BNode bNode, int value) {
        if (!isNodeExist(bNode)) {
            createNote(bNode, value);
        } else if (value < bNode.value) {
            insert(bNode.left, value);
        } else {
            insert(bNode.right, value);
        }
    }

    /**
     * В бинарном дереве поиска, если значение меньше значения корневого узла,
     * то поиск продолжается в левом поддереве. Если значение больше, то поиск продолжается в правом поддереве.
     */
    private static BNode search(BNode bNode, int value) {
        if (!isNodeExist(bNode)) {
            return null;
        }
        if (bNode.value == value) {
            return bNode;
        }
        if (value < bNode.value) {
            return search(bNode.left, value);
        }
        return search(bNode.right, value);
    }


    /**
     * В обходе “In-Order” (прямом порядке)
     * сначала обрабатывается левое поддерево,
     * затем корневой узел, а затем правое поддерево
     */
    private static void inOrderTraversal(BNode bNode) {
        if (!isNodeExist(bNode)) {
            return;
        }
        inOrderTraversal(bNode.left);
        System.out.println(bNode.value);
        inOrderTraversal(bNode.right);
    }

    /**
     * В обходе “Post-Order” (обратном порядке) сначала обрабатывается левое поддерево,
     * затем правое поддерево, и в конце обрабатывается корневой узел.
     */
    private static void postOrderTraversal(BNode bNode) {
        if (!isNodeExist(bNode)) {
            return;
        }
        postOrderTraversal(bNode.left);
        postOrderTraversal(bNode.right);
        System.out.println(bNode.value);
    }


    /**
     * В обходе “Pre-Order” (прямом порядке) сначала обрабатывается корневой узел,
     * затем левое поддерево, и в конце обрабатывается правое поддерево.
     */

    private static void preOrderTraversal(BNode bNode) {
        if (!isNodeExist(bNode)) {
            return;
        }
        System.out.println(bNode.value);
        preOrderTraversal(bNode.left);
        preOrderTraversal(bNode.right);
    }

    /**
     * Этот метод предназначен
     * для получения количества  подузлов данного узла bNode
     * в бинарном дереве.
     */
    private static int getChildCount(BNode bNode) {
        int count = 0;
        if (!isNodeExist(bNode.left)) {
            count++;
        }
        if (!isNodeExist(bNode.right)) {
            count++;
        }
        return count;
    }

    /**
     * В бинарном дереве поиска узел
     * с минимальным значением всегда находится в самом левом узле дерева.
     */
    private static BNode getMin(BNode bNode) {
        if (!isNodeExist(bNode)) {
            bNode = bNode.left;
        }
        return bNode;
    }

    /**
     * В бинарном дереве поиска узел с
     * максимальным значением всегда находится в самом правом узле дерева.
     */
    private static BNode getMax(BNode bNode) {
        if (!isNodeExist(bNode.right)) {
            bNode = bNode.right;
        }
        return bNode;
    }


    private static void removeNodeWithOneOrZeroChild(BNode BNodeToDelete) {
        if (!isNodeExist(BNodeToDelete)) {
            return;
        }
        if (isNodeExist(BNodeToDelete.right) &&
                !isNodeExist(BNodeToDelete.left)) {
            // Если у узла есть только правый потомок,
            // заменяем удаляемый узел его правым потомком
            BNodeToDelete = BNodeToDelete.right;
        } else if (isNodeExist(BNodeToDelete.left) &&
                !isNodeExist(BNodeToDelete.right)) {
            // Если у узла есть только левый потомок,
            // заменяем удаляемый узел его левым потомком
            BNodeToDelete = BNodeToDelete.left;
        } else {
            // Если у узла нет потомков, просто удаляем его
            BNodeToDelete = null;
        }
/**
 * private static void removeNodeWithTwoChildren(BNode BNodeToDelete) {
 *     // Найти преемника (узел с наименьшим значением в правом поддереве)
 *     BNode successor = getMin(BNodeToDelete.right);
 *
 *     // Копировать данные преемника в удаляемый узел
 *     BNodeToDelete.data = successor.data;
 *
 *     // Удалить преемника
 *     removeNodeWithOneOrZeroChild(successor);
 * }
 *
 * private static BNode getMin(BNode bNode) {
 *     while (bNode.left != null) {
 *         bNode = bNode.left;
 *     }
 *     return bNode;
 * }
 */
    }


    private static boolean remove(BNode root, int value) {
/**
 * private static BNode remove(BNode root, int value) {
 *     if (root == null) {
 *         return null;
 *     }
 *
 *     if (value < root.data) {
 *         root.left = remove(root.left, value);
 *     } else if (value > root.data) {
 *         root.right = remove(root.right, value);
 *     } else {
 *         // Узел найден, выполняем удаление
 *         if (root.left == null && root.right == null) {
 *             // У узла нет потомков, просто удаляем его
 *             root = null;
 *         } else if (root.right != null) {
 *             // У узла есть только правый потомок или оба потомка, заменяем удаляемый узел на его преемника
 *             BNode minNodeForRight = getMin(root.right);
 *             root.data = minNodeForRight.data;
 *             // Удаляем преемника из правого поддерева
 *             root.right = remove(root.right, minNodeForRight.data);
 *         } else {
 *             // У узла есть только левый потомок, заменяем удаляемый узел на его левого потомка
 *             root = root.left;
 *         }
 *     }
 *
 *     return root;
 * }
 *
 * private static BNode getMin(BNode root) {
 *     if (root.left != null) {
 *         return getMin(root.left);
 *     }
 *     return root;
 * }
 */
        return true;
    }
}
