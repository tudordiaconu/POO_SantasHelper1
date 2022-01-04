package data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ReadWrite {
    private Database database;

    public ReadWrite(Database database) {
        this.database = database;
    }

    public void readAllData(String file) throws IOException {
        database = new ObjectMapper().readerFor(Database.class).readValue(new File(file));
    }

    public void writeAllData() {
        ObjectMapper om = new ObjectMapper();
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
