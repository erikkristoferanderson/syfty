package net.syfty.app.database.loader;

import net.syfty.app.database.entity.User;
import net.syfty.app.database.entity.UserRole;
import net.syfty.app.database.repository.UserRepository;
import net.syfty.app.database.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component  // uncomment this to load data in a fresh database
class DataLoader {

    Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    DataLoader(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }


    @PostConstruct
    private void loadData() {
        try {
            User user1 = new User("admin@syfty.net", "$2a$12$RxXO4c/xIZPdHa/b3v05TOa2pgsKidGGg.GQah0GEOukZwdKewwUe");
            userRepository.save(user1);
//            UserRole userRoleUser1User = new UserRole("USER", user1); // covered by "always add USER role"
//            userRoleRepository.save(userRoleUser1User);
            UserRole userRoleUser1Admin = new UserRole("ADMIN", user1);
            userRoleRepository.save(userRoleUser1Admin);
        } catch (Exception e) {
            logger.warn(String.valueOf(e));
        }
    }
}