package org.spacebattle.domain.objects;

public interface UObject {
    Object getProperty(String key);
    void setProperty(String key, Object newValue);
}

