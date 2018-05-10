package cn.infisa;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class IdCardFill {

    @Test
    public void start() throws IOException {
        List<String> list = new ArrayList<>();
        Resource resource = new ClassPathResource("id_card.csv");
        try (Stream<String> stream = Files.lines(Paths.get(resource.getURI()))) {
            stream.forEach(s -> {
                String[] strs = StringUtils.split(s, ",");
                String id = strs[13];
                try {
                    Document doc = Jsoup.connect("http://qq.ip138.com/idsearch/index.asp?action=idcard&userid="+id+"&B1=%B2%E9+%D1%AF").get();
                    Elements node = doc.select("table");
                    int size = node.size();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }

//        XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
//        if (workbook == null) {
//            System.out.println("未读取到内容,请检查路径！");
//        } else {
//            Sheet sheet = workbook.getSheetAt(0);
//            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                Row row = sheet.getRow(i);
//                String id = row.getCell(13).getStringCellValue();
//                if (StringUtils.isBlank(id) || id.length() != 15 || id.length() != 18) {
//                    System.out.println("id错误：" + id);
//                } else {
//                    System.out.println(id);
//                }
//            }
//        }
    }
}
