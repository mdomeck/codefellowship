## codefellowship
Clone repo from gitHub
In terminal `git clone <add Github linkhere>`
Setup Postgresql database named `codefellowship`
Update application properties

spring.datasource.url=jdbc:postgresql://localhost:5433/codefellowship
spring.datasource.username=add username
spring.datasource.password=add password
#spring.jpa.hibernate.ddl-auto=create
spring.jpa.hibernate.ddl-auto=update

`gradle bootRun` to launch your server
Open `http://localhost:8080/`

## functionality
- root route / that contains option to login or signup
- Signup has first name, last name, dob, bio saved to database
- password encoder to protect login info
- login page for current users