package com.ms.jansing.common.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.LocalDate;

/**
 * Created by jansing on 17-7-22.
 */
@MappedTypes({LocalDate.class})
@MappedJdbcTypes({JdbcType.DATE})
public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {
    public LocalDateTypeHandler() {
    }

    public void setNonNullParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType) throws SQLException {
        ps.setDate(i, Date.valueOf(parameter));
    }

    public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.transfer(rs.getDate(columnName));
    }

    public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.transfer(rs.getDate(columnIndex));
    }

    public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.transfer(cs.getDate(columnIndex));
    }

    private LocalDate transfer(Date date) {
        return date == null ? null : date.toLocalDate();
    }
}