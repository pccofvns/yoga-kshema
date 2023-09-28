# Income Tax Calculation using Decision Model Notation

# Running

- Compile and Run

  ```
  mvn clean package spring-boot:run
  ```

# Test your application

Generated application comes with sample test process that allows you to verify if the application is working as expected. Simply execute following command to try it out

```sh
curl -X 'POST' \
  'http://localhost:8080/TaxCalculation' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "IncomeTaxRequest": {
    "gender": "male",
    "taxableIncome": 900000,
    "age": 20,
    "regime": "OLD"
  }
}'

```

Once successfully invoked you should see following output in the console of the running application.

```json
{
  "IncomeTaxResponse": {
    "amount": 0,
    "slab": 0
  },
  "IncomeTaxRequest": {
    "gender": "male",
    "taxableIncome": 0,
    "age": 120,
    "regime": "OLD"
  }
}
```

The generated application provides out of the box multiple samples of Kogito assets; you can reference the generated Swagger documentation and JUnit tests.

# Developing

Add your business assets resources (process definition, rules, decisions) into src/main/resources.

Add your java classes (data model, utilities, services) into src/main/java.

Then just build the project and run.

# OpenAPI (Swagger) documentation

[Specification at swagger.io](https://swagger.io/docs/specification/about/)

You can take a look at the [OpenAPI definition](http://localhost:8080/v3/api-docs) - automatically generated and included in this service - to determine all available operations exposed by this service. For easy readability you can visualize the OpenAPI definition file using a UI tool like for example available [Swagger UI](https://editor.swagger.io).

In addition, various clients to interact with this service can be easily generated using this OpenAPI definition.
