package com.codegym.luxstay.repository;

import com.codegym.luxstay.model.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends PagingAndSortingRepository<Notification,Long> {
    @Query(value = "select * from notification where user_id=?1 ",nativeQuery = true)
    Iterable<Notification> findAllByUser(long userId);
}
