cd web
mvn clean install -Dmaven.test.skip=true

cd ..
docker-compose build

docker-compose down

docker-compose up
