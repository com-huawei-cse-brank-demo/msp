package cn.brank.chuyiting.chuyitingmsp.auth;

import cn.brank.chuyiting.chuyitingmsp.entity.User;
import cn.brank.chuyiting.chuyitingmsp.result.Result;
import cn.brank.chuyiting.chuyitingmsp.service.UserService;
import io.swagger.annotations.ApiParam;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@RestSchema(schemaId = "user")
@RequestMapping(path = "user/v1")
public class UserServiceImpl {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/{name}/{password}", method = RequestMethod.GET)
	public Object query(@PathVariable("name") String name, @PathVariable("password") String password) {
		User user = userService.get(name, password);
		InvocationException failResponse = new InvocationException(BAD_REQUEST,"用户名/密码不正确");

		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK).getBody();
		} else {
			return new ResponseEntity<>(failResponse, HttpStatus.BAD_REQUEST).getBody().getMessage();
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public Object updatePassword(@RequestParam String name, @RequestParam String oldPassword, @RequestParam String newPassword) {


		int i = userService.update(name, oldPassword,newPassword);

		InvocationException failResponse1 = new InvocationException(BAD_REQUEST,"用户名不存在");
		InvocationException failResponse2 = new InvocationException(BAD_REQUEST,"密码不正确");

		if (i == 0){
			return new ResponseEntity<>(failResponse1, HttpStatus.BAD_REQUEST).getBody().getMessage();
		}else if(i == -1){
			return new ResponseEntity<>(failResponse2, HttpStatus.BAD_REQUEST).getBody().getMessage();
		}
		else {
			return new ResponseEntity<>(Result.success("修改成功"), HttpStatus.OK).getBody().getMessage();
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public Object registrationAccount(@ApiParam("用户名")@RequestParam("name")String name, @ApiParam("密码")@RequestParam("password") String password) {
		User user = new User(name, password);
		int i = userService.save(user);

		InvocationException failResponse = new InvocationException(BAD_REQUEST,"用户已存在");
		if (i != 0) {
			return new ResponseEntity<>(Result.success("注册用户成功"), HttpStatus.OK).getBody().getMessage();
		} else {
			return new ResponseEntity<>(failResponse, HttpStatus.BAD_REQUEST).getBody().getMessage();
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.DELETE)
	@ResponseBody
	public Object delete(@ApiParam("用户名")@RequestParam("name")String name,@ApiParam("密码")@RequestParam("password") String password) {
		User user = new User(name, password);
		int i = userService.delete(user);

		InvocationException failResponse1 = new InvocationException(BAD_REQUEST,"用户名不存在");
		InvocationException failResponse2 = new InvocationException(BAD_REQUEST,"密码不正确");
		if (i == 0){
			return new ResponseEntity<>(failResponse1, HttpStatus.NOT_FOUND).getBody().getMessage();
		}else if(i == -1){
			return new ResponseEntity<>(failResponse2, HttpStatus.NOT_FOUND).getBody().getMessage();
		}
		else {
			return new ResponseEntity<>(Result.success("注销用户成功"), HttpStatus.OK).getBody().getMessage();
		}
	}
}
