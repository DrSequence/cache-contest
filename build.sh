echo "starting build all services..."

# cofein cache service with postgres
echo "building cf-db services..."
cd cf-db-service

echo "prepare image..."
./gradlew clean build

echo "build image..."
cat Dockerfile
docker build -t ere/cf-db-service .
cd ..
pwd

echo "done!"

echo "building cf-kafka services..."
# todo...