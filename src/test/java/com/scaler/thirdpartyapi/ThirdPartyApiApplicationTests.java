package com.scaler.thirdpartyapi;

import com.scaler.thirdpartyapi.Models.Category;
import com.scaler.thirdpartyapi.Models.Product;
import com.scaler.thirdpartyapi.Repositories.CategoryRepository;
import com.scaler.thirdpartyapi.Repositories.ProductRepository;
import com.scaler.thirdpartyapi.Repositories.Projections.ProductWithIdAndTitle;
import com.scaler.thirdpartyapi.Repositories.Projections.ProductWithIdTitleAndDescription;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ThirdPartyApiApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void test() {

		List<Product> productList = productRepository.query1();

		List<ProductWithIdAndTitle> list = productRepository.query2();

		List<ProductWithIdTitleAndDescription> list2 = productRepository.query3(102L);

		List<Product> list3 = productRepository.nativeSqlQuery();

		List<ProductWithIdAndTitle> list4 = productRepository.nativeSql3(102L);

		for(ProductWithIdAndTitle p : list4) {

			System.out.println(p.getId());

			System.out.println(p.getTitle());
		}

	}

	@Transactional
	@Test
	public void fetchTestCases() {

		System.out.println("Fetching.....");

		Optional<Category> category = categoryRepository.findById(1);

		Category category1 = category.get();

		List<Product> productList = category1.getProducts();

		productList.get(0).getTitle();
	}

}
