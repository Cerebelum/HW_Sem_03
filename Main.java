package HW_Exception.HW_Sem_03;

import HW_Exception.HW_Sem_03.controller.UserController;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.processUserInput(userController.getUserData());
    }

}
