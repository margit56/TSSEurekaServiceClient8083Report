cd "C:\Users\Marzec\Desktop\TSSEurekaServer 8761\target\"
REM "C:\Program Files\jdk-17\bin\java" -jar TSSEurekaServer8761-0.0.1-SNAPSHOT.jar
START startPetlaJDK17
ECHO Eureka 1 dzia³a   -jar TSSEurekaServer8761-0.0.1-SNAPSHOT.jar

timeout 30

cd "C:\Users\Marzec\Desktop\TSSEurekaServer 8762\target\"
REM "C:\Program Files\jdk-17\bin\java" -jar TSSEurekaServer8762-0.0.1-SNAPSHOT.jar
START startPetlaJDK17
ECHO Eureka 2 dzia³a   -jar TSSEurekaServer8762-0.0.1-SNAPSHOT.jar