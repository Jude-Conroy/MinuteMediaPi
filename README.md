# MinuteMediaPi


Application Server Software
------------

    Download the version specific for the OS
    https://docs.mongodb.com/manual/tutorial/
    #Great Client UI tool for admin
    https://github.com/rsercano/mongoclient
    #Java 8
    http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html
    
Development and build software
------------

    Download Maven https://maven.apache.org/install.html and install
    Download ZIP file from this respsitory and drop it into a directory
    
    Unzip and modify the hostname and port of the mongodb running instance in piConfig.yml
    
    # Change default server ports
    server:
      applicationConnectors:
      - type: http
        port: 9000
      adminConnectors:
      - type: http
        port: 9001
    
    mongo:
      seeds:
      - host: servername
        port: 27017
      database: piData
    #  credentials:
    #    userName: superuser
    #    password: 12345678
    
Once the code is compiled run the following from the command line.

    mvn package

mvn package will create the original-pi-0.0.1-SNAPSHOT.jar, this is now bitecode.

To run from the command line, navigate to the app root directory (level containing the piConfig.yml)

    java -jar /original-pi-0.0.1-SNAPSHOT.jar server piConfig.yml
   
You now have a running web server waiting for submits.

Client test
------------

Either run the following from the command line
curl -v -F "filename=@/Users/Jude/Downloads/custom/json2.txt" http://localhost:8090/uploadFile

or update custom.sh

Remove
----------
if [ \$count -gt 9 ]; then
        gzip json.txt

Modify
----------
curl --form uploaded=@//tmp/json.txt.gz --form submit=Upload http://[]/upload.php

To use the following 
----------
curl -v -F "filename=@//tmp/json.txt" http://localhost:8090/uploadFile

NOTES 
----------
File compression/decompression not added
    
