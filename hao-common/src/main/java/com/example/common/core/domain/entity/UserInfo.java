package com.example.common.core.domain.entity;

import com.example.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UserInfo extends BaseEntity {

    /** 用户ID */
    private Long userId;

    /** 微信openId */
    private String openId;

    /** 用户昵称 */
    private String nickName;

    /** 用户账号 */
    private String userName;

    /** 用户类型 0：管理员 ；1：教练 ；2：学员 */
    private String userType;

    /** 用户性别 1：男 ；2：女*/
    private String gender;

    /** 用户语言 */
    private String language;

    /** 用户国家 */
    private String country;

    /** 用户省份 */
    private String province;

    /** 用户城市 */
    private String city;

    /** 用户邮箱 */
    private String email;

    /** 手机号码 */
    private String phoneNumber;

    /** 用户头像 */
    private String avatarUrl;

    /** 密码 */
    private String password;

    /** 盐加密 */
//    private String salt;

    /** 帐号状态（0正常 1停用） */
    private String status;

    /** 删除状态（0存在 2删除） */
    private String delFlag;

    /** 备注 */
    private String remark;


}
