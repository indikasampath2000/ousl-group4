package ousl.group4.webapp.util;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SpreadSheetUtil {

    public static List<String> readSpreadSheet(InputStream inputStream){
        Workbook w;
        List<String> spreadSheet = new ArrayList<String>();
        try {
            w = Workbook.getWorkbook(inputStream);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            // Loop over first 10 column and lines

            for (int j = 0; j < 1; j++) {
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    if (type == CellType.LABEL) {
                        spreadSheet.add(cell.getContents());
                    }

                    if (type == CellType.NUMBER) {
                        spreadSheet.add("0"+cell.getContents());
                    }
                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return spreadSheet;
    }
}
