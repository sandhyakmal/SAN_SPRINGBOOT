package com.bcafinance.sanspringboot.utils;


import com.bcafinance.sanspringboot.models.UploadCSV.Employee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static boolean isCsv(MultipartFile multipartFile)
    {
        if(!ConstantMessage.CONTENT_TYPE_CSV.equals(multipartFile.getContentType()))
        {
            return false;
        }
        return true;
    }

    public static List<Employee> csvToEmployeeData(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<Employee> lsEmployee = new ArrayList<Employee>();
        try {

            Iterable<CSVRecord> iterRecords = csvParser.getRecords();

            for (CSVRecord record : iterRecords) {
                Employee employee = new Employee();
                employee.setEmployeeName(record.get("EmployeeName"));
                employee.setFirstName(record.get("FisrtName"));
                employee.setLastName(record.get("LastName"));
                employee.setBirthDate(LocalDate.parse(record.get("BirthDate")));
                employee.setAddress(record.get("Address"));
                employee.setGender(record.get("Gender"));
                lsEmployee.add(employee);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return lsEmployee;
        }
    }
}