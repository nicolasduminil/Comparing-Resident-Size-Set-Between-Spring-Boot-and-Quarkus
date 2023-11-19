package fr.simplex_software.rss.metrics.spring.aspect;

import org.apache.commons.lang3.ArrayUtils;

public record RestMappingAnnotation (
        String type,
        String[] value,
        String[] path){
    @Override
    public String toString() {
        return  type + " "+ getValueOrPath();
    }

    private String getValueOrPath() {
        if (ArrayUtils.isEmpty(value) && ArrayUtils.isEmpty(path))
            return "N/A";
        if (ArrayUtils.isEmpty(value))
            return ArrayUtils.isEmpty(path) ? "" : path[0];
        // if both value and path are not null, but have different values a AnnotationConfigurationException will be thrown
        return value.length == 0 ? "" : value[0];
    }
}
