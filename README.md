# 📚 Library Management System

A full-stack web application built with **Java Servlets, JSP, and Hibernate ORM**, supporting complete library workflows — book inventory, member management, issue/return tracking, and role-based admin control.

---

## 🚀 Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java Servlets |
| Frontend | JSP (JavaServer Pages) |
| ORM | Hibernate |
| Database | MySQL |
| Session Management | HttpSession API |
| Build Tool | Maven / Eclipse Dynamic Web Project |
| Server | Apache Tomcat |

---

## ✨ Features

- **Full-Stack MVC Architecture** using Servlets (Controller) + JSP (View) + Hibernate (Model)
- **Book Management** — add, update, delete, and search books by title or author
- **Member Management** — register and manage library members
- **Issue & Return Workflows** — track book transactions with due dates
- **Overdue Tracking** — flag and display overdue books across member accounts
- **Role-Based Dashboards** — separate JSP views for Admin and User roles
- **Session Security** — admin access gated via `HttpSession` with logout and timeout handling
- **Hibernate ORM** — entity relationships and CRUD via HQL queries

---

## 🏗️ Architecture

```
Browser Request
      │
      ▼
┌──────────────────────┐
│   JSP (View Layer)   │  ← Dynamic HTML rendering, role-based UI
└────────┬─────────────┘
         │  HTTP Request / Response
         ▼
┌──────────────────────┐
│  Servlet (Controller)│  ← doGet / doPost, session checks, routing
└────────┬─────────────┘
         │
         ▼
┌──────────────────────┐
│   Service / DAO      │  ← Business logic, Hibernate Session calls
└────────┬─────────────┘
         │
         ▼
┌──────────────────────┐
│   Hibernate ORM      │  ← Entity mapping, HQL queries, transactions
└────────┬─────────────┘
         │
         ▼
┌──────────────────────┐
│        MySQL         │
└──────────────────────┘
```

---

## 🗃️ Database Design

### Entities & Relationships

```
Book (1) ────────────── (M) Transaction
Member (1) ──────────── (M) Transaction
```

| Entity | Key Fields |
|---|---|
| `Book` | id, title, author, isbn, quantity, available |
| `Member` | id, name, email, phone, joinDate |
| `Transaction` | id, book_id (FK), member_id (FK), issueDate, dueDate, returnDate, status |

- **One Member → Many Transactions**
- **One Book → Many Transactions**
- Relationships defined via Hibernate `@OneToMany` / `@ManyToOne` annotations

---

## 📡 Servlet Endpoints

### Admin Workflows
| URL Pattern | Method | Description |
|---|---|---|
| `/admin/dashboard` | GET | Admin home with inventory summary |
| `/admin/books/add` | POST | Add new book to inventory |
| `/admin/books/update` | POST | Update book details |
| `/admin/books/delete` | GET | Remove book |
| `/admin/members` | GET | View all members |
| `/admin/issue` | POST | Issue book to member |
| `/admin/return` | POST | Process book return |
| `/admin/overdue` | GET | View all overdue transactions |

### User Workflows
| URL Pattern | Method | Description |
|---|---|---|
| `/user/dashboard` | GET | User home with borrowed books |
| `/user/search` | GET | Search books by title or author |
| `/auth/login` | POST | Login and create HttpSession |
| `/auth/logout` | GET | Invalidate session and redirect |

---

## 🔐 Security & Session Management

Access control is enforced at the Servlet level using `HttpSession`:

```java
HttpSession session = request.getSession(false);
if (session == null || session.getAttribute("adminUser") == null) {
    response.sendRedirect("/login.jsp");
    return;
}
```

- Session is created on successful login and stores the user role
- Admin routes check for `adminUser` session attribute before serving content
- Logout explicitly calls `session.invalidate()`
- Session timeout configured in `web.xml` (default: 30 minutes)

---

## 🗂️ Project Structure

```
LibraryManagementSystem/
├── src/
│   └── main/
│       └── java/com/library/
│           ├── servlet/        # HttpServlet subclasses (controllers)
│           ├── dao/            # Hibernate DAO classes
│           ├── model/          # Hibernate entity classes (Book, Member, Transaction)
│           └── util/           # HibernateUtil (SessionFactory config)
├── WebContent/
│   ├── WEB-INF/
│   │   ├── web.xml             # Servlet mappings, session timeout
│   │   └── lib/                # JAR dependencies
│   ├── admin/
│   │   ├── dashboard.jsp
│   │   ├── manageBooks.jsp
│   │   ├── manageMembers.jsp
│   │   └── overdueList.jsp
│   ├── user/
│   │   ├── dashboard.jsp
│   │   └── searchBooks.jsp
│   ├── login.jsp
│   └── index.jsp
└── hibernate.cfg.xml           # DB connection & entity mappings
```

---

## ⚙️ Configuration

### `hibernate.cfg.xml`

```xml
<hibernate-configuration>
  <session-factory>
    <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/librarydb</property>
    <property name="hibernate.connection.username">your_username</property>
    <property name="hibernate.connection.password">your_password</property>
    <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
    <property name="hibernate.hbm2ddl.auto">update</property>
    <property name="hibernate.show_sql">true</property>

    <mapping class="com.library.model.Book"/>
    <mapping class="com.library.model.Member"/>
    <mapping class="com.library.model.Transaction"/>
  </session-factory>
</hibernate-configuration>
```

### `web.xml` (Session Timeout)

```xml
<session-config>
  <session-timeout>30</session-timeout>
</session-config>
```

---

## 🏃 Getting Started

### Prerequisites
- Java 8+
- Apache Tomcat 9+
- MySQL 8+
- Eclipse IDE (Dynamic Web Project) or IntelliJ with Tomcat plugin

### Setup Steps

```bash
# 1. Clone the repository
git clone https://github.com/your-username/Library-Management-System.git

# 2. Create the MySQL database
mysql -u root -p -e "CREATE DATABASE librarydb;"

# 3. Update hibernate.cfg.xml with your DB credentials

# 4. Deploy to Tomcat
#    In Eclipse: Right-click project → Run As → Run on Server
#    Or build WAR: mvn clean package → deploy to Tomcat webapps/

# 5. Access the app
#    http://localhost:8080/LibraryManagementSystem/
```

---

## 🖥️ Role-Based UI

### Admin Dashboard
- Full inventory view with add / edit / delete controls
- Issue and return book to/from members
- Overdue report with member details and days overdue
- Member registration and management

### User Dashboard
- View currently borrowed books and due dates
- Search books by title or author
- View availability status

---

## 📌 Key Design Decisions

- **Servlet as Controller** — each workflow (book, member, transaction) has a dedicated Servlet, keeping routing logic separate from view rendering
- **JSP for View** — dynamic HTML generated server-side; JSTL used to minimize scriptlet code in JSPs
- **HibernateUtil Singleton** — `SessionFactory` is built once at startup and reused, avoiding expensive re-initialization per request
- **HQL over raw SQL** — queries operate on entity objects rather than table names, making the codebase portable across databases
- **Session-based Auth** — lightweight, server-side access control without a third-party security framework

---

## 👨‍💻 Author

Shreemansu Sekhar Pradhan   
[GitHub](https://github.com/shreemansu) · [LinkedIn](https://linkedin.com/in/shreemansu)
