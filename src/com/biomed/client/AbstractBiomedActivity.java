package com.biomed.client;

import com.biomed.client.place.BiomedPlace;

public abstract class AbstractBiomedActivity implements BiomedActivity {

  protected BiomedPlace place;

  @Override
  public String mayStop() {
    return null;
  }

  @Override
  public void onCancel() {
  }

  @Override
  public void onStop() {
  }

  @Override
  public void setPlace(BiomedPlace place) {
    this.place = place;
  }
}
