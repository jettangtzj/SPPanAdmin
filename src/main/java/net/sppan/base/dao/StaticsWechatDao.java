package net.sppan.base.dao;

import net.sppan.base.entity.StaticsByAccount;
import net.sppan.base.entity.StaticsByDate;
import net.sppan.base.entity.WechatLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StaticsWechatDao extends CrudRepository<WechatLog, Long> {

    @Query(value =
            "SELECT v.account AS account, COUNT(v) AS count " +
                    "FROM WechatLog v " +
                    "WHERE v.username = :username " +
                    "GROUP BY v.account")
    List<StaticsByAccount> getCountByAccount(@Param("username") String username);


    @Query(nativeQuery = true, value =
            "SELECT COUNT(*) AS count, v.create_time AS date " +
                    "FROM tb_wechatlog v " +
                    "WHERE v.username = :username " +
                    "GROUP BY v.create_time ORDER BY v.create_time DESC LIMIT 7")
    List<StaticsByDate> getCountByDate(@Param("username") String username);
}
