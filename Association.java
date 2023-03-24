
/*
* Autores:
* Diego García 22404
* Mónica Salvatierra 22249 
* Fecha: 19/03/2023
* Hoja de Trabajo #7
* Association
*/

public class Association<K, V> {
    private K key;
    private V value1;
    private V value2;

    /**
     * Constructor que crea una nueva asociación con la clave y los valores especificados.
     * @param key la clave de la asociación
     * @param value1 el primer valor de la asociación
     * @param value2 el segundo valor de la asociación
     */

    public Association(K key, V value1, V value2) {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Devuelve la clave de la asociación.
     * @return la clave de la asociación
     */

    public K getKey() {
        return key;
    }

    /**
     * Devuelve el primer valor de la asociación.
     * @return el primer valor de la asociación
     */

    public V getValue() {
        return value1;
    }

    /**
     * Devuelve el segundo valor de la asociación.
     * @return el segundo valor de la asociación
     */
    
    public V getValue2() {
        return value2;
    }
}

