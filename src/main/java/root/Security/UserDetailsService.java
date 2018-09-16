package root.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;

import root.Entitys.RoleEntity;
import root.Entitys.UserEntity;
import root.Repositories.UserEntityRepository;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService
{


    @Autowired
    UserEntityRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserEntity user;
        // TODO: 15.09.2018 запилить нормальную регулярку для номера телефна
        if (username.startsWith("+79") || username.startsWith("79") || username.startsWith("89"))
        {
            user = users.findOneByphone(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User with phone: " + username + " was not found"));
        }
        else
        {
            user = users.findOneByusername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User wtih username: " + username + " was not found"));
        }
//            if (user == null){
//                throw new UsernameNotFoundException(username + " was not found");
//            }


        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(user.getRolesArray()
                ));
    }

}