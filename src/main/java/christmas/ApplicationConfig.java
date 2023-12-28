package christmas;

import christmas.repository.EventRepository;
import christmas.service.EventService;

public class ApplicationConfig {

    public static EventService eventService() {
        return new EventService(eventRepository());
    }

    public static EventRepository eventRepository() {
        return new EventRepository();
    }
}
