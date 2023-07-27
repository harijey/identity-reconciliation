
# Identity-Reconciliation

## Steps to Follow
* Clone the git repository or run the below command.
---
    git clone https://github.com/harijey/identity-reconciliation.git
---
* Unzip the zip file into a directory.
* Open the project Directory.
* Run the following command in the terminal => **docker-compose up**
* Exposed Port : **9876**
* SWAGGER URL: http://localhost:9876/identity-reconciliation/swagger-ui/index.html

---
    Curl Command : curl --location 'http://localhost:9876/contact/identify' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "email":"<EMAIL_ID>",
    "phoneNumber":"<PHONE_NUMBER>"
    }'
    Note: Replace <EMAIL_ID> with the email and <PHONE_NUMBER> with phone number.
---



    ---
    Back-End Framework: Springboot 2.7.7
    DataBase: Postgres:13-Alpine
    ---
    