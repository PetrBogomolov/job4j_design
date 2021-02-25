package ru.job4j.ood.isp;

/*
 * Интерфейс описывает функционал работы с сайтом
 * Для соблядения принципа ISP интерфейс Server необходимо разделить на в нашем случае два интерфейса.
 * первый - полный, для админа
 * второй - пользовательский, для юзера
 */
public interface Server<T> {

    T findByName();

    T findByCity();

    T findByAge();

    void deleteUser();

    void banUser();

    void addUser();

    /*
     * Класс реализующий интерфейс
     */

    class ServerImpl implements Server {

        @Override
        public Object findByName() {
            return null;
        }

        @Override
        public Object findByCity() {
            return null;
        }

        @Override
        public Object findByAge() {
            return null;
        }

        @Override
        public void deleteUser() {

        }

        @Override
        public void banUser() {

        }

        @Override
        public void addUser() {

        }
    }

    /*
     * Этому классу нужен весь функционал интерфейса Server
     */
    class Admin {
        ServerImpl server = new ServerImpl();

        public void deletUser() {
            server.deleteUser();
        }
    }

    /*
     * Этому классу не нужен весь функционал интерфейса Server.
     * Более того, наличие доп. функционала у пользователя ставит под сомнения безопастность системы
     */
    class User {
        ServerImpl server = new ServerImpl();

        public Object findByName() {
           return server.findByName();
        }
    }
}
