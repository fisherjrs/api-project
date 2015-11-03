
package com.myretail.utils;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * TypeHandler for LocalDataTime.
 * Mybatis doesn't have a mapper for Java8 LocalDateTime from mysql timestamp.
 */
@MappedTypes(LocalDateTime.class)
public class DateTimeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
    	//TODO: implement this.
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnName);
        if (ts != null) {
            return LocalDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
        }
        return null;
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    	//TODO: implement this.
        return null;
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        //TODO: implement this.
        return null;
    }
}