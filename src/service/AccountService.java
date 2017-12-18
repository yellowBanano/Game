package service;

import dao.AccountDao;
import dto.account.CreateNewAccountDto;
import dto.account.ViewAccountFullInfoDto;
import dto.account.ViewAccountGeneralInfo;
import entity.Account;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountService {
    private static AccountService INSTANCE = null;

    private AccountService() {}

    public static AccountService getInstance() {
        if (INSTANCE == null) {
            synchronized (AccountService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AccountService();
                }
            }
        }
        return INSTANCE;
    }

    public List<ViewAccountGeneralInfo> getAllAccounts() {
        return AccountDao.getInstance().findAll().stream()
                .map(account -> new ViewAccountGeneralInfo(account.getId(), account.getEmail(), account.getLogin()))
                .collect(Collectors.toList());
    }

    public Account find(String email, String password) {
        return AccountDao.getInstance().findAll().stream()
                .filter(account -> account.getEmail().equals(email) && account.getPassword().equals(password))
                .findFirst().orElse(null);
    }

    public void createNewAccount(CreateNewAccountDto dto) {
        AccountDao.getInstance().create(new Account(dto.getEmail(), dto.getLogin(), dto.getPassword()), dto.getIdAvatar());
    }

    public ViewAccountFullInfoDto getFullInfo(long id) {
        Optional<Account> accountOptional = AccountDao.getInstance().findById(id);
        if (!accountOptional.isPresent()) {
            throw new IllegalArgumentException("No account with provided id!");
        }
        Account account = accountOptional.get();
        return new ViewAccountFullInfoDto(account.getEmail(), account.getLogin(),
                account.getPassword(), account.getAvatar().getName(), account.getAvatar().getId());
    }
}
