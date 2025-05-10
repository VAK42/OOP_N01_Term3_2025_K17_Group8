# Store Inventory Management Desktop Application
  
## **I - Title**
  
Store Inventory Management Desktop Application
  
  
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
