# Bank Management System
This Bank Management System is a mini project created using Java for Object Oriented Programming Course 2021-2022. It is a simple Command Line Application which can be used using terminal. The data of users is stored in MySQL database. We can ```CREATE``` a new user means a new account, can ```READ``` the users data, can ```UPDATE``` the details of the user, can ```DELETE``` a record of the user

## Group
```Group 19```

## All Contributors ✨✨

<table>
  <tr>
    <td><a href="https://github.com/AmoghPatel1"><img src="https://github.com/AmoghPatel1.png" width="100px;" alt=""/><br /><sub><b>AMOGH PATEL<br>S20200010015</b></sub></a></td>
    <td><a href="https://github.com/ranjithgoud23"><img src="https://github.com/ranjithgoud23.png" width="100px;" alt=""/><br /><sub><b>RANJITH GOUD KOLA<br>S20200010094</b></sub></a></td>
    <td><a href="https://github.com/github-akhila"><img src="https://github.com/github-akhila.png" width="100px;" alt=""/><br /><sub><b>AKHILA GUTTULA<br>S20200010074</b></sub></a></td>
    <td><a href="https://github.com/venuvasam09"><img src="https://github.com/venuvasam09.png" width="100px;" alt=""/><br /><sub><b>VASAM VENU<br>S20200010225</b></sub></a></td>
    <td><a href="https://github.com/VARUN-K-BANSAL"><img src="https://github.com/VARUN-K-BANSAL.png" width="100px;" alt=""/><br /><sub><b>VARUN KUMAR BANSAL<br>S20200010223</b></sub></a></td>
  </tr>
</table>

## Requirements
[MySQL server](https://www.mysql.com/downloads/)<br>
[Java JDK](https://www.oracle.com/java/technologies/downloads/)<br>
CLASSPATH environment variable having JDBC driver path in it.

## Getting Started

#### 1. Clone the repository
```
git clone https://github.com/VARUN-K-BANSAL/oops-mini-project.git
cd oops-mini-project
```

#### 2. Compile the Main file
```
javac Main.java
```

#### 3. Run the Program
```
java Main
```
```
java Main -h
```

## Other Instructions
### Help or Instruction module
```java
java Main -h
```
```java
java Main
```
------------------
### Creating a new account
```java
java Main -c
```
> On running the above command it will list a help containing the arguments required for this command
```
1. Type of account you want For savings account give SA, for current account give CA.
2. Name of the account holder and if your name has spaces in between (firstname lastname) then give like this "firstname lastname".
3. Gender give M for male and F for female.
4. Username you wanted to use for future banking purposes
5. Password you wanted to use for login and banking purposes
6. Opening Balance for the account
```
----------------------
### For updating your password
```java
java Main -up
```
> On running the above command it will list a help containing the arguments required for this command
```
1. Username of the account
2. Old password or the current password you are using
3. New password to set
```
--------------------------
### Searching in Database
```java
java Main -s
```
> On running the above command it will list a help containing the arguments required for this command
```
-u for searching using username
java Main -s -u username
```
```
-a for searching using account number
java Main -s -a account_number
```
```
-n for searching using name
java Main -s -n name
```
```
-t for tracing a transaction using transaction i
java Main -s -t transaction_id
```
--------------------------
### Deleting your account
```java
java Main -d
```
> On running the above command it will list a help containing the arguments required for this command
```
1. username of your account
2. password of your account
Below is the complete instruction
```
```java
java Main -d username password
```
---------------------------
### Transactions
```java
java Main -tr
```
> On running the above command it will list a help containing the arguments required for this command
```
For withdrawing Money use -w
1. username of account
2. Password of account
3. Amount
Complete command will look like this
java Main -tr -w username password amount
```
```
For depositing Money use -d
1. username of account
2. Amount
java Main -tr -d username amount
```
```
For transferring Money use -t
1. username of account
2. Password of account
3. Amount
4. Username of the beneficiary
java Main -tr -t sender_username password amount beneficiary_username
```



