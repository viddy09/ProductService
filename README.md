# ProductService : 
	It is one of the service of E-commerce project deals with managing products.
	End user can view single product, search for produts, can see products by category can apply filters like sorting by various factors like title, price, etc.
	Admin user can manage products.

# Flow:
	-> User Sign Up 
	-> User login (JWT token is returned from UserService)\
	-> User can call API 
 		-> Token is validated (Authroization checked, Role checked)
			Successfully
				Users request processed.
			Else 
				Request Denied.
![E-Commerce](https://github.com/viddy09/ProductService/assets/70717147/2622fe4a-f749-4481-97a3-ca5870dc764c)


# Pre Requisites:
  	JDK 12+, Spring and spring boot Framework, Maven dependency, Redis for Windows is required.

# Supported PlatForms:
  	Windows

# References:
  	Spring Data : https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html
  	Spring Security : https://docs.spring.io/spring-authorization-server/reference/getting-started.html
  	Others : StackOverFlow, Spring Docs, etc
  
