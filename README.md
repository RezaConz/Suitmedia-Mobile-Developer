# Android Suitmedia Mobile Test

<img src="https://i.postimg.cc/Jh5VFtqy/Screen-Shot-2023-01-16-at-01-22-21.png" />

## ABOUT
Android application projects developed during the Suitmedia Mobile Developer Test for MBKM Batch 7

## CLONE REPO
To get the *completed code*, clone the repo or download the repo

## DESIGN
The design was provided by Suitmedia in the context of selection as a mobile developer.

## API CONSUME
This application consumed the API from <a href="https://reqres.in">Reqres In</a>

### Get Users

Request :

- Method : GET
- Endpoint : `/api/users`
- Query Param:
  - page: integer
  - per_page: integer

Response :

```json
{
  "page":1,
  "per_page":10,
  "total":12,
  "total_pages":2,
  "data":[
    {
      "id":1,
      "email":"george.bluth@reqres.in",
      "first_name":"George",
      "last_name":"Bluth",
      "avatar":"https://reqres.in/img/faces/1-image.jpg"
    },...],
  "support":{"url":"https://reqres.in/#support-heading","text":"To keep ReqRes free, contributions towards server costs are appreciated!"}
}
```

## RESULT


