package com.oauth.controller;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/rsa")
public class KeyPairController {

  @Autowired
  private KeyPair keyPair;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping("/publicKey")
  @ResponseBody
  public JSONObject publicKey() {
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    RSAKey key = new RSAKey.Builder(publicKey).build();
    return new JWKSet(key).toJSONObject();
  }

  @GetMapping("/encryption")
  @ResponseBody
  public JSONObject encryption(@RequestParam("word") String word) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("ciphertext",passwordEncoder.encode(word));
    return jsonObject;
  }

}
