# Ecommerce Store Spring Boot

## About the Application
- This application consists of products stored in database, and CRUD can be implemented.
- This application runs on port 4444.
- It uses MySql as Database.
- It uses Thymeleaf Template Engine for UI.
- It uses UUID format.
- In this application both Controller and RestController is implemented.

## How to Access
### This app contains 4 Controllers
- ProductController (Rest Controller)
  - Create Product
    - Returns GenericProductDto
    - 
      > POST
      > localhost:4444/products/
  - Get all Products
    - Returns List of GenericProductDto
    - 
      > GET
      > localhost:4444/products/
  - Get Product By Id
    - Returns a GenericProductDto
    - 
      > GET
      > localhost:4444/products/{id}
  - Update Product
    - Returns GenericProductDto
    - 
      > PUT
      > localhost:4444/products/{id}
  - Delete Product
    - Returns ResponseEntity of GenericProductDto
    - 
      > DELETE
      > localhost:4444/products/{id}
- CategoryController   (Rest Controller)
  - Get All Products in Category
    - Returns List of GenericProductDto
    -
      > GET
      > localhost:4444/categories/{id}
  - Get All Categories
    - Returns List of CategoryDto
    -  
      > GET
      > localhost:4444/categories/{id}   

- UIProductDBController   (Controller) (Browser)
  - Get all Products 
     - Returns String of Products
    -  
      > GET
      > localhost:4444/productservice/get-all-products 
  - Get Single Product
     - Returns Product
    -  
      > GET
      > localhost:4444/productservice/get-single-product 
      > localhost:4444/productservice/get-all-products 
  - Create Product
     - Redirects to Get all Products
    -  
      > POST
      > localhost:4444/productservice/create-a-product
  - Update Product
     - Redirects to Get all Products
    -  
      > PUT
      > localhost:4444/productservice/update-a-product
      > localhost:4444/productservice/create-a-product
  - Delete Product
     - Redirects to Get all Products
    -  
      > DELETE
      > localhost:4444/productservice/delete-a-product




## Screenshots from Browser  (Controller)
===========================
Get all products.
![GetAllProducts](https://github.com/jsbiresh/productservice-naman/assets/133354225/d662c475-94e6-43f8-8895-af640bffdb92)

Get single product.
![GetSingleProduct](https://github.com/jsbiresh/productservice-naman/assets/133354225/fb8e955d-99c9-49f7-8344-39d81f3b4e5d)

Get all Categories
![GetAllCategories](https://github.com/jsbiresh/productservice-naman/assets/133354225/2f289454-983d-4c10-bc60-bfdd3dbcd49b)

Get in Category
![GetInCategory](https://github.com/jsbiresh/productservice-naman/assets/133354225/08c45ba3-f9fe-4b58-ae21-22a01eefcd05)

Create a product
![CreateAProduct](https://github.com/jsbiresh/productservice-naman/assets/133354225/43522551-87ba-405a-8749-72dea172e409)

Update product
![UpdateAProduct](https://github.com/jsbiresh/productservice-naman/assets/133354225/dae550d7-eded-4c3a-b405-ab80103f873a)

Delete product
![DeleteAProduct](https://github.com/jsbiresh/productservice-naman/assets/133354225/279c6631-0a8a-4a80-ac08-31c9162945f1)


## Screenshots from Postman  (RestController)
===========================
Get all products.
![GetAllProducts](https://github.com/jsbiresh/productservice-naman/assets/133354225/fbcdd28a-2b3d-459e-be0d-fd054ce17cf3)

Get single product.
![GetSingleProduct](https://github.com/jsbiresh/productservice-naman/assets/133354225/156588c0-94c5-449d-87c9-34464ec879ae)

Get all Categories
![GetAllCategories](https://github.com/jsbiresh/productservice-naman/assets/133354225/cc3851fd-ec62-4613-bceb-b81b8d241d3d)

Get in Category
![GetInCategory](https://github.com/jsbiresh/productservice-naman/assets/133354225/3a3a6475-91a0-481b-aeb5-b3d8477f28c2)

Create a product
![CreateAProduct](https://github.com/jsbiresh/productservice-naman/assets/133354225/afaa765b-75b6-457a-a876-f75e5fa1cbed)

Update product
![UpdateAProduct](https://github.com/jsbiresh/productservice-naman/assets/133354225/5c7f12a9-3967-4363-a541-f879ec78e121)

Delete product
![DeleteAProduct](https://github.com/jsbiresh/productservice-naman/assets/133354225/8a09111b-db85-4ac5-bf46-15be22c37742)










 
