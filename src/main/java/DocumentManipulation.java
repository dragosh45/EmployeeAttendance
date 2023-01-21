
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;


///TODO :

public class DocumentManipulation {
    File document;
    public DocumentManipulation(File document) {
        this.document = document;
    }
    public File getDocument() {
        return this.document;
    }
    public File manipulateDocumentITM(File xlFile) {
        HashMap<Integer, ArrayList<Date>> hashMap = new HashMap<>();
        try {
            FileInputStream inFile = new FileInputStream(xlFile);
            Workbook workbookInFile = new XSSFWorkbook(inFile);
            Sheet sheetInFile = workbookInFile.getSheetAt(0);
            Iterator<Row> rowIteratorInFile = sheetInFile.iterator();
            int rowCountInFile = 5, key = 0, countEmpty = 0, rowCountModelFile = 10;
            while (rowIteratorInFile.hasNext()) {
                ArrayList<Date> arrayList = new ArrayList<>();
                Row rowInFile = rowIteratorInFile.next();
                if (rowInFile.getRowNum() == rowCountInFile) {
                    countEmpty = 0;
                    key++;
                    Iterator<Cell> cellIteratorInFile = rowInFile.cellIterator();
                    arrayList = new ArrayList<>();
                    while (cellIteratorInFile.hasNext()) {
                        Cell cell = cellIteratorInFile.next();
                        if ((cell.getCellType() == CellType.NUMERIC) && (cell.getColumnIndex() != 0)) {
                            Date data = cell.getDateCellValue();
                            arrayList.add(data);
                        }
                    }
                    hashMap.put(key, arrayList);
                    rowCountInFile = rowCountInFile + 4;
                }
            }
            inFile.close();
            for (Integer I : hashMap.keySet()) {
                ArrayList<Date> replaceArray = hashMap.get(I);
                System.out.println("***");
                for (int i = 0; i < replaceArray.size(); i++) {
                    System.out.println(replaceArray.get(i).getHours());
                }
            }
            String modelPath = "/home/h1dr0/Documents/unimineral/Model foaie de prezenta (another copy).xlsx";
            FileInputStream modelFile = new FileInputStream(modelPath);
            Workbook workbookModel = new XSSFWorkbook(modelFile);
            Sheet sheetModelFile = workbookModel.getSheetAt(0);
            Iterator<Row> rowIteratorModelFile = sheetModelFile.iterator();
            ArrayList<Date> replaceArray2 = new ArrayList<>();
            //for (Integer I : hashMap.keySet()) {
                Iterator it = hashMap.entrySet().iterator();
                while (rowIteratorModelFile.hasNext()) {
                    Row rowModelFile = rowIteratorModelFile.next();
                    //Iterator<Cell> cellIteratorModelFile = rowModelFile.cellIterator();
                    //Iterator<Integer>
                    if (rowModelFile.getRowNum() == rowCountModelFile) {
                        //replaceArray2 = hashMap.get(I);
                        Iterator<Cell> cellIteratorModelFile = rowModelFile.cellIterator();
                        Map.Entry pair = (Map.Entry)it.next();
                        replaceArray2 = (ArrayList<Date>) pair.getValue();
                        while (cellIteratorModelFile.hasNext()) {
                            Cell cell = cellIteratorModelFile.next();
                            if (replaceArray2.size() != 0) {
                                for (int i = 0; i < replaceArray2.size(); i++) {
                                    if ((replaceArray2.get(i).getHours() != 0) && replaceArray2.get(i).toString() != "" && (cell.getColumnIndex() != 18)) {
                                        //  if ((cell.getCellType() == CellType.NUMERIC)) {
                                        cell.setCellValue(8);
                                        //System.out.println("cell: "+cell.getNumericCellValue());
                                    }
                                    else {
                                        cell.setCellValue(" ");
                                    }
                                }
                            } else {
                                cell.setCellValue(" ");
                            }
                            if(cell.getCellType() == CellType.NUMERIC) {
                                System.out.println("cell: " + cell.getNumericCellValue());
                            }
                            else if(cell.getCellType() == CellType.STRING) {
                                System.out.println("cell: " + cell.getStringCellValue());
                            }
                        }
                    rowCountModelFile = rowCountModelFile + 3;
                    }
                }
            modelFile.close();
            //}
            FileOutputStream outputStream = new FileOutputStream("/home/h1dr0/Documents/unimineral/generate.xlsx",false);
            workbookModel.write(outputStream);
            outputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return xlFile;
    }
    public File manipulateDocumentReal(File xlFile) {
        try
        {
            FileInputStream file = new FileInputStream(xlFile);

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case NUMERIC:
                            cell.setCellValue(100);
                            //System.out.print(cell.getNumericCellValue() + "t");
                            break;
                        case STRING:
                            cell.setCellValue("*modification*");
                            //System.out.print(cell.getStringCellValue() + "t");
                            break;
                    }
                }
                System.out.println("");
            }
            file.close();
            FileOutputStream outputStream = new FileOutputStream("/home/h1dr0/Documents/unimineral/gg.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return xlFile;
    }
}
