@echo off
For /f "tokens=2-4 delims=/ " %%a in ('date /t') do (set mydate=%%c%%a%%b)
For /f "tokens=1-2 delims=/:" %%a in ("%TIME%") do (set mytime=%%a%%b)
SET timestamp=%mydate%%mytime%
echo Publishing with time stamp : %timestamp%
docker tag mic-utilisateur-gestion-server 10.3.4.18:5000/mic-utilisateur-gestion-server:%timestamp%
docker push 10.3.4.18:5000/mic-utilisateur-gestion-server:%timestamp%