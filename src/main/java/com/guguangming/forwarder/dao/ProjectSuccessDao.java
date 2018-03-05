package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.ProjectSuccessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectSuccessDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加成功项目信息
     *
     * @param projectSuccessEntity
     * @return
     */
    public boolean addProjectSuccess(ProjectSuccessEntity projectSuccessEntity) {
        String sql = "INSERT INTO project_success(project_name," +
                "project_img_url, " +
                "project_synopsis, " +
                "project_time) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, projectSuccessEntity) == 1;
    }

    /**
     * 查找所有项目信息
     *
     * @param
     * @return
     */
    public ProjectSuccessEntity getProjectSuccessByProjectName() {
        String sql = "SELECT * FROM project_success";
        return jdbcTemplate.queryForObject(sql, ProjectSuccessEntity.class);
    }

    /**
     * 根据编号查找项目信息
     *
     * @param projectId
     * @return
     */
    public ProjectSuccessEntity getProjectSuccessByProjectId(Integer projectId) {
        String sql = "SELECT * FROM project_success WHERE project_id =" + projectId;
        return jdbcTemplate.queryForObject(sql, ProjectSuccessEntity.class);
    }

    /**
     * 根据项目名查找项目信息
     *
     * @param projectName
     * @return
     */
    public ProjectSuccessEntity getProjectSuccessByProjectName(String projectName) {
        String sql = "SELECT * FROM project_success WHERE project_name = '" + projectName + "'";
        return jdbcTemplate.queryForObject(sql, ProjectSuccessEntity.class);
    }

    /**
     * 根据编号更新项目信息
     *
     * @param projectSuccessEntity
     * @return
     */
    public boolean updateProjectSuccessByProjectId(ProjectSuccessEntity projectSuccessEntity) {
        String sql = "UPDATE project_success SET project_name ='" + projectSuccessEntity.getProjectName()
                + "', project_img_url = '" + projectSuccessEntity.getProjectImgUrl()
                + "', project_synopsis = '" + projectSuccessEntity.getProjectSynopsis()
                + "', project_time = '" + projectSuccessEntity.getProjectTime()
                + "' WHERE project_id =" + projectSuccessEntity.getProjectId();
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据编号删除项目
     *
     * @param projectId
     * @return
     */
    public boolean deleteProjectSuccess(Integer projectId) {
        String sql = "DELETE FROM project_success WHERE project_id =" + projectId;
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据项目名删除项目
     *
     * @param projectName
     * @return
     */
    public boolean deleteProjectSuccess(String projectName) {
        String sql = "DELETE FROM project_success WHERE project_name = '" + projectName + "'";
        return jdbcTemplate.update(sql) == 1;
    }
}