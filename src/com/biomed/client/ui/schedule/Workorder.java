package com.biomed.client.ui.schedule;

import java.util.Date;

import com.biomed.shared.api.PropertyBag;
import com.google.gwt.i18n.shared.DateTimeFormat;


public class Workorder {
  public int id;
  public Date jobDate;
  public Date jobStart;
  public Date jobEnd;
  public int clientId;
  public String clientIdentifier;
  public String clientName;
  public String clientAddress;
  public String clientAddress2;
  public String clientCity;
  public String clientState;
  public String clientZip;
  public int techId;
  public String techName;
  public int color;

  public Workorder() { }

  public Workorder(PropertyBag bag) {
    DateTimeFormat format = DateTimeFormat.getFormat("yyyy-MM-dd");

    id = bag.getInteger("workorder_id");

    jobDate = format.parse(bag.getString("job_date"));

    int start = bag.getInteger("job_start");
    int end = bag.getInteger("job_end");

    jobStart = new Date(jobDate.getTime() + (((start / 100 * 60) + (start % 100))  * 60000));
    jobEnd = new Date(jobDate.getTime() + (((end / 100 * 60) + (end % 100)) * 60000));

    clientId = bag.getInteger("client_id");
    clientIdentifier = bag.getString("client_identification");
    clientName = bag.getString("client_name");
    clientAddress = bag.getString("client_address");
    clientAddress2 = bag.getString("client_address_2");
    clientCity = bag.getString("client_city");
    clientState = bag.getString("client_state");
    clientZip = bag.getString("client_zip");
    techId = bag.getInteger("tech_id");
    techName = bag.getString("tech_first_name") + " " + bag.getString("tech_last_name");
  }
}
