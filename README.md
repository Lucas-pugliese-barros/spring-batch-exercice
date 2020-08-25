
## Project

Sistema de análise de dados, onde o batch vai ler os arquivos .dat, processá-los e posteriormente enviar para uma das seguintes pastas:

* **DONE** onde ficarão os arquivos que já foram devidamente processados;
* **INPUT ou DATA/IN** onde ficarão os arquivos que devem ser processados;
* **INVALID** onde ficarão os arquivos inválidos;
* **OUTPUT ou DATA/OUT** onde ficarão os relatórios dos arquivos processados;

Obs: essas pastas devem ser criadas e configuradas no **application.properties**

```
input.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/main/resources/input
done.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/main/resources/done
output.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/main/resources/output
invalid.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/main/resources/invalid

```

---

## TESTs

Para rodar os testes, executar:

```

CMD: mvn test

```


Para rodar os testes é necessário alterar as seguintes pastas no **application-test.properties**:

```
scenarios.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/test/resources/scenarios
input.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/test/resources/input
done.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/test/resources/done
output.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/test/resources/output
invalid.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/test/resources/invalid

```

Obs: a pasta **scenarios.folder** contém os arquivos que serão utilizados para os testes de integração

---

# Enviroment!!

1. SO: Fedora release 32;
2. Java version: OPENJDK 11.0.8;
3. Maven version: 3.6.1 (Red Hat 3.6.1-5);
4. IDE: IntelliJ IDEA;

