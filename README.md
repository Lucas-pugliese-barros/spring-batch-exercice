
# Aplicacao

Projeto desenvolvido com o proposito de ler arquivos .dat, processá-los, gerar relatorio e posteriormente enviar os mesmos para suas devidas pastas a depender do fluxo. As pastas envolvidas nesse processo:

* **DONE** onde ficarão os arquivos que já foram devidamente processados;
* **INPUT ou DATA/IN** onde ficarão os arquivos que devem ser processados;
* **INVALID** onde ficarão os arquivos inválidos;
* **OUTPUT ou DATA/OUT** onde ficarão os relatórios dos arquivos processados;

Obs: Para rodar o projeto pela **IDE** ou pelo **Docker** essas pastas devem ser criadas na sua **HOME** com a seguinte estrutura:

```
/data/input
/data/done
/data/output
/data/invalid

```

## Exemplo de arquivos .dat

**Valido**
```
001ç1234567891234çPedroç50000
001ç3245678865434çPauloç40000.99
002ç2345675434544345çJose da SilvaçRural
002ç2345675433444345çEduardo PereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo

```

**Invalido**
```
001ç1234567891234çPedroç50000
001ç3245678865434çPauloç40000.99
002ç2345675434544345çJose da SilvaçRural
002ç2345675433444345çEduardo PereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
003ç08ç[1-34-10,2-33-1.50,3-40-0.10çPaulo

```

## Exemplo de relatorio
```
Quantidade de clientes no arquivo de entrada: 2
Quantidade de vendedores no arquivo de entrada: 2
ID da venda mais cara: 10
O pior vendedor: Paulo

```


---

## Como executar?


**Garanta que voce tem:**
1. Git;
2. Java 11;
3. Docker e docker-compose;

**Depois:**

1. Obtenha o projeto com o git: git clone https://github.com/Lucas-pugliese-barros/spring-batch-exercice.git
2. Crie as seguintes pastas na sua **HOME**:

```
/data/input
/data/done
/data/output
/data/invalid

```
3. Crie a imagem docker com: `mvn clean package`
4. Execute o batch-compose com: `docker-compose -f batch-compose.yml up`
5. Adicione um arquivo .dat em `/data/input`

Obs: O arquivo **batch-compose** se encontra na seguinte pasta do projeto: `src/main/docker/compose`

---

## TESTs / Docker

Para rodar os testes voce precisa entrar na pasta do projeto e executar:

```

CMD: mvn clean test

```

Para rodar os testes e criar imagem docker voce precisa entrar na pasta do projeto e executar:
```

CMD: mvn clean package

```

---

# Desenvolvimento com:

1. SO: Fedora release 32;
2. Java version: OPENJDK 11.0.8;
3. Maven version: 3.6.1 (Red Hat 3.6.1-5);
4. [Spring Batch](https://spring.io/projects/spring-batch);
5. [Spring Boot](https://spring.io/projects/spring-boot);
6. [Cucumber](https://cucumber.io/);
7. [Assert J](https://joel-costigliola.github.io/assertj/);
8. IDE: IntelliJ IDEA;


