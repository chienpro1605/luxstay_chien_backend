package com.codegym.luxstay.repository;

import com.codegym.luxstay.model.Apartment;
import com.codegym.luxstay.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Repository
public interface IApartmentRepository extends JpaRepository<Apartment, Long> {
    Iterable<Apartment> findAllByNameContaining(String name);
    Iterable<Apartment> findAllByStatusContaining(String status);
    Iterable<Apartment> findAllByPriceContaining(String price);
    Iterable<Apartment> findAllByApartmentTypeContaining(String type);
    Iterable<Apartment> findAllByDescriptionContaining(String description);

    @Query(value = "select * from apartment order by apartment.id desc limit 8", nativeQuery = true)
    Iterable<Apartment> find8Newest();


    @Modifying
    @Query(value = "Select *,count(o.apartment_id) from Apartment a left join Orders o on o.apartment_id = a.id group by o.apartment_id order by count(o.apartment_id) desc limit 5", nativeQuery = true)
    Iterable<Apartment> findTop5();

    @Query(value = "select * from Apartment a where a.status like 'not available'", nativeQuery = true)
    Iterable<Apartment> findApartmentNotAvailable();

    @Query(value = "select * from Apartment a where a.status like 'available'", nativeQuery = true)
    Iterable<Apartment> findApartmentAvailable();

    @Query(value = "select * from Apartment a left join Orders  o on o.apartment_id = a.id group by o.apartment_id having (a.name like :name or a.district like :name or a.ward like :name) and (:startDate not between o.start_date and o.end_date)", nativeQuery = true)
    Iterable<Apartment> searchMany1(@Param("name") String name, @Param("startDate") Date startDate);

    @Query(value = "select * from Apartment a left join Orders  o on o.apartment_id = a.id group by :startDate not between o.start_date and o.end_date", nativeQuery = true)
    Iterable<Apartment> searchManyDate(@Param("startDate") Date startDate);

    @Query(value = "select * from Apartment a where a.name like :name or a.ward like :name or a.district like :name or a.city like :name ", nativeQuery = true)
    Iterable<Apartment> searchByString(@Param("name") String name);


    @Modifying
    @Query(value = "select * from apartment where apartment.user_id = ?1", nativeQuery = true)
    Iterable<Apartment> findAllByUserId(long id);

    Iterable<Apartment> findAllByAddressContaining(@NotBlank @Size(min = 3) String address);
    Iterable<Apartment> findAllByCityContaining(@NotBlank String city);
    Iterable<Apartment> findAllByCityContainingAndDistrictContainingAndWardContaining(
            @NotBlank String city, @NotBlank String district, @NotBlank String ward);
    @Query(value = "select * from Apartment a where a.price between :price1 and :price2", nativeQuery = true)
    Iterable<Apartment> findbyPrice(@Param("price1") Double price1, @Param("price2") Double price2);

    @Query(value = "select * from Apartment a where (:value is null or a.name like %:value% or a.city like %:value% or a.district like %:value% or a.ward like %:value% ) and (:typeID = 0 or a.apartment_type_id = :typeID) and ((:price1 = 0 and :price2 = 0) or (a.price between :price1 and :price2)) group by a.id", nativeQuery = true)
        Iterable<Apartment> findByAll(@Param("value") String value,@Param("typeID") Long typeID, @Param("price1") Double price1, @Param("price2") Double price2 );
}
