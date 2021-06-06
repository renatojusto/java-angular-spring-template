build:
	./mvnw -f ./backend/pom.xml -Pprod verify jib:dockerBuild -Pintegration-test
