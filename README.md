# Крестики-Нолики (Tic-Tac-Toe) на Java

## Описание

Этот проект представляет собой реализацию классической игры "Крестики-нолики" на языке Java. Код предоставляет базовую логику игры, включая инициализацию игрового поля, отображение текущего состояния игры, проверку доступных ходов, определение победителя и обработку пользовательского ввода.

## Состав проекта

Проект состоит из одного класса:

*   **`Game.java`**: Содержит всю логику игры "Крестики-нолики".

    *   **`enum Cell`**: Перечисление, определяющее возможные состояния ячейки игрового поля: `X` (крестик), `O` (нолик), `EMPTY` (пустая).
    *   **`initGame()`**: Метод, инициализирующий игровое поле (3x3), заполняя все ячейки значением `EMPTY`.
    *   **`formatGame(Cell[][] field)`**: Метод, форматирующий текущее состояние игрового поля в удобочитаемую строку для отображения в консоли.
    *   **`isMoveAvailable(Cell[][] field)`**: Метод, проверяющий, есть ли на игровом поле доступные ходы (пустые ячейки).
    *   **`checkRowsCols(Cell[][] field)`**: Метод, проверяющий наличие выигрышной комбинации в строках и столбцах.
    *   **`checkDiags(Cell[][] field)`**: Метод, проверяющий наличие выигрышной комбинации на диагоналях.
    *   **`checkWin(Cell[][] field)`**: Метод, определяющий победителя игры (`X` или `O`) или возвращающий `EMPTY`, если победителя нет.
    *   **`move(Cell[][] field, Cell player, Scanner scanner)`**: Метод, обрабатывающий ход игрока, принимая координаты от пользователя, проверяя их корректность и занимая соответствующую ячейку на поле.
    *   **`main(String[] args)`**: Основной метод, запускающий игру.

## Запуск игры

1.  **Установите JDK (Java Development Kit):** Убедитесь, что у вас установлена Java SE Development Kit (JDK) версии 8 или выше.
2.  **Скомпилируйте код:** Откройте командную строку или терминал и перейдите в каталог, содержащий файл `Game.java`. Выполните команду:

    ```bash
    javac Game.java
    ```
3.  **Запустите игру:** Выполните команду:

    ```bash
    java Game
    ```

    Игра запустится в консоли. Вам будет предложено вводить ходы в формате "буква + цифра" (например, "a1", "b2", "c3").

## Управление

*   Для совершения хода, введите координаты ячейки в формате "буква + цифра" (например, "a1").
*   Буквы соответствуют строкам (a, b, c), цифры – столбцам (1, 2, 3).
