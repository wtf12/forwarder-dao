package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class NewsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加新闻
     *
     * @param newsEntity
     * @return
     */
    public boolean addNews(NewsEntity newsEntity) {
        String sql = "INSERT INTO news(news_title, news_img_url, " +
                "news_content, " +
                "news_synopsis, " +
                "news_synopsis_img_url, " +
                "news_time)" +
                "VALUES (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, newsEntity) == 1;
    }

    /**
     * 查找所有新闻
     *
     * @param
     * @return
     */
    public NewsEntity getNews() {
        String sql = "SELECT * FROM news";
        return jdbcTemplate.queryForObject(sql, NewsEntity.class);
    }

    /**
     * 根据编号查新闻
     *
     * @param id
     * @return
     */
    public NewsEntity getNewsById(Integer id) {
        String sql = "SELECT * FROM news WHERE id =" + id;
        return jdbcTemplate.queryForObject(sql, NewsEntity.class);
    }

    /**
     * 根据新闻标题查新闻
     *
     * @param newsTitle
     * @return
     */
    public NewsEntity getNewsByNewsTitle(String newsTitle) {
        String sql = "SELECT * FROM news WHERE news_title =" + newsTitle;
        return jdbcTemplate.queryForObject(sql, NewsEntity.class);
    }

    /**
     * 根据新闻时间查新闻
     *
     * @param newsTime
     * @return
     */
    public NewsEntity getNewsByNewsTime(String newsTime) {
        String sql = "SELECT * FROM news WHERE news_time =" + newsTime;
        return jdbcTemplate.queryForObject(sql, NewsEntity.class);
    }

    /**
     * 根据编号更新新闻
     *
     * @param newsEntity
     * @return
     */
    public boolean updateNewsById(NewsEntity newsEntity) {
        String sql = "UPDATE news SET news_title =" + newsEntity.getNewsTitle()
                + ", news_img_url =" + newsEntity.getNewsImgUrl()
                + ", news_content =" + newsEntity.getNewsContent()
                + ", news_synopsis =" + newsEntity.getNewsSynopsis()
                + ", news_synopsis_img_url =" + newsEntity.getNewsSynopsisImgUrl()
                + ", news_time =" + newsEntity.getNewsTime()
                + " WHERE id =" + newsEntity.getId();
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据编号删除新闻
     *
     * @param id
     * @return
     */
    public boolean deleteNewsById(Integer id) {
        String sql = "DELETE FROM news WHERE id =" + id;
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据新闻时间删除新闻
     *
     * @param newsTime
     * @return
     */
    public boolean deleteNewsByNewsTime(Date newsTime) {
        String sql = "DELETE FROM news WHERE news_time =" + newsTime;
        return jdbcTemplate.update(sql) == 1;
    }
}