package com.example.empresasjava.controller;

import com.example.empresasjava.configs.jwt.JwtTokenUtil;
import com.example.empresasjava.models.RequestEntity.LoginRequest;
import com.example.empresasjava.models.ResponseEntity.LoginResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.service.UserService;
import com.example.empresasjava.service.impl.JwtUserDetailsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	@ApiOperation(value = "Gerar token de autenticação caso email e senha sejam válidos")
	public ResponseEntity<?> createAuthenticationToken(
			@ApiParam(value = "Json referente a autenticação")
			@RequestBody LoginRequest authenticationRequest) throws Exception {

		final User userDetails = this.userService.findByEmail(authenticationRequest.getEmail())
				.orElseThrow(()->new BadCredentialsException("Email ou senha incorreto!"));

		ResponseEntity<String> authenticate = authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		if(authenticate.getStatusCode().equals(HttpStatus.OK)){
			final String token = this.jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(new LoginResponse(token));
		}

		return authenticate;

	}


	private ResponseEntity<String> authenticate(String username, String password) throws Exception {
		try {
			Optional<User> user = this.userService.findByEmail(username);
			if (user.isPresent()) {
				if (null != user.get().getDeletedAt()) {
					return new ResponseEntity<>("Usuário desativado!", HttpStatus.UNAUTHORIZED);
				}
			}
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch (BadCredentialsException e){
			throw new BadCredentialsException("Email ou senha incorreto!");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
