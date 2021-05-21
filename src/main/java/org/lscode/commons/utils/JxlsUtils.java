package org.lscode.commons.utils;

import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.jxls.template.SimpleExporter;
import org.lscode.commons.model.TribeMember;

import java.io.*;
import java.util.*;

public class JxlsUtils {

    private static String xml = "F:\\private\\project\\java\\lscode-commons-utils\\src\\main\\resources\\jxls\\tribe.xml";
    private static String xls = "C:\\Users\\llsh\\Desktop\\tribeMembers.xlsx";

    public static List<TribeMember> readBeans(){
        try {
            InputStream inputXML = new BufferedInputStream(new FileInputStream(xml));
            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
            InputStream inputXLS = new BufferedInputStream(new FileInputStream(xls));

            List tribeMembers = new ArrayList();
            Map beans = new HashMap();
            beans.put("tribeMembers", tribeMembers);
//            beans.put("tribeMember", tribeMember);
            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
            return tribeMembers;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void export(List<TribeMember> tribeMembers){
        try(OutputStream os1 = new FileOutputStream("target/simple_export_output1.xls")) {

            List<String> headers = Arrays.asList("uid", "msg");
            SimpleExporter exporter = new SimpleExporter();
            exporter.gridExport(headers, tribeMembers, "uid, msg", os1);

            // now let's show how to register custom template
//            try (InputStream is = JxlsUtils.class.getResourceAsStream("template")) {
//                try (OutputStream os2 = new FileOutputStream("target/simple_export_output2.xlsx")) {
//                    exporter.registerGridTemplate(is);
//                    headers = Arrays.asList("Name", "Payment", "Birth Date");
//                    exporter.gridExport(headers, tribeMembers, "uid, msg", os2);
//                }
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        List<TribeMember> tribeMembers = readBeans();

        for (TribeMember tribeMember : tribeMembers) {
            String msg = RegionTextUtils.getRegionTextLanguage("tribe.msg.award.a", "ar", tribeMember.getName());
            System.out.println(msg);
            tribeMember.setMsg(msg);
            if(msg == null){
                System.out.println(msg);
            }
        }
        export(tribeMembers);
    }

}
