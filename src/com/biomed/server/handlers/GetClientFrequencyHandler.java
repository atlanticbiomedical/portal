package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biomed.server.AbstractGetHandler;
import com.biomed.shared.api.GetClientFrequencyAction;
import com.biomed.shared.api.dto.FrequencyDTO;
import com.biomed.shared.api.dto.FrequencyType;

public class GetClientFrequencyHandler extends AbstractGetHandler<GetClientFrequencyAction, FrequencyDTO> {

  private static final String QUERY =
      "SELECT " +
        "id, " +
        "frequency, " +
        "frequency_annual, " +
        "frequency_semi, " +
        "frequency_quarterly, " +
        "frequency_sterilizer, " +
        "frequency_tg, " +
        "frequency_ert, " +
        "frequency_rae, " +
        "frequency_medgas, " +
        "frequency_imaging, " +
        "frequency_neptune, " +
        "frequency_anesthesia " +
      "FROM " + 
        "client " +
      "WHERE " +
        "id = ? ";

  @Override
  public Class<GetClientFrequencyAction> getType() {
    return GetClientFrequencyAction.class;
  }

  @Override
  protected PreparedStatement createQuery(Connection connection, GetClientFrequencyAction action)
      throws SQLException {

    String sql = QUERY;
    PreparedStatement query = connection.prepareStatement(sql);
    query.setInt(1, action.getClientId());

    return query;
  }

  @Override
  protected FrequencyDTO prepareResult(ResultSet resultSet, GetClientFrequencyAction action)
      throws SQLException {

    FrequencyDTO result = new FrequencyDTO();
    result.setFrequenciesFromString(FrequencyType.Anesthesia, resultSet.getString("frequency_anesthesia"));
    result.setFrequenciesFromString(FrequencyType.Annual, resultSet.getString("frequency_annual"));
    result.setFrequenciesFromString(FrequencyType.ERT, resultSet.getString("frequency_ert"));
    result.setFrequenciesFromString(FrequencyType.Imaging, resultSet.getString("frequency_imaging"));
    result.setFrequenciesFromString(FrequencyType.Legacy, resultSet.getString("frequency"));
    result.setFrequenciesFromString(FrequencyType.Medgas, resultSet.getString("frequency_medgas"));
    result.setFrequenciesFromString(FrequencyType.Neptune, resultSet.getString("frequency_neptune"));
    result.setFrequenciesFromString(FrequencyType.Quarterly, resultSet.getString("frequency_quarterly"));
    result.setFrequenciesFromString(FrequencyType.RoomAirExchange, resultSet.getString("frequency_rae"));
    result.setFrequenciesFromString(FrequencyType.Semi, resultSet.getString("frequency_semi"));
    result.setFrequenciesFromString(FrequencyType.Sterilizer, resultSet.getString("frequency_sterilizer"));
    result.setFrequenciesFromString(FrequencyType.TraceGas, resultSet.getString("frequency_tg"));
    return result;
  }

}
