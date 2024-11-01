    package br.com.compasso.notifyapi.repository;

    import br.com.compasso.notifyapi.entity.Notification;
    import org.springframework.data.mongodb.repository.MongoRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface NotificationRepository extends MongoRepository<Notification, String> {
    }
