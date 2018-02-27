package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.EmployeesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加员工信息
     *
     * @param employeesEntity
     * @return
     */
    public boolean addEmployees(EmployeesEntity employeesEntity) {
        String sql = "INSERT INTO employees(employees_img_name, " +
                "employees_img_url, " +
                "employees_synopsis) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, employeesEntity) == 1;
    }

    /**
     * 查找所有员工照片信息
     *
     * @param
     * @return
     */
    public EmployeesEntity getEmployees() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.queryForObject(sql, EmployeesEntity.class);
    }

    /**
     * 根据编号查找员工照片信息
     *
     * @param employeesId
     * @return
     */
    public EmployeesEntity getEmployeesByEmployeesId(String employeesId) {
        String sql = "SELECT * FROM employees WHERE employees_id =" + employeesId;
        return jdbcTemplate.queryForObject(sql, EmployeesEntity.class);
    }

    /**
     * 根据编号更新员工照片信息
     *
     * @param employeesEntity
     * @return
     */
    public boolean updateEmployeesById(EmployeesEntity employeesEntity) {
        String sql = "UPDATE employees SET employees_img_name =" + employeesEntity.getEmployeesImgName()
                + ", employees_img_url =" + employeesEntity.getEmployeesImgUrl()
                + ", employees_synopsis =" + employeesEntity.getEmployeesSynopsis()
                + " WHERE employees_id =" + employeesEntity.getEmployeesId();
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据编号删除员工照片信息
     *
     * @param employeesId
     * @return
     */
    public boolean deleteEmployeesById(String employeesId) {
        String sql = "DELETE FROM employees WHERE employees_id =" + employeesId;
        return jdbcTemplate.update(sql) == 1;
    }
}