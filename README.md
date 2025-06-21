# OOP - Store Inventory Management Application
  
## **I - Title**
  
Store Inventory Management Application
## **II - Introduction**
OOP is a project developed as part of the Object-Oriented Programming course at Phenikaa University. The project focuses on building a software application using the object-oriented programming paradigm, aiming to solve real-world problems through a clear, maintainable, and extensible architecture.

The application is designed to deliver a user-friendly interface, stable performance, and an optimal user experience.

The project was developed by a team of 3 members under the guidance of Dr. Lệ Thu Nguyễn.
## **III - Project Goal**
- The Store Inventory Management Application is designed to provide an efficient, user-friendly, and scalable solution for managing store inventory. The main goals of the project are:

- Simplify Inventory Operations: Streamline the processes of adding, updating, importing, and exporting products to improve management efficiency and reduce human error.

- Centralize Product Management: Provide a centralized platform to manage products, stock movements (import/export), and related records with clarity and transparency.

- Enhance Reporting and Decision-Making: Offer customizable and real-time reporting features to help users analyze inventory trends, track stock levels, and make data-driven decisions.

- User Access Control: Enable secure user management with role-based access to protect sensitive data and prevent unauthorized operations.

- Modular and Scalable Architecture: Built using OOP principles and MVC architecture, the system is designed to be extendable, maintainable, and suitable for future enhancements or integrations.
## **IV - Team Members**
- **Vu Anh Kiet** - VAK42
- **Tran Ha Quang** - Babiboyy55 
- **Pham Thi Minh Ngocg** - mcongie
## **V - Technologies Used **
- Programming Languages: Java, HTML, CSS

- Framework: Spring Boot

- Database: PostgreSQL

## ** VI -  Main Features**
- Register, login, and logout

- Forgot password and change password

- Add, edit, and delete expense records

- Display list of expenses

- View expense statistics via charts

- View expenses over specific time periods

- Update personal information

## **VII - Database Structure**
  
### 1. User
  
Manages Authorization
  
| Field      | Type    | Description               |
|------------|---------|---------------------------|
| userid     | UUID    | Unique Identifier         |
| username   | String  | Username                  |
| password   | String  | Encrypted Password        |
| email      | String  | User's Contact Email      |
| role       | String  | User Role (admin/user)    |
  
---
  
### 2. Category
  
Groups Products For Better Management
  
| Field        | Type   | Description              |
|--------------|--------|--------------------------|
| categoryid   | UUID   | Unique Category ID       |
| name         | String | Category Name            |
  
---
  
### 3. Product
  
Main Entity For Inventory Tracking
  
| Field        | Type     | Description                         |
|--------------|----------|-------------------------------------|
| pdid         | UUID     | Product ID                          |
| pdname       | String   | Product Name                        |
| pdprice      | Decimal  | Unit Price                          |
| categoryid   | UUID     | Foreign Key To Category             |
| pdinfo       | String   | Product Description/Info            |
| pdquantity   | Integer  | Quantity In Stock                   |
  
---
  
### 4. Import
  
Tracks Incoming Product Stock (Restocking)
  
| Field       | Type     | Description                       |
|-------------|----------|-----------------------------------|
| ipid        | UUID     | Import ID                         |
| pdid        | UUID     | Product ID                        |
| pdprice     | Decimal  | Purchase Price At Import          |
| pdquantity  | Integer  | Quantity Imported                 |
| date        | DateTime | Date Of Import                    |
  
---
  
### 5. Export
  
Tracks Outgoing Stock (Sales)
  
| Field         | Type     | Description                         |
|---------------|----------|-------------------------------------|
| epid          | UUID     | Export ID                           |
| pdid          | UUID     | Product ID                          |
| pdprice       | Decimal  | Selling Price At Export             |
| pdquantity    | Integer  | Quantity Exported                   |
| pdtotalprice  | Decimal  | Total Revenue From This Export      |
| userid        | UUID     | User Who Processed The Export       |
| date          | DateTime | Date Of Export                      |
  
---
  
### 6. Report
  
User-created Custom Reports
  
| Field      | Type   | Description                     |
|------------|--------|---------------------------------|
| reportid   | UUID   | Unique Report ID                |
| userid     | UUID   | Creator User ID                 |
| rpname     | String | Report Title                    |
| rpinfo     | String | Report Details                  |
  
---

## **III - Project Structure**
  
```plaintext
oop/
├── src/
│   ├── App.java
│   ├── Category.java
│   ├── Product.java
│   ├── Export.java
│   ├── Import.java
│   ├── User.java
│   ├── Report.java
│   ├── CategoryList.java
│   ├── ProductList.java
│   ├── ExportList.java
│   ├── ImportList.java
│   ├── UserList.java
│   └── ReportList.java
│
└── test/
    ├── CategoryTest.java
    ├── ProductTest.java
    ├── ExportTest.java
    ├── ImportTest.java
    ├── UserTest.java
    └── ReportTest.java
```
  
## **IV - Team Members**
  
*Vu Anh Kiet* - VAK42 (*) 
  
*Tran Ha Quang* - Babiboyy55 
  
*Pham Thi Minh Ngoc* - mcongie
  
## **V - Behavioural Diagram - Activity Diagram**

```plaintext
** Login

(Start)
    |
[Enter Login Information]
    |
[Check Account]
    |
{Valid Account?}
    |           \
  [Yes]         [No]
    |             \
[Homepage]      [Display Error]
    |             |
   End       [Return To Login]
```
  
```plaintext
** Home

(Start)
    |
[Homepage]
    |
    |-------[Product]
    |           |
    |           |---[Add New Product (Create)]
    |           |---[View Product List (Read)]
    |           |---[Edit Product (Update)]
    |           |---[Delete Product (Delete)]
    |
    |-------[Import]
    |           |
    |           |---[Create Import Record (Create)]
    |           |---[View Import Records (Read)]
    |           |---[Update Import Record (Update)]
    |           |---[Delete Import Record (Delete)]
    |
    |-------[Export]
    |           |
    |           |---[Create Export Record (Create)]
    |           |---[View Export Records (Read)]
    |           |---[Update Export Record (Update)]
    |           |---[Delete Export Record (Delete)]
    |
    |-------[Report]
    |           |
    |           |---[Generate Report (Read)]
    |           |---[View Saved Reports (Read)]
    |           |---[Customize Report (Update)]
    |           |---[Delete Report (Delete)]
    |
    |-------[User]
    |           |
    |           |---[Add New User (Create)]
    |           |---[View User List (Read)]
    |           |---[Edit User (Update)]
    |           |---[Delete User (Delete)]
    |
   End
```

```plaintext
** Product

(Start)
    |
[Homepage]
    |
    |-------[Product]
    |           |
    |           |---[Add New Product (Create)]
    |           |       |---[Enter Product Details (Name, Price, Description)]
    |           |       |---[Upload Product Images]
    |           |       |---[Assign Product Category]
    |           |       |---[Save New Product]
    |           |
    |           |---[View Product List (Read)]
    |           |       |---[Display Product Table (With Filters/Sorting)]
    |           |       |---[Search Products]
    |           |       |---[View Product Details Page]
    |           |
    |           |---[Edit Product (Update)]
    |           |       |---[Select Product To Edit]
    |           |       |---[Modify Product Details]
    |           |       |---[Update Product Images]
    |           |       |---[Change Product Category]
    |           |       |---[Save Changes]
    |           |
    |           |---[Delete Product (Delete)]
    |           |       |---[Select Product To Delete]
    |           |       |---[Confirm Deletion]
    |           |       |---[Remove Product From Database]
    |
    |-------[Import]
    |           |
    |           |---[Create Import Record (Create)]
    |           |---[View Import Records (Read)]
    |           |---[Update Import Record (Update)]
    |           |---[Delete Import Record (Delete)]
    |
    |-------[Export]
    |           |
    |           |---[Create Export Record (Create)]
    |           |---[View Export Records (Read)]
    |           |---[Update Export Record (Update)]
    |           |---[Delete Export Record (Delete)]
    |
    |-------[Report]
    |           |
    |           |---[Generate Report (Read)]
    |           |---[View Saved Reports (Read)]
    |           |---[Customize Report (Update)]
    |           |---[Delete Report (Delete)]
    |
    |-------[User]
    |           |
    |           |---[Add New User (Create)]
    |           |---[View User List (Read)]
    |           |---[Edit User (Update)]
    |           |---[Delete User (Delete)]
    |
   End
```

## **VI - Information**
  
Includes Product Management Functionality. Ability To Add, Update, And Delete Products (Product). Display A List Of Products With Filtering Options By Category, Price, And Quantity.
  
Includes User Management Functionality. Ability To Add, Update, And Delete Users (User).
  
Ability To Assign Products To Users When Performing Import Or Export Operations.
  
Data Must Be Stored In Binary Files.
  
Must Create Classes Related To Product, User, And Import To Read From And Write To One Or More Binary Files.
  
## **X - Class Diagram**
  
<img src="img/Screenshot 2025-06-21 112729.png">
