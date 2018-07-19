# InvoiceReport-Api

<h2>Introduction</h2>
InvoiceReport will help for Shopkeeper in creating Invoce for Customers. The project will contain the backend code and APIs for Invoice Report.

<h2>Project details and setup</h2>
The project is written in Java 8.

Maven is used for Project Management. The pom.xml file will contain all the dependencies needed for the project as well as other configuration details.

<h2>Prerequisites</h2>
<ul>
<li>Java8</li>
<li>Maven 3</li>
<li>Postgres 9.3</li>
</ul>

<h2>Build tasks</h2>
<ul>
<li>
 <dl> <dt>Running test</dt>
   <dd>mvn test </dd>
 </dl>
 </li>

<li>Compile project / generate code coverage report / make war mvn clean package </li>

<li>Running server mvn tomcat7:run</li>
<li>Applying migrations mvn clean compile flyway:migrate</li>
</ul>
