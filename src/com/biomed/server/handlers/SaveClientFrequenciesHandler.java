package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.biomed.server.AbstractMutateHandler;
import com.biomed.shared.api.SaveClientFrequenciesAction;
import com.biomed.shared.api.dto.FrequencyType;

public class SaveClientFrequenciesHandler extends
    AbstractMutateHandler<SaveClientFrequenciesAction> {

  private static final String QUERY =
      "UPDATE client SET " + 
          "frequency = ?, " +
          "frequency_annual = ?, " +
          "frequency_semi = ?, " +
          "frequency_quarterly = ?, " +
          "frequency_sterilizer = ?, " +
          "frequency_tg = ?, " +
          "frequency_ert = ?, " +
          "frequency_rae = ?, " +
          "frequency_medgas = ?, " +
          "frequency_imaging = ?, " +
          "frequency_neptune = ?, " +
          "frequency_anesthesia = ? " +
      "WHERE id = ? LIMIT 1";

  @Override
  public Class<SaveClientFrequenciesAction> getType() {
    return SaveClientFrequenciesAction.class;
  }

  @Override
  protected PreparedStatement createQuery(Connection connection, SaveClientFrequenciesAction action)
      throws SQLException {

    String sql = QUERY;
    PreparedStatement query = connection.prepareStatement(sql);
    query.setString(1, action.getFrequencies().getFrequenciesAsString(FrequencyType.Legacy));
    query.setString(2, action.getFrequencies().getFrequenciesAsString(FrequencyType.Annual));
    query.setString(3, action.getFrequencies().getFrequenciesAsString(FrequencyType.Semi));
    query.setString(4, action.getFrequencies().getFrequenciesAsString(FrequencyType.Quarterly));
    query.setString(5, action.getFrequencies().getFrequenciesAsString(FrequencyType.Sterilizer));
    query.setString(6, action.getFrequencies().getFrequenciesAsString(FrequencyType.TraceGas));
    query.setString(7, action.getFrequencies().getFrequenciesAsString(FrequencyType.ERT));
    query.setString(8, action.getFrequencies().getFrequenciesAsString(FrequencyType.RoomAirExchange));
    query.setString(9, action.getFrequencies().getFrequenciesAsString(FrequencyType.Medgas));
    query.setString(10, action.getFrequencies().getFrequenciesAsString(FrequencyType.Imaging));
    query.setString(11, action.getFrequencies().getFrequenciesAsString(FrequencyType.Neptune));
    query.setString(12, action.getFrequencies().getFrequenciesAsString(FrequencyType.Anesthesia));
    query.setInt(13, action.getClientId());

    return query;
  }
}
