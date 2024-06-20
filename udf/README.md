$ mvn clean install

$ mvn -B archetype:generate \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DgroupId=com.johnsonfitness \
  -DartifactId=aws-athena-udfs-ddb-unmarshal

$ aws lambda create-function \
  --function-name DdbUnmarshalCS \
  --runtime java11 \
  --role arn:aws:iam::304179817986:role/service-role/DdbUnmarshal-role-plyjlxqz \
  --handler com.johnsonfitness.DdbUnmarshal\
  --timeout 900 \
  --memory 1024 \
  --zip-file fileb://./target/aws-athena-udfs-ddb-unmarshal-1.0-SNAPSHOT.jar