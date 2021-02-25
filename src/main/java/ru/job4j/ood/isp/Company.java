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
        Company dev = new CompanyImpl();

        public void developProgramm() {
            dev.developProgramm();
        }
    }

    class HR {
        Company HR = new CompanyImpl();

        public void hireEmloyee() {
            HR.hireEmployee();
        }
    }

    class Accounting {
        Company Accounting = new CompanyImpl();

        public void calculateSalary() {
            Accounting.calculateSalary();
        }
    }

    class DinnerRoom {
        Company dinner = new CompanyImpl();

        public void cookDinner() {
            dinner.cookDinner();
        }
    }
}
