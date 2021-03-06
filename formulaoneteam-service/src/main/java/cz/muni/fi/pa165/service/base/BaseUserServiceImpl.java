package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.dao.base.UserDao;
import cz.muni.fi.pa165.entity.base.User;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import cz.muni.fi.pa165.service.utils.Validator;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseUserServiceImpl<T extends User, D extends UserDao<T>>
        extends BaseServiceImpl<T, D> implements BaseUserService<T> {

    @Override
    public T register(T user, String unencryptedPassword) {
        if (unencryptedPassword == null || unencryptedPassword.isEmpty()) {
            throw new FormulaOneTeamException("Password can't be empty");
        }
        user.setPassword(Validator.createHash(unencryptedPassword));

        dao.add(user);

        return user;
    }

    @Override
    public boolean authenticate(String email, String password) {
        return Validator.validatePassword(password, dao.findByEmail(email).getPassword());
    }

    @Override
    public T findByEmail(String email) {
        return dao.findByEmail(email);
    }
}
