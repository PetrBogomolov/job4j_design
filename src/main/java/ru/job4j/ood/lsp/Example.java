package ru.job4j.ood.lsp;

/*
 * Примеры нарушения принципа LCP:
 * класс Example является базовым, содержит в себе поле диаметр трубы и метод подающий воду,
 * если соблюдены предусловия подачи воды.
 * Пример 1.
 * класс ExampleViolationOne является подклассом Example и переопределяет его метод, усиливая одно из его предусловий
 * в итоге мы получим аварийную ситуацию, когда при подаче воды будем использовать класс ExampleViolationOne,
 * в которм условия проверки на давление, с которым поступает вода в трубу, завышены.
 * Подадим воду под большим давлением, чем может выдержать система, получим аварию.
 *
 * Пример 2.
 * класс ExampleViolationTwo является подклассом Example и переопределяет его метод,
 * меняя предусловие в меньшую сторону.
 * Это вызывает ситуацию, когда используя объект класса ExampleViolationTwo для подачи воды, мы можем
 * дать давление недостаточное для того, чтобы эта воды пошла, но метод нас об этом не будут оповещать.
 *
 * Пример 3.
 * класс ExampleViolationThree является подклассом Example и переопределяет его метод,
 * убирая предусловия метода базового класса.
 * Это может вызвать аварийную ситуацию, т.к. теперь мы вообще не проверяем давление, под которым подаем водуБ
 * а проверяем только диаметр трубы.
 */
public class Example {

    protected final double diameter;

    public Example(double diameter) {
        this.diameter = diameter;
    }

    public boolean getWater(int waterPressure) {
        boolean result = true;
        if (diameter < 10 && waterPressure < 5 || waterPressure > 10) {
            result = false;
        }
        return result;
    }

    static class ExampleViolationOne extends Example {

        public ExampleViolationOne(double diameter) {
            super(diameter);
        }

        @Override
        public boolean getWater(int waterPressure) {
            boolean result = true;
            if (diameter < 10 && waterPressure < 5 || waterPressure > 13) {
                result = false;
            }
            return result;
        }
    }

    static class ExampleViolationTwo extends Example {

        public ExampleViolationTwo(double diameter) {
            super(diameter);
        }

        @Override
        public boolean getWater(int waterPressure) {
            boolean result = true;
            if (diameter < 10 && waterPressure < 4 || waterPressure > 10) {
                result = false;
            }
            return result;
        }
    }

    static class ExampleViolationThree extends Example {

        public ExampleViolationThree(double diameter) {
            super(diameter);
        }

        @Override
        public boolean getWater(int waterPressure) {
            boolean result = true;
            if (diameter < 10) {
                result = false;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Example example = new Example(12);
        System.out.println(example.getWater(12));

        Example exampleOne = new ExampleViolationOne(12);
        System.out.println(exampleOne.getWater(12));

        Example exampleTwo = new ExampleViolationTwo(12);
        System.out.println(exampleTwo.getWater(4));

        Example exampleThree = new ExampleViolationThree(12);
        System.out.println(exampleThree.getWater(50));
    }
}
