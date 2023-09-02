package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name="Data")
    public String[][] getAllData() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//studentData.xlsx";
        XLUtility xl=new XLUtility(path);

        int rowNum=xl.getRowCount("Hoja1");
        int colcount=xl.getCellCount("Hoja1",1);

        String[][] apiData =new String[rowNum][colcount];

        for(int i=1;i<=rowNum;i++)
        {
            for(int j=0;j<colcount;j++)
            {
                apiData[i-1][j]=xl.getCellData("Hoja1", i, j);
            }
        }
        return apiData;
    }


    @DataProvider(name="StudentId")
    public Integer[] getStudentId() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//studentData.xlsx";
        XLUtility xl=new XLUtility(path);

        int rowNum=xl.getRowCount("Hoja1");

        Integer[] apiData =new Integer[rowNum];
        for(int i=1;i<=rowNum;i++)
        {
            int num=Integer.parseInt(xl.getCellData("Hoja1", i, 0));
            apiData[i-1]=num;
        }

        return apiData;

    }
}