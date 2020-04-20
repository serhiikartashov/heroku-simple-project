# java-project-template
This is template for CI configurations

## launch
* ```mvn install```
* ```mvn jetty:run```

## heroku
https://devcenter.heroku.com/articles/heroku-cli-commands
* create account on heroku.com
* ```heroku login```
* ```heroku create java-project-template```
* ```heroku info```
* ```mvn clean heroku:deploy-war```
* ```heroku open```
* ```heroku logs```
* ```heroku logs -tn 20```
* ```heroku logs -tn 20 --source app```
* ```heroku logs -tn 20 -s app```

NOTE: make sure git added new heroku remote repo
```
git remote -v
heroku  https://git.heroku.com/java-project-template.git (fetch)
heroku  https://git.heroku.com/java-project-template.git (push)
origin  https://github.com/searhiy/java-project-template.git (fetch)
origin  https://github.com/searhiy/java-project-template.git (push)
```
NOTE: heroku filter logs
https://devcenter.heroku.com/articles/logging

NOTE: heroku postgresql
https://elements.heroku.com/addons/heroku-postgresql
https://devcenter.heroku.com/articles/heroku-postgresql
* ```heroku addons:create heroku-postgresql:hobby-dev --version=9.5```
* ```heroku pg:wait``` 
* ```heroku config``` -- will return connection $DATABASE_URL to database (AWS)
https://devcenter.heroku.com/articles/config-vars
* ```heroku run echo \$JDBC_DATABASE_URL``` -- use this url for JDBC
https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java
https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java#using-the-database_url-in-plain-jdbc
git://github.com/heroku/devcenter-java-database.git

###NOTE: java servlet annotations
https://www.codejava.net/java-ee/servlet/servlet-annotations-reference

* @WebServlet annotation
* @WebListener annotation
* @WebFilter annotation
* @WebInitParam annotation
* @HandlesTypes annotation
* @MultipartConfig annotation
* @ServletSecurity, @HttpMethodConstraint and @HttpConstraint annotations


https://api.nasa.gov/
BwKaaRv4epDjcbXXiLdcURczJyUwTkNrSabQHeZ9
i.e.
https://api.nasa.gov/planetary/apod?api_key=BwKaaRv4epDjcbXXiLdcURczJyUwTkNrSabQHeZ9


https://www.w3.org/TR/html-json-forms/
```
<form enctype='application/json'>
  <input name='name' value='Bender'>
  <select name='hind'>
    <option selected>Bitable</option>
    <option>Kickable</option>
  </select>
  <input type='checkbox' name='shiny' checked>
</form>

// produces
{
  "name":   "Bender"
, "hind":   "Bitable"
, "shiny":  true
}
```

https://www.w3schools.com/tags/att_form_action.asp
https://www.codejava.net/java-ee/servlet/handling-html-form-data-with-java-servlet