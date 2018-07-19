# InvoiceReport-Api

<h2>Introduction</h2>
InvoiceReport will help for Shopkeeper in creating Invoce for Customers. The project will contain the backend code and APIs for Invoice Report.

<h2>Project details and setup</h2>
The project is written in Java 8.

Maven is used for Project Management. The pom.xml file will contain all the dependencies needed for the project as well as other configuration details.

<h2>Prerequisites</h2>
<li>
<ul>Java8</ul>
<ul>Maven 3</ul>
<ul>Postgres 9.3</ul>
</li>

<h2>Build tasks</h2>
<li>
<ul> Running test mvn test </ul>

<ul>Compile project / generate code coverage report / make war mvn clean package </ul>

<ul>Running server mvn tomcat7:run</ul>
<ul>Applying migrations mvn clean compile flyway:migrate</ul>
</li>
