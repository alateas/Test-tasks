
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

import java.io.File;

public class DbWorker {
    private final String DB_NAME = "sqllite_db";
    private final String TABLE_NAME = "data";
    private SqlJetDb db;
    private ISqlJetTable table;

    public DbWorker() {
        try {
            createDb();
            createTable();
        } catch (SqlJetException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createDb() throws SqlJetException {
        File dbFile = new File(DB_NAME);
        dbFile.delete();
        db = SqlJetDb.open(dbFile, true);
        db.getOptions().setAutovacuum(true);
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            db.getOptions().setUserVersion(1);
        } finally {
            db.commit();
        }
    }

    private void createTable() throws SqlJetException {
        String createTableQuery = "CREATE TABLE data (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , num INTEGER NOT NULL, string TEXT NOT NULL)";
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            db.createTable(createTableQuery);
            table = db.getTable(TABLE_NAME);
        } finally {
            db.commit();
        }
    }

    public void insertRecord(int num, String string) {
        try {
            db.beginTransaction(SqlJetTransactionMode.WRITE);
            try {
                table.insert(null, num, string);
            } finally {
                db.commit();
            }
        } catch (SqlJetException e) {
            System.out.println(e.getMessage());
        }

    }

    public void printRecords() {
        try {
            db.beginTransaction(SqlJetTransactionMode.WRITE);
            ISqlJetCursor cursor = table.open();
            try {
                if (!cursor.eof()) {
                    do {
                        System.out.println(cursor.getRowId() + " : " +
                                cursor.getInteger("num") + " " +
                                cursor.getString("string"));
                    } while (cursor.next());
                }
            } finally {
                cursor.close();
                db.commit();
            }
        } catch (SqlJetException e) {
            System.out.println(e.getMessage());
        }

    }

}
