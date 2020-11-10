package com.example.common.core.domain.vo;

import com.example.common.core.domain.entity.UserInfo;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UserInfoVo extends UserInfo {

    /** 用户类型 0：管理员 ；1：教练 ；3：普通会员 4：VIP会员 */
    private String userTypeText;

    /** 用户性别 1：男 ；2：女*/
    private String genderText;

    /** 帐号状态（0正常 1停用 2删除） */
    private String statusText;



}
