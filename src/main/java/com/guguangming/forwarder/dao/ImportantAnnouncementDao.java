package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.entity.ImportantAnnouncementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Repository
public class ImportantAnnouncementDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加公告内容
     *
     * @param importantAnnouncementEntity
     * @return
     */
    public boolean addImportantAnnouncement(ImportantAnnouncementEntity importantAnnouncementEntity) {
        String sql = "INSERT INTO important_announcement(announcement_name, " +
                "announcement_content, " +
                "announcement_time) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, importantAnnouncementEntity) == 1;
    }

    /**
     * 查找所有公告
     *
     * @param
     * @return
     */
    public ImportantAnnouncementEntity getImportantAnnouncement() {
        String sql = "SELECT * FROM important_announcement";
        return jdbcTemplate.queryForObject(sql, ImportantAnnouncementEntity.class);
    }

    /**
     * 根据公告编号查找公告
     *
     * @param announcementId
     * @return
     */
    public ImportantAnnouncementEntity getImportantAnnouncementByAnnouncementId(Integer announcementId) {
        String sql = "SELECT * FROM important_announcement WHERE announcement_id =" + announcementId;
        return jdbcTemplate.queryForObject(sql, ImportantAnnouncementEntity.class);
    }

    /**
     * 根据公告名称查找公告
     *
     * @param announcementName
     * @return
     */
    public ImportantAnnouncementEntity getImportantAnnouncementByAnnouncementName(String announcementName) {
        String sql = "SELECT * FROM important_announcement WHERE announcement_name = '" + announcementName + "'";
        return jdbcTemplate.queryForObject(sql, ImportantAnnouncementEntity.class);
    }

    /**
     * 根据公告时间查找公告
     *
     * @param announcementTime
     * @return
     */
    public ImportantAnnouncementEntity getImportantAnnouncementByAnnouncementTime(Date announcementTime) {
        String sql = "SELECT * FROM important_announcement WHERE announcement_time = '" + announcementTime + "'";
        return jdbcTemplate.queryForObject(sql, ImportantAnnouncementEntity.class);
    }

    /**
     * 根据编号更新公告
     *
     * @param importantAnnouncementEntity
     * @return
     */
    public boolean updateImportantAnnouncementById(ImportantAnnouncementEntity importantAnnouncementEntity) {
        String sql = "UPDATE important_announcement SET announcement_name = '" + importantAnnouncementEntity.getAnnouncementName()
                + "', announcement_content = '" + importantAnnouncementEntity.getAnnouncementContent()
                + "', announcement_time = '" + importantAnnouncementEntity.getAnnouncementTime()
                + "' WHERE announcement_id =" + importantAnnouncementEntity.getAnnouncementId();
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据编号删除公告
     *
     * @param announcementId
     * @return
     */
    public boolean deleteImportantAnnouncementByAnnouncementId(Integer announcementId) {
        String sql = "DELETE FROM important_announcement WHERE announcement_id =" + announcementId;
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据公告时间删除公告
     *
     * @param announcementTime
     * @return
     */
    public boolean updateImportantAnnouncementByAnnouncementTime(Date announcementTime) {
        String sql = "DELETE FROM important_announcement WHERE announcement_time = '" + announcementTime + "'";
        return jdbcTemplate.update(sql) == 1;
    }


}