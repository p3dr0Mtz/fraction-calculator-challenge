# fraction-calculator-challenge


>Fraction Calculator - OneLogin Code Challenge.
>
>V1 program written in Java by Pedro A Martinez. OneLogin Coding Challenge.  

---

### Table of Contents
You're sections headers will be used to reference location of destination.

- [Description](#description)
- [How To Use](#how-to-use)
- [Author Info](#author-info)

---

## Description

Fraction Calculator

Command line program that take operations on fractions as an input and produce a fractional result.

Legal operators shall be *, /, +, - (multiply, divide, add, subtract)

Operands and operators shall be separated by one or more spaces
Mixed numbers will be represented by the whole_numerator/denominator. e.g. "3_1/4"

Improper fractions and whole numbers are also allowed as operands 

#### Technologies

- Java 11
- Maven 3.6
- Log4J2 2.13
- Lombok 1.18
- TestNG 7.1

[Back To The Top](#fraction-calculator-challenge)

---

## How To Use
- Execute the program.
- Type the Operation. e.g. "3_6/9 + 9/1"
- Wait for operation to be process.
- Close the program typing "exit".   

#### Installation
- Maven
    - In command lines navigate to project root.
    - Execute "mvn compile exec:java"    
- Docker
    - In command line navigate to project root.
    - Execute "mvn package -Dmaven.repo.local=MvnLocalRepo/ -DskipTests"
    - Execute "docker build -t codechallenge ."
    - Execute "docker run -ti --rm --name test codechallenge" 
    
[Back To The Top](#fraction-calculator-challenge)

---

## Author Info

- Pedro Antonio Martinez Vazquez

[Back To The Top](#fraction-calculator-challenge)