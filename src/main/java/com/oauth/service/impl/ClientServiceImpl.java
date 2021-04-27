package com.oauth.service.impl;

import com.oauth.converter.ClientConverter;
import com.oauth.dao.ClientServerEntityMapper;
import com.oauth.entity.ClientServerEntity;
import com.oauth.service.ClientService;
import com.oauth.vo.Client;
import com.oauth.vo.ClientPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientServerEntityMapper clientServerEntityMapper;

  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    Client client = null;
    try{
      client = findClients(clientId);
    }catch(Exception e){
      e.printStackTrace();
    }
    if (ObjectUtils.isEmpty(client)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT_FOUND_CLIENT");
    }
    return new ClientPrincipal(client);
  }

  private Client findClients(String clientId){
    ClientServerEntity clients = clientServerEntityMapper.selectByClientId(clientId);
    Client client = new Client();
    BeanCopier copier = BeanCopier.create(ClientServerEntity.class, Client.class, true);
    copier.copy(clients, client,new ClientConverter());
    return client;
  }

}
