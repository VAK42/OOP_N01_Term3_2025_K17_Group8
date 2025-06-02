# Store Inventory Management Application
  
## **I - Title**
  
Store Inventory Management Application
  
## **II - Database Structure**
  
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