package dao;

import dto.ProductDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static Connection conn = null;
    static {
        String url= "jdbc:mysql://localhost:3306/j2ee";
        String userName = "root";
        String password = "sql123";

        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int insertProduct(ProductDTO newProduct) {
        int n = 0;
        String insertQuery = "insert into product_info value(?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, newProduct.getProductId());
            pstmt.setString(2, newProduct.getProductName());
            pstmt.setDouble(3, newProduct.getProductPrice());
            pstmt.setString(4, newProduct.getProductType());

            n = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("INVALID INPUT !!");
        }
        return n;
    }

    public int updateProduct(int productId, String productName, double productPrice, String productType) {
        PreparedStatement pstmt = null;
        String query = "update product_info set product_price=?, product_name=?, product_type=? where product_id=?";
        int count = 0;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, productPrice);
            pstmt.setString(2, productName);
            pstmt.setString(3, productType);
            pstmt.setInt(4, productId);
            count = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Invalid Input !!");
        }
        return count;
    }

    public int removeProduct(int productId) {
        PreparedStatement pstmt = null;
        int n = 0;
        String query = "delete from product_info where product_id=?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, productId);

            n = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    public List<ProductDTO> viewProduct() {
        String query = "select * from product_info";
        List<ProductDTO> productList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                int productId = rs.getInt(1);
                String productName = rs.getString(2);
                double productPrice = rs.getDouble(3);
                String productType = rs.getString(4);

                ProductDTO viewProduct = new ProductDTO(productId, productName, productPrice, productType);
                productList.add(viewProduct);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return productList;
    }

    public ProductDTO searchProduct(int productId){
        String query = "select * from product_info where product_id = ?";
        ProductDTO count = new ProductDTO();
        PreparedStatement pstmt= null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,productId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                count = new ProductDTO(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("Invalid Input !!");
        }
        return count;
    }


    public List<ProductDTO> byPrice(double productPrice) {
        String searchQuery = "SELECT * FROM PRODUCT_INFO WHERE PRODUCT_PRICE <= ?";
        List<ProductDTO> productList = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(searchQuery);
            pstmt.setDouble(1, productPrice);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO(rs.getInt(1), rs.getString(2),
                        rs.getDouble(3), rs.getString(4));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("INVALID INPUT !!");
        }

        return productList;
    }


    public List<ProductDTO> byCategory(String productType) {
        String searchQuery = "SELECT * FROM PRODUCT_INFO WHERE PRODUCT_TYPE = ?";
        List<ProductDTO> productList = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(searchQuery);
            pstmt.setString(1, productType);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO(rs.getInt(1), rs.getString(2),
                        rs.getDouble(3), rs.getString(4));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("INVALID INPUT !!");
        }

        return productList;
    }
}
