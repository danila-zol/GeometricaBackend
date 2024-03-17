Set-PSDebug -Trace 1
curl -i  -H "Content-Type:application/json" -d '{"type": "Granite", "name": "Blackrock", "description": "Nice black rock"}' http://localhost:8080/tiles
curl -i  -H "Content-Type:application/json" -d '{"firstName": "Frodo", "lastName": "Dessler"}' http://localhost:8080/customers
curl -i  -H "Content-Type:application/json" -d '{"file": "TEVFVA==", "name": "1337", "type": "text", "illustrates": [1]}' http://localhost:8080/media/upload
