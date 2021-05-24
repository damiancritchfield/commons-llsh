package org.lscode.commons.utils;

import com.google.common.base.Joiner;
import org.jxls.common.Context;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.jxls.template.SimpleExporter;
import org.jxls.util.JxlsHelper;
import org.lscode.commons.model.TribeMember;

import java.io.*;
import java.util.*;

public class JxlsUtils {

    public static <T> List<T> simpleRead(String inputXmlPath, String inputXlsPath, String beanKey){
        try {
            InputStream inputXML = new BufferedInputStream(new FileInputStream(inputXmlPath));
            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
            InputStream inputXLS = new BufferedInputStream(new FileInputStream(inputXlsPath));

            List<T> beans = new ArrayList<>();
            Map<String, Object> beanMap = new HashMap<>();
            beanMap.put(beanKey, beans);
            XLSReadStatus readStatus = mainReader.read(inputXLS, beanMap);
            if(!readStatus.isStatusOK()){
                System.out.println("readStatus error");
            }
            return beans;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void simpleExport(String outPutPath, List<?> objects, List<String> headers, List<String> properties){
        try(OutputStream os = new FileOutputStream(outPutPath)) {
            SimpleExporter exporter = new SimpleExporter();
            String propertiesStr = Joiner.on(",").join(properties);
            exporter.gridExport(headers, objects, propertiesStr, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportByTemplate(Context context, String templatePath, String outPutPath){
        try(InputStream is = new FileInputStream(templatePath)) {
            try (OutputStream os = new FileOutputStream(outPutPath)) {
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String xml = "F:\\private\\project\\java\\lscode-commons-utils\\src\\main\\resources\\jxls\\tribe.xml";
        String xls = "C:\\Users\\llsh\\Desktop\\tribeMembers.xlsx";
        List<TribeMember> tribeMembers = simpleRead(xml, xls, "tribeMembers");

        assert tribeMembers != null;
        for (TribeMember tribeMember : tribeMembers) {
            String msg = RegionTextUtils.getRegionTextLanguage("tribe.msg.award.a", "ar", tribeMember.getName());
            tribeMember.setMsg(msg);
        }
//        simpleExport("F:\\tmp\\a.xls", tribeMembers, Arrays.asList("uid", "msg"), Arrays.asList("uid", "msg"));

        Context context = new Context();
        context.putVar("tribeMembers", tribeMembers);
        exportByTemplate(context,
                "F:\\private\\project\\java\\lscode-commons-utils\\src\\main\\resources\\jxls\\template.xlsx",
                "F:\\tmp\\a.xls");
    }

}
