#!/bin/bash

# Navigate to the Tomcat bin directory
cd /Users/meghnadsaha/IdeaProjects/server/apache-tomcat-9.0.98/bin

# Echo the current directory
echo "Current Directory: $(pwd)"

# Echo statement indicating Tomcat server stopping
echo "Stopping Tomcat server..."

# Stop the Tomcat server
./shutdown.sh
#./catalina.sh jpda stop

# Wait for 5 seconds to ensure the server stops properly
echo "Waiting for the Tomcat server to stop..."
sleep 5

# Echo statement indicating Tomcat server has been stopped
echo "Tomcat server stopped successfully."

# Uncomment below line if you want to start Tomcat with JPDA debugging
# ./catalina.sh jpda start
