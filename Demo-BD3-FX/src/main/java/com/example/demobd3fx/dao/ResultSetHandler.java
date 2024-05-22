package com.example.demobd3fx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultSetHandler<T> {

    T handle(ResultSet rs) throws SQLException;

    List<T> findAll();

    T findById(int id);

}
