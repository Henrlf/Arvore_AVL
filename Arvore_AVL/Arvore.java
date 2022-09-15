
public class Arvore {
    public No root = null;

    public Arvore() {
        root = null;
    }

    // Retorna a altura da árvore
    private static int height(No t) {
        return t == null ? -1 : t.height;
    }

    // Retorna o maior valor
    private static int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

    // Retorna o fator de balanceamento
    private int getFactor(No t) {
        return height(t.left) - height(t.right);
    }

    // Insercao
    public boolean insert(int x) {
        root = insert(x, root);
        return true;
    }

    private No insert(int x, No t) {
        if (t == null) {
            t = new No(x, null, null);
        } else if (x == t.key)
            System.out.println("Chave já presente!");
        else if (x < t.key)
            t.left = insert(x, t.left);
        else if (x > t.key)
            t.right = insert(x, t.right);
        t = balance(t);
        return t;
    }

    // Remocao
    public void remove(int x) {
        root = remove(x, root);
    }

    private No remove(int x, No t) {
        if (t == null) {
            System.out.println("Chave não encontrada para remoção!");
            return null;
        }
        if (x < t.key) {
            t.left = remove(x, t.left);
            return balance(t);
        } else if (x > t.key) {
            t.right = remove(x, t.right);
            return balance(t);
        } else {
            if (t.left == null && t.right == null) {
                return null;
            }
            if (t.left == null) {
                return t.right;
            }
            if (t.right == null) {
                return t.left;
            } else if (t.left != null && t.right != null) {
                t.key = minDir(t.right).key;
                t.right = remove(t.key, t.right);
            } else
                t = (t.left != null) ? t.left : t.right;
            return balance(t);
        }
    }

    // Metodo auxiliar para o metodo da remocao.
    protected No minDir(No t) {
        if (t.left == null) {
            return t;
        } else {
            t = t.left;
            return minDir(t);
        }
    }

    public No balance(No t) {
        if (getFactor(t) == 2) {
            if (getFactor(t.left) > 0) {
                System.out.println("\nRotação para esquerda.");
                t = doRightRotation(t);
            } else {
                System.out.println("\nRotação dupla para esquerda.");
                t = doDoubleRightRotation(t);
            }
        } else if (getFactor(t) == -2) {
            if (getFactor(t.right) < 0) {
                System.out.println("\nRotação para direita.");
                t = doLeftRotation(t);
            } else {
                System.out.println("\nRotação dupla para direita.");
                t = doDoubleLeftRotation(t);
            }
        }
        t.height = max(height(t.left), height(t.right)) + 1;
        return t;
    }

    // Rotação simples a direita
    private static No doRightRotation(No k2) {
        No k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }

    // Rotação simples à esquerda
    private static No doLeftRotation(No k1) {
        No k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;
        return k2;
    }

    // Rotação dupla à direita
    private static No doDoubleRightRotation(No k3) {
        k3.left = doLeftRotation(k3.left);
        return doRightRotation(k3);
    }

    // Rotação dupla à esquerda
    private static No doDoubleLeftRotation(No k1) {
        k1.right = doRightRotation(k1.right);
        return doLeftRotation(k1);
    }

    protected No searchFather(int el) {
        No p = root;
        No prev = null;
        while (p != null && !(p.key == el)) { // acha o nó p com a chave el
            prev = p;
            if (p.key < el)
                p = p.right;
            else
                p = p.left;
        }
        if (p != null && p.key == el)
            return prev;
        return null;
    }

    public void displayTree() {
        if (root == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        String separator = String.valueOf("  |__");
        System.out.println(this.root.key);
        displaySubTree(root.left, separator);
        displaySubTree(root.right, separator);
    }

    private void displaySubTree(No node, String separator) {
        if (node != null) {
            No father = this.searchFather(node.key);
            if (node.equals(father.left) == true) {
                System.out.println(separator + node.key + " (ESQ)");
            } else {
                System.out.println(separator + node.key + " (DIR)");
            }
            displaySubTree(node.left, "     " + separator);
            displaySubTree(node.right, "     " + separator);
        }
    }
}
