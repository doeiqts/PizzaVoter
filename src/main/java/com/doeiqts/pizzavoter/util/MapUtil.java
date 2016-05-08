package com.doeiqts.pizzavoter.util;

import java.util.*;

/** Resort a map based on value
 *
 * @Param Map\<K,V\> a Map where V is naturally Comparable(or comparable is implemented within the class)
 * @Param boolean descending - tells whether to sort descending or ascending.
 * @return Map with entry sets ordered by value
 */
public class MapUtil {
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, final boolean descending )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                //sort descending
                if(descending)
                    return ( o2.getValue() ).compareTo( o1.getValue() );
                else
                    return ( o1.getValue() ).compareTo( o2.getValue() );
            }
        } );
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }

}
