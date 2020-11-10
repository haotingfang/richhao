package com.example.web;

import com.example.common.core.domain.entity.UserInfo;
import com.example.common.core.domain.vo.UserInfoVo;
import com.example.common.utils.EnumUtil;
import com.example.common.enums.UserType;
import com.example.dao.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class HaoWebApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void contextLoads() {
        UserInfo userInfo = userInfoMapper.selectUserByOpenId("o-qWr5RdDFgYfI2TRx2J8hajjDUM");
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo,userInfoVo);
        System.out.println("-------------------"+userInfoVo.getUserName());
    }



    @Test
    void test() {
        String pass = "admin";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println(hashPass);

        boolean f = bcryptPasswordEncoder.matches("admin",hashPass);
        System.out.println(f);
    }


    @Test
    void co(){
        List<String> shopCodes = Arrays.asList("000001","asz123","ccdp001","CZYDP001","I1002O","DFIYSZYDP","DQSHOP001","FLJDP101","FLJDP102","FLJDP103","FLJDP000103","FLJDP104","FLJDP105","FLJDP001","FLJDP002","冯坏坏","hssDP003","hssDP001","hssDP002","hssDP005","hssDP006","hssDP007","hssDP010","hssDP021","hssDP022","hssDP023","hssDP024","hssDP025","hssDP004","HYHDP003","HZ001","JHDP002","JHDP006","JHDP007","JHDP009","JHDP001","LHDP0001","lx_shop_001","LYDP002","luohao13052353513","lh1451137","lh1451","lh1234","summer001","Lh3456","Test0001","Test0002","Test0003","Test0004","I2101I","bjac","I1003I","I1004I","I5011","I1001I","I1006I","I1007I","ITEST","SHOP001","SHOP002","CS1","00102217","I8653I","ABC12","HFGP003","TESTDP003","TESTDP001","I1005I","I4101I","I9999I","I9191I","ITEST2","I998I","I9998I","ITEST1","ceshiyixiamsg","ceshiyixiamsg1","ceshi001","I1501I","I2401I","I2402I","I2403I","I2711I","CZYDP0002","CZYDP0001","CZYDP0003","I3901I","I3902I","I1701I","DQJXSSHOP01","LHJDP001","INFINITI","I2921I","OY001","OY002","OY003","OY004","A0000A","I4103I","I4102I","R0000R","A1001A","A1002A","A1003A","I1402I","I1401I","I2111I","I3103I","yaoling11","I3102I","I3101I","I3104I","DQSHOP002","I2902I","I2901I","I2301I","HFGP001","I3212I","I66666","I2751I","I1901I","I1911I","I2702I","JHDP004","JHDP005","JHDP003","I2811I","LWJDP001","LWJDP003","I1913I","LXSHOP001","LYDP001","12635263764lufeicode","JHDP008","I2311I","I5101I","I2502I","I2501I","I4002I","I4001I","I4003I","I2701I","I2703I","I3201I","I3202I","I3203I","I2002I","I2001I","I1003O","I5731I","I1103I","I0000I","I1104I","I1101I","I1102I","I4202I","I4206I","I4201I","I1601I","I1602I","I1811I","I2802I","I2801I","I2803I","I3501I","I2201I","I2741I","I2742I","I1801I","I1202I","I1203I","I1204I","I1201I","I9001I","ABC123","I3302I","I3302IC","I3303I","I14321","cyy20180703","I2602I","I2601I","I2911I","I3803I","I3802I","I3801I","ZC2018","LXSHOP002","danhuizhi","danhuizhi11","newshop","newshop02","newshop03","ABC001","I2731I","I1914I","I3001I","I3701I","I3702I","19911903","19911900","19911901","经销商编码测试","19911904","188000","108000","zjq108000","zjq108001","zjq108002","333300","9999000","99990770","9990070","000012","I5801I","I5802I","I5311I","I4302I","I4303I","I4301I","I4402I","I4401I","I4801I","I4701I","I6211I","I4104I","I5602I","I5601I","I5201I","I9003I","I8501I","I6102I","I6101I","I3211I","I4501I","I6801I","I4511I","I6201I","I4902I","I5001I","I9002I","I7501I","I7601I","I6202I","I6203I","I8302I","I4811I","I4611I","I6501I","I4205I","I4204I","I4203I","I4403I","I6401I","I8601I","I5733I","I5732I","I5011I","I8101I","I8102I","I7001I","I8201I","I5301I","I8301I","I5401I","I4521I","I4601I","I4621I","I2721I","ZCW","I2761I","I2302I","I2303I","I1001O","I1302I","I1303I","I1304I","I1301I","ZNJXSSHOP004","ZNTESTJK01","ZNJXSSHOP01","ZNJXS01","ZNJK01","ZNJXSSHOP02","ZNDP1234567890ABCDEF","ZNDP1234567890ABCDE1","ZNDP1234567890ABCDE2","ZNDP1234567890ABCDE3","ZNDP1234567890ABCDE4","ZXDP001","I1912I","HFGDP002","8888");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < shopCodes.size(); i++) {
            if (i == 0) {
                sb.append("'").append(shopCodes.get(i)).append("'");
            } else {
                sb.append(",").append("'").append(shopCodes.get(i)).append("'");
            }
        }
        System.out.println(sb);
    }

}
