# microservicios

https://www.youtube.com/watch?v=czWbpqC1fLY&list=PL4bT56Uw3S4yTSw5Cg1-mhgoS85fVeFkT

## Hay dos formas de comunicar Microservicios
1. RestTemplate
2. Feign

## Monitoreo de Microservicios
### Deprecados
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>

### Levantar Zipkin
Colocar jar en caulquier parte:
cmd: java -jar zipkin-server-2.24.3-exec.jar