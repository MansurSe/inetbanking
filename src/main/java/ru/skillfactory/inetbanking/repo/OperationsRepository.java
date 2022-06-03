package ru.skillfactory.inetbanking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skillfactory.inetbanking.entity.Operations;
import ru.skillfactory.inetbanking.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OperationsRepository extends JpaRepository<Operations, Long> {

    @Query("select o from Operations o where o.users_id=?1 and o.trans_date between ?2 and ?3")
    List<Operations> getOperations(Long user_id, LocalDateTime dateFrom, LocalDateTime dateTo);

    @Query("select o from Operations o where o.users_id=?1")
    List<Operations> getOperationsWithoutDate(Long user_id);


}
