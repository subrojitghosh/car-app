package com.app.service.CarsServices;

import com.app.entity.cars.Brand;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BulkUploadBrandNameService {
    public List<Brand> readExcel(String filePath) throws IOException {
        List<Brand>entities=new ArrayList<>();
        FileInputStream fis =new FileInputStream(new File(filePath));
       Workbook workbook =new XSSFWorkbook(fis);
       Sheet sheet=workbook.getSheetAt(0);
      Iterator<Row> rowIterator = sheet.iterator();


      while(rowIterator.hasNext()){
          Row row=rowIterator.next();
          Brand brand=new Brand();
          // Assuming first column is id and second column is name in excel file.
         brand.setBrandName(row.getCell(0).getStringCellValue());
          entities.add(brand);
      }
        workbook.close();
        fis.close();
        return entities;
    }
}
