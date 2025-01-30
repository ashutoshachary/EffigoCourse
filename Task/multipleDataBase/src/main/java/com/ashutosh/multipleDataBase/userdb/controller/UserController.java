package com.ashutosh.multipleDataBase.userdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashutosh.multipleDataBase.accountdb.entity.Account;
import com.ashutosh.multipleDataBase.accountdb.repository.AccountRepository;
import com.ashutosh.multipleDataBase.userdb.entity.User;
import com.ashutosh.multipleDataBase.userdb.repository.UserRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
	@Autowired
	AccountRepository accountRepository;
	
    @Autowired
    UserRepository userRepository;

    @PostMapping("api/v1/users")
    public User saveUser(@RequestBody User user){
        return userRepository.save(user);
    }
    @GetMapping("api/v1/users")
    public List<User> getUserList(){
        return userRepository.findAll();

    }
    @GetMapping("api/v1/users/{id}")
    public EntityModel<Optional<User>> getOneUser(@PathVariable Long id) throws Exception {
    	Optional<User> user = userRepository.findById(id);
		
		if(user==null) {
			throw new Exception("id: "+id);
		}
		EntityModel<Optional<User>> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUserList());
		
		entityModel.add(link.withRel("all-users"));
		return entityModel;
    }
    
    @PutMapping("api/v1/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        updatedUser.setId(id);
        User savedUser = userRepository.save(updatedUser);

        return ResponseEntity.ok(savedUser);
    }
    
    @DeleteMapping("api/v1/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    
    @PostMapping("api/v1/users/{id}/accounts")
    public Account saveAccount(@PathVariable Long id , @RequestBody Account account) {
    	account.setUser_id(id);
		return accountRepository.save(account);
    	
    	
    }
    
    @GetMapping("api/v1/users/{id}/accounts")
    public List<Account> getAccountsByUserId(@PathVariable Long id) {
        return accountRepository.findByUser_id(id);
    }
    
    @GetMapping("api/v1/users/{id}/accounts/{accId}")
    public ResponseEntity<EntityModel<Account>> getOneAccountForOneUser(@PathVariable Long id, @PathVariable Long accId) {
        Optional<Account> account = accountRepository.findById(accId);

        if (account.isEmpty() || !account.get().getUser_id().equals(id)) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Account> entityModel = EntityModel.of(account.get());

        WebMvcLinkBuilder userLink = linkTo(methodOn(this.getClass()).getUserList());
        WebMvcLinkBuilder accountsLink = linkTo(methodOn(this.getClass()).getAccountsByUserId(id));

        entityModel.add(userLink.withRel("all-users"));
        entityModel.add(accountsLink.withRel("all-accounts-for-user"));

        return ResponseEntity.ok(entityModel);
    }
    
    @PutMapping("api/v1/users/{id}/accounts/{accId}")
    public ResponseEntity<Account> updateAccountForUser(@PathVariable Long id, @PathVariable Long accId, @RequestBody Account updatedAccount) {
        Optional<Account> existingAccount = accountRepository.findById(accId);

        if (existingAccount.isEmpty() || !existingAccount.get().getUser_id().equals(id)) {
            return ResponseEntity.notFound().build();
        }

        updatedAccount.setId(accId);
        updatedAccount.setUser_id(id);
        
        Account savedAccount = accountRepository.save(updatedAccount);

        return ResponseEntity.ok(savedAccount);
    }
    
    
    @DeleteMapping("api/v1/users/{id}/accounts/{accId}")
    public ResponseEntity<Void> deleteAccountForUser(@PathVariable Long id, @PathVariable Long accId) {
        Optional<Account> account = accountRepository.findById(accId);

        if (account.isEmpty() || !account.get().getUser_id().equals(id)) {
            return ResponseEntity.notFound().build();
        }

        accountRepository.deleteById(accId);
        return ResponseEntity.noContent().build();
    }



    
    
    


}
