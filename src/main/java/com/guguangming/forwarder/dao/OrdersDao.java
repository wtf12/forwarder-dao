package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.OrdersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加账单信息
     *
     * @param ordersEntity
     * @return
     */
    public boolean addOrders(OrdersEntity ordersEntity) {
        String sql = "INSERT INTO orders(bill_of_lading_number, " +
                "entrust_the_number, " +
                "job_number," +
                "ship_name, " +
                "voyage, " +
                "dock_planner_manifest, " +
                "date_of_sailing, " +
                "cut_of_date, " +
                "ship_company_name," +
                "box_and_box_quantity, " +
                "port_of_discharge, " +
                "place_of_delivery, " +
                "operator_name," +
                "operator_phone, " +
                "operator_email, " +
                "documentary_clerk_name, " +
                "documentary_clerk_phone, " +
                "documentary_clerk_email)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, ordersEntity) == 1;
    }

    /**
     * 查所有账单信息
     *
     * @param
     * @return
     */
    public OrdersEntity getOrders() {
        String sql = "SELECT * FROM orders";
        return jdbcTemplate.queryForObject(sql, OrdersEntity.class);
    }

    /**
     * 根据提单号查账单
     *
     * @param billOfLadingNumber
     * @return
     */
    public OrdersEntity getOrdersByBillOfLadingNumber(String billOfLadingNumber) {
        String sql = "SELECT * FROM orders WHERE bill_of_lading_number = '" + billOfLadingNumber + "'";
        return jdbcTemplate.queryForObject(sql, OrdersEntity.class);
    }

    /**
     * 根据委托编号查账单
     *
     * @param entrustTheNumber
     * @return
     */
    public OrdersEntity getOrdersByEntrustTheNumber(String entrustTheNumber) {
        String sql = "SELECT * FROM orders WHERE entrust_the_number = '" + entrustTheNumber + "'";
        return jdbcTemplate.queryForObject(sql, OrdersEntity.class);
    }

    /**
     * 根据提单号更新账单
     *
     * @param ordersEntity
     * @return
     */
    public boolean updateOrdersByBillOfLadingNumber(OrdersEntity ordersEntity) {
        String sql = "UPDATE orders SET entrust_the_number = '" + ordersEntity.getEntrustTheNumber()
                + "', job_number = '" + ordersEntity.getJobNumber()
                + "', ship_name = '" + ordersEntity.getShipName()
                + "', voyage = '" + ordersEntity.getVoyage()
                + "', dock_planner_manifest = '" + ordersEntity.getDockPlannerManifest()
                + "', date_of_sailing = '" + ordersEntity.getDateOfSailing()
                + "', cut_of_date = '" + ordersEntity.getCutOfDate()
                + "', ship_company_name = '" + ordersEntity.getShipCompanyName()
                + "', box_and_box_quantity = '" + ordersEntity.getBoxAndBoxQuantity()
                + "', port_of_discharge = '" + ordersEntity.getPortOfDischarge()
                + "', place_of_delivery = '" + ordersEntity.getPlaceOfDelivery()
                + "', operator_name = '" + ordersEntity.getOperatorName()
                + "', operator_phone = '" + ordersEntity.getOperatorPhone()
                + "', operator_email = '" + ordersEntity.getOperatorEmail()
                + "', documentary_clerk_name = '" + ordersEntity.getDocumentaryClerkName()
                + "', documentary_clerk_phone = '" + ordersEntity.getDocumentaryClerkPhone()
                + "', documentary_clerk_email = '" + ordersEntity.getDocumentaryClerkEmail()
                + "' WHERE bill_of_lading_number = '" + ordersEntity.getBillOfLadingNumber() + "'";
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据委托编号更新账单
     *
     * @param ordersEntity
     * @return
     */
    public boolean updateOrdersByEntrustTheNumber(OrdersEntity ordersEntity) {
        String sql = "UPDATE orders SET bill_of_lading_number = '" + ordersEntity.getBillOfLadingNumber()
                + "', job_number = '" + ordersEntity.getJobNumber()
                + "', ship_name = '" + ordersEntity.getShipName()
                + "', voyage = '" + ordersEntity.getVoyage()
                + "', dock_planner_manifest = '" + ordersEntity.getDockPlannerManifest()
                + "', date_of_sailing = '" + ordersEntity.getDateOfSailing()
                + "', cut_of_date = '" + ordersEntity.getCutOfDate()
                + "', ship_company_name = '" + ordersEntity.getShipCompanyName()
                + "', box_and_box_quantity = '" + ordersEntity.getBoxAndBoxQuantity()
                + "', port_of_discharge = '" + ordersEntity.getPortOfDischarge()
                + "', place_of_delivery = '" + ordersEntity.getPlaceOfDelivery()
                + "', operator_name = '" + ordersEntity.getOperatorName()
                + "', operator_phone = '" + ordersEntity.getOperatorPhone()
                + "', operator_email = '" + ordersEntity.getOperatorEmail()
                + "', documentary_clerk_name = '" + ordersEntity.getDocumentaryClerkName()
                + "', documentary_clerk_phone = '" + ordersEntity.getDocumentaryClerkPhone()
                + "', documentary_clerk_email = '" + ordersEntity.getDocumentaryClerkEmail()
                + "' WHERE entrust_the_number = '" + ordersEntity.getEntrustTheNumber() + "'";
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据提单号删除账单
     *
     * @param billOfLadingNumber
     * @return
     */
    public boolean deleteOrderByBillOfLadingNumber(String billOfLadingNumber) {
        String sql = "DELETE FROM orders WHERE bill_of_lading_number = '" + billOfLadingNumber + "'";
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据委托编号删除账单
     *
     * @param entrustTheNumber
     * @return
     */
    public boolean deleteOrderByEntrustTheNumber(String entrustTheNumber) {
        String sql = "DELETE FROM orders WHERE entrust_the_number = '" + entrustTheNumber + "'";
        return jdbcTemplate.update(sql) == 1;
    }
}