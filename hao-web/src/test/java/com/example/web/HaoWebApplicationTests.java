package com.example.web;

import com.example.common.core.domain.entity.UserInfo;
import com.example.common.core.domain.vo.UserInfoVo;
import com.example.common.utils.EnumUtil;
import com.example.common.enums.UserType;
import com.example.common.utils.StringUtils;
import com.example.dao.mapper.UserInfoMapper;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Test
    public void bb(){
        // set转换为字符串
        Set<String> set = new HashSet<String>();
        set.add("AA");
        set.add("BB");
        set.add("CC");
        String str = StringUtils.join(set.toArray(), ";");
        System.out.println("特殊数组转换："+str);
    }

    @Test
    public void jj(){
        double data = 3.46;
        //利用字符串格式化的方式实现四舍五入,保留1位小数
        String result1 = String.format("%.1f",data);
        //1代表小数点后面的位数, 不足补0。f代表数据是浮点类型。保留2位小数就是“%.2f”。
        System.out.println(result1);//输出3.0

        //利用BigDecimal来实现四舍五入.保留一位小数
        double result2 = new BigDecimal(data).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        //1代表保留1位小数,保留两位小数就是2
        //BigDecimal.ROUND_HALF_UP 代表使用四舍五入的方式
        System.out.println(result2);//输出3.0

    }

    @Test
    public void testss(){
        List<Integer> l1=  Arrays.asList(2, 4, 3, 9);
        List<Integer> l2= Arrays.asList(5, 6, 4);
        List res = addTwoNumbers(l1,l2);
        System.out.println(res.toString());


    }

//    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//    示例：
//    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//    输出：7 -> 0 -> 8
//    原因：342 + 465 = 807
    public List addTwoNumbers(List<Integer> l1, List<Integer> l2) {
        int l1_length = l1.size();
        int l2_length = l2.size();
        int max_length = l1_length > l2_length ? l1_length : l2_length;
        int min_length = l1_length > l2_length ? l2_length : l1_length;
        boolean flag = l1_length > l2_length ? true :false;
        int jinwei_count = 0 ;
        List res_list = new ArrayList(Collections.nCopies(max_length,1));
        for(int i = max_length-1  ; i >= 0 ;i-- ){
            if(i>min_length-1 && flag){
                res_list.set(i,l1.get(i));
            }else if(i>min_length-1 && !flag){
                res_list.set(i,l2.get(i));
            }else{
                int count = l1.get(i) +l2.get(i) +jinwei_count;
                if(count<10){
                    jinwei_count = 0;
                    res_list.set(i,count);
                }else{
                    jinwei_count = 1;
                    res_list.set(i,count-10);
                }
            }
        }
        return res_list;

    }


    @Test
    public void bbcc(){
//        String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime future30 = now.plusDays(30).plusHours(10);
//        LocalDateTime future7 = now.plusDays(7).plusHours(10);
//        System.out.println("30："+future30.format(formatter));
//        System.out.println("07："+future7.format(formatter));

       String ss= String.format("%.1f",null);
        System.out.println("------------"+ss);

        Map map = new HashMap();
        map.put("sss",null);
        System.out.println("------------"+map);

    }


    @Test
    public void jjjj(){
        String s = "I6502I, I4207I, I3903I, I2603I, I1917I, I2931I, I1916I, I1105I, I8103I, I4206I, I3221I, I6103I, I4903I, I9005I, I1812I, I7511I, I3204I, I1502I, 0021330, 0010, 3333770, 9228770, 9988770, I2421I, I6201I, I1401I, I3105I, I1915I, I1001O, I1004I, I1201I, I5311I, I7601I, I5301I, I5401I, I2301I, I4002I, I3101I, I3701I, I2411I, I9004I, I2703I, I8302I, I2803I, I2002I, I2751I, I1104I, I6203I, I2311I, I2701I, I2911I, I2711I, I2731I, I2721I, I8601I, I5201I, I8102I, I4203I, I2401I, I1913I, I6102I, I1002O, I1911I, I2811I, I6801I, I6202I, I5601I, I5001I, I4621I, I8301I, I4401I, I4302I, I4301I, I4102I, I8101I, I1005I, I1301I, I3102I, I1701I, I2403I, I3203I, I4101I, I4801I, I3501I, I3202I, I3201I, I1103I, I1101I, I8201I, I2802I, I2201I, I1901I, I1601I, I1501I, I2101I, I1801I, I1001I, I5801I, I2302I, I1602I, I3802I, I2111I, I2902I, I1302I, I5802I, I2901I, I2801I, I4501I, I2303I, I2741I, I3902I, I3901I, I2501I, I1912I, I3104I, I5011I, I2921I, I2702I, I1006I, I4611I, I4521I, I4511I, I1811I, I1202I, I4902I, I4601I, I4202I, I4001I, I3302I, I3103I, I1003I, I4003I, I4303I, I3212I, I4104I, I1304I, I9003I, I1003O, I4403I, I3702I, I2742I, I6101I, I3303I, I2761I, I1303I, I4205I, I1203I, I1914I, I3801I, I1204I, I1007I, I9002I, I2602I, I4701I, I3803I, I2601I, I2001I, I1102I, I4103I, I4204I, I2402I, I6501I, I1402I, I5602I, I5101I, I4811I, I5732I, I6401I, I2502I, I6211I, I7501I, I7001I, I5733I, I4402I, I3001I, 8888";
        String[] ss = s.split(",");
        StringBuffer sb =new StringBuffer();
        for(String sss:ss){
            sb.append("'"+sss.trim()+"',");
        }
        System.out.println(sb);

        String q = "series-2, series-1668, series-1476, series-1266, series-1176, series-2818, series-2817, series-2055-n, series-2816, series-50312";
        String[] qq = q.split(",");
        StringBuffer sb1 =new StringBuffer();
        for(String qqq:qq){
            sb1.append("'"+qqq.trim()+"',");
        }
        System.out.println(sb1);
    }


    @Test
    public void linkTest(){
        String s = "4193 with words";
        s = s.trim();
        if (s.length() <= 0) {
            return ;
        }
        char[] ss = s.toCharArray();
        boolean f_flag = false;
        for (int i = 0; i < ss.length; i++) {
            char c = ss[i];
            if (i == 0) {
                f_flag = checkzf(c);
                if(f_flag){
                    s =s.substring(1,ss.length);
                }
            }
            if(!f_flag || i != 0){
                boolean flag = checkChar(c);
                if(!flag){
                    s = s.substring(0,i);
                    break;
                }
            }
        }
        Integer i =Integer.parseInt(s);
       Integer res = f_flag ? -i : i;
        System.out.println(res);
    }

    @Test
    public Integer myAtoi() {
        String s = "-42";
        s = s.trim();
        if (s.length() <= 0) {
            return 0;
        }
        char[] ss = s.toCharArray();
        boolean f_flag = false;
        for (int i = 0; i < ss.length; i++) {
            char c = ss[i];
            int begin = 0;
            if (i == 0) {
                f_flag = checkzf(c);
            }
            if(f_flag){
                begin = 1;
            }else{
                boolean flag = checkChar(c);
                if(!flag){
                    s = s.substring(0,i);
                }
            }
        }
        Integer i =Integer.parseInt(s);
        return f_flag ? -i : i;
    }

    private Boolean checkzf(char c){
        if(c=='-' ){
            return true;
        }
        return false;
    }

    private Boolean checkChar(char c){
       if(c >='0' && c <= '9' ){
            return true;
        }
        return false;

    }

}
