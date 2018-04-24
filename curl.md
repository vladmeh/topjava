### meals getAll

```bash
curl -s http://localhost:8080/topjava/rest/profile/meals
```

### meals get

```bash
curl -s http://localhost:8080/topjava/rest/profile/meals/100005
```

### meals create

```bash
curl -s -X POST -d '{"dateTime": "2015-06-01T18:00","description": "New meals","calories": 300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/profile/meals
```

### meals delete

```bash
curl -s -X DELETE http://localhost:8080/topjava/rest/profile/meals/100002
```

### meals update

```bash
curl -s -X PUT -d '{"dateTime": "2015-05-30T10:00","description": "Update meals","calories": 400}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/profile/meals/100005
```

### meals filter

```bash
curl -sb "startDate=2015-05-31;endTime=17:00:00"  http://localhost:8080/topjava/rest/profile/meals/filter
```
