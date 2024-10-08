# common starter
echo "building common starter"
cd order-starter
./gradlew clean publishToMavenLocal 
cd ..
echo "build was finished!"

echo "starting build all services..."

# coffein cache service with postgres
echo "building cf-db services..."
cd cf-db-service

echo "prepare image..."
./gradlew clean build

echo "build image..."
cat Dockerfile
sudo docker build -t ere/cf-db-service .
cd ..
pwd

echo "done!"

# echo "building cf-kafka services..."
# # todo...