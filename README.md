Employee Management Rest API based Web Application : CRUD operation and Spring Security

> At the start 2 nos. of users will be given authority having roles as "ADMIN" & "USER"
> ADMIN will have all the authorization which includes creation of User and different Roles
> ADMIN can create User & Role at runtime which will be saved on the database as well with bycrypted password
> ADMIN can Assign role to the created user at runtime.
> User created at runtime will have immediate access according the the role assigned
> "USER" Role will have to access employees operation such as: GET: Sort, search etc

> Following endpoints can be accessed as per user roles-->

>localhost:8080/api/employees/
  > GET    : 1. Fetch Employee list, 2. Search Employee by ID , 3. Search Employee by firstName 
             4. Search Employee by Department name , 5. Sort Employee by ID 6. Sort Employee by firstName
  > POST   : 1. Save a new employee into database
  > PUT    : 1. Update existing record in the database
  > DELETE : 1. Delete existing employee by ID from the database

>localhost:8080/api/roles/
  > GET    : 1. Fetch list of roles from database , 2. Search roles by ID
  > POST   : 1. Creating the new Role into databsae
  > DELETE : 1. Deleting existing Role by ID from the database

>localhost:8080/api/users/
  > GET    : 1. Fetch Users list , 2. Search User by ID 
  > POST   : 1. Create new username with password into database
  > DELETE : 1. Delete existing User by ID from the database

>localhost:8080/api/users/roles
  > GET    : 1. Fetch user with their role ID

> localhost:8080/api/users/{User_ID}/roles{Role_Id}
  > PUT    : 1. Assigning a role to the user at runtime

> localhost:8080/logout : logout
