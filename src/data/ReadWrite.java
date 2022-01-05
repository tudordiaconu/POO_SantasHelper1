package data;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ReadWrite {
    private Database database;
    private WriteDatabase writeDatabase;

    public WriteDatabase getWriteDatabase() {
        return writeDatabase;
    }

    public void setWriteDatabase(WriteDatabase writeDatabase) {
        this.writeDatabase = writeDatabase;
    }

    public ReadWrite(Database database, WriteDatabase writeDatabase) {
        this.database = database;
        this.writeDatabase = writeDatabase;
    }

    public void readAllData(String file) throws IOException {
        database = new ObjectMapper().readerFor(Database.class).readValue(new File(file));
    }

    public void writeAllData(String file) throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.writerWithDefaultPrettyPrinter().writeValue(new File(file), writeDatabase);
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
