#!/bin/bash

URL="http://localhost:8081/pagamento/consultar/1"
TIMEOUT=60
END=$((SECONDS + TIMEOUT))

while [ $SECONDS -lt $END ]; do
    STATUS=$(curl -s -o /dev/null -w "%{http_code}" $URL)
    if [ $STATUS -eq 400 ]; then
        echo "Application ok!"
        exit 0
    fi
    sleep 1
done

echo "Timeout atingido!"
exit 1
