@ECHO OFF

SET fileJAR="Brak pliku"


for %%i in (*.jar) do (
set fileJAR=%%i%)

ECHO %fileJAR%


"D:\Program Files\jdk-19.0.1\bin\java"  -jar %fileJar%