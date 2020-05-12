# Expressions parser
### Expression
- Классы Const, Variable
- Бинарные операции Add, Subtract, Multiply, Divide, Min, Max
- Унарные операции Negate, Count
- Все классы реализовывают интерфейс ToMiniString, метод `toMiniString` выводит выражение с минимально возможным количеством скобок
Пример использования:
  ```java
  new Subtract(
      new Multiply(
          new Const(2),
          new Variable("x")
      ),
      new Const(3)
  ).evaluate(5)
  ```
  
### Parser
- Строит выражение из классов `expression` по записи выражения
- Работает за линейное время, разбор выражений производится методом рекурсивного спуска

### Exceptions
- Различные ошибки парсинга ParsingException
- Ошибки при выислении (OverflowException, ZeroDivisionException)

### Evaluators
- Реализация вычислений в различных типах
