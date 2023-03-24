/*
* Autores:
* Diego García 22404
* Mónica Salvatierra 22249 
* Fecha: 19/03/2023
* Hoja de Trabajo #7
* Binary Search Tree
*/

import java.util.*;

public class BinarySearchTree<K extends Comparable<K>, V> {

    // Clase tomada como referencia de
    // https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
    // Fue modificada según las necesidades del programa

    private Node root;

    /**

    Esta clase representa un nodo en el árbol binario de búsqueda.
    Cada nodo contiene una clave y un valor asociado a esa clave.
    */  

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        /**

    Constructor de la clase Node.
    @param key la clave asociada a este nodo
    @param value el valor asociado a la clave en este nodo
    */


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**

    Agrega un par clave-valor al árbol binario de búsqueda. Si la clave ya existe en el árbol,
    se actualiza el valor asociado a esa clave.
    @param key la clave del par clave-valor
    @param value el valor asociado a la clave
    */

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        return node;
    }

    /**
    Obtiene el valor asociado a una clave en el árbol binario de búsqueda.
    @param key la clave cuyo valor se desea obtener
    @return el valor asociado a la clave, o null si la clave no existe en el árbol
    */

    public V get(K key) {
        Node node = get(root, key);
        return (node == null) ? null : node.value;
    }

    private Node get(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    /**
    Recorre el árbol en orden ascendente y aplica una acción a cada elemento.
    @param action la acción que se aplica a cada elemento del árbol
    */

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.key);
            inOrderTraversal(node.right);
        }
    }

    /** 
    Busca un valor asociado a una clave específica en el árbol.
    @param key La clave de la asociación buscada.
    @return El valor asociado a la clave, si se encuentra en el árbol. Si la clave no está en el árbol, retorna null.
    */

    public V search(K key) {
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                return current.value;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    /**
    Retorna un iterable con todas las claves del árbol en orden ascendente.
    @return Un iterable con todas las claves del árbol en orden ascendente.
    */

    public Iterable<K> keys() {
        List<K> keys = new ArrayList<>();
        keys(root, keys);
        return keys;
    }

    /**
    Método auxiliar que agrega todas las claves del árbol en orden ascendente a la lista de claves dada.
    @param node El nodo actual.
    @param keys La lista en la que se van a agregar las claves.
    */

    private void keys(Node node, List<K> keys) {
        if (node != null) {
            keys(node.left, keys);
            keys.add(node.key);
            keys(node.right, keys);
        }
    }

}