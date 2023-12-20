# Requests:

### Reactive Client (Request to reactive-server):
```
curl --location 'http://localhost:8081/v1/transactions' \
--form 'file=@"/path/to/file"' \
--form 'user="{
   \"name\":\"Leonardo\"
}";type=application/json'
```
### Reactive Client (Request to non-reactive-server):
```
curl --location 'http://localhost:8081/v1/transactions/non-reactive' \
--form 'file=@"/path/to/file"' \
--form 'user="{
   \"name\":\"Leonardo\"
}";type=application/json'
```

### Reactive Server:
```
curl --location 'http://localhost:8080/v1/operations' \
--form 'file=@"/path/to/file"' \
--form 'user="{
   \"name\":\"Leonardo\"
}";type=application/json'
```
