package com.example.framework.web.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.entity.UserInfo;
import com.example.common.core.domain.model.LoginUser;
import com.example.common.core.domain.vo.UserInfoVo;
import com.example.common.enums.CommonStatus;
import com.example.common.enums.DelFlag;
import com.example.common.enums.UserType;
import com.example.common.utils.EnumUtil;
import com.example.common.utils.SecurityUtils;
import com.example.dao.mapper.UserInfoMapper;
import com.example.dao.service.MenuService;
import com.example.dao.service.RoleService;
import com.example.dao.service.impl.MenuServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class SysLoginService {

    private static Logger logger = LoggerFactory.getLogger(SysLoginService.class);

    @Value("${wx.defaultPassword}")
    private String defaultPassword;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /*web端登录*/
    public AjaxResult login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            String token = tokenService.createToken(loginUser);
            Map res = new HashMap<>();
            res.put("token", token);
            res.put("loginUser", loginUser);
            AjaxResult ajaxResult = AjaxResult.success(res);
            return ajaxResult;
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new RuntimeException("用户名或密码不正确");
            } else {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    /*微信端-帐号密码登录*/
    public LoginUser login(String username, String password, String code) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            //请求微信后台 绑定用户openid等信息
            UserInfoVo userInfoVo = loginUser.getUserInfoVo();
            bingUserOpenid(code, userInfoVo);
            tokenService.createToken(loginUser);

            return loginUser;
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new RuntimeException("用户名或密码不正确");
            } else {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    /*微信端-微信授权登录*/
    public AjaxResult login(String code, UserInfo userInfo) throws WxErrorException {
        //待开发
        UserInfoVo userInfoVo = buildSysUserByWxCode(code, userInfo);
        //获取用户权限
        Set<String> roles = roleService.getRolePermission(userInfoVo.getUserId());
        Set<String> permissions = menuService.getMenuPermission(userInfoVo.getUserId());

        LoginUser loginUser = new LoginUser(userInfoVo, roles, permissions);
        String token = tokenService.createToken(loginUser);
        Map res = new HashMap<>();
        res.put("token", token);
        res.put("loginUser", loginUser);
        AjaxResult ajaxResult = AjaxResult.success(res);
        return ajaxResult;
    }

    private void bingUserOpenid(String code, UserInfoVo userInfoVo) throws WxErrorException {
        WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
        String sessionKey = session.getSessionKey();
        String openId = session.getOpenid();
        //更新openid
        userInfoVo.setOpenId(openId);
        int i = userInfoMapper.updateUserById(userInfoVo);
        logger.info("更新用户成功 sysUser：[{}] , i: [{}]", userInfoVo, i);
    }

    private UserInfoVo buildSysUserByWxCode(String code, UserInfo wxuserInfo) throws WxErrorException {
        WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
        String sessionKey = session.getSessionKey();
        String openId = session.getOpenid();

        //校验用户是否存在
        UserInfo userInfo = userInfoMapper.selectUserByOpenId(openId);
        if (userInfo == null) {
            wxuserInfo.setOpenId(openId);
            wxuserInfo.setUserName(openId);
            wxuserInfo.setPassword(SecurityUtils.encryptPassword(defaultPassword));
            wxuserInfo.setUserType(Integer.parseInt(UserType.ORDINARY_MEMBER.getCode()));
            wxuserInfo.setStatus(CommonStatus.OK.getCode());
            wxuserInfo.setDelFlag(DelFlag.EXIST.getCode());
            int i = userInfoMapper.addUser(wxuserInfo);
            userInfo = wxuserInfo;
        } else {
            //更新用户信息

        }
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        userInfoVo.setUserTypeText(EnumUtil.getByCode(String.valueOf(userInfo.getUserType()), UserType.class));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userInfoVo.setCreateTime(userInfo.getCreateTime());
        logger.info("新增或更新用户成功 userInfo：[{}] ", userInfoVo);
        return userInfoVo;
    }


}
