# Blogging-Platform-API
This is an API for a Blogging Platform that allows users to create posts, follow other users, comment on posts, and perform various other actions related to blogging. It is built using the following frameworks and languages:

## Frameworks and Language Used
- Spring Framework (including Spring Boot, Spring MVC, Spring Data JPA)
- Java
- Hibernate
- MySQL
- Swagger
- AWS

## Data Flow
The data flow in the Blogging Platform API is organized into the following components:
- Controller
- Services
- Repository
- Database Design

## controller
The Controller layer handles the incoming HTTP requests and manages the routing of these requests to the appropriate service methods. It provides the necessary endpoints for interacting with the API. Some of the key functionalities implemented in the controllers include:
- User registration and authentication
- Post creation and retrieval
- Follow and unfollow users
- Comment creation and retrieval
## Endpoints
### User Controller:
- **Sign Up**: `POST /user/signUp`
- **Sign In**: `POST /User/signIn`
- **Get Post by ID**: `GET /postById/{postId}`
- **Change Password**: `PUT /change/password`
- **Sign Out**: `DELETE /user/signOut`

### Post Controller

- **Create Vlog Post**: `POST /Post/vlog`
- **Get All Posts**: `GET /All/Post`
- **Update Post by ID**: `PUT /update/Post/{postId}`
- **Delete Post by ID**: `DELETE /Post/{postId}`

### Follower Controller

- **Follow User**: `POST /follow/user/{targetUserId}`
- **Get All Followers**: `GET /all/follower`

### Comment Controller

- **Comment on Post**: `POST /comment/post/{postId}`
- **Get All Comments for a Post**: `GET /all/comment/{postId}`
- **Update Comment by ID**: `PUT /update/comment/{commentId}`
- **Delete Comment by ID**: `DELETE /post/comment/{commentId}`


## services
The Services layer contains the business logic of the application. It acts as an intermediary between the controllers and the repositories, implementing the necessary operations and transformations on the data. The services handle operations such as:
- **UserService:** creation, retrieval, update, deletion.
- **PostService:** creation, retrieval, update
- **FollowerService:** adding followers, retrieving followers, retrieving followed users, unfollow.
- **CommentService:** adding comments, retrieving comments, deletion.

## repository
The Repository layer is responsible for interacting with the underlying database. It provides an abstraction over the database operations, allowing the services to query and manipulate the data. Some of the key responsibilities of the repositories include:
- **UserRepo:** Provides methods for retrieving and storing user-related data.
- **PostRepo:** Offers methods for retrieving and storing post-related data.
- **FollowerRepo:** Handles the storage and retrieval of follow relationship data.
- **CommentRepo:** Manages the storage and retrieval of comment data.

## Database Design
The database design for the Blogging Platform API follows the object-relational mapping (ORM) approach provided by Spring Data JPA. The entities (such as User, Post, Follow, Comment) are mapped to database tables, and the relationships between them are defined using annotations. The data is persisted in a relational database, such as MySQL.

## Data Structures Used
- Lists: Used to store collections of objects, such as lists of posts, followers, and followings.


## Project Summary
The Blogger Web Application is a full-stack web application that allows users to create, read, update, and delete blog posts and comments. It also allows users to follow other users and view their blog posts. The application uses Spring Boot, Spring MVC, and Spring Data JPA to implement the backend, and MySQL for the database. Swagger is used for API documentation.

## Deployment
The application has been deployed on an AWS EC2 instance and can be accessed using the following URL: http://3.7.73.195:8080/swagger-ui/index.html#/
