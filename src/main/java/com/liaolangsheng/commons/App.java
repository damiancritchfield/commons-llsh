package com.liaolangsheng.commons;

import com.liaolangsheng.commons.utils.FileUtil;
import com.liaolangsheng.commons.utils.Log4jUtil;
import com.liaolangsheng.commons.utils.QRcodeUtil;
import com.liaolangsheng.commons.utils.VelocityUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        genB6Msg();
    }

    public static void exsample(){
        String path = QRcodeUtil.generateQRcode("ddd", "F:\\my_temp", 100, 100);
        FileUtil.openDir(path);

        String url = FileUtil.getFilePathInClassPath("config/log4j.properties");
        System.out.println(url);

        Logger logger = Log4jUtil.getLogger(App.class);

        logger.info("测试日志输出");

        Map<String, Object> map = new HashMap<>();
        map.put("name","廖浪升");
        map.put("say",100);
        String str = VelocityUtil.generate(FileUtil.getFilePathInClassPath("template/hello.vm"), map);
        System.out.println(str);
    }

    public static void genB6Msg(){
        String alertName = "功率超定值";
        String altCatalogId_1 = "388da718-9037-4570-86bf-a0803cd5beb1";
        String altCatalogId_2 = "dca4abcc-f2a0-4457-839c-c677d053797b";
        String msg_1 = "终端的总加功率（采用5分钟平均滑差功率）超过设定的${alertContent.occurPowerTv}kW，就产生功率超定值告警。";
        String msg_2 = "终端的总加功率（采用5分钟平均滑差功率）小于设定的${alertContent.recoverPowerTv}kW，就产生功率超定值恢复告警。";

        Map<String, Object> map = new HashMap<>();
        map.put("alertName", alertName);
        map.put("altCatalogId_1", altCatalogId_1);
        map.put("altCatalogId_2", altCatalogId_2);
        map.put("msg_1", msg_1);
        map.put("msg_2", msg_2);
        genMsg(map);
    }

    public static void genB4Msg(){
        String alertName = "负荷过载";//(B_3)
        String altCatalogId_1 = "472a2b4e-6827-4e61-9eb7-a9603329f36a";
        String altCatalogId_2 = "f7ae8afe-48a5-41c4-ba0f-592ed1f1deaa";
        String msg_1 = "视在功率超过设定的${alertContent.occurApparentPowerTv}kW，且持续时间超过设定的负荷过载判断持续时间${alertContent.occurDurationTime}分钟，产生负荷过载告警。";
        String msg_2 = "视在功率小于设定的${alertContent.recoverApparentPowerTv}kW时，且持续时间超过设定的负荷过载判断持续时间${alertContent.recoverDurationTime}分钟，产生负荷过载恢复告警。";

        Map<String, Object> map = new HashMap<>();
        map.put("alertName", alertName);
        map.put("altCatalogId_1", altCatalogId_1);
        map.put("altCatalogId_2", altCatalogId_2);
        map.put("msg_1", msg_1);
        map.put("msg_2", msg_2);
        genMsg(map);
    }

    public static void genB3Msg(){
        String alertName = "断相";//(B_3)
        String altCatalogId_1 = "6aee1dcc-c12a-4865-91e1-b34acbbd659b";
        String altCatalogId_2 = "ca3e5510-529f-49db-896d-29fe4bfa0d8e";
        String msg_1 = "${alertContent.occurLineVoltage}小于启动电压${alertContent.occurStartingVoltage}V，电压值最大的电压元件为${alertContent.maxLineVoltage}，与最大电压无关的那一相电流${alertContent.occurPhaseCurrent}小于启动电流${alertContent.occurStartingCurrent}A，且持续时间大于设定的判断时间${alertContent.occurDurationTime}分钟，所以判定${alertContent.lossPhaseVoltage}断相。";
        String msg_2 = "#foreach($item in ${alertContent.recoverLineVoltages})#if($velocityCount != 1)、#end$item#end都大于返回电压${alertContent.recoverReturnVoltage}V，且持续时间大于设定的判断恢复时间${alertContent.recoverDurationTime}分钟，所以判定断相恢复。";

        Map<String, Object> map = new HashMap<>();
        map.put("alertName", alertName);
        map.put("altCatalogId_1", altCatalogId_1);
        map.put("altCatalogId_2", altCatalogId_2);
        map.put("msg_1", msg_1);
        map.put("msg_2", msg_2);
        genMsg(map);
    }

    public static void genB1Msg(){
        String alertName = "失压";//(B_1)
        String altCatalogId_1 = "c8e9226e-4ca6-4dcb-8511-5da1d038ce05";
        String altCatalogId_2 = "99ce2a92-5800-4fbc-bcf4-1b8a8ea71817";
        String msg_1 = "${alertContent.occurLineVoltage}小于启动电压${alertContent.occurStartingVoltage}V，电压值最大的电压元件为${alertContent.maxLineVoltage}，与最大电压无关的那一相电流${alertContent.occurPhaseCurrent}大于启动电流${alertContent.occurStartingCurrent}A，且持续时间大于设定的判断时间${alertContent.occurDurationTime}分钟，所以判定${alertContent.lossPhaseVoltage}失压。";
        String msg_2 = "#foreach($item in ${alertContent.recoverLineVoltages})#if($velocityCount != 1)、#end$item#end都大于返回电压${alertContent.recoverReturnVoltage}V，且持续时间大于设定的判断恢复时间${alertContent.recoverDurationTime}分钟，所以判定失压恢复。";

        Map<String, Object> map = new HashMap<>();
        map.put("alertName", alertName);
        map.put("altCatalogId_1", altCatalogId_1);
        map.put("altCatalogId_2", altCatalogId_2);
        map.put("msg_1", msg_1);
        map.put("msg_2", msg_2);
        genMsg(map);
    }

    public static void genMsg(Map<String, Object> map){
        String noticeTplId_1 = UUID.randomUUID().toString();
        String noticeTplId_2 = UUID.randomUUID().toString();
        String ctlNoticeTplId_1 = UUID.randomUUID().toString();
        String ctlNoticeTplId_2 = UUID.randomUUID().toString();

        map.put("noticeTplId_1", noticeTplId_1);
        map.put("noticeTplId_2", noticeTplId_2);
        map.put("ctlNoticeTplId_1", ctlNoticeTplId_1);
        map.put("ctlNoticeTplId_2", ctlNoticeTplId_2);

        String str = VelocityUtil.generate(FileUtil.getFilePathInClassPath("template/msg.vm"), map);
        System.out.println(str);
    }
}
