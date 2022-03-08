package WH;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ExportToExcel {
        private String name = "Warehouse";
        private HSSFWorkbook book; //книга
        private HSSFSheet sheet; //лист
        private Row row; //строка
        private Cell cell; //ячейка

        public ExportToExcel(String name, Map<String, Integer> map){
                if (!name.equals("")) this.name = name; //если пользователь не задал имя, то содаем с дефолтным
                createBook();
                fillBook(map);
                saveBook();
        }
        private void createBook(){ //создаем новый экземпляр книги и лист в нем
                book = new HSSFWorkbook();
                sheet = book.createSheet("Storage 1");
        }
        private void saveBook(){
                try {
                        FileOutputStream fos = new FileOutputStream(name+".xls"); //создаем файл
                        book.write(fos); //записываем данные из книги в файл
                        fos.close(); //закрываем поток
                        book.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        private void fillBook(Map<String, Integer> map){
                int rowNum = 0;
                row = sheet.createRow(rowNum);
                sheet.setColumnWidth(0, 4200);
                sheet.setColumnWidth(1, 4200);
                cell = row.createCell(0);
                cell.setCellValue("Наименование"); //создаем шапку столбца
                cell.setCellStyle(style()); //применяем стили
                cell = row.createCell(1);
                cell.setCellValue("Количество ед.");
                cell.setCellStyle(style());
                for (var entry : map.entrySet()) { //пробегаемся по мапе и выгружаем пары ключ-значение, в соседнии ячейки
                        rowNum++; //спискаемся на строку ниже
                        row = sheet.createRow(rowNum);
                        cell = row.createCell(0);
                        cell.setCellValue(entry.getKey());
                        cell = row.createCell(1);
                        cell.setCellValue(entry.getValue());
                }
                rowNum++;
                row = sheet.createRow(rowNum); //создаем строку с результатами
                cell = row.createCell(0);
                cell.setCellValue(map.size()); //всего позиций
                cell.setCellStyle(style());
                cell = row.createCell(1);
                cell.setCellFormula("SUM(B2:B" + (rowNum) + ")"); //их сумма
                cell.setCellStyle(style());

        }
        private CellStyle style(){
                CellStyle style = book.createCellStyle();
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //выбираем тип заполнения
                style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex()); //цвет заливки
                style.setBorderBottom(BorderStyle.THIN); //красим границы ячейки
                style.setBorderLeft(BorderStyle.THIN);
                style.setBorderRight(BorderStyle.THIN);
                style.setBorderTop(BorderStyle.THIN);
                style.setFont(font()); //задаем шрифт
                return style;
        }
        private Font font(){
                Font font = book.createFont();
                font.setFontName("Courier New");
                font.setFontHeightInPoints((short) 10); //размер
                font.setBold(true); //жирный текст
                return font;
        }
    }

