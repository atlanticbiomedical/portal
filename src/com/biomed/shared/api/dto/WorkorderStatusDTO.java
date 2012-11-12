package com.biomed.shared.api.dto;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class WorkorderStatusDTO implements Entity {
  public final static WorkorderStatusDTO COMPLETE = new WorkorderStatusDTO(7, "Complete");
  public final static WorkorderStatusDTO NEXT_VISIT_REMINDER = new WorkorderStatusDTO(6, "Next Visit Reminder");
  public final static WorkorderStatusDTO PENDING = new WorkorderStatusDTO(11, "Pending");
  public final static WorkorderStatusDTO PM_DUE = new WorkorderStatusDTO(10, "PM Due");
  public final static WorkorderStatusDTO REOPEN_AS_WARRANTY = new WorkorderStatusDTO(5, "Reopen as Warranty");
  public final static WorkorderStatusDTO REPAIR_DECLINED = new WorkorderStatusDTO(8, "Repair Declined");
  public final static WorkorderStatusDTO SCHEDULED = new WorkorderStatusDTO(9, "Scheduled");
  public final static WorkorderStatusDTO WAITING_PARTS = new WorkorderStatusDTO(3, "Waiting Parts");
  public final static WorkorderStatusDTO WAITING_QUOTE_APPROVAL = new WorkorderStatusDTO(4, "Waiting Quote Approval");
  public final static WorkorderStatusDTO WITH_PARTS = new WorkorderStatusDTO(1, "With Parts");
  public final static WorkorderStatusDTO WITHOUT_PARTS = new WorkorderStatusDTO(2, "Without Parts");

  public static final List<WorkorderStatusDTO> VALUES = ImmutableList.<WorkorderStatusDTO>builder()
      .add(COMPLETE)
      .add(NEXT_VISIT_REMINDER)
      .add(PENDING)
      .add(PM_DUE)
      .add(REOPEN_AS_WARRANTY)
      .add(REPAIR_DECLINED)
      .add(SCHEDULED)
      .add(WAITING_PARTS)
      .add(WAITING_QUOTE_APPROVAL)
      .add(WITH_PARTS)
      .add(WITHOUT_PARTS)
      .build();

  public static WorkorderStatusDTO getById(int id) {
    for (WorkorderStatusDTO dto : VALUES) {
      if (dto.getId() == id) {
        return dto;
      }
    }

    return null;
  }

  private int id;
  private String label;

  public WorkorderStatusDTO() { }

  private WorkorderStatusDTO(int id, String label) {
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
