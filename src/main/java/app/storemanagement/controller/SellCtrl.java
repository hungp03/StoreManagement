package app.storemanagement.controller;

/**
 *
 * @author Hung Pham
 */
public class SellCtrl {

    public static String generateQuery(String keyword, String searchMethod) {
        String query = """
                       SELECT Product_ID, Product_Name, Unit_Price, Quantity_In_Stock FROM Product
                       WHERE Expiry_Date > GETDATE() and Quantity_In_Stock > 0
                       """;
        if (!keyword.trim().isEmpty()) {
            switch (searchMethod) {
                case "Mã SP" ->
                    query += " and Product_ID LIKE N'%" + keyword.trim() + "%'";
                case "Tên SP" ->
                    query += " and Product_Name LIKE N'%" + keyword + "%' COLLATE Vietnamese_CI_AI";
                default -> {
                }
            }
        }
        return query;
    }
}
