
```
USING EXTERNAL FUNCTION unmarshal(input VARCHAR) RETURNS VARCHAR LAMBDA 'DdbUnmarshalCS'
SELECT unmarshal('{"hello":{"S":"world"},"GG":{"S":"yy"}}')
```