package hexlet.code.service;

import hexlet.code.Dto.UserDto;
import hexlet.code.model.User;

public interface UserService {
    User createUser(UserDto userDto) throws Exception;
    User updateUser(long id, UserDto userDto) throws Exception;
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);

    void checkUserAssociatedWithTasks(User user);

    String getCurrentUserName();

    User getCurrentUser();
}