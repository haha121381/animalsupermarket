package com.example.animalsupermarket.repository

import com.example.animalsupermarket.model.Comment
import java.util.Date

fun getSampleComments(): Map<Int, List<Comment>> {
    return mapOf(
        1 to listOf(
            Comment(1, 1, "用户1", "https://randomuser.me/api/portraits/women/1.jpg", 5, "猫咪很爱吃，颗粒大小也合适。", Date()),
            Comment(2, 1, "用户2", "https://randomuser.me/api/portraits/men/1.jpg", 4, "不错，性价比高。", Date()),
            Comment(3, 1, "用户3", "https://randomuser.me/api/portraits/women/2.jpg", 5, "无限回购！", Date()),
            Comment(4, 1, "用户4", "https://randomuser.me/api/portraits/men/2.jpg", 5, "发货很快，包装很好。", Date()),
            Comment(5, 1, "用户5", "https://randomuser.me/api/portraits/women/3.jpg", 4, "猫咪吃了之后毛色亮了。", Date()),
            Comment(6, 1, "用户6", "https://randomuser.me/api/portraits/men/3.jpg", 5, "值得推荐！", Date()),
            Comment(7, 1, "用户7", "https://randomuser.me/api/portraits/women/4.jpg", 5, "我家挑食的猫咪也喜欢。", Date()),
            Comment(8, 1, "用户8", "https://randomuser.me/api/portraits/men/4.jpg", 3, "感觉一般，没什么特别的。", Date()),
            Comment(9, 1, "用户9", "https://randomuser.me/api/portraits/women/5.jpg", 5, "日期很新鲜。", Date()),
            Comment(10, 1, "用户10", "https://randomuser.me/api/portraits/men/5.jpg", 5, "客服态度很好。", Date())
        ),
        2 to listOf(
            Comment(11, 2, "用户11", "https://randomuser.me/api/portraits/women/6.jpg", 5, "狗狗很喜欢，肉含量高。", Date()),
            Comment(12, 2, "用户12", "https://randomuser.me/api/portraits/men/6.jpg", 5, "一分钱一分货，品质很好。", Date()),
            Comment(13, 2, "用户13", "https://randomuser.me/api/portraits/women/7.jpg", 4, "价格有点贵，但值得。", Date()),
            Comment(14, 2, "用户14", "https://randomuser.me/api/portraits/men/7.jpg", 5, "吃了之后狗狗精力旺盛。", Date()),
            Comment(15, 2, "用户15", "https://randomuser.me/api/portraits/women/8.jpg", 5, "无限回购的狗粮。", Date()),
            Comment(16, 2, "用户16", "https://randomuser.me/api/portraits/men/8.jpg", 5, "推荐给朋友了。", Date()),
            Comment(17, 2, "用户17", "https://randomuser.me/api/portraits/women/9.jpg", 4, "物流很快。", Date()),
            Comment(18, 2, "用户18", "https://randomuser.me/api/portraits/men/9.jpg", 5, "狗狗的毛发变好了。", Date()),
            Comment(19, 2, "用户19", "https://randomuser.me/api/portraits/women/10.jpg", 5, "客服很专业。", Date()),
            Comment(20, 2, "用户20", "https://randomuser.me/api/portraits/men/10.jpg", 5, "会继续支持。", Date())
        ),
        3 to listOf(
            Comment(21, 3, "用户21", "https://randomuser.me/api/portraits/women/11.jpg", 5, "空间很大，猫咪很喜欢。", Date()),
            Comment(22, 3, "用户22", "https://randomuser.me/api/portraits/men/11.jpg", 4, "防外溅效果很好。", Date()),
            Comment(23, 3, "用户23", "https://randomuser.me/api/portraits/women/12.jpg", 5, "安装很简单。", Date()),
            Comment(24, 3, "用户24", "https://randomuser.me/api/portraits/men/12.jpg", 5, "颜值很高。", Date()),
            Comment(25, 3, "用户25", "https://randomuser.me/api/portraits/women/13.jpg", 4, "除臭效果不错。", Date()),
            Comment(26, 3, "用户26", "https://randomuser.me/api/portraits/men/13.jpg", 5, "质量很好，很结实。", Date()),
            Comment(27, 3, "用户27", "https://randomuser.me/api/portraits/women/14.jpg", 5, "值得购买。", Date()),
            Comment(28, 3, "用户28", "https://randomuser.me/api/portraits/men/14.jpg", 3, "有点小贵。", Date()),
            Comment(29, 3, "用户29", "https://randomuser.me/api/portraits/women/15.jpg", 5, "猫咪用得很开心。", Date()),
            Comment(30, 3, "用户30", "https://randomuser.me/api/portraits/men/15.jpg", 5, "推荐！", Date())
        ),
        4 to listOf(
            Comment(31, 4, "用户31", "https://randomuser.me/api/portraits/women/16.jpg", 5, "很好用，剪指甲很方便。", Date()),
            Comment(32, 4, "用户32", "https://randomuser.me/api/portraits/men/16.jpg", 4, "安全挡片很实用。", Date()),
            Comment(33, 4, "用户33", "https://randomuser.me/api/portraits/women/17.jpg", 5, "刀头很锋利。", Date()),
            Comment(34, 4, "用户34", "https://randomuser.me/api/portraits/men/17.jpg", 5, "不会剪到血线。", Date()),
            Comment(35, 4, "用户35", "https://randomuser.me/api/portraits/women/18.jpg", 4, "性价比很高。", Date()),
            Comment(36, 4, "用户36", "https://randomuser.me/api/portraits/men/18.jpg", 5, "新手也能用。", Date()),
            Comment(37, 4, "用户37", "https://randomuser.me/api/portraits/women/19.jpg", 5, "质量不错。", Date()),
            Comment(38, 4, "用户38", "https://randomuser.me/api/portraits/men/19.jpg", 5, "推荐购买。", Date()),
            Comment(39, 4, "用户39", "https://randomuser.me/api/portraits/women/20.jpg", 5, "小巧方便。", Date()),
            Comment(40, 4, "用户40", "https://randomuser.me/api/portraits/men/20.jpg", 5, "物流很快。", Date())
        ),
        5 to listOf(
            Comment(41, 5, "用户41", "https://randomuser.me/api/portraits/women/21.jpg", 5, "狗狗超爱玩！", Date()),
            Comment(42, 5, "用户42", "https://randomuser.me/api/portraits/men/21.jpg", 5, "非常耐咬。", Date()),
            Comment(43, 5, "用户43", "https://randomuser.me/api/portraits/women/22.jpg", 4, "可以塞零食，狗狗玩不腻。", Date()),
            Comment(44, 5, "用户44", "https://randomuser.me/api/portraits/men/22.jpg", 5, "质量很好。", Date()),
            Comment(45, 5, "用户45", "https://randomuser.me/api/portraits/women/23.jpg", 5, "解放双手。", Date()),
            Comment(46, 5, "用户46", "https://randomuser.me/api/portraits/men/23.jpg", 5, "值得购买。", Date()),
            Comment(47, 5, "用户47", "https://randomuser.me/api/portraits/women/24.jpg", 5, "狗狗玩得很开心。", Date()),
            Comment(48, 5, "用户48", "https://randomuser.me/api/portraits/men/24.jpg", 4, "有点味道，洗洗就好了。", Date()),
            Comment(49, 5, "用户49", "https://randomuser.me/api/portraits/women/25.jpg", 5, "推荐！", Date()),
            Comment(50, 5, "用户50", "https://randomuser.me/api/portraits/men/25.jpg", 5, "发货很快。", Date())
        ),
        6 to listOf(
            Comment(51, 6, "用户51", "https://randomuser.me/api/portraits/women/26.jpg", 5, "狗狗吃了肠胃很好。", Date()),
            Comment(52, 6, "用户52", "https://randomuser.me/api/portraits/men/26.jpg", 4, "草本配方很放心。", Date()),
            Comment(53, 6, "用户53", "https://randomuser.me/api/portraits/women/27.jpg", 5, "性价比高。", Date()),
            Comment(54, 6, "用户54", "https://randomuser.me/api/portraits/men/27.jpg", 5, "狗狗爱吃。", Date()),
            Comment(55, 6, "用户55", "https://randomuser.me/api/portraits/women/28.jpg", 5, "会回购。", Date()),
            Comment(56, 6, "用户56", "https://randomuser.me/api/portraits/men/28.jpg", 5, "推荐。", Date()),
            Comment(57, 6, "用户57", "https://randomuser.me/api/portraits/women/29.jpg", 4, "包装很好。", Date()),
            Comment(58, 6, "用户58", "https://randomuser.me/api/portraits/men/29.jpg", 5, "物流快。", Date()),
            Comment(59, 6, "用户59", "https://randomuser.me/api/portraits/women/30.jpg", 5, "客服好。", Date()),
            Comment(60, 6, "用户60", "https://randomuser.me/api/portraits/men/30.jpg", 5, "满意。", Date())
        ),
        7 to listOf(
            Comment(61, 7, "用户61", "https://randomuser.me/api/portraits/women/31.jpg", 5, "颜值太高了！", Date()),
            Comment(62, 7, "用户62", "https://randomuser.me/api/portraits/men/31.jpg", 5, "猫咪很喜欢。", Date()),
            Comment(63, 7, "用户63", "https://randomuser.me/api/portraits/women/32.jpg", 5, "空间很大。", Date()),
            Comment(64, 7, "用户64", "https://randomuser.me/api/portraits/men/32.jpg", 4, "有点小贵，但值得。", Date()),
            Comment(65, 7, "用户65", "https://randomuser.me/api/portraits/women/33.jpg", 5, "安装方便。", Date()),
            Comment(66, 7, "用户66", "https://randomuser.me/api/portraits/men/33.jpg", 5, "质量很好。", Date()),
            Comment(67, 7, "用户67", "https://randomuser.me/api/portraits/women/34.jpg", 5, "推荐购买。", Date()),
            Comment(68, 7, "用户68", "https://randomuser.me/api/portraits/men/34.jpg", 5, "非常满意。", Date()),
            Comment(69, 7, "用户69", "https://randomuser.me/api/portraits/women/35.jpg", 5, "猫咪用得很开心。", Date()),
            Comment(70, 7, "用户70", "https://randomuser.me/api/portraits/men/35.jpg", 5, "好评！", Date())
        ),
        8 to listOf(
            Comment(71, 8, "用户71", "https://randomuser.me/api/portraits/women/36.jpg", 5, "驱虫效果很好。", Date()),
            Comment(72, 8, "用户72", "https://randomuser.me/api/portraits/men/36.jpg", 5, "一直在用这个牌子。", Date()),
            Comment(73, 8, "用户73", "https://randomuser.me/api/portraits/women/37.jpg", 4, "使用方便。", Date()),
            Comment(74, 8, "用户74", "https://randomuser.me/api/portraits/men/37.jpg", 5, "很放心。", Date()),
            Comment(75, 8, "用户75", "https://randomuser.me/api/portraits/women/38.jpg", 5, "必备药品。", Date()),
            Comment(76, 8, "用户76", "https://randomuser.me/api/portraits/men/38.jpg", 5, "推荐。", Date()),
            Comment(77, 8, "用户77", "https://randomuser.me/api/portraits/women/39.jpg", 5, "发货快。", Date()),
            Comment(78, 8, "用户78", "https://randomuser.me/api/portraits/men/39.jpg", 5, "包装好。", Date()),
            Comment(79, 8, "用户79", "https://randomuser.me/api/portraits/women/40.jpg", 5, "客服专业。", Date()),
            Comment(80, 8, "用户80", "https://randomuser.me/api/portraits/men/40.jpg", 5, "满意。", Date())
        ),
        9 to listOf(
            Comment(81, 9, "用户81", "https://randomuser.me/api/portraits/women/41.jpg", 5, "猫咪喝水变多了。", Date()),
            Comment(82, 9, "用户82", "https://randomuser.me/api/portraits/men/41.jpg", 5, "活水循环很吸引猫咪。", Date()),
            Comment(83, 9, "用户83", "https://randomuser.me/api/portraits/women/42.jpg", 4, "过滤效果不错。", Date()),
            Comment(84, 9, "用户84", "https://randomuser.me/api/portraits/men/42.jpg", 5, "静音效果很好。", Date()),
            Comment(85, 9, "用户85", "https://randomuser.me/api/portraits/women/43.jpg", 5, "值得购买。", Date()),
            Comment(86, 9, "用户86", "https://randomuser.me/api/portraits/men/43.jpg", 5, "App控制很方便。", Date()),
            Comment(87, 9, "用户87", "https://randomuser.me/api/portraits/women/44.jpg", 5, "推荐！", Date()),
            Comment(88, 9, "用户88", "https://randomuser.me/api/portraits/men/44.jpg", 4, "清洗有点麻烦。", Date()),
            Comment(89, 9, "用户89", "https://randomuser.me/api/portraits/women/45.jpg", 5, "质量很好。", Date()),
            Comment(90, 9, "用户90", "https://randomuser.me/api/portraits/men/45.jpg", 5, "好评。", Date())
        ),
        10 to listOf(
            Comment(91, 10, "用户91", "https://randomuser.me/api/portraits/women/46.jpg", 5, "适合室内猫。", Date()),
            Comment(92, 10, "用户92", "https://randomuser.me/api/portraits/men/46.jpg", 5, "猫咪很爱吃。", Date()),
            Comment(93, 10, "用户93", "https://randomuser.me/api/portraits/women/47.jpg", 4, "价格小贵。", Date()),
            Comment(94, 10, "用户94", "https://randomuser.me/api/portraits/men/47.jpg", 5, "吃了之后体重控制得很好。", Date()),
            Comment(95, 10, "用户95", "https://randomuser.me/api/portraits/women/48.jpg", 5, "会回购。", Date()),
            Comment(96, 10, "用户96", "https://randomuser.me/api/portraits/men/48.jpg", 5, "推荐。", Date()),
            Comment(97, 10, "用户97", "https://randomuser.me/api/portraits/women/49.jpg", 5, "发货快。", Date()),
            Comment(98, 10, "用户98", "https://randomuser.me/api/portraits/men/49.jpg", 5, "包装好。", Date()),
            Comment(99, 10, "用户99", "https://randomuser.me/api/portraits/women/50.jpg", 5, "客服好。", Date()),
            Comment(100, 10, "用户100", "https://randomuser.me/api/portraits/men/50.jpg", 5, "满意。", Date())
        )
    )
}