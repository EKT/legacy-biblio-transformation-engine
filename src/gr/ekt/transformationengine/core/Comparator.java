package gr.ekt.transformationengine.core;

import java.util.List;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public interface Comparator {
    
    public boolean compare(List<Object> valuesList, String recordValue);
    
}
