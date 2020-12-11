package com.example.web.controller.login;

import com.example.common.annotation.Log;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.entity.Menu;
import com.example.common.core.domain.entity.UserInfo;
import com.example.common.core.domain.model.LoginUser;
import com.example.common.core.domain.vo.UserInfoVo;
import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;
import com.example.common.utils.ServletUtils;
import com.example.dao.service.MenuService;
import com.example.dao.service.RoleService;
import com.example.framework.web.service.SysLoginService;
import com.example.framework.web.service.TokenService;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.api.scripting.AbstractJSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Api("登录模块")
@RestController
@RequestMapping("/auth")
public class SysLoginController {

    private static Logger logger = LoggerFactory.getLogger(SysLoginController.class);

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Log(title = "web登录" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("登录")
    @PostMapping("/web_login")
    public AjaxResult login(@RequestBody UserInfo userInfo) {
        logger.info("请求登录接口 UserInfo:[{}] ", userInfo);
        AjaxResult ajaxResult = loginService.login(userInfo.getUserName(), userInfo.getPassword());
        return ajaxResult;
    }


    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        UserInfoVo userInfoVo = loginUser.getUserInfoVo();
        // 角色集合
        Set<String> roles = roleService.getRolePermission(userInfoVo.getUserId());
        // 权限集合
        Set<String> permissions = menuService.getMenuPermission(userInfoVo.getUserId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", userInfoVo);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public AjaxResult  getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        UserInfoVo userInfoVo = loginUser.getUserInfoVo();
        List<Menu> menus = menuService.selectMenuTreeByUserId(userInfoVo.getUserId());
        AjaxResult ajaxResult = AjaxResult.success(menuService.buildMenus(menus));
        logger.info("  getRouters ajaxResult:"+ajaxResult);
        return ajaxResult;
//        {"msg":"操作成功","code":200,"data":[{"name":"System","path":"/system","hidden":false,"redirect":"noRedirect","component":"Layout","alwaysShow":true,"meta":{"title":"系统管理","icon":"system","noCache":false},"children":[{"name":"User","path":"user","hidden":false,"component":"system/user/index","meta":{"title":"用户管理","icon":"user","noCache":false}},{"name":"Role","path":"role","hidden":false,"component":"system/role/index","meta":{"title":"角色管理","icon":"peoples","noCache":false}},{"name":"Menu","path":"menu","hidden":false,"component":"system/menu/index","meta":{"title":"菜单管理","icon":"tree-table","noCache":false}},{"name":"Dept","path":"dept","hidden":false,"component":"system/dept/index","meta":{"title":"部门管理","icon":"tree","noCache":false}},{"name":"Post","path":"post","hidden":false,"component":"system/post/index","meta":{"title":"岗位管理","icon":"post","noCache":false}},{"name":"Dict","path":"dict","hidden":false,"component":"system/dict/index","meta":{"title":"字典管理","icon":"dict","noCache":false}},{"name":"Config","path":"config","hidden":false,"component":"system/config/index","meta":{"title":"参数设置","icon":"edit","noCache":false}},{"name":"Notice","path":"notice","hidden":false,"component":"system/notice/index","meta":{"title":"通知公告","icon":"message","noCache":false}},{"name":"Log","path":"log","hidden":false,"redirect":"noRedirect","component":"system/log/index","alwaysShow":true,"meta":{"title":"日志管理","icon":"log","noCache":false},"children":[{"name":"Operlog","path":"operlog","hidden":false,"component":"monitor/operlog/index","meta":{"title":"操作日志","icon":"form","noCache":false}},{"name":"Logininfor","path":"logininfor","hidden":false,"component":"monitor/logininfor/index","meta":{"title":"登录日志","icon":"logininfor","noCache":false}}]}]},{"name":"Monitor","path":"/monitor","hidden":false,"redirect":"noRedirect","component":"Layout","alwaysShow":true,"meta":{"title":"系统监控","icon":"monitor","noCache":false},"children":[{"name":"Online","path":"online","hidden":false,"component":"monitor/online/index","meta":{"title":"在线用户","icon":"online","noCache":false}},{"name":"Job","path":"job","hidden":false,"component":"monitor/job/index","meta":{"title":"定时任务","icon":"job","noCache":false}},{"name":"Druid","path":"druid","hidden":false,"component":"monitor/druid/index","meta":{"title":"数据监控","icon":"druid","noCache":false}},{"name":"Server","path":"server","hidden":false,"component":"monitor/server/index","meta":{"title":"服务监控","icon":"server","noCache":false}},{"name":"Cache","path":"cache","hidden":false,"component":"monitor/cache/index","meta":{"title":"缓存监控","icon":"redis","noCache":false}}]},{"name":"Tool","path":"/tool","hidden":false,"redirect":"noRedirect","component":"Layout","alwaysShow":true,"meta":{"title":"系统工具","icon":"tool","noCache":false},"children":[{"name":"Build","path":"build","hidden":false,"component":"tool/build/index","meta":{"title":"表单构建","icon":"build","noCache":false}},{"name":"Gen","path":"gen","hidden":false,"component":"tool/gen/index","meta":{"title":"代码生成","icon":"code","noCache":false}},{"name":"Swagger","path":"swagger","hidden":false,"component":"tool/swagger/index","meta":{"title":"系统接口","icon":"swagger","noCache":false}}]},{"name":"Http://ruoyi.vip","path":"http://ruoyi.vip","hidden":false,"component":"Layout","meta":{"title":"若依官网","icon":"guide","noCache":false}}]}
//        return "{\"msg\":\"操作成功\",\"code\":200,\"data\":[{\"path\":\"/system\",\"redirect\":\"noRedirect\",\"component\":\"Layout\",\"alwaysShow\":true,\"meta\":{\"title\":\"系统管理\",\"icon\":\"system\"},\"children\":[{\"path\":\"user\",\"component\":\"system/user/index\",\"meta\":{\"title\":\"用户管理\",\"icon\":\"user\"}},{\"name\":\"Role\",\"path\":\"role\",\"hidden\":false,\"component\":\"system/role/index\",\"meta\":{\"title\":\"角色管理\",\"icon\":\"peoples\",\"noCache\":false}},{\"name\":\"Menu\",\"path\":\"menu\",\"hidden\":false,\"component\":\"system/menu/index\",\"meta\":{\"title\":\"菜单管理\",\"icon\":\"tree-table\",\"noCache\":false}},{\"name\":\"Dept\",\"path\":\"dept\",\"hidden\":false,\"component\":\"system/dept/index\",\"meta\":{\"title\":\"部门管理\",\"icon\":\"tree\",\"noCache\":false}},{\"name\":\"Post\",\"path\":\"post\",\"hidden\":false,\"component\":\"system/post/index\",\"meta\":{\"title\":\"岗位管理\",\"icon\":\"post\",\"noCache\":false}},{\"name\":\"Dict\",\"path\":\"dict\",\"hidden\":false,\"component\":\"system/dict/index\",\"meta\":{\"title\":\"字典管理\",\"icon\":\"dict\",\"noCache\":false}},{\"name\":\"Config\",\"path\":\"config\",\"hidden\":false,\"component\":\"system/config/index\",\"meta\":{\"title\":\"参数设置\",\"icon\":\"edit\",\"noCache\":false}},{\"name\":\"Notice\",\"path\":\"notice\",\"hidden\":false,\"component\":\"system/notice/index\",\"meta\":{\"title\":\"通知公告\",\"icon\":\"message\",\"noCache\":false}},{\"name\":\"Log\",\"path\":\"log\",\"hidden\":false,\"redirect\":\"noRedirect\",\"component\":\"system/log/index\",\"alwaysShow\":true,\"meta\":{\"title\":\"日志管理\",\"icon\":\"log\",\"noCache\":false},\"children\":[{\"name\":\"Operlog\",\"path\":\"operlog\",\"hidden\":false,\"component\":\"monitor/operlog/index\",\"meta\":{\"title\":\"操作日志\",\"icon\":\"form\",\"noCache\":false}},{\"name\":\"Logininfor\",\"path\":\"logininfor\",\"hidden\":false,\"component\":\"monitor/logininfor/index\",\"meta\":{\"title\":\"登录日志\",\"icon\":\"logininfor\",\"noCache\":false}}]}]},{\"name\":\"Monitor\",\"path\":\"/monitor\",\"hidden\":false,\"redirect\":\"noRedirect\",\"component\":\"Layout\",\"alwaysShow\":true,\"meta\":{\"title\":\"系统监控\",\"icon\":\"monitor\",\"noCache\":false},\"children\":[{\"name\":\"Online\",\"path\":\"online\",\"hidden\":false,\"component\":\"monitor/online/index\",\"meta\":{\"title\":\"在线用户\",\"icon\":\"online\",\"noCache\":false}},{\"name\":\"Job\",\"path\":\"job\",\"hidden\":false,\"component\":\"monitor/job/index\",\"meta\":{\"title\":\"定时任务\",\"icon\":\"job\",\"noCache\":false}},{\"name\":\"Druid\",\"path\":\"druid\",\"hidden\":false,\"component\":\"monitor/druid/index\",\"meta\":{\"title\":\"数据监控\",\"icon\":\"druid\",\"noCache\":false}},{\"name\":\"Server\",\"path\":\"server\",\"hidden\":false,\"component\":\"monitor/server/index\",\"meta\":{\"title\":\"服务监控\",\"icon\":\"server\",\"noCache\":false}},{\"name\":\"Cache\",\"path\":\"cache\",\"hidden\":false,\"component\":\"monitor/cache/index\",\"meta\":{\"title\":\"缓存监控\",\"icon\":\"redis\",\"noCache\":false}}]},{\"name\":\"Tool\",\"path\":\"/tool\",\"hidden\":false,\"redirect\":\"noRedirect\",\"component\":\"Layout\",\"alwaysShow\":true,\"meta\":{\"title\":\"系统工具\",\"icon\":\"tool\",\"noCache\":false},\"children\":[{\"name\":\"Build\",\"path\":\"build\",\"hidden\":false,\"component\":\"tool/build/index\",\"meta\":{\"title\":\"表单构建\",\"icon\":\"build\",\"noCache\":false}},{\"name\":\"Gen\",\"path\":\"gen\",\"hidden\":false,\"component\":\"tool/gen/index\",\"meta\":{\"title\":\"代码生成\",\"icon\":\"code\",\"noCache\":false}},{\"name\":\"Swagger\",\"path\":\"swagger\",\"hidden\":false,\"component\":\"tool/swagger/index\",\"meta\":{\"title\":\"系统接口\",\"icon\":\"swagger\",\"noCache\":false}}]},{\"name\":\"Http://ruoyi.vip\",\"path\":\"http://ruoyi.vip\",\"hidden\":false,\"component\":\"Layout\",\"meta\":{\"title\":\"若依官网\",\"icon\":\"guide\",\"noCache\":false}}]}";
    }

    /**
     * 测试
     *
     * @return
     */
    @GetMapping("test")
    public AjaxResult test()
    {
        return AjaxResult.success();
    }


}
