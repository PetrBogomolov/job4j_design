package ru.job4j.ood.isp;

/*
 * Интерфейс Company содержить в себе весь функционал, который выполняют различные отделы компании.
 * В итоге при реализации каждым отделом собственной задачи, они получают дополнительные возможности
 * влиять на рабочие процессы в других отделах.
 * Это нарушение принципа ISP
 * В этом случае необходимо для каждого отдела в компании вынести собственный интерфейс
 */
public interface Company {

    int calculateSalary();

    boolean hireEmployee();

    void developProgramm();

    void cookDinner();

    class CompanyImpl implements Company {

        @Override
        public int calculateSalary() {
            return 0;
        }

        @Override
        public boolean hireEmployee() {
            return false;
        }

        @Override
        public void developProgramm() {

        }

        @Override
        public void cookDinner() {

        }
    }

    class Developer {
        private final Company dev = new CompanyImpl();

        public void developProgramm() {
            dev.developProgramm();
        }
    }

    class HR {
        private final Company hr = new CompanyImpl();

        public void hireEmloyee() {
            hr.hireEmployee();
        }
    }

    class Accounting {
        private final Company accounting = new CompanyImpl();

        public void calculateSalary() {
            accounting.calculateSalary();
        }
    }

    class DinnerRoom {
        private final Company dinner = new CompanyImpl();

        public void cookDinner() {
            dinner.cookDinner();
        }
    }
}
