package shop.model.repository;

import java.sql.SQLException;

public interface tableOperations {
    //todo
    void createTable() throws SQLException; // создание таблицы
    void createForeignKeys() throws SQLException; // создание связей между таблицами
    void createExtraConstraints() throws SQLException; // создание дополнительных правил для значений полей таблиц
}
