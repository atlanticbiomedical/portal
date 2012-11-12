package com.biomed.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import com.biomed.server.dispatch.ActionHandler;
import com.biomed.shared.api.PropertyBag;
import com.biomed.shared.dispatch.DispatchAction;
import com.biomed.shared.dispatch.DispatchResult;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public abstract class AbstractSqlHandler<A extends DispatchAction<R>, R extends DispatchResult>
    implements ActionHandler<A, R> {

  protected List<PropertyBag> resultSetToList(ResultSet rs) throws SQLException {
    List<PropertyBag> results = Lists.newArrayList();

    while (rs.next()) {
      results.add(resultSetToMap(rs));
    }

    return results;
  }

  protected PropertyBag resultSetToMap(ResultSet rs) throws SQLException {
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();

    PropertyBag result = new PropertyBag();
    for (int i = 1; i <= columns; i++) {
      Object value = rs.getObject(i);
      if (value instanceof String) {
        if (((String) value).isEmpty()) {
          value = null;
        }
      }
      result.set(md.getColumnLabel(i), value);
    }

    return result;
  }

  protected PreparedStatement generateInsert(Connection conn, String table, List<String> columns,
      PropertyBag values) throws SQLException {

    String query = "INSERT INTO " + table + "(" + Joiner.on(",").join(columns) + ") VALUES (";
    
    List<String> params = Lists.newArrayListWithExpectedSize(columns.size());
    for (int i = 0; i < columns.size(); i++) {
      params.add("?");
    }

    query += Joiner.on(",").join(params) + ")";

    PreparedStatement statement = conn.prepareStatement(query);

    for (int i = 0; i < columns.size(); i++) {
      statement.setObject(i + 1, values.get(columns.get(i)));
    }

    return statement;
  }

  protected PreparedStatement generateUpdate(Connection conn, String table, List<String> columns,
      List<String> where, PropertyBag values) throws SQLException {

    List<String> setValues = Lists.newArrayListWithExpectedSize(columns.size());
    for (String column : columns) {
      setValues.add(column + " = ?");
    }

    List<String> whereValues = Lists.newArrayListWithExpectedSize(where.size());
    for (String w : where) {
      whereValues.add(w + " = ?");
    }

    String query = "UPDATE " + table + " SET " + Joiner.on(",").join(setValues) + " WHERE "
        + Joiner.on(" AND ").join(whereValues);

    PreparedStatement statement = conn.prepareStatement(query);

    int index = 0;

    for (String column : columns) {
      index++;
      statement.setObject(index, values.get(column));
    }

    for (String w : where) {
      index++;
      statement.setObject(index, values.get(w));
    }

    return statement;
  }
}
