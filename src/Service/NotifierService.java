package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifierService {

    private final Map<EventE, List<EventListener>> listeners = new HashMap<>(){{
        put(EventE.CLEAR_SPACE, new ArrayList<>());
    }};

    public void subscribe(final EventE event, EventListener listener) {
        List<EventListener> selectedListeners = listeners.get(event);
        selectedListeners.add(listener);
    }

    public void notify(final EventE event) {
        listeners.get(event).forEach(listener -> listener.update(event));
    }
}
