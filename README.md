 <h1>Task tracker email sending service</h1>
<p>
  Проект был реализован в соответствии с 
  <a href="https://zhukovsd.github.io/java-backend-learning-course/projects/task-tracker/">
     тех. заданием.
  </a>
</p>
<p>
   Сервис получает объекты EmailMessage из очереди брокера RabbitMQ, после чего отправляет их, используя 
   MailGun API.
</p>
<h3>Использованные технологии:</h3>
<p>
  MailGun, RabbitMQ, Lombok, JUnit, Spring Boot, Spring Data Jpa, Spring AMQP.
</p>
