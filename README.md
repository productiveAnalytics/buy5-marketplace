Server side of buy5-marketplace

localhost:8080/products (GET: all products)
localhost:8080/product/id/1 (GET: product by {id})
localhost:8080/product/name/iPhoneX (GET: product by {name})

localhost:8080/users (GET: all users or persons)
localhost:8080/user/larry (GET: user or person by {userName})

===

See TestCartController class for good user of RestTemplate client