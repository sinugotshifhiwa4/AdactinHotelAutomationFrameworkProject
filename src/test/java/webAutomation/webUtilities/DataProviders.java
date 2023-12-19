package webAutomation.webUtilities;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class DataProviders {


    private static final String EXCEL_PATH = "C:\\Users\\sinut001\\Documents\\Automation Projects\\Automation Projects\\Web\\AdactinHotelAutomationFramework\\AdactinHotelAutomationFramework\\testData\\UserData.xlsx";
    private static final String CREDENTIALS_SHEET = "UserCredentials"; // Replace with your actual sheet name
    private static final String SEARCH_HOTEL_SHEET = "SearchHotel"; // Replace with your actual sheet name

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

    @DataProvider(name = "SearchHotelData")
    public static Object[][] getSearchHotelData() throws IOException {

        XLUtility xlSearchHotelData = new XLUtility(EXCEL_PATH);

        int rowCount = xlSearchHotelData.getRowCount(SEARCH_HOTEL_SHEET);
        int colCount = xlSearchHotelData.getCellCount(SEARCH_HOTEL_SHEET, 1);

        Object[][] searchHotel;

        // Check if there is only one row
        if (rowCount == 1) {
            searchHotel = new Object[1][colCount];
            for (int j = 0; j < colCount; j++) {
                searchHotel[0][j] = xlSearchHotelData.getCellData(SEARCH_HOTEL_SHEET, 1, j);
            }
        } else {
            searchHotel = new Object[rowCount][colCount];
            for (int i = 1; i <= rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    searchHotel[i - 1][j] = xlSearchHotelData.getCellData(SEARCH_HOTEL_SHEET, i, j);
                }
            }
        }

        xlSearchHotelData.close();  // Close the XLUtility to release resources
        return searchHotel;
    }


}
