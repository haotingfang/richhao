package com.example.common.core.domain.model;

import com.example.common.core.domain.entity.UserInfo;
import com.example.common.core.domain.vo.UserInfoVo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
public class LoginUser implements UserDetails {

    private String token;

    private long loginTime;

    private long expireTime;

    private UserInfoVo userInfoVo;

    private Set<String> roles;

    private Set<String> permissions;

    public LoginUser() {
    }

    public LoginUser(UserInfoVo userInfoVo, Set<String> roles, Set<String> permissions) {
        this.userInfoVo = userInfoVo;
        this.roles = roles;
        this.permissions = permissions;
    }

    /*设置用户权限*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        Set<String> roles = this.getRoles();
        for (String role : roles) {
            auths.add(new SimpleGrantedAuthority(role));
        }
        Set<String> pers = this.getPermissions();
        for (String per : pers) {
            auths.add(new SimpleGrantedAuthority(per));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return userInfoVo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfoVo.getUserName();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
