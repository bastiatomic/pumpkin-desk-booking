package international.pumpkin.api.repository;

import international.pumpkin.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

/*    @Query(value = "SELECT temp FROM tbl_desk WHERE desk_id =:desk_id", nativeQuery = true)
    public double getTempFromSingleDesk(@Param("desk_id") final long desk_id);*/


}