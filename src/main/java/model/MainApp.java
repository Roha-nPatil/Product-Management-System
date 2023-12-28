package model;

import dao.ProductDAO;
import dto.ProductDTO;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    static ProductDAO dao = new ProductDAO();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n*** PRODUCT MANAGEMENT APPLICATION ***");
        home();
    }

    private static void filterProduct() {
        boolean status = true;


        while (status)
        {
            System.out.println("----------------------------");
            System.out.println("1. BY PRICE");
            System.out.println("2. BY CATEGORY");
            System.out.println("3. Main Menu");
            System.out.println("4. Exit");
            System.out.println("----------------------------");
            System.out.print("SELECT OPTION : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    filterByPrice();
                    break;
                case 2:
                    filterByCategory();
                    break;
                case 3:
                    home();
                    break;
                case 4:
                    System.exit(0);
                default:
                    status = false;
                    System.err.println("INVALID INPUT !!");
            }
        }
    }

    private static void filterByCategory() {
        System.out.println("ENTER PRODUCT CATEGORY");
        String productType = sc.next() ;

        List<ProductDTO> productList = dao.byCategory(productType);
        System.out.println("Id \t\t Name \t\t Price \t\t Type ");
        for(ProductDTO p: productList){
            System.out.println(p.getProductId() +" \t "+ p.getProductName() +" \t "+ p.getProductPrice() +" \t "+p.getProductType());
        }
    }

    private static void filterByPrice() {
        System.out.println("ENTER PRODUCT price");
        double productPrice = sc.nextDouble() ;

        List<ProductDTO> productList = dao.byPrice(productPrice);
        System.out.println("Id \t\t Name \t\t Price \t\t Type ");
        for(ProductDTO p: productList){
            System.out.println(p.getProductId() +" \t "+ p.getProductName() +" \t "+ p.getProductPrice() +" \t "+p.getProductType());
        }

    }


    private static void searchProduct() {
        System.out.println("ENTER PRODUCT ID");
        int productId = sc.nextInt();

        ProductDTO p = dao.searchProduct(productId);
        System.out.println("Product_ID\tProduct_Name\tPrice\tType");
        System.out.println("\t"+p.getProductId()+"\t\t\t"+p.getProductName()+"\t\t"+p.getProductPrice()+"\t"+p.getProductType());

    }

    private static void viewProduct() {

        List<ProductDTO> productList= dao.viewProduct();
        System.out.println("Product_ID\tProduct_Name\tPrice\tType");
        for(ProductDTO p : productList)
        {
            System.out.println("\t"+p.getProductId()+"\t"+p.getProductName()+"\t"+p.getProductPrice()+"\t"+p.getProductType());
        }
    }

    private static void removeProduct() {
        System.out.println("ENTER PRODUCT ID");
        int productId = sc.nextInt();

        int n = dao.removeProduct(productId);
        if(n>0)
        {
            System.out.println("\n"+n+" PRODUCT REMOVED !!\n");
        }else{
            System.err.println("\n"+n+" PRODUCT REMOVED !!\n");
        }
    }

    private static void updateProduct() {
        System.out.println("ENTER PRODUCT ID");
        int productId = sc.nextInt();

        System.out.println("ENTER PRODUCT NAME");
        String productName = sc.next();

        System.out.println("ENTER PRODUCT PRICE");
        double productPrice = sc.nextDouble();

        System.out.println("ENTER PRODUCT TYPE");
        String productType = sc.next();

        int n = dao.updateProduct(productId,productName, productPrice, productType);
        if (n>0){
            System.out.println(n+" PRODUCT UPDATED SUCCESSFULLY !!");
        }else {
            System.err.println("PRODUCT IS NOT UPDATED !!");
        }
    }

    private static void insertProduct() {
        System.out.println("ENTER PRODUCT ID");
        int productId = sc.nextInt();
        System.out.println("ENTER PRODUCT NAME");
        String productName = sc.next();
        System.out.println("ENTER PRODUCT PRICE");
        double productPrice = sc.nextDouble();
        System.out.println("ENTER PRODUCT TYPE");
        String productType = sc.next();

        ProductDTO newProduct = new ProductDTO(productId, productName, productPrice, productType);
        int n = dao.insertProduct(newProduct);
        if(n>0){
            System.out.println("PRODUCT IS ADDED SUCCESSFULLY !!");
        }else{
            System.err.println("PRODUCT IS NOT ADDED !!");
        }
    }

    public static void home()
    {
        System.out.println("----------------------------");
        System.out.println("1. INSERT PRODUCT");
        System.out.println("2. UPDATE PRODUCT");
        System.out.println("3. REMOVE PRODUCT");
        System.out.println("4. VIEW PRODUCT");
        System.out.println("5. SEARCH PRODUCT");
        System.out.println("6. FILTER PRODUCT");
        System.out.println("7. EXIT");
        System.out.println("----------------------------");

        System.out.print("SELECT OPTION : ");
        int choice = sc.nextInt();
        switch (choice)
        {
            case 1:
                insertProduct();
                break;
            case 2:
                updateProduct();
                break;
            case 3:
                removeProduct();
                break;
            case 4:
                viewProduct();
                break;
            case 5:
                searchProduct();
            case 6:
                filterProduct();
            case 7:
                System.exit(0);
            default:
                System.err.println("INVALID INPUT");
        }
        home();
    }
}
