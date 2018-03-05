package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.CheckNoticeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CheckNoticeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据编号查找查单须知内容(编号有且仅为1)
     *
     * @param
     * @return
     */
    public CheckNoticeEntity getCheckNoticeByNoticeId() {
        String sql = "SELECT notice FROM check_notice WHERE notice_id = 1";
        return jdbcTemplate.queryForObject(sql, CheckNoticeEntity.class);
    }

    /**
     * 根据编号更新查单须知内容(编号有且仅为1)
     *
     * @param
     * @return
     */
    public boolean updateCheckNoticeByNoticeId(String notic) {
        String sql = "UPDATE check_notice SET notice = '" + notic + "' WHERE notice_id = 1";
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据编号删除查单须知内容(编号有且仅为1)
     *
     * @param
     * @return
     */
    public boolean deleteCheckNoticeByNoticeId() {
        String sql = "DELETE FROM check_notice WHERE notice_id = 1";
        return jdbcTemplate.update(sql) == 1;
    }
}