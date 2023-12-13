package webAutomation.webUtilities;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class DataProviders {


    private static final String EXCEL_PATH = "C:\\Users\\sinut001\\Documents\\Automation Projects\\Automation Projects\\Web\\AdactinHotelAutomationFramework\\AdactinHotelAutomationFramework\\testData\\UserData.xlsx";
    private static final String CREDENTIALS_SHEET = "UserCredentials"; // Replace with your actual sheet name

    @DataProvider(name = "Credentials")
    public static Object[][] getUserCredentials() throws IOException {

        XLUtility xlCredentialsUtil = new XLUtility(EXCEL_PATH);

        int rowCount = xlCredentialsUtil.getRowCount(CREDENTIALS_SHEET);
        int colCount = xlCredentialsUtil.getCellCount(CREDENTIALS_SHEET, 1);

        Object[][] credentialsData = new Object[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                credentialsData[i - 1][j] = xlCredentialsUtil.getCellData(CREDENTIALS_SHEET, i, j);
            }
        }

        return credentialsData;
    }
}
