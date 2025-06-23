# **Store Inventory Management System**  
<p align="center">
  <img src="https://miro.medium.com/v2/0*9XI4DHbHZoGzMVfD.gif" alt="VAK" width="600"/>
</p>
  
## **I - Introduction**  
A CRUD-Based Web Application For Managing Store Inventory Operations - Developed With Spring Boot MVC + Thymeleaf + PostgreSQL. This System Enables Comprehensive Control Over Products, Categories, Imports, Exports, Users, Reports Through An Intuitive Dashboard.
  
---
  
## **II - Features**  
- Dashboard Overview With Statistics  
- User Authentication  
- Category Management  
- Product Management  
- Import Product Entries  
- Export Product Sales  
- Auto-Calculated Export Totals  
- Report Generation  
- Token-Based Session Handling  
- Data Visualization
- Excel + PDF Export  
  
---
  
## **III - Database Design**  
  
### 1. User  
_Manages Authorization_
  
| Field    | Type   | Description                    |
|----------|--------|--------------------------------|
| userId   | UUID   | Unique Identifier              |
| username | String | Username                       |
| password | String | Password                       |
| email    | String | Contact Email                  |
| role     | String | User Role (admin/user)         |
  
---
  
### 2. Token  
_Handles User Sessions_
  
| Field  | Type   | Description                          |
|--------|--------|--------------------------------------|
| userId | UUID   | Linked To User (Foreign Key)         |
| token  | String | Unique Session Token                 |
| date   | Date   | Time Of Token Creation               |
  
---
  
### 3. Category  
_Groups Products For Better Management_
  
| Field      | Type   | Description            |
|------------|--------|------------------------|
| categoryId | UUID   | Unique Category ID     |
| name       | String | Category Name          |
  
---
  
### 4. Product  
_Represents Store Products_
  
| Field       | Type     | Description                          |
|-------------|----------|--------------------------------------|
| pdId        | UUID     | Unique Product ID                    |
| pdName      | String   | Product Name                         |
| pdPrice     | Decimal  | Unit Price Of Product                |
| categoryId  | UUID     | Linked Category (Foreign Key)        |
| pdInfo      | Text     | Product Description                  |
| pdQuantity  | Integer  | Quantity In Stock (Default: 0)       |
  
---
  
### 5. Import  
_Tracks Incoming Inventory_
  
| Field      | Type     | Description                                |
|------------|----------|--------------------------------------------|
| ipId       | UUID     | Unique Import Record ID                    |
| pdId       | UUID     | Imported Product ID (Foreign Key)          |
| pdPrice    | Decimal  | Import Price Per Unit                      |
| pdQuantity | Integer  | Quantity Imported                          |
| userId     | UUID     | Handled By User (Foreign Key)              |
| date       | DateTime | Date Of Import (Default: Current Timestamp)|
  
---
  
### 6. Export  
_Tracks Outgoing Inventory_
  
| Field        | Type     | Description                                         |
|--------------|----------|-----------------------------------------------------|
| epId         | UUID     | Unique Export Record ID                             |
| pdId         | UUID     | Exported Product ID (Foreign Key)                   |
| pdPrice      | Decimal  | Export Price Per Unit                               |
| pdQuantity   | Integer  | Quantity Exported                                   |
| pdTotalPrice | Decimal  | Auto-Calculated As pdPrice × pdQuantity             |
| userId       | UUID     | Handled By User (Foreign Key)                       |
| date         | DateTime | Date Of Export (Default: Current Timestamp)         |
  
---
  
### 7. Report  
_Custom User Reports_
  
| Field     | Type   | Description                   |
|-----------|--------|-------------------------------|
| reportId  | UUID   | Unique Report ID              |
| userId    | UUID   | Created By User (Foreign Key) |
| rpName    | String | Report Name                   |
| rpInfo    | Text   | Report Content                |
  
---
  
## **IV - Used Technologies**  
- **Frontend:** HTML, CSS, Javascript, Thymeleaf  
  <a href="https://github.com/VAK42"><img src="https://skillicons.dev/icons?i=html,css,js,spring" /></a>  
- **Backend:** Java, Spring Boot, Hibernate ORM  
  <a href="https://github.com/VAK42"><img src="https://skillicons.dev/icons?i=java,spring,hibernate,maven" /></a>  
- **Database:** PostgreSQL, MySQL  
  <a href="https://github.com/VAK42"><img src="https://skillicons.dev/icons?i=postgres,mysql" /></a>  
- **Data Visualization:** Chart JS  
- **Testing:** JUnit, Mockito  
- **Version Control:** Github  
  <a href="https://github.com/VAK42"><img src="https://skillicons.dev/icons?i=git,github" /></a>  
- **IDE & Text Editor:** IntelliJ IDEA, VSCodium  
  <a href="https://github.com/VAK42"><img src="https://skillicons.dev/icons?i=idea,vscodium" /></a>  
  
---
  
## **V - Project Structure**  
  
### Architecture: MVC (Model - View - Controller)  
  
- Controller: Handles Routing & Request Processing  
- Model: Entity & Domain Classes  
- Repository: JPA Interfaces For Database Access  
- Service: Business Logic Layer  
  
---
  
## **VI - Folder Structure**  
  
```plaintext
.
├── .mvn/wrapper
│   └── maven-wrapper.properties
├── pom.xml
├── mvnw.cmd
├── src
│   └── main
│       ├── java/com/vak/oop
│       │   ├── controller
│       │   │   ├── CategoryController.java
│       │   │   ├── DashboardController.java
│       │   │   ├── ExportController.java
│       │   │   ├── ImportController.java
│       │   │   ├── ProductController.java
│       │   │   ├── ReportController.java
│       │   │   ├── RequestControllerAdvice.java
│       │   │   └── UserController.java
│       │   ├── model
│       │   │   ├── Category.java
│       │   │   ├── Export.java
│       │   │   ├── Import.java
│       │   │   ├── Product.java
│       │   │   ├── Report.java
│       │   │   └── User.java
│       │   ├── repository
│       │   │   ├── CategoryRepository.java
│       │   │   ├── ExportRepository.java
│       │   │   ├── ImportRepository.java
│       │   │   ├── ProductRepository.java
│       │   │   ├── ReportRepository.java
│       │   │   └── UserRepository.java
│       │   └── service
│       │       ├── CategoryService.java
│       │       ├── ExportService.java
│       │       ├── ImportService.java
│       │       ├── ProductService.java
│       │       ├── ReportService.java
│       │       └── UserService.java
│       └── resources
│           ├── application.properties
│           ├── VAK.sql
│           ├── static
│           │   ├── OOP.js
│           │   ├── OOP.png
│           │   ├── VAK.css
│           │   └── VAK.js
│           └── templates
│               ├── category
│               │   ├── form.html
│               │   └── list.html
│               ├── dashboard
│               │   └── index.html
│               ├── export
│               │   ├── form.html
│               │   └── list.html
│               ├── import
│               │   ├── form.html
│               │   └── list.html
│               ├── product
│               │   ├── form.html
│               │   └── list.html
│               ├── report
│               │   ├── form.html
│               │   └── list.html
│               └── user
│               |   ├── form.html
│               |   └── list.html
│               └── index.html
├── test/java/com/vak/oop/controller
│   ├── CategoryControllerTest.java
│   ├── ReportControllerTest.java
│   └── UserControllerTest.java
```
  
---
  
## **VII - Models & Functionality**  
  
### 1. User  
- Fields: UserId, Username, Password, Email, Role  
- Functions: Login, Role Validation, Token Association  
  
### 2. Token  
- Fields: UserId, Token, Date  
- Functions: Session Authentication, Expiration  
  
### 3. Category  
- Fields: CategoryId, Name  
- Functions: Add, Update, Delete, List Categories  
  
### 4. Product  
- Fields: PdId, PdName, PdPrice, CategoryId, PdInfo, PdQuantity  
- Functions: Add, Edit, Delete, Search Products  
  
### 5. Import  
- Fields: IpId, PdId, PdPrice, PdQuantity, UserId, Date  
- Functions: Register Product Imports, Stock Management  
  
### 6. Export  
- Fields: EpId, PdId, PdPrice, PdQuantity, PdTotalPrice, UserId, Date  
- Functions: Register Sales, Calculate Total, Reduce Stock  
  
### 7. Report  
- Fields: ReportId, UserId, RpName, RpInfo  
- Functions: Report Generation, Storage, Display  
  
---
  
## **VIII - Diagrams**  
- Coming Soon  
  
---
  
## **IX - Error Handling & Testing**  
  
- Try - Catch Blocks For Exception Management  
- Testing:  
  + JUnit For Unit Tests  
  + Mockito For Mocking & Integration  
  
---
  
## **X - Instructions**  
  
### Requirements  
- JDK 24  
- PostgreSQL  
- IntelliJ IDEA  
  
### Installation  
  
- Clone The Repository: git clone https://github.com/VAK42/OOP_N01_Term3_2025_K17_Group8.git  
  
- Navigate To The Project: cd OOP_N01_Term3_2025_K17_Group8  
  
- Configure Database In application.properties  
  
- Run The Application: ./mvnw.cmd spring-boot:run  
  
- Test The Application: ./mvnw.cmd test  
  
---
  
## **XI - Team Members**  
  
*Vu Anh Kiet* - VAK42 (*) 
  
*Tran Ha Quang* - Babiboyy55 
  
*Pham Thi Minh Ngoc* - mcongie
  
---
  
## **XII - Individual Works**  
  
*Vu Anh Kiet* - Base Initialization + Database + Dashboard + Category + Product  
  
*Tran Ha Quang* - Export + Report  
  
*Pham Thi Minh Ngoc* - Import + User  
  
---
  
## **XIII - Sources & Documents**  
- https://developer.mozilla.org/en-US/  
- https://www.thymeleaf.org/documentation.html  
- https://docs.spring.io/spring-boot/index.html  
- https://dev.java/learn/
- https://mvnrepository.com/
- https://hibernate.org/orm/documentation/7.0/
- https://www.postgresql.org/docs/
- https://dev.mysql.com/doc/
- https://www.chartjs.org/docs/latest/
- https://git-scm.com/doc
  
---
  
## XIV - Gallery  
  
- Dashboard  
  <img src="img/Dashboard.png" alt="Dashboard"/>  
- Category  
  <img src="img/Category.png" alt="Category"/>  
- Product  
  <img src="img/Product.png" alt="Product"/>  
- Import  
  <img src="img/Import.png" alt="Import"/>  
- Export  
  <img src="img/Export.png" alt="Export"/>  
- Report  
  <img src="img/Report.png" alt="Report"/>
- User  
  <img src="img/User.png" alt="User"/>  
