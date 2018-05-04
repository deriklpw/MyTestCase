package com.derik.demo.c_third;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.derik.demo.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RenameTestActivity extends Activity {

    private Button rename;
    private static String[] imageFormatSet = new String[]{".mp3", ".wav",
            ".3gp", ".aac"};

    private List<String> audioList = new ArrayList<String>();
    private Words words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename_test);
        rename = (Button) findViewById(R.id.rename);
        words = new Words();

        rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFiles("/sdcard/words/");
            }
        });

    }

    // 此为500字时使用，可修改重命名规则
    private void getFiles(String url) {
        File files = new File(url);

        if (files.exists()) {

            File[] file = files.listFiles();
            try {
                for (File f : file) {
                    if (f.isDirectory()) {
                        getFiles(f.getAbsolutePath());
                    } else {
                        if (isAudioFile(f.getPath())) {
//                        audioList.add(f.getPath());
                            String str = f.getName();
                            Log.i("word name", str);
                            if (words.getWordId(str) != -1) {
                                File newName = new File("/sdcard/words/av" + words.getWordId(str) + ".wav");
                                f.renameTo(newName);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Log.e("Error", "Directory is not exit");
        }
    }

    private static boolean isAudioFile(String path) {
        Log.i("file path", path);
        for (String format : imageFormatSet) {
            if (path.endsWith(format)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Created by derik on 16-11-30.
     */
    public static class Words {

        private static final String words[] = {"一", "虫", "上", "头", "六", "红", "画", "人", "云", "江", "二", "牛", "下", "只", "七", "灯", "听", "耳", "日", "花", "三", "鸟", "大", "朵", "八", "妈", "飞", "牙", "月", "叶", "四", "鱼", "小", "棵", "九", "白", "种", "口", "山", "沙", "五", "马", "少", "条", "不", "车", "打", "手", "水", "草", "十", "鸭", "中", "火", "个", "门", "好", "女", "田", "木", "天", "你", "气", "蓝", "光", "爷", "树", "松", "春", "同", "空", "我", "候", "黄", "亮", "奶", "果", "竹", "秋", "学", "世", "他", "风", "绿", "明", "爸", "瓜", "柳", "冬", "老", "界", "她", "晴", "色", "太", "姐", "枝", "桃", "年", "师", "土", "它", "雨", "青", "星", "家", "米", "森", "岁", "习", "地", "们", "雪", "美", "阳", "母", "生", "林", "像", "园", "又", "河", "欢", "跑", "东", "为", "来", "许", "毛", "方", "和", "海", "乐", "跳", "西", "什", "去", "多", "角", "圆", "也", "流", "高", "行", "南", "么", "回", "几", "兔", "点", "再", "清", "兴", "走", "北", "知", "别", "千", "禾", "正", "就", "池", "歌", "立", "前", "道", "从", "有", "苗", "台", "才", "满", "金", "坐", "后", "会", "向", "没", "面", "位", "用", "这", "着", "进", "捉", "说", "最", "孩", "送", "眼", "做", "那", "了", "出", "找", "话", "很", "子", "还", "足", "住", "边", "的", "过", "放", "叫", "每", "衣", "给", "皮", "变", "里", "节", "到", "玩", "声", "片", "干", "让", "发", "把", "总", "成", "开", "活", "吹", "分", "力", "要", "巴", "处", "是", "在", "入", "动", "问", "告", "事", "得", "心", "新", "吃", "书", "夜", "朋", "请", "觉", "热", "工", "作", "快", "笑", "纸", "晚", "友", "原", "跟", "爱", "厂", "业", "真", "看", "笔", "早", "伴", "完", "步", "城", "校", "课", "全", "见", "写", "乌", "自", "可", "石", "市", "商", "本", "对", "想", "字", "时", "己", "起", "都", "乡", "场", "题", "长", "奇", "数", "间", "答", "带", "国", "村", "医", "目", "教", "拉", "男", "身", "张", "岛", "伙", "床", "针", "群", "改", "推", "哥", "体", "夏", "湖", "丁", "钟", "线", "众", "及", "拔", "弟", "脚", "虹", "岸", "所", "桌", "伞", "泪", "格", "排", "父", "嘴", "秀", "桥", "现", "杯", "裙", "汗", "办", "拍", "儿", "脸", "亲", "漂", "今", "瓶", "服", "直", "级", "投", "童", "香", "情", "游", "午", "镜", "布", "化", "语", "实", "近", "两", "汽", "视", "饭", "重", "积", "被", "文", "根", "远", "样", "船", "望", "猪", "量", "往", "包", "加", "李", "短", "百", "司", "闻", "羊", "速", "专", "座", "认", "贝", "深", "万", "机", "净", "鹅", "度", "各", "席", "等", "公", "广", "代", "路", "忘", "鸡", "左", "王", "意", "结", "共", "古", "精", "列", "赶", "蛋", "右", "主", "外", "平", "洗", "猫", "报", "记", "战", "首", "骑", "吓", "京", "整", "扫", "象", "名", "收", "保", "先", "领", "惊", "华", "齐", "读", "蛇", "比", "议", "刀", "常", "查", "怕", "窗", "忙", "唱", "虾", "赛", "闭", "将", "已", "引", "苦", "户", "响", "练", "蚁", "争", "合", "军", "并", "划", "冷", "房", "物", "选", "洞", "取", "关", "队", "些", "提", "疼", "屋", "安", "装", "建", "造", "楼", "施", "电", "影", "网", "球", "照", "相", "运", "消", "举", "值", "转", "采", "管", "治"};
        private Map<String, Integer> map;

        public Words() {
            map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                map.put(words[i], i + 1);
            }
        }

        public int getWordId(String wordStr) {

            if (map != null) {
                if (wordStr != null && !wordStr.equals("") ){
                    if (map.containsKey(wordStr)){
                        return map.get(wordStr);
                    }

                }
            }

            return -1;
        }

    }
}
