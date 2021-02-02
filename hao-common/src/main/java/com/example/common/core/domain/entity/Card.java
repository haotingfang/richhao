package entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Card)实体类
 *
 * @author makejava
 * @since 2021-02-02 17:49:31
 */
@Data
public class Card implements Serializable {
    private static final long serialVersionUID = 578958986091175150L;

    private Long id;
    /**
     * 卡 标题 简介
     */
    private String title;
    /**
     * 卡类型（1：次卡；2：期卡）
     */
    private String type;
    /**
     * 次卡的次数
     */
    private Long cardCount;
    /**
     * 卡的有效期限（1：周卡；2:月卡；3:季卡；4:半年卡 5：年卡 6:2年卡
     * ）
     */
    private String cardTerm;
    /**
     * 门店通用类型（1:通用 ；2:不通用）
     */
    private String storeCurrency;
    /**
     * 价格
     */
    private Integer price;
    /**
     * 状态（0:正常 1:停用）
     */
    private String status;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 卡详情描述
     */
    private String remark;


}