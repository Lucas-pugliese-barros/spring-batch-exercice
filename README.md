
## Project

Sistema de análise de dados, onde o batch vai ler os arquivos .dat, processa-los e posteriormente enviar o mesmo para uma das seguintes pastas:

* **DONE** onde ficaram os arquivos que ja foram processados devidamente;
* **INPUT ou DATA/IN** onde ficaram os arquivos que devem ser processados;
* **INVALID** onde ficaram os arquivos invalidos;
* **OUTPUT ou DATA/OUT** onde ficaram os relatorios dos arquivos processados;

Obs: essas pastas podem e devem ser configuradas no **application.properties**

```
input.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/main/resources/input
done.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/main/resources/done
output.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/main/resources/output
invalid.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/main/resources/invalid

```

---

## TESTs

Para rodar os testes executar:

```

CMD: mvn test

```


Para rodar os testes e necessario alterar as seguintes pastas:

```
scenarios.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/test/resources/scenarios
input.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/test/resources/input
done.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/test/resources/done
output.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/test/resources/output
invalid.folder=/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/test/resources/invalid

```

Obs: a pasta **scenarios.folder** contem os arquivos que seram utilizados para os testes de integracao

---

# Enviroment!!

1. SO: Fedora release 32;
2. Java version: OPENJDK 11.0.8;
3. Maven version: 3.6.1 (Red Hat 3.6.1-5);
4. IDE: IntelliJ IDEA;

