package dev.naman.productservice.services;

/*
 * @Repository("fakeStoreProductService")
 * public class FakeStoreProductService implements ProductService {
 * 
 * private FakeStoryProductServiceClient fakeStoryProductServiceClient;
 * 
 * private GenericProductDto
 * convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto
 * fakeStoreProductDto) {
 * 
 * GenericProductDto product = new GenericProductDto();
 * product.setId(fakeStoreProductDto.getId());
 * product.setImage(fakeStoreProductDto.getImage());
 * product.setDescription(fakeStoreProductDto.getDescription());
 * product.setTitle(fakeStoreProductDto.getTitle());
 * product.setPrice(fakeStoreProductDto.getPrice());
 * product.setCategory(fakeStoreProductDto.getCategory());
 * 
 * return product;
 * }
 * 
 * public FakeStoreProductService(FakeStoryProductServiceClient
 * fakeStoryProductServiceClient) {
 * this.fakeStoryProductServiceClient = fakeStoryProductServiceClient;
 * }
 * 
 * 
 * @Override
 * public GenericProductDto createProduct(GenericProductDto product) {
 * return
 * convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.
 * createProduct(product));
 * }
 * 
 * @Override
 * public GenericProductDto getProductById(Long id) throws NotFoundException {
 * return
 * convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.
 * getProductById(id));
 * }
 * 
 * @Override
 * public List<GenericProductDto> getAllProducts() {
 * List<GenericProductDto> genericProductDtos = new ArrayList<>();
 * 
 * for (FakeStoreProductDto fakeStoreProductDto:
 * fakeStoryProductServiceClient.getAllProducts()) {
 * genericProductDtos.add(convertFakeStoreProductIntoGenericProduct(
 * fakeStoreProductDto));
 * }
 * return genericProductDtos;
 * }
 * 
 * @Override
 * public GenericProductDto deleteProduct(Long id) {
 * return
 * convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.
 * deleteProduct(id));
 * }
 * }
 */
