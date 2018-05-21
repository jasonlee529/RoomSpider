package cn.lee.spider.data.sql.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lee.spider.data.sql.result.SqlResult;
import cn.lee.spider.data.sql.service.ConfigSourceService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;


/**
 * Created by jason on 18-1-6.
 */
@RestController
@RequestMapping(value = "/data/file")
public class DataFileDownloadController {

    @Autowired
    private ConfigSourceService configSourceService;

    public static final List<HashMap<String, String>> COLUMNS = new ArrayList<HashMap<String, String>>() {{
        this.add(new HashMap<String, String>() {{
            this.put("name", "county");
            this.put("title", "区县");
        }});
        this.add(new HashMap<String, String>() {{
            this.put("name", "region");
            this.put("title", "街道");
        }});
        this.add(new HashMap<String, String>() {{
            this.put("name", "district");
            this.put("title", "小区");
        }});
        this.add(new HashMap<String, String>() {{
            this.put("name", "total_price");
            this.put("title", "总价");
        }});
        this.add(new HashMap<String, String>() {{
            this.put("name", "avg_price");
            this.put("title", "每平米");
        }});
        this.add(new HashMap<String, String>() {{
            this.put("name", "area");
            this.put("title", "面积");
        }});
        this.add(new HashMap<String, String>() {{
            this.put("name", "build_year");
            this.put("title", "建造年代");
        }});
        this.add(new HashMap<String, String>() {{
            this.put("name", "trading_right");
            this.put("title", "交易属性");
        }});
        this.add(new HashMap<String, String>() {{
            this.put("name", "deal_date");
            this.put("title", "成交时间");
        }});
    }};

    @RequestMapping(value = "cn/lee/spider/data/sql/{id}")
    public void one(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        SqlResult result = new SqlResult();
        try {
            Map model = WebUtils.getParametersStartingWith(request, "filter__");
            result = configSourceService.findOne(id, model);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet s1 = workbook.createSheet();
            int rowNum = 0;
            int cellNum = 0;
            Row row = s1.createRow(rowNum);
            for (HashMap<String, String> column : COLUMNS) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(column.get("title"));
                cellNum++;

            }
            rowNum++;
            for (Map<String, Object> data : result.getResult()) {
                row = s1.createRow(rowNum++);
                cellNum = 0;
                for (HashMap<String, String> column : COLUMNS) {
                    Cell cell = row.createCell(cellNum);
                    cell.setCellValue(String.valueOf(data.get(column.get("name"))));
                    cellNum++;
                }
            }
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xlsx");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            result.setCode(SqlResult.ERROR);
            result.setMsg(e.getMessage());
        }
    }
}

