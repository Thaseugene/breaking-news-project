package by.itacademy.news.service.impl;

import by.itacademy.news.model.User;
import by.itacademy.news.model.constants.Role;
import by.itacademy.news.repository.IUserRepository;
import by.itacademy.news.repository.RepositoryProvider;
import by.itacademy.news.repository.UserRepositoryException;
import by.itacademy.news.service.IUserService;
import by.itacademy.news.service.exception.IncorrectLoginException;
import by.itacademy.news.service.exception.UserExistsException;
import by.itacademy.news.service.exception.UserServiceException;
import by.itacademy.news.service.exception.ValidationServiceException;
import by.itacademy.news.util.validation.user.UserValidationResult;
import by.itacademy.news.util.validation.user.impl.FullUserValidatorChain;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class UserService implements IUserService {

    private final IUserRepository userRepository = RepositoryProvider.getInstance().getUserRepository();
    private final Semaphore semaphore = new Semaphore(1);

    public UserService() {

    }

    @Override
    public void addNewUser(String name, String surname, String email, String login, String password, String confirmPassword) throws
            UserServiceException,
            UserExistsException,
            ValidationServiceException {
        try {
            semaphore.acquire();
            if (!userRepository.checkIsLoginExists(login)) {
                User user = new User(
                        (new Random()).nextInt(),
                        name,
                        surname,
                        email,
                        login,
                        password,
                        Role.USER,
                        new Date(),
                        true);
                UserValidationResult result = new FullUserValidatorChain().validate(user);
                if (result.isValid()) {
                    userRepository.addNewUser(user);
                } else {
                    throw new ValidationServiceException(result.getErrorMessages().get(0).getMessage());
                }

            } else {
                throw new UserExistsException("User with this login already exists");
            }
        } catch (UserRepositoryException | InterruptedException e) {
            throw new UserServiceException(e);
        } finally {
            semaphore.release();
        }
    }

    @Override
    public User getUserByLoginAndPass(String login, String password) throws
            UserServiceException,
            IncorrectLoginException {

            try {
                int id = userRepository.takeUsersIdByLogin(login, password);
                if (id != 0) {
                    return userRepository.takeUserById(id);
                } else {
                    throw new IncorrectLoginException();
                }
            } catch (UserRepositoryException e) {
                throw new UserServiceException(e);
            }
    }

}
