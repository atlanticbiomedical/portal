package com.biomed.shared.api;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

public class PropertyBag implements Serializable {
  private Map<String, String> values;

  public PropertyBag() {
    values = Maps.newHashMap();
  }

  public PropertyBag(Map<String, String> values) {
    this.values = values;
  }

  public void set(String key, Object value) {
    if (value != null) {
      values.put(key, value.toString());
    } else {
      values.put(key, null);
    }
  }

  public Object get(String key) {
    return values.get(key);
  }

  public String getString(String key) {
    return (String) values.get(key);
  }

  public String getString(String key, String defaultValue) {
    if (values.containsKey(key)) {
      return (String) values.get(key);
    } else {
      return defaultValue;
    }
  }

  public int getInteger(String key) {
    return Integer.parseInt(values.get(key));
  }

  public boolean containsKey(String key) {
    return values.containsKey(key);
  }

  public boolean hasValue(String key) {
    return values.containsKey(key) && values.get(key) != null;
  }
}
