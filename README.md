# java-project-template
This is template for CI configurations

## launch
* ```mvn install```
* ```mvn jetty:run```

## heroku
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

###NOTE: java servlet annotations
https://www.codejava.net/java-ee/servlet/servlet-annotations-reference

* @WebServlet annotation
* @WebListener annotation
* @WebFilter annotation
* @WebInitParam annotation
* @HandlesTypes annotation
* @MultipartConfig annotation
* @ServletSecurity, @HttpMethodConstraint and @HttpConstraint annotations
