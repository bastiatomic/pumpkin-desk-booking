package international.pumpkin.api.repository;

import international.pumpkin.api.model.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Integer> {

    //@Query(value = "SELECT temp FROM tbl_desk WHERE desk_id =:desk_id", nativeQuery = true)
    //public double getTempFromSingleDesk(@Param("desk_id") final long desk_id);

    @Query(value = "UPDATE DeskBaseData SET Blocked = true WHERE ID=:desk_id", nativeQuery = true  )
    void blocking(@Param("desk_id") final int desk_id);

    @Query(value = "UPDATE DeskBaseData SET Blocked = false WHERE ID=:desk_id", nativeQuery = true  )
    void unblocking(@Param("desk_id") final int desk_id);

/*
    @Query(value = " INSERT INTO DeskUsage (BlockedTimestamp, Desk_ID) VALUES (?time, ?desk_id)", nativeQuery = true)
    void blockLogging(@Param("desk_id") final int desk_id,
                             @Param("time") final ZonedDateTime time);
*/

/*
    @Query(value = " UPDATE DeskUsage SET FreedTimestamp =:time WHERE Desk_ID=:desk_id", nativeQuery = true)
    void unblockLogging(@Param("desk_id") final int desk_id,
                               @Param("time") final ZonedDateTime time);
*/

    @Query(value = "INSERT INTO Temperature (Desk_ID , TimeStamp , Value) VALUES ( :desk_id , :time , :temperature )", nativeQuery = true)
    void setTemperature(@Param("desk_id") final int desk_id,
                        @Param("time") final Timestamp time,
                        @Param("temperature") final float temperature);
    @Query(value = "SELECT * FROM DeskBaseData", nativeQuery = true)
    List<Desk> getBaseData();
}