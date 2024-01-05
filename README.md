# easyshop-api

Welcome to the EasyShop E-Commerce Capstone Project repository! This project is a Spring Boot-based e-commerce application for EasyShop. Below, you'll find details about each phase of development and the features implemented.

## Project Overview

EasyShop is a fully functional e-commerce website with a Spring Boot API backend and a MySQL database for data storage. EasyShop allows users to log in, browse products in various categories, and prices, add them to a shopping cart, and check out to order the products. The project involves fixing existing bugs in the API code and implementing new features, all while ensuring the backend functions smoothly.

## Built With
- ### Programming Languages:
  - Backend: Java, SQL
  - Frontend: HTML, CSS, JavaScript
- ### Tools:
  - IntelliJ (Maven)
    - Spring Boot Framework
  - MySQL Workbench
  - Postman
 
## Getting Started
- Clone this repository to your local machine.
- Open the project in your preferred Java IDE.
- Set up the MySQL database using the provided script.
- Run the Spring Boot API.
- Access the front-end web application to test your changes.
- Testing:
  - Utilize Postman for API endpoint testing. A frontend web application is also available for testing your API's integration with the web.

## Phase 1 - CategoriesController
- Implemented methods in `CategoriesController` class.
- Only administrators can insert, update, or delete a category.
- Implemented code in `MySqlCategoriesDao`.
- Used annotations appropriately in the controller.

## Phase 2 - Fix Bugs
- Resolved bugs in the `ProductsController`:
  - Bug 1: Fixed incorrect search results.
  - Bug 2: Resolved product duplication issues.

## Phase 3 - Shopping Cart
### New Feature - Shopping Cart
- Implemented shopping cart functionality where logged-in users can items to their shopping cart.
- Shopping cart data is stored in the database.
- Implemented methods in `ShoppingCartController` for required REST actions:
  - **GET**: Returns the shopping cart for the current user.
  - **POST**: Adds a new product to the shopping cart.
  - **DELETE**: Clears the shopping cart.
  - **PUT**: Updates the quantity of a product in the cart.
- Implemented code in `ShoppingCartDao` and `MySqlShoppingCartDao`.

## Acknowledgments
- This project is part of the capstone requirements for Pluralsight LTCA.
- Thanks to Paul Kimball for guidance and support.


