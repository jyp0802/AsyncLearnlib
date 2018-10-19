# AsyncLearnlib

### TCP_server
Python server code used for RaspberryPi 3

### TCP_learner.java
Main learner for TCP handshake

### code/learnlib
Original Learnlib code with modifications for asynchronous querying as shown below.

#### Modifications in Learnlib
Mostly duplicating existing query functions to support multiple querying.

Main modificatoins in:

**stepMultiple()** - *learnlib\drivers\mapper\src\main\java\de\learnlib\mapper\MappedSUL.java*

**executeMultiple()** - *learnlib\drivers\basic\src\main\java\de\learnlib\drivers\reflect\ConcreteMethodInput.java*

**stepMultiple()** - *learnlib\drivers\simulator\src\main\java\de\learnlib\driver\util\MealySimulatorSUL.java*

**stepMultiple()** - *learnlib\oracles\filters\cache\src\main\java\de\learnlib\filter\cache\sul\SULCache.java*
