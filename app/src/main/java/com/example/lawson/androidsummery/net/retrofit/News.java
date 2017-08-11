package com.example.lawson.androidsummery.net.retrofit;

import java.util.List;

/**
 * Created by Ian.Lu on 2017/8/10.
 * Project : AndroidSummary
 */

public class News {

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"79e847fc25ffb5efa7d0dc9a13da8245","title":"警惕！新型传销来了！就连地方党政干部也被蒙蔽，你该咋办？","date":"2017-08-10 10:44","category":"头条","author_name":"半月谈","url":"http://mini.eastday.com/mobile/170810104440956.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170810/20170810104440_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170810/20170810104440_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170810/20170810104440_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"60c0c058cb9b04fbb974a41b19032f1b","title":"创始人卸任 韩国乐天彻底告别\u201c辛格浩时代\u201d","date":"2017-08-10 10:45","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170810104541816.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170810/20170810104541_48a6920360e667c3510daed982bc9e5e_1_mwpm_03200403.jpg"},{"uniquekey":"7a323394a6c0f16586c46137dc2be0ff","title":"九寨沟地震牵动人心 彩票公益金贡献救灾力量","date":"2017-08-10 10:38","category":"头条","author_name":"人民网-彩票频道","url":"http://mini.eastday.com/mobile/170810103806827.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170810/20170810103806_c95e555a680fd2664d6674b81ff50b5b_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170810/20170810103806_c95e555a680fd2664d6674b81ff50b5b_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20170810/20170810103806_c95e555a680fd2664d6674b81ff50b5b_1_mwpm_03200403.jpg"},{"uniquekey":"04e5a2e0ffa32e89b887d6c5b066bfeb","title":"九寨沟地震｜熊猫海16名游客被困，救援官兵因垮塌暂时回撤","date":"2017-08-10 10:36","category":"头条","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/170810103613299.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170810/20170810103613_25b28690885427812ff87a242b4e3446_1_mwpm_03200403.jpg"},{"uniquekey":"f5e463592d332c2a7d15cfb68ff64b7d","title":"河北石家庄查处一\u201c黑校车\u201d：五座小面包内竟塞了35名幼童","date":"2017-08-10 10:36","category":"头条","author_name":"河北广播电视台即通客户端","url":"http://mini.eastday.com/mobile/170810103612943.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170810/20170810103612_164c871330816db397e0c4d1eb94bc4f_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20170810/20170810103612_164c871330816db397e0c4d1eb94bc4f_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20170810/20170810103612_164c871330816db397e0c4d1eb94bc4f_2_mwpm_03200403.jpg"},{"uniquekey":"8b18f204fff234827e37949f7cdad2ca","title":"九寨沟地震｜阿坝水务局：15座水库水电站已全部确认安全","date":"2017-08-10 10:36","category":"头条","author_name":"中国水利网","url":"http://mini.eastday.com/mobile/170810103612580.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170810/20170810103612_d5560d02ecbb39875f3996efcd076977_1_mwpm_03200403.jpg"},{"uniquekey":"3bbcc83255768c324af05f03cf621450","title":"九寨沟地震丨女导游深夜徒步40分钟，只为确认一游客安危","date":"2017-08-10 10:36","category":"头条","author_name":"四川新闻网","url":"http://mini.eastday.com/mobile/170810103612340.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170810/20170810103612_781b8320ede0abb404cb4ad598baf665_1_mwpm_03200403.jpg"},{"uniquekey":"9ef0bbff3e7a864848675f43bd40ef05","title":"瞬间传输！中国科学家实现地星间量子隐形传态","date":"2017-08-10 10:25","category":"头条","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/170810102531276.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170810/20170810102531_3fe1ac07f8dfe8605e9f03df9c17be84_1_mwpm_03200403.jpg"},{"uniquekey":"d37882ae6235a92e0cb8dc66cb9b8c74","title":"巴黎车撞哨兵嫌犯落网 法军人或沦为恐袭\u201c靶子\u201d","date":"2017-08-10 10:25","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170810102531175.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170810/20170810102531_6b608020984916eca05dd736c92dce25_1_mwpm_03200403.jpg"},{"uniquekey":"9754f23e489c1f3fe364d7c052f00498","title":"大飞机之后，乌克兰再送中国一大宝贝，俄网友急眼了","date":"2017-08-10 10:10","category":"头条","author_name":"占豪","url":"http://mini.eastday.com/mobile/170810101048574.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170810/20170810101048_845f2ed415064bdee3e844343850f7c0_7_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170810/20170810101048_845f2ed415064bdee3e844343850f7c0_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20170810/20170810101048_845f2ed415064bdee3e844343850f7c0_6_mwpm_03200403.jpg"},{"uniquekey":"785487ec7a6130ed0bf7c70a6bffb00c","title":"郑剑戈当选汕头市人民政府市长(图/简历)","date":"2017-08-10 10:03","category":"头条","author_name":"南方网","url":"http://mini.eastday.com/mobile/170810100351468.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170810/20170810100351_a0602124604de3fb86950f1e4be5d50a_1_mwpm_03200403.jpg"},{"uniquekey":"73ed47cc1b62c14540959201ca6c78b4","title":"中国捐赠安重根铜像落座韩国广场 系击毙伊藤博文抗日义士","date":"2017-08-10 09:55","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170810095515390.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170810/20170810095515_26bf302c18a6cd62e0a3e97fff80cfac_1_mwpm_03200403.jpg"},{"uniquekey":"d7d5ed06a15424959650901b57c619f7","title":"朴槿惠休息一天后出庭油光满面一脸疲惫 崔顺实同日受审","date":"2017-08-10 09:55","category":"头条","author_name":"东方IC","url":"http://mini.eastday.com/mobile/170810095515110.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170810/20170810095515_beafee08f7fa033be66effac16a580eb_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20170810/20170810095515_beafee08f7fa033be66effac16a580eb_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20170810/20170810095515_beafee08f7fa033be66effac16a580eb_5_mwpm_03200403.jpg"},{"uniquekey":"99341eb20a62e54d0136090140bec8af","title":"BOSS直聘就\u201c李文星事件\u201d发布道歉信：坚决落实整改要求","date":"2017-08-10 09:52","category":"头条","author_name":"搜狐","url":"http://mini.eastday.com/mobile/170810095204789.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170810/20170810095204_69244615f45bcd99e4074e2e148160b1_1_mwpm_03200403.jpg"},{"uniquekey":"ff8457695abe5687b7ebe318442b72a6","title":"杜特尔特鼓励警员射杀毒贩队友：杀一个奖200万比索","date":"2017-08-10 09:45","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/170810094511946.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170810/20170810094511_d5f6b24f76a2aeea107a96e9f17737fa_1_mwpm_03200403.jpg"},{"uniquekey":"8cbd00e67a9c3e6903e67a8d3cdc31d3","title":"机器人 25 秒写出九寨沟地震报道，记者这个职业还有希望吗？","date":"2017-08-10 09:43","category":"头条","author_name":"36氪","url":"http://mini.eastday.com/mobile/170810094310718.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170810/20170810094310_8598a96b6c0c23b2d788e042b68074ed_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170810/20170810094310_8598a96b6c0c23b2d788e042b68074ed_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20170810/20170810094310_8598a96b6c0c23b2d788e042b68074ed_1_mwpm_03200403.jpg"},{"uniquekey":"f485ef8199dfedc034799bdfb197aa62","title":"\u201c飞机上有炸弹！\u201d男子航班上编虚假恐怖信息被刑拘","date":"2017-08-10 09:40","category":"头条","author_name":"看看新闻","url":"http://mini.eastday.com/mobile/170810094052045.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170810/20170810094052_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170810/20170810094052_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"2a1389aae6cb0e7ee109504bc241baba","title":"美国目前的敌人都有哪些？中国排名令国人意想不到！","date":"2017-08-10 09:39","category":"头条","author_name":"星语星愿","url":"http://mini.eastday.com/mobile/170810093957688.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170810/20170810_9a0f06952f9f1d570f76bf6462f23e35_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170810/20170810_bbee648f7b520d161bb9808942ee93d5_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170810/20170810_ee3347b881136aa51c9b2d9bf3514af4_cover_mwpm_03200403.jpeg"},{"uniquekey":"a0a13751b02b24dd51cc96e779364132","title":"她们被称为世界上最狂野女兵，最害怕的却不是死亡，内幕难以启齿","date":"2017-08-10 09:39","category":"头条","author_name":"星语星愿","url":"http://mini.eastday.com/mobile/170810093911098.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170810/20170810_527097f716eab29276b603034cb37855_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170810/20170810_b6d93ce9c0820584e523251febcbc964_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20170810/20170810_efe87cd4fe091f8647e94dc0f9f611eb_cover_mwpm_03200403.jpeg"},{"uniquekey":"2999179b70dd4b89bae8205bb48785ac","title":"摩拜单车今晨现大面积故障：打不开","date":"2017-08-10 09:33","category":"头条","author_name":"快科技","url":"http://mini.eastday.com/mobile/170810093350938.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170810/20170810093350_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20170810/20170810093350_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08.imgmini.eastday.com/mobile/20170810/20170810093350_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"156529993b51bd5845a702c6d0b2b0d0","title":"恭喜！57岁唐季礼被曝将娶小25岁女友","date":"2017-08-10 09:31","category":"头条","author_name":"东网","url":"http://mini.eastday.com/mobile/170810093131821.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170810/20170810093131_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"d43b9cf874d753df45659a75a55ec64c","title":"台\u201c友邦\u201d不满邻国获蔡政府高额金援 称要考虑大陆","date":"2017-08-10 09:31","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/170810093131727.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170810/20170810093131_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"377f2ff1a80a11493257541c03f952d2","title":"【幕后强音】央视制片人唐琳：优质节目内容不可替代","date":"2017-08-10 09:31","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/170810093104866.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170810/20170810093104_e23ba4f652d5f96d2603a0549ddf82dc_1_mwpm_03200403.jpg"},{"uniquekey":"ebf257a56eeafab66ea6c7413b494a58","title":"今晨北上广深多地现摩拜单车开锁难 网友扫码扫出乱码","date":"2017-08-10 09:29","category":"头条","author_name":"新民网","url":"http://mini.eastday.com/mobile/170810092915572.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170810/20170810092915_248504b7b85a9a2083bdcbb8befb6ede_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170810/20170810092915_248504b7b85a9a2083bdcbb8befb6ede_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20170810/20170810092915_248504b7b85a9a2083bdcbb8befb6ede_3_mwpm_03200403.jpg"},{"uniquekey":"678dea9ea4eec88383e455c6c5f92a87","title":"菲律宾总统：如子女有人涉及贪腐事件 我将立刻辞职","date":"2017-08-10 09:24","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170810092458484.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170810/20170810092458_17df1cdf5d4e2e43e544367bedba978d_1_mwpm_03200403.jpg"},{"uniquekey":"124464cd40263fdacf4b61aedd007e7f","title":"《人间至味是清欢》佟大为陈乔恩上演\u201c傍晚更懂夜的美\u201d","date":"2017-08-10 09:24","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/170810092432041.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170810/20170810092432_d075efe37ac1994dc6180f43e6483206_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170810/20170810092432_3178197f4f51728cd780c3dde9af1271_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170810/20170810092432_99d31780c32a4e75ade9f08fe2a94471_5_mwpm_03200403.jpg"},{"uniquekey":"8d7f19569684cd2e0ee1c456fdf830da","title":"九寨沟地震｜为让更多旅客撤离，一架航班自愿\u201c延误\u201d3小时","date":"2017-08-10 09:22","category":"头条","author_name":" 成都晚报","url":"http://mini.eastday.com/mobile/170810092242616.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170810/20170810092242_7fd89bc4a250c2e08bc4aa34ffed4988_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170810/20170810092242_7fd89bc4a250c2e08bc4aa34ffed4988_2_mwpm_03200403.jpg"},{"uniquekey":"d86f6d3bad365834599efca4d4b5b339","title":"一期投资超70亿元 无锡洛社新能源特色汽车小镇呼之欲出","date":"2017-08-10 09:17","category":"头条","author_name":"荔枝网","url":"http://mini.eastday.com/mobile/170810091725641.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170810/20170810091725_04dbb823d8aa631246571c989e22b56e_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20170810/20170810091725_04dbb823d8aa631246571c989e22b56e_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20170810/20170810091725_04dbb823d8aa631246571c989e22b56e_1_mwpm_03200403.jpg"},{"uniquekey":"c0f359a26d8848a5884c73565b6b3448","title":"九寨沟地震灾情有无扩大可能？你关心的这八个疑问都在这里","date":"2017-08-10 09:16","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/170810091621526.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170810/20170810091621_a2bb89c7422eb3e8ace8983595873741_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170810/20170810091621_a2bb89c7422eb3e8ace8983595873741_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170810/20170810091621_a2bb89c7422eb3e8ace8983595873741_5_mwpm_03200403.jpg"},{"uniquekey":"ddea22e6be1f0010546c244f9ba68d32","title":"林更新窦骁这些主动加吻戏的演员，除了剧情需要还有个人原因！","date":"2017-08-10 09:15","category":"头条","author_name":"小樱说娱乐","url":"http://mini.eastday.com/mobile/170810091511160.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170810/20170810091511_cf6fd10572afc50f536b73b06b4f98be_7_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20170810/20170810091511_cf6fd10572afc50f536b73b06b4f98be_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20170810/20170810091511_cf6fd10572afc50f536b73b06b4f98be_1_mwpm_03200403.jpg"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"uniquekey":"79e847fc25ffb5efa7d0dc9a13da8245","title":"警惕！新型传销来了！就连地方党政干部也被蒙蔽，你该咋办？","date":"2017-08-10 10:44","category":"头条","author_name":"半月谈","url":"http://mini.eastday.com/mobile/170810104440956.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170810/20170810104440_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170810/20170810104440_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170810/20170810104440_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"60c0c058cb9b04fbb974a41b19032f1b","title":"创始人卸任 韩国乐天彻底告别\u201c辛格浩时代\u201d","date":"2017-08-10 10:45","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170810104541816.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170810/20170810104541_48a6920360e667c3510daed982bc9e5e_1_mwpm_03200403.jpg"},{"uniquekey":"7a323394a6c0f16586c46137dc2be0ff","title":"九寨沟地震牵动人心 彩票公益金贡献救灾力量","date":"2017-08-10 10:38","category":"头条","author_name":"人民网-彩票频道","url":"http://mini.eastday.com/mobile/170810103806827.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170810/20170810103806_c95e555a680fd2664d6674b81ff50b5b_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170810/20170810103806_c95e555a680fd2664d6674b81ff50b5b_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20170810/20170810103806_c95e555a680fd2664d6674b81ff50b5b_1_mwpm_03200403.jpg"},{"uniquekey":"04e5a2e0ffa32e89b887d6c5b066bfeb","title":"九寨沟地震｜熊猫海16名游客被困，救援官兵因垮塌暂时回撤","date":"2017-08-10 10:36","category":"头条","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/170810103613299.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170810/20170810103613_25b28690885427812ff87a242b4e3446_1_mwpm_03200403.jpg"},{"uniquekey":"f5e463592d332c2a7d15cfb68ff64b7d","title":"河北石家庄查处一\u201c黑校车\u201d：五座小面包内竟塞了35名幼童","date":"2017-08-10 10:36","category":"头条","author_name":"河北广播电视台即通客户端","url":"http://mini.eastday.com/mobile/170810103612943.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170810/20170810103612_164c871330816db397e0c4d1eb94bc4f_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20170810/20170810103612_164c871330816db397e0c4d1eb94bc4f_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20170810/20170810103612_164c871330816db397e0c4d1eb94bc4f_2_mwpm_03200403.jpg"},{"uniquekey":"8b18f204fff234827e37949f7cdad2ca","title":"九寨沟地震｜阿坝水务局：15座水库水电站已全部确认安全","date":"2017-08-10 10:36","category":"头条","author_name":"中国水利网","url":"http://mini.eastday.com/mobile/170810103612580.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170810/20170810103612_d5560d02ecbb39875f3996efcd076977_1_mwpm_03200403.jpg"},{"uniquekey":"3bbcc83255768c324af05f03cf621450","title":"九寨沟地震丨女导游深夜徒步40分钟，只为确认一游客安危","date":"2017-08-10 10:36","category":"头条","author_name":"四川新闻网","url":"http://mini.eastday.com/mobile/170810103612340.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170810/20170810103612_781b8320ede0abb404cb4ad598baf665_1_mwpm_03200403.jpg"},{"uniquekey":"9ef0bbff3e7a864848675f43bd40ef05","title":"瞬间传输！中国科学家实现地星间量子隐形传态","date":"2017-08-10 10:25","category":"头条","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/170810102531276.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170810/20170810102531_3fe1ac07f8dfe8605e9f03df9c17be84_1_mwpm_03200403.jpg"},{"uniquekey":"d37882ae6235a92e0cb8dc66cb9b8c74","title":"巴黎车撞哨兵嫌犯落网 法军人或沦为恐袭\u201c靶子\u201d","date":"2017-08-10 10:25","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170810102531175.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170810/20170810102531_6b608020984916eca05dd736c92dce25_1_mwpm_03200403.jpg"},{"uniquekey":"9754f23e489c1f3fe364d7c052f00498","title":"大飞机之后，乌克兰再送中国一大宝贝，俄网友急眼了","date":"2017-08-10 10:10","category":"头条","author_name":"占豪","url":"http://mini.eastday.com/mobile/170810101048574.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170810/20170810101048_845f2ed415064bdee3e844343850f7c0_7_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170810/20170810101048_845f2ed415064bdee3e844343850f7c0_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20170810/20170810101048_845f2ed415064bdee3e844343850f7c0_6_mwpm_03200403.jpg"},{"uniquekey":"785487ec7a6130ed0bf7c70a6bffb00c","title":"郑剑戈当选汕头市人民政府市长(图/简历)","date":"2017-08-10 10:03","category":"头条","author_name":"南方网","url":"http://mini.eastday.com/mobile/170810100351468.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170810/20170810100351_a0602124604de3fb86950f1e4be5d50a_1_mwpm_03200403.jpg"},{"uniquekey":"73ed47cc1b62c14540959201ca6c78b4","title":"中国捐赠安重根铜像落座韩国广场 系击毙伊藤博文抗日义士","date":"2017-08-10 09:55","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170810095515390.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170810/20170810095515_26bf302c18a6cd62e0a3e97fff80cfac_1_mwpm_03200403.jpg"},{"uniquekey":"d7d5ed06a15424959650901b57c619f7","title":"朴槿惠休息一天后出庭油光满面一脸疲惫 崔顺实同日受审","date":"2017-08-10 09:55","category":"头条","author_name":"东方IC","url":"http://mini.eastday.com/mobile/170810095515110.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170810/20170810095515_beafee08f7fa033be66effac16a580eb_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20170810/20170810095515_beafee08f7fa033be66effac16a580eb_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20170810/20170810095515_beafee08f7fa033be66effac16a580eb_5_mwpm_03200403.jpg"},{"uniquekey":"99341eb20a62e54d0136090140bec8af","title":"BOSS直聘就\u201c李文星事件\u201d发布道歉信：坚决落实整改要求","date":"2017-08-10 09:52","category":"头条","author_name":"搜狐","url":"http://mini.eastday.com/mobile/170810095204789.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170810/20170810095204_69244615f45bcd99e4074e2e148160b1_1_mwpm_03200403.jpg"},{"uniquekey":"ff8457695abe5687b7ebe318442b72a6","title":"杜特尔特鼓励警员射杀毒贩队友：杀一个奖200万比索","date":"2017-08-10 09:45","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/170810094511946.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170810/20170810094511_d5f6b24f76a2aeea107a96e9f17737fa_1_mwpm_03200403.jpg"},{"uniquekey":"8cbd00e67a9c3e6903e67a8d3cdc31d3","title":"机器人 25 秒写出九寨沟地震报道，记者这个职业还有希望吗？","date":"2017-08-10 09:43","category":"头条","author_name":"36氪","url":"http://mini.eastday.com/mobile/170810094310718.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170810/20170810094310_8598a96b6c0c23b2d788e042b68074ed_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170810/20170810094310_8598a96b6c0c23b2d788e042b68074ed_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20170810/20170810094310_8598a96b6c0c23b2d788e042b68074ed_1_mwpm_03200403.jpg"},{"uniquekey":"f485ef8199dfedc034799bdfb197aa62","title":"\u201c飞机上有炸弹！\u201d男子航班上编虚假恐怖信息被刑拘","date":"2017-08-10 09:40","category":"头条","author_name":"看看新闻","url":"http://mini.eastday.com/mobile/170810094052045.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170810/20170810094052_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170810/20170810094052_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"2a1389aae6cb0e7ee109504bc241baba","title":"美国目前的敌人都有哪些？中国排名令国人意想不到！","date":"2017-08-10 09:39","category":"头条","author_name":"星语星愿","url":"http://mini.eastday.com/mobile/170810093957688.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170810/20170810_9a0f06952f9f1d570f76bf6462f23e35_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170810/20170810_bbee648f7b520d161bb9808942ee93d5_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170810/20170810_ee3347b881136aa51c9b2d9bf3514af4_cover_mwpm_03200403.jpeg"},{"uniquekey":"a0a13751b02b24dd51cc96e779364132","title":"她们被称为世界上最狂野女兵，最害怕的却不是死亡，内幕难以启齿","date":"2017-08-10 09:39","category":"头条","author_name":"星语星愿","url":"http://mini.eastday.com/mobile/170810093911098.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170810/20170810_527097f716eab29276b603034cb37855_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170810/20170810_b6d93ce9c0820584e523251febcbc964_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20170810/20170810_efe87cd4fe091f8647e94dc0f9f611eb_cover_mwpm_03200403.jpeg"},{"uniquekey":"2999179b70dd4b89bae8205bb48785ac","title":"摩拜单车今晨现大面积故障：打不开","date":"2017-08-10 09:33","category":"头条","author_name":"快科技","url":"http://mini.eastday.com/mobile/170810093350938.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170810/20170810093350_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20170810/20170810093350_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08.imgmini.eastday.com/mobile/20170810/20170810093350_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"156529993b51bd5845a702c6d0b2b0d0","title":"恭喜！57岁唐季礼被曝将娶小25岁女友","date":"2017-08-10 09:31","category":"头条","author_name":"东网","url":"http://mini.eastday.com/mobile/170810093131821.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170810/20170810093131_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"d43b9cf874d753df45659a75a55ec64c","title":"台\u201c友邦\u201d不满邻国获蔡政府高额金援 称要考虑大陆","date":"2017-08-10 09:31","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/170810093131727.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170810/20170810093131_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"377f2ff1a80a11493257541c03f952d2","title":"【幕后强音】央视制片人唐琳：优质节目内容不可替代","date":"2017-08-10 09:31","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/170810093104866.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170810/20170810093104_e23ba4f652d5f96d2603a0549ddf82dc_1_mwpm_03200403.jpg"},{"uniquekey":"ebf257a56eeafab66ea6c7413b494a58","title":"今晨北上广深多地现摩拜单车开锁难 网友扫码扫出乱码","date":"2017-08-10 09:29","category":"头条","author_name":"新民网","url":"http://mini.eastday.com/mobile/170810092915572.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170810/20170810092915_248504b7b85a9a2083bdcbb8befb6ede_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170810/20170810092915_248504b7b85a9a2083bdcbb8befb6ede_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20170810/20170810092915_248504b7b85a9a2083bdcbb8befb6ede_3_mwpm_03200403.jpg"},{"uniquekey":"678dea9ea4eec88383e455c6c5f92a87","title":"菲律宾总统：如子女有人涉及贪腐事件 我将立刻辞职","date":"2017-08-10 09:24","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170810092458484.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170810/20170810092458_17df1cdf5d4e2e43e544367bedba978d_1_mwpm_03200403.jpg"},{"uniquekey":"124464cd40263fdacf4b61aedd007e7f","title":"《人间至味是清欢》佟大为陈乔恩上演\u201c傍晚更懂夜的美\u201d","date":"2017-08-10 09:24","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/170810092432041.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170810/20170810092432_d075efe37ac1994dc6180f43e6483206_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170810/20170810092432_3178197f4f51728cd780c3dde9af1271_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170810/20170810092432_99d31780c32a4e75ade9f08fe2a94471_5_mwpm_03200403.jpg"},{"uniquekey":"8d7f19569684cd2e0ee1c456fdf830da","title":"九寨沟地震｜为让更多旅客撤离，一架航班自愿\u201c延误\u201d3小时","date":"2017-08-10 09:22","category":"头条","author_name":" 成都晚报","url":"http://mini.eastday.com/mobile/170810092242616.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170810/20170810092242_7fd89bc4a250c2e08bc4aa34ffed4988_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170810/20170810092242_7fd89bc4a250c2e08bc4aa34ffed4988_2_mwpm_03200403.jpg"},{"uniquekey":"d86f6d3bad365834599efca4d4b5b339","title":"一期投资超70亿元 无锡洛社新能源特色汽车小镇呼之欲出","date":"2017-08-10 09:17","category":"头条","author_name":"荔枝网","url":"http://mini.eastday.com/mobile/170810091725641.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170810/20170810091725_04dbb823d8aa631246571c989e22b56e_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20170810/20170810091725_04dbb823d8aa631246571c989e22b56e_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20170810/20170810091725_04dbb823d8aa631246571c989e22b56e_1_mwpm_03200403.jpg"},{"uniquekey":"c0f359a26d8848a5884c73565b6b3448","title":"九寨沟地震灾情有无扩大可能？你关心的这八个疑问都在这里","date":"2017-08-10 09:16","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/170810091621526.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170810/20170810091621_a2bb89c7422eb3e8ace8983595873741_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170810/20170810091621_a2bb89c7422eb3e8ace8983595873741_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170810/20170810091621_a2bb89c7422eb3e8ace8983595873741_5_mwpm_03200403.jpg"},{"uniquekey":"ddea22e6be1f0010546c244f9ba68d32","title":"林更新窦骁这些主动加吻戏的演员，除了剧情需要还有个人原因！","date":"2017-08-10 09:15","category":"头条","author_name":"小樱说娱乐","url":"http://mini.eastday.com/mobile/170810091511160.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170810/20170810091511_cf6fd10572afc50f536b73b06b4f98be_7_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20170810/20170810091511_cf6fd10572afc50f536b73b06b4f98be_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20170810/20170810091511_cf6fd10572afc50f536b73b06b4f98be_1_mwpm_03200403.jpg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * uniquekey : 79e847fc25ffb5efa7d0dc9a13da8245
             * title : 警惕！新型传销来了！就连地方党政干部也被蒙蔽，你该咋办？
             * date : 2017-08-10 10:44
             * category : 头条
             * author_name : 半月谈
             * url : http://mini.eastday.com/mobile/170810104440956.html
             * thumbnail_pic_s : http://05.imgmini.eastday.com/mobile/20170810/20170810104440_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg
             * thumbnail_pic_s02 : http://05.imgmini.eastday.com/mobile/20170810/20170810104440_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg
             * thumbnail_pic_s03 : http://05.imgmini.eastday.com/mobile/20170810/20170810104440_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "uniquekey='" + uniquekey + '\'' +
                        "," + "\n" + " title='" + title + '\'' +
                        "," + "\n" + " date='" + date + '\'' +
                        "," + "\n" + " category='" + category + '\'' +
                        "," + "\n" + " author_name='" + author_name + '\'' +
                        "," + "\n" + " url='" + url + '\'' +
                        "," + "\n" + " thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                        "," + "\n" + " thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                        "," + "\n" + " thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                        '}' + "\n";
            }
        }
    }

    @Override
    public String toString() {
        return "News{" +
                "reason='" + reason + '\'' +
                "," + "\n" + "result=" + result.toString() +
                "," + "\n" + " error_code=" + error_code +
                '}' + "\n";
    }
}
