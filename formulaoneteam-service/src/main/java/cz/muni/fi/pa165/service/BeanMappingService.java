package cz.muni.fi.pa165.service;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BeanMappingService {
	
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

    <T> Map<T, List<String>> mapTo(Map<?, List<String>> map, Class<T> mapToClass);

    Mapper getMapper();
}
