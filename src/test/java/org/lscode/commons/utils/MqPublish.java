package org.lscode.commons.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.net.URISyntaxException;

public class MqPublish {

    private static String topic = "zd/12345678910/data";
    private static String content = "发送的消息啊";
    private static int qos = 2;
    private static String broker = "tcp://119.23.249.51:7000";
    private static String clientId = "zd/12345678910";

    private static String max = "{\"ClientId\":\"zd/100\",\"Opt\":\"SendData\",\"Cmd\":\"Max\",\"Time\":\"17-01-01 01:30\",\"Data\":{\"Max_U\":0,\"Max_Ua\":0,\"Max_Ub\":0,\"Max_Uc\":0,\"Max_Uab\":0,\"Max_Ubc\":0,\"Max_Uca\":0,\"Max_I\":0,\"Max_Ia\":0,\"Max_Ib\":0,\"Max_Ic\":0,\"Max_Pa\":0,\"Max_Pb\":0,\"Max_Pc\":0,\"Max_Qa\":0,\"Max_Qb\":0,\"Max_Qc\":0,\"Max_Sa\":0,\"Max_Sb\":0,\"Max_Sc\":0,\"Max_PF\":10,\"Max_PFa\":10,\"Max_PFb\":10,\"Max_PFc\":10,\"Max_Dmd_P\":0,\"Max_Dmd_Q\":0,\"Max_Dmd_S\":0,\"Min_Ua\":0,\"Min_Ub\":0,\"Min_Uc\":0,\"Min_Uab\":0,\"Min_Ubc\":0,\"Min_Uca\":0,\"Min_Ia\":0,\"Min_Ib\":0,\"Min_Ic\":0,\"Min_P\":0,\"Min_Q\":0,\"Min_S\":0,\"Min_PF\":10,\"Min_Dmd_P\":0,\"Min_Dmd_Q\":0,\"Min_Dmd_S\":0}}";
    private static String energy = "{\"ClientId\":\"zd/100\",\"Opt\":\"SendData\",\"Cmd\":\"Energy\",\"Time\":\"17-01-01 01:30\",\"Data\":{\"imp_P\":0,\"exp_P\":0,\"imp_Q\":0,\"exp_Q\":0,\"total_P\":0,\"total_Q\":0}}";
    private static String real = "{\"ClientId\":\"zd/100\",\"Opt\":\"SendData\",\"Cmd\":\"Real\",\"Time\":\"18-01-01 01:31\",\"Data\":{\"Freq\":0,\"Ua\":0,\"Ub\":0,\"Uc\":0,\"Upavg\":0,\"Uab\":0,\"Ubc\":0,\"Uca\":0,\"Ulavg\":0,\"Ia\":0,\"Ib\":0,\"Ic\":0,\"Iavg\":0,\"In\":0,\"Pa\":0,\"Pb\":0,\"Pc\":0,\"P\":0,\"Qa\":0,\"Qb\":0,\"Qc\":0,\"Q\":0,\"Sa\":0,\"Sb\":0,\"Sc\":0,\"S\":0,\"PFa\":1000,\"PFb\":1000,\"PFc\":1000,\"PF\":1000,\"UNBL_U\":0,\"UNBL_I\":0,\"Dmd_P\":0,\"Dmd_Q\":0,\"Dmd_S\":0}}";
    private static String harm = "{\"ClientId\":\"zd/100\",\"Cmd\":\"Harm\",\"Opt\":\"SendData\",\"Time\":\"19-01-01 01:30\",\"Data\":{\"THD_Ia\":0,\"THD_Ib\":0,\"THD_Ic\":0,\"THD_Ua\":0,\"THD_Ub\":0,\"THD_Uc\":0,\"TEHD_Ia\":0,\"TEHD_Ib\":0,\"TEHD_Ic\":0,\"TEHD_Ua\":0,\"TEHD_Ub\":0,\"TEHD_Uc\":0,\"TOHD_Ia\":0,\"TOHD_Ib\":0,\"TOHD_Ic\":0,\"TOHD_Ua\":0,\"TOHD_Ub\":0,\"TOHD_Uc\":0,\"THD_Iavg\":0,\"THD_Uavg\":0}}";
    private static String harmUa = "{\"ClientId\":\"zd/100\",\"Cmd\":\"Harm\",\"Opt\":\"SendData\",\"Time\":\"20-01-01 01:30\",\"Data\":{\"HD_Ua2\":0,\"HD_Ua3\":0,\"HD_Ua4\":0,\"HD_Ua5\":0,\"HD_Ua6\":0,\"HD_Ua7\":0,\"HD_Ua8\":0,\"HD_Ua9\":0,\"HD_Ua10\":0,\"HD_Ua11\":0,\"HD_Ua12\":0,\"HD_Ua13\":0,\"HD_Ua14\":0,\"HD_Ua15\":0,\"HD_Ua16\":0,\"HD_Ua17\":0,\"HD_Ua18\":0,\"HD_Ua19\":0,\"HD_Ua20\":0,\"HD_Ua21\":0,\"HD_Ua22\":0,\"HD_Ua23\":0,\"HD_Ua24\":0,\"HD_Ua25\":0,\"HD_Ua26\":0,\"HD_Ua27\":0,\"HD_Ua28\":0,\"HD_Ua29\":0,\"HD_Ua30\":0,\"HD_Ua31\":0}}";
    private static String harmUb = "{\"ClientId\":\"zd/100\",\"Cmd\":\"Harm\",\"Opt\":\"SendData\",\"Time\":\"21-01-01 01:30\",\"Data\":{\"HD_Ub2\":0,\"HD_Ub3\":0,\"HD_Ub4\":0,\"HD_Ub5\":0,\"HD_Ub6\":0,\"HD_Ub7\":0,\"HD_Ub8\":0,\"HD_Ub9\":0,\"HD_Ub10\":0,\"HD_Ub11\":0,\"HD_Ub12\":0,\"HD_Ub13\":0,\"HD_Ub14\":0,\"HD_Ub15\":0,\"HD_Ub16\":0,\"HD_Ub17\":0,\"HD_Ub18\":0,\"HD_Ub19\":0,\"HD_Ub20\":0,\"HD_Ub21\":0,\"HD_Ub22\":0,\"HD_Ub23\":0,\"HD_Ub24\":0,\"HD_Ub25\":0,\"HD_Ub26\":0,\"HD_Ub27\":0,\"HD_Ub28\":0,\"HD_Ub29\":0,\"HD_Ub30\":0,\"HD_Ub31\":0}}";
    private static String harmUc = "{\"ClientId\":\"zd/100\",\"Cmd\":\"Harm\",\"Opt\":\"SendData\",\"Time\":\"22-01-01 01:30\",\"Data\":{\"HD_Uc2\":0,\"HD_Uc3\":0,\"HD_Uc4\":0,\"HD_Uc5\":0,\"HD_Uc6\":0,\"HD_Uc7\":0,\"HD_Uc8\":0,\"HD_Uc9\":0,\"HD_Uc10\":0,\"HD_Uc11\":0,\"HD_Uc12\":0,\"HD_Uc13\":0,\"HD_Uc14\":0,\"HD_Uc15\":0,\"HD_Uc16\":0,\"HD_Uc17\":0,\"HD_Uc18\":0,\"HD_Uc19\":0,\"HD_Uc20\":0,\"HD_Uc21\":0,\"HD_Uc22\":0,\"HD_Uc23\":0,\"HD_Uc24\":0,\"HD_Uc25\":0,\"HD_Uc26\":0,\"HD_Uc27\":0,\"HD_Uc28\":0,\"HD_Uc29\":0,\"HD_Uc30\":0,\"HD_Uc31\":0}}";
    private static String harmIa = "{\"ClientId\":\"zd/100\",\"Cmd\":\"Harm\",\"Opt\":\"SendData\",\"Time\":\"23-01-01 01:30\",\"Data\":{\"HD_Ia2\":0,\"HD_Ia3\":0,\"HD_Ia4\":0,\"HD_Ia5\":0,\"HD_Ia6\":0,\"HD_Ia7\":0,\"HD_Ia8\":0,\"HD_Ia9\":0,\"HD_Ia10\":0,\"HD_Ia11\":0,\"HD_Ia12\":0,\"HD_Ia13\":0,\"HD_Ia14\":0,\"HD_Ia15\":0,\"HD_Ia16\":0,\"HD_Ia17\":0,\"HD_Ia18\":0,\"HD_Ia19\":0,\"HD_Ia20\":0,\"HD_Ia21\":0,\"HD_Ia22\":0,\"HD_Ia23\":0,\"HD_Ia24\":0,\"HD_Ia25\":0,\"HD_Ia26\":0,\"HD_Ia27\":0,\"HD_Ia28\":0,\"HD_Ia29\":0,\"HD_Ia30\":0,\"HD_Ia31\":0}}";
    private static String harmIb = "{\"ClientId\":\"zd/100\",\"Cmd\":\"Harm\",\"Opt\":\"SendData\",\"Time\":\"24-01-01 01:30\",\"Data\":{\"HD_Ib2\":0,\"HD_Ib3\":0,\"HD_Ib4\":0,\"HD_Ib5\":0,\"HD_Ib6\":0,\"HD_Ib7\":0,\"HD_Ib8\":0,\"HD_Ib9\":0,\"HD_Ib10\":0,\"HD_Ib11\":0,\"HD_Ib12\":0,\"HD_Ib13\":0,\"HD_Ib14\":0,\"HD_Ib15\":0,\"HD_Ib16\":0,\"HD_Ib17\":0,\"HD_Ib18\":0,\"HD_Ib19\":0,\"HD_Ib20\":0,\"HD_Ib21\":0,\"HD_Ib22\":0,\"HD_Ib23\":0,\"HD_Ib24\":0,\"HD_Ib25\":0,\"HD_Ib26\":0,\"HD_Ib27\":0,\"HD_Ib28\":0,\"HD_Ib29\":0,\"HD_Ib30\":0,\"HD_Ib31\":0}}";
    private static String harmIc = "{\"ClientId\":\"zd/100\",\"Cmd\":\"Harm\",\"Opt\":\"SendData\",\"Time\":\"25-01-01 01:30\",\"Data\":{\"HD_Ic2\":0,\"HD_Ic3\":0,\"HD_Ic4\":0,\"HD_Ic5\":0,\"HD_Ic6\":0,\"HD_Ic7\":0,\"HD_Ic8\":0,\"HD_Ic9\":0,\"HD_Ic10\":0,\"HD_Ic11\":0,\"HD_Ic12\":0,\"HD_Ic13\":0,\"HD_Ic14\":0,\"HD_Ic15\":0,\"HD_Ic16\":0,\"HD_Ic17\":0,\"HD_Ic18\":0,\"HD_Ic19\":0,\"HD_Ic20\":0,\"HD_Ic21\":0,\"HD_Ic22\":0,\"HD_Ic23\":0,\"HD_Ic24\":0,\"HD_Ic25\":0,\"HD_Ic26\":0,\"HD_Ic27\":0,\"HD_Ic28\":0,\"HD_Ic29\":0,\"HD_Ic30\":0,\"HD_Ic31\":0}}";

    public static void main(String[] args) throws Exception {
        MemoryPersistence persistence = new MemoryPersistence();

        Logger logger = Log4jUtils.getLogger(MqPublish.class);
        logger.info("开始发送mqtt消息");

        try{
            for (int i = 0; i < 10000; i++) {
                publish(persistence, content);
                logger.info("发送完成：i=" + i);
                Thread.sleep(100);
            }
        } catch (Exception e){
            logger.error(e.getMessage(), e);
        }
	}

	public static void publishMsg(MqttClient sampleClient, String msg) throws MqttException {
        MqttMessage message = new MqttMessage(msg.getBytes());
        message.setQos(qos);
        sampleClient.publish(topic, message);
    }

    public static void publish(MemoryPersistence persistence, String content) throws MqttException {
        MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setUserName("test");
        connOpts.setPassword("test".toCharArray());
        connOpts.setCleanSession(true);
        sampleClient.connect(connOpts);

        publishMsg(sampleClient, max);
        publishMsg(sampleClient, energy);
        publishMsg(sampleClient, real);
        publishMsg(sampleClient, harm);
        publishMsg(sampleClient, harmUa);
        publishMsg(sampleClient, harmUb);
        publishMsg(sampleClient, harmUc);
        publishMsg(sampleClient, harmIa);
        publishMsg(sampleClient, harmIb);
        publishMsg(sampleClient, harmIc);

        sampleClient.disconnect();
    }
}
