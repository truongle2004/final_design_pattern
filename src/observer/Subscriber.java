package observer;

import java.sql.SQLException;

public interface Subscriber {
    public void update() throws SQLException;
}
