package com.biomed.client.resources;

import java.util.ArrayList;
import java.util.List;

public class BiomedAppointmentTheme {
  // border, background

  public static final BiomedAppointmentStyle DARK_GRAY = new BiomedAppointmentStyle("#979797", "#C2C2C2");
  public static final BiomedAppointmentStyle BOLD_BLUE = new BiomedAppointmentStyle("#979797", "#5484ED");
  public static final BiomedAppointmentStyle BLUE = new BiomedAppointmentStyle("#979797", "#A4BDFC");
  public static final BiomedAppointmentStyle TURQUOISE = new BiomedAppointmentStyle("#979797", "#46D6DB");
  public static final BiomedAppointmentStyle GREEN = new BiomedAppointmentStyle("#979797", "#7AE7BF");
  public static final BiomedAppointmentStyle BOLD_GREEN = new BiomedAppointmentStyle("#979797", "#51B749");
  public static final BiomedAppointmentStyle YELLOW = new BiomedAppointmentStyle("#979797", "#FBD75B");
  public static final BiomedAppointmentStyle ORANGE = new BiomedAppointmentStyle("#979797", "#FFB878");
  public static final BiomedAppointmentStyle RED = new BiomedAppointmentStyle("#979797", "#FF887C");
  public static final BiomedAppointmentStyle BOLD_RED = new BiomedAppointmentStyle("#979797", "#DC2127");
  public static final BiomedAppointmentStyle PURPLE = new BiomedAppointmentStyle("#979797", "#DBADFF");
  public static final BiomedAppointmentStyle GRAY = new BiomedAppointmentStyle("#979797", "#E1E1E1");

  public static final List<BiomedAppointmentStyle> STYLES = new ArrayList<BiomedAppointmentStyle>();

  static {
    STYLES.add(DARK_GRAY);
    STYLES.add(BOLD_BLUE);
    STYLES.add(BLUE);
    STYLES.add(TURQUOISE);
    STYLES.add(GREEN);
    STYLES.add(BOLD_GREEN);
    STYLES.add(YELLOW);
    STYLES.add(ORANGE);
    STYLES.add(RED);
    STYLES.add(BOLD_RED);
    STYLES.add(PURPLE);
    STYLES.add(GRAY);

  }

  private BiomedAppointmentTheme() {
  }
}
