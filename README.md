# onanismo
## Requirements
```
java jdk 1.8.0
postgres 10.6
maven 3.6.0
heroku 7.19.4
```
## Database operations
```
heroku pg:push DATABASE DATABASE_URL --app fathomless-brushlands-85867
heroku pg:pull DATABASE_URL DATABASE --app fathomless-brushlands-85867
```
## Deploying
```
mvn package
git push heroku master
```
