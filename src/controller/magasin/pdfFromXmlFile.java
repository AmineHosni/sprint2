package controller.magasin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
//import org.apache.log4j.BasicConfigurator;
import util.MyConnection;

public class pdfFromXmlFile {

    public static void createTmpFile(String query) {
        try {
            FileInputStream is = new FileInputStream(new File("C:\\jasperreport\\StyledTextReport\\b.jrxml"));
            Scanner sc = new Scanner(is);
            BufferedWriter bw;
            FileWriter fw = new FileWriter("C:\\jasperreport\\StyledTextReport\\tmp.jrxml");
            bw = new BufferedWriter(fw);

            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.contains("<queryString language=\"SQL\"")) {
                    line = "<queryString language=\"SQL\"><![CDATA[" + query + "]]></queryString>";
                }
                System.out.println(line);
                bw.write(line + "\n");

            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CreateMagasin(String query)
            throws JRException, ClassNotFoundException, SQLException {
//        BasicConfigurator.configure();

        pdfFromXmlFile p = new pdfFromXmlFile();
        //createTmpFile("SELECT name,address,owner from magasin where name LIKE '%ma%'");
        createTmpFile(query);
        JasperReport jasperReport = JasperCompileManager.compileReport("C:/jasperreport/StyledTextReport/tmp.jrxml");
        Connection c = MyConnection.getInstance();

        Map<String, Object> parameters = new HashMap();

        JRDataSource dataSource = new JREmptyDataSource();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);

        String dest = "C:/jasperoutput/";
        File outDir = new File(dest);
        outDir.mkdirs();
        //i++;
        System.out.println(LocalDateTime.now().toLocalTime());

        String now = Timestamp.valueOf(LocalDateTime.now()).toString();
        String end = now.substring(now.lastIndexOf(".") + 1, now.length());
        JasperExportManager.exportReportToPdfFile(jasperPrint, dest + "rendu" + end + ".pdf");

        System.out.println("Done!");

    }
}
