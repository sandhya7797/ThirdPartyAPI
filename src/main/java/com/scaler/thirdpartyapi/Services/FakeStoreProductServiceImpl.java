package com.scaler.thirdpartyapi.Services;

import com.scaler.thirdpartyapi.Exceptions.CategoryNotExistsException;
import com.scaler.thirdpartyapi.Exceptions.ProductNotExistsException;
import com.scaler.thirdpartyapi.Models.Category;
import com.scaler.thirdpartyapi.Models.Product;
import com.scaler.thirdpartyapi.Repositories.CategoryRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("fakestoreproductservice")
public class FakeStoreProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;

    // To call 3rd party api we have use either RestTemplate or WebClient and single bean is sufficient across application so we store it inside configs module.

    private String API_URL = "https://fakestoreapi.com/products";

    private RestTemplate restTemplate;

    public FakeStoreProductServiceImpl(RestTemplate restTemplate, CategoryRepository categoryRepository) {
        this.restTemplate = restTemplate;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProduct(long id) throws ProductNotExistsException {

//        int a = 1/0; vl be caught by controller advice

        FakeStoreResponseDTO responseDTO = restTemplate.getForObject(API_URL + "/" + id,
                FakeStoreResponseDTO.class);
        if(responseDTO==null) {
            throw new ProductNotExistsException("Product with " + id + " doesn't exists!");
        }
        return convertFakeStoreProductToProduct(responseDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreResponseDTO[] fakeStoreResponseDTOArray =
                restTemplate.getForObject(API_URL, FakeStoreResponseDTO[].class);

        List<Product> productList = new ArrayList<>();

        if(fakeStoreResponseDTOArray != null) {
            for (FakeStoreResponseDTO fakeStoreResponseDTO : fakeStoreResponseDTOArray) {
                productList.add(convertFakeStoreProductToProduct(fakeStoreResponseDTO));
            }
        }

        return productList;
    }

    @Override
    public Product addProduct(Product product) throws CategoryNotExistsException {

        FakeStoreResponseDTO request = new FakeStoreResponseDTO();
        request.setTitle(product.getTitle());
        request.setDescription(product.getDescription());
        request.setPrice(product.getPrice());
        request.setImage(product.getImage());
        request.setCategory(product.getCategory().getName());

        FakeStoreResponseDTO responseDTO = restTemplate.postForObject(API_URL,
                request, FakeStoreResponseDTO.class);
        return convertFakeStoreProductToProduct(responseDTO);
    }

    //FAKESTORE PATCH API IS NOT WORKING
    @Override
    public Product updateProduct(long productId, Product product) {

        FakeStoreResponseDTO fakeStoreProduct = new FakeStoreResponseDTO();

        fakeStoreProduct.setTitle(product.getTitle());
        fakeStoreProduct.setImage(product.getImage());
        fakeStoreProduct.setPrice(product.getPrice());
        fakeStoreProduct.setDescription(product.getDescription());

        Optional<Category> savedCategory = categoryRepository.findByName(product.getCategory().getName());

        if(savedCategory.isPresent()) {
            fakeStoreProduct.setCategory(product.getCategory().getName());
        }

        FakeStoreResponseDTO fakeStoreResponseDTO = restTemplate.patchForObject(API_URL +"/" + productId,
                fakeStoreProduct, FakeStoreResponseDTO.class);

//        HttpEntity<FakeStoreResponseDTO> requestEntity = new HttpEntity<>(product);
//
//        ResponseEntity<FakeStoreResponseDTO> responseDTO = restTemplate.exchange(API_URL + "/" + productId,
//                HttpMethod.PATCH, requestEntity, FakeStoreResponseDTO.class);

        return convertFakeStoreProductToProduct(fakeStoreResponseDTO);
    }

    //PUT
    //Since put() api is having 'void' return type, we are using execute() api which is a low-level api for CRUD operations using RestTemplate.
    @Override
    public Product replaceProduct(long productId, Product product) throws CategoryNotExistsException {

        FakeStoreResponseDTO fakeStoreProduct = new FakeStoreResponseDTO();

        fakeStoreProduct.setTitle(product.getTitle());
        fakeStoreProduct.setImage(product.getImage());
        fakeStoreProduct.setPrice(product.getPrice());
        fakeStoreProduct.setDescription(product.getDescription());

        Category savedCategory = categoryRepository.findByName(product.getCategory().getName()).orElseThrow(
                () -> new CategoryNotExistsException("category with name " + product.getCategory().getName()+
                        " not exists")
        );

        fakeStoreProduct.setCategory(savedCategory.getName());

//        HttpEntity<FakeStoreResponseDTO> requestEntity = new HttpEntity<>(fakeStoreProduct);
//
//        ResponseEntity<FakeStoreResponseDTO> responseDTO = restTemplate.exchange(API_URL +"/" + productId,
//                HttpMethod.PUT, requestEntity, FakeStoreResponseDTO.class);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProduct,FakeStoreResponseDTO.class);
        HttpMessageConverterExtractor<FakeStoreResponseDTO> responseExtractor =
                new HttpMessageConverterExtractor(FakeStoreResponseDTO.class, restTemplate.getMessageConverters());
        FakeStoreResponseDTO responseDTO =  restTemplate.execute(API_URL + "/" + productId, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(responseDTO);
    }

    @Override
    public Product deleteProduct(long productId) {

        ResponseEntity<FakeStoreResponseDTO> responseDTO = restTemplate.exchange(API_URL +"/" + productId,
                HttpMethod.DELETE, null, FakeStoreResponseDTO.class);

        return convertFakeStoreProductToProduct(responseDTO.getBody());
    }

    private Product convertFakeStoreProductToProduct(FakeStoreResponseDTO responseDTO) {

        Product product = new Product();

        product.setId(responseDTO.getId());
        product.setTitle(responseDTO.getTitle());
        product.setDescription(responseDTO.getDescription());
        product.setPrice(responseDTO.getPrice());

        //Product has category object initialize properly
        Category category = new Category();
        category.setName(responseDTO.getCategory());
        product.setCategory(category);

        product.setImage(responseDTO.getImage());

        return product;

    }
}
