package com.biomed.shared.api.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class FrequencyDTO implements Entity {
  private int clientId;
  private HashMap<FrequencyType, HashSet<Month>> frequencies;

  public FrequencyDTO() {
    frequencies = Maps.newHashMap();
    for (FrequencyType type : FrequencyType.values()) {
      frequencies.put(type, new HashSet<Month>());
    }
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public void setFrequency(FrequencyType type, Month month, boolean value) {
    if (value) {
      frequencies.get(type).add(month);
    } else {
      frequencies.get(type).remove(month);
    }
  }

  public boolean getFrequency(FrequencyType type, Month month) {
    return frequencies.get(type).contains(month);
  }

  public void setFrequenciesFromString(FrequencyType type, String months) {
    if (months != null) {
      setFrequency(type, Month.January, months.contains("JAN"));
      setFrequency(type, Month.February, months.contains("FEB"));
      setFrequency(type, Month.March, months.contains("MAR"));
      setFrequency(type, Month.April, months.contains("APR"));
      setFrequency(type, Month.May, months.contains("MAY"));
      setFrequency(type, Month.June, months.contains("JUN"));
      setFrequency(type, Month.July, months.contains("JUL"));
      setFrequency(type, Month.August, months.contains("AUG"));
      setFrequency(type, Month.September, months.contains("SEP"));
      setFrequency(type, Month.October, months.contains("OCT"));
      setFrequency(type, Month.November, months.contains("NOV"));
      setFrequency(type, Month.December, months.contains("DEC"));
    }
  }

  public String getFrequenciesAsString(FrequencyType type) {
    List<String> set = Lists.newArrayList();
    if (getFrequency(type, Month.January)) {
      set.add("JAN");
    }
    if (getFrequency(type, Month.February)) {
      set.add("FEB");
    }
    if (getFrequency(type, Month.March)) {
      set.add("MAR");
    }
    if (getFrequency(type, Month.April)) {
      set.add("APR");
    }
    if (getFrequency(type, Month.May)) {
      set.add("MAY");
    }
    if (getFrequency(type, Month.June)) {
      set.add("JUN");
    }
    if (getFrequency(type, Month.July)) {
      set.add("JUL");
    }
    if (getFrequency(type, Month.August)) {
      set.add("AUG");
    }
    if (getFrequency(type, Month.September)) {
      set.add("SEP");
    }
    if (getFrequency(type, Month.October)) {
      set.add("OCT");
    }
    if (getFrequency(type, Month.November)) {
      set.add("NOV");
    }
    if (getFrequency(type, Month.December)) {
      set.add("DEC");
    }

    return Joiner.on(",").join(set);
  }
  /*
   * id, frequency, frequency_annual, frequency_semi, frequency_quarterly,
   * frequency_sterilizer, frequency_tg, frequency_ert, frequency_rae,
   * frequency_mdgas, frequency_imaging, frequency_neptune, frequency_anesthesia
   */
}
