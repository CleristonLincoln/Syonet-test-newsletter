package br.com.syonet.newsletter.utils;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseCleaner {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String PATH_SQL = "/db/testData/afterMigration.sql";

    @Autowired
    private DataSource dataSource;

    private Connection connection;


    public void clearTables() {
        try (Connection connection = dataSource.getConnection()) {
            this.connection = connection;

            checkTestDatabase();
            tryToClearTables();
            insertDataBase();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.connection = null;
        }
    }

    private void insertDataBase() {
        try (
                InputStream inFromClass = this.getClass().getResourceAsStream(PATH_SQL);
                BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inFromClass)))
        ) {

            List<String> inserts = new ArrayList<>();

            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) inserts.add(line);
            }

            try (Statement statement = connection.createStatement()) {

                connection.setAutoCommit(false);

                String replaceSQL = "";

                for (String sql : inserts) {

                    if (sql.endsWith(";") && replaceSQL.isEmpty()) {
                        replaceSQL = sql;

                    } else {
                        replaceSQL = replaceSQL + " " + sql;
                    }

                    if (replaceSQL.endsWith(";")) {
                        //   System.out.println(replaceSQL);
                        statement.addBatch(replaceSQL);
                        replaceSQL = "";
                    }
                }

                statement.executeBatch();

                connection.commit();

            } catch (SQLException e) {
                throw new RuntimeException("Erro ao executar os comandos SQL.", e);
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo SQL.", e);
        }
    }


    private void checkTestDatabase() throws SQLException {
        String catalog = connection.getCatalog();

        if (catalog == null || !catalog.endsWith("test")) {
            throw new RuntimeException(
                    "Cannot clear database tables because '" + catalog + "' is not a test database (suffix 'test' not found).");
        }
    }

    private void tryToClearTables() throws SQLException {
        List<String> tableNames = getTableNames();
        clear(tableNames);
    }

    private List<String> getTableNames() throws SQLException {
        List<String> tableNames = new ArrayList<>();

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs = metaData.getTables(connection.getCatalog(), null, null, new String[]{"TABLE"});

        while (rs.next()) {
            tableNames.add(rs.getString("TABLE_NAME"));
        }

        tableNames.remove("flyway_schema_history");

        return tableNames;
    }

    private void clear(List<String> tableNames) throws SQLException {
        Statement statement = buildSqlStatement(tableNames);

        logger.debug("Executing SQL");
        statement.executeBatch();
    }

    private Statement buildSqlStatement(List<String> tableNames) throws SQLException {
        Statement statement = connection.createStatement();

        statement.addBatch(sql("SET FOREIGN_KEY_CHECKS = 0"));
        addTruncateSatements(tableNames, statement);
        statement.addBatch(sql("SET FOREIGN_KEY_CHECKS = 1"));

        return statement;
    }

    private void addTruncateSatements(List<String> tableNames, Statement statement) {
        tableNames.forEach(tableName -> {
            try {
                statement.addBatch(sql("TRUNCATE TABLE " + tableName));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String sql(String sql) {
        logger.debug("Adding SQL: {}", sql);
        return sql;
    }
}