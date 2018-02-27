package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.SailingScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public class SailingScheduleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加船期表信息
     *
     * @param sailingScheduleEntity
     * @return
     */
    public boolean addSailingSchedule(SailingScheduleEntity sailingScheduleEntity) {
        String sql = "INSERT INTO sailing_schedule(schedule_name, " +
                "schedule_size, " +
                "schedule_type, " +
                "schedule_url, " +
                "schedule_time) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql, sailingScheduleEntity) == 1;
    }

    /**
     * 查找所有船期表信息
     *
     * @return
     */
    public SailingScheduleEntity getSailingSchedule() {
        String sql = "SELECT * FROM sailing_schedule";
        return jdbcTemplate.queryForObject(sql, SailingScheduleEntity.class);
    }

    /**
     * 根据船期表编号查船期表信息
     *
     * @return
     */
    public SailingScheduleEntity getSailingScheduleByScheduleId(Integer scheduleId) {
        String sql = "SELECT * FROM sailing_schedule WHERE schedule_id =" + scheduleId;
        return jdbcTemplate.queryForObject(sql, SailingScheduleEntity.class);
    }

    /**
     * 根据船期表名字超船期表信息
     *
     * @return
     */
    public SailingScheduleEntity getSailingScheduleByScheduleName(String scheduleName) {
        String sql = "SELECT * FROM sailing_schedule WHERE schedule_name =" + scheduleName;
        return jdbcTemplate.queryForObject(sql, SailingScheduleEntity.class);
    }

    /**
     * 根据船期表时间查船期表
     *
     * @return
     */
    public SailingScheduleEntity getSailingScheduleByScheduleTime(Date scheduleTime) {
        String sql = "SELECT * FROM sailing_schedule WHERE schedule_time =" + scheduleTime;
        return jdbcTemplate.queryForObject(sql, SailingScheduleEntity.class);
    }

    /**
     * 根据船期表编号更新船期表信息
     *
     * @param sailingScheduleEntity
     * @return
     */
    public boolean updateSailingScheduleByScheduleId(SailingScheduleEntity sailingScheduleEntity) {
        String sql = "UPDATE sailing_schedule SET schedule_name =" + sailingScheduleEntity.getScheduleName()
                + ", schedule_size =" + sailingScheduleEntity.getScheduleSize()
                + ", schedule_type =" + sailingScheduleEntity.getScheduleType()
                + ", schedule_url =" + sailingScheduleEntity.getScheduleUrl()
                + ", schedule_time =" + sailingScheduleEntity.getScheduleTime()
                + " WHERE schedule_id =" + sailingScheduleEntity.getScheduleId();
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据船期表编号删除船期表信息
     *
     * @param scheduleId
     * @return
     */
    public boolean deleteSailingScheduleByScheduleId(Integer scheduleId) {
        String sql = "DELETE FROM sailing_schedule WHERE schedule_id =" + scheduleId;
        return jdbcTemplate.update(sql) == 1;
    }
}