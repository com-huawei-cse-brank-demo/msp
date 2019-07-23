/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.brank.chuyiting.chuyitingmsp.controller;

import cn.brank.chuyiting.chuyitingmsp.entity.User;
import cn.brank.chuyiting.chuyitingmsp.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@RestSchema(schemaId = "auth")
@RequestMapping(path = "auth")
public class AuthController {
  @Autowired
  private UserService userService;

  @PostMapping(path = "/auth")
  public boolean auth(@RequestParam(name = "token") String token) {
    String authHeader = token;
    System.out.println("dddd "+authHeader);
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return false;
    }
    final String jwtToken = authHeader.substring(7);
    String userName = Jwts.parser().setSigningKey("brankSecreteKey").parseClaimsJws(jwtToken).getBody().getSubject();
    if (userName == null){
      return false;
    }
    return true;
  }
  @GetMapping(path = "/login")
  public ResponseEntity<?> login(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
    User user = userService.get(name, password);
    String jwtToken = "";
    HttpHeaders headers = new HttpHeaders();
    try {
      jwtToken = Jwts.builder().setSubject(user.getUserName()).claim("roles","member").setIssuedAt(new Date()).
              signWith(SignatureAlgorithm.HS256, "brankSecreteKey").compact();
      System.out.println(jwtToken);
      headers = generateAuthHeader(jwtToken);
    }catch (Exception e){
    }
    InvocationException failResponse = new InvocationException(BAD_REQUEST,"用户名/密码不正确");
    if (user != null) {
      return new ResponseEntity<>(user, headers, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(failResponse, HttpStatus.BAD_REQUEST);
    }
  }



  private HttpHeaders generateAuthHeader(String token){
    HttpHeaders headers = new HttpHeaders();
    headers.add(AUTHORIZATION, token);
    return headers;
  }

}
