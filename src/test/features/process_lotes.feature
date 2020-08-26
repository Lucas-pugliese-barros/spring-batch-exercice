Feature: Read .dat files and generate sale report based on that

  Scenario: Process a valid file
    Given file "file_one.dat" to be processed with the following content
    """
    001ç1234567891234çPedroç50000
    001ç3245678865434çPauloç40000.99
    002ç2345675434544345çJose da SilvaçRural
    002ç2345675433444345çEduardo PereiraçRural
    003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
    003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
    """
    When the batch execute the process
    Then should exist a report named "file_one.done.dat"
    And in the report "file_one.done.dat" the quantity of clients should be "2"
    And in the report "file_one.done.dat" the quantity of salesman should be "2"
    And in the report "file_one.done.dat" the best sale must have ID "10"
    And in the report "file_one.done.dat" the worst salesman should be "Paulo"
    And should move the file "file_one.dat" to done folder

  Scenario: Process a invalid file
    Given file "file_two_invalid.dat" to be processed with the following content
    """
    001ç1234567891234çPedroç50000
    001ç3245678865434çPauloç40000.99
    002ç2345675434544345çJose da SilvaçRural
    002ç2345675433444345çEduardo PereiraçRural
    003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
    003ç08ç[1-34-10,2-33-1.50,3-40-0.10çPaulo
    """
    When the batch execute the process
    Then should not exist a report named "file_two_invalid.done.dat"
    And should move the file "file_two_invalid.dat" to invalid folder