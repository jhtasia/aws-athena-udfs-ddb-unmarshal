
Below is the steps to build the Athena UDF - DDB Unmarshal.
```console
$ cd cdk
$ npx projen build
$ npx projen deploy
```

In Athena query editor, you can test the following SQL. You can run UDF by different lambda versions as well.
```sql
USING EXTERNAL FUNCTION unmarshal(input VARCHAR) RETURNS VARCHAR LAMBDA 'DdbUnmarshalCS'
SELECT unmarshal('{"hello":{"S":"world"},"GG":{"S":"yy"}}')

-- 
USING EXTERNAL FUNCTION unmarshal(input VARCHAR) RETURNS VARCHAR LAMBDA 'DdbUnmarshalCS:9'
SELECT unmarshal('{"hello":{"S":"world"},"GG":{"S":"yy"}}')

```