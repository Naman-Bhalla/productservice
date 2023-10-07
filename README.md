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




## Screenshots from Browser and Postman
Get all products.
![Get all products](https://github.com/jsbiresh/productservice-naman/blob/901a5041153c4665693f2b4a7c80c1d5cd836e49/Get%20all%20products.png)
![Get single product](https://github.com/jsbiresh/productservice-naman/blob/901a5041153c4665693f2b4a7c80c1d5cd836e49/Get%20single%20Product.png)
![Get all categories](https://github.com/jsbiresh/productservice-naman/blob/901a5041153c4665693f2b4a7c80c1d5cd836e49/Get%20all%20categories.png)
![Get in category](https://github.com/jsbiresh/productservice-naman/blob/901a5041153c4665693f2b4a7c80c1d5cd836e49/Get%20in%20Category.png)
![CreateAProduct]((https://raw.githubusercontent.com/jsbiresh/productservice-naman/task/CreateAProduct.png)
![Update product](https://github.com/jsbiresh/productservice-naman/blob/901a5041153c4665693f2b4a7c80c1d5cd836e49/Update%20a%20product.png)
![Delete product](https://github.com/jsbiresh/productservice-naman/blob/901a5041153c4665693f2b4a7c80c1d5cd836e49/Delete%20a%20product.png)
 
