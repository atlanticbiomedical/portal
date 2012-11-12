package com.biomed.shared.dispatch;

import java.io.Serializable;

public class ActionException extends Exception implements Serializable {
  public ActionException() {
  }

  public ActionException(String message) {
    super(message);
  }

  public ActionException(Throwable cause) {
    super(cause.getMessage(), cause);
  }

  public ActionException(String message, Throwable cause) {
    super(message, cause);
  }
}

