package com.neuedu.pojo;

import java.util.Date;

public class Cart {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neuedu_cart.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neuedu_cart.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neuedu_cart.produce_id
     *
     * @mbg.generated
     */
    private Integer produceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neuedu_cart.quantity
     *
     * @mbg.generated
     */
    private Integer quantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neuedu_cart.checked
     *
     * @mbg.generated
     */
    private Integer checked;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neuedu_cart.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neuedu_cart.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neuedu_cart.id
     *
     * @return the value of neuedu_cart.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neuedu_cart.id
     *
     * @param id the value for neuedu_cart.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neuedu_cart.user_id
     *
     * @return the value of neuedu_cart.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neuedu_cart.user_id
     *
     * @param userId the value for neuedu_cart.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neuedu_cart.produce_id
     *
     * @return the value of neuedu_cart.produce_id
     *
     * @mbg.generated
     */
    public Integer getProduceId() {
        return produceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neuedu_cart.produce_id
     *
     * @param produceId the value for neuedu_cart.produce_id
     *
     * @mbg.generated
     */
    public void setProduceId(Integer produceId) {
        this.produceId = produceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neuedu_cart.quantity
     *
     * @return the value of neuedu_cart.quantity
     *
     * @mbg.generated
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neuedu_cart.quantity
     *
     * @param quantity the value for neuedu_cart.quantity
     *
     * @mbg.generated
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neuedu_cart.checked
     *
     * @return the value of neuedu_cart.checked
     *
     * @mbg.generated
     */
    public Integer getChecked() {
        return checked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neuedu_cart.checked
     *
     * @param checked the value for neuedu_cart.checked
     *
     * @mbg.generated
     */
    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neuedu_cart.create_time
     *
     * @return the value of neuedu_cart.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neuedu_cart.create_time
     *
     * @param createTime the value for neuedu_cart.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neuedu_cart.update_time
     *
     * @return the value of neuedu_cart.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neuedu_cart.update_time
     *
     * @param updateTime the value for neuedu_cart.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}