package com.example.dao.mapper;

import com.example.common.core.domain.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public UserInfo selectUserByUserName(String userName);

    /**
     * 通过用户id查询用户
     *
     * @param userId 用户id
     * @return 用户对象信息
     */
    public UserInfo selectUserByUserId(Long userId);

    /**
     * 通过openId查询用户
     *
     * @param openId openId
     * @return 用户对象信息
     */
    public UserInfo selectUserByOpenId(String openId);

    /**
     * 删除用户
     *
     * @param ids 用户Id
     * @return 更新的条数
     */
    public int deleteUsers(@Param("ids") Long[] ids, @Param("updateBy") String updateBy);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     * @return 更新的条数
     */
    public int updateUserById(UserInfo userInfo);

    /**
     * 插入用户信息
     *
     * @param userInfo 用户信息
     * @return 更新的条数
     */
    public int addUser(UserInfo userInfo);

    /**
     * 插入用户信息
     *
     * @param userInfo 用户信息
     * @return 更新的条数
     */
    public List<UserInfo> selectUserList(UserInfo userInfo);

    public Boolean checkPhoneExist(@Param("phoneNumber") String phoneNumber,@Param("userId") Long userId);

    public Boolean checkEmailExist(@Param("email") String email , @Param("userId") Long userId );


}
