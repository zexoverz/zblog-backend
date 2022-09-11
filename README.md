# zblog-backend

Deployed Backend (Heroku) : https://zblog-backend.herokuapp.com

BASEURL = https://zblog-backend.herokuapp.com/zblog/

------


## **1. `POST` User / Register**

  

Register new user and will returns data user and password that has been hashed.

  

-  **URL**

  

/user/register

  

-  **Method:**

  

`POST`

  

-  **URL params**

  

none

  

-  **Data Body**

  

```json

username=[string]

password=[string]

```

  

-  **Success Response:**

  

-  **Code:** 200

**Content:**

  

```json

{  username  :  "username",

password  :  "$2a$10$ASA5ZM/cnNoBcR/OIl1iZOczgacDPUamq3Kwrmn1C01Pw0u4.4Iqi"  }

```

  

-  **Sample Input:**

  

```JSON

{

"username":  "username",

"password":  "123456"

}

```


## **2. `POST` User / Login**

  

Login user and will returns username and data Token.

  

-  **URL**

  

/user/login

  

-  **Method:**

  

`POST`

  

-  **URL params**

  

none

  

-  **Data Body**

  

```

username=[string]

password=[string]

```

  

-  **Success Response:**

  

-  **Code:** 200

**Content:**

  

```json

{

"token":  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNTY5OTgwMDE0fQ.UOxbgr1EY5sXCM1csgZIjba2vbbML-Tc-LtqDTKs5NY",

"username":  "username"

}

```

  

-  **Sample Input:**

  

```JSON

{

"username":  "username"

"password":  "123456"

}

```
