package com.biomed.shared.api.dto;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class WorkorderReasonDTO implements Entity {
  public static final WorkorderReasonDTO ADD_NEW_EQUIPMENT = new WorkorderReasonDTO(18, "Add New Equipment");
  public static final WorkorderReasonDTO AS_DIRECTED = new WorkorderReasonDTO(24, "As Directed");
  public static final WorkorderReasonDTO ALLAUTOCLAVE_REPAIR = new WorkorderReasonDTO(7, "Autoclave Repair");
  public static final WorkorderReasonDTO CALIBRATION = new WorkorderReasonDTO(21, "Calibration");
  public static final WorkorderReasonDTO DELIVERY = new WorkorderReasonDTO(9, "Delivery");
  public static final WorkorderReasonDTO DIAGNOSE_PROBLEM = new WorkorderReasonDTO(10, "Diagnose Problem");
  public static final WorkorderReasonDTO INSTALL_PARTS = new WorkorderReasonDTO(19, "Install Parts");
  public static final WorkorderReasonDTO OFF = new WorkorderReasonDTO(22, "Off");
  public static final WorkorderReasonDTO PM_RESCHEDULE = new WorkorderReasonDTO(23, "PM Reschedule");
  public static final WorkorderReasonDTO PREVENTIVE_MAINTENANCE = new WorkorderReasonDTO(17, "Preventive Maintenance");
  public static final WorkorderReasonDTO PRINTER_FAILURE = new WorkorderReasonDTO(6, "Printer Failure");
  public static final WorkorderReasonDTO REPAIR = new WorkorderReasonDTO(20, "Repair");

  public static final List<WorkorderReasonDTO> VALUES = ImmutableList.<WorkorderReasonDTO>builder()
      .add(ADD_NEW_EQUIPMENT)
      .add(AS_DIRECTED)
      .add(ALLAUTOCLAVE_REPAIR)
      .add(CALIBRATION)
      .add(DELIVERY)
      .add(DIAGNOSE_PROBLEM)
      .add(INSTALL_PARTS)
      .add(OFF)
      .add(PM_RESCHEDULE)
      .add(PREVENTIVE_MAINTENANCE)
      .add(PRINTER_FAILURE)
      .add(REPAIR)
      .build();

  public static WorkorderReasonDTO getById(int id) {
    for (WorkorderReasonDTO dto : VALUES) {
      if (dto.getId() == id) {
        return dto;
      }
    }

    return null;
  }

  private int id;
  private String label;

  public WorkorderReasonDTO() { }

  private WorkorderReasonDTO(int id, String label) {
    this.id = id;
    this.label = label;
  }

  public int getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }
}