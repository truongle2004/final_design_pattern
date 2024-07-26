package observer;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Publisher {

    private List<Subscriber> subscribers = new LinkedList<>();

    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

    public void notifySubscribers() throws SQLException {
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }
}
