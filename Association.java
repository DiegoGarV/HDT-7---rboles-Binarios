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

    public Association(K key, V value1, V value2) {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value1;
    }

    public V getValue2() {
        return value2;
    }
}
