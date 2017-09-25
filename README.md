Email Queue Service
===================
Consumes email send requests from the AMQP queue and sends email via configured SMTP server

```
+--------------+     +-----------------------+     +---------------+
|              |     |                       |     |               |
|  AMQP Queue  +----->  Email Queue Service  +----->  SMTP Server  |
|              |     |                       |     |               |
+--------------+     +-----------------------+     +---------------+
```

Service Environment Variables
-------------------------------------------
| Environment variable  | Desciption |
| ------------- | ------------- |
| EMAIL_SERVICE_API_PORT  | Service API port  |
| EMAIL_SERVICE_ADMIN_PORT  | Service admin port  |

AMQP Environment Variables
-------------------------------------------
| Environment variable  | Desciption |
| ------------- | ------------- |
| EMAIL_SERVICE_AMQP_HOST | AMQP server hostname  |
| EMAIL_SERVICE_AMQP_PORT | AMQP server port  |
| EMAIL_SERVICE_AMQP_VHOST  | AMQP server virtual host  |
| EMAIL_SERVICE_AMQP_USERNAME | AMQP server username  |
| EMAIL_SERVICE_AMQP_PASSWORD | AMQP server password  |
| EMAIL_SERVICE_AMQP_EXCHANGE| AMQP server exchange name  |

SMTP Environment Variables
-------------------------------------------
| Environment variable  | Desciption |
| ------------- | ------------- |
| EMAIL_SERVICE_SMTP_HOST | SMTP server hostname  |
| EMAIL_SERVICE_SMTP_PORT | SMTP server port  |
| EMAIL_SERVICE_SMTP_USERNAME | SMTP server username  |
| EMAIL_SERVICE_SMTP_PASSWORD | SMTP server password  |
