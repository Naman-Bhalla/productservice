### start mysql container
docker run -p 3306:3306 --name my-mysql -e MYSQL_ROOT_PASSWORD=secret -v $HOME/mysql-data:/var/lib/mysql -d mysql:latest