//package com.scaler.thirdpartyapi.Controllers;
//
//import com.scaler.thirdpartyapi.Exceptions.ProductNotExistsException;
//import com.scaler.thirdpartyapi.Models.Product;
//import com.scaler.thirdpartyapi.Services.ProductService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class ProductControllerTest {
//
//    @MockBean
//    private ProductService productService;
//
//    @Autowired
//    private ProductController productController;
//
//    //@Test
//    void getAllProducts() {
//
//        //Arrange
//        List<Product> originalProducts = new ArrayList<>();
//
//        Product p1 = new Product();
//        p1.setTitle("P1");
//        Product p2 = new Product();
//        p2.setTitle("P2");
//        Product p3 = new Product();
//        p3.setTitle("P3");
//
//        originalProducts.add(p1);
//        originalProducts.add(p2);
//        originalProducts.add(p3);
//
//        List<Product> productsToPass = new ArrayList<>();//TODO : BUG1 fixed.
//
//        for(Product originalProduct : originalProducts) {
//            Product newProduct = new Product();
//            newProduct.setTitle(originalProduct.getTitle());
//            productsToPass.add(newProduct);
//        }
//
//        //ACT
//        when(productService.getAllProducts()).thenReturn(productsToPass);
//
//        ResponseEntity<List<Product>> response = productController.getAllProducts();
//
//        List<Product> responseProducts = response.getBody();
//
//        //Assert
//
//        assertEquals(originalProducts.size(), response.getBody().size());
//
//        for(int i=0;i<originalProducts.size();i++) {
//            assertEquals(originalProducts.get(i).getTitle(), responseProducts.get(i).getTitle());//FixMe : BUG1 due to pass by reference.
//        }
//
//    }
//
//
//
//   // @Test
//    void getProduct() throws ProductNotExistsException {
//
//        //Arrange
//
//        List<Product> originalProducts = new ArrayList<>();
//
//        Product p1 = new Product();
//        p1.setId(1);
//        p1.setTitle("P1");
//
//        originalProducts.add(p1);
//
//
//        //ACT
//
//        when(productService.getProduct(1)).thenReturn(p1);
//
//        ResponseEntity<Product> response = productController.getProduct(1);
//
//        Product responseProduct = response.getBody();
//
//
//        //Assert
//
//        assertEquals(originalProducts.get(0).getTitle(), responseProduct.getTitle());
//
//
//    }
//
//    //@Test
//    void getSingleProductThrowsProductNotExistsException() throws ProductNotExistsException {
//
//        //Arrange
//
//
//        //Act
//        when(productService.getProduct(10)).thenThrow(new ProductNotExistsException("Product Not Exists"));
//
//        //Assert
//        assertThrows(ProductNotExistsException.class, () -> productController.getProduct(10));
//    }
//
//
//}