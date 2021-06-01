rootProject.name = "sample-bff"

include(":sample-bff-api")
include(":sample-bff-business")
include(":sample-bff-third-party")
include(":sample-bff-database")
include(":sample-bff-webservice")

project(":sample-bff-api").projectDir = file("api")
project(":sample-bff-business").projectDir = file("business")
project(":sample-bff-third-party").projectDir = file("third-party")
project(":sample-bff-database").projectDir = file("database")
project(":sample-bff-webservice").projectDir = file("webservice")
