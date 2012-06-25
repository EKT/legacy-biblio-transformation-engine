/*
 * Comparator.java
 *
 * Created on October 1, 2007, 2:29 PM
 *
 */

package gr.ekt.transformationengine.core;

import java.util.List;

public interface Comparator {
    
    public boolean compare(List<Object> valuesList, String recordValue);
    
}
