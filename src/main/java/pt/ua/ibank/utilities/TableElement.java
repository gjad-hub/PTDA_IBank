/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.utilities;

/**
 *
 * @author ricar
 * @param <K> Key
 * @param <V> Value
 */
public class TableElement<K, V> {

    public String key;
    public String pair;

    public TableElement(String pKey, String pPair) {
        this.key = pKey;
        this.pair = pPair;
    }

}
